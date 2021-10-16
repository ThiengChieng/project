import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import com.opencsv.CSVReader;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Collections;

public class CSVRead {
	private String FileName;
	public CSVRead(){
		
	}

	public ArrayList<ArrayList> Result(String FileName) throws IOException {
		
		CSVReader reader = new CSVReader(new FileReader(FileName));

		ArrayList<String[]> Input = new ArrayList<String[]>();
		
		ArrayList<ArrayList> OutPut = new ArrayList<ArrayList>();
		Date test = new Date();
		String date;
		String inHTime;
		String inRTime;
		String outTime;
		long temp;
		int temp2;
		int count = 0;
		SimpleDateFormat simple = new SimpleDateFormat();
		
		
		String[] record = null;
		try{
			while((record = reader.readNext()) != null){
				Input.add(record);
				count++;
			}
		}catch(Exception e){
		}
		reader.close();
		
		for(int i = 1; i < count; i++){
			ArrayList inArray = new ArrayList();
			ArrayList inArray2 = new ArrayList();
			ArrayList inArray3 = new ArrayList();
			date = (Input.get(i))[0];
			inHTime = (Input.get(i))[2];
			inRTime = (Input.get(i))[3];
			temp = Math.round(Double.parseDouble((Input.get(i))[1])*60);
			temp2 = (int)temp;
			simple.applyPattern("HH:mm:ss");
			try{
				test = simple.parse(inRTime);
			}catch(Exception e){
			}
			test.setSeconds(test.getSeconds() + temp2);
			outTime = simple.format(test);
			
			inArray.add(date);
			inArray.add(inHTime);
			inArray.add(1);
			OutPut.add(inArray);
			
			inArray2.add(date);
			inArray2.add(inRTime);
			inArray2.add(2);
			OutPut.add(inArray2);
			
			inArray3.add(date);
			inArray3.add(outTime);
			inArray3.add(3);
			OutPut.add(inArray3);
		}
		/*for(int i = 0; i < OutPut.size(); i++){
			System.out.println(OutPut.get(i).get(0));
		}*/
		String a = (String)OutPut.get(0).get(0);
		String b = (String)OutPut.get(1).get(0);
		if(b.equals(a)){
			System.out.println("Yes");
		}else{
			System.out.println("No");
		}
		ALSort al = new ALSort();
		Collections.sort(OutPut,al);
		for(int i = 0; i < OutPut.size(); i++){
			System.out.println(OutPut.get(i));
		}
	}
	private class ALSort implements Comparator<ArrayList>{
		public int compare(ArrayList a, ArrayList b){
			String Date1 = (String)a.get(0);
			String Date2 = (String)b.get(0);
			String Time1 = (String)a.get(1);
			String Time2 = (String)b.get(1);
			int m1 = (int)a.get(2);
			int m2 = (int)b.get(2);
			if(!(Date1.equals(Date2))){
				return Date1.compareTo(Date2);
			}if(!(Time1.equals(Time2))){
				return Time1.compareTo(Time2);
			}else{
				return m1-m2;
			}
		}
	}
	

}