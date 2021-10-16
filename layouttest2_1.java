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
import javax.swing.border.EmptyBorder;
 
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.util.ArrayList;





public class layouttest2_1{
	private static String imageArray[]={"D:\\notepad++\\JAVA_program\\image\\add.png","D:\\notepad++\\JAVA_program\\image\\del.png","D:\\notepad++\\JAVA_program\\image\\addall.png","D:\\notepad++\\JAVA_program\\image\\delall.png"};
	private static JPanel panel0=new JPanel();
	private static JPanel panel1=new JPanel();
	private static JPanel panel2=new JPanel();
	private static JPanel panel3=new JPanel();
	private static JPanel panel4=new JPanel();
	private static JPanel panel5=new JPanel();
	private static JButton button = new JButton();
	private static JButton button2 = new JButton();
	private static JButton button3 = new JButton();
	private static JButton button4 = new JButton();
	private static ArrayList<String> KEVIN = new ArrayList<String>();
	private static JList<String> addlist = new JList<String>();
	private static JList<String> list = new JList<String>();
	private static JScrollPane scrollPane = new JScrollPane(list);;
	private static JScrollPane scrollPane2 = new JScrollPane(addlist);;
	

	public layouttest2_1(){
	
		panel0.removeAll();
		panel1.removeAll();
		panel2.removeAll();
		panel3.removeAll();
		panel4.removeAll();
		panel5.removeAll();
		KEVIN.removeAll(KEVIN);
		//JFrame frame = new JFrame("Test");
	   
	   
		//Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); // 取得螢幕尺寸
		//frame.setSize(screenSize.width,screenSize.height);//設定視窗顯示大小與螢幕尺寸相同
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		DefaultListModel<String> defaultListModel = new DefaultListModel<String>();
		defaultListModel.addElement("看診星期");
		defaultListModel.addElement("病患年齡");
		defaultListModel.addElement("班別早午診轉換");
		defaultListModel.addElement("實際看診順序");
		defaultListModel.addElement("掛號類別轉換");
		defaultListModel.addElement("病患性別轉換");
		defaultListModel.addElement("前次是否開立醫囑轉換");
		defaultListModel.addElement("初複診轉換");
		defaultListModel.addElement("4");
		defaultListModel.addElement("5");
		defaultListModel.addElement("6");
		defaultListModel.addElement("7");
		defaultListModel.addElement("8");
		defaultListModel.addElement("9");
		defaultListModel.addElement("10");
		defaultListModel.addElement("11");
		defaultListModel.addElement("12");
		defaultListModel.addElement("13");
		defaultListModel.addElement("14");
		defaultListModel.addElement("15");
		defaultListModel.addElement("16");
		defaultListModel.addElement("a");
		defaultListModel.addElement("b");
		defaultListModel.addElement("c");
		defaultListModel.addElement("d");
		defaultListModel.addElement("e");
		defaultListModel.addElement("f");
		defaultListModel.addElement("榮總方案");
		defaultListModel.addElement("隨機森林");

		
		

		
		
		list.setModel(defaultListModel);
		Font listFont=new Font(list.getFont().getName(),list.getFont().getStyle(),30);  //將字體大小設為50
		list.setFont(listFont);
		//list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		//list.setVisibleRowCount(0);
		
		//scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		//list.setPreferredSize(new Dimension(400, 200));
		//scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		   
		   
		DefaultListModel<String> addListModel = new DefaultListModel<String>();
		
		addlist.setModel(addListModel);
		Font addlistFont=new Font(addlist.getFont().getName(),addlist.getFont().getStyle(),30);  //將字體大小設為50
		addlist.setFont(addlistFont);
		
		//scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		//scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		   
		ImageIcon image=new ImageIcon(imageArray[0]);
		button.setIcon(image);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				   
				((DefaultListModel<String>)addlist.getModel()).addElement(list.getSelectedValue().toString());
				
				KEVIN.add(list.getSelectedValue().toString());
				System.out.println(list.getSelectedValue().toString());
				defaultListModel.remove(list.getSelectedIndex());
				
			}
		});
		   
		ImageIcon image2=new ImageIcon(imageArray[1]);
		button2.setIcon(image2);
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				((DefaultListModel<String>)list.getModel()).addElement(addlist.getSelectedValue().toString());
				
				//countaddlist-=1;
				//System.out.println(countaddlist);
				KEVIN.remove(addlist.getSelectedValue().toString());
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
					KEVIN.add(list.getModel().getElementAt(i));
				}
				defaultListModel.clear();
				
				//countaddlist= defsize;  
				//System.out.println(countaddlist);
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
				KEVIN.clear();
				
				//countaddlist=0;   
				//System.out.println(countaddlist);
			}
		});
		
		
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
		panel4.setBackground(new Color(100,100,100));
		
		button.setAlignmentX(Component.CENTER_ALIGNMENT); 
		button2.setAlignmentX(Component.CENTER_ALIGNMENT); 
		button3.setAlignmentX(Component.CENTER_ALIGNMENT); 
		button4.setAlignmentX(Component.CENTER_ALIGNMENT); 
		
		panel4.add(Box.createRigidArea(new Dimension(60, 120)));  
		panel4.add(button);
		panel4.add(Box.createRigidArea(new Dimension(30, 30)));
		panel4.add(button2);
		panel4.add(Box.createRigidArea(new Dimension(30, 30)));
		panel4.add(button3);
		panel4.add(Box.createRigidArea(new Dimension(30, 30)));
		panel4.add(button4);
		
		
		panel2.setBackground(new Color(100,100,100));
		panel2.setLayout(new BorderLayout());
		
		Font reruFont2 = new Font("SansSerif", Font.BOLD, 36);
        Color reruFontColor2 = Color.white;
        panel2.setBorder(new TitledBorder(new LineBorder(Color.ORANGE, 0,false),"候選特徵：", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, reruFont2, reruFontColor2));
  
		panel2.add(Box.createRigidArea(new Dimension(30, 30)), BorderLayout.NORTH);
		panel2.add(Box.createRigidArea(new Dimension(30, 30)), BorderLayout.EAST);		
		panel2.add(Box.createRigidArea(new Dimension(50, 30)), BorderLayout.WEST); 
		panel2.add(Box.createRigidArea(new Dimension(30, 30)), BorderLayout.SOUTH); 
		panel2.add(scrollPane, BorderLayout.CENTER);
		
		panel3.setBackground(new Color(100,100,100));
		panel3.setLayout(new BorderLayout());
		
		Font reruFont1 = new Font("SansSerif", Font.BOLD, 36);
        Color reruFontColor1 = Color.white;
        panel3.setBorder(new TitledBorder(new LineBorder(Color.ORANGE, 0,false),"已選特徵：", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, reruFont1, reruFontColor1));
		
		panel3.add(Box.createRigidArea(new Dimension(30, 30)), BorderLayout.NORTH);
		panel3.add(Box.createRigidArea(new Dimension(30, 30)), BorderLayout.WEST);
		panel3.add(Box.createRigidArea(new Dimension(30, 30)), BorderLayout.EAST);
		panel3.add(Box.createRigidArea(new Dimension(30, 30)), BorderLayout.SOUTH); 
		panel3.add(scrollPane2, BorderLayout.CENTER);
		

		panel0.setLayout(new BorderLayout());
		JTextArea jTextArea = new JTextArea("1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n1\n0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000008");
        //jTextArea.setLineWrap(true);//自動換行
		Font textareaFont=new Font(jTextArea.getFont().getName(),jTextArea.getFont().getStyle(),36);  //將字體大小設為50
		jTextArea.setFont(textareaFont);
		jTextArea.setEditable(false);
		
		Font reruFont = new Font("SansSerif", Font.BOLD, 36);
        Color reruFontColor = Color.white;
		panel0.setBorder(new TitledBorder(new LineBorder(Color.ORANGE, 0,false),"關聯規則探勘結果：", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, reruFont, reruFontColor));
        //將多行文字框，加到滾動捲軸面板中
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
		
		
		panel0.add(Box.createRigidArea(new Dimension(30, 30)), BorderLayout.NORTH);
		panel0.add(Box.createRigidArea(new Dimension(30, 30)), BorderLayout.EAST);
		panel0.add(Box.createRigidArea(new Dimension(30, 30)), BorderLayout.SOUTH);
		panel0.add(Box.createRigidArea(new Dimension(50, 30)), BorderLayout.WEST);
		panel0.add(jScrollPane, BorderLayout.CENTER);
		
		
		panel0.setBackground(new Color(100,100,100));		
		panel1.setLayout(new BorderLayout());
		panel1.setPreferredSize(new Dimension(800,800));
		panel2.setPreferredSize(new Dimension(800,800));
		panel3.setPreferredSize(new Dimension(800,800));
		panel1.add(panel2,BorderLayout.WEST);
		panel1.add(panel4,BorderLayout.CENTER);
		panel1.add(panel3,BorderLayout.EAST);
		
		panel0.setPreferredSize(new Dimension(400,400));
		panel5.setLayout(new BorderLayout());
		panel5.setBackground(new Color(100,100,100));
		panel5.add(Box.createRigidArea(new Dimension(15, 15)), BorderLayout.NORTH);
		
		panel5.add(panel0, BorderLayout.SOUTH);
		panel5.add(panel1, BorderLayout.CENTER);
		
		//frame.setLayout(new BorderLayout());
		//frame.add(panel5, BorderLayout.CENTER);
		
		
		   

		//frame.setVisible(true);
    }
    public JPanel CALLLISTPANEL(){
		return panel5;
	}
	
	public ArrayList CALLARRAYLIST(){
		System.out.println(KEVIN);
		return KEVIN;
	}
}