import java.io.File;
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 
import java.util.Arrays;

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

public class TestFileRadio2 extends JPanel{
	private static JRadioButton[] jRadioButton; 
	private static String[] S;
	private static int x=0;
	private ButtonGroup buttonGroup = new ButtonGroup();
	private RadioButtonListener radioButtonListener = new RadioButtonListener();
	public static String SRadioB="";
	
	public TestFileRadio2(String folder){ 
		ButtonGroup bg=new ButtonGroup();
		String[] C;	
		setLayout(new GridLayout(15,100));
        File file = new File(folder+File.separator);  
		S = new String[200];
        C = list(file);
		JRadioButton[] jRadioButton = new JRadioButton[x];
		for(int i = 0;i<x;i++){
			
			jRadioButton[i]=new JRadioButton();
			jRadioButton[i].setText(C[i]); 
			bg.add(jRadioButton[i]);
			add(jRadioButton[i]);
			buttonGroup.add(jRadioButton[i]);
			jRadioButton[i].addActionListener(radioButtonListener);
		}		
		
	 }

	private String[] list(File file) {
		String[] name= new String[200];
		
		/*if (file.isDirectory())// 判斷file是否是目錄
		{
			File[] lists = file.listFiles();
			if (lists != null) {
				for (int i = 0; i < lists.length; i++) {
					name = list(lists[i]);// 是目錄就遞迴進入目錄內再進行判斷
				}
				
			}
		}*/
		File[] lists = file.listFiles();
		//System.out.println(lists.length);
			for (int i = 0; i < lists.length; i++) {
				if(lists[i].getPath().endsWith(".csv")){
					name[x] = lists[i].getPath().replace("D:\\notepad++\\JAVA_program", "").replace("\\", "").replace(".csv", "");
					S[x]=name[x];
					System.out.println(lists[i].getPath());
					x++;
				}
				
			}
		/*if(file.getPath().endsWith(".csv")){
			
			name[x] = file.getPath().replace("D:\\notepad++\\JAVA_program\\cut", "").replace("\\", "").replace(".csv", "");
			S[x]=name[x];
			x++;
			
		}*/
		
		return S;
		
	}
	
	 public class RadioButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            JRadioButton temp=(JRadioButton)arg0.getSource();
            if(temp.isSelected()){
                System.out.println(temp.getText());
				SRadioB=temp.getText();
            }
             
        }
	}
	
	public String getRadioB(){
		return SRadioB;
	}
}