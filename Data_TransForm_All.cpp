#include<iostream>
#include<fstream>
#include<vector>
#include<string>
#include<sstream>
#include<algorithm>
#include<cmath>
using namespace std;

bool cmp1(const vector<string> &a, const vector<string> &b){
	if(a[0] != b[0]){
		return a[0] < b[0];
	}
	if(a[24] != b[24]){
		return a[24] < b[24];
	}
}

long double average(long double sum, int n){
	return sum/n;
}

long double sd(long double ave, vector<long double> SDTime){
	
	long double sum = 0.0;
	
	for(int i = 0; i < SDTime.size(); i++){
		sum += ((SDTime[i] - ave) * (SDTime[i] - ave));
	}
	sum = sum / SDTime.size();
	sum = sqrt(sum);
	return sum;
}

int CaculateWeekDay(int y, int m, int d){
	if (m == 1 || m == 2){
		m += 12;
		y--;
	}
	int iWeek = (d + 2 * m + 3 * (m + 1) / 5 + y + y / 4 - y / 100 + y / 400) % 7;
	return iWeek+1;
}

int main(int argc, char const *argv[]){
	string CutPath = "";//要清洗的檔案路徑
	string FileYear = "";//檔案年份
	vector<string> FileName;//檔案名稱
	
    fstream SourceTxt("javatoc", ios::in);//開啟txt讀取檔案路徑 
	getline(SourceTxt, CutPath);//獲得路徑 
	cout<<CutPath<<endl;
	int Position = CutPath.rfind('\\', CutPath.size());
	for(int i = Position - 4; i < Position; i++){
		FileYear += CutPath[i];
	}
	
	
	string listPath ="dir " + CutPath + " /b /on >list.txt";//列出要讀取的檔案名稱
	cout<<listPath.c_str()<<endl;
	system(listPath.c_str());
    fstream f("list.txt", ios::in);//開啟要讀取檔案名稱的檔案 
    
    for(string s;f >> s;) {
        FileName.push_back(s);
    }//加入FileName的佇列
    
    for(int ii = 0; ii < FileName.size(); ii++){
    	string FilePath = CutPath + "\\" + FileName[ii];
		fstream Doc(FilePath, ios::in);//開啟該檔案
		stringstream ss;
		string TempContent;
		vector<vector<string>> FileContent;
		
		while(getline(Doc, TempContent)){
			ss << TempContent;
			vector<string> RowContent;
			string s;
			//cout<<TempContent<<endl;
			for(int i = 0; getline(ss, s, ','); i++){
				RowContent.push_back(s);
			}
			FileContent.push_back(RowContent);
			ss.str("");
			ss.clear();
			
		}//逐行讀取Doc檔案內容，將每個Column分開後，加入FileContent
		
		if(FileContent.size() != 1){
			
			vector<vector<string>>::iterator it;
			for(it = FileContent.begin(); it < FileContent.end(); it++){
				if(it == FileContent.begin() && (*it)[0] != (*(it+1))[0]){
					FileContent.erase(it);
					it == FileContent.begin();
					if(FileContent.size() == 1){
						FileContent.erase(it);
						break;
					}
					it--;
				}else if(it == FileContent.end()-1 && (*it)[0] != (*(it-1))[0]){
					FileContent.erase(it);
					
				}else if(it != FileContent.end()-1 && (*it)[0] != (*(it+1))[0] && (*it)[0] != (*(it-1))[0]){
					FileContent.erase(it);
					it--;
				}
			}
		}else{
			vector<vector<string>>::iterator it = FileContent.begin();
			FileContent.erase(it);			
		}/*若檔案內容只有一筆，則直接刪除
			若超過一筆，開始判斷是否有單日看診，
			若第一筆即為當日看診，則刪除後重頭開始
			其餘則刪除後繼續。*/ 
		if(FileContent.size() == 0){
			continue;
		}
		
		sort(FileContent.begin(), FileContent.end(), cmp1);//排序看診資料 
		
		/*long double SumForAve = 0.0;
		long double AverageTime = 0.0;
		long double SD;
		vector<long double> SDTime;
		for(int i = 0; i < FileContent.size(); i++){
		
			if(FileContent[i][34] >  FileContent[i][24]){
				FileContent[i].push_back("0");
				SDTime.push_back(0.0);
				continue;
			}
			stringstream DtoS,AP,AC;
			string APT,ACT,STotalTime;
			long double APA[3] = {0.0};
			long double ACA[3] = {0.0};
			long double DTotalTime;
			AP << FileContent[i][34];
			AC << FileContent[i][24];
			
			for(int j = 0; j < 3; j++){
				getline(AP, APT, ':');
				getline(AC, ACT, ':');
				APA[j] = stod(APT);
				ACA[j] = stod(ACT);
			}
			
			DTotalTime = (ACA[0] - APA[0]) * 60 + (ACA[1] - APA[1]) + (ACA[2] - APA[2]) / 60;
			DtoS << DTotalTime;
			DtoS >> STotalTime;
			FileContent[i].push_back(STotalTime);
			
			SumForAve += DTotalTime;
			SDTime.push_back(DTotalTime);
		}//計算等待時間&平均值 
		AverageTime = average(SumForAve,FileContent.size());//平均值 
		SD = sd(AverageTime, SDTime);//標準差*/ 
		
		//診療時間計算開始 
		vector<long double> DocTime;
		long double SumOfDoc = 0.0;
		long double DocAverageTime = 0.0;
		long double DocSD = 0.0;
		for(int i = 0; i < FileContent.size(); i++){
			
			string DocIn, DocOut, NextDoc, DocWriteString;//DocIn = 插卡 DocOut = 批價 NextDoc = 下一位的插卡 
			stringstream SDO,SDI,DocWrite;
			long double DO[3] = {0.0};//紀錄轉換後的 DocOut
			long double DI[3] = {0.0};//紀錄轉換後的 DocIn 
			long double DocTotal;
			
			if(i == FileContent.size()-1){
				FileContent[i].push_back("0");
				DocTime.push_back(0);
				break;
			}
			
			if(FileContent[i][0] == FileContent[i+1][0]){
				for(int j = 25; j < 34; j++){
					if(FileContent[i][j] == "00:00:00"){
						if(j == 25){
							DocOut = FileContent[i+1][24];
							break;
						}
						DocOut = FileContent[i][j-1];
						break;
					}else{
						DocOut = FileContent[i][33];
					}
				}
				
				DocIn = FileContent[i][24];
				NextDoc = FileContent[i+1][24];
				
				if(NextDoc <= DocOut){
					DocOut = NextDoc;
				}
				if(DocOut <= DocIn){
					DocOut = FileContent[i+1][24];
				}
				
				SDO << DocOut;
				SDI << DocIn;
				//cout<<"DocOut : "<<DocOut<<" DocIn : "<<DocIn<<endl;
				for(int j = 0; j < 3; j++){
					string I, O;
					getline(SDO, O, ':');
					getline(SDI, I, ':');
					//cout<<"O : "<<O<<" I : "<<I<<endl;
					DO[j] = stod(O);
					DI[j] = stod(I);
				}
				DocTotal = (DO[0] - DI[0]) * 60 + (DO[1] - DI[1]) + (DO[2] - DI[2]) / 60;
				DocWrite << DocTotal;
				DocWrite >> DocWriteString;
				FileContent[i].push_back(DocWriteString);
				DocTime.push_back(DocTotal);
				SumOfDoc += DocTotal;
			}else{
				FileContent[i].push_back("0");
				DocTime.push_back(0);
			}
		}
		
		DocAverageTime = average(SumOfDoc, FileContent.size());
		DocSD = sd(DocAverageTime, DocTime);
		//cout<<"平均："<<DocAverageTime<<" 標準差："<<DocSD<<endl; 
		long double Cluster[10] = {0.0};
		for(int i = 0; i < 10; i++){
			Cluster[i] = DocAverageTime + ((-2 + (i / 2.0)) * DocSD);
			//cout<<"Cluster "<<i<<" :"<<Cluster[i]<<endl;
		}
		for(int i = 0; i < FileContent.size(); i++){
			bool Flag = false;
			for(int j = 0; j < 9; j++){
				if(DocTime[i] <= Cluster[j]){
					FileContent[i].push_back(to_string(j+1));
					Flag = true;
					break;
				}
			}
			if(!Flag){
				FileContent[i].push_back(to_string(10));
			}
		}
		//診療計算結束
		
		//星期計算
		for(int i = 0; i < FileContent.size(); i++){
			int Week[3] = {0};
			int Age[3] = {0};
			int TheDay, year, month, day;
			stringstream WeekTrans, AgeTrans,tempS;
			WeekTrans << FileContent[i][0];
			AgeTrans << FileContent[i][7];
			for(int j = 0; j < 3; j++){
				string Wtemp = "";
				string Atemp = "";
				getline(WeekTrans, Wtemp, '-');
				getline(AgeTrans, Atemp, '-');
				tempS<<Wtemp;
				tempS>>Week[j];
				tempS.str("");
				tempS.clear();
				tempS<<Atemp;
				tempS>>Age[j];
				tempS.str("");
				tempS.clear();
			}
			TheDay = CaculateWeekDay(Week[0], Week[1], Week[2]);
			FileContent[i].push_back(to_string(TheDay));
			
			year = Week[0] - Age[0];
			month = Week[1] - Age[1];
			day = Week[2] - Age[2];
			
			if(month < 0){
				year -= 1; 
			}else if(month == 0){
				if(day < 0){
					year -= 1;
				}
			}
			
			if(year <= 6){
				FileContent[i].push_back("1");
			}else if(year >= 7 && year <= 17){
				FileContent[i].push_back("2");
			}else if(year >= 18 && year <= 65){
				FileContent[i].push_back("3");
			}else if(year >= 66 && year <= 79){
				FileContent[i].push_back("4");
			}else if(year >= 80 && year <= 99){
				FileContent[i].push_back("5");
			}else if(year >= 100){
				FileContent[i].push_back("6");
			}	 
		} 
		
		//早午診&實際看診順序&掛號類別 
		double SeeDocOrder = 0.1;
		for(int i = 0; i < FileContent.size(); i++){
			//早午診 
			if(FileContent[i][11] == "A"){
				FileContent[i].push_back("1");
			}else{
				FileContent[i].push_back("2");
			}
			
			//看診順序
			FileContent[i].push_back(to_string(SeeDocOrder));
			SeeDocOrder += 0.1;
			if(i+1 != FileContent.size() && FileContent[i][0] != FileContent[i+1][0]){
				SeeDocOrder = 1;
			}
			
			//掛號類別 
			char Oringin = FileContent[i][8][0];
			switch(Oringin){
				
				case 'W':
					FileContent[i].push_back("9");
					break;
				
				case 'E':
					FileContent[i].push_back("10");
					break;
				
				case 'O':
					FileContent[i].push_back("11");
					break;
					
				case 'G':
					FileContent[i].push_back("12");
					break;
				
				case 'S':
					FileContent[i].push_back("13");
					break;
				
				case 'I':
					FileContent[i].push_back("14");
					break;
				
				case 'J':
					FileContent[i].push_back("15");
					break;
				
				case 'K':
					FileContent[i].push_back("16");
					break;
				
				case 'U':
					FileContent[i].push_back("17");
					break;
				
				case 'H':
					FileContent[i].push_back("18");
					break;
				
				case 'F':
					FileContent[i].push_back("19");
					break;
					
				case 'V':
					FileContent[i].push_back("20");
					break;
				
				case 'X':
					FileContent[i].push_back("21");
					break;
				
				case 'Y':
					FileContent[i].push_back("22");
					break;
				
				case 'Z':
					FileContent[i].push_back("23");
					break;
				
				case 'C':
					FileContent[i].push_back("24");
					break;
					
				case 'Q':
					FileContent[i].push_back("25");
					break;
				
				default:
					FileContent[i].push_back(FileContent[i][8]);
			}
			
			//性別
			if(FileContent[i][6] == "M"){
				FileContent[i].push_back("0");
			}else{
				FileContent[i].push_back("1");
			}
			
			//前次是否看診
			if(FileContent[i][12] == "Y"){
				FileContent[i].push_back("1");
			}else{
				FileContent[i].push_back("0");
			}
			
			//初複診 
			if(FileContent[i][9] == "Y"){
				FileContent[i].push_back("1");
			}else{
				FileContent[i].push_back("0");
			}  
		} 
		 
		string name = FileName[ii];
		string C_name = FileName[ii];
		string OutPath = "Hos_Data\\Doc_Train\\Wa_Ch\\" + FileYear;
		string DirPath = "mkdir " + OutPath;
		system(DirPath.c_str());
		C_name.replace(4,C_name.size(),"_Cluster.txt");
		//更改檔案名稱
		 
		string OutFilePath = OutPath + "\\" + name;
		string C_OutFilePath = OutPath + "\\" + C_name;
		
		fstream OutFile(OutFilePath, ios::out);
		fstream C_OutFile(C_OutFilePath, ios::out);
		
		
		string Title = "看診日期,看診科別,看診診間,病歷號,醫師卡號,跟診護理師卡號,病患性別,病患出生日期,掛號類別,初複診,掛號序號,班別早午診,前次是否開立醫囑,診斷碼1,診斷碼2,診斷碼3,診斷碼4,診斷碼5,診斷碼6,診斷碼7,診斷碼8,診斷碼9,診斷碼10,自助報到插卡時間,醫師插卡時間,醫師批價時間1,醫師批價時間2,醫師批價時間3,醫師批價時間4,醫師批價時間5,醫師批價時間6,醫師批價時間7,醫師批價時間8,醫師批價時間9,預估看診時間,診療時間,診療分群,看診星期,病患年齡,班別早午診_轉換,實際看診順序,掛號類別_轉換,病患性別_轉換,前次是否開立醫囑_轉換,初複診_轉換";
		
		OutFile << Title << '\n';
		for(int i = 0; i < FileContent.size(); i++){
			string out ="";
			for(int j = 0; j < FileContent[i].size(); j++){
				if(j == FileContent[i].size()-1){
					out += FileContent[i][j];
				}else{
					out = out + FileContent[i][j] +',';
				}
			}
			OutFile << out << '\n';
		}
		OutFile.close();
		
		for(int i = 0; i < sizeof(Cluster)/sizeof(Cluster[0]); i++){
			C_OutFile<<Cluster[i]<<endl;
		}
		C_OutFile.close();
		
		//將清洗好的內容放於V01 
		//cout<<FileName[ii]<<" Done"<<" Ave: "<<DocAverageTime<<" SD: "<<DocSD<<endl;
	}
	//cout<<"123";
	
	
	//顯示已完成的檔案
	string OutPath = "Hos_Data\\Doc_Train\\Wa_Ch\\" + FileYear; 
	fstream CtoJava("ctojava",ios::out);
	CtoJava<<OutPath<<"\n";
	CtoJava.close();
	//cout<<OutPath;
    return 0;
}
