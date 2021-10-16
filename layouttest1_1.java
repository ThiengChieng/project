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

/*
PP-----整個版面的Panel數(不含Cardlayout)(int)
panel[]-----整個版面的Panel之陣列(JPanel)
BB-----左方步驟按鈕數(int)
button[]-----左方步驟按鈕之陣列(JButton)
CP-----CardLayout Panel數(int)
Cpanel[]-----CardLayout Panel之陣列(CardLayout)
DP-----提示欄CardLayout Panel數(int)
CDpanel[]-----提示欄CardLayout Panel之陣列(CardLayout)
HintArray[]-----提示欄訊息之陣列(String)
CPArray[]-----CardLayout Panel代表號之陣列(String)
imageArray[]-----左方按鈕圖片檔案位置之陣列(String)
logo-----醫院的LOGO(JLable)
cardlayout1-----中間版面的CardLayout(CardLayout)
cardlayout2-----提示訊息版面的CardLayout(CardLayout)
oporfile-----開啟原始檔(File)
readtxt1-----將readtxt_3新增出的物件名稱(readtxt_3)
tarea-----顯示JTable的物件(JTextArea)
frame-----版面名稱(JFrame)

HiBuArray[]-----提示欄按鈕名稱之陣列(String)


*/


public class layouttest1_1{
	private static JFrame frame;
	public static JPanel panel0=new JPanel();
	public static JPanel panel1=new JPanel();
	public static JPanel panel2=new JPanel();
	private static TestFileRadio6 fileradiob3;
	private static TestFileRadio5 fileradiob4;
	private static String foldername;
	//public static JPanel returnpanel=new JPanel();
	public static JScrollPane jScrollPane;
	public static JScrollPane jScrollPane2;
	//Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); // 取得螢幕尺寸
	public static int jud=0;
	public static int judfile=0;

	public layouttest1_1(int judfile){
		jud=0;
		panel0.removeAll();
		panel1.removeAll();
		panel2.removeAll();

		if(judfile<=3){
			jud=judfile;
			fileradiob3 = new TestFileRadio6(judfile);
			jScrollPane = new JScrollPane(fileradiob3.panel);
			jScrollPane2 = new JScrollPane(fileradiob3.panel2);
		}else{
			jud=judfile;
			judfile=judfile-4;
			
			fileradiob4 = new TestFileRadio5(judfile);
			jScrollPane = new JScrollPane(fileradiob4.panel);
			jScrollPane2 = new JScrollPane(fileradiob4.panel2);
		}
		
		

		System.out.println("TTTTTTTTTTTTTT");
		
		panel1.add(jScrollPane);
		panel2.add(jScrollPane2);
		panel1.revalidate();
		panel1.repaint();
		panel2.revalidate();
		panel2.repaint();

		panel1.setBackground(new Color(100,100,100));
		panel2.setBackground(new Color(110,110,110));
		//panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 20,20));
		//panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 20,20));
		//panel1.setPreferredSize(new Dimension(200,screenSize.height));
		panel0.setLayout(new BorderLayout());
	
		panel0.add(jScrollPane,BorderLayout.WEST);
		panel0.add(jScrollPane2,BorderLayout.CENTER);
		
		//returnpanel.setLayout(new BorderLayout());
		//returnpanel.add(panel0,BorderLayout.CENTER);
		
	}
	
	public JPanel CALLFILECH(){
		return panel0;
		
	}
	
	public String CALLFILPATH(){
		System.out.println("Success2");
		
		if(jud<=3){
			//jud=0;
			return fileradiob3.getRadioB();
		}else{
			//jud=0;
			return fileradiob4.getRadioB();
		}
		
	
		
	}
	public String CALLOnlyName(){
		System.out.println("Success3");
		if(jud<=3){
			jud=0;
			return fileradiob3.getOnlyName();
		}else{
			jud=0;
			return fileradiob4.getOnlyName();
		}
		
	
		
	}
	
	
	public String[] getfoldersize(){
		if(jud<=3){
			return fileradiob3.getfoldersize();
		}else{
			return fileradiob4.getfoldersize();
		}
	
	}

	public String[] getfilesize(){
		if(jud<=3){
			return fileradiob3.getfilesize();
		}else{
			return fileradiob4.getfilesize();
		}
	}

	public int IsClick(){
		if(jud<=3){
			System.out.println("是否有按下"+fileradiob3.click);
			return fileradiob3.click;
		}else{
			System.out.println("是否有按下"+fileradiob4.click);
			return fileradiob4.click;
		}
	}

}