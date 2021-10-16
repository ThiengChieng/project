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
import java.awt.Component; 

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



public class layouttest2{
	private static String imageArray[]={"D:\\notepad++\\JAVA_program\\image\\add.png","D:\\notepad++\\JAVA_program\\image\\del.png","D:\\notepad++\\JAVA_program\\image\\addall.png","D:\\notepad++\\JAVA_program\\image\\delall.png"};
	private static JPanel panel1=new JPanel();
	private static JPanel panel2=new JPanel();
	private static JPanel panel3=new JPanel();
	private static JPanel panel4=new JPanel();
	private static JButton button = new JButton();
	private static JButton button2 = new JButton();
	private static JButton button3 = new JButton();
	private static JButton button4 = new JButton();
	public layouttest2() {
       //JFrame frame = new JFrame("Test");
	   
	   
	   //Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); // 取得螢幕尺寸
	   //frame.setSize(screenSize.width,screenSize.height);//設定視窗顯示大小與螢幕尺寸相同
       //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       DefaultListModel<String> defaultListModel = new DefaultListModel<String>();
       defaultListModel.addElement("1");
       defaultListModel.addElement("2");
       defaultListModel.addElement("Blo blo blo");

       final JList<String> list = new JList<String>();
       list.setPreferredSize(new Dimension(400, 200));
       list.setModel(defaultListModel);
	   
	   
	   DefaultListModel<String> addListModel = new DefaultListModel<String>();
	   final JList<String> addlist = new JList<String>();
	   addlist.setPreferredSize(new Dimension(400, 200));
       addlist.setModel(addListModel);

       
	   ImageIcon image=new ImageIcon(imageArray[0]);
	   button.setIcon(image);
       button.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
			   
               ((DefaultListModel<String>)addlist.getModel()).addElement(list.getSelectedValue().toString());
			   defaultListModel.remove(list.getSelectedIndex());
           }
       });
	   
	   ImageIcon image2=new ImageIcon(imageArray[1]);
	   button2.setIcon(image2);
	   button2.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
			   ((DefaultListModel<String>)list.getModel()).addElement(addlist.getSelectedValue().toString());
               addListModel.remove(addlist.getSelectedIndex());
			   
           }
       });
	   
	   ImageIcon image3=new ImageIcon(imageArray[2]);
	   button3.setIcon(image3);
	   button3.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
			   int defsize = list.getModel().getSize();
			   for(int i =0;i<defsize;i++){
				   ((DefaultListModel<String>)addlist.getModel()).addElement(list.getModel().getElementAt(i));
			   }
               defaultListModel.clear();
			   
           }
       });
	   
	   
	   ImageIcon image4=new ImageIcon(imageArray[3]);
	   button4.setIcon(image4);
	   button4.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
			   int addsize = addlist.getModel().getSize();
			   for(int i =0;i<addsize;i++){
				   ((DefaultListModel<String>)list.getModel()).addElement(addlist.getModel().getElementAt(i));
			   }
               addListModel.clear();
			   
           }
       });
	  
	   panel4.setLayout(new GridLayout(4,1));
	   panel4.setLayout( new BoxLayout(panel4, BoxLayout.Y_AXIS));
	  
	  
	   button.setAlignmentX(Component.CENTER_ALIGNMENT); 
	   button2.setAlignmentX(Component.CENTER_ALIGNMENT); 
	   button3.setAlignmentX(Component.CENTER_ALIGNMENT); 
	   button4.setAlignmentX(Component.CENTER_ALIGNMENT); 
	   
	   panel4.add(button);
	   panel4.add(button2);
	   panel4.add(button3);
	   panel4.add(button4);
	   
	   
	   panel2.setBackground(new Color(100+(10*1),100+(10*1),100+(10*1)));
	   panel3.setBackground(new Color(100+(10*2),100+(10*2),100+(10*2)));
	   panel4.setBackground(new Color(100+(10*3),100+(10*3),100+(10*3)));
	   
	   panel2.add(new JScrollPane(list), BorderLayout.CENTER);
	   panel3.add(new JScrollPane(addlist), BorderLayout.CENTER);
	   
	   panel1.setLayout(new GridLayout(1,3));
	   panel1.add(panel2);
	   panel1.add(panel4);
	   panel1.add(panel3);
       //frame.setLayout(new FlowLayout());
	   //frame.add(panel1, BorderLayout.CENTER);
	   //frame.add(button4, BorderLayout.SOUTH);
	   //frame.add(button3, BorderLayout.SOUTH);
       //frame.add(button2, BorderLayout.SOUTH);
       //frame.add(button, BorderLayout.SOUTH);
	   

       //frame.setVisible(true);
   }
   public JPanel CALLLISTPANEL(){
		return panel1;
	}
}