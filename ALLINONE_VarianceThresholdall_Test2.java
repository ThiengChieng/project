import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;

 
public class ALLINONE_VarianceThresholdall_Test2{
	
	public ALLINONE_VarianceThresholdall_Test2() {
		
		  try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec("python VarianceThresholdall_Test2.py");
			InputStream stdin = proc.getInputStream();
			InputStreamReader isr = new InputStreamReader(stdin);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			System.out.println("");
			while ((line = br.readLine()) != null)
			System.out.println(line);
			System.out.println("");
			int exitVal = proc.waitFor();
			System.out.println("Process exitValue: " + exitVal);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
