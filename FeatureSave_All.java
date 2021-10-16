import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.nio.charset.Charset;

public class FeatureSave_All{
	private ArrayList<String> isaList;//要擷取的特徵
	private ArrayList<String[]> TwoD = new ArrayList<String[]>();//原始檔案內容
	private ArrayList<Integer> FeatureCheck;//特徵在檔案中的索引值
	private String Target = "診療分群"; //目標
	private String FileInPath;//原始檔案路徑
	private String FileoutPath = "";//輸出檔案路徑
	private String FileName;//檔案名稱
	private String aDelimiter = ",";
	private String line = null;
	private String Name2 = "";
	private File file;
	private String ctojava = "ctojava";
	private String ctojava2 = "ctojava2";
	private String[] FeatureFileName;//所有特徵檔案名稱
	private String Path;
	private ArrayList<Integer> Feature = new ArrayList<Integer>();
	private String[] FeatureNumber = {"看診星期", "病患年齡", "班別早午診_轉換", "實際看診順序", "掛號類別_轉換", "病患性別_轉換", "前次是否開立醫囑_轉換", "初複診_轉換"};//對應特徵號碼
	private String FeaturePath;
	
	public FeatureSave_All(){
		//System.out.println("禎禎傳進來的"+Feature);
		//System.out.println(Path);
		//System.out.println(Name);
		
		try{
			InputStreamReader ctoj = new InputStreamReader(new FileInputStream(ctojava));//讀取清洗與轉換資料夾路徑
			BufferedReader ctojavaReader = new BufferedReader(ctoj);
			Path = ctojavaReader.readLine();
			
			InputStreamReader ctoj2 = new InputStreamReader(new FileInputStream(ctojava2));//讀取特徵資料夾路徑
			BufferedReader ctojava2Reader = new BufferedReader(ctoj2);
			FeaturePath = ctojava2Reader.readLine();
		}catch(IOException e){}
		
		
		file = new File(FeaturePath);//列出所有特徵檔案名稱
		FeatureFileName = file.list();
		
		for(String Name:FeatureFileName){
			if(Name.charAt(5) == 'C'){//處理卡方特徵
				String line2;
				try{
					InputStreamReader featureFileIn = new InputStreamReader(new FileInputStream(FeaturePath + "\\" + Name));//讀取卡方特徵檔案
					BufferedReader featureFileIn2 = new BufferedReader(featureFileIn);
					while((line2 = featureFileIn2.readLine()) != null){//放入特徵號碼
						char tempC = line2.charAt(0);
						int tempI = tempC - '0';
						//System.out.println(tempI);
						Feature.add(tempI);
					}
				}catch(IOException e){}
				
			}else if(Name.charAt(5) == 'F'){//處理方差特徵
				String line2;
				String[] line3;
				String tempS;
				try{
					InputStreamReader featureFileIn = new InputStreamReader(new FileInputStream(FeaturePath + "\\" + Name),"UTF-8");//讀取方差特徵檔案
					BufferedReader featureFileIn2 = new BufferedReader(featureFileIn);
					line2 = featureFileIn2.readLine();
					line3 = line2.split(",");
					//System.out.println("--------------------");
					for(int i = 0; i < line3.length; i++){
						for(int j = 0; j < FeatureNumber.length; j++){
							if(line3[i].equals(FeatureNumber[j])){
								Feature.add(j);
								//System.out.println("+++++++++");
								break;
							}
						}
					}
				}catch(IOException e){}
			}
			
			
			if(Name.charAt(5) == 'C'){
				Name = Name.replace("_CHi2.txt",".csv");
				//System.out.println(Name);
			}else if(Name.charAt(5) == 'F'){
				Name = Name.replace("_FT.txt",".csv");
			}
			FileoutPath = FeaturePath + "\\";
			Name2 = Name.replace(".txt",".csv");
			if(Feature.contains(36)){
				
				Feature.remove(Feature.get(Feature.indexOf(36)));
				
			}
			for(int i =0;i<Feature.size();i++){
				Feature.set(i, Feature.get(i)+37);//重新替代(所要的欄位在第37欄)
			}
			//System.out.println("禎禎+37後的"+Feature);
			
			/*儲存所選特徵欄位號碼*/
			try{
			BufferedWriter TTC = new BufferedWriter(new FileWriter(FileoutPath + Name.replace(".csv",".txt")));
				for(int i =0;i<Feature.size();i++){
					//System.out.println("write "+Feature);
					TTC.write(Feature.get(i).toString());
					TTC.write("\n");
				}
				TTC.close();
			}catch(IOException e){}
			/*儲存所選特徵欄位號碼*/
			FeatureCheck = Feature;
			FeatureCheck.add(36);
			//Collections.sort(FeatureCheck);
			//System.out.println("冠宏的"+Feature);
			
			FileInPath = Path + '\\' + Name2;
			//System.out.println(FileInPath);
			FileName = Name;
			try{
				WriteFile();
			}catch(IOException  e){
				//System.out.println("File not found");
			}
			Feature.clear();
		}
		
	}



	private void WriteFile() throws java.io.IOException{

		InputStreamReader DocFile = new InputStreamReader(new FileInputStream(FileInPath));//檔案讀取路徑
		BufferedReader reader = new BufferedReader(DocFile);
		
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(FileoutPath + FileName));//檔案輸出路徑
			
		
			
		while((line = reader.readLine()) != null){
			String[] temp = line.split(",");
			for(int i = 0; i < temp.length; i++){
				temp[i] = temp[i].trim();
			}
			TwoD.add(temp);
		}//將檔案內容讀入
		
		for(int i = 0; i < TwoD.size(); i++){
			for(int j = 0; j < FeatureCheck.size(); j++){
				bw.write(TwoD.get(i)[FeatureCheck.get(j)]);
				if (j != FeatureCheck.size() - 1)
					bw.write(aDelimiter);
			}
			bw.write("\n");
		}//寫檔
		TwoD.clear();
		bw.close();
	}
	
	public String getOutPath(){
		//System.out.println("郭冠宏的最後檔案路徑"+FileoutPath);
		return FileoutPath;
	}
}