import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;
import java.lang.ProcessBuilder;
 
public class ALLINONE_RF_PreAll2{
	
	public ALLINONE_RF_PreAll2() {
	
		  try {
			System.out.println("有執行預測隨機森林");
			ProcessBuilder builder = new ProcessBuilder("python", "RF_PredictAll3.py");
			builder.redirectErrorStream(true);
			Process proc = builder.start();
			
			InputStream fis=proc.getInputStream();
			//錯誤流

			InputStreamReader isr=new InputStreamReader(fis);
			//用緩沖器讀行
			BufferedReader br=new BufferedReader(isr);
			String line=null;
			//直到讀完為止
			while((line=br.readLine())!=null) {
			//有可能發生阻塞的問題
					//System.out.println("return input Str:" + line);
			}

			int exitVal = proc.waitFor();
			System.out.println("exitVal:" + exitVal);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
