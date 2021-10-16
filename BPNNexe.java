import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

 
public class BPNNexe {
	
	public BPNNexe(){
		Process proc;
		try {
			//"python ./TARGET.py"         
			proc = Runtime.getRuntime().exec("python BPNN_C.py");
				
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
