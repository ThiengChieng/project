#include <iostream>
#include <vector>
#include <string>
#include <direct.h>
#include <fstream>
using namespace std;
string datadate;
class doctor {
	private:
		string doctorname;
		vector<string> doctordata;

	public:
		bool nameequal(string comparename) {
			return doctorname == comparename ? true : false;
		}
		void adddoctordata(string PatientRow) {
			doctordata.push_back(PatientRow);
		}
		string getdoctorname() {
			return doctorname;
		}
		void setname(string name) {
			doctorname = name;
		}
		void writefile(string filepath) {
			ofstream out((filepath + doctorname + ".csv").c_str());
			for (int i = 0; i < doctordata.size(); i++) {
				out << doctordata[i] << endl;
			}
			out.close();
			doctordata.clear();
		}
};
void readfile(string filepath) {
	int doctorindex; //?Ψ?P?_?O?_???s???
	vector<doctor> doctorlist;
	doctorlist.clear(); //??l??

	ifstream in(filepath.c_str()); //?Nfilepath?????e??in(in???Vfilepath)

	if (!in) {
		//file not exist
		return;
	}
	string readline, name; //readline=???????? , name=?????W??
	string year;
	int posfirst, poslast, circle = 0;
	int ypostfirst = -1, yposlast = -1;
	string title = "看診日期,看診科別,看診診間,病歷號,醫師卡號,跟診護理師卡號,病患性別,病患出生日期,掛號類別,初複診,掛號序號,班別早午診,前次是否開立醫囑,診斷碼1,診斷碼2,診斷碼3,診斷碼4,診斷碼5,診斷碼6,診斷碼7,診斷碼8,診斷碼9,診斷碼10,自助報到插卡時間,醫師插卡時間,醫師批價時間1,醫師批價時間2,醫師批價時間3,醫師批價時間4,醫師批價時間5,醫師批價時間6,醫師批價時間7,醫師批價時間8,醫師批價時間9,預估看診時間";

	getline(in,readline);
	while (getline(in, readline)) {
		posfirst = 0, poslast = 0;
		if (ypostfirst == -1 && yposlast == -1) {
			ypostfirst = 0;
			yposlast = readline.find_first_of("-", ypostfirst + 1);
			year = readline.substr(ypostfirst, yposlast - ypostfirst);
		}
		for (int i = 0; i < 4; i++) {
			posfirst = readline.find_first_of(",", posfirst + 1); //從posfirst+1的位置開始往後找','的位置找到第四個欄位的位置
		}
		poslast = readline.find_first_of(",", posfirst + 1);		  //從posfirst+1的位置開始往後找','的索引直
		name = readline.substr(posfirst + 1, poslast - posfirst - 1); //切割名字
		doctorindex = -1;
		for (int i = 0; i < doctorlist.size(); i++) {
			if (doctorlist[i].getdoctorname() == name) {
				doctorlist[i].adddoctordata(readline);
				doctorindex = i;
				break;
			}
		}
		if (doctorindex == -1) {
			doctor d;
			d.setname(name);
			doctorindex = doctorlist.size();
			doctorlist.push_back(d);

			//?s?W
			doctorlist[doctorindex].adddoctordata(title);
			doctorlist[doctorindex].adddoctordata(readline);
		}
	}
	cout << year << endl;
	in.close();
	//string outfilepath = filepath.substr(0, filepath.find_last_of("\\")) + "\\V00\\";
	//string outfilepath = filepath.substr(0, filepath.find_last_of("\\")) + "\\Doc_Train\\Cut\\"+ year + "\\";
	string outfilepath = filepath.substr(0, filepath.find_last_of("\\")) + "\\Doc_Test\\";
	_mkdir(outfilepath.c_str());
	outfilepath += "Cut\\";
	_mkdir(outfilepath.c_str());
	outfilepath += year + "\\";
	_mkdir(outfilepath.c_str());
	
	fstream pathForJava("ctojava", ios::out);
	pathForJava<<outfilepath<<endl;
	pathForJava.close();
	
	for (int i = 0; i < doctorlist.size(); i++) {
		doctorlist[i].writefile(outfilepath);
	}
}
int main() {
	string input = "javatoc"; //input?Ω????java???c?????
	fstream javatoc(input, ios::in);					   //???javatoc?o?????
	string filepath;
	javatoc >> filepath;
	cout << filepath << endl;

	readfile(filepath);
	//system("pause");
	//cout<<filepath;
	return 0;
}

