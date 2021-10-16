#include<iostream>
#include<fstream>
#include<vector>
#include<string>
#include<sstream>
#include<algorithm>
#include<cmath>
#include <ctime>
using namespace std;

typedef struct{
	string FieldName;
	string Operator;
	string Number;
} Condition;

bool cmp1(const vector<string> &a, const vector<string> &b){
	if(a[0] != b[0]){
		return a[0] < b[0];
	}
	if(a[10] != b[10]){
		return a[10] < b[10];
	}
}

string TimeCompute(string DCtime, string Minute){
	stringstream ss,sss;
	string s, result;
	long double TempMin;
	int DCHMS[3], min, sec;
	ss << DCtime;
	for(int i = 0; getline(ss, s, ':'); i++){
		sss << s; 
		sss >> DCHMS[i];
		sss.str("");
		sss.clear();
	} 
	ss.str("");
	ss.clear();
	ss <<  Minute;
	ss >> TempMin;
	ss.str("");
	ss.clear();
	min = (int)TempMin;
	sec = round((TempMin * 60) - (min * 60));
	DCHMS[2] = DCHMS[2] + sec;
	if(DCHMS[2] >= 60){
		int pass = DCHMS[2] / 60;
		DCHMS[1] += pass;
		DCHMS[2] -= pass * 60;
	}
	DCHMS[1] = DCHMS[1] + min;
	if(DCHMS[1] >= 60){
		int pass = DCHMS[1] / 60;
		DCHMS[0] += pass;
		DCHMS[1] -= pass * 60;
	}
	if(DCHMS[0] >= 24){
		DCHMS[0] -= 24;
	}
	ss<<DCHMS[0]<<":"<<DCHMS[1]<<":"<<DCHMS[2];
	ss>>result;
	return result;
}

