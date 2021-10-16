import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FeatureSave_All_Ori{
	private ArrayList<String> isaList;//要擷取的特徵
	private ArrayList<String[]> TwoD = new ArrayList<String[]>();//原始檔案內容
	private ArrayList<Integer> FeatureCheck;//特徵在檔案中的索引值
	private String Target = "診療分群"; //目標
	private String FileInPath;//原始檔案路徑
	private String FileoutPath = "";//輸出檔案路徑
	private String FileName;//檔案名稱
	private String aDelimiter = ",";
	private String line = null;
	private String NowTime = "";
	private File file;
	private String ctojava = "ctojava";
	private String EndofFIle = ".txt";
	private String[] FeatureFileName;//所有特徵檔案名稱
	private String Path;
	private ArrayList<Integer> Feature = new ArrayList<Integer>();
	private String[] FeatureNumber = {"看診星期", "病患年齡", "班別早午診_轉換", "實際看診順序", "掛號類別_轉換", "病患性別_轉換", "前次是否開立醫囑_轉換", "初複診_轉換"};//對應特徵號碼
	private String FeaturePath;
	private String FeatureYear = "";
	private int LastPosition;
	private File dir;
	
	public FeatureSave_All_Ori(){
		//System.out.println("特徵---原始");
		//System.out.println("禎禎傳進來的"+Feature);
		//System.out.println(Path);
		//System.out.println(Name);
		
		try{
			InputStreamReader ctoj = new InputStreamReader(new FileInputStream(ctojava));//讀取清洗與轉換資料夾路徑
			BufferedReader ctojavaReader = new BufferedReader(ctoj);
			Path = ctojavaReader.readLine();
			
		}catch(IOException e){}
		
		
		file = new File(Path);//列出所有清洗與轉換檔案名稱
		FeatureFileName = file.list();
		
		for(String Name:FeatureFileName){
			if(Name.endsWith(EndofFIle)){//過濾清洗與特徵的檔案
				continue;
			}
			
			for(int i = 0; i < 8; i++){//加入特徵號碼
				Feature.add(i);
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat();//格式
			sdf.applyPattern("MMdd_HHmm");//時間的格式  
			Date date = new Date();//得到當前時間
			NowTime = sdf.format(date);
			
			LastPosition = Path.lastIndexOf("\\");//獲得檔案年份
			for(int i = LastPosition + 1; i <= LastPosition + 4; i++){
				FeatureYear = FeatureYear + Path.charAt(i);
			}
			
			FeaturePath = "Hos_Data\\Doc_Train\\Feature\\"+ FeatureYear + "\\" + "All_" + NowTime + "_Ori\\";//建立輸出路徑
			dir = new File(FeaturePath);
			dir.mkdirs();
			
			FileoutPath = FeaturePath;
			//System.out.println(FeaturePath);
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
			
			FileInPath = Path + '\\' + Name;
			//System.out.println(FileInPath);
			FileName = Name;
			try{
				WriteFile();
			}catch(IOException  e){
				//System.out.println("File not found");
			}
			Feature.clear();
			FeatureYear = "";
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