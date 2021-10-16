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
import java.io.File;
import java.io.IOException;
public class TestFileRadio3 extends JPanel{
   private static JRadioButton[] jRadioButton;
   private ButtonGroup buttonGroup = new ButtonGroup();   
   private RadioButtonListener radioButtonListener = new RadioButtonListener();
   private static int x=0;
   public static String SRadioB="";
   private static String[] S;
   
   public TestFileRadio3(){ 
	   ButtonGroup bg=new ButtonGroup();
	   setLayout(new GridLayout(15,100));
	   String[] C;
	   S = new String[101];
	   File directoryPath = new File("D:\\notepad++\\JAVA_program");
	   C = list(directoryPath);
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
   private String[] list(File directoryPath) {
	   String[] name= new String[200];
	   File filesList[] = directoryPath.listFiles();
       System.out.println("List of files and directories in the specified directory:");
	  
       for(File file : filesList) {
		  if(file.isDirectory()){
			 S[x]=(file.getName()).toString();
			 x++;
			 /*System.out.println("File name: "+file.getName());
			 System.out.println("File path: "+file.getAbsolutePath());
			 System.out.println("Size :"+file.getTotalSpace());
			 System.out.println(" ");*/
		  }
         
       }
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