import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

 
public class BPNN_Lexe {
	
	public BPNN_Lexe(){
		Process proc;
		try {
			//"python ./TARGET.py" 
			System.out.println("BPNN有沒有近來呼叫");			
			proc = Runtime.getRuntime().exec("python BPNN_L.py");
				
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
