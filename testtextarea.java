import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;
import java.awt.Font; //文字的import
import java.io.*;//開啟檔案的import

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.*;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.Timer;
import java.io.IOException;


public class testtextarea {
	public static JPanel panel;
	public static JTextArea jTextArea;
	public static JScrollPane jScrollPane;
	public testtextarea(String filename){
	//public static void main(String[] args)throws InterruptedException {
		//JFrame frame = new JFrame("");
		panel = new JPanel();
		//Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); // 取得螢幕尺寸
		//frame.setSize(screenSize.width,screenSize.height);//設定視窗顯示大小與螢幕尺寸相同
		//frame.setLayout(new BorderLayout());
		jTextArea = new JTextArea();
		int x=0;
		try{
			BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filename))));
			String line = null;
			//line = bf.readLine();
		
			while((line = bf.readLine()) != null){
				jTextArea.append(line+"\n");
				
				x++;
				//System.out.println(line);
				//System.out.println("字"+x);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	
			
			
			
					 
		
	
		jScrollPane = new JScrollPane(jTextArea);
		//jTextArea.setLineWrap(true);//自動換行
		Font textareaFont=new Font(jTextArea.getFont().getName(),jTextArea.getFont().getStyle(),20);  //將字體大小設為50
		jTextArea.setFont(textareaFont);
		jTextArea.setEditable(false);
		//panel.add(jScrollPane);
		//frame.add(jScrollPane);
		//frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//將X設定為關閉所有程式
		//frame.setVisible(true);//顯示視窗
	}
	
	public JScrollPane CALLTEXTAREA(){
		return jScrollPane;
	}
}