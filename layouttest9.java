import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;
import java.awt.Font; 
import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.ArrayList;





public class layouttest9{
	private static JFrame frame;
	public static JPanel panel=new JPanel();
	private static JButton button0=new JButton();
	private static JButton button1=new JButton();
	private static JTextField jtextfield0=new JTextField();
	private static JTextField jtextfield1=new JTextField();
	private static JLabel label0=new JLabel("原始檔案選取：",SwingConstants.RIGHT);
	private static JLabel label1=new JLabel("模型選取：",SwingConstants.RIGHT);
	private static JLabel label2=new JLabel("特徵方法選取：",SwingConstants.RIGHT);
	private static JLabel label3=new JLabel("預測檔案選取：",SwingConstants.RIGHT);
	private static ButtonGroup buttonGroup0 = new ButtonGroup();
	private static ButtonGroup buttonGroup1 = new ButtonGroup();
    private static JRadioButton radiobtn0 = new JRadioButton("隨機森林");
	private static JRadioButton radiobtn1 = new JRadioButton("子群組探勘統計方法分析");
	private static JRadioButton radiobtn2 = new JRadioButton("卡方檢驗");
	private static JRadioButton radiobtn3 = new JRadioButton("方差過濾");
	private static JRadioButton radiobtn4 = new JRadioButton("以原始資料為主");
	
	private static GridBagConstraints c;
	private static File oporfile1,oporfile2;
	private RadioButtonListener radioButtonListener = new RadioButtonListener();
	public static String SRadioB="隨機森林";
	public static String SRadioB2="卡方檢驗";
	public static String SRadioB3="卡方檢驗";
	public static String ORIfile="";
	public static String PREfile="";
	private static String OriFileName[] = {"看診日期","看診科別","看診診間","病歷號","醫師卡號","跟診護理師卡號","病患性別","病患出生日期","掛號類別","初複診","掛號序號","班別早午診","前次是否開立醫囑","診斷碼1","診斷碼2","診斷碼3","診斷碼4","診斷碼5","診斷碼6","診斷碼7","診斷碼8","診斷碼9","診斷碼10","自助報到插卡時間","醫師插卡時間","醫師批價時間1","醫師批價時間2","醫師批價時間3","醫師批價時間4","醫師批價時間5","醫師批價時間6","醫師批價時間7","醫師批價時間8","醫師批價時間9","預估看診時間"};
	private static readtxt_3 readtxt_oporfile1,readtxt_oporfile2;
	
	

	public layouttest9(){
		//JFrame.setDefaultLookAndFeelDecorated(true);//將版面設美觀用
		//Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); // 取得螢幕尺寸
		//JFrame frame = new JFrame("");
        //frame.setSize(screenSize.width,screenSize.height);//設定視窗顯示大小與螢幕尺寸相同
        //frame.setLayout(new BorderLayout());
		panel.setLayout(new GridBagLayout());
		panel.setBackground(new Color(100,100,100));
		label0.setBackground(new Color(100,100,100));
		label1.setBackground(new Color(100,100,100));
		label2.setBackground(new Color(100,100,100));
		label3.setBackground(new Color(100,100,100));
		radiobtn0.setBackground(new Color(100,100,100));
		radiobtn1.setBackground(new Color(100,100,100));
		radiobtn2.setBackground(new Color(100,100,100));
		radiobtn3.setBackground(new Color(100,100,100));
		radiobtn4.setBackground(new Color(100,100,100));
		
		
		buttonGroup0.add(radiobtn0);
		buttonGroup0.add(radiobtn1);
		buttonGroup1.add(radiobtn2);
		buttonGroup1.add(radiobtn3);
		buttonGroup1.add(radiobtn4);
		
		radiobtn0.setSelected(true);
		radiobtn2.setSelected(true);
		
		ImageIcon pic_btn0=new ImageIcon("image\\allinone_ori.png");
		button0.setIcon(pic_btn0);
		button0.setBorderPainted(false);//BUTTON邊線設為透明
		button0.setContentAreaFilled(false);//將BUTTON背景設為透明的
		button0.setPreferredSize(new Dimension(100,88));
		button0.addActionListener(new buttonhandler());
		
		ImageIcon pic_btn1=new ImageIcon("image\\allinone_pre.png");
		button1.setIcon(pic_btn1);
		button1.setBorderPainted(false);//BUTTON邊線設為透明
		button1.setContentAreaFilled(false);//將BUTTON背景設為透明的
		button1.setPreferredSize(new Dimension(100,88));
		button1.addActionListener(new buttonhandler());
		
		
		Font font = new Font("微軟正黑體", Font.PLAIN, 32);
		jtextfield0.setFont(font);
		jtextfield1.setFont(font);
		jtextfield0.setEditable(false);
		jtextfield1.setEditable(false);
		label0.setFont(font);
		label0.setForeground(Color.white);
		label0.setPreferredSize(new Dimension(250,88));
		label1.setFont(font);
		label1.setForeground(Color.white);
		label1.setPreferredSize(new Dimension(250,88));
		label2.setFont(font);
		label2.setForeground(Color.white);
		label2.setPreferredSize(new Dimension(250,88));
		label3.setFont(font);
		label3.setForeground(Color.white);
		label3.setPreferredSize(new Dimension(250,88));
		radiobtn0.setFont(font);
		radiobtn0.setForeground(Color.white);
		radiobtn0.setPreferredSize(new Dimension(250,88));
		radiobtn0.addActionListener(radioButtonListener);
		radiobtn1.setFont(font);
		radiobtn1.setForeground(Color.white);
		radiobtn1.setPreferredSize(new Dimension(250,88));
		radiobtn1.addActionListener(radioButtonListener);
		radiobtn2.setFont(font);
		radiobtn2.setForeground(Color.white);
		radiobtn2.setPreferredSize(new Dimension(250,88));
		radiobtn2.addActionListener(radioButtonListener);
		radiobtn3.setFont(font);
		radiobtn3.setForeground(Color.white);
		radiobtn3.setPreferredSize(new Dimension(250,88));
		radiobtn3.addActionListener(radioButtonListener);
		radiobtn4.setFont(font);
		radiobtn4.setForeground(Color.white);
		radiobtn4.setPreferredSize(new Dimension(250,88));
		radiobtn4.addActionListener(radioButtonListener);
		
		
	
		
		
        c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;		
		panel.add(label0,c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 19; 
		c.fill = GridBagConstraints.BOTH;
		panel.add(Box.createRigidArea(new Dimension(1400, 30)),c);
		
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1; 
		c.fill = GridBagConstraints.BOTH;
		panel.add(button0,c);
		
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 19; 
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(jtextfield0,c);
		
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2; 
		c.fill = GridBagConstraints.BOTH;
		panel.add(label1,c);
		
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 2; 
		c.fill = GridBagConstraints.BOTH;
		panel.add(radiobtn0,c);
		
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 4;
		c.gridy = 2;
		c.gridwidth = 6; 
		c.fill = GridBagConstraints.BOTH;
		panel.add(Box.createRigidArea(new Dimension(150, 30)),c);
		
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 10;
		c.gridy = 2;
		c.gridwidth = 10; 
		c.fill = GridBagConstraints.BOTH;
		panel.add(radiobtn1,c);

		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2; 
		c.fill = GridBagConstraints.BOTH;
		panel.add(label2,c);
		
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 3;
		c.gridwidth = 2; 
		c.fill = GridBagConstraints.BOTH;
		panel.add(radiobtn2,c);
		
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 4;
		c.gridy = 3;
		c.gridwidth = 6; 
		c.fill = GridBagConstraints.BOTH;
		panel.add(Box.createRigidArea(new Dimension(150, 30)),c);
		
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 10;
		c.gridy = 3;
		c.gridwidth = 2; 
		c.fill = GridBagConstraints.BOTH;
		panel.add(radiobtn3,c);
		
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 12;
		c.gridy = 3;
		c.gridwidth = 6; 
		c.fill = GridBagConstraints.BOTH;
		panel.add(Box.createRigidArea(new Dimension(250, 30)),c);
		
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 18;
		c.gridy = 3;
		c.gridwidth = 2; 
		c.fill = GridBagConstraints.BOTH;
		panel.add(radiobtn4,c);
		
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1; 
		c.fill = GridBagConstraints.BOTH;
		panel.add(label3,c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 19; 
		c.fill = GridBagConstraints.BOTH;
		panel.add(Box.createRigidArea(new Dimension(1400, 30)),c);
		
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2; 
		c.fill = GridBagConstraints.BOTH;
		panel.add(button1,c);
		
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 2;
		c.gridy = 5;
		c.gridwidth = 19; 
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(jtextfield1,c);
        
		//frame.add(panel);
		//frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//將X設定為關閉所有程式
		//frame.setVisible(true);//顯示視窗
		
	}
	public JPanel AllInOne(){
		return panel;
	}
	
	
	public static class buttonhandler implements ActionListener {
		public void actionPerformed(ActionEvent e){
			//Font.BOLD
			//UIManager.put("OptionPane.messageFont",new Font("微軟正黑體", Font.PLAIN, 20));
			//UIManager.put("OptionPane.buttonFont",new Font("微軟正黑體", Font.PLAIN, 16));
			
			if((JButton)e.getSource()==button0){
				
				JFileChooser chooser1=new JFileChooser(".");//選擇檔案，並且預設位置為現在位置
				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV file", "csv");
				chooser1.setFileFilter(filter);
				int returnVal = chooser1.showOpenDialog(panel);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					oporfile1 = chooser1.getSelectedFile();//取得使用者所選檔案
					if(oporfile1.toString().contains(".csv")){
						readtxt_oporfile1 = new readtxt_3(oporfile1.toString());
						ArrayList<Integer> judFileNameSame=new ArrayList<Integer>();
						judFileNameSame.clear();
						for(int i = 0;i<readtxt_oporfile1.ColumnCount();i++){
							if(readtxt_oporfile1.getColumnName(i).equals(OriFileName[i])){
								judFileNameSame.add(0);
							}else{
								judFileNameSame.add(1);
							}
							//System.out.println(judFileNameSame.get(i));
						}
						if(judFileNameSame.contains(1)){
							JOptionPane.showMessageDialog(frame,"所選檔案不符合系統所要求內容(門診紀錄)");
						}else{
							try {
							  BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(oporfile1)));
							  jtextfield0.setText("");
							  jtextfield0.setText(oporfile1.toString());
							  System.out.println(oporfile1.toString());//測試，印出檔案路徑字串
							  ORIfile=oporfile1.toString();
							} catch (Exception v) {
							  System.out.println("檔案不存在,請檢查檔案名稱.");
							  
							}	
						}
						
					}else{
						JOptionPane.showMessageDialog(frame,"所選檔案不符合系統所需格式(.csv)");
					}
					
					
				}else{
					System.out.println("Operation is CANCELLED :(");
				}
				
				System.out.println("STEP1---選取原始檔");//測試，印出"STEP1---選取原始檔"
			
			}else if((JButton)e.getSource()==button1){
					JFileChooser chooser2=new JFileChooser(".");//選擇檔案，並且預設位置為現在位置
					FileNameExtensionFilter filter2 = new FileNameExtensionFilter("CSV file", "csv");
					chooser2.setFileFilter(filter2);
					int returnVal2 = chooser2.showOpenDialog(panel);
					if (returnVal2 == JFileChooser.APPROVE_OPTION){
						oporfile2 = chooser2.getSelectedFile();//取得使用者所選檔案
						if(oporfile2.toString().contains(".csv")){
							readtxt_oporfile2 = new readtxt_3(oporfile2.toString());
							ArrayList<Integer> judFileNameSame2=new ArrayList<Integer>();
							judFileNameSame2.clear();
							for(int i = 0;i<readtxt_oporfile2.ColumnCount();i++){
								if(readtxt_oporfile2.getColumnName(i).equals(OriFileName[i])){
									judFileNameSame2.add(0);
								}else{
									judFileNameSame2.add(1);
								}
								//System.out.println(judFileNameSame2.get(i));
							}
							if(judFileNameSame2.contains(1)){
								JOptionPane.showMessageDialog(frame,"所選檔案不符合系統所要求內容(門診紀錄)");
							}else{
								try {
								  BufferedReader input2 = new BufferedReader(new InputStreamReader(new FileInputStream(oporfile2)));
								  jtextfield1.setText("");
								  jtextfield1.setText(oporfile2.toString());
								  System.out.println(oporfile2.toString());//測試，印出檔案路徑字串
								  PREfile=oporfile2.toString();
								} catch (Exception v) {
								  System.out.println("檔案不存在,請檢查檔案名稱.");
								  
								}		
							}
						}else{
							JOptionPane.showMessageDialog(frame,"所選檔案不符合系統所需格式(.csv)");
						}
				}
			}
		}
	}
	
	
	
	public class RadioButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent arg0) {
            JRadioButton temp=(JRadioButton)arg0.getSource();
            if(temp.isSelected() && (temp==radiobtn0 || temp==radiobtn1)){
				if(temp.getText()=="子群組探勘統計方法分析"){
					buttonGroup1.clearSelection();
					radiobtn2.setEnabled(false);
					radiobtn3.setEnabled(false);
					radiobtn4.setEnabled(false);
					JOptionPane.showMessageDialog(frame,"子群組探勘統計方法分析無須選擇特徵方法");
					SRadioB2="無須選擇";
				}else if(temp.getText()=="隨機森林"){
					radiobtn2.setEnabled(true);
					radiobtn3.setEnabled(true);
					radiobtn4.setEnabled(true);
					SRadioB2=SRadioB3;
					
				}else{
					System.out.println("為選擇模型");
				}
				SRadioB=temp.getText();
		
                //System.out.println(temp.getText());
					
            }else if(temp.isSelected() && (temp==radiobtn2 || temp==radiobtn3 || temp==radiobtn4)){
				//radiobtn1.setEnabled(false);
				SRadioB2=temp.getText();	
				SRadioB3=SRadioB2;
			}else{
				SRadioB="隨機森林";
				SRadioB2="卡方檢驗";
			}
        }
	}
	
	
	
	public String getRadioB(){
		return SRadioB;
	}
	
	public String getRadioB2(){
		return SRadioB2;
	}
	
	public String getORIFILE(){
		return ORIfile;
	}
	
	public String getPREFILE(){
		return PREfile;
	}
	
	public void erroedeal(){
		/*radiobtn0.setEnabled(true);
		radiobtn1.setEnabled(true);
		radiobtn2.setEnabled(true);
		radiobtn3.setEnabled(true);
		radiobtn4.setEnabled(true);
		radiobtn0.setSelected(true);
		radiobtn2.setSelected(true);
		jtextfield0.setText("");
		jtextfield1.setText("");
		SRadioB="隨機森林";
		SRadioB2="卡方檢驗";*/
	}
}