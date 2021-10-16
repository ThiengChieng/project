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
public class TestFileRadio4 {
   private static JRadioButton[] jRadioButton;
   private ButtonGroup buttonGroup = new ButtonGroup();   
   private ButtonGroup buttonGroup2 = new ButtonGroup();   
   private RadioButtonListener radioButtonListener = new RadioButtonListener();
   private static int x=0;
   private static int x2=0;
   public static String SRadioB="";
   public static String SRadioB2="";
   public static String OnlyName="";
   private static String[] S = new String[1000];
   public static String[] S2 = new String[1000];
   public static JPanel panel = new JPanel();
   public static JPanel panel2 = new JPanel();
   public static JRadioButton[] jRadioButton2;
   private static String JUD_FILE[] = {"Cut","Wa_Ch","Feature","Model"};
   private static int judfile=-1;
   public static int click=0;
   
   public TestFileRadio4(int jud){ 
		click=0;
		x=0;
		judfile=-1;
		SRadioB="";
		SRadioB2="";
		
		
		judfile=jud;
		panel.removeAll();
		panel2.removeAll();
		
		panel2.setLayout(new GridLayout(100,5));
		
		panel.setBackground(new Color(100,100,100));
		System.out.println("Test the jud is :"+jud) ;
		//panel.setLayout(new GridLayout(100,1));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		String[] C;
		
		
		File directoryPath = new File("Hos_Data/Doc_Train/"+JUD_FILE[jud]);
		//System.out.println(directoryPath+"xxxxxxxxxxxxxxxxxxxxxxxxxxx") ;
		C = list(directoryPath);
	
		JRadioButton[] jRadioButton = new JRadioButton[x];
		
		for(int i = 0;i<x;i++){
			jRadioButton[i]=new JRadioButton();
			jRadioButton[i].setText(C[i]);
			//System.out.println("Test the text is :"+C[i]) ;
			Font jRadioButtonFont=new Font(jRadioButton[i].getFont().getName(),jRadioButton[i].getFont().getStyle(),30);  //將字體大小設為50
			jRadioButton[i].setFont(jRadioButtonFont); 		
			jRadioButton[i].setBackground(new Color(100,100,100));
			jRadioButton[i].setForeground(Color.white);
			panel.add(jRadioButton[i]);
			buttonGroup.add(jRadioButton[i]);
			jRadioButton[i].addActionListener(radioButtonListener);
	
				
		}		
		panel.revalidate();
		panel.repaint();
		
		buttonGroup.clearSelection();
		
		
		panel2.setBackground(new Color(110,110,110));
		
		
	}
   private String[] list(File directoryPath) {
	   String[] name= new String[1000];
	   File filesList[] = directoryPath.listFiles();
       System.out.println("List of files and directories in the specified directory:");
	   if(directoryPath.exists()){
		    System.out.println("進");
		    for(File file : filesList) {
			   if(file.isDirectory()){
				  S[x]=(file.getName()).toString();
				  System.out.println("Test the directoryPath is :"+S[x]) ;
				  x++;
			   }
			 
		    }
		   
			return S;
	   }else{
		    return null;
	   }
   }
   public class RadioButtonListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent arg0) {
			
			String[] C2;	
            JRadioButton temp=(JRadioButton)arg0.getSource();
            if(temp.isSelected()){
                System.out.println(temp.getText());
				SRadioB=temp.getText();
				
            }
			File file2 = new File("Hos_Data/Doc_Train/"+JUD_FILE[judfile]+"/"+SRadioB+File.separator);
			System.out.println(file2.toString());
			C2 = list2(file2);
			System.out.println(C2);
		
			JRadioButton[] jRadioButton2 = new JRadioButton[x2];
			panel2.removeAll();
			if(C2[0]=="0"){
				System.out.println("2222222222222222222222222");
				panel2.removeAll();
				panel2.revalidate();//Cpanel[0]版面重新布局
				panel2.repaint();//Cpanel[0]版面重新繪製
			}else{
				for(int i = 0;i<x2;i++){
					//System.out.println("OOOK");
					C2[i]=C2[i].replace("Hos_Data\\Doc_Train\\"+JUD_FILE[judfile]+"\\"+SRadioB+"\\", "");
					jRadioButton2[i]=new JRadioButton();
					
					jRadioButton2[i].setText(C2[i]); 
					Font jRadioButton2Font=new Font(jRadioButton2[i].getFont().getName(),jRadioButton2[i].getFont().getStyle(),30);  //將字體大小設為50
					jRadioButton2[i].setFont(jRadioButton2Font); 
					jRadioButton2[i].setBackground(new Color(110,110,110));
					jRadioButton2[i].setForeground(Color.white);
					panel2.add(jRadioButton2[i]);
					panel2.revalidate();//Cpanel[0]版面重新布局
					panel2.repaint();//Cpanel[0]版面重新繪製
					buttonGroup2.add(jRadioButton2[i]);
					
					
					jRadioButton2[i].addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							//System.out.println("哈哈哈哈哈");
							JRadioButton temp2=(JRadioButton)e.getSource();
							if(temp2.isSelected()){
								OnlyName="";
								//System.out.println("Hos_Data/Doc_Train/"+JUD_FILE[judfile]+"/"+SRadioB+"/"+temp2.getText());
								SRadioB2=("Hos_Data\\Doc_Train\\"+JUD_FILE[judfile]+"\\"+SRadioB+"\\"+temp2.getText());
								OnlyName=temp2.getText();
								click=1;
								//System.out.println(SRadioB2);
								//System.out.println(OnlyName);
							}

						}
					});
					
							
				
				}
				buttonGroup2.clearSelection();
			}
			
			
			
				
			
             
        }
	}
	
	
	private String[] list2(File file2) {
		x2=0;
		String[] name= new String[1000];
		File[] lists2 = file2.listFiles();
		
		S2[0]="0";
			for (int i = 0; i < lists2.length; i++) {
				
				if(lists2[i].getPath().endsWith(".csv")){
					//name[x2] = lists2[i].getPath().replace("D:\\notepad++\\JAVA_program", "").replace("\\", "").replace(".csv", "");
					name[x2] = lists2[i].getPath().replace("Hos_Data\\Doc_Train\\"+JUD_FILE[judfile]+"\\"+SRadioB+"\\", "");
					S2[x2]=name[x2];
					//System.out.println("11111111111111111111111111111111");
					//System.out.println(lists2[i].getPath());
					//System.out.println(S2[x2]);
					
					x2++;
				}
				
			}
		
		return S2;
		
	}
	
	public String getRadioB(){
		return SRadioB2;
	}
	public String getOnlyName(){
		return OnlyName;
	}
	
	public String[] getfoldersize(){
		return S;
	}
	
	public String[] getfilesize(){
		return S2;
	}
	
	public int IsClick(){
		return click;
	}

}