#include<iostream>
#include<fstream>
#include<vector>
#include<string>
#include<sstream>
#include<algorithm>
#include<cmath>
using namespace std;

typedef struct InRoom{
	string time;
	int order;
}Inroom;

bool cmp1(const vector<string> &a, const vector<string> &b){
	if(a[0] != b[0]){
		return a[0] < b[0];
	}
	if(a[1] != b[1]){
		return a[1] < b[1];
	}
}

bool cmp2(const Inroom &a, const Inroom &b){
	if(a.time != b.time){
		return a.time< b.time;
	}
	if(a.order != b.order){
		return a.order< b.order;
	}else{
		return a.order< b.order;
	}
}

stringstream ss,sss;
string TimeCompute(string DCtime, string Minute){
	
	string s, result;
	string tt[3];
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
	for(int i = 0; i < 3; i++){
		ss<<DCHMS[i];
		ss>>tt[i];
		if(DCHMS[i] < 10){
			tt[i] = "0" + tt[i];
		}
		ss.str("");
		ss.clear();
	}
	result = tt[0] + ":" + tt[1] + ":" + tt[2];
	//cout<<"DCtime:"<<DCtime<<"  "<<"Minute:"<<Minute<<" "<<"result:"<<result<<endl;
	ss.str("");
	ss.clear();
	return result;
}

string TimeMinus(string DCtime, string DCtime2){
	
	string s, re;
	long double TempMin,resu;
	int DCHMS[3],DCSP[3],result[3];
	ss << DCtime;
	for(int i = 0; getline(ss, s, ':'); i++){
		sss << s; 
		sss >> DCHMS[i];
		sss.str("");
		sss.clear();
	}
	ss.str("");
	ss.clear(); 
	ss<<DCtime2;
	for(int i = 0; getline(ss, s, ':'); i++){
		sss << s; 
		sss >> DCSP[i];
		sss.str("");
		sss.clear();
	}
	for(int i = 0; i < 3; i++){
		result[i] = DCHMS[i] - DCSP[i];
	} 
	ss.str("");
	ss.clear(); 
	resu = (result[0] * 60) + result[1] + (result[2] / 60.0);
	ss<<resu;
	ss>>re;
	ss.str("");
	ss.clear(); 
	return re;
}

double queueAve(vector<Inroom> a){
	int count = 0;
	int ave = 0, ma = 0; 
	double result = 0;
	
	for(int i = 0; i < a.size(); i++){
		if(a[i].order == 1){
			count++;
		}
		if(a[i].order == 2){
			count--;
		}
		ma = max(ma, count);
		if(count == 0){
			ave++;
			result += ma;
			//cout<<"maresult:"<<endl;
			//cout<<"ma:"<<ma<<endl;
			ma = 0;
		}
	}
	if(result == 0){
		return 0;
	}else{
		return (result/ave);
	}
	
}

double queueMax(vector<Inroom> a){
	int count = 0;
	int ma = 0; 
	
	for(int i = 0; i < a.size(); i++){
		if(a[i].order == 1){
			count++;
		}
		if(a[i].order == 2){
			count--;
		}
		ma = max(ma, count);
	}
	return ma;
}

int main(){
	string AnalysisFilePath = "";
	string VGHKSFilePath = "Hos_Data\\Doc_Test\\Wa_Ch\\";
	 
	string FileYear = "";
	string FileName = "";
	
	fstream SourceTxt("javatoc", ios::in);
	getline(SourceTxt, AnalysisFilePath);
	int Position = AnalysisFilePath.rfind('\\', AnalysisFilePath.size());
	if(Position == -1){
		Position = AnalysisFilePath.rfind('/', AnalysisFilePath.size());
	}
	cout<<AnalysisFilePath<<endl;
	for(int i = Position - 4; i < Position; i++){
		FileYear += AnalysisFilePath[i];
	}
	for(int i = Position + 1; i < Position+5; i++){
		FileName += AnalysisFilePath[i];
	}
	VGHKSFilePath = VGHKSFilePath + FileYear + "\\" + FileName + ".csv";
	cout<<VGHKSFilePath<<endl;
	
	fstream AnalysisFile(AnalysisFilePath, ios::in);
	fstream VGHKSFile(VGHKSFilePath, ios::in);
	
	vector<vector<string>> AnalysisFileContent;//要比較的檔案之內容(我們) 
	vector<vector<string>> VGHKSFileContent;//要比較的檔案之內容(榮總)
	//日期、實際診療時間、榮總建議到達時間、榮總醫師看診時間、榮總診療結束時間、
	//模型建議到達時間、模型醫師看診時間、模型診療結束時間、模型預期診療時間 
	vector<vector<string>> IntegrationContent;//放入排序後的內容，以利計算 
	vector<vector<string>> SortContent;//放入日期、醫師插卡時間、實際診療時間、模型預期診療時間
	vector<string> month;
	
	stringstream ContentCut;
	string TempContent;
	
	while(getline(AnalysisFile, TempContent)){
    	ContentCut << TempContent;
    	vector<string> RowContent;
    	string s;
    	//cout<<TempContent<<endl;
    	for(int i = 0; getline(ContentCut, s, ','); i++){
    		RowContent.push_back(s);
		}
		AnalysisFileContent.push_back(RowContent);
		ContentCut.str("");
		ContentCut.clear();
	}
	
	while(getline(VGHKSFile, TempContent)){
    	ContentCut << TempContent;
    	vector<string> RowContent;
    	string s;
    	//cout<<TempContent<<endl;
    	for(int i = 0; getline(ContentCut, s, ','); i++){
    		RowContent.push_back(s);
		}
		VGHKSFileContent.push_back(RowContent);
		ContentCut.str("");
		ContentCut.clear();
	}
	
	for(int i = 1; i < VGHKSFileContent.size(); i++){
		vector<string> temp;
		temp.push_back(VGHKSFileContent[i][0]);
		temp.push_back(VGHKSFileContent[i][24]);
		temp.push_back(VGHKSFileContent[i][35]);
		temp.push_back(AnalysisFileContent[i][AnalysisFileContent[i].size()-2]);
		SortContent.push_back(temp);
	}
	
	sort(SortContent.begin(),SortContent.end(),cmp1);
	
	for(int i = 0; i < SortContent.size(); i++){
		SortContent[i].push_back(VGHKSFileContent[i+1][34]);
	}
	
	for(int i = 0; i < SortContent.size(); i++){
		vector<string> temp;
		temp.push_back(SortContent[i][0]);
		temp.push_back(SortContent[i][1]);
		temp.push_back(SortContent[i][2]);
		temp.push_back(SortContent[i][4]);
		IntegrationContent.push_back(temp);
	}
	
	for(int i = 0; i < IntegrationContent.size(); i++){//榮總
		
		if(i == 0){
			IntegrationContent[i].push_back("09:00:00");//醫師看診時間
			string computeTime = TimeCompute(IntegrationContent[i][4],IntegrationContent[i][2]);//計算診療結束時間 
			IntegrationContent[i].push_back(computeTime);
		}else{
			if(IntegrationContent[i][0] == IntegrationContent[i-1][0]){
				IntegrationContent[i].push_back(max(IntegrationContent[i][3],IntegrationContent[i-1][5]));//醫師看診時間  
				string computeTime = TimeCompute(IntegrationContent[i][4],IntegrationContent[i][2]);//計算診療結束時間
				IntegrationContent[i].push_back(computeTime);
			}else{
				IntegrationContent[i].push_back("09:00:00");//醫師看診時間
				string computeTime = TimeCompute(IntegrationContent[i][4],IntegrationContent[i][2]);//計算診療結束時間 
				IntegrationContent[i].push_back(computeTime);
			}
		}
		
		
		
		if(IntegrationContent[i][4] > IntegrationContent[i][3]){
			IntegrationContent[i].push_back(TimeMinus(IntegrationContent[i][4], IntegrationContent[i][3]));//計算病患等待時間 
		}else{
			IntegrationContent[i].push_back("0");
		}
		
		if(i == 0){
			IntegrationContent[i].push_back("0");//醫師閒置時間 
		}else{
			if(IntegrationContent[i][0] == IntegrationContent[i-1][0]){ 
				//cout<< IntegrationContent[i][5]<< "   "<<IntegrationContent[i][4]<<endl;
				string computeTime = TimeMinus(IntegrationContent[i][4],IntegrationContent[i-1][5]);//醫師閒置時間
				IntegrationContent[i].push_back(computeTime);
			}else{
				IntegrationContent[i].push_back("0");//醫師閒置時間 
			}
		}	
	}
	
	for(int i = 0; i < IntegrationContent.size(); i++){//模型 
		IntegrationContent[i].push_back(SortContent[i][3]);//加入模型預測診療時間 
		if(i == 0 || IntegrationContent[i][0] != IntegrationContent[i-1][0]){
			IntegrationContent[i].push_back("09:00:00");//加入模型建議到達時間
			IntegrationContent[i].push_back("09:00:00");//加入模型醫師看診時間
		}else{
			IntegrationContent[i].push_back(TimeCompute(IntegrationContent[i-1][10],IntegrationContent[i-1][8]));//加入模型建議到達時間
			IntegrationContent[i].push_back(max(IntegrationContent[i][9],IntegrationContent[i-1][11]));//加入模型醫師看診時間
		}
		
		string computeTime = TimeCompute(IntegrationContent[i][10],IntegrationContent[i][2]);//計算模型診療結束時間 
		IntegrationContent[i].push_back(computeTime);
		
		if(i == 0 || IntegrationContent[i][0] != IntegrationContent[i-1][0]){
			IntegrationContent[i].push_back("0");//加入模型病患等待時間
			IntegrationContent[i].push_back("0");//加入模型醫師閒置時間
		}else{
			if(IntegrationContent[i-1][11] > IntegrationContent[i][9]){
				IntegrationContent[i].push_back(TimeMinus(IntegrationContent[i-1][11],IntegrationContent[i][9]));//計算模型病患等候時間
			}else{
				IntegrationContent[i].push_back("0");//計算模型病患等候時間
			}
			
			if(IntegrationContent[i][10] > IntegrationContent[i-1][11]){
				IntegrationContent[i].push_back(TimeMinus(IntegrationContent[i][10],IntegrationContent[i-1][11]));//加入模型醫師閒置時間
			}else{
				IntegrationContent[i].push_back("0");//加入模型醫師閒置時間
			}
		}
		
				
	}
	
	for(int i = 0; i < IntegrationContent.size(); i++){
		//cout<<"Time:"<<IntegrationContent[i][8]<<endl;
	}
	
	//計算平均佇列 
	int first,last;
	double result = 0;
	vector<Inroom> VGHKSforSort;
	vector<double> VGHKSAverage,VGHKSAverageQueue;//VGHKSAverage 天;  VGHKSAverageQueue月 
	for(int i = 0; i < IntegrationContent.size(); i++){
		first = IntegrationContent[i][0].find_first_of('-', 0);
		last = IntegrationContent[i][0].find_last_of('-');
		
		if(i == 0 || IntegrationContent[i][0] == IntegrationContent[i-1][0]){
			if(IntegrationContent[i][3] == IntegrationContent[i][4]){
				continue;
			}
			Inroom t1,t2 ;
			t1.time = IntegrationContent[i][3];
			t1.order = 1;
			VGHKSforSort.push_back(t1);
			t2.time = IntegrationContent[i][4];
			t2.order = 2;
			VGHKSforSort.push_back(t2);
		}else if(IntegrationContent[i][0][first+1] == IntegrationContent[i-1][0][first+1] && IntegrationContent[i][0][last-1] == IntegrationContent[i-1][0][last-1]){
/*			cout<<"Start"<<endl;
			for(int i = 0; i < VGHKSforSort.size(); i++){
				cout<<"I:"<<VGHKSforSort[i].time<<endl;
			}
*/			
			sort(VGHKSforSort.begin(), VGHKSforSort.end(), cmp2);
			
			//cout<<"Queue:"<<queueAve(VGHKSforSort)<<endl;
			VGHKSAverage.push_back(queueAve(VGHKSforSort));
			VGHKSforSort.clear();
		}else{
			sort(VGHKSforSort.begin(), VGHKSforSort.end(), cmp2);
			//cout<<"Queue:"<<queueAve(VGHKSforSort)<<endl;
			VGHKSAverage.push_back(queueAve(VGHKSforSort));
			VGHKSforSort.clear();
			for(int i = 0; i < VGHKSAverage.size(); i++){
				result += VGHKSAverage[i];
				//cout<<"AVE:"<<VGHKSAverage[i]<<endl; 
			}
			
			//cout<<"result:"<<result<<endl; 
			VGHKSAverageQueue.push_back(result/VGHKSAverage.size());
			VGHKSAverage.clear();
			VGHKSforSort.clear();
			//cout<<"size: "<<VGHKSAverageQueue.size()<<endl;
			result = 0;
		}
		if(i == IntegrationContent.size()-1){
			sort(VGHKSforSort.begin(), VGHKSforSort.end(), cmp2);
			//cout<<"Queue:"<<queueAve(VGHKSforSort)<<endl;
			VGHKSAverage.push_back(queueAve(VGHKSforSort));
			VGHKSforSort.clear();
			for(int i = 0; i < VGHKSAverage.size(); i++){
				result += VGHKSAverage[i];
				//cout<<"AVE:"<<VGHKSAverage[i]<<endl; 
			}
			VGHKSAverageQueue.push_back(result/VGHKSAverage.size());
			VGHKSAverage.clear();
			VGHKSforSort.clear();
			result = 0;
		}
		
	}
	for(int i = 0; i < VGHKSAverageQueue.size(); i++){
		//cout<<"Avefin:"<<VGHKSAverageQueue[i]<<endl;
	}
	
	vector<Inroom> AnalysisforSort;
	vector<double> AnalysisAverage,AnalysisAverageQueue;//AnalysisAverage 天;  AnalysisAverageQueue 月 
	for(int i = 0; i < IntegrationContent.size(); i++){
		first = IntegrationContent[i][0].find_first_of('-', 0);
		last = IntegrationContent[i][0].find_last_of('-');
		if(i == 0 || IntegrationContent[i][0] == IntegrationContent[i-1][0]){
			if(IntegrationContent[i][9] == IntegrationContent[i][10]){
				continue;
			}
			Inroom t1,t2 ;
			t1.time = IntegrationContent[i][9];
			t1.order = 1;
			AnalysisforSort.push_back(t1);
			t2.time = IntegrationContent[i][10];
			t2.order = 2;
			AnalysisforSort.push_back(t2);
		}else{
			sort(AnalysisforSort.begin(), AnalysisforSort.end(), cmp2);
			//cout<<"Queue:"<<queueAve(AnalysisforSort)<<endl;
			AnalysisAverage.push_back(queueAve(AnalysisforSort));
			AnalysisforSort.clear();
			if(IntegrationContent[i][0][first+1] != IntegrationContent[i-1][0][first+1] || IntegrationContent[i][0][last-1] != IntegrationContent[i-1][0][last-1]){
				for(int i = 0; i < AnalysisAverage.size(); i++){
				result += AnalysisAverage[i];
				//cout<<"AVE:"<<AnalysisAverage[i]<<endl;
				}
				//cout<<"result:"<<result<<endl; 
				AnalysisAverageQueue.push_back(result/AnalysisAverage.size());
				AnalysisAverage.clear();
				AnalysisforSort.clear();
				//cout<<"size: "<<AnalysisAverage.size()<<endl;
				result = 0;
			} 
		}
		if(i == IntegrationContent.size()-1){
			sort(AnalysisforSort.begin(), AnalysisforSort.end(), cmp2);
			////cout<<"Queue:"<<queueAve(AnalysisforSort)<<endl;
			AnalysisAverage.push_back(queueAve(AnalysisforSort));
			AnalysisforSort.clear();
			for(int i = 0; i < AnalysisAverage.size(); i++){
			result += AnalysisAverage[i];
			//cout<<"AVE:"<<AnalysisAverage[i]<<endl; 
			}
			AnalysisAverageQueue.push_back(result/AnalysisAverage.size());
			AnalysisAverage.clear();
			AnalysisforSort.clear();
			result = 0;
			}
	} 
	for(int i = 0; i < AnalysisAverageQueue.size(); i++){
		//cout<<"Avefin:"<<AnalysisAverageQueue[i]<<endl;
	}
	
	//最大佇列 
	vector<Inroom> VGHKSforMaxSort;
	vector<double> VGHKSMaxQueue;//VGHKSMaxQueue 月 
	int MaxQueueLength = 0;
	for(int i = 0; i < IntegrationContent.size(); i++){
		first = IntegrationContent[i][0].find_first_of('-', 0);
		last = IntegrationContent[i][0].find_last_of('-');
		if(i == 0 || IntegrationContent[i][0] == IntegrationContent[i-1][0]){
			if(IntegrationContent[i][3] == IntegrationContent[i][4]){
				continue;
			}
			Inroom t1,t2 ;
			t1.time = IntegrationContent[i][3];
			t1.order = 1;
			VGHKSforMaxSort.push_back(t1);
			t2.time = IntegrationContent[i][4];
			t2.order = 2;
			VGHKSforMaxSort.push_back(t2);
		}else{
			sort(VGHKSforMaxSort.begin(), VGHKSforMaxSort.end(), cmp2);
			//cout<<"QueueMax:"<<queueMax(VGHKSforMaxSort)<<endl;
			int a = queueMax(VGHKSforMaxSort);
			MaxQueueLength = max(MaxQueueLength, a);
			VGHKSforMaxSort.clear();
			if(IntegrationContent[i][0][first+1] != IntegrationContent[i-1][0][first+1] || IntegrationContent[i][0][last-1] != IntegrationContent[i-1][0][last-1]){
				VGHKSMaxQueue.push_back(MaxQueueLength);
				VGHKSforMaxSort.clear();
				MaxQueueLength = 0;
			} 
		}
		if(i == IntegrationContent.size()-1){
			sort(VGHKSforMaxSort.begin(), VGHKSforMaxSort.end(), cmp2);
			//cout<<"QueueMax:"<<queueMax(VGHKSforMaxSort)<<endl;
			int a = queueMax(VGHKSforMaxSort);
			MaxQueueLength = max(MaxQueueLength, a);
			VGHKSMaxQueue.push_back(MaxQueueLength);
			VGHKSforMaxSort.clear();
			MaxQueueLength = 0;
			}
	} 
	for(int i = 0; i < VGHKSMaxQueue.size(); i++){
		//cout<<"MaxQ: "<<VGHKSMaxQueue[i]<<endl;
	}
	vector<Inroom> AnalysisforMaxSort;
	vector<double> AnalysisMaxQueue;//VGHKSMaxQueue 月 
	MaxQueueLength = 0;
	for(int i = 0; i < IntegrationContent.size(); i++){
		first = IntegrationContent[i][0].find_first_of('-', 0);
		last = IntegrationContent[i][0].find_last_of('-');
		if(i == 0 || IntegrationContent[i][0] == IntegrationContent[i-1][0]){
			if(IntegrationContent[i][9] == IntegrationContent[i][10]){
				continue;
			}
			//cout<<IntegrationContent[i][9]<<"  "<<IntegrationContent[i][10]<<endl;
			Inroom t1,t2 ;
			t1.time = IntegrationContent[i][9];
			t1.order = 1;
			AnalysisforMaxSort.push_back(t1);
			t2.time = IntegrationContent[i][10];
			t2.order = 2;
			AnalysisforMaxSort.push_back(t2);
		}else{
			sort(AnalysisforMaxSort.begin(), AnalysisforMaxSort.end(), cmp2);
			//cout<<AnalysisforMaxSort.size()<<endl;
			//cout<<"QueueMax:"<<queueMax(AnalysisforMaxSort)<<endl;
			//cout<<"RUN"<<endl;
			int a = queueMax(AnalysisforMaxSort);
			MaxQueueLength = max(MaxQueueLength, a);
			AnalysisforMaxSort.clear();
			if(IntegrationContent[i][0][first+1] != IntegrationContent[i-1][0][first+1] || IntegrationContent[i][0][last-1] != IntegrationContent[i-1][0][last-1]){
				AnalysisMaxQueue.push_back(MaxQueueLength);
				AnalysisforMaxSort.clear();
				MaxQueueLength = 0;
			} 
		}
		if(i == IntegrationContent.size()-1){
			sort(AnalysisforMaxSort.begin(), AnalysisforMaxSort.end(), cmp2);
			//cout<<"QueueMax:"<<queueMax(AnalysisforMaxSort)<<endl;
			int a = queueMax(AnalysisforMaxSort);
			MaxQueueLength = max(MaxQueueLength, a);
			AnalysisMaxQueue.push_back(MaxQueueLength);
			AnalysisforMaxSort.clear();
			MaxQueueLength = 0;
			}
	} 
	for(int i = 0; i < AnalysisMaxQueue.size(); i++){
		//cout<<"MaxQ: "<<AnalysisMaxQueue[i]<<endl;
	}
	
	//平均、最大病患等候時間
	double VGHKSAvePatianTime = 0.0, VGHKSAvePatianTemp = 0, VGHKSMaxPatianTime = 0;
	vector<double>  VGHKSAvePatian, VGHKSMaxPatian;
	int AvePatianCount = 0;
	for(int i = 0; i < IntegrationContent.size(); i++){
		if(i == 0){
			continue;
		}
		first = IntegrationContent[i][0].find_first_of('-', 0);
		last = IntegrationContent[i][0].find_last_of('-');
		if(IntegrationContent[i][0][first+1] == IntegrationContent[i-1][0][first+1] && IntegrationContent[i][0][last-1] == IntegrationContent[i-1][0][last-1]){
			if(IntegrationContent[i][6] == "0"){
				if(i == IntegrationContent.size()-1){
					if(VGHKSAvePatianTime == 0){
						VGHKSAvePatian.push_back(0);
					}else{
						VGHKSAvePatian.push_back(VGHKSAvePatianTime/AvePatianCount);
					}
					VGHKSMaxPatian.push_back(VGHKSMaxPatianTime);
					VGHKSMaxPatianTime = 0.0;
					VGHKSAvePatianTime = 0.0;
					AvePatianCount = 0;
				}
				continue;
			}
			ss.str("");
			ss.clear();
			ss<<IntegrationContent[i][6];
			ss>>VGHKSAvePatianTemp;
			ss.str("");
			ss.clear();
			VGHKSMaxPatianTime = max(VGHKSMaxPatianTime, VGHKSAvePatianTemp);
			VGHKSAvePatianTime += VGHKSAvePatianTemp;
			AvePatianCount++;
		}else{
			if(VGHKSAvePatianTime == 0){
				VGHKSAvePatian.push_back(0);
			}else{
				VGHKSAvePatian.push_back(VGHKSAvePatianTime/AvePatianCount);
			}
			VGHKSMaxPatian.push_back(VGHKSMaxPatianTime);
			VGHKSMaxPatianTime = 0.0;
			VGHKSAvePatianTime = 0.0;
			AvePatianCount = 0;
		}
		if(i == IntegrationContent.size()-1){
			if(VGHKSAvePatianTime == 0){
				VGHKSAvePatian.push_back(0);
			}else{
				VGHKSAvePatian.push_back(VGHKSAvePatianTime/AvePatianCount);
			}
			VGHKSMaxPatian.push_back(VGHKSMaxPatianTime);
			VGHKSMaxPatianTime = 0.0;
			VGHKSAvePatianTime = 0.0;
			AvePatianCount = 0;
		}
	}
	for(int i = 0; i < VGHKSAvePatian.size(); i++){
		//cout<<"VGHKSAvePatianTime:"<<VGHKSAvePatian[i]<<endl;
		//cout<<"VGHKSMaxPatianTime:"<<VGHKSMaxPatian[i]<<endl;
	}
	
	double AnalysisAvePatianTime = 0.0, AnalysisAvePatianTemp = 0, AnalysisMaxPatianTime = 0;
	vector<double>  AnalysisAvePatian, AnalysisMaxPatian;
	AvePatianCount = 0;
	for(int i = 0; i < IntegrationContent.size(); i++){
		if(i == 0){
			continue;
		}
		first = IntegrationContent[i][0].find_first_of('-', 0);
		last = IntegrationContent[i][0].find_last_of('-');
		if(IntegrationContent[i][0][first+1] == IntegrationContent[i-1][0][first+1] && IntegrationContent[i][0][last-1] == IntegrationContent[i-1][0][last-1]){
			if(IntegrationContent[i][12] == "0"){
				if(i == IntegrationContent.size()-1){
					if(AnalysisAvePatianTime == 0){
						AnalysisAvePatian.push_back(0);
					}else{
						AnalysisAvePatian.push_back(AnalysisAvePatianTime/AvePatianCount);
					} 
					AnalysisMaxPatian.push_back(AnalysisMaxPatianTime);
					AnalysisMaxPatianTime = 0.0;
					AnalysisAvePatianTime = 0.0;
					AvePatianCount = 0;
				}
				continue;
			}
			ss.str("");
			ss.clear();
			ss<<IntegrationContent[i][12];
			ss>>AnalysisAvePatianTemp;
			ss.str("");
			ss.clear();
			AnalysisMaxPatianTime = max(AnalysisMaxPatianTime, AnalysisAvePatianTemp);
			AnalysisAvePatianTime += AnalysisAvePatianTemp;
			AvePatianCount++;
		}else{
			if(AnalysisAvePatianTime == 0){
				AnalysisAvePatian.push_back(0);
			}else{
				AnalysisAvePatian.push_back(AnalysisAvePatianTime/AvePatianCount);
			} 
			AnalysisMaxPatian.push_back(AnalysisMaxPatianTime);
			AnalysisMaxPatianTime = 0.0;
			AnalysisAvePatianTime = 0.0;
			AvePatianCount = 0;
		}
		if(i == IntegrationContent.size()-1){
			if(AnalysisAvePatianTime == 0){
				AnalysisAvePatian.push_back(0);
			}else{
				AnalysisAvePatian.push_back(AnalysisAvePatianTime/AvePatianCount);
			} 
			AnalysisMaxPatian.push_back(AnalysisMaxPatianTime);
			AnalysisMaxPatianTime = 0.0;
			AnalysisAvePatianTime = 0.0;
			AvePatianCount = 0;
		}
	}
	for(int i = 0; i < AnalysisAvePatian.size(); i++){
		//cout<<"AnalysisAvePatianTime:"<<AnalysisAvePatian[i]<<endl;
		//cout<<"AnalysisMaxPatianTime:"<<AnalysisMaxPatian[i]<<endl;
	}
	
	//平均、最大醫師閒置時間
	double VGHKSAveIdelTime = 0.0, VGHKSAveIdelTemp = 0, VGHKSMaxIdelTime = 0;
	vector<double>  VGHKSAveIdel, VGHKSMaxIdel;
	int AveIdelCount = 0;
	for(int i = 0; i < IntegrationContent.size(); i++){
		if(i == 0){
			continue;
		}
		first = IntegrationContent[i][0].find_first_of('-', 0);
		last = IntegrationContent[i][0].find_last_of('-');
		if(IntegrationContent[i][0][first+1] == IntegrationContent[i-1][0][first+1] && IntegrationContent[i][0][last-1] == IntegrationContent[i-1][0][last-1]){
			if(IntegrationContent[i][7] == "0"){
				if(i == IntegrationContent.size()-1){
					if(VGHKSAveIdelTime == 0){
						VGHKSAveIdel.push_back(0);
					}else{
						VGHKSAveIdel.push_back(VGHKSAveIdelTime/AveIdelCount);	
					}
					VGHKSMaxIdel.push_back(VGHKSMaxIdelTime);
					VGHKSMaxIdelTime = 0.0;
					VGHKSAveIdelTime = 0.0;
					AveIdelCount = 0;
				}
				continue;
			}
			ss.str("");
			ss.clear();
			ss<<IntegrationContent[i][7];
			ss>>VGHKSAveIdelTemp;
			ss.str("");
			ss.clear();
			VGHKSMaxIdelTime = max(VGHKSMaxIdelTime, VGHKSAveIdelTemp);
			VGHKSAveIdelTime += VGHKSAveIdelTemp;
			AveIdelCount++;
		}else{
			if(VGHKSAveIdelTime == 0){
			VGHKSAveIdel.push_back(0);
			}else{
			VGHKSAveIdel.push_back(VGHKSAveIdelTime/AveIdelCount);	
			} 
			
			VGHKSMaxIdel.push_back(VGHKSMaxIdelTime);
			VGHKSMaxIdelTime = 0.0;
			VGHKSAveIdelTime = 0.0;
			AveIdelCount = 0;
		}
		
		if(i == IntegrationContent.size()-1){
			if(VGHKSAveIdelTime == 0){
				VGHKSAveIdel.push_back(0);
			}else{
				VGHKSAveIdel.push_back(VGHKSAveIdelTime/AveIdelCount);	
			}
			VGHKSMaxIdel.push_back(VGHKSMaxIdelTime);
			VGHKSMaxIdelTime = 0.0;
			VGHKSAveIdelTime = 0.0;
			AveIdelCount = 0;
		}
	}
	for(int i = 0; i < VGHKSAveIdel.size(); i++){
		//cout<<"VGHKSAveIdelTime:"<<VGHKSAveIdel[i]<<endl;
		//cout<<"VGHKSMaxIdelTime:"<<VGHKSMaxIdel[i]<<endl;
	}
	
	double AnalysisAveIdelTime = 0.0, AnalysisAveIdelTemp = 0, AnalysisMaxIdelTime = 0;
	vector<double>  AnalysisAveIdel, AnalysisMaxIdel;
	AveIdelCount = 0;
	for(int i = 0; i < IntegrationContent.size(); i++){
		if(i == 0){
			continue;
		}
		first = IntegrationContent[i][0].find_first_of('-', 0);
		last = IntegrationContent[i][0].find_last_of('-');
		if(IntegrationContent[i][0][first+1] == IntegrationContent[i-1][0][first+1] && IntegrationContent[i][0][last-1] == IntegrationContent[i-1][0][last-1]){
			if(IntegrationContent[i][13] == "0"){
				if(i == IntegrationContent.size()-1){
					string a = string(1,IntegrationContent[i][0][first+1]);
					string b = string(1,IntegrationContent[i][0][last-1]);
					string m = a + b;
					month.push_back(m);
					if(AnalysisAveIdelTime == 0){
						AnalysisAveIdel.push_back(0);
					}else{
						AnalysisAveIdel.push_back(AnalysisAveIdelTime/AveIdelCount);
					}
					AnalysisMaxIdel.push_back(AnalysisMaxIdelTime);
					AnalysisMaxIdelTime = 0.0;
					AnalysisAveIdelTime = 0.0;
					AveIdelCount = 0;
				}
				continue;
			}
			ss.str("");
			ss.clear();
			ss<<IntegrationContent[i][13];
			ss>>AnalysisAveIdelTemp;
			ss.str("");
			ss.clear();
			AnalysisMaxIdelTime = max(AnalysisMaxIdelTime, AnalysisAveIdelTemp);
			AnalysisAveIdelTime += AnalysisAveIdelTemp;
			AveIdelCount++;
		}else{
			string a = string(1,IntegrationContent[i-1][0][first+1]);
			string b = string(1,IntegrationContent[i-1][0][last-1]);
			string m = a + b;
			month.push_back(m);
			if(AnalysisAveIdelTime == 0){
				AnalysisAveIdel.push_back(0);
			}else{
				AnalysisAveIdel.push_back(AnalysisAveIdelTime/AveIdelCount);
			}
			AnalysisMaxIdel.push_back(AnalysisMaxIdelTime);
			AnalysisMaxIdelTime = 0.0;
			AnalysisAveIdelTime = 0.0;
			AveIdelCount = 0;
		}
		if(i == IntegrationContent.size()-1){
			string a = string(1,IntegrationContent[i][0][first+1]);
			string b = string(1,IntegrationContent[i][0][last-1]);
			string m = a + b;
			month.push_back(m);
			if(AnalysisAveIdelTime == 0){
				AnalysisAveIdel.push_back(0);
			}else{
				AnalysisAveIdel.push_back(AnalysisAveIdelTime/AveIdelCount);
			}
			AnalysisMaxIdel.push_back(AnalysisMaxIdelTime);
			AnalysisMaxIdelTime = 0.0;
			AnalysisAveIdelTime = 0.0;
			AveIdelCount = 0;
		}
	}
	for(int i = 0; i < AnalysisAveIdel.size(); i++){
		//cout<<"AnalysisAveIdelTime:"<<AnalysisAveIdel[i]<<endl;
		//cout<<"AnalysisMaxIdelTime:"<<AnalysisMaxIdel[i]<<endl;
	}
	
/*	vector<int> VGHKSMaxqueue ,AnalysisMaxqueue;
	vector<double> VGHKSAveragequeue, AnalysisAveragequeue;
	int first, last;
	double VGHKSqueuecount = 0.0, Analysisqueuecount = 0.0;
	int VGHKScount = 0, VGHKSMax = 0, Analysiscount = 0, AnalysisMax = 0;
	for(int i = 0; i < IntegrationContent.size(); i++){//計算平均佇列長度
		if(i == 0){
			continue;
		}
		first = IntegrationContent[i][0].find_first_of('-', 0);
		last = IntegrationContent[i][0].find_last_of('-');
		if(IntegrationContent[i][0] == IntegrationContent[i-1][0]){//同一天的情況 
			if(IntegrationContent[i][6] != "0"){//榮總 
				VGHKScount++;
				if(i == IntegrationContent.size()-1){
					VGHKSqueuecount += 1;
					VGHKSAveragequeue.push_back(VGHKScount/VGHKSqueuecount);
					break;
				}
				if(IntegrationContent[i+1][6] == "0"){
					VGHKSqueuecount += 1;
				}
			}
		}else if(IntegrationContent[i][0][first+1] == IntegrationContent[i-1][0][first+1] && IntegrationContent[i][0][last-1] == IntegrationContent[i-1][0][last-1]){//同月不同日 
			if(IntegrationContent[i][6] != "0"){//榮總 
				VGHKScount++;
				if(i == IntegrationContent.size()-1){
					VGHKSqueuecount += 1;
					VGHKSAveragequeue.push_back(VGHKScount/VGHKSqueuecount);
					break;
				}
				if(IntegrationContent[i+1][6] == "0"){
					VGHKSqueuecount += 1;
				}
			}
		}else{
			VGHKSAveragequeue.push_back(VGHKScount/VGHKSqueuecount);
			VGHKScount = 0;
			VGHKSqueuecount = 0.0;
		}
	}
	
	for(int i = 0; i < IntegrationContent.size(); i++){//計算平均佇列長度
		if(i == 0){
			continue;
		}
		first = IntegrationContent[i][0].find_first_of('-', 0);
		last = IntegrationContent[i][0].find_last_of('-');
		if(IntegrationContent[i][0] == IntegrationContent[i-1][0]){//同一天的情況 
			if(IntegrationContent[i][12] != "0"){//模型 
				Analysiscount++;
				if(i == IntegrationContent.size()-1){
					Analysisqueuecount += 1;
					AnalysisAveragequeue.push_back(Analysiscount/Analysisqueuecount);
					break;
				}
				if(IntegrationContent[i+1][12] == "0"){
					Analysisqueuecount += 1;
				}
			}
		}else if(IntegrationContent[i][0][first+1] == IntegrationContent[i-1][0][first+1] && IntegrationContent[i][0][last-1] == IntegrationContent[i-1][0][last-1]){//同月不同日 
			if(IntegrationContent[i][12] != "0"){//模型 
				Analysiscount++;
				if(i == IntegrationContent.size()-1){
					Analysisqueuecount += 1;
					AnalysisAveragequeue.push_back(Analysiscount/Analysisqueuecount);
					break;
				}
				if(IntegrationContent[i+1][12] == "0"){
					Analysisqueuecount += 1;
				}
			}
		}else{
			AnalysisAveragequeue.push_back(Analysiscount/Analysisqueuecount);
			Analysiscount = 0;
			Analysisqueuecount = 0.0;
		}
	}
	
	VGHKScount = 0;
	for(int i = 0; i < IntegrationContent.size(); i++){//計算最大佇列長度
		if(i == 0){
			continue;
		}
		first = IntegrationContent[i][0].find_first_of('-', 0);
		last = IntegrationContent[i][0].find_last_of('-');
		if(IntegrationContent[i][0] == IntegrationContent[i-1][0]){//同一天的情況 
			if(IntegrationContent[i][6] != "0"){//榮總 
				VGHKScount++;
				if(i == IntegrationContent.size()-1){
					VGHKSMax = max(VGHKSMax, VGHKScount);
					VGHKSMaxqueue.push_back(VGHKSMax);
					break;
				}
				if(IntegrationContent[i+1][6] == "0"){
					VGHKSMax = max(VGHKSMax, VGHKScount);
					VGHKScount = 0; 
				}
			}
		}else if(IntegrationContent[i][0][first+1] == IntegrationContent[i-1][0][first+1] && IntegrationContent[i][0][last-1] == IntegrationContent[i-1][0][last-1]){//同月不同日 
			if(IntegrationContent[i][6] != "0"){//榮總 
				VGHKScount++;
				if(i == IntegrationContent.size()-1){
					VGHKSMax = max(VGHKSMax, VGHKScount);
					VGHKSMaxqueue.push_back(VGHKSMax);
					VGHKSMaxqueue.push_back(VGHKScount/VGHKSqueuecount);
					break;
				}
				if(IntegrationContent[i+1][6] == "0"){
					VGHKSMax = max(VGHKSMax, VGHKScount);
					VGHKScount = 0; 
				}
			}
		}else{
			VGHKSMax = max(VGHKSMax, VGHKScount);
			
			VGHKSMaxqueue.push_back(VGHKSMax);
			VGHKScount = 0;
			VGHKSMax = 0;
		}
		//cout<<VGHKSMax<<endl;
	}
	
	
	for(int i = 0; i < VGHKSAveragequeue.size(); i++){
		//cout<<VGHKSAveragequeue[i]<<"  "<<AnalysisAveragequeue[i]<<endl;	
	}
	for(int i = 0; i < VGHKSMaxqueue.size(); i++){
		//cout<<VGHKSMaxqueue[i]<<endl;	
	}
*/	
	for(int i = 0; i < IntegrationContent.size(); i++){
		//cout<<IntegrationContent[i][0]<<"  "<<IntegrationContent[i][9]<<"  "<<IntegrationContent[i][10]<<endl;
	}
		
	string OutPath = "Hos_Data\\Doc_Test\\Pic\\" + FileYear;
	string DirPath = "mkdir " + OutPath;
	system(DirPath.c_str());
	
	string MaxQueueFilePath = OutPath + "\\" + FileName + "_Queue.csv";
	
	string MaxWaitFilePath = OutPath + "\\" + FileName + "_Wait.csv";
	
	string MaxIdelFilePath = OutPath + "\\" + FileName + "_Idel.csv";
	
	fstream MaxQueueFile(MaxQueueFilePath, ios::out);
	MaxQueueFile<<"VGHKSA_MaxQueue,Analysis_MaxQueue,VGHKSA_AveQueue,Analysis_AveQueue,Month\n";
	for(int i = 0; i < VGHKSMaxQueue.size(); i++){
		string temp;
		ss.str("");
		ss.clear();
		ss<<VGHKSMaxQueue[i];
		ss>>temp;
		MaxQueueFile<<temp;
		MaxQueueFile<<',';
		ss.str("");
		ss.clear();
		ss<<AnalysisMaxQueue[i];
		ss>>temp;
		MaxQueueFile<<temp;
		MaxQueueFile<<',';
		ss.str("");
		ss.clear();
		ss<<VGHKSAverageQueue[i];
		ss>>temp;
		MaxQueueFile<<temp;
		MaxQueueFile<<',';
		ss.str("");
		ss.clear();
		ss<<AnalysisAverageQueue[i];
		ss>>temp;
		MaxQueueFile<<temp;
		MaxQueueFile<<',';
		ss.str("");
		ss.clear();
		MaxQueueFile<<month[i];
		MaxQueueFile<<'\n';
	}
	MaxQueueFile.close();
	
	fstream MaxWaitFile(MaxWaitFilePath, ios::out);
	MaxWaitFile<<"VGHKSA_MaxWait,Analysis_MaxWait,VGHKSA_AveWait,Analysis_AveWait,Month\n";
	for(int i = 0; i < VGHKSMaxPatian.size(); i++){
		string temp;
		ss.str("");
		ss.clear();
		ss<<VGHKSMaxPatian[i];
		ss>>temp;
		MaxWaitFile<<temp;
		MaxWaitFile<<',';
		ss.str("");
		ss.clear();
		ss<<AnalysisMaxPatian[i];
		ss>>temp;
		MaxWaitFile<<temp;
		MaxWaitFile<<',';
		ss.str("");
		ss.clear();
		ss<<VGHKSAvePatian[i];
		ss>>temp;
		MaxWaitFile<<temp;
		MaxWaitFile<<',';
		ss.str("");
		ss.clear();
		ss<<AnalysisAvePatian[i];
		ss>>temp;
		MaxWaitFile<<temp;
		MaxWaitFile<<',';
		ss.str("");
		ss.clear();
		MaxWaitFile<<month[i];
		MaxWaitFile<<'\n';
	}
	MaxWaitFile.close();
	
	fstream MaxIdelFile(MaxIdelFilePath, ios::out);
	MaxIdelFile<<"VGHKSA_MaxIdel,Analysis_MaxIdel,VGHKSA_AveIdel,Analysis_AveIdel,Month\n";
	for(int i = 0; i < VGHKSMaxIdel.size(); i++){
		string temp;
		ss.str("");
		ss.clear();
		ss<<VGHKSMaxIdel[i];
		ss>>temp;
		MaxIdelFile<<temp;
		MaxIdelFile<<',';
		ss.str("");
		ss.clear();
		ss<<AnalysisMaxIdel[i];
		ss>>temp;
		MaxIdelFile<<temp;
		MaxIdelFile<<',';
		ss.str("");
		ss.clear();
		ss<<VGHKSAveIdel[i];
		ss>>temp;
		MaxIdelFile<<temp;
		MaxIdelFile<<',';
		ss.str("");
		ss.clear();
		ss<<AnalysisAveIdel[i];
		ss>>temp;
		MaxIdelFile<<temp;
		MaxIdelFile<<',';
		ss.str("");
		ss.clear();
		MaxIdelFile<<month[i];
		MaxIdelFile<<'\n';
	}
	MaxIdelFile.close();
	
	fstream writeFile("ctojava2", ios::out);
	writeFile<<MaxQueueFilePath<<"\n";
	writeFile<<MaxWaitFilePath<<"\n";
	writeFile<<MaxIdelFilePath<<"\n";
	writeFile<<FileName<<"\n"; 
	writeFile.close();
	
/*	OutFile<<"日期,醫師插卡時間,實際診療時間,榮總建議到達,榮總醫師看診,榮總診療結束時間,榮總病患等待時間,榮總醫師閒置時間,模型預測診療時間,模型建議到達時間,模型醫師看診時間,模型診療結束時間,模型病患等待時間,模型醫師閒置時間"<<endl;
	for(int i = 0; i < IntegrationContent.size(); i++){
		string out = "";
		for(int j = 0; j < IntegrationContent[i].size(); j++){
			if(j == IntegrationContent[i].size()-1){
				out += IntegrationContent[i][j];
			}else{
				out = out + IntegrationContent[i][j] + ",";
			}
		}
		OutFile<<out<<"\n";
	}
	OutFile.close();*/
/*	for(int i = 0; i < IntegrationContent.size(); i++){
		for(int j = 0; j < IntegrationContent[i].size(); j++){
			//cout<<IntegrationContent[i][j]<<"     ";
		}
		//cout<<endl;
	}
*/	
}
