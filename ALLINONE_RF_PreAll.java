import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;

public class ALLINONE_RF_PreAll{
	public static void main(String[] args) {
		  try {
			System.out.println("有執行預測隨機森林");
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec("python RF_PredictAll3.py");
			InputStream fis=proc.getInputStream();
			InputStream ferrs=proc.getErrorStream();
			InputStreamReader isr=new InputStreamReader(fis);
			InputStreamReader errsr=new InputStreamReader(ferrs);
			BufferedReader br=new BufferedReader(isr);
			BufferedReader errbr=new BufferedReader(errsr);
			String line=null;
			String lineerr = null;
			
			while((line=br.readLine())!=null) {
				System.out.println("return input Str:" + line);
			}
			while((lineerr=errbr.readLine())!=null){
				System.out.println("return err Str:" + lineerr);
			}
			int exitVal = proc.waitFor();
			System.out.println("exitVal:" + exitVal);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