int main(int argc, char const *argv[]){
	
	
	string MainFilePath_1 = "";//要預測的檔案(Test的Wa_Ch)
	string SubFilePath_1 = "";//模型的位置 
	string CluFilePath_1 = "";//標準差的檔案
	
	string MainFilePath = "";//要預測的檔案(Test的Wa_Ch)
	string SubFilePath = "";//模型的位置 
	string CluFilePath = "";//標準差的檔案
	
	string FileYear = "";
	string MainFileYear = "";
	string FileName = "";
	vector<string> TestFileName;//檔案名稱
	
	fstream inFile("javatoc2", ios::in);
	fstream inFileForc("javatoc", ios::in);
	getline(inFile, SubFilePath_1);
	getline(inFileForc, MainFilePath_1);
	
	SubFilePath_1 = SubFilePath_1.substr(0, SubFilePath_1.size()-1);
	
	string listPath ="dir " + SubFilePath_1 + "\\*.txt /b /on >subList.txt";//列出要讀取的檔案名稱
	//cout<<listPath.c_str()<<endl;
	system(listPath.c_str());
    fstream f("subList.txt", ios::in);//開啟要讀取檔案名稱的檔案 
    
    for(string s;f >> s;) {
    	if(s.find("SubDisc")){
    		TestFileName.push_back(s);	
		}  
    }//加入FileName的佇列
	for(int i = 0; i < TestFileName.size(); i++){
		//cout<<TestFileName[i]<<endl;
	}
	inFile.close();
	
	
	int Pos = SubFilePath_1.rfind("Model", SubFilePath_1.size());
	for(int i = Pos + 6; i < Pos + 10; i++){
		FileYear += SubFilePath_1[i];
	}
	Pos = SubFilePath_1.rfind("\\", SubFilePath_1.size());
	string OutFilePath = SubFilePath_1.substr(0, Pos);
	
	
	Pos = MainFilePath_1.rfind("\\", MainFilePath_1.size());
	for(int i = Pos + 1; i < Pos + 5; i++){
		MainFileYear += MainFilePath_1[i];
	}
	
	for(int i = 0; i < TestFileName.size(); i++){
		int pos = TestFileName[i].find("_");
		TestFileName[i] = TestFileName[i].substr(0, pos);
	}
	
	CluFilePath_1 = MainFilePath_1;
	CluFilePath_1 = CluFilePath_1.replace(CluFilePath_1.find("Doc_Test"),8,"Doc_Train");
	CluFilePath_1 = CluFilePath_1.replace(CluFilePath_1.rfind("\\") + 1,4,FileYear);
	time_t now = time(0);
	tm *ltm = localtime(&now);
	OutFilePath = OutFilePath.replace(OutFilePath.find(FileYear), 4, MainFileYear);
	OutFilePath = OutFilePath.replace(OutFilePath.find("Model"), 5, "Mod_Re");
	OutFilePath = OutFilePath.replace(OutFilePath.find("Doc_Train"), 9, "Doc_Test") + "\\All_" +
				(ltm->tm_mon < 10 ? "0" + to_string(ltm->tm_mon + 1) : to_string(ltm->tm_mon + 1)) + 
				(ltm->tm_mday < 10 ? "0" + to_string(ltm->tm_mday) : to_string(ltm->tm_mday)) + "_" + 
				(ltm->tm_hour < 10 ? "0" + to_string(ltm->tm_hour) : to_string(ltm->tm_hour)) + 
				(ltm->tm_min < 10 ? "0" + to_string(ltm->tm_min) : to_string(ltm->tm_min)) + "_SubDisc";
	string DirPath = "mkdir " + OutFilePath;
	system(DirPath.c_str());
		
	for(int i = 0; i < TestFileName.size(); i++){
		CluFilePath = CluFilePath_1 + "\\" + TestFileName[i] + "_Cluster.txt";
		MainFilePath = MainFilePath_1 + "\\" + TestFileName[i] + ".csv";
		SubFilePath = SubFilePath_1 + "\\" + TestFileName[i] + "_SubDisc.txt";
		//cout<<"C: "<<CluFilePath<<endl;
		//cout<<"M: "<<MainFilePath<<endl;
		//cout<<"S: "<<SubFilePath<<endl;
		fstream MainFile(MainFilePath, ios::in);
		fstream SubFile(SubFilePath, ios::in);
		fstream CluFile(CluFilePath, ios::in);
		
		if(!MainFile){
			//cout<<"MainFile not found"<<endl;
			continue;
		}
		if(!SubFile){
			//cout<<"SubFile not found"<<endl;
			continue;
		}
		if(!CluFile){
			//cout<<"CluFile not found"<<endl;
			continue;
		}
		
		vector<vector<string>> MainFileContent;//要預測的檔案之內容 
		vector<vector<string>> SubFileContent;//子群組分析的檔案之內容 
		vector<string> CluFileContent;//標準差的檔案之內容  
		vector<Condition> SubGroupCondition;//子群組分析的條件之內容 
		vector<int> Position;
		
		stringstream ContentCut;
		string TempContent;
		
		while(getline(MainFile, TempContent)){
	    	ContentCut << TempContent;
	    	vector<string> RowContent;
	    	string s;
	    	//cout<<TempContent<<endl;
	    	for(int i = 0; getline(ContentCut, s, ','); i++){
	    		RowContent.push_back(s);
			}
			MainFileContent.push_back(RowContent);
			ContentCut.str("");
			ContentCut.clear();
		}
		
		while(getline(SubFile, TempContent)){
	    	ContentCut << TempContent;
	    	vector<string> RowContent;
	    	string s;
	    	//cout<<TempContent<<endl;
	    	for(int i = 0; getline(ContentCut, s, '\t'); i++){
	    		RowContent.push_back(s);
			}
			SubFileContent.push_back(RowContent);
			ContentCut.str("");
			ContentCut.clear();
		}
		
		while(getline(CluFile, TempContent)){
	    	CluFileContent.push_back(TempContent);
		}
			
		if(SubFileContent[2][SubFileContent[2].size() - 1] != "NextCluster"){
			for(int i = 2; SubFileContent[i][SubFileContent[i].size() - 1] != "NextCluster"; i++){//取出條件名稱、運算子、數字 
				Condition temp;
				ContentCut<<SubFileContent[i][SubFileContent[i].size() - 1];
				ContentCut>>temp.FieldName;
				ContentCut>>temp.Operator;
				ContentCut>>temp.Number;
				ContentCut.str("");
				ContentCut.clear();
				SubGroupCondition.push_back(temp);
				//cout<<"FieldName: "<<temp.FieldName<<" Operater:"<<temp.Operator<<" Number:"<<temp.Number<<endl;
			}
			
			for(int i = 0; i < SubGroupCondition.size()-1; i++){//刪除重複條件 
				for(int j = i+1; j < SubGroupCondition.size();){
					if(SubGroupCondition[i].FieldName == SubGroupCondition[j].FieldName){
						SubGroupCondition.erase(SubGroupCondition.begin() + j);
					}else{
						j++;
					}
				}
			}
			
			for(int i = 0; i < SubGroupCondition.size(); i++){//重設定Number 
				if(SubGroupCondition[i].FieldName == "實際看診順序"){
					continue;
				}
				string temp = "";
				for(int j = 0 ; j < SubGroupCondition[i].Number.size(); j++){
					if(SubGroupCondition[i].Number[j] != '\''){
						if(SubGroupCondition[i].Number[j] == '.'){
							break;
						}
						temp += SubGroupCondition[i].Number[j];
					}
				}
				SubGroupCondition[i].Number = temp;
			}
//			cout<<"----------"<<endl;
			
			for(int i = 0; i < SubGroupCondition.size(); i++){//找出條件在檔案中的位置 
				for(int j = 0; j < MainFileContent[0].size(); j++){
					
//					cout<<"SubGroupCondition: "<<SubGroupCondition[i].FieldName<<"  "<<"MainFileContent: "<<MainFileContent[0][j]<<endl;
					if(SubGroupCondition[i].FieldName == MainFileContent[0][j]){
						Position.push_back(j);
						break;
					}
				}
			}
//			cout<<"----------"<<endl;
		/*	for(int i = 0; i < SubGroupCondition.size(); i++){//找出條件在檔案中的位置 
				//cout<<SubGroupCondition[i].Number<<endl;
			}	
		*/	
			int Threshhold = round((double(SubGroupCondition.size()) / 2));
			
			for(int i = 1; i < MainFileContent.size(); i++){
				int count = 0;
				for(int j = 0; j < Position.size(); j++){
					if(SubGroupCondition[j].Operator == "="){
						if(MainFileContent[i][Position[j]] == SubGroupCondition[j].Number){
							//cout<<MainFileContent[i][Position[j]]<<"  "<<SubGroupCondition[j].FieldName<<endl;
							count++;
						}
					}else if(SubGroupCondition[j].Operator == ">="){
						if(MainFileContent[i][Position[j]] >= SubGroupCondition[j].Number){
							//cout<<MainFileContent[i][Position[j]]<<"  "<<SubGroupCondition[j].FieldName<<endl;
							count++;
						}
					}else if(SubGroupCondition[j].Operator == "<="){
						if(MainFileContent[i][Position[j]] <= SubGroupCondition[j].Number){
							//cout<<MainFileContent[i][Position[j]]<<"  "<<SubGroupCondition[j].FieldName<<endl;
							count++;
						}
					}
				}
				//若符合找出條件的一半，則分類為第十 
				if(count >= Threshhold){
					MainFileContent[i].push_back(CluFileContent[9]);
				}else{
					MainFileContent[i].push_back(CluFileContent[4]);
				}
			}
		}else{
			for(int i = 1; i < MainFileContent.size(); i++){
				MainFileContent[i].push_back(CluFileContent[4]);
			}
		}
		
		
		//sort(MainFileContent.begin()+1,MainFileContent.end(),cmp1);
		for(int i = 1; i < MainFileContent.size(); i++){
			if(i == 0){
				if(MainFileContent[i][11] == "A"){
					MainFileContent[i].push_back("09:00:00");
				}else{
					MainFileContent[i].push_back("14:00:00");
				}
			}else{
				if(MainFileContent[i][0] != MainFileContent[i-1][0]){
					if(MainFileContent[i][11] == "A"){
						MainFileContent[i].push_back("09:00:00");
					}else{
						MainFileContent[i].push_back("14:00:00");
					}
				}else{
					MainFileContent[i].push_back(TimeCompute(MainFileContent[i-1][46], MainFileContent[i-1][45]));
				}
			}
		}
		
		
		
		string OutFilePath_1 = OutFilePath + "\\" + TestFileName[i] + "_SubDisc.csv";
		
		fstream OutFile(OutFilePath_1, ios::out);
		MainFileContent[0].push_back("預估診療時間"); 
		MainFileContent[0].push_back("建議到達時間");
		for(int i = 0; i < MainFileContent.size(); i++){
			string out = "";
			for(int j = 0; j < MainFileContent[i].size(); j++){
				if(j == MainFileContent[i].size()-1){
					out += MainFileContent[i][j];
				}else{
					out = out + MainFileContent[i][j] + ",";
				}
			}
			OutFile<<out<<"\n";
		}
		OutFile.close();
		//cout<<"out: "<<OutFilePath_1<<endl;
		
	}
	fstream WriteFile("ctojava", ios::out);
	WriteFile<<OutFilePath<<"\n";
	WriteFile.close();
	
    return 0;
}
