import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class FeatureSave{
	private ArrayList<String> isaList;//要擷取的特徵
	private ArrayList<String[]> TwoD = new ArrayList<String[]>();//原始檔案內容
	private ArrayList<Integer> FeatureCheck;//特徵在檔案中的索引值
	private String Target = "診療分群"; //目標
	private String FileInPath;//原始檔案路徑
	private String FileoutPath = "Hos_Data\\Doc_Train\\Feature\\";//輸出檔案路徑
	private String FileName;//檔案名稱
	private String aDelimiter = ",";
	private String line = null;
	
	
	public FeatureSave(ArrayList<Integer> Feature, String Path, String Name){
		System.out.println("禎禎傳進來的"+Feature);
		System.out.println(Path.substring(25,Path.length()));
		System.out.println(Path);
		
		FileoutPath = FileoutPath + Path.substring(25,30);
		if(Feature.contains(36)){
			
			Feature.remove(Feature.get(Feature.indexOf(36)));
			
		}
		for(int i =0;i<Feature.size();i++){
			Feature.set(i, Feature.get(i)+37);//重新替代(所要的欄位在第37欄)
		}
		System.out.println("禎禎+37後的"+Feature);
		
		/*儲存所選特徵欄位號碼*/
		try{
		BufferedWriter TTC = new BufferedWriter(new FileWriter(FileoutPath + Name.replace(".csv",".txt")));
			for(int i =0;i<Feature.size();i++){
				System.out.println("write "+Feature);
				TTC.write(Feature.get(i).toString());
				TTC.write("\n");
			}
			TTC.close();
		}catch(IOException e){}
		/*儲存所選特徵欄位號碼*/
		FeatureCheck = Feature;
		FeatureCheck.add(36);
		//Collections.sort(FeatureCheck);
		System.out.println("冠宏的"+Feature);
		
		FileInPath = Path;
		FileName = Name;
		try{
			WriteFile();
		}catch(IOException  e){
			System.out.println("File not found");
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
		
		bw.close();
	}
	
	public String getOutPath(){
		System.out.println("郭冠宏的最後檔案路徑"+FileoutPath+FileName);
		return FileoutPath+FileName;
	}
}