import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

 
public class RFPredictexe {
	
	public RFPredictexe(){
		Process proc;
		try {
			System.out.println("到底有沒有執行 ");
			
			//"python ./TARGET.py"         
			proc = Runtime.getRuntime().exec("python RF_Predict2_KKH.py");
				
			BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = null;	
			while ((line = in.readLine()) != null) {
				System.out.println(line);
					
			}
				
			in.close();
			proc.waitFor();
				
		} catch (IOException e) {
				
			e.printStackTrace();
		} catch (InterruptedException e) {
				
			e.printStackTrace();
		}
	}
}
