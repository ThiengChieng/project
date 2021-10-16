#include<iostream>
#include<fstream>
#include<vector>
#include<string>
#include<sstream>
#include<algorithm>
#include<cmath>
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
		DCHMS[1] += 1;
		DCHMS[2] -= 60;
	}
	DCHMS[1] = DCHMS[1] + min;
	if(DCHMS[1] >= 60){
		DCHMS[0] += 1;
		DCHMS[1] -= 60;
	}
	ss<<DCHMS[0]<<":"<<DCHMS[1]<<":"<<DCHMS[2];
	ss>>result;
	
	return result;
}

int main(int argc, char const *argv[]){
	
	
	string MainFilePath = "Hos_Data\\Doc_Test\\Wa_Ch\\";//要預測的檔案 
	string SubFilePath = "";//模型 
	string CluFilePath = "Hos_Data\\Doc_Train\\Wa_Ch\\";//標準差的檔案
	string javatocPath = "";
	
	string FileYear = "";
	string MainFileYear = "";
	string FileName = "";
	
	fstream inFile("javatoc2", ios::in);
	fstream inFileForc("javatoc", ios::in);
	getline(inFile, SubFilePath);
	getline(inFileForc, javatocPath);
	
	inFile.close();
	int Pos = SubFilePath.rfind('\\', SubFilePath.size());
	for(int i = Pos - 4; i < Pos; i++){
		FileYear += SubFilePath[i];
	}
	for(int i = Pos + 1; i < Pos+5; i++){
		FileName += SubFilePath[i];
	}
	Pos = javatocPath.rfind('\\', javatocPath.size());
	for(int i = Pos - 4; i < Pos; i++){
		MainFileYear += javatocPath[i];
	}
	CluFilePath = CluFilePath + FileYear + "\\" + FileName + "_Cluster.txt";
	MainFilePath = MainFilePath + MainFileYear + "\\" + FileName + ".csv";
	cout<<MainFilePath<<endl;
	fstream MainFile(MainFilePath, ios::in);
	fstream SubFile(SubFilePath, ios::in);
	fstream CluFile(CluFilePath, ios::in);
	
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
	
	for(int i = 2; SubFileContent[i][SubFileContent[i].size() - 1] != "NextCluster"; i++){//取出條件名稱、運算子、數字 
		Condition temp;
		ContentCut<<SubFileContent[i][SubFileContent[i].size() - 1];
		ContentCut>>temp.FieldName;
		ContentCut>>temp.Operator;
		ContentCut>>temp.Number;
		ContentCut.str("");
		ContentCut.clear();
		SubGroupCondition.push_back(temp);
		cout<<"FieldName: "<<temp.FieldName<<" Operater:"<<temp.Operator<<" Number:"<<temp.Number<<endl;
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
	
	for(int i = 0; i < SubGroupCondition.size(); i++){//找出條件在檔案中的位置 
		for(int j = 0; j < MainFileContent[0].size(); j++){
			if(SubGroupCondition[i].FieldName == MainFileContent[0][j]){
				Position.push_back(j);
				break;
			}
		}
	}
	
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
	
	string OutFilePath = "Hos_Data\\Doc_Test\\Mod_Re\\" + FileYear;
	string DirPath = "mkdir " + OutFilePath;
	system(DirPath.c_str());
	
	OutFilePath = "Hos_Data\\Doc_Test\\Mod_Re\\" + FileYear + "\\" + FileName + "_SubDisc.csv";
	fstream OutFile(OutFilePath, ios::out);
	MainFileContent[0].push_back("模型預估診療時間"); 
	MainFileContent[0].push_back("模型建議到達時間");
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
	
	fstream WriteFile("ctojava", ios::out);
	WriteFile<<OutFilePath<<"\n";
	WriteFile.close();
	
    return 0;
}
