import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.nio.*;

public class FeatureSave2_All{
	private ArrayList<String> isaList;//要擷取的特徵
	private ArrayList<String[]> TwoD = new ArrayList<String[]>();//原始檔案內容
	private ArrayList<Integer> FeatureCheck = new ArrayList<Integer>();//特徵在檔案中的索引值
	private String Target = "診療分群"; //目標
	private String FileInPath;//原始檔案路徑
	private String FileoutPath = "";//輸出檔案路徑
	private String FileName;//檔案名稱
	private String aDelimiter = ",";
	private String line = null;
	private String[] FeatureFileName;//所有特徵檔案名稱
	private String EndofFIle = ".csv";
	private String ctojava = "javatoc";
	private String Path = ""; //傳入的路徑(訓練集的特徵檔案位置)
	private String TestYear = "";//測試集年份
	private String TrainYear = "";//訓練集年份
	
	public FeatureSave2_All(String Path){
		this.Path = Path;
		FileoutPath = Path.replace("Doc_Train", "Doc_Test");
		
		
		try{
			InputStreamReader ctoj = new InputStreamReader(new FileInputStream(ctojava));//讀取清洗與轉換資料夾路徑
			BufferedReader ctojavaReader = new BufferedReader(ctoj);
			FileInPath = ctojavaReader.readLine();
		}catch(IOException e){}
		
		int position = FileInPath.lastIndexOf("\\");
		TestYear = FileInPath.substring(position+1);
		//System.out.println("TestYear:" + TestYear);
		
		position = Path.lastIndexOf("Feature");
		TrainYear = Path.substring(position+8,position+12);
		//System.out.println(TrainYear);
		FileoutPath = FileoutPath.replace(TrainYear, TestYear);
		//System.out.println("out:" + FileoutPath);
		
		File mkDic = new File(FileoutPath); //建立輸出資料夾
		mkDic.mkdirs();
		File fileNameList = new File(Path); //列出Train的特徵檔案名稱
		FeatureFileName = fileNameList.list();
		for(String Name:FeatureFileName){
			if(Name.endsWith(EndofFIle) || Name.length() > 9){
				continue;
			}
			FileName = Name; //檔案名稱
			try{
				//System.out.println("In:" + FileInPath + "\\" + FileName.replace(".txt",".csv"));
				ReadFile();
				WriteFile();
			}catch(IOException  e){
				//System.out.println("File not found");
			}
			TwoD.clear();
			FeatureCheck.clear();
		}
		
	}



	private void WriteFile() throws java.io.IOException{

		InputStreamReader DocFile = new InputStreamReader(new FileInputStream(FileInPath + "\\" + FileName.replace(".txt",".csv")));//檔案讀取路徑
		BufferedReader reader = new BufferedReader(DocFile);
		BufferedWriter bw = new BufferedWriter(new FileWriter(FileoutPath + "\\" + FileName.replace(".txt",".csv")));//檔案輸出路徑
		//System.out.println(FileoutPath);
		
			
		while((line = reader.readLine()) != null){
			String[] temp = line.split(",");
			for(int i = 0; i < temp.length; i++){
				temp[i] = temp[i].trim();
			}
			TwoD.add(temp);
		}//將檔案內容讀入
		//System.out.println(TwoD.get(0)[0]);
		for(int i = 0; i < TwoD.size(); i++){
			for(int j = 0; j < FeatureCheck.size(); j++){
				bw.write(TwoD.get(i)[FeatureCheck.get(j)]);
				if (j != FeatureCheck.size() - 1)
					bw.write(aDelimiter);
			}
			bw.write("\n");
		}//寫檔
		
		bw.close();
	}
	
	private void ReadFile() throws java.io.IOException{

		InputStreamReader DocFile = new InputStreamReader(new FileInputStream(Path + FileName));//檔案讀取路徑
		BufferedReader reader = new BufferedReader(DocFile);		
			
		while((line = reader.readLine()) != null){
			//System.out.println(line);
			int temp = Integer.valueOf(line);
			
			FeatureCheck.add(temp);
		}//將檔案內容讀入
		FeatureCheck.add(36);
	}
	
	public String getOutPath(){
		return FileoutPath;
	}
}