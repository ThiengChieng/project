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
import javax.swing.BoxLayout;

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



public class layouttest5{
	private static JFrame frame;
	public static JPanel panel;
	private static layouttest3 layout,layout2,layout3,layout4;
	
	
	
	public static void main(String[] args){
		JFrame.setDefaultLookAndFeelDecorated(true);//將版面設美觀用
		
		/*Panel Layout 設定START*/
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); // 取得螢幕尺寸
		JFrame frame = new JFrame("");//外圍最大視窗
        frame.setSize(screenSize.width,screenSize.height);//設定視窗顯示大小與螢幕尺寸相同
        frame.setLayout(new BorderLayout());//將視窗排版設定為BorderLayout
	
		panel=new JPanel();
		
		panel.setLayout(new FlowLayout());
		panel.setBackground(new Color(100,100,100));
		
		frame.add(panel,BorderLayout.CENTER);
		

		layout = new layouttest3("test");
		layout2 = new layouttest3("test2");
		layout3 = new layouttest3("test3");
		layout4 = new layouttest3("test4");
		
		
		//panel.add(layout.CALLPIC());//將readtxt_3所回傳之JScrollPane加到Upanel[0]
		panel.add(layout2.CALLPIC());
		panel.add(layout3.CALLPIC());
		//panel.add(layout4.CALLPIC());
		
		
		
		
		
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//將X設定為關閉所有程式
		frame.setVisible(true);//顯示視窗
    }
	
	
	
	/*implement START*/
	public static class buttonhandler implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
	}
	/*implement END*/
	
	
}