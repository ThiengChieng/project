import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;
import java.awt.Font; //文字的import
import java.io.*;//開啟檔案的import
//C:\Windows\Fonts\msjh.ttc
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
import javax.swing.UIManager; 
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.filechooser.FileNameExtensionFilter;


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
------------------------------------------------------------------
Sta_Flag-----判斷前一次之FLAG
Cho_Flag-----判斷此次有無做過選取原始檔之FLAG
Dacu_Flag-----判斷此次有無做過資料切割之FLAG
Watr_Flag-----判斷此次有無做過資料清洗與轉換之FLAG

Fet_Flag-----判斷此次有無做過特徵選取之FLAG
Mod_Flag-----判斷此次有無做過創建模型之FLAG
Eva_Flag-----判斷此次有無做過預測最佳到診時間之FLAG
Com_Flag-----判斷此次有無做過效能比較之FLAG

*/



public class newlayout52{
	/*旗標*/
	private static int Sta_Flag=0;
	private static int Cho_Flag=0;
	private static int Dacu_Flag=0;
	private static int Watr_Flag=0;
	private static int Fet_Flag=0;
	private static int FetSav_Flag=0;
	private static int Mod_Flag=0;
	private static int Eva_Flag=0;
	private static int Com_Flag=0;

	
	
	/*檔案判斷名稱*/
	private static String JUD_FILE[] = {"Cut","Wa_Ch","Feature","Model"};
	
	
	/*介面變數*/
	private static int PP = 6;
	private static int BB = 8;
	private static int CP = 50;
	private static int DP = 50;
	private static String CPArray[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","45","47","48","49","50"};
	private static String UPArray[] = {"51","52","53","54","55","56","57","58","59","60","61","62","63","64","65","66","67","68","69","70","71","72","73","74","75","76","77","78","79","80","81","82","83","84","85","86","87","88","89","90","91","92","93","94","95","96","97","98","99","100"};
	private static String imageArray[] = {"image\\file.png","image\\scissor.png","image\\washchange.png","image\\feature.png","image\\model.png","image\\predict.png","image\\compare.png","image\\super.png"};
	private static String ClickbtArray[] = {"image\\file2.png","image\\scissor2.png","image\\washchange2.png","image\\feature2.png","image\\model2.png","image\\predict2.png","image\\compare2.png","image\\super2.png"};
	private static JLabel logo = new JLabel("");
	private static CardLayout cardlayout1 = new CardLayout();
	private static CardLayout cardlayout2 = new CardLayout();
	private static JPanel panel[]=new JPanel[PP];
	private static JPanel Cpanel[]=new JPanel[CP];
	private static JPanel Upanel[]=new JPanel[CP];
	private static JPanel CDpanel[]=new JPanel[DP];
	private static JPanel Dopanel[]=new JPanel[DP];
	private static JButton button[] = new JButton[BB];
	private static File oporfile1,oporfile2;
	private static JTextArea tarea;
	private static JFrame frame;
	private static String HintArray[] = {"確認完畢請按資料切割","確認檔案無誤後，請按下資料清洗與轉換","確認檔案無誤後，請按下資料清洗與轉換","確認完畢請按特徵選取","確認完畢請按特徵選取","確認完畢請按下一步","確認特徵無誤後，請按儲存特徵檔案","確認檔案無誤後，請按下資料清洗與轉換","確認完畢請按下一步","下一步請按評估最佳到診時間","下一步請按評估最佳到診時間","下一步請按評估最佳到診時間","確認完畢請按選擇模型","確認完畢請按進行評估","本系統所採用方法應用於個案醫院的效能比較","確認完畢請按進行處理","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
	private static String HintArray2[] = {"確認檔案無誤後，請按下資料清洗與轉換","確認檔案無誤後，請按下資料清洗與轉換","確認完畢請按特徵選取","確認檔案無誤後，請按下資料清洗與轉換","確認完畢請按特徵選取","確認檔案無誤後，請按下資料清洗與轉換","確認檔案無誤後，請按下資料清洗與轉換","確認檔案無誤後，請按下資料清洗與轉換","確認檔案無誤後，請按下資料清洗與轉換","確認完畢請按特徵選取","確認檔案無誤後，請按下資料清洗與轉換","確認檔案無誤後，請按下資料清洗與轉換","確認完畢請按特徵選取","確認檔案無誤後，請按下資料清洗與轉換","確認檔案無誤後，請按下資料清洗與轉換","確認完畢請按下一步","確認特徵無誤後，請按儲存特徵檔案","確認檔案無誤後，請按下創建模型。","確認檔案無誤後，請按下創建模型。","確認檔案無誤後，請按下資料清洗與轉換","確認檔案無誤後，請按下上一步","下一步請按評估最佳到診時間","確認完畢請按選擇模型","確認完畢請按進行評估","確認完畢請按進行評估","確認完畢請按效能比較","確認完畢請按效能比較","確認完畢請按進行評估","確認完畢請按進行評估","確認完畢請按效能比較","確認完畢請按效能比較","確認完畢請按上一步","本系統所採用方法應用於個案醫院的效能比較","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
	private static int Hint_num=50;
	private static JLabel Hint_Label[]=new JLabel[Hint_num];
	private static JLabel Hint_Label2[]=new JLabel[Hint_num];
	private static String HiBuArray[] = {"image\\view.png","image\\view.png","image\\view.png","image\\chooseof.png","image\\chooseof.png","image\\view.png","image\\view.png","image\\next.png","image\\view.png","image\\save.png","image\\view.png","image\\view.png","image\\next.png","image\\view.png","image\\choosemo.png","image\\view.png","image\\comparepic.png","image\\allinone.png","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
	private static String D_HiBuArray[] = {"image\\Previous.png","image\\Previous.png","image\\chooseof.png","image\\viewof.png","image\\view.png","image\\view.png","image\\view.png","image\\Previous.png","image\\view.png","image\\Previous.png","image\\chooseof.png","image\\viewof.png","image\\view.png","image\\Previous.png","image\\chooseof.png","image\\view.png","image\\view.png","image\\Previous.png","image\\viewof.png","image\\next.png","image\\view.png","image\\save.png","image\\Previous.png","image\\Previous.png","image\\Previous.png","image\\Previous.png","image\\Previous.png","image\\choosemo.png","image\\view.png","image\\predicting.png","image\\Previous.png","image\\predicting.png","image\\view.png","image\\predicting.png","image\\Previous.png","image\\predicting.png","image\\Previous.png","","","","","","","","","","","","",""};
    private static JButton HiBuArraybutton[] = new JButton[DP];
	private static JButton D_HiBuArraybutton[] = new JButton[DP];
	private static String MesArray[]={"尚未選取原始檔，請先選取原始檔。","尚未選取原始檔，請先選取檔案以進行清洗與轉換。","請先選取原始檔進行切割。","特徵檔案儲存完成，請按下創建模型。","請先進行資料清洗與切割。","請先選取原始檔進行切割。","模型創建完成！","請先進行特徵選取","請先進行資料清洗與轉換","請先選擇原始檔進行切割","請先進行創建模型","請先進行特徵選取","請先進行資料清洗與轉換","請先選取原始檔進行切割。","評估完成","評估完成","評估完成","評估完成","尚未有任何模型結果"};
	private static String ProMesArray[]={"資料切割進行中\n請稍候...","資料清洗與轉換進行中\n請稍候...","正在尋找特徵\n請稍候...","創建模型進行中\n請稍候...","測試集資料正在進行切割、清洗與轉換中\n請稍候...","正在繪製比較圖\n請稍候...","評估程序進行中\n請稍候...","開檔程序進行中請稍候..."};
	private static String ProEndMesArray[]={"資料切割進行完畢","資料清洗與轉換進行完畢","創建模型進行完畢","切割清洗轉換進行完畢","特徵尋找進行完畢","比較圖繪製進行完畢"};
	private static Font softfont[]=new Font[5];
	private static String OriFileName[] = {"看診日期","看診科別","看診診間","病歷號","醫師卡號","跟診護理師卡號","病患性別","病患出生日期","掛號類別","初複診","掛號序號","班別早午診","前次是否開立醫囑","診斷碼1","診斷碼2","診斷碼3","診斷碼4","診斷碼5","診斷碼6","診斷碼7","診斷碼8","診斷碼9","診斷碼10","自助報到插卡時間","醫師插卡時間","醫師批價時間1","醫師批價時間2","醫師批價時間3","醫師批價時間4","醫師批價時間5","醫師批價時間6","醫師批價時間7","醫師批價時間8","醫師批價時間9","預估看診時間"};
	
	/*外部程式宣告*/
	private static readtxt_3 readtxt1,readtxt2,readtxt3,readtxt4,readtxt5,readtxt6,readtxt7,readtxt8,readtxt9,readtxt10,readtxt11,readtxt12,readtxt13,readtxt14,readtxt15,readtxt16,readtxt18,readtxt19,readtxt20,readtxt21,readtxt22,readtxt23; 
	private static layouttest1 FileCH;
	private static layouttest1_1 FileCH2;
	private static String NOWChFile="";
	private static layouttest2_2 listpanel;
	private static FeatureSave featuresave;
	private static FeatureSave2 featuresave2;
	private static layouttest6_6_2 piclayout,piclayout2;
	private static String data_cuwachfe_path;
	private static int opt1;
	private static Cortana cortana;
	private static Cortana2 cortana2;
	private static testtextarea fetxarea;
	private static int isclick=0;
	private static layouttest9 allinone = new layouttest9();
	
	public static void main(String[] args)throws InterruptedException {
		
		
		/*判斷資料夾是否存在(Sta_Flag) START*/
		for(int i=3;i>=0;i--){
			File jud_file =new File("Hos_Data/Doc_Train/"+JUD_FILE[i]); 
			if  (!jud_file .exists()){       
				System.out.println(jud_file.toString()+"不存在"+Sta_Flag); //資料夾不存在
			}else{  
				Sta_Flag=i+1;
				System.out.println(jud_file.toString()+"目錄存在  "+Sta_Flag);//資料夾存在 
				break;				
			} 
		}
		/*判斷資料夾是否存在(Sta_Flag) END*/
		try{
			FileWriter fw = new FileWriter("javatoc");
			
			fw.write("");
			fw.flush();
			fw.close();
		}catch (Exception v) {
			System.out.println("javatoc清除不成功");
					  
		}
		
		
		/*主畫面Layout設定START*/
		JFrame.setDefaultLookAndFeelDecorated(true);//將版面設美觀用
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); // 取得螢幕尺寸
		JFrame frame = new JFrame("");
        frame.setSize(screenSize.width,screenSize.height);//設定視窗顯示大小與螢幕尺寸相同
        frame.setLayout(new BorderLayout());
		
		
		/*將版面JPanel新增出來並設定顏色 START*/
		for(int i=0;i<PP;i++){
			panel[i]=new JPanel();
			panel[i].setBackground(new Color(100+(2*i),100+(2*i),100+(2*i)));
		}
		/*將版面JPanel新增出來並設定顏色 END*/
		
		
		/*左邊按鈕介面START*/
		panel[0].setLayout(new BorderLayout());
		panel[0].add(panel[1],BorderLayout.NORTH);
		panel[0].add(panel[2],BorderLayout.CENTER);
		panel[1].setLayout(new GridLayout(1,1));
		panel[2].setLayout(new GridLayout(BB,1));
		panel[2].setPreferredSize(new Dimension(210,150));
		/*左邊按鈕介面END*/
		
		
		/*中間介面START*/
		
		panel[3].setLayout(new BorderLayout());
		panel[3].add(panel[4],BorderLayout.CENTER);
		panel[3].add(panel[5],BorderLayout.SOUTH);
		panel[4].setLayout(cardlayout1);
		panel[5].setLayout(cardlayout2);
		panel[5].setPreferredSize(new Dimension(200,130));
		
		
		/*將用於CardLayout的JPanel新增出來並設定顏色且將其版面設定成BorderLayout最後加入panel[4](主頁顯示) START*/
		for(int i=0;i<CP;i++){
			Cpanel[i]=new JPanel();
			Upanel[i]=new JPanel();
			Cpanel[i].setBackground(new Color(100,100,100));
			Upanel[i].setBackground(new Color(100,100,100));
			//Cpanel[i].setBackground(new Color(100+(2*i),100+(2*i),100+(2*i)));
			//Upanel[i].setBackground(new Color(100+(2*i),100+(2*i),100+(2*i)));
			Cpanel[i].setLayout(new BorderLayout());
			Upanel[i].setLayout(new BorderLayout());
			panel[4].add(Cpanel[i],CPArray[i]);
			panel[4].add(Upanel[i],UPArray[i]);	
		}
		/*將用於CardLayout的JPanel新增出來並設定顏色且將其版面設定成BorderLayout最後加入panel[4](主頁顯示) END*/
		
		
		/*將用於CardLayout的JPanel新增出來並設定顏色且將其版面設定成BorderLayout最後加入panel[5](提示訊息) START*/
		for(int i=0;i<DP;i++){
			CDpanel[i]=new JPanel();
			Dopanel[i]=new JPanel();
			CDpanel[i].setBackground(new Color(100,100,100));
			Dopanel[i].setBackground(new Color(100,100,100));
			//CDpanel[i].setBackground(new Color(100+(2*i),100+(2*i),100+(2*i)));
			//Dopanel[i].setBackground(new Color(100+(2*i),100+(2*i),100+(2*i)));
			CDpanel[i].setLayout(new FlowLayout(FlowLayout.LEFT, 20, 25));
			Dopanel[i].setLayout(new FlowLayout(FlowLayout.LEFT, 20, 25));
			panel[5].add(CDpanel[i],CPArray[i]);
			panel[5].add(Dopanel[i],UPArray[i]);
			HiBuArraybutton[i] = new JButton();
			D_HiBuArraybutton[i] = new JButton();
			
			ImageIcon hintbuimage=new ImageIcon(HiBuArray[i]);
			HiBuArraybutton[i].setIcon(hintbuimage);
			HiBuArraybutton[i].setBorderPainted(false);//BUTTON邊線設為透明
			HiBuArraybutton[i].setContentAreaFilled(false);//將BUTTON背景設為透明的
			ImageIcon D_hintbuimage=new ImageIcon(D_HiBuArray[i]);
			D_HiBuArraybutton[i].setIcon(D_hintbuimage);
			//D_HiBuArraybutton[i].setBorderPainted(false);//BUTTON邊線設為透明
			D_HiBuArraybutton[i].setContentAreaFilled(false);//將BUTTON背景設為透明的
			
			HiBuArraybutton[i].addActionListener(new buttonhandler2());
			D_HiBuArraybutton[i].addActionListener(new buttonhandler2());
		}
		/*將用於CardLayout的JPanel新增出來並設定顏色且將其版面設定成BorderLayout最後加入panel[5](提示訊息) END*/
		
		/*提示訊息 START*/
		softfont[0]=new Font("微軟正黑體", Font.PLAIN, 50);
		for(int i=0;i<Hint_num;i++){
			Hint_Label[i]=new JLabel(HintArray[i]);
			Hint_Label[i].setFont(softfont[0]);
			Hint_Label2[i]=new JLabel(HintArray2[i]);
			Hint_Label2[i].setFont(softfont[0]);
			Hint_Label[i].setBackground(new Color(100,100,100));
			Hint_Label2[i].setBackground(new Color(100,100,100));
			
			//Hint_Label[i].setBackground(new Color(100+(2*i),100+(2*i),100+(2*i)));
			//Hint_Label2[i].setBackground(new Color(100+(2*i),100+(2*i),100+(2*i)));
						
			Hint_Label[i].setForeground(Color.white);
			Hint_Label2[i].setForeground(Color.white);
			
			
		}
		/*提示訊息 END*/
		
		
		/*中間介面END*/
		
		
		frame.add(panel[0],BorderLayout.WEST);
		frame.add(panel[3],BorderLayout.CENTER);
		/*Panel Layout 設定END*/
		
		
		/*logo加入版面start*/
		ImageIcon logoimage=new ImageIcon("image\\logo.png");//新增圖片
		logo.setHorizontalAlignment(SwingConstants.CENTER);//將JLabel水平對齊設為置中
		logo.setVerticalAlignment(SwingConstants.CENTER);//將JLabel垂直對齊設為置中
		Image img = logoimage.getImage();//取得圖片
		img = img.getScaledInstance(150, 113, Image.SCALE_DEFAULT);//調整圖片大小
		logoimage.setImage(img);
		logo.setIcon(logoimage);//將JLabel加入LOGO
		panel[1].add(logo);//將LOGO加入panel[1]
		/*logo加入版面end*/
		
		
		
		/*左方Button START*/
		for(int i=0;i<BB;i++){//將左方步驟按鈕新增出來並將大小設為寬100,高100，且將圖片放入，接著將Button依序加到panel[2]，最後將Button加到buttonhandler實作
			button[i]=new JButton();
			button[i].setPreferredSize(new Dimension(100,100));
			ImageIcon image=new ImageIcon(imageArray[i]);
			button[i].setIcon(image);
			button[i].setBorderPainted(false);//BUTTON邊線設為透明
			button[i].setContentAreaFilled(false);//將BUTTON背景設為透明的
			panel[2].add(button[i]);
			
			button[i].addActionListener(new buttonhandler());
		}
		/*左方Button END*/
		
	
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//將X設定為關閉所有程式
		frame.setVisible(true);//顯示視窗
    }
	
	
	
	/*implement START*/
	public static class buttonhandler implements ActionListener {
		public void actionPerformed(ActionEvent e){
			//Font.BOLD
			UIManager.put("OptionPane.messageFont",new Font("微軟正黑體", Font.PLAIN, 20));
			UIManager.put("OptionPane.buttonFont",new Font("微軟正黑體", Font.PLAIN, 16));
			/*第一步點選選取原始檔 START*/
			if((JButton)e.getSource()==button[0]){//若按下選取原始檔
				JFileChooser chooser1=new JFileChooser(".");//選擇檔案，並且預設位置為現在位置
				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV file", "csv");
				chooser1.setFileFilter(filter);
				int returnVal = chooser1.showOpenDialog(panel[4]);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					oporfile1 = chooser1.getSelectedFile();//取得使用者所選檔案
					if(oporfile1.toString().contains(".csv")){
						/*跳出等待視窗 START*/
						JOptionPane WaitPanel3 =new JOptionPane(ProMesArray[7], JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
						JDialog Waitdialog3 = new JDialog();
						Waitdialog3.setModal(true);
						Waitdialog3.setContentPane(WaitPanel3);
							
						Waitdialog3.pack();
						Dimension screenSize3 = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
						Dimension GIFdialogSize = Waitdialog3.getSize();
						Waitdialog3.setLocation(screenSize3.width/2-GIFdialogSize.width/2,screenSize3.height/2-GIFdialogSize.height/2);
						Waitdialog3.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
						
						
						
						
						/*跳出等待視窗 END*/
						try {
						  Cho_Flag=1;//將Cho_Flag設為1表示此次做過選取原始檔
						  BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(oporfile1)));
						  new Thread(new Runnable() {
							  @Override
							  public void run() {
								try {
									//process.waitFor();
									CDpanel[0].removeAll();//將先前所在CDpanel[0]的物件進行清空
									CDpanel[0].add(Hint_Label[0]);//將提示訊息"確認完畢請按資料切割"加入到CDpanel[0]
									readtxt1 = new readtxt_3(oporfile1.toString());//將使用者選取檔案路徑轉為字串並呼叫readtxt_3
									System.out.println(oporfile1.toString());//測試，印出檔案路徑字串
									ArrayList<Integer> judFileNameSame=new ArrayList<Integer>();
									judFileNameSame.clear();
									for(int i = 0;i<readtxt1.ColumnCount();i++){
										if(readtxt1.getColumnName(i).equals(OriFileName[i])){
											judFileNameSame.add(0);
										}else{
											judFileNameSame.add(1);
										}
										//System.out.println(judFileNameSame.get(i));
									}
									if(judFileNameSame.contains(1)){
										Waitdialog3.setVisible(false);
										JOptionPane.showMessageDialog(frame,"所選檔案不符合系統所要求內容(門診紀錄)");
										ImageIcon image=new ImageIcon(imageArray[0]);
										button[0].setIcon(image);
										Cpanel[0].removeAll();
										CDpanel[0].removeAll();
										
									}else{
										Cpanel[0].removeAll();//將先前所在Cpanel[0]的物件進行清空
										Cpanel[0].add(readtxt1.CALLTABLE());//將readtxt_3所回傳之JScrollPane加到Cpanel[0]
										Cpanel[0].revalidate();//Cpanel[0]版面重新布局
										Cpanel[0].repaint();//Cpanel[0]版面重新繪製 
										ImageIcon image=new ImageIcon(ClickbtArray[0]);
										button[0].setIcon(image);
										//tarea.read(input, "READING FILE :-)");
																  
										cardlayout1.show(panel[4],CPArray[0]);//將Cpanel[0]顯示(JTabel)
										cardlayout2.show(panel[5],CPArray[0]);//將CDpanel[0]顯示(提示訊息"確認完畢請按資料切割")										
									}
									judFileNameSame.clear();  
								  
								  
								} catch (Exception v) {
									System.out.println(v);
								}
								Waitdialog3.setVisible(false);
							  }
							}).start();
							Waitdialog3.setVisible(true);
						  
						} catch (Exception v) {
						  System.out.println("檔案不存在,請檢查檔案名稱.");
						  
						}	
					}else{
						JOptionPane.showMessageDialog(frame,"所選檔案不符合系統所需格式(.csv)");
					}
					
					
					
					
					
					
					
				} else {
					System.out.println("Operation is CANCELLED :(");
				}
				
				
				System.out.println("STEP1---選取原始檔");//測試，印出"STEP1---選取原始檔"
			/*第一步點選選取原始檔 END*/
				
				
			/*第二步點選資料切割 START*/
			}else if((JButton)e.getSource()==button[1]){//若按下資料切割
				
				if(Cho_Flag==1){//如果此次做過選取原始檔

					/*資料切割呼叫外部程式寫檔*/
					try{
						FileWriter fw = new FileWriter("javatoc");
						data_cuwachfe_path=oporfile1.toString();//s是檔案路徑
						fw.write(data_cuwachfe_path);
						fw.flush();
						fw.close();
						
						//呼叫外部程式
						Runtime runtime=Runtime.getRuntime();             
						Process process = runtime.exec("Data_Cut_End.exe");//file為執行外部程式的檔案名稱             
						System.out.println("切割執行中--------------------");
						
						
						/*跳出等待視窗 START*/
						//OptionPane.font.getDefault()
						
						JOptionPane WaitPanel =new JOptionPane(ProMesArray[0], JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
						//WaitPanel.setFont(softfont[0]);
						JDialog Waitdialog = new JDialog();
						Waitdialog.setModal(true);
						Waitdialog.setContentPane(WaitPanel);
						
						Waitdialog.pack();
						Dimension screenSize2 = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
						Dimension GIFdialogSize = Waitdialog.getSize();
						Waitdialog.setLocation(screenSize2.width/2-GIFdialogSize.width/2,screenSize2.height/2-GIFdialogSize.height/2);
						Waitdialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
						System.out.println("切割執行中--------------------");
						
						new Thread(new Runnable() {
						  @Override
						  public void run() {
							try {
							  process.waitFor();
							  //Thread.sleep(5000);
							} catch (InterruptedException e) {
							  e.printStackTrace();
							}
							Waitdialog.setVisible(false);
						  }
						}).start();
						Waitdialog.setVisible(true);
						/*跳出等待視窗 END*/
						
						JOptionPane.showMessageDialog(frame,ProEndMesArray[0]);
						ImageIcon image=new ImageIcon(ClickbtArray[1]);
						button[1].setIcon(image);
						Dacu_Flag=1;//將Dacu_Flag設為1表示此次做過資料切割
					}catch(Exception v){
						System.out.println("切割執行錯誤");
					}
					
					
					FileCH=new layouttest1(0);//將切割好的年份及檔案名稱顯示(0表示顯示切割的資料夾)
					Cpanel[1].removeAll();//將先前所在Cpanel[1]的物件進行清空
					Cpanel[1].add(FileCH.CALLFILECH());//將回傳切割資料夾內容加入到Cpanel[1]
					
					Cpanel[1].revalidate();//Cpanel[1]版面重新布局
					Cpanel[1].repaint();//Cpanel[1]版面重新繪製
					CDpanel[1].removeAll();//將先前所在CDpanel[1]的物件進行清空
					CDpanel[1].add(Hint_Label[1]);//將提示訊息"確認完畢請按資料切割"加入到CDpanel[1]	
					HiBuArraybutton[0].setPreferredSize(new Dimension(190,88));//將"檢視"按鈕設定大小寬150，高60
					CDpanel[1].add(HiBuArraybutton[0]);//將"檢視"按鈕加入到CDpanel[1]
										
					CDpanel[1].revalidate();//CDpanel[1]版面重新布局
					CDpanel[1].repaint();//CDpanel[1]版面重新繪製
					cardlayout1.show(panel[4],CPArray[1]);//將Cpanel[1]顯示
					cardlayout2.show(panel[5],CPArray[1]);//將CDpanel[1]顯示
					
					System.out.println("已選取");
					System.out.println("STEP2---資料切割");//測試，印出"STEP2---資料切割"
					
				}else if(Cho_Flag==0){//如果此次沒有選取原始檔
					System.out.println("未選取");
					JOptionPane.showMessageDialog(frame,MesArray[0]);//跳出小視窗顯示"尚未選取原始檔，請先選取原始檔"
					
				}else{
					System.out.println("ERROR");
				}				
			/*第二步點選資料切割 END*/
				
				
			/*第三步點選資料清洗與轉換 START*/
			}else if((JButton)e.getSource()==button[2]){//若按下資料清洗與轉換
				
				if(Sta_Flag>=1 || Dacu_Flag==1){//如果先前做過資料切割或是資料清洗與轉換，或是此次有做過資料切割
					
					if(Dacu_Flag==0){//若是此次沒有做過資料切割
						JOptionPane.showMessageDialog(frame,MesArray[1]);//跳出小視窗顯示"尚未選取原始檔，請先選取原始檔以進行清洗與轉換"
						
						FileCH=new layouttest1(0);//將切割好的年份及檔案名稱顯示(0表示顯示切割的資料夾)
						
						Cpanel[2].removeAll();//將先前所在Cpanel[2]的物件進行清空
					
						Cpanel[2].add(FileCH.CALLFILECH());//將回傳切割資料夾內容加入到Cpanel[2]
						Cpanel[2].revalidate();//Cpanel[2]版面重新布局
						Cpanel[2].repaint();//Cpanel[2]版面重新繪製
						
						CDpanel[2].removeAll();//將先前所在CDpanel[2]的物件進行清空
						CDpanel[2].add(Hint_Label[2]);//將提示訊息"確認完畢請按資料切割"加入到CDpanel[2]	
						
						HiBuArraybutton[1].setPreferredSize(new Dimension(190,88));//將"檢視"按鈕設定大小寬150，高60
						CDpanel[2].add(HiBuArraybutton[1]);//將"檢視"按鈕加入到CDpanel[2]
						CDpanel[2].revalidate();//CDpanel[2]版面重新布局
						CDpanel[2].repaint();//CDpanel[2]版面重新繪製
							
						cardlayout1.show(panel[4],CPArray[2]);//將Cpanel[2]顯示
						cardlayout2.show(panel[5],CPArray[2]);//將CDanel[2]顯示
						System.out.println("STEP3---顯示切割檔");//測試，成功執行印出"1"
						Dacu_Flag=1;
						
						
						
					}else{
						if(FileCH.IsClick()==1){
							System.out.println("Time to wash");
							if(Watr_Flag != 1){
								/*資料清洗與轉換呼叫外部程式寫檔*/
								try{
									
									FileWriter fw = new FileWriter("javatoc");
									if(FileCH.CALLFILPATH().contains("Wa_Ch")){
										data_cuwachfe_path=FileCH.CALLFILPATH().replace("Wa_Ch", "Cut");
									}else{
										data_cuwachfe_path=FileCH.CALLFILPATH();//s是檔案路徑
									}
									System.out.println("測試檔名路徑為"+data_cuwachfe_path);
									fw.write(data_cuwachfe_path);
									fw.flush();
									fw.close();
										
									//呼叫外部程式
									Runtime runtime=Runtime.getRuntime();             
									Process process = runtime.exec("Data_TransForm_Final.exe");//file為執行外部程式的檔案名稱             
									System.out.println("清洗與轉換執行中--------------------");
									/*跳出等待視窗 START*/
									JOptionPane WaitPanel2 =new JOptionPane(ProMesArray[1], JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
									JDialog Waitdialog2 = new JDialog();
									Waitdialog2.setModal(true);
									Waitdialog2.setContentPane(WaitPanel2);
									
									Waitdialog2.pack();
									Dimension screenSize2 = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
									Dimension GIFdialogSize = Waitdialog2.getSize();
									Waitdialog2.setLocation(screenSize2.width/2-GIFdialogSize.width/2,screenSize2.height/2-GIFdialogSize.height/2);
									Waitdialog2.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
									System.out.println("切割執行中--------------------");
									
									new Thread(new Runnable() {
									  @Override
									  public void run() {
										try {
										  process.waitFor();
										  Thread.sleep(1000);
										} catch (InterruptedException e) {
										  e.printStackTrace();
										}
										Waitdialog2.setVisible(false);
									  }
									}).start();
									Waitdialog2.setVisible(true);
									/*跳出等待視窗 END*/
									ImageIcon image=new ImageIcon(ClickbtArray[2]);
									button[2].setIcon(image);
									Watr_Flag=1;
								}catch(Exception v){
									System.out.println("清洗與轉換錯誤");
								}
								JOptionPane.showMessageDialog(frame,ProEndMesArray[1]);
							}
							FileCH=new layouttest1(1);//將切割好的年份及檔案名稱顯示(0表示顯示切割的資料夾)
							
							Cpanel[3].removeAll();//將先前所在Cpanel[3]的物件進行清空
							Cpanel[3].add(FileCH.CALLFILECH());//將回傳切割資料夾內容加入到Cpanel[3]
								
							
							Cpanel[3].revalidate();//Cpanel[3]版面重新布局
							Cpanel[3].repaint();//Cpanel[3]版面重新繪製
								
							CDpanel[3].removeAll();//將先前所在CDpanel[3]的物件進行清空
							CDpanel[3].add(Hint_Label[3]);//將提示訊息"確認完畢請按資料切割"加入到CDpanel[3]	
							HiBuArraybutton[3].setPreferredSize(new Dimension(200,100));//將"選擇清洗其他檔案"按鈕設定大小寬150，高60
							HiBuArraybutton[2].setPreferredSize(new Dimension(190,88));//將"檢視"按鈕設定大小寬150，高60
							CDpanel[3].add(HiBuArraybutton[3]);//將"選擇清洗其他檔案"按鈕加入到CDpanel[3]
							CDpanel[3].add(HiBuArraybutton[2]);//將"檢視"按鈕加入到CDpanel[3]					
							CDpanel[3].revalidate();//CDpanel[3]版面重新布局
							CDpanel[3].repaint();//CDpanel[3]版面重新繪製
									
							cardlayout1.show(panel[4],CPArray[3]);//將Cpanel[3]顯示
							cardlayout2.show(panel[5],CPArray[3]);//將CDpanel[3]顯示
							System.out.println("STEP4---顯示清洗與轉換檔");//測試，成功執行印出"1"
							Dacu_Flag=0;
						}else{
							JOptionPane.showMessageDialog(frame,"尚未選取檔案");
						}
					
	/*-----------------------------------------------------------------------------------------------------------------------------*/
					
					}
				}else{
					JOptionPane.showMessageDialog(frame,MesArray[2]);//跳出小視窗顯示"請先選取原始檔進行切割"
					Cpanel[4].removeAll();//將先前所在Cpanel[3]的物件進行清空
					Cpanel[4].revalidate();//Cpanel[3]版面重新布局
					Cpanel[4].repaint();//Cpanel[3]版面重新繪製
					CDpanel[4].removeAll();//將先前所在Cpanel[3]的物件進行清空
					//CDpanel[4].setBackground(new Color (108,108,108));
					CDpanel[4].revalidate();//Cpanel[3]版面重新布局
					CDpanel[4].repaint();//Cpanel[3]版面重新繪製
					cardlayout1.show(panel[4],CPArray[4]);//將Cpanel[3]顯示
					cardlayout2.show(panel[5],CPArray[4]);//將CDpanel[3]顯示
					System.out.println("STEP5---顯示選取原始檔");//測試，成功執行印出"1"
				}
				/*第三步點選資料清洗與轉換 END*/
				
				/*第四步點選特徵選取 START*/
			}else if((JButton)e.getSource()==button[3]){
				
				String ChFile="";
				String ChFile2="";
				Object[] options = {"卡方檢驗","方差過濾","改選其他檔案"};
				
				
				if(Sta_Flag>=2 || Watr_Flag==1){
					
					if(Watr_Flag==0){
						
						FileCH=new layouttest1(1);//將切割好的年份及檔案名稱顯示(0表示顯示切割的資料夾)
						
						Cpanel[5].removeAll();//將先前所在Cpanel[3]的物件進行清空
						Cpanel[5].add(FileCH.CALLFILECH());//將回傳切割資料夾內容加入到Cpanel[3]
						Cpanel[5].revalidate();//Cpanel[3]版面重新布局
						Cpanel[5].repaint();//Cpanel[3]版面重新繪製
						
						CDpanel[5].removeAll();//將先前所在CDpanel[3]的物件進行清空
						CDpanel[5].add(Hint_Label[4]);//將提示訊息"確認完畢請按資料切割"加入到CDpanel[3]	
						HiBuArraybutton[4].setPreferredSize(new Dimension(200,100));//將"選擇清洗其他檔案"按鈕設定大小寬150，高60
						HiBuArraybutton[5].setPreferredSize(new Dimension(190,88));//將"檢視"按鈕設定大小寬150，高60
						CDpanel[5].add(HiBuArraybutton[4]);//將"選擇清洗其他檔案"按鈕加入到CDpanel[3]
						CDpanel[5].add(HiBuArraybutton[5]);//將"檢視"按鈕加入到CDpanel[3]					
						CDpanel[5].revalidate();//CDpanel[3]版面重新布局
						CDpanel[5].repaint();//CDpanel[3]版面重新繪製
						
						
						cardlayout1.show(panel[4],CPArray[5]);//將Cpanel[3]顯示
						cardlayout2.show(panel[5],CPArray[5]);//將CDpanel[3]顯示
						System.out.println("STEP6---顯示已清洗與轉換檔");//測試，成功執行印出"1"
						Watr_Flag=1;
						
					}else{
						if(FileCH.IsClick()==1){
							if(Fet_Flag==1){
								FileCH=new layouttest1(1);//將切割好的年份及檔案名稱顯示(0表示顯示切割的資料夾)
							
								Cpanel[5].removeAll();//將先前所在Cpanel[3]的物件進行清空
								Cpanel[5].add(FileCH.CALLFILECH());//將回傳切割資料夾內容加入到Cpanel[3]
								Cpanel[5].revalidate();//Cpanel[3]版面重新布局
								Cpanel[5].repaint();//Cpanel[3]版面重新繪製
								
								CDpanel[5].removeAll();//將先前所在CDpanel[3]的物件進行清空
								CDpanel[5].add(Hint_Label[4]);//將提示訊息"確認完畢請按資料切割"加入到CDpanel[3]	
								HiBuArraybutton[4].setPreferredSize(new Dimension(200,100));//將"選擇清洗其他檔案"按鈕設定大小寬150，高60
								HiBuArraybutton[5].setPreferredSize(new Dimension(190,88));//將"檢視"按鈕設定大小寬150，高60
								CDpanel[5].add(HiBuArraybutton[4]);//將"選擇清洗其他檔案"按鈕加入到CDpanel[3]
								CDpanel[5].add(HiBuArraybutton[5]);//將"檢視"按鈕加入到CDpanel[3]					
								CDpanel[5].revalidate();//CDpanel[3]版面重新布局
								CDpanel[5].repaint();//CDpanel[3]版面重新繪製
								
								
								cardlayout1.show(panel[4],CPArray[5]);//將Cpanel[3]顯示
								cardlayout2.show(panel[5],CPArray[5]);//將CDpanel[3]顯示
								System.out.println("STEP6---顯示已清洗與轉換檔xxx");//測試，成功執行印出"1"
								Watr_Flag=1;
								Fet_Flag=0;
							}else{
								ChFile=FileCH.CALLOnlyName();
								ChFile2=FileCH.CALLFILPATH();
								System.out.println(ChFile);//測試，成功執行印出"1"
								System.out.println(ChFile2);
								
								opt1=JOptionPane.showOptionDialog(panel[3], "目前選擇的檔案為"+ChFile+"\n請選擇特徵選取方法","請選取",JOptionPane.YES_OPTION,JOptionPane.PLAIN_MESSAGE,null, options, options[2] ) ;
								if(opt1==2){
									NOWChFile="";
									FileCH=new layouttest1(1);//將切割好的年份及檔案名稱顯示(0表示顯示切割的資料夾)
									
									Cpanel[6].removeAll();//將先前所在Cpanel[3]的物件進行清空
									Cpanel[6].add(FileCH.CALLFILECH());//將回傳切割資料夾內容加入到Cpanel[3]
									Cpanel[6].revalidate();//Cpanel[3]版面重新布局
									Cpanel[6].repaint();//Cpanel[3]版面重新繪製
										
									CDpanel[6].removeAll();//將先前所在CDpanel[3]的物件進行清空
									CDpanel[6].add(Hint_Label[5]);//將提示訊息"確認完畢請按資料切割"加入到CDpanel[3]	
									HiBuArraybutton[6].setPreferredSize(new Dimension(190,88));//將"選擇清洗其他檔案"按鈕設定大小寬150，高60
									HiBuArraybutton[7].setPreferredSize(new Dimension(190,88));//將"檢視"按鈕設定大小寬150，高60
									CDpanel[6].add(HiBuArraybutton[6]);//將"選擇清洗其他檔案"按鈕加入到CDpanel[3]
									CDpanel[6].add(HiBuArraybutton[7]);//將"檢視"按鈕加入到CDpanel[3]					
									CDpanel[6].revalidate();//CDpanel[3]版面重新布局
									CDpanel[6].repaint();//CDpanel[3]版面重新繪製
										
										
									cardlayout1.show(panel[4],CPArray[6]);//將Cpanel[3]顯示
									cardlayout2.show(panel[5],CPArray[6]);//將CDpanel[3]顯示
									System.out.println("STEP6---顯示已清洗與轉換檔");//測試，成功執行印出"1"
								}else if(opt1==0||opt1==1){
									
									try{
										FileWriter fw = new FileWriter("javatoc");
										data_cuwachfe_path=FileCH.CALLFILPATH();//s是檔案路徑
										fw.write(data_cuwachfe_path);
										fw.flush();
										fw.close();
										System.out.println("特徵選取複寫檔中--------------------");
										/*跳出等待視窗 START*/
											JOptionPane WaitPanel3 =new JOptionPane(ProMesArray[2], JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
											JDialog Waitdialog3 = new JDialog();
											Waitdialog3.setModal(true);
											Waitdialog3.setContentPane(WaitPanel3);
											
											Waitdialog3.pack();
											Dimension screenSize2 = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
											Dimension GIFdialogSize = Waitdialog3.getSize();
											Waitdialog3.setLocation(screenSize2.width/2-GIFdialogSize.width/2,screenSize2.height/2-GIFdialogSize.height/2);
											Waitdialog3.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
											System.out.println("特徵執行中--------------------");
											
											new Thread(new Runnable() {
											  @Override
											  public void run() {
												try {
												 // process.waitFor();
													cortana = new Cortana(FileCH.CALLFILPATH());
													if(opt1==0){
														listpanel=new layouttest2_2(FileCH.CALLFILPATH().replace("Wa_Ch", "Feature").replace(".csv", "_CHi2.txt"),0,cortana.getPath());	
														
													}else if(opt1==1){
														listpanel=new layouttest2_2(FileCH.CALLFILPATH().replace("Wa_Ch", "Feature").replace(".csv", "_FT.txt"),1,cortana.getPath());	
													}
									
												} catch (Exception e) {
												 System.out.println(e);
												}
												Waitdialog3.setVisible(false);
											  }
											}).start();
											Waitdialog3.setVisible(true);
											JOptionPane.showMessageDialog(frame,ProEndMesArray[4]);
											/*跳出等待視窗 END*/
											Cpanel[7].removeAll();//將先前所在Upanel[0]的物件進行清空
											Cpanel[7].setBackground(new Color(100,100,100));
											Cpanel[7].add(listpanel.CALLLISTPANEL());//將readtxt_3所回傳之JScrollPane加到Upanel[0]
											Cpanel[7].add(Box.createRigidArea(new Dimension(15, 15)), BorderLayout.WEST);
											Cpanel[7].revalidate();//Upanel[0]版面重新布局
											Cpanel[7].repaint();//Upanel[0]版面重新繪製
											CDpanel[7].removeAll();//將先前所在Dopanel[0]的物件進行清空
											CDpanel[7].add(Hint_Label[6]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
											HiBuArraybutton[8].setPreferredSize(new Dimension(190,88));//將"上一步"按鈕設定大小寬150，高60
											HiBuArraybutton[8].setEnabled(false);
											HiBuArraybutton[9].setPreferredSize(new Dimension(190,88));
											CDpanel[7].add(HiBuArraybutton[8]);//將"上一步"按鈕加入到Dopanel[0]
											CDpanel[7].add(HiBuArraybutton[9]);
											CDpanel[7].revalidate();//Dopanel[0]版面重新布局
											CDpanel[7].repaint();//Dopanel[0]版面重新繪製
											cardlayout1.show(panel[4],CPArray[7]);//將Upanel[0]顯示
											cardlayout2.show(panel[5],CPArray[7]);//將Dopanel[0]顯示
											FetSav_Flag=1;
									}catch(Exception v){
										System.out.println("特徵選取錯誤");
									}	
								}
							}
						}else{
							JOptionPane.showMessageDialog(frame,"尚未選取檔案");
						}
						
					
					}
				
				
				}else if(Sta_Flag==1 || Dacu_Flag==1){
					JOptionPane.showMessageDialog(frame,MesArray[4]);//跳出小視窗顯示"請先進行資料清洗與切割"
					FileCH=new layouttest1(0);//將切割好的年份及檔案名稱顯示(0表示顯示切割的資料夾)
						
					Cpanel[8].removeAll();//將先前所在Cpanel[3]的物件進行清空
					Cpanel[8].add(FileCH.CALLFILECH());//將回傳切割資料夾內容加入到Cpanel[3]
					Cpanel[8].revalidate();//Cpanel[3]版面重新布局
					Cpanel[8].repaint();//Cpanel[3]版面重新繪製
						
					CDpanel[8].removeAll();//將先前所在CDpanel[3]的物件進行清空
					CDpanel[8].add(Hint_Label[7]);//將提示訊息"確認完畢請按資料切割"加入到CDpanel[3]	
					HiBuArraybutton[10].setPreferredSize(new Dimension(190,88));//將"選擇清洗其他檔案"按鈕設定大小寬150，高60
					CDpanel[8].add(HiBuArraybutton[10]);//將"選擇清洗其他檔案"按鈕加入到CDpanel[3]
					CDpanel[8].revalidate();//CDpanel[3]版面重新布局
					CDpanel[8].repaint();//CDpanel[3]版面重新繪製
						
						
					cardlayout1.show(panel[4],CPArray[8]);//將Cpanel[3]顯示
					cardlayout2.show(panel[5],CPArray[8]);//將CDpanel[3]顯示
					
					
				}else{
					JOptionPane.showMessageDialog(frame,MesArray[5]);
					Cpanel[9].removeAll();//將先前所在Cpanel[3]的物件進行清空
					Cpanel[9].revalidate();//Cpanel[3]版面重新布局
					Cpanel[9].repaint();//Cpanel[3]版面重新繪製
					Cpanel[9].setBackground(new Color(100+(10*4),100+(10*4),100+(10*4)));
					CDpanel[9].removeAll();//將先前所在Cpanel[3]的物件進行清空
					CDpanel[9].setBackground(new Color(100+(10*4),100+(10*4),100+(10*4)));
					CDpanel[9].revalidate();//Cpanel[3]版面重新布局
					CDpanel[9].repaint();//Cpanel[3]版面重新繪製
					cardlayout1.show(panel[4],CPArray[9]);//將Cpanel[3]顯示
					cardlayout2.show(panel[5],CPArray[9]);//將CDpanel[3]顯示
				}
				
				/*第四步點選特徵選取 END*/
				
				/*第五步點選創建模型 START*/
			}else if((JButton)e.getSource()==button[4]){
				if(FetSav_Flag==1){
					Object[] options2 = {"是","否"};
						
					int mType2=JOptionPane.QUESTION_MESSAGE;
					int oType2=JOptionPane.YES_NO_CANCEL_OPTION;
					int opt3=JOptionPane.showOptionDialog(panel[3], "尚未儲存特徵，是否離開本頁面？","請選取",oType2,mType2,null,options2,options2[0]);
					if(opt3==0){
						if(Sta_Flag >= 3 || Fet_Flag == 1){
							if(Fet_Flag == 0){
								FileCH=new layouttest1(2);
								
								Cpanel[10].removeAll();
								Cpanel[10].add(FileCH.CALLFILECH());
								Cpanel[10].revalidate();
								Cpanel[10].repaint();
								
								CDpanel[10].removeAll();
								CDpanel[10].add(Hint_Label[8]);	
								HiBuArraybutton[11].setPreferredSize(new Dimension(190,88));
								HiBuArraybutton[12].setPreferredSize(new Dimension(190,88));
								CDpanel[10].add(HiBuArraybutton[11]);
								CDpanel[10].add(HiBuArraybutton[12]);			
								CDpanel[10].revalidate();
								CDpanel[10].repaint();
								
								
								cardlayout1.show(panel[4],CPArray[10]);
								cardlayout2.show(panel[5],CPArray[10]);
								System.out.println("---顯示特徵選取檔案");
								
							}else{
								if(Mod_Flag == 1){
									FileCH=new layouttest1(2);
								
									Cpanel[10].removeAll();
									Cpanel[10].add(FileCH.CALLFILECH());
									Cpanel[10].revalidate();
									Cpanel[10].repaint();
									
									CDpanel[10].removeAll();
									CDpanel[10].add(Hint_Label[8]);	
									HiBuArraybutton[11].setPreferredSize(new Dimension(190,88));
									HiBuArraybutton[12].setPreferredSize(new Dimension(190,88));
									CDpanel[10].add(HiBuArraybutton[11]);
									CDpanel[10].add(HiBuArraybutton[12]);			
									CDpanel[10].revalidate();
									CDpanel[10].repaint();
									
									
									cardlayout1.show(panel[4],CPArray[10]);
									cardlayout2.show(panel[5],CPArray[10]);
									System.out.println("---顯示特徵選取檔案");
									Mod_Flag = 0;
								}else{
									//if()
									//Object[] options = {"隨機森林","倒傳遞神經網路","子群組探勘統計方法分析"};
									Object[] options = {"隨機森林","子群組探勘統計方法分析"};
								
									int mType=JOptionPane.QUESTION_MESSAGE;
									int oType=JOptionPane.YES_NO_CANCEL_OPTION;
									int opt2=JOptionPane.showOptionDialog(panel[3], "請選擇要創建的模型","請選取",oType,mType,null,options,options[0]);
									
									if(opt2==0){
										try{
											FileWriter fw = new FileWriter("javatoc");
											data_cuwachfe_path=featuresave.getOutPath();//s是檔案路徑
											fw.write(data_cuwachfe_path);
											fw.flush();
											fw.close();
											
											//呼叫外部程式
											
											/*跳出等待視窗 START*/
											JOptionPane WaitPanel3 =new JOptionPane(ProMesArray[3], JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
											JDialog Waitdialog3 = new JDialog();
											Waitdialog3.setModal(true);
											Waitdialog3.setContentPane(WaitPanel3);
												
											Waitdialog3.pack();
											Dimension screenSize3 = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
											Dimension GIFdialogSize = Waitdialog3.getSize();
											Waitdialog3.setLocation(screenSize3.width/2-GIFdialogSize.width/2,screenSize3.height/2-GIFdialogSize.height/2);
											Waitdialog3.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
											System.out.println("建模執行中--------------------");
											
											new Thread(new Runnable() {
											  @Override
											  public void run() {
												try {
												  //process.waitFor();
												  RF_Cexe Rf_C_exe= new RF_Cexe();
												  Thread.sleep(500);
												} catch (InterruptedException e) {
												  e.printStackTrace();
												}
												Waitdialog3.setVisible(false);
											  }
											}).start();
											Waitdialog3.setVisible(true);
											/*跳出等待視窗 END*/
											
											BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File("ctojava"))));
											String line = null;
											line = bf.readLine();
											fetxarea = new testtextarea (line);
											JOptionPane.showMessageDialog(frame,ProEndMesArray[2]);
											ImageIcon image=new ImageIcon(ClickbtArray[4]);
											button[4].setIcon(image);
										}catch(Exception v){
											System.out.println(v);
										}
										
										
										ImageIcon image=new ImageIcon(ClickbtArray[4]);
										button[4].setIcon(image);
										Mod_Flag=1;	
										Cpanel[11].removeAll();//將先前所在Upanel[0]的物件進行清空
										
										Cpanel[11].add(Box.createRigidArea(new Dimension(15, 15)), BorderLayout.NORTH);
										Cpanel[11].add(Box.createRigidArea(new Dimension(15, 15)), BorderLayout.EAST);
										Cpanel[11].add(Box.createRigidArea(new Dimension(15, 15)), BorderLayout.SOUTH);
										Cpanel[11].add(Box.createRigidArea(new Dimension(15, 15)), BorderLayout.WEST);
										Cpanel[11].add(fetxarea.CALLTEXTAREA(), BorderLayout.CENTER);
										Cpanel[11].revalidate();//Upanel[0]版面重新布局
										Cpanel[11].repaint();//Upanel[0]版面重新繪製
											
										CDpanel[11].removeAll();//將先前所在Dopanel[0]的物件進行清空
										CDpanel[11].add(Hint_Label[9]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
										CDpanel[11].revalidate();//Dopanel[0]版面重新布局
										CDpanel[11].repaint();//Dopanel[0]版面重新繪製
											
											
										cardlayout1.show(panel[4],CPArray[11]);
										cardlayout2.show(panel[5],CPArray[11]);
											
										
												
										
									}else if(opt2==1){
										System.out.println("給冠宏建模的路徑--------------------"+FileCH.CALLFILPATH().replace("Feature","Wa_Ch"));
										
										cortana2 = new Cortana2(FileCH.CALLFILPATH().replace("Feature","Wa_Ch"));
										try{
											FileWriter fw = new FileWriter("javatoc");
											data_cuwachfe_path=featuresave.getOutPath();//s是檔案路徑
											fw.write(data_cuwachfe_path);
											fw.flush();
											fw.close();
											
											//呼叫外部程式
											
											/*跳出等待視窗 START*/
											JOptionPane WaitPanel3 =new JOptionPane(ProMesArray[3], JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
											JDialog Waitdialog3 = new JDialog();
											Waitdialog3.setModal(true);
											Waitdialog3.setContentPane(WaitPanel3);
												
											Waitdialog3.pack();
											Dimension screenSize3 = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
											Dimension GIFdialogSize = Waitdialog3.getSize();
											Waitdialog3.setLocation(screenSize3.width/2-GIFdialogSize.width/2,screenSize3.height/2-GIFdialogSize.height/2);
											Waitdialog3.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
											System.out.println("建模2執行中--------------------");
											
											
											new Thread(new Runnable() {
											  @Override
											  public void run() {
												try {
												  //process.waitFor();
												  
												  Thread.sleep(500);
												} catch (InterruptedException e) {
												  e.printStackTrace();
												}
												Waitdialog3.setVisible(false);
											  }
											}).start();
											Waitdialog3.setVisible(true);
											/*跳出等待視窗 END*/
											fetxarea = new testtextarea (cortana2.getPath());
											JOptionPane.showMessageDialog(frame,ProEndMesArray[2]);
											ImageIcon image=new ImageIcon(ClickbtArray[4]);
											button[4].setIcon(image);
											Mod_Flag=1;
											Cpanel[13].removeAll();//將先前所在Upanel[0]的物件進行清空
											
											Cpanel[13].add(Box.createRigidArea(new Dimension(15, 15)), BorderLayout.NORTH);
											Cpanel[13].add(Box.createRigidArea(new Dimension(15, 15)), BorderLayout.EAST);
											Cpanel[13].add(Box.createRigidArea(new Dimension(15, 15)), BorderLayout.SOUTH);
											Cpanel[13].add(Box.createRigidArea(new Dimension(15, 15)), BorderLayout.WEST);
											Cpanel[13].add(fetxarea.CALLTEXTAREA(), BorderLayout.CENTER);
											Cpanel[13].revalidate();//Upanel[0]版面重新布局
											Cpanel[13].repaint();//Upanel[0]版面重新繪製
											
											CDpanel[13].removeAll();//將先前所在Dopanel[0]的物件進行清空
											CDpanel[13].add(Hint_Label[11]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
											CDpanel[13].revalidate();//Dopanel[0]版面重新布局
											CDpanel[13].repaint();//Dopanel[0]版面重新繪製
											
											
											cardlayout1.show(panel[4],CPArray[13]);
											cardlayout2.show(panel[5],CPArray[13]);
											
											System.out.println(FileCH.CALLFILPATH());
											
										}catch(Exception v){
											System.out.println(v);
										}
												
									}
								}
							}
							
							
						}else if(Sta_Flag == 2 || Watr_Flag == 1){
							Cpanel[14].removeAll();
							Cpanel[14].revalidate();
							Cpanel[14].repaint();
							CDpanel[14].removeAll();
							CDpanel[14].revalidate();
							CDpanel[14].repaint();
							cardlayout1.show(panel[4],CPArray[14]);
							cardlayout2.show(panel[5],CPArray[14]);
							JOptionPane.showMessageDialog(frame,MesArray[7]);
						}else if(Sta_Flag == 1 || Dacu_Flag == 1){
							Cpanel[15].removeAll();
							Cpanel[15].revalidate();
							Cpanel[15].repaint();
							CDpanel[15].removeAll();
							CDpanel[15].revalidate();
							CDpanel[15].repaint();
							cardlayout1.show(panel[4],CPArray[15]);
							cardlayout2.show(panel[5],CPArray[15]);
							JOptionPane.showMessageDialog(frame,MesArray[8]);
						}else{
							Cpanel[16].removeAll();
							Cpanel[16].revalidate();
							Cpanel[16].repaint();
							CDpanel[16].removeAll();
							CDpanel[16].revalidate();
							CDpanel[16].repaint();
							cardlayout1.show(panel[4],CPArray[16]);
							cardlayout2.show(panel[5],CPArray[16]);
							JOptionPane.showMessageDialog(frame,MesArray[9]);
						}
					}
					//JOptionPane.showMessageDialog(frame,"特徵尚未被儲存");
				}else{
					if(Sta_Flag >= 3 || Fet_Flag == 1){
							if(Fet_Flag == 0){
								FileCH=new layouttest1(2);
								
								Cpanel[10].removeAll();
								Cpanel[10].add(FileCH.CALLFILECH());
								Cpanel[10].revalidate();
								Cpanel[10].repaint();
								
								CDpanel[10].removeAll();
								CDpanel[10].add(Hint_Label[8]);	
								HiBuArraybutton[11].setPreferredSize(new Dimension(190,88));
								HiBuArraybutton[12].setPreferredSize(new Dimension(190,88));
								CDpanel[10].add(HiBuArraybutton[11]);
								CDpanel[10].add(HiBuArraybutton[12]);			
								CDpanel[10].revalidate();
								CDpanel[10].repaint();
								
								
								cardlayout1.show(panel[4],CPArray[10]);
								cardlayout2.show(panel[5],CPArray[10]);
								System.out.println("---顯示特徵選取檔案");
								
							}else{
								if(Mod_Flag == 1){
									FileCH=new layouttest1(2);
								
									Cpanel[10].removeAll();
									Cpanel[10].add(FileCH.CALLFILECH());
									Cpanel[10].revalidate();
									Cpanel[10].repaint();
									
									CDpanel[10].removeAll();
									CDpanel[10].add(Hint_Label[8]);	
									HiBuArraybutton[11].setPreferredSize(new Dimension(190,88));
									HiBuArraybutton[12].setPreferredSize(new Dimension(190,88));
									CDpanel[10].add(HiBuArraybutton[11]);
									CDpanel[10].add(HiBuArraybutton[12]);			
									CDpanel[10].revalidate();
									CDpanel[10].repaint();
									
									
									cardlayout1.show(panel[4],CPArray[10]);
									cardlayout2.show(panel[5],CPArray[10]);
									System.out.println("---顯示特徵選取檔案");
									Mod_Flag = 0;
								}else{
									//if()
									//Object[] options = {"隨機森林","倒傳遞神經網路","子群組探勘統計方法分析"};
									Object[] options = {"隨機森林","子群組探勘統計方法分析"};
								
									int mType=JOptionPane.QUESTION_MESSAGE;
									int oType=JOptionPane.YES_NO_CANCEL_OPTION;
									int opt2=JOptionPane.showOptionDialog(panel[3], "請選擇要創建的模型","請選取",oType,mType,null,options,options[0]);
									
									if(opt2==0){
										try{
											FileWriter fw = new FileWriter("javatoc");
											data_cuwachfe_path=featuresave.getOutPath();//s是檔案路徑
											fw.write(data_cuwachfe_path);
											fw.flush();
											fw.close();
											
											//呼叫外部程式
											
											/*跳出等待視窗 START*/
											JOptionPane WaitPanel3 =new JOptionPane(ProMesArray[3], JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
											JDialog Waitdialog3 = new JDialog();
											Waitdialog3.setModal(true);
											Waitdialog3.setContentPane(WaitPanel3);
												
											Waitdialog3.pack();
											Dimension screenSize3 = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
											Dimension GIFdialogSize = Waitdialog3.getSize();
											Waitdialog3.setLocation(screenSize3.width/2-GIFdialogSize.width/2,screenSize3.height/2-GIFdialogSize.height/2);
											Waitdialog3.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
											System.out.println("建模執行中--------------------");
											
											new Thread(new Runnable() {
											  @Override
											  public void run() {
												try {
												  //process.waitFor();
												  RF_Cexe Rf_C_exe= new RF_Cexe();
												  Thread.sleep(500);
												} catch (InterruptedException e) {
												  e.printStackTrace();
												}
												Waitdialog3.setVisible(false);
											  }
											}).start();
											Waitdialog3.setVisible(true);
											/*跳出等待視窗 END*/
											
											BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File("ctojava"))));
											String line = null;
											line = bf.readLine();
											fetxarea = new testtextarea (line);
											JOptionPane.showMessageDialog(frame,ProEndMesArray[2]);
											ImageIcon image=new ImageIcon(ClickbtArray[4]);
											button[4].setIcon(image);
										}catch(Exception v){
											System.out.println(v);
										}
										
										
										ImageIcon image=new ImageIcon(ClickbtArray[4]);
										button[4].setIcon(image);
										Mod_Flag=1;	
										Cpanel[11].removeAll();//將先前所在Upanel[0]的物件進行清空
										
										Cpanel[11].add(Box.createRigidArea(new Dimension(15, 15)), BorderLayout.NORTH);
										Cpanel[11].add(Box.createRigidArea(new Dimension(15, 15)), BorderLayout.EAST);
										Cpanel[11].add(Box.createRigidArea(new Dimension(15, 15)), BorderLayout.SOUTH);
										Cpanel[11].add(Box.createRigidArea(new Dimension(15, 15)), BorderLayout.WEST);
										Cpanel[11].add(fetxarea.CALLTEXTAREA(), BorderLayout.CENTER);
										Cpanel[11].revalidate();//Upanel[0]版面重新布局
										Cpanel[11].repaint();//Upanel[0]版面重新繪製
											
										CDpanel[11].removeAll();//將先前所在Dopanel[0]的物件進行清空
										CDpanel[11].add(Hint_Label[9]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
										CDpanel[11].revalidate();//Dopanel[0]版面重新布局
										CDpanel[11].repaint();//Dopanel[0]版面重新繪製
											
											
										cardlayout1.show(panel[4],CPArray[11]);
										cardlayout2.show(panel[5],CPArray[11]);
											
										
												
										
									}else if(opt2==1){
										System.out.println("給冠宏建模的路徑--------------------"+FileCH.CALLFILPATH().replace("Feature","Wa_Ch"));
										
										cortana2 = new Cortana2(FileCH.CALLFILPATH().replace("Feature","Wa_Ch"));
										try{
											FileWriter fw = new FileWriter("javatoc");
											data_cuwachfe_path=featuresave.getOutPath();//s是檔案路徑
											fw.write(data_cuwachfe_path);
											fw.flush();
											fw.close();
											
											//呼叫外部程式
											
											/*跳出等待視窗 START*/
											JOptionPane WaitPanel3 =new JOptionPane(ProMesArray[3], JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
											JDialog Waitdialog3 = new JDialog();
											Waitdialog3.setModal(true);
											Waitdialog3.setContentPane(WaitPanel3);
												
											Waitdialog3.pack();
											Dimension screenSize3 = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
											Dimension GIFdialogSize = Waitdialog3.getSize();
											Waitdialog3.setLocation(screenSize3.width/2-GIFdialogSize.width/2,screenSize3.height/2-GIFdialogSize.height/2);
											Waitdialog3.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
											System.out.println("建模2執行中--------------------");
											
											
											new Thread(new Runnable() {
											  @Override
											  public void run() {
												try {
												  //process.waitFor();
												  
												  Thread.sleep(500);
												} catch (InterruptedException e) {
												  e.printStackTrace();
												}
												Waitdialog3.setVisible(false);
											  }
											}).start();
											Waitdialog3.setVisible(true);
											/*跳出等待視窗 END*/
											fetxarea = new testtextarea (cortana2.getPath());
											JOptionPane.showMessageDialog(frame,ProEndMesArray[2]);
											ImageIcon image=new ImageIcon(ClickbtArray[4]);
											button[4].setIcon(image);
											Mod_Flag=1;
											Cpanel[13].removeAll();//將先前所在Upanel[0]的物件進行清空
											
											Cpanel[13].add(Box.createRigidArea(new Dimension(15, 15)), BorderLayout.NORTH);
											Cpanel[13].add(Box.createRigidArea(new Dimension(15, 15)), BorderLayout.EAST);
											Cpanel[13].add(Box.createRigidArea(new Dimension(15, 15)), BorderLayout.SOUTH);
											Cpanel[13].add(Box.createRigidArea(new Dimension(15, 15)), BorderLayout.WEST);
											Cpanel[13].add(fetxarea.CALLTEXTAREA(), BorderLayout.CENTER);
											Cpanel[13].revalidate();//Upanel[0]版面重新布局
											Cpanel[13].repaint();//Upanel[0]版面重新繪製
											
											CDpanel[13].removeAll();//將先前所在Dopanel[0]的物件進行清空
											CDpanel[13].add(Hint_Label[11]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
											CDpanel[13].revalidate();//Dopanel[0]版面重新布局
											CDpanel[13].repaint();//Dopanel[0]版面重新繪製
											
											
											cardlayout1.show(panel[4],CPArray[13]);
											cardlayout2.show(panel[5],CPArray[13]);
											
											System.out.println(FileCH.CALLFILPATH());
											
										}catch(Exception v){
											System.out.println(v);
										}
												
									}
								}
							}
							
							
						}else if(Sta_Flag == 2 || Watr_Flag == 1){
							Cpanel[14].removeAll();
							Cpanel[14].revalidate();
							Cpanel[14].repaint();
							CDpanel[14].removeAll();
							CDpanel[14].revalidate();
							CDpanel[14].repaint();
							cardlayout1.show(panel[4],CPArray[14]);
							cardlayout2.show(panel[5],CPArray[14]);
							JOptionPane.showMessageDialog(frame,MesArray[7]);
						}else if(Sta_Flag == 1 || Dacu_Flag == 1){
							Cpanel[15].removeAll();
							Cpanel[15].revalidate();
							Cpanel[15].repaint();
							CDpanel[15].removeAll();
							CDpanel[15].revalidate();
							CDpanel[15].repaint();
							cardlayout1.show(panel[4],CPArray[15]);
							cardlayout2.show(panel[5],CPArray[15]);
							JOptionPane.showMessageDialog(frame,MesArray[8]);
						}else{
							Cpanel[16].removeAll();
							Cpanel[16].revalidate();
							Cpanel[16].repaint();
							CDpanel[16].removeAll();
							CDpanel[16].revalidate();
							CDpanel[16].repaint();
							cardlayout1.show(panel[4],CPArray[16]);
							cardlayout2.show(panel[5],CPArray[16]);
							JOptionPane.showMessageDialog(frame,MesArray[9]);
						}
				}
				
				
				
				/*第五步點選創建模型 END*/
				
				
				/*第六步點選預測最佳到診時間 START*/
			}else if((JButton)e.getSource()==button[5]){
				
				if(Sta_Flag==4 || Mod_Flag==1){
					JFileChooser chooser2=new JFileChooser(".");//選擇檔案，並且預設位置為現在位置
					FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV file", "csv");
					chooser2.setFileFilter(filter);
					int returnVal = chooser2.showOpenDialog(panel[4]);
					
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						oporfile2 = chooser2.getSelectedFile();//取得使用者所選檔案
						if(oporfile2.toString().contains(".csv")){
							readtxt_3 readtxt_oporfile2 = new readtxt_3(oporfile2.toString());
							try {
								/*資料切割呼叫外部程式寫檔*/
								FileWriter fw = new FileWriter("javatoc");
								data_cuwachfe_path=oporfile2.toString();//s是檔案路徑
								fw.write(data_cuwachfe_path);
								fw.flush();
								fw.close();
								ArrayList<Integer> judFileNameSame2=new ArrayList<Integer>();
								judFileNameSame2.clear();
								for(int i = 0;i<readtxt_oporfile2.ColumnCount();i++){
									if(readtxt_oporfile2.getColumnName(i).equals(OriFileName[i])){
										judFileNameSame2.add(0);
									}else{
										judFileNameSame2.add(1);
									}
									//System.out.println(judFileNameSame.get(i));
								}
								if(judFileNameSame2.contains(1)){
									JOptionPane.showMessageDialog(frame,"所選檔案不符合系統所要求內容(門診紀錄)");
									Cpanel[17].removeAll();
									CDpanel[17].removeAll();
								}else{
									Object[] options = {"確定","取消"};
						
									int mType=JOptionPane.QUESTION_MESSAGE;
									int oType=JOptionPane.YES_NO_CANCEL_OPTION;
									int opt=JOptionPane.showOptionDialog(frame,"所選取預測檔案為：\n"+oporfile2.toString(),"請確認",oType,mType,null,options,options[0]);
									if(opt==0){
										//呼叫外部程式
										Runtime runtime=Runtime.getRuntime();             
										Process process = runtime.exec("Data_Cut_P2.exe");//file為執行外部程式的檔案名稱             
										System.out.println("切割執行中2--------------------");
										/*跳出等待視窗 START*/
										JOptionPane WaitPanel2 =new JOptionPane(ProMesArray[4], JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
										JDialog Waitdialog2 = new JDialog();
										Waitdialog2.setModal(true);
										Waitdialog2.setContentPane(WaitPanel2);
												
										Waitdialog2.pack();
										Dimension screenSize2 = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
										Dimension GIFdialogSize = Waitdialog2.getSize();
										Waitdialog2.setLocation(screenSize2.width/2-GIFdialogSize.width/2,screenSize2.height/2-GIFdialogSize.height/2);
										Waitdialog2.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
										System.out.println("評估執行中--------------------");
										
										new Thread(new Runnable() {
										  @Override
										  public void run() {
											try {
											  process.waitFor();
											  //Thread.sleep(500);
											} catch (InterruptedException e) {
											  e.printStackTrace();
											}
											Waitdialog2.setVisible(false);
										  }
										}).start();
										Waitdialog2.setVisible(true);
										/*跳出等待視窗 END*/
							
									
										/*資料切割呼叫外部程式寫檔*/
										JOptionPane.showMessageDialog(frame,ProEndMesArray[3]);	
										/*--------------------------------------------------------------------------------------------------------------------*/	
										
										FileCH2=new layouttest1_1(3);//將切割好的年份及檔案名稱顯示(0表示顯示切割的資料夾)
										Cpanel[17].removeAll();//將先前所在Cpanel[2]的物件進行清空
										Cpanel[17].add(FileCH2.CALLFILECH());//將回傳切割資料夾內容加入到Cpanel[2]
										Cpanel[17].revalidate();//Cpanel[2]版面重新布局
										Cpanel[17].repaint();//Cpanel[2]版面重新繪製
												
										CDpanel[17].removeAll();//將先前所在CDpanel[2]的物件進行清空
										CDpanel[17].add(Hint_Label2[27]);//將提示訊息"確認完畢請按資料切割"加入到CDpanel[2]	
										D_HiBuArraybutton[32].setPreferredSize(new Dimension(190,88));
										D_HiBuArraybutton[33].setPreferredSize(new Dimension(190,88));//將"檢視"按鈕設定大小寬150，高60
										CDpanel[17].add(D_HiBuArraybutton[32]);//將"檢視"按鈕加入到CDpanel[2]
										CDpanel[17].add(D_HiBuArraybutton[33]);
										CDpanel[17].revalidate();//CDpanel[2]版面重新布局
										CDpanel[17].repaint();//CDpanel[2]版面重新繪製
								
										cardlayout1.show(panel[4],CPArray[17]);//將Cpanel[2]顯示
										cardlayout2.show(panel[5],CPArray[17]);//將CDanel[2]顯示
									}										
								}
								judFileNameSame2.clear();  
								  
								
								
							} catch (Exception k) {
								System.out.println(k);
							}
						}else{
							JOptionPane.showMessageDialog(frame,"所選檔案不符合系統所需格式(.csv)");
						}		
					} else {
						System.out.println("Operation is CANCELLED :(");
					}
					
					
					
						
				}else if(Sta_Flag==3||Fet_Flag==1){
					Cpanel[18].removeAll();//將先前所在Cpanel[3]的物件進行清空
					Cpanel[18].revalidate();//Cpanel[3]版面重新布局
					Cpanel[18].repaint();//Cpanel[3]版面重新繪製
					CDpanel[18].removeAll();//將先前所在CDpanel[3]的物件進行清空
					CDpanel[18].revalidate();//Cpanel[3]版面重新布局
					CDpanel[18].repaint();//Cpanel[3]版面重新繪製
					cardlayout1.show(panel[4],CPArray[18]);//將Cpanel[3]顯示
					cardlayout2.show(panel[5],CPArray[18]);//將CDpanel[3]顯示
					
					JOptionPane.showMessageDialog(frame,MesArray[10]);//跳出小視窗顯示"請先進行資料清洗與切割"
				}else if(Sta_Flag==2||Watr_Flag==1){
					Cpanel[19].removeAll();//將先前所在Cpanel[3]的物件進行清空
					Cpanel[19].revalidate();//Cpanel[3]版面重新布局
					Cpanel[19].repaint();//Cpanel[3]版面重新繪製
					CDpanel[19].removeAll();//將先前所在CDpanel[3]的物件進行清空
					CDpanel[19].revalidate();//Cpanel[3]版面重新布局
					CDpanel[19].repaint();//Cpanel[3]版面重新繪製
					cardlayout1.show(panel[4],CPArray[19]);//將Cpanel[3]顯示
					cardlayout2.show(panel[5],CPArray[19]);//將CDpanel[3]顯示
					JOptionPane.showMessageDialog(frame,MesArray[11]);//跳出小視窗顯示"請先進行資料清洗與切割"
				}else if(Sta_Flag==1||Dacu_Flag==1){
					Cpanel[20].removeAll();//將先前所在Cpanel[3]的物件進行清空
					Cpanel[20].revalidate();//Cpanel[3]版面重新布局
					Cpanel[20].repaint();//Cpanel[3]版面重新繪製
					CDpanel[20].removeAll();//將先前所在CDpanel[3]的物件進行清空
					CDpanel[20].revalidate();//Cpanel[3]版面重新布局
					CDpanel[20].repaint();//Cpanel[3]版面重新繪製
					cardlayout1.show(panel[4],CPArray[20]);//將Cpanel[3]顯示
					cardlayout2.show(panel[5],CPArray[20]);//將CDpanel[3]顯示
					JOptionPane.showMessageDialog(frame,MesArray[12]);//跳出小視窗顯示"請先進行資料清洗與切割"
				}else{
					Cpanel[21].removeAll();//將先前所在Cpanel[3]的物件進行清空
					Cpanel[21].revalidate();//Cpanel[3]版面重新布局
					Cpanel[21].repaint();//Cpanel[3]版面重新繪製
					CDpanel[21].removeAll();//將先前所在CDpanel[3]的物件進行清空
					CDpanel[21].revalidate();//Cpanel[3]版面重新布局
					CDpanel[21].repaint();//Cpanel[3]版面重新繪製
					cardlayout1.show(panel[4],CPArray[21]);//將Cpanel[3]顯示
					cardlayout2.show(panel[5],CPArray[21]);//將CDpanel[3]顯示
					JOptionPane.showMessageDialog(frame,MesArray[13]);//跳出小視窗顯示"請先進行資料清洗與切割"
				}
				/*第六步點選預測最佳到診時間 END*/
				
				
				/*第七步點選效能比較 START*/
			}else if((JButton)e.getSource()==button[6]){
				String Queue=null;
				String waittime=null;
				String idle=null;
				String doctor_code=null;
				 
				ImageIcon image=new ImageIcon(ClickbtArray[6]);
				
				
				if(Eva_Flag==0 || (Eva_Flag==1&&Sta_Flag==4)){
					File Mod_re_file =new File("Hos_Data/Doc_Test/Mod_Re");
					
					//FileCH.getfilesize()==null
					//	||FileCH.getfoldersize()==null
					
					if(!Mod_re_file.exists()  && !Mod_re_file.isDirectory()){
						JOptionPane.showMessageDialog(frame,MesArray[18]);
					}else{
						FileCH=new layouttest1(6);//將切割好的年份及檔案名稱顯示(0表示顯示切割的資料夾)
						Cpanel[22].removeAll();//將先前所在Cpanel[3]的物件進行清空
						Cpanel[22].add(FileCH.CALLFILECH());//將回傳切割資料夾內容加入到Cpanel[3]
						Cpanel[22].revalidate();//Cpanel[3]版面重新布局
						Cpanel[22].repaint();//Cpanel[3]版面重新繪製
						CDpanel[22].removeAll();//將先前所在CDpanel[3]的物件進行清空
						CDpanel[22].add(Hint_Label[13]);//將提示訊息"確認完畢請按資料切割"加入到CDpanel[3]	
						HiBuArraybutton[15].setPreferredSize(new Dimension(190,88));//將"選擇清洗其他檔案"按鈕設定大小寬150，高60
						HiBuArraybutton[16].setPreferredSize(new Dimension(190,88));//將"檢視"按鈕設定大小寬150，高60
						CDpanel[22].add(HiBuArraybutton[15]);//將"選擇清洗其他檔案"按鈕加入到CDpanel[3]
						CDpanel[22].add(HiBuArraybutton[16]);//將"檢視"按鈕加入到CDpanel[3]
						
					}
			
					CDpanel[22].revalidate();//CDpanel[3]版面重新布局
					CDpanel[22].repaint();//CDpanel[3]版面重新繪製
						
						
					cardlayout1.show(panel[4],CPArray[22]);//將Cpanel[3]顯示
					cardlayout2.show(panel[5],CPArray[22]);//將CDpanel[3]顯示
					
					System.out.println("---第七步");//測試，成功執行印出"1"
					
					
					
					
				}else{
					
					try{
						String modelname=null;
						BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File("ctojava"))));          
										
					
						modelname = bf.readLine();
						FileWriter fw = new FileWriter("javatoc");
						
						System.out.println("2模型結果檔名路徑為"+modelname);
						
						
						fw.write(modelname);
						fw.flush();
						fw.close();
						
					}catch(Exception v){
						System.out.println(v);
					}	
					try {
						//呼叫外部程式
						Runtime runtime=Runtime.getRuntime();             
						Process process = runtime.exec("Effect_Comparsion_Sys.exe");//file為執行外部程式的檔案名稱             
						System.out.println("效能比較--------------------");
						/*跳出等待視窗 START*/
							
							
						JOptionPane WaitPanel =new JOptionPane(ProMesArray[5], JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
						//WaitPanel.setFont(softfont[0]);
						JDialog Waitdialog = new JDialog();
						Waitdialog.setModal(true);
						Waitdialog.setContentPane(WaitPanel);
							
						Waitdialog.pack();
						Dimension screenSize2 = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
						Dimension GIFdialogSize = Waitdialog.getSize();
						Waitdialog.setLocation(screenSize2.width/2-GIFdialogSize.width/2,screenSize2.height/2-GIFdialogSize.height/2);
						Waitdialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
						System.out.println("畫圖執行中--------------------");
							
						new Thread(new Runnable() {
						  @Override
						  public void run() {
							try {
							  process.waitFor();
							  //Thread.sleep(5000);
							} catch (InterruptedException e) {
							  e.printStackTrace();
							}
							Waitdialog.setVisible(false);
						  }
						}).start();
						Waitdialog.setVisible(true);
						JOptionPane.showMessageDialog(frame,ProEndMesArray[5]);
						/*跳出等待視窗 END*/
						BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File("ctojava2"))));          
										
					
						Queue = bf.readLine();
						System.out.println("有讀取1 "+Queue);
						waittime = bf.readLine();
						System.out.println("有讀取2 "+waittime);
						idle = bf.readLine();
						System.out.println("有讀取3 "+idle);  
						doctor_code = bf.readLine();
						System.out.println("有讀取4 "+doctor_code); 
					} catch (Exception k) {
							System.out.println(k);
					} 		
				
				
					piclayout = new layouttest6_6_2("test",Queue,waittime,idle,doctor_code);
				
					Cpanel[23].removeAll();//將先前所在Upanel[0]的物件進行清空
					Cpanel[23].setLayout(new BoxLayout(Cpanel[23], BoxLayout.Y_AXIS));
					Cpanel[23].add(piclayout.CALLPIC());//將readtxt_3所回傳之JScrollPane加到Upanel[0]
					
					Cpanel[23].revalidate();//Upanel[0]版面重新布局
					Cpanel[23].repaint();//Upanel[0]版面重新繪製
					
					CDpanel[23].removeAll();//將先前所在Dopanel[0]的物件進行清空
					CDpanel[23].add(Hint_Label[14]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
					
					
					CDpanel[23].revalidate();//Dopanel[0]版面重新布局
					CDpanel[23].repaint();//Dopanel[0]版面重新繪製
					
					
					cardlayout1.show(panel[4],CPArray[23]);//將Cpanel[3]顯示
					cardlayout2.show(panel[5],CPArray[23]);//將CDpanel[3]顯示
					
					button[6].setIcon(image);
				}
				/*第七步點選效能比較 END*/
				
				/*第八步點選一鍵處理 START*/
			}else if((JButton)e.getSource()==button[7]){
				System.out.println("第8步");//測試，印出"第8步"
				ImageIcon image=new ImageIcon(ClickbtArray[7]);
				Cpanel[24].removeAll();
				
				Cpanel[24].add(allinone.AllInOne());
				Cpanel[24].revalidate();//Cpanel[1]版面重新布局
				Cpanel[24].repaint();//Cpanel[1]版面重新繪製
				CDpanel[24].removeAll();//將先前所在Dopanel[0]的物件進行清空
				CDpanel[24].add(Hint_Label[15]);
				HiBuArraybutton[17].setPreferredSize(new Dimension(190,88));
				CDpanel[24].add(HiBuArraybutton[17]);
				
				CDpanel[24].revalidate();//Dopanel[0]版面重新布局
				CDpanel[24].repaint();//Dopanel[0]版面重新繪製
				cardlayout1.show(panel[4],CPArray[24]);//將Cpanel[3]顯示
				cardlayout2.show(panel[5],CPArray[24]);//將CDpanel[3]顯示
				//button[7].setIcon(image);
				/*第八步點選一鍵處理 END*/
			}else{
				System.out.println("測試，失敗");//測試，失敗印出"失敗"
			}
		}
	}
	
	public static class buttonhandler2 implements ActionListener {
		public void actionPerformed(ActionEvent e){
			if((JButton)e.getSource()==HiBuArraybutton[0]){//若按下資料切割中的"檢視"按鈕
				if(FileCH.IsClick()==1){
					readtxt2 = new readtxt_3(FileCH.CALLFILPATH());//將使用者選取檔案路徑轉為字串並呼叫readtxt_3
					Upanel[0].removeAll();//將先前所在Upanel[0]的物件進行清空
					Upanel[0].add(readtxt2.CALLTABLE());//將readtxt_3所回傳之JScrollPane加到Upanel[0]
					Upanel[0].revalidate();//Upanel[0]版面重新布局
					Upanel[0].repaint();//Upanel[0]版面重新繪製
					
					Dopanel[0].removeAll();//將先前所在Dopanel[0]的物件進行清空
					Dopanel[0].add(Hint_Label2[0]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
					D_HiBuArraybutton[0].setPreferredSize(new Dimension(190,88));//將"上一步"按鈕設定大小寬150，高60
					Dopanel[0].add(D_HiBuArraybutton[0]);//將"上一步"按鈕加入到Dopanel[0]
					Dopanel[0].revalidate();//Dopanel[0]版面重新布局
					Dopanel[0].repaint();//Dopanel[0]版面重新繪製
					
					
					cardlayout1.show(panel[4],UPArray[0]);//將Upanel[0]顯示
					cardlayout2.show(panel[5],UPArray[0]);//將Dopanel[0]顯示
					
					System.out.println(FileCH.CALLFILPATH());
					//System.out.println("檢視---------");
				}else{
					JOptionPane.showMessageDialog(frame,"尚未選取檔案以致無法顯示");
				}
				
				
			}else if((JButton)e.getSource()==D_HiBuArraybutton[0]){//若按下資料切割中的"上一頁"按鈕
				cardlayout1.show(panel[4],CPArray[1]);//將Cpanel[1]顯示
				cardlayout2.show(panel[5],CPArray[1]);//將CDpanel[1]顯示
				
			}else if((JButton)e.getSource()==HiBuArraybutton[1]){//若按下資料清洗與轉換中的"檢視"按鈕
				if(FileCH.IsClick()==1){
					readtxt3 = new readtxt_3(FileCH.CALLFILPATH());//將使用者選取檔案路徑轉為字串並呼叫readtxt_3
					Upanel[1].removeAll();//將先前所在Cpanel[4]的物件進行清空
					Upanel[1].add(readtxt3.CALLTABLE());//將readtxt_3所回傳之JScrollPane加到Cpanel[0]
					Upanel[1].revalidate();//Cpanel[4]版面重新布局
					Upanel[1].repaint();//Cpanel[4]版面重新繪製
					
					Dopanel[1].removeAll();//將先前所在CDpanel[4]的物件進行清空
					Dopanel[1].add(Hint_Label2[1]);//將提示訊息"確認完畢請按資料切割"加入到CDpanel[4]
					D_HiBuArraybutton[1].setPreferredSize(new Dimension(190,88));//將"上一步"按鈕設定大小寬150，高60
					Dopanel[1].add(D_HiBuArraybutton[1]);//將"上一步"按鈕加入到CDpanel[4]
					Dopanel[1].revalidate();//CDpanel[4]版面重新布局
					Dopanel[1].repaint();//CDpanel[4]版面重新繪製
					
					
					cardlayout1.show(panel[4],UPArray[1]);//將Cpanel[4]顯示
					cardlayout2.show(panel[5],UPArray[1]);//將CDpanel[4]顯示
					
					System.out.println(FileCH.CALLFILPATH());
					System.out.println("檢視---------22222");
				}else{
					JOptionPane.showMessageDialog(frame,"尚未選取檔案以致無法顯示");
				}
			}else if((JButton)e.getSource()==D_HiBuArraybutton[1]){//若按下資料清洗與轉換中的"上一頁"按鈕
				cardlayout1.show(panel[4],CPArray[2]);//將Cpanel[2]顯示
				cardlayout2.show(panel[5],CPArray[2]);//將CDpanel[2]顯示
			}else if((JButton)e.getSource()==HiBuArraybutton[2]){
				if(FileCH.IsClick()==1){
					readtxt4 = new readtxt_3(FileCH.CALLFILPATH());//將使用者選取檔案路徑轉為字串並呼叫readtxt_3
					Upanel[3].removeAll();//將先前所在Upanel[0]的物件進行清空
					Upanel[3].add(readtxt4.CALLTABLE());//將readtxt_3所回傳之JScrollPane加到Upanel[0]
					Upanel[3].revalidate();//Upanel[0]版面重新布局
					Upanel[3].repaint();//Upanel[0]版面重新繪製
					
					Dopanel[3].removeAll();//將先前所在Dopanel[0]的物件進行清空
					Dopanel[3].add(Hint_Label2[2]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
					D_HiBuArraybutton[2].setPreferredSize(new Dimension(190,88));//將"上一步"按鈕設定大小寬150，高60
					D_HiBuArraybutton[3].setPreferredSize(new Dimension(190,88));
					Dopanel[3].add(D_HiBuArraybutton[2]);//將"上一步"按鈕加入到Dopanel[0]
					Dopanel[3].add(D_HiBuArraybutton[3]);
					Dopanel[3].revalidate();//Dopanel[0]版面重新布局
					Dopanel[3].repaint();//Dopanel[0]版面重新繪製
					
					
					cardlayout1.show(panel[4],UPArray[3]);//將Upanel[0]顯示
					cardlayout2.show(panel[5],UPArray[3]);//將Dopanel[0]顯示
					
					System.out.println(FileCH.CALLFILPATH());
					//System.out.println("檢視---------");
				}else{
					JOptionPane.showMessageDialog(frame,"尚未選取檔案以致無法顯示");
				}
			}else if((JButton)e.getSource()==D_HiBuArraybutton[2]){
				//---------------------------------------------------------------------------
				FileCH=new layouttest1(0);//將切割好的年份及檔案名稱顯示(0表示顯示切割的資料夾)
				Upanel[4].removeAll();//將先前所在Cpanel[2]的物件進行清空
				Upanel[4].add(FileCH.CALLFILECH());//將回傳切割資料夾內容加入到Cpanel[2]
				Upanel[4].revalidate();//Cpanel[2]版面重新布局
				Upanel[4].repaint();//Cpanel[2]版面重新繪製
						
				Dopanel[4].removeAll();//將先前所在CDpanel[2]的物件進行清空
				Dopanel[4].add(Hint_Label2[3]);//將提示訊息"確認完畢請按資料切割"加入到CDpanel[2]	
						
				D_HiBuArraybutton[4].setPreferredSize(new Dimension(190,88));//將"檢視"按鈕設定大小寬150，高60
				Dopanel[4].add(D_HiBuArraybutton[4]);//將"檢視"按鈕加入到CDpanel[2]
				Dopanel[4].revalidate();//CDpanel[2]版面重新布局
				Dopanel[4].repaint();//CDpanel[2]版面重新繪製
			
				cardlayout1.show(panel[4],UPArray[4]);//將Cpanel[2]顯示
				cardlayout2.show(panel[5],UPArray[4]);//將CDanel[2]顯示
			}else if((JButton)e.getSource()==D_HiBuArraybutton[4]){
				if(FileCH.IsClick()==1){
					readtxt5 = new readtxt_3(FileCH.CALLFILPATH());//將使用者選取檔案路徑轉為字串並呼叫readtxt_3
					Upanel[5].removeAll();//將先前所在Upanel[0]的物件進行清空
					Upanel[5].add(readtxt5.CALLTABLE());//將readtxt_3所回傳之JScrollPane加到Upanel[0]
					Upanel[5].revalidate();//Upanel[0]版面重新布局
					Upanel[5].repaint();//Upanel[0]版面重新繪製
					
					Dopanel[5].removeAll();//將先前所在Dopanel[0]的物件進行清空
					Dopanel[5].add(Hint_Label2[4]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
					D_HiBuArraybutton[5].setPreferredSize(new Dimension(190,88));//將"上一步"按鈕設定大小寬150，高60
					Dopanel[5].add(D_HiBuArraybutton[5]);//將"上一步"按鈕加入到Dopanel[0]
					Dopanel[5].revalidate();//Dopanel[0]版面重新布局
					Dopanel[5].repaint();//Dopanel[0]版面重新繪製
					
					
					cardlayout1.show(panel[4],UPArray[5]);//將Upanel[0]顯示
					cardlayout2.show(panel[5],UPArray[5]);//將Dopanel[0]顯示
					
					System.out.println(FileCH.CALLFILPATH());
					//System.out.println("檢視---------");
				}else{
					JOptionPane.showMessageDialog(frame,"尚未選取檔案以致無法顯示");
				}
			}else if((JButton)e.getSource()==D_HiBuArraybutton[5]){
				cardlayout1.show(panel[4],UPArray[4]);//將Cpanel[2]顯示
				cardlayout2.show(panel[5],UPArray[4]);//將CDanel[2]顯示
			}else if((JButton)e.getSource()==D_HiBuArraybutton[3]){
				cardlayout1.show(panel[4],CPArray[3]);//將Cpanel[3]顯示
				cardlayout2.show(panel[5],CPArray[3]);//將CDpanel[3]顯示
			}else if((JButton)e.getSource()==HiBuArraybutton[3]){
				
				FileCH=new layouttest1(0);//將切割好的年份及檔案名稱顯示(0表示顯示切割的資料夾)
				Upanel[2].removeAll();//將先前所在Cpanel[2]的物件進行清空
				Upanel[2].add(FileCH.CALLFILECH());//將回傳切割資料夾內容加入到Cpanel[2]
				Upanel[2].revalidate();//Cpanel[2]版面重新布局
				Upanel[2].repaint();//Cpanel[2]版面重新繪製
						
				Dopanel[2].removeAll();//將先前所在CDpanel[2]的物件進行清空
				Dopanel[2].add(Hint_Label2[5]);//將提示訊息"確認完畢請按資料切割"加入到CDpanel[2]	
						
				D_HiBuArraybutton[6].setPreferredSize(new Dimension(190,88));//將"檢視"按鈕設定大小寬150，高60
				Dopanel[2].add(D_HiBuArraybutton[6]);//將"檢視"按鈕加入到CDpanel[2]
				Dopanel[2].revalidate();//CDpanel[2]版面重新布局
				Dopanel[2].repaint();//CDpanel[2]版面重新繪製
			
				cardlayout1.show(panel[4],UPArray[2]);//將Cpanel[2]顯示
				cardlayout2.show(panel[5],UPArray[2]);//將CDanel[2]顯示
				
			}else if((JButton)e.getSource()==D_HiBuArraybutton[6]){
				if(FileCH.IsClick()==1){
					readtxt6 = new readtxt_3(FileCH.CALLFILPATH());//將使用者選取檔案路徑轉為字串並呼叫readtxt_3
					Upanel[6].removeAll();//將先前所在Upanel[0]的物件進行清空
					Upanel[6].add(readtxt6.CALLTABLE());//將readtxt_3所回傳之JScrollPane加到Upanel[0]
					Upanel[6].revalidate();//Upanel[0]版面重新布局
					Upanel[6].repaint();//Upanel[0]版面重新繪製
					
					Dopanel[6].removeAll();//將先前所在Dopanel[0]的物件進行清空
					Dopanel[6].add(Hint_Label2[6]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
					D_HiBuArraybutton[7].setPreferredSize(new Dimension(190,88));//將"上一步"按鈕設定大小寬150，高60
					Dopanel[6].add(D_HiBuArraybutton[7]);//將"上一步"按鈕加入到Dopanel[0]
					Dopanel[6].revalidate();//Dopanel[0]版面重新布局
					Dopanel[6].repaint();//Dopanel[0]版面重新繪製
					
					
					cardlayout1.show(panel[4],UPArray[6]);//將Upanel[0]顯示
					cardlayout2.show(panel[5],UPArray[6]);//將Dopanel[0]顯示
					
					System.out.println(FileCH.CALLFILPATH());
					//System.out.println("檢視---------");
				}else{
					JOptionPane.showMessageDialog(frame,"尚未選取檔案以致無法顯示");
				}
				
			}else if((JButton)e.getSource()==D_HiBuArraybutton[7]){
				cardlayout1.show(panel[4],UPArray[2]);//將Cpanel[2]顯示
				cardlayout2.show(panel[5],UPArray[2]);//將CDanel[2]顯示
			}else if((JButton)e.getSource()==HiBuArraybutton[4]){
				FileCH=new layouttest1(0);//將切割好的年份及檔案名稱顯示(0表示顯示切割的資料夾)
				Upanel[7].removeAll();//將先前所在Cpanel[2]的物件進行清空
				Upanel[7].add(FileCH.CALLFILECH());//將回傳切割資料夾內容加入到Cpanel[2]
				Upanel[7].revalidate();//Cpanel[2]版面重新布局
				Upanel[7].repaint();//Cpanel[2]版面重新繪製
						
				Dopanel[7].removeAll();//將先前所在CDpanel[2]的物件進行清空
				Dopanel[7].add(Hint_Label2[7]);//將提示訊息"確認完畢請按資料切割"加入到CDpanel[2]	
						
				D_HiBuArraybutton[8].setPreferredSize(new Dimension(190,88));//將"檢視"按鈕設定大小寬150，高60
				Dopanel[7].add(D_HiBuArraybutton[8]);//將"檢視"按鈕加入到CDpanel[2]
				Dopanel[7].revalidate();//CDpanel[2]版面重新布局
				Dopanel[7].repaint();//CDpanel[2]版面重新繪製
			
				cardlayout1.show(panel[4],UPArray[7]);//將Cpanel[2]顯示
				cardlayout2.show(panel[5],UPArray[7]);//將CDanel[2]顯示
			}else if((JButton)e.getSource()==D_HiBuArraybutton[8]){
				if(FileCH.IsClick()==1){
					readtxt7 = new readtxt_3(FileCH.CALLFILPATH());//將使用者選取檔案路徑轉為字串並呼叫readtxt_3
					Upanel[8].removeAll();//將先前所在Upanel[0]的物件進行清空
					Upanel[8].add(readtxt7.CALLTABLE());//將readtxt_3所回傳之JScrollPane加到Upanel[0]
					Upanel[8].revalidate();//Upanel[0]版面重新布局
					Upanel[8].repaint();//Upanel[0]版面重新繪製
					
					Dopanel[8].removeAll();//將先前所在Dopanel[0]的物件進行清空
					Dopanel[8].add(Hint_Label2[8]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
					D_HiBuArraybutton[9].setPreferredSize(new Dimension(190,88));//將"上一步"按鈕設定大小寬150，高60
					Dopanel[8].add(D_HiBuArraybutton[9]);//將"上一步"按鈕加入到Dopanel[0]
					Dopanel[8].revalidate();//Dopanel[0]版面重新布局
					Dopanel[8].repaint();//Dopanel[0]版面重新繪製
					
					
					cardlayout1.show(panel[4],UPArray[8]);//將Upanel[0]顯示
					cardlayout2.show(panel[5],UPArray[8]);//將Dopanel[0]顯示
					
					System.out.println(FileCH.CALLFILPATH());
					//System.out.println("檢視---------");
				}else{
					JOptionPane.showMessageDialog(frame,"尚未選取檔案以致無法顯示");
				}
				
			}else if((JButton)e.getSource()==D_HiBuArraybutton[9]){
				cardlayout1.show(panel[4],UPArray[7]);//將Cpanel[2]顯示
				cardlayout2.show(panel[5],UPArray[7]);//將CDanel[2]顯示
			}else if((JButton)e.getSource()==HiBuArraybutton[5]){
				if(FileCH.IsClick()==1){
					readtxt8 = new readtxt_3(FileCH.CALLFILPATH());//將使用者選取檔案路徑轉為字串並呼叫readtxt_3
					Upanel[9].removeAll();//將先前所在Upanel[0]的物件進行清空
					Upanel[9].add(readtxt8.CALLTABLE());//將readtxt_3所回傳之JScrollPane加到Upanel[0]
					Upanel[9].revalidate();//Upanel[0]版面重新布局
					Upanel[9].repaint();//Upanel[0]版面重新繪製
					
					Dopanel[9].removeAll();//將先前所在Dopanel[0]的物件進行清空
					Dopanel[9].add(Hint_Label2[9]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
					D_HiBuArraybutton[10].setPreferredSize(new Dimension(190,88));//將"上一步"按鈕設定大小寬150，高60
					D_HiBuArraybutton[11].setPreferredSize(new Dimension(190,88));
					Dopanel[9].add(D_HiBuArraybutton[10]);//將"上一步"按鈕加入到Dopanel[0]
					Dopanel[9].add(D_HiBuArraybutton[11]);
					Dopanel[9].revalidate();//Dopanel[0]版面重新布局
					Dopanel[9].repaint();//Dopanel[0]版面重新繪製
					
					
					cardlayout1.show(panel[4],UPArray[9]);//將Upanel[0]顯示
					cardlayout2.show(panel[5],UPArray[9]);//將Dopanel[0]顯示
					
					System.out.println(FileCH.CALLFILPATH());
					//System.out.println("檢視---------");*/
				}else{
					JOptionPane.showMessageDialog(frame,"尚未選取檔案以致無法顯示");
				}
				
				
				
			}else if((JButton)e.getSource()==D_HiBuArraybutton[10]){
				FileCH=new layouttest1(0);//將切割好的年份及檔案名稱顯示(0表示顯示切割的資料夾)
				Upanel[10].removeAll();//將先前所在Cpanel[2]的物件進行清空
				Upanel[10].add(FileCH.CALLFILECH());//將回傳切割資料夾內容加入到Cpanel[2]
				Upanel[10].revalidate();//Cpanel[2]版面重新布局
				Upanel[10].repaint();//Cpanel[2]版面重新繪製
						
				Dopanel[10].removeAll();//將先前所在CDpanel[2]的物件進行清空
				Dopanel[10].add(Hint_Label2[10]);//將提示訊息"確認完畢請按資料切割"加入到CDpanel[2]	
						
				D_HiBuArraybutton[12].setPreferredSize(new Dimension(190,88));//將"檢視"按鈕設定大小寬150，高60
				Dopanel[10].add(D_HiBuArraybutton[12]);//將"檢視"按鈕加入到CDpanel[2]
				Dopanel[10].revalidate();//CDpanel[2]版面重新布局
				Dopanel[10].repaint();//CDpanel[2]版面重新繪製
			
				cardlayout1.show(panel[4],UPArray[10]);//將Cpanel[2]顯示
				cardlayout2.show(panel[5],UPArray[10]);//將CDanel[2]顯示
			}else if((JButton)e.getSource()==D_HiBuArraybutton[12]){
				if(FileCH.IsClick()==1){
					readtxt9 = new readtxt_3(FileCH.CALLFILPATH());//將使用者選取檔案路徑轉為字串並呼叫readtxt_3
					Upanel[11].removeAll();//將先前所在Upanel[0]的物件進行清空
					Upanel[11].add(readtxt9.CALLTABLE());//將readtxt_3所回傳之JScrollPane加到Upanel[0]
					Upanel[11].revalidate();//Upanel[0]版面重新布局
					Upanel[11].repaint();//Upanel[0]版面重新繪製
					
					Dopanel[11].removeAll();//將先前所在Dopanel[0]的物件進行清空
					Dopanel[11].add(Hint_Label2[11]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
					D_HiBuArraybutton[13].setPreferredSize(new Dimension(190,88));//將"上一步"按鈕設定大小寬150，高60
					Dopanel[11].add(D_HiBuArraybutton[13]);//將"上一步"按鈕加入到Dopanel[0]
					Dopanel[11].revalidate();//Dopanel[0]版面重新布局
					Dopanel[11].repaint();//Dopanel[0]版面重新繪製
					
					
					cardlayout1.show(panel[4],UPArray[11]);//將Upanel[0]顯示
					cardlayout2.show(panel[5],UPArray[11]);//將Dopanel[0]顯示
					
					System.out.println(FileCH.CALLFILPATH());
					//System.out.println("檢視---------");*/
				}else{
					JOptionPane.showMessageDialog(frame,"尚未選取檔案以致無法顯示");
				}
				
			}else if((JButton)e.getSource()==D_HiBuArraybutton[13]){
				cardlayout1.show(panel[4],UPArray[10]);//將Cpanel[2]顯示
				cardlayout2.show(panel[5],UPArray[10]);//將CDanel[2]顯示
			}else if((JButton)e.getSource()==D_HiBuArraybutton[11]){
				FileCH=new layouttest1(1);//將切割好的年份及檔案名稱顯示(0表示顯示切割的資料夾)
				Upanel[12].removeAll();//將先前所在Cpanel[2]的物件進行清空
				Upanel[12].add(FileCH.CALLFILECH());//將回傳切割資料夾內容加入到Cpanel[2]
				Upanel[12].revalidate();//Cpanel[2]版面重新布局
				Upanel[12].repaint();//Cpanel[2]版面重新繪製
						
				Dopanel[12].removeAll();//將先前所在CDpanel[2]的物件進行清空
				Dopanel[12].add(Hint_Label2[12]);//將提示訊息"確認完畢請按資料切割"加入到CDpanel[2]	
						
				D_HiBuArraybutton[14].setPreferredSize(new Dimension(190,88));//將"檢視"按鈕設定大小寬150，高60
				D_HiBuArraybutton[15].setPreferredSize(new Dimension(190,88));
				Dopanel[12].add(D_HiBuArraybutton[14]);//將"檢視"按鈕加入到CDpanel[2]
				Dopanel[12].add(D_HiBuArraybutton[15]);
				Dopanel[12].revalidate();//CDpanel[2]版面重新布局
				Dopanel[12].repaint();//CDpanel[2]版面重新繪製
			
				cardlayout1.show(panel[4],UPArray[12]);//將Cpanel[2]顯示
				cardlayout2.show(panel[5],UPArray[12]);//將CDanel[2]顯示
			}else if((JButton)e.getSource()==D_HiBuArraybutton[14]){
				FileCH=new layouttest1(0);//將切割好的年份及檔案名稱顯示(0表示顯示切割的資料夾)
				Upanel[13].removeAll();//將先前所在Cpanel[2]的物件進行清空
				Upanel[13].add(FileCH.CALLFILECH());//將回傳切割資料夾內容加入到Cpanel[2]
				Upanel[13].revalidate();//Cpanel[2]版面重新布局
				Upanel[13].repaint();//Cpanel[2]版面重新繪製
						
				Dopanel[13].removeAll();//將先前所在CDpanel[2]的物件進行清空
				Dopanel[13].add(Hint_Label2[13]);//將提示訊息"確認完畢請按資料切割"加入到CDpanel[2]	
						
				D_HiBuArraybutton[16].setPreferredSize(new Dimension(190,88));//將"檢視"按鈕設定大小寬150，高60
				Dopanel[13].add(D_HiBuArraybutton[16]);//將"檢視"按鈕加入到CDpanel[2]
				Dopanel[13].revalidate();//CDpanel[2]版面重新布局
				Dopanel[13].repaint();//CDpanel[2]版面重新繪製
			
				cardlayout1.show(panel[4],UPArray[13]);//將Cpanel[2]顯示
				cardlayout2.show(panel[5],UPArray[13]);//將CDanel[2]顯示
			}else if((JButton)e.getSource()==D_HiBuArraybutton[16]){
				if(FileCH.IsClick()==1){
					readtxt10 = new readtxt_3(FileCH.CALLFILPATH());//將使用者選取檔案路徑轉為字串並呼叫readtxt_3
					Upanel[14].removeAll();//將先前所在Upanel[0]的物件進行清空
					Upanel[14].add(readtxt10.CALLTABLE());//將readtxt_3所回傳之JScrollPane加到Upanel[0]
					Upanel[14].revalidate();//Upanel[0]版面重新布局
					Upanel[14].repaint();//Upanel[0]版面重新繪製
					
					Dopanel[14].removeAll();//將先前所在Dopanel[0]的物件進行清空
					Dopanel[14].add(Hint_Label2[14]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
					D_HiBuArraybutton[17].setPreferredSize(new Dimension(190,88));//將"上一步"按鈕設定大小寬150，高60
					Dopanel[14].add(D_HiBuArraybutton[17]);//將"上一步"按鈕加入到Dopanel[0]
					Dopanel[14].revalidate();//Dopanel[0]版面重新布局
					Dopanel[14].repaint();//Dopanel[0]版面重新繪製
					
					
					cardlayout1.show(panel[4],UPArray[14]);//將Upanel[0]顯示
					cardlayout2.show(panel[5],UPArray[14]);//將Dopanel[0]顯示
					
					System.out.println(FileCH.CALLFILPATH());
					//System.out.println("檢視---------");*/
				}else{
					JOptionPane.showMessageDialog(frame,"尚未選取檔案以致無法顯示");
				}
				
			}else if((JButton)e.getSource()==D_HiBuArraybutton[17]){
				cardlayout1.show(panel[4],UPArray[13]);//將Cpanel[2]顯示
				cardlayout2.show(panel[5],UPArray[13]);//將CDanel[2]顯示
			}else if((JButton)e.getSource()==D_HiBuArraybutton[15]){
				cardlayout1.show(panel[4],UPArray[9]);//將Upanel[0]顯示
				cardlayout2.show(panel[5],UPArray[9]);//將Dopanel[0]顯示
			}else if((JButton)e.getSource()==HiBuArraybutton[6]){
				if(FileCH.IsClick()==1){
					NOWChFile="";
					readtxt11 = new readtxt_3(FileCH.CALLFILPATH());//將使用者選取檔案路徑轉為字串並呼叫readtxt_3
					Upanel[15].removeAll();//將先前所在Upanel[0]的物件進行清空
					Upanel[15].add(readtxt11.CALLTABLE());//將readtxt_3所回傳之JScrollPane加到Upanel[0]
					Upanel[15].revalidate();//Upanel[0]版面重新布局
					Upanel[15].repaint();//Upanel[0]版面重新繪製
					
					Dopanel[15].removeAll();//將先前所在Dopanel[0]的物件進行清空
					Dopanel[15].add(Hint_Label2[15]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
					D_HiBuArraybutton[18].setPreferredSize(new Dimension(190,88));//將"上一步"按鈕設定大小寬150，高60
					D_HiBuArraybutton[19].setPreferredSize(new Dimension(190,88));
					Dopanel[15].add(D_HiBuArraybutton[18]);//將"上一步"按鈕加入到Dopanel[0]
					Dopanel[15].add(D_HiBuArraybutton[19]);
					Dopanel[15].revalidate();//Dopanel[0]版面重新布局
					Dopanel[15].repaint();//Dopanel[0]版面重新繪製
					
					
					cardlayout1.show(panel[4],UPArray[15]);//將Upanel[0]顯示
					cardlayout2.show(panel[5],UPArray[15]);//將Dopanel[0]顯示
					NOWChFile=FileCH.CALLOnlyName();
					System.out.println(FileCH.CALLOnlyName()+"ㄎㄎㄎ");
					//System.out.println("檢視---------");*/
				}else{
					JOptionPane.showMessageDialog(frame,"尚未選取檔案以致無法顯示");
				}
				
				
			}else if((JButton)e.getSource()==D_HiBuArraybutton[18]){
				cardlayout1.show(panel[4],CPArray[6]);//將Cpanel[3]顯示
				cardlayout2.show(panel[5],CPArray[6]);//將CDpanel[3]顯示
			}else if((JButton)e.getSource()==HiBuArraybutton[7]||(JButton)e.getSource()==D_HiBuArraybutton[19]){
				if(FileCH.IsClick()==1){
					NOWChFile=FileCH.CALLOnlyName();
					Object[] options = {"卡方檢驗","方差過濾"};
					
					int mType=JOptionPane.QUESTION_MESSAGE;
					int oType=JOptionPane.YES_NO_CANCEL_OPTION;
					opt1=JOptionPane.showOptionDialog(panel[3], "目前選擇的檔案為"+NOWChFile+"\n請選擇特徵選取方法","請選取",oType,mType,null,options,options[0]) ;
					Upanel[16].removeAll();//將先前所在Upanel[0]的物件進行清空
					
					try{
						FileWriter fw = new FileWriter("javatoc");
						data_cuwachfe_path=FileCH.CALLFILPATH();//s是檔案路徑
						fw.write(data_cuwachfe_path);
						fw.flush();
						fw.close();
						System.out.println("特徵選取複寫檔中--------------------");
						cortana = new Cortana(FileCH.CALLFILPATH());
						if(opt1==0){
							listpanel=new layouttest2_2(FileCH.CALLFILPATH().replace("Wa_Ch", "Feature").replace(".csv", "_CHi2.txt"),0,cortana.getPath());	
							/*跳出等待視窗 START*/
							JOptionPane WaitPanel3 =new JOptionPane(ProMesArray[2], JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
							JDialog Waitdialog3 = new JDialog();
							Waitdialog3.setModal(true);
							Waitdialog3.setContentPane(WaitPanel3);
							
							Waitdialog3.pack();
							Dimension screenSize2 = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
							Dimension GIFdialogSize = Waitdialog3.getSize();
							Waitdialog3.setLocation(screenSize2.width/2-GIFdialogSize.width/2,screenSize2.height/2-GIFdialogSize.height/2);
							Waitdialog3.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
							System.out.println("特徵執行中--------------------");
							
							new Thread(new Runnable() {
							  @Override
							  public void run() {
								try {
								 // process.waitFor();
								 Thread.sleep(1000);
								} catch (Exception e) {
								 System.out.println(e);
								}
								Waitdialog3.setVisible(false);
							  }
							}).start();
							Waitdialog3.setVisible(true);
							JOptionPane.showMessageDialog(frame,ProEndMesArray[4]);	
							
							Upanel[16].add(listpanel.CALLLISTPANEL());//將readtxt_3所回傳之JScrollPane加到Upanel[0]
							Upanel[16].revalidate();//Upanel[0]版面重新布局
							Upanel[16].repaint();//Upanel[0]版面重新繪製
							Dopanel[16].removeAll();//將先前所在Dopanel[0]的物件進行清空
							Dopanel[16].add(Hint_Label2[16]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
							D_HiBuArraybutton[20].setPreferredSize(new Dimension(190,88));//將"上一步"按鈕設定大小寬150，高60
							D_HiBuArraybutton[21].setPreferredSize(new Dimension(190,88));
							Dopanel[16].add(D_HiBuArraybutton[20]);//將"上一步"按鈕加入到Dopanel[0]
							D_HiBuArraybutton[20].setEnabled(false);
							Dopanel[16].add(D_HiBuArraybutton[21]);
							Dopanel[16].revalidate();//Dopanel[0]版面重新布局
							Dopanel[16].repaint();//Dopanel[0]版面重新繪製
							cardlayout1.show(panel[4],UPArray[16]);//將Upanel[0]顯示
							cardlayout2.show(panel[5],UPArray[16]);//將Dopanel[0]顯示
							/*跳出等待視窗 END*/					
						}else if(opt1==1){
							listpanel=new layouttest2_2(FileCH.CALLFILPATH().replace("Wa_Ch", "Feature").replace(".csv", "_FT.txt"),1,cortana.getPath());	
							/*跳出等待視窗 START*/
							JOptionPane WaitPanel3 =new JOptionPane(ProMesArray[2], JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
							JDialog Waitdialog3 = new JDialog();
							Waitdialog3.setModal(true);
							Waitdialog3.setContentPane(WaitPanel3);
							
							Waitdialog3.pack();
							Dimension screenSize2 = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
							Dimension GIFdialogSize = Waitdialog3.getSize();
							Waitdialog3.setLocation(screenSize2.width/2-GIFdialogSize.width/2,screenSize2.height/2-GIFdialogSize.height/2);
							Waitdialog3.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
							System.out.println("特徵執行中--------------------");
							
							new Thread(new Runnable() {
							  @Override
							  public void run() {
								try {
								 // process.waitFor();
									Thread.sleep(1000);
								} catch (Exception e) {
								 System.out.println(e);
								}
								Waitdialog3.setVisible(false);
							  }
							}).start();
							Waitdialog3.setVisible(true);
							JOptionPane.showMessageDialog(frame,ProEndMesArray[4]);	
							Upanel[16].add(listpanel.CALLLISTPANEL());//將readtxt_3所回傳之JScrollPane加到Upanel[0]
							Upanel[16].revalidate();//Upanel[0]版面重新布局
							Upanel[16].repaint();//Upanel[0]版面重新繪製
							Dopanel[16].removeAll();//將先前所在Dopanel[0]的物件進行清空
							Dopanel[16].add(Hint_Label2[16]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
							D_HiBuArraybutton[20].setPreferredSize(new Dimension(190,88));//將"上一步"按鈕設定大小寬150，高60
							D_HiBuArraybutton[21].setPreferredSize(new Dimension(190,88));
							Dopanel[16].add(D_HiBuArraybutton[20]);//將"上一步"按鈕加入到Dopanel[0]
							D_HiBuArraybutton[20].setEnabled(false);
							Dopanel[16].add(D_HiBuArraybutton[21]);
							Dopanel[16].revalidate();//Dopanel[0]版面重新布局
							Dopanel[16].repaint();//Dopanel[0]版面重新繪製
							cardlayout1.show(panel[4],UPArray[16]);//將Upanel[0]顯示
							cardlayout2.show(panel[5],UPArray[16]);//將Dopanel[0]顯示	
						}
						
						
						
						}catch(Exception v){
							System.out.println("特徵選取錯誤");
						}
	/*----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
					//cortana = new Cortana(FileCH.CALLFILPATH());
				}else{
					JOptionPane.showMessageDialog(frame,"尚未選取檔案");
				}	
				
				
			}else if((JButton)e.getSource()==HiBuArraybutton[9]||(JButton)e.getSource()==D_HiBuArraybutton[21]){
				ImageIcon image=new ImageIcon(ClickbtArray[3]);
				button[3].setIcon(image);
				D_HiBuArraybutton[20].setEnabled(true);
				HiBuArraybutton[8].setEnabled(true);
				JOptionPane.showMessageDialog(frame,MesArray[3]);
				Fet_Flag=1;
				FetSav_Flag=0;
				System.out.println(listpanel.CALLFEATURENUM()+"---------------------------------123");
				ArrayList<Integer> Feature=new ArrayList<Integer>();
				Collections.sort(listpanel.addFeaturenum);
				Collections.sort(listpanel.defaFeaturenum);
				
				featuresave= new FeatureSave(listpanel.CALLFEATURENUM(),FileCH.CALLFILPATH(),FileCH.CALLOnlyName());
				
				//---------------------------------------------------------------------------------------------------------------
				
			}else if((JButton)e.getSource()==HiBuArraybutton[8]){
				
				Upanel[17].removeAll();//將先前所在Upanel[0]的物件進行清空
				readtxt12 = new readtxt_3(featuresave.getOutPath());//將使用者選取檔案路徑轉為字串並呼叫readtxt_3
				Upanel[17].add(readtxt12.CALLTABLE());//將readtxt_3所回傳之JScrollPane加到Upanel[0]
				Upanel[17].revalidate();//Upanel[0]版面重新布局
				Upanel[17].repaint();//Upanel[0]版面重新繪製
				
				Dopanel[17].removeAll();//將先前所在Dopanel[0]的物件進行清空
				Dopanel[17].add(Hint_Label2[17]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
				D_HiBuArraybutton[22].setPreferredSize(new Dimension(190,88));//將"上一步"按鈕設定大小寬150，高60
				Dopanel[17].add(D_HiBuArraybutton[22]);//將"上一步"按鈕加入到Dopanel[0]
				Dopanel[17].revalidate();//Dopanel[0]版面重新布局
				Dopanel[17].repaint();//Dopanel[0]版面重新繪製
				
				
				cardlayout1.show(panel[4],UPArray[17]);//將Upanel[0]顯示
				cardlayout2.show(panel[5],UPArray[17]);//將Dopanel[0]顯示
				
				System.out.println(FileCH.CALLFILPATH());
			}else if((JButton)e.getSource()==D_HiBuArraybutton[22]){
				HiBuArraybutton[8].setEnabled(true);
				
				cardlayout1.show(panel[4],CPArray[7]);//將Upanel[0]顯示
				cardlayout2.show(panel[5],CPArray[7]);//將Dopanel[0]顯示
			}else if((JButton)e.getSource()==D_HiBuArraybutton[20]){
				readtxt13 = new readtxt_3(featuresave.getOutPath());//將使用者選取檔案路徑轉為字串並呼叫readtxt_3
				Upanel[18].removeAll();//將先前所在Upanel[0]的物件進行清空
				Upanel[18].add(readtxt13.CALLTABLE());//將readtxt_3所回傳之JScrollPane加到Upanel[0]
				Upanel[18].revalidate();//Upanel[0]版面重新布局
				Upanel[18].repaint();//Upanel[0]版面重新繪製
				
				Dopanel[18].removeAll();//將先前所在Dopanel[0]的物件進行清空
				Dopanel[18].add(Hint_Label2[18]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
				D_HiBuArraybutton[23].setPreferredSize(new Dimension(190,88));//將"上一步"按鈕設定大小寬150，高60
				Dopanel[18].add(D_HiBuArraybutton[23]);//將"上一步"按鈕加入到Dopanel[0]
				Dopanel[18].revalidate();//Dopanel[0]版面重新布局
				Dopanel[18].repaint();//Dopanel[0]版面重新繪製
				
				
				cardlayout1.show(panel[4],UPArray[18]);//將Upanel[0]顯示
				cardlayout2.show(panel[5],UPArray[18]);//將Dopanel[0]顯示
				
				System.out.println(FileCH.CALLFILPATH());
			}else if((JButton)e.getSource()==D_HiBuArraybutton[23]){
				D_HiBuArraybutton[20].setEnabled(true);
				cardlayout1.show(panel[4],UPArray[16]);//將Upanel[0]顯示
				cardlayout2.show(panel[5],UPArray[16]);//將Dopanel[0]顯示
			}else if((JButton)e.getSource()==HiBuArraybutton[10]){
				readtxt14 = new readtxt_3(FileCH.CALLFILPATH());//將使用者選取檔案路徑轉為字串並呼叫readtxt_3
				Upanel[19].removeAll();//將先前所在Upanel[0]的物件進行清空
				Upanel[19].add(readtxt14.CALLTABLE());//將readtxt_3所回傳之JScrollPane加到Upanel[0]
				Upanel[19].revalidate();//Upanel[0]版面重新布局
				Upanel[19].repaint();//Upanel[0]版面重新繪製
				
				Dopanel[19].removeAll();//將先前所在Dopanel[0]的物件進行清空
				Dopanel[19].add(Hint_Label2[19]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
				D_HiBuArraybutton[24].setPreferredSize(new Dimension(190,88));//將"上一步"按鈕設定大小寬150，高60
				Dopanel[19].add(D_HiBuArraybutton[24]);//將"上一步"按鈕加入到Dopanel[0]
				Dopanel[19].revalidate();//Dopanel[0]版面重新布局
				Dopanel[19].repaint();//Dopanel[0]版面重新繪製
				
				
				cardlayout1.show(panel[4],UPArray[19]);//將Upanel[0]顯示
				cardlayout2.show(panel[5],UPArray[19]);//將Dopanel[0]顯示
				
				System.out.println(FileCH.CALLFILPATH());
				
			}else if((JButton)e.getSource()==D_HiBuArraybutton[24]){
				cardlayout1.show(panel[4],CPArray[8]);//將Cpanel[3]顯示
				cardlayout2.show(panel[5],CPArray[8]);//將CDpanel[3]顯示
				
			}else if((JButton)e.getSource()==HiBuArraybutton[11]){
				if(FileCH.IsClick()==1){
					readtxt15 = new readtxt_3(FileCH.CALLFILPATH());//將使用者選取檔案路徑轉為字串並呼叫readtxt_3
					Upanel[20].removeAll();//將先前所在Upanel[0]的物件進行清空
					Upanel[20].add(readtxt15.CALLTABLE());//將readtxt_3所回傳之JScrollPane加到Upanel[0]
					Upanel[20].revalidate();//Upanel[0]版面重新布局
					Upanel[20].repaint();//Upanel[0]版面重新繪製
					
					Dopanel[20].removeAll();//將先前所在Dopanel[0]的物件進行清空
					Dopanel[20].add(Hint_Label2[20]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
					D_HiBuArraybutton[25].setPreferredSize(new Dimension(190,88));//將"上一步"按鈕設定大小寬150，高60
					Dopanel[20].add(D_HiBuArraybutton[25]);//將"上一步"按鈕加入到Dopanel[0]
					Dopanel[20].revalidate();//Dopanel[0]版面重新布局
					Dopanel[20].repaint();//Dopanel[0]版面重新繪製
					
					
					cardlayout1.show(panel[4],UPArray[20]);//將Upanel[0]顯示
					cardlayout2.show(panel[5],UPArray[20]);//將Dopanel[0]顯示
					
					System.out.println(FileCH.CALLFILPATH());
				}else{
					JOptionPane.showMessageDialog(frame,"尚未選取檔案以致無法顯示");
				}
				
			}else if((JButton)e.getSource()==D_HiBuArraybutton[25]){
				cardlayout1.show(panel[4],CPArray[10]);//將Cpanel[3]顯示
				cardlayout2.show(panel[5],CPArray[10]);//將CDpanel[3]顯示
				
			}else if((JButton)e.getSource()==HiBuArraybutton[12]){
				if(FileCH.IsClick()==1){
					//Object[] options = {"隨機森林","倒傳遞神經網路","子群組探勘統計方法分析"};
					Object[] options = {"隨機森林","子群組探勘統計方法分析"};
					
					int mType=JOptionPane.QUESTION_MESSAGE;
					int oType=JOptionPane.YES_NO_CANCEL_OPTION;
					
					int opt2=JOptionPane.showOptionDialog(panel[3], "請選擇要創建的模型","請選取",oType,mType,null,options,options[0]);
					System.out.println("測試路徑--------------------"+FileCH.CALLFILPATH());
					
					if(opt2==0){
						try{
							FileWriter fw = new FileWriter("javatoc");
							data_cuwachfe_path=FileCH.CALLFILPATH();//s是檔案路徑
							fw.write(data_cuwachfe_path);
							fw.flush();
							fw.close();
								
							//呼叫外部程式
								
							/*跳出等待視窗 START*/
							JOptionPane WaitPanel3 =new JOptionPane(ProMesArray[3], JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
							JDialog Waitdialog3 = new JDialog();
							Waitdialog3.setModal(true);
							Waitdialog3.setContentPane(WaitPanel3);
									
							Waitdialog3.pack();
							Dimension screenSize3 = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
							Dimension GIFdialogSize = Waitdialog3.getSize();
							Waitdialog3.setLocation(screenSize3.width/2-GIFdialogSize.width/2,screenSize3.height/2-GIFdialogSize.height/2);
							Waitdialog3.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
							System.out.println("建模執行中--------------------");
								
							new Thread(new Runnable() {
								@Override
								public void run() {
								try {
									 //process.waitFor();
									RF_Cexe Rf_C_exe= new RF_Cexe();
									Thread.sleep(500);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								Waitdialog3.setVisible(false);
								}
							}).start();
							Waitdialog3.setVisible(true);
								/*跳出等待視窗 END*/
							JOptionPane.showMessageDialog(frame,ProEndMesArray[2]);
							ImageIcon image=new ImageIcon(ClickbtArray[4]);
							button[4].setIcon(image);
							BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File("ctojava")))); 
							fetxarea = new testtextarea (bf.readLine());
						}catch(Exception v){
							System.out.println(v);
						}
									
									
							
					}else if(opt2==1){
						System.out.println("給冠宏建模的路徑--------------------"+FileCH.CALLFILPATH().replace("Feature","Wa_Ch"));
							
						cortana2 = new Cortana2(FileCH.CALLFILPATH().replace("Feature","Wa_Ch"));
						try{
							//FileWriter fw = new FileWriter("javatoc");
							//data_cuwachfe_path=featuresave.getOutPath();//s是檔案路徑
							//fw.write(data_cuwachfe_path);
							//fw.flush();
							//fw.close();
								
							//呼叫外部程式
								
							/*跳出等待視窗 START*/
							JOptionPane WaitPanel3 =new JOptionPane(ProMesArray[3], JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
							JDialog Waitdialog3 = new JDialog();
							Waitdialog3.setModal(true);
							Waitdialog3.setContentPane(WaitPanel3);
									
							Waitdialog3.pack();
							Dimension screenSize3 = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
							Dimension GIFdialogSize = Waitdialog3.getSize();
							Waitdialog3.setLocation(screenSize3.width/2-GIFdialogSize.width/2,screenSize3.height/2-GIFdialogSize.height/2);
							Waitdialog3.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
							System.out.println("建模2執行中--------------------");
								
								
							new Thread(new Runnable() {
								@Override
								public void run() {
								try {
									//process.waitFor();
									  
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								Waitdialog3.setVisible(false);
								}
							}).start();
							Waitdialog3.setVisible(true);
							/*跳出等待視窗 END*/
							JOptionPane.showMessageDialog(frame,ProEndMesArray[2]);
							ImageIcon image=new ImageIcon(ClickbtArray[4]);
							button[4].setIcon(image);
							//System.out.println("最後冠宏回傳路徑---"+cortana2.getPath());
							fetxarea = new testtextarea (cortana2.getPath());
						}catch(Exception v){
							System.out.println(v);
						}
									
					}
					//JOptionPane.showMessageDialog(frame,MesArray[6]);
					 
					
					Upanel[21].removeAll();//將先前所在Upanel[0]的物件進行清空
					
					Upanel[21].add(Box.createRigidArea(new Dimension(30, 30)), BorderLayout.NORTH);
					Upanel[21].add(Box.createRigidArea(new Dimension(30, 30)), BorderLayout.EAST);
					Upanel[21].add(Box.createRigidArea(new Dimension(30, 30)), BorderLayout.SOUTH);
					Upanel[21].add(Box.createRigidArea(new Dimension(30, 30)), BorderLayout.WEST);
					Upanel[21].add(fetxarea.CALLTEXTAREA(), BorderLayout.CENTER);
					Upanel[21].revalidate();//Upanel[0]版面重新布局
					Upanel[21].repaint();//Upanel[0]版面重新繪製
					
					Dopanel[21].removeAll();//將先前所在Dopanel[0]的物件進行清空
					Dopanel[21].add(Hint_Label2[21]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
					Dopanel[21].revalidate();//Dopanel[0]版面重新布局
					Dopanel[21].repaint();//Dopanel[0]版面重新繪製
					
					
					cardlayout1.show(panel[4],UPArray[21]);//將Upanel[0]顯示
					cardlayout2.show(panel[5],UPArray[21]);//將Dopanel[0]顯示
					
					System.out.println(FileCH.CALLFILPATH());
					Mod_Flag=1;
				}else{
					JOptionPane.showMessageDialog(frame,"尚未選取檔案");
				}
					
			
				
			}else if((JButton)e.getSource()==D_HiBuArraybutton[26]){
				cardlayout1.show(panel[4],CPArray[17]);//將Cpanel[3]顯示
				cardlayout2.show(panel[5],CPArray[17]);//將CDpanel[3]顯示
			}else if((JButton)e.getSource()==D_HiBuArraybutton[27]){
				FileCH=new layouttest1(4);//將切割好的年份及檔案名稱顯示(0表示顯示切割的資料夾)
				Upanel[23].removeAll();//將先前所在Cpanel[2]的物件進行清空
				Upanel[23].add(FileCH.CALLFILECH());//將回傳切割資料夾內容加入到Cpanel[2]
				Upanel[23].revalidate();//Cpanel[2]版面重新布局
				Upanel[23].repaint();//Cpanel[2]版面重新繪製
						
				Dopanel[23].removeAll();//將先前所在CDpanel[2]的物件進行清空
				Dopanel[23].add(Hint_Label2[23]);//將提示訊息"確認完畢請按資料切割"加入到CDpanel[2]	
				D_HiBuArraybutton[28].setPreferredSize(new Dimension(190,88));
				D_HiBuArraybutton[29].setPreferredSize(new Dimension(190,88));//將"檢視"按鈕設定大小寬150，高60
				Dopanel[23].add(D_HiBuArraybutton[28]);//將"檢視"按鈕加入到CDpanel[2]
				Dopanel[23].add(D_HiBuArraybutton[29]);
				Dopanel[23].revalidate();//CDpanel[2]版面重新布局
				Dopanel[23].repaint();//CDpanel[2]版面重新繪製
			
				cardlayout1.show(panel[4],UPArray[23]);//將Cpanel[2]顯示
				cardlayout2.show(panel[5],UPArray[23]);//將CDanel[2]顯示
			}else if((JButton)e.getSource()==D_HiBuArraybutton[28]){
				if(FileCH.IsClick()==1){
					readtxt18 = new readtxt_3(FileCH.CALLFILPATH());//將使用者選取檔案路徑轉為字串並呼叫readtxt_3
					Upanel[24].removeAll();//將先前所在Upanel[0]的物件進行清空
					Upanel[24].add(readtxt18.CALLTABLE());//將readtxt_3所回傳之JScrollPane加到Upanel[0]
					Upanel[24].revalidate();//Upanel[0]版面重新布局
					Upanel[24].repaint();//Upanel[0]版面重新繪製
					
					Dopanel[24].removeAll();//將先前所在Dopanel[0]的物件進行清空
					Dopanel[24].add(Hint_Label2[24]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
					D_HiBuArraybutton[30].setPreferredSize(new Dimension(190,88));//將"上一步"按鈕設定大小寬150，高60
					D_HiBuArraybutton[31].setPreferredSize(new Dimension(190,88));
					Dopanel[24].add(D_HiBuArraybutton[30]);//將"上一步"按鈕加入到Dopanel[0]
					Dopanel[24].add(D_HiBuArraybutton[31]);
					Dopanel[24].revalidate();//Dopanel[0]版面重新布局
					Dopanel[24].repaint();//Dopanel[0]版面重新繪製
					
					
					cardlayout1.show(panel[4],UPArray[24]);//將Upanel[0]顯示
					cardlayout2.show(panel[5],UPArray[24]);//將Dopanel[0]顯示
					
					System.out.println(FileCH.CALLFILPATH());
				}else{
					JOptionPane.showMessageDialog(frame,"尚未選取檔案以致無法顯示");
				}
				
				
			}else if((JButton)e.getSource()==D_HiBuArraybutton[30]){
				cardlayout1.show(panel[4],UPArray[23]);//將Cpanel[2]顯示
				cardlayout2.show(panel[5],UPArray[23]);//將CDanel[2]顯示
				
			}else if((JButton)e.getSource()==D_HiBuArraybutton[31]){
				
				/*跳出等待視窗 START*/
				JOptionPane WaitPanel3 =new JOptionPane(ProMesArray[6], JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
				JDialog Waitdialog3 = new JDialog();
				Waitdialog3.setModal(true);
				Waitdialog3.setContentPane(WaitPanel3);
					
				Waitdialog3.pack();
				Dimension screenSize3 = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
				Dimension GIFdialogSize = Waitdialog3.getSize();
				Waitdialog3.setLocation(screenSize3.width/2-GIFdialogSize.width/2,screenSize3.height/2-GIFdialogSize.height/2);
				Waitdialog3.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				
				
				
				new Thread(new Runnable() {
				  @Override
				  public void run() {
					try {
					  //process.waitFor();
					  
					  Thread.sleep(5000);
					} catch (InterruptedException e) {
					  e.printStackTrace();
					}
					Waitdialog3.setVisible(false);
				  }
				}).start();
				Waitdialog3.setVisible(true);
				/*跳出等待視窗 END*/
			/*----------------------------------------------------------------------------------------------------------------*/	
				JOptionPane.showMessageDialog(frame,MesArray[14]);
				Eva_Flag=1;
				
				readtxt19 = new readtxt_3(FileCH.CALLFILPATH());//將使用者選取檔案路徑轉為字串並呼叫readtxt_3
				Upanel[25].removeAll();//將先前所在Upanel[0]的物件進行清空
				Upanel[25].add(readtxt19.CALLTABLE());//將readtxt_3所回傳之JScrollPane加到Upanel[0]
				Upanel[25].revalidate();//Upanel[0]版面重新布局
				Upanel[25].repaint();//Upanel[0]版面重新繪製
				
				Dopanel[25].removeAll();//將先前所在Dopanel[0]的物件進行清空
				Dopanel[25].add(Hint_Label2[25]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
				
				
				Dopanel[25].revalidate();//Dopanel[0]版面重新布局
				Dopanel[25].repaint();//Dopanel[0]版面重新繪製
				
				
				cardlayout1.show(panel[4],UPArray[25]);//將Upanel[0]顯示
				cardlayout2.show(panel[5],UPArray[25]);//將Dopanel[0]顯示
				
				System.out.println(FileCH.CALLFILPATH());
			}else if((JButton)e.getSource()==D_HiBuArraybutton[29]){
				/*跳出等待視窗 START*/
				JOptionPane WaitPanel3 =new JOptionPane(ProMesArray[6], JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
				JDialog Waitdialog3 = new JDialog();
				Waitdialog3.setModal(true);
				Waitdialog3.setContentPane(WaitPanel3);
					
				Waitdialog3.pack();
				Dimension screenSize3 = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
				Dimension GIFdialogSize = Waitdialog3.getSize();
				Waitdialog3.setLocation(screenSize3.width/2-GIFdialogSize.width/2,screenSize3.height/2-GIFdialogSize.height/2);
				Waitdialog3.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				
				
				
				new Thread(new Runnable() {
				  @Override
				  public void run() {
					try {
					  //process.waitFor();
					  
					  Thread.sleep(5000);
					} catch (InterruptedException e) {
					  e.printStackTrace();
					}
					Waitdialog3.setVisible(false);
				  }
				}).start();
				Waitdialog3.setVisible(true);
				/*跳出等待視窗 END*/
				/*----------------------------------------------------------------------------------------------------------------*/	
				JOptionPane.showMessageDialog(frame,MesArray[15]);
				Eva_Flag=1;
				readtxt20 = new readtxt_3(FileCH.CALLFILPATH());//將使用者選取檔案路徑轉為字串並呼叫readtxt_3
				Upanel[26].removeAll();//將先前所在Upanel[0]的物件進行清空
				Upanel[26].add(readtxt20.CALLTABLE());//將readtxt_3所回傳之JScrollPane加到Upanel[0]
				Upanel[26].revalidate();//Upanel[0]版面重新布局
				Upanel[26].repaint();//Upanel[0]版面重新繪製
				
				Dopanel[26].removeAll();//將先前所在Dopanel[0]的物件進行清空
				Dopanel[26].add(Hint_Label2[26]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
				
				
				Dopanel[26].revalidate();//Dopanel[0]版面重新布局
				Dopanel[26].repaint();//Dopanel[0]版面重新繪製
				
				
				cardlayout1.show(panel[4],UPArray[26]);//將Upanel[0]顯示
				cardlayout2.show(panel[5],UPArray[26]);//將Dopanel[0]顯示
				
				System.out.println(FileCH.CALLFILPATH());
	
			}else if((JButton)e.getSource()==D_HiBuArraybutton[32]){
				if(FileCH2.IsClick()==1){
					fetxarea = new testtextarea (FileCH2.CALLFILPATH());//將使用者選取檔案路徑轉為字串並呼叫readtxt_3
					Upanel[28].removeAll();//將先前所在Upanel[0]的物件進行清空
					Upanel[28].add(Box.createRigidArea(new Dimension(15, 15)), BorderLayout.NORTH);
					Upanel[28].add(Box.createRigidArea(new Dimension(15, 15)), BorderLayout.EAST);
					Upanel[28].add(Box.createRigidArea(new Dimension(15, 15)), BorderLayout.SOUTH);
					Upanel[28].add(Box.createRigidArea(new Dimension(15, 15)), BorderLayout.WEST);
					Upanel[28].add(fetxarea.CALLTEXTAREA());//將readtxt_3所回傳之JScrollPane加到Upanel[0]
					Upanel[28].revalidate();//Upanel[0]版面重新布局
					Upanel[28].repaint();//Upanel[0]版面重新繪製
					
					Dopanel[28].removeAll();//將先前所在Dopanel[0]的物件進行清空
					Dopanel[28].add(Hint_Label2[28]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
					D_HiBuArraybutton[34].setPreferredSize(new Dimension(190,88));//將"上一步"按鈕設定大小寬150，高60
					D_HiBuArraybutton[35].setPreferredSize(new Dimension(190,88));
					Dopanel[28].add(D_HiBuArraybutton[34]);//將"上一步"按鈕加入到Dopanel[0]
					Dopanel[28].add(D_HiBuArraybutton[35]);
					Dopanel[28].revalidate();//Dopanel[0]版面重新布局
					Dopanel[28].repaint();//Dopanel[0]版面重新繪製
					
					
					cardlayout1.show(panel[4],UPArray[28]);//將Upanel[0]顯示
					cardlayout2.show(panel[5],UPArray[28]);//將Dopanel[0]顯示
					
					//System.out.println(FileCH.CALLFILPATH());
				}else{
					JOptionPane.showMessageDialog(frame,"尚未選取檔案以致無法顯示");
				}
				
			}else if((JButton)e.getSource()==D_HiBuArraybutton[34]){
				cardlayout1.show(panel[4],CPArray[17]);//將Cpanel[2]顯示
				cardlayout2.show(panel[5],CPArray[17]);//將CDanel[2]顯示
				
			}else if((JButton)e.getSource()==D_HiBuArraybutton[35]){
				System.out.println("ru.4g45k4u4");
				/*跳出等待視窗 START*/
				JOptionPane WaitPanel3 =new JOptionPane(ProMesArray[6], JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
				JDialog Waitdialog3 = new JDialog();
				Waitdialog3.setModal(true);
				Waitdialog3.setContentPane(WaitPanel3);
					
				Waitdialog3.pack();
				Dimension screenSize3 = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
				Dimension GIFdialogSize = Waitdialog3.getSize();
				Waitdialog3.setLocation(screenSize3.width/2-GIFdialogSize.width/2,screenSize3.height/2-GIFdialogSize.height/2);
				Waitdialog3.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				
				
				new Thread(new Runnable() {
				  @Override
				  public void run() {
					try {
					  //process.waitFor();
					  
					  //Thread.sleep(5000);
					  /*將使用者所選檔案轉成模型檔寫入javatoc2*/
						String predicttofun=null;
						String predictosee=null;
						predicttofun=FileCH2.CALLFILPATH();
						predictosee=FileCH2.CALLFILPATH();
						if(predicttofun.contains("_RF")){
							predicttofun=predicttofun.replace(".txt", ".pkl");
						}else if(predicttofun.contains("_SubDisc")){
							predicttofun=predicttofun.replace(".txt", ".txt");
						}else if(predicttofun.contains("_BPNN")){
							predicttofun=predicttofun.replace(".txt", ".h5");
						}
						
						
						FileWriter fw = new FileWriter("javatoc2");
						fw.write(predicttofun);
						fw.flush();
						fw.close();
						
						
						//System.out.println("傳給各位的路徑名稱1 ： "+predicttofun);
						
						/*將使用者所選檔案轉成模型檔寫入javatoc2*/
						
						/*資料清洗與轉換呼叫外部程式寫檔*/
						BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File("ctojava"))));  
						String cuttowa = bf.readLine();
						//System.out.println("選擇傳給各位的路徑名稱2 ： "+cuttowa);
						String Modeljud=cuttowa+FileCH2.CALLOnlyName();
						
						//String Modeljud="Hos_Data\\Doc_Test\\Cut\\2020\\"+FileCH2.CALLOnlyName();
						FileWriter fw2 = new FileWriter("javatoc");
						if(Modeljud.contains("_RF")){
							data_cuwachfe_path=Modeljud.replace("_RF.txt", ".csv");
						}else if(Modeljud.contains("_SubDisc")){
							data_cuwachfe_path=Modeljud.replace("_SubDisc.txt", ".csv");
						}else if(Modeljud.contains("_BPNN")){
							data_cuwachfe_path=Modeljud.replace("_BPNN.txt", ".csv");
						}
						//System.out.println("測試檔名路徑為3"+data_cuwachfe_path);
							
						fw2.write(data_cuwachfe_path);
						fw2.flush();
						fw2.close();
										
						//呼叫外部程式
						Runtime runtime=Runtime.getRuntime();             
						Process process = runtime.exec("Data_TransForm_P2_1.exe");//file為執行外部程式的檔案名稱             
						//System.out.println("清洗與轉換2執行中--------------------");
						process.waitFor();
							
						
						/*特徵選取儲存檔案*/
						String FeaTuresave1=null;
						String FeaTuresave2=null;
						if(predicttofun.contains("_RF.pkl")){
							FeaTuresave1=predicttofun.replace("Model", "Feature").replace("_RF.pkl", ".txt");
						}else if(predicttofun.contains("_SubDisc.txt")){
							FeaTuresave1=predicttofun.replace("Model", "Feature").replace("_SubDisc.txt", ".txt");
						}else if(predicttofun.contains("_BPNN.h5")){
							FeaTuresave1=predicttofun.replace("Model", "Feature").replace("_BPNN.h5", ".txt");
						}
						
						FeaTuresave2=data_cuwachfe_path.replace("Cut", "Wa_Ch");
						//System.out.println("傳給FEATURESAVE14 "+FeaTuresave1);
						//System.out.println("傳給FEATURESAVE25 "+FeaTuresave2);
						featuresave2 = new FeatureSave2(FeaTuresave2,FeaTuresave1);
						
						
						FileWriter fw3 = new FileWriter("javatoc");
						//System.out.println("FEATURESAVE2傳回來的路徑6 "+featuresave2.getOutPath());
						fw3.write(featuresave2.getOutPath());
						fw3.flush();
						fw3.close();
						
						/*特徵選取儲存檔案*/
						if(Modeljud.contains("_RF")){
							RFPredictexe Rfpredict = new RFPredictexe();
						}else if(Modeljud.contains("_SubDisc")){
							Runtime runtime2=Runtime.getRuntime();             
							Process process2 = runtime2.exec("SubGroup_Analysis_Sys.exe");							
						}else if(Modeljud.contains("_BPNN")){
							BPNN_Lexe BPpredict= new BPNN_Lexe();
						}
						
						
					} catch (Exception i) {
						System.out.println(i);
					  //e.printStackTrace();
					}
					Waitdialog3.setVisible(false);
				  }
				}).start();
				Waitdialog3.setVisible(true);
				/*跳出等待視窗 END*/
				
				/*----------------------------------------------------------------------------------------------------------------*/	
				JOptionPane.showMessageDialog(frame,MesArray[16]);
				Eva_Flag=1;
				String predict_file=null;
				try{
					BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File("ctojava"))));  
					//System.out.println("模型ctojava "+bf.readLine());
					predict_file=bf.readLine().toString();
					

				}catch(Exception i){
					System.out.println(i);
				}
				readtxt21 = new readtxt_3(predict_file);//將使用者選取檔案路徑轉為字串並呼叫readtxt_3
				Upanel[29].removeAll();//將先前所在Upanel[0]的物件進行清空
				Upanel[29].add(readtxt21.CALLTABLE());//將readtxt_3所回傳之JScrollPane加到Upanel[0]
				Upanel[29].revalidate();//Upanel[0]版面重新布局
				Upanel[29].repaint();//Upanel[0]版面重新繪製
				
				Dopanel[29].removeAll();//將先前所在Dopanel[0]的物件進行清空
				Dopanel[29].add(Hint_Label2[29]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
				
				
				Dopanel[29].revalidate();//Dopanel[0]版面重新布局
				Dopanel[29].repaint();//Dopanel[0]版面重新繪製
				ImageIcon image=new ImageIcon(ClickbtArray[5]);
				button[5].setIcon(image);
				
				cardlayout1.show(panel[4],UPArray[29]);//將Upanel[0]顯示
				cardlayout2.show(panel[5],UPArray[29]);//將Dopanel[0]顯示
				
				System.out.println(FileCH.CALLFILPATH());
				
			}else if((JButton)e.getSource()==D_HiBuArraybutton[33]){
				/*跳出等待視窗 START*/
				if(FileCH2.IsClick()==1){
					JOptionPane WaitPanel3 =new JOptionPane(ProMesArray[6], JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
					JDialog Waitdialog3 = new JDialog();
					Waitdialog3.setModal(true);
					Waitdialog3.setContentPane(WaitPanel3);
						
					Waitdialog3.pack();
					Dimension screenSize3 = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
					Dimension GIFdialogSize = Waitdialog3.getSize();
					Waitdialog3.setLocation(screenSize3.width/2-GIFdialogSize.width/2,screenSize3.height/2-GIFdialogSize.height/2);
					Waitdialog3.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
					
					
					
					new Thread(new Runnable() {
					  @Override
					  public void run() {
						try {
						  //process.waitFor();
						  
						  //Thread.sleep(5000);
						  /*將使用者所選檔案轉成模型檔寫入javatoc2*/
							String predicttofun=null;
							String predictosee=null;
							predicttofun=FileCH2.CALLFILPATH();
							predictosee=FileCH2.CALLFILPATH();
							if(predicttofun.contains("_RF")){
								predicttofun=predicttofun.replace(".txt", ".pkl");
							}else if(predicttofun.contains("_SubDisc")){
								predicttofun=predicttofun.replace(".txt", ".txt");
							}else if(predicttofun.contains("_BPNN")){
								predicttofun=predicttofun.replace(".txt", ".h5");
							}
							
							
							FileWriter fw = new FileWriter("javatoc2");
							fw.write(predicttofun);
							fw.flush();
							fw.close();
							
							
							//System.out.println("傳給各位的路徑名稱1 ： "+predicttofun);
							
							/*將使用者所選檔案轉成模型檔寫入javatoc2*/
							
							/*資料清洗與轉換呼叫外部程式寫檔*/
							BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File("ctojava"))));  
							String cuttowa = bf.readLine();
							//System.out.println("選擇傳給各位的路徑名稱2 ： "+cuttowa);
							String Modeljud=cuttowa+FileCH2.CALLOnlyName();
							
							//String Modeljud="Hos_Data\\Doc_Test\\Cut\\2020\\"+FileCH2.CALLOnlyName();
							FileWriter fw2 = new FileWriter("javatoc");
							if(Modeljud.contains("_RF")){
								data_cuwachfe_path=Modeljud.replace("_RF.txt", ".csv");
							}else if(Modeljud.contains("_SubDisc")){
								data_cuwachfe_path=Modeljud.replace("_SubDisc.txt", ".csv");
							}else if(Modeljud.contains("_BPNN")){
								data_cuwachfe_path=Modeljud.replace("_BPNN.txt", ".csv");
							}
							//System.out.println("測試檔名路徑為3"+data_cuwachfe_path);
								
							fw2.write(data_cuwachfe_path);
							fw2.flush();
							fw2.close();
											
							//呼叫外部程式
							Runtime runtime=Runtime.getRuntime();             
							Process process = runtime.exec("Data_TransForm_P2_1.exe");//file為執行外部程式的檔案名稱             
							//System.out.println("清洗與轉換2執行中--------------------");
							process.waitFor();
								
							
							/*特徵選取儲存檔案*/
							String FeaTuresave1=null;
							String FeaTuresave2=null;
							if(predicttofun.contains("_RF.pkl")){
								FeaTuresave1=predicttofun.replace("Model", "Feature").replace("_RF.pkl", ".txt");
							}else if(predicttofun.contains("_SubDisc.txt")){
								FeaTuresave1=predicttofun.replace("Model", "Feature").replace("_SubDisc.txt", ".txt");
							}else if(predicttofun.contains("_BPNN.h5")){
								FeaTuresave1=predicttofun.replace("Model", "Feature").replace("_BPNN.h5", ".txt");
							}
							
							FeaTuresave2=data_cuwachfe_path.replace("Cut", "Wa_Ch");
							//System.out.println("傳給FEATURESAVE14 "+FeaTuresave1);
							//System.out.println("傳給FEATURESAVE25 "+FeaTuresave2);
							featuresave2 = new FeatureSave2(FeaTuresave2,FeaTuresave1);
							
							
							FileWriter fw3 = new FileWriter("javatoc");
							//System.out.println("FEATURESAVE2傳回來的路徑6 "+featuresave2.getOutPath());
							fw3.write(featuresave2.getOutPath());
							fw3.flush();
							fw3.close();
							
							/*特徵選取儲存檔案*/
							if(Modeljud.contains("_RF")){
								RFPredictexe Rfpredict = new RFPredictexe();
							}else if(Modeljud.contains("_SubDisc")){
								Runtime runtime2=Runtime.getRuntime();             
								Process process2 = runtime2.exec("SubGroup_Analysis_Sys.exe");							
							}else if(Modeljud.contains("_BPNN")){
								BPNN_Lexe BPpredict= new BPNN_Lexe();
							}
							
							
						} catch (Exception i) {
							System.out.println(i);
						  //e.printStackTrace();
						}
						Waitdialog3.setVisible(false);
					  }
					}).start();
					Waitdialog3.setVisible(true);
					/*跳出等待視窗 END*/
					
					
					
					JOptionPane.showMessageDialog(frame,MesArray[17]);
					Eva_Flag=1;
					
					
					/*----------------------------------------------------------------------------------------------------------------*/	
					String predict_file=null;
					try{
						BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File("ctojava"))));  
						//System.out.println("模型ctojava "+bf.readLine());
						predict_file=bf.readLine().toString();
						
			
					}catch(Exception i){
						System.out.println(i);
					}
					
					//將使用者選取檔案路徑轉為字串並呼叫readtxt_3
					//System.out.println("列印 "+predict_file);
					ImageIcon image=new ImageIcon(ClickbtArray[5]);
					button[5].setIcon(image);
					readtxt22 = new readtxt_3(predict_file); 
					Upanel[30].removeAll();//將先前所在Upanel[0]的物件進行清空
					Upanel[30].add(readtxt22.CALLTABLE());//將readtxt_3所回傳之JScrollPane加到Upanel[0]
					Upanel[30].revalidate();//Upanel[0]版面重新布局
					Upanel[30].repaint();//Upanel[0]版面重新繪製
					
					Dopanel[30].removeAll();//將先前所在Dopanel[0]的物件進行清空
					Dopanel[30].add(Hint_Label2[30]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
					
					
					Dopanel[30].revalidate();//Dopanel[0]版面重新布局
					Dopanel[30].repaint();//Dopanel[0]版面重新繪製
					
					
					cardlayout1.show(panel[4],UPArray[30]);//將Upanel[0]顯示
					cardlayout2.show(panel[5],UPArray[30]);//將Dopanel[0]顯示
				}else{
					JOptionPane.showMessageDialog(frame,"尚未選取檔案");
				}
			
			}else if((JButton)e.getSource()==HiBuArraybutton[15]){
				if(FileCH.IsClick()==1){
					readtxt23 = new readtxt_3(FileCH.CALLFILPATH());//將使用者選取檔案路徑轉為字串並呼叫readtxt_3
					Upanel[31].removeAll();//將先前所在Upanel[0]的物件進行清空
					Upanel[31].add(readtxt23.CALLTABLE());//將readtxt_3所回傳之JScrollPane加到Upanel[0]
					Upanel[31].revalidate();//Upanel[0]版面重新布局
					Upanel[31].repaint();//Upanel[0]版面重新繪製
					
					Dopanel[31].removeAll();//將先前所在Dopanel[0]的物件進行清空
					Dopanel[31].add(Hint_Label2[31]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
					
					D_HiBuArraybutton[36].setPreferredSize(new Dimension(190,88));
					Dopanel[31].add(D_HiBuArraybutton[36]);//將"上一步"按鈕加入到Dopanel[0]
					Dopanel[31].revalidate();//Dopanel[0]版面重新布局
					Dopanel[31].repaint();//Dopanel[0]版面重新繪製
					
					
					cardlayout1.show(panel[4],UPArray[31]);//將Upanel[0]顯示
					cardlayout2.show(panel[5],UPArray[31]);//將Dopanel[0]顯示
					
					System.out.println(FileCH.CALLFILPATH());
				}else{
					JOptionPane.showMessageDialog(frame,"尚未選取檔案以致無法顯示");
				}
			}else if((JButton)e.getSource()==D_HiBuArraybutton[36]){
				cardlayout1.show(panel[4],CPArray[22]);//將Cpanel[3]顯示
				cardlayout2.show(panel[5],CPArray[22]);//將CDpanel[3]顯示
			}else if((JButton)e.getSource()==HiBuArraybutton[16]){
				if(FileCH.IsClick()==1){
					String Queue=null;
					String waittime=null;
					String idle=null;
					String doctor_code=null;
					try{
						
						FileWriter fw = new FileWriter("javatoc");
						
						System.out.println("模型結果檔名路徑為"+FileCH.CALLFILPATH());
						
						
						fw.write(FileCH.CALLFILPATH());
						fw.flush();
						fw.close();
						
					}catch(Exception v){
						System.out.println(v);
					}
					try {
						//呼叫外部程式
						Runtime runtime=Runtime.getRuntime();             
						Process process = runtime.exec("Effect_Comparsion_Sys.exe");//file為執行外部程式的檔案名稱             
						System.out.println("效能比較--------------------");
						/*跳出等待視窗 START*/
							
							
						JOptionPane WaitPanel =new JOptionPane(ProMesArray[5], JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
						//WaitPanel.setFont(softfont[0]);
						JDialog Waitdialog = new JDialog();
						Waitdialog.setModal(true);
						Waitdialog.setContentPane(WaitPanel);
							
						Waitdialog.pack();
						Dimension screenSize2 = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
						Dimension GIFdialogSize = Waitdialog.getSize();
						Waitdialog.setLocation(screenSize2.width/2-GIFdialogSize.width/2,screenSize2.height/2-GIFdialogSize.height/2);
						Waitdialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
						System.out.println("畫圖執行中--------------------");
							
						new Thread(new Runnable() {
						  @Override
						  public void run() {
							try {
							  process.waitFor();
							  //Thread.sleep(5000);
							} catch (InterruptedException e) {
							  e.printStackTrace();
							}
							Waitdialog.setVisible(false);
						  }
						}).start();
						Waitdialog.setVisible(true);
						JOptionPane.showMessageDialog(frame,ProEndMesArray[5]);
						/*跳出等待視窗 END*/
						BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File("ctojava2"))));          
										
					
						Queue = bf.readLine();
						System.out.println("有讀取1 "+Queue);
						waittime = bf.readLine();
						System.out.println("有讀取2 "+waittime);
						idle = bf.readLine();
						System.out.println("有讀取3 "+idle);
						doctor_code= bf.readLine();
						System.out.println("有讀取4 "+doctor_code);
				  
					} catch (Exception k) {
							System.out.println(k);
					} 		
					
					piclayout2 = new layouttest6_6_2("test",Queue,waittime,idle,doctor_code);
					
					Upanel[32].removeAll();//將先前所在Upanel[0]的物件進行清空
					Upanel[32].setLayout(new BoxLayout(Upanel[32], BoxLayout.Y_AXIS));
					Upanel[32].add(piclayout2.CALLPIC());//將readtxt_3所回傳之JScrollPane加到Upanel[0]
					
					Upanel[32].revalidate();//Upanel[0]版面重新布局
					Upanel[32].repaint();//Upanel[0]版面重新繪製
					
					Dopanel[32].removeAll();//將先前所在Dopanel[0]的物件進行清空
					Dopanel[32].add(Hint_Label2[32]);//將提示訊息"確認完畢請按資料切割"加入到Dopanel[0]
					
					
					Dopanel[32].revalidate();//Dopanel[0]版面重新布局
					Dopanel[32].repaint();//Dopanel[0]版面重新繪製
					
					
					cardlayout1.show(panel[4],UPArray[32]);//將Upanel[0]顯示
					cardlayout2.show(panel[5],UPArray[32]);//將Dopanel[0]顯示
					ImageIcon image=new ImageIcon(ClickbtArray[6]);
					button[6].setIcon(image);
				}else{
					JOptionPane.showMessageDialog(frame,"尚未選取檔案");
				}
				
				
			}else if((JButton)e.getSource()==HiBuArraybutton[17]){
				
				System.out.println(allinone.getRadioB());
				System.out.println(allinone.getRadioB2());
				System.out.println(allinone.getORIFILE());
				System.out.println(allinone.getPREFILE());
				Object[] options = {"確定","取消"};
					
				int mType=JOptionPane.QUESTION_MESSAGE;
				int oType=JOptionPane.YES_NO_CANCEL_OPTION;
				int opt=JOptionPane.showOptionDialog(frame,"原始檔案："+allinone.getORIFILE()+"\n模型方法："+allinone.getRadioB()+"\n特徵方法："+allinone.getRadioB2()+"\n預測檔案："+allinone.getPREFILE(),"請確認",oType,mType,null,options,options[0]);
				if(opt==0){
					
					JOptionPane AllWaitPanel =new JOptionPane("正在進行一鍵處理中\n請稍候...",JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
					AllWaitPanel.setFont(softfont[0]);
					JDialog AllWaitdialog = new JDialog();
					AllWaitdialog.setModal(true);
					AllWaitdialog.setContentPane(AllWaitPanel);
							
					AllWaitdialog.pack();
					Dimension screenSize2 = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
					Dimension GIFdialogSize = AllWaitdialog.getSize();
					AllWaitdialog.setLocation(screenSize2.width/2-GIFdialogSize.width/2,screenSize2.height/2-GIFdialogSize.height/2);
					AllWaitdialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
					
					/*資料切割呼叫外部程式寫檔*/
					try{
						FileWriter fw = new FileWriter("javatoc");
						data_cuwachfe_path=allinone.getORIFILE();//s是檔案路徑
						fw.write(data_cuwachfe_path);
						fw.flush();
						fw.close();
							
						//呼叫外部程式
						Runtime runtime=Runtime.getRuntime();             
						Process process = runtime.exec("Data_Cut_End2.exe");//file為執行外部程式的檔案名稱             
						System.out.println("切割執行中--------------------");
						
						
						
						Thread threadA = new Thread(new Runnable() { 
							@Override
							public void run() {
								try {
									process.waitFor();
									//Thread.sleep(5000);
									System.out.println("切割執行完畢--------------------");
																
								}catch(Exception v){
									System.out.println("切割執行錯誤");
								}
								//AllWaitdialog.setVisible(false);
								//ImageIcon image=new ImageIcon(ClickbtArray[7]);
								//button[7].setIcon(image);
								AllWaitdialog.setVisible(false);
							}
						});
						
						threadA.start();
						AllWaitdialog.setVisible(true);
						//AllWaitdialog.setVisible(true);
						//JOptionPane.showMessageDialog(frame,"一鍵處理流程進行完畢");
						
						try{
							System.out.println("Current Thread: "+ Thread.currentThread().getName());
							
							threadA.join();
							Dacu_Flag=1;	
						}catch(Exception ex){
							System.out.println("Exception has " +"been caught" + ex);
						}
						
	 
	
						BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File("ctojava"))));
						FileWriter fw2 = new FileWriter("javatoc");
						fw2.write(bf.readLine().toString());
						bf.close();
						fw2.flush();
						fw2.close();
							
						//呼叫外部程式
						Runtime runtime2=Runtime.getRuntime();             
						Process process2 = runtime2.exec("Data_TransForm_All_1.exe");//file為執行外部程式的檔案名稱             
						System.out.println("清洗與轉換執行中--------------------");
						
						Thread threadB = new Thread(new Runnable() { 
							@Override
							public void run() {
								try {
									InputStream stderr = process2.getErrorStream();
									InputStreamReader isr = new InputStreamReader(stderr);
									BufferedReader br = new BufferedReader(isr);
									String line = null;
									while((line = br.readLine()) != null){
										//System.out.println(line);
										
									}
									process2.waitFor();
									//Thread.sleep(5000);
									br.close();
									
								}catch(Exception v){
									System.out.println("清洗與轉換執行錯誤");
								}
								AllWaitdialog.setVisible(false);
							}
						});
						
						
						threadB.start();
						AllWaitdialog.setVisible(true);
						try{
							System.out.println("Current Thread2: "+ Thread.currentThread().getName());
							threadB.join();
							System.out.println("清洗與轉換執行完畢--------------------");
							Watr_Flag=1;
							
						}
				  
						catch(Exception ex){
							System.out.println("Exception has " +"been caught" + ex);
						}	
						BufferedReader bf2 = new BufferedReader(new InputStreamReader(new FileInputStream(new File("ctojava"))));
						FileWriter fw3 = new FileWriter("javatoc");
						fw3.write(bf2.readLine().toString());
						bf2.close();
						fw3.flush();
						fw3.close();
						if(allinone.getRadioB2()=="卡方檢驗"){
							System.out.println("特徵選取(卡方檢驗)執行中--------------------");
							Thread threadC = new Thread(new Runnable() { 
							@Override
								public void run() {
									try {	
										ALLINONE_CHi2_All_javatoc CHi2_All_javatoc = new ALLINONE_CHi2_All_javatoc();
		
									} catch (Exception i) {
										System.out.println(i);
									  
									}
									AllWaitdialog.setVisible(false);
								}
							});
							threadC.start();
							AllWaitdialog.setVisible(true);
							try{
								System.out.println("Current Thread3: "+ Thread.currentThread().getName());
								threadC.join();
								System.out.println("特徵選取(卡方檢驗)執行完畢--------------------");
								
							}
					  
							catch(Exception ex){
								System.out.println("Exception has " +"been caught" + ex);
							}	
							
						}else if(allinone.getRadioB2()=="方差過濾"){
							System.out.println("特徵選取(方差過濾)執行中--------------------");
							Thread threadC = new Thread(new Runnable() { 
							@Override
								public void run() {
									try {	
										ALLINONE_VarianceThresholdall_Test3 VarianceThresholdall_Test3 = new ALLINONE_VarianceThresholdall_Test3();
									} catch (Exception i) {
										System.out.println(i);
									  
									}
									AllWaitdialog.setVisible(false);
								}
							});
							threadC.start();
							AllWaitdialog.setVisible(true);
							try{
								System.out.println("Current Thread3: "+ Thread.currentThread().getName());
								threadC.join();
								System.out.println("特徵選取(方差過濾)執行完畢--------------------");
								
								
							}
					  
							catch(Exception ex){
								System.out.println("Exception has " +"been caught" + ex);
							}	
							
						
						}else if(allinone.getRadioB2()=="以原始資料為主"){
							System.out.println("特徵選取(以原始資料為主)");
							
						}else{
							System.out.println("特徵未選取");
							
						}
						
						String Pre_FeaturesaveAll_Path=null;
						if(allinone.getRadioB2()=="以原始資料為主"){
							FeatureSave_All_Ori FeaturesaveAll_Ori = new FeatureSave_All_Ori();
							System.out.println("featuresave回傳的路徑"+FeaturesaveAll_Ori.getOutPath());
							Pre_FeaturesaveAll_Path = FeaturesaveAll_Ori.getOutPath();
							FileWriter fw4 = new FileWriter("javatoc");
							fw4.write(FeaturesaveAll_Ori.getOutPath());
							fw4.flush();
							fw4.close();
							Fet_Flag=1;
						}else if(allinone.getRadioB2()=="無須選擇"){
							System.out.println("特徵無須選擇");
							Fet_Flag=1;
						}else{
							FeatureSave_All FeaturesaveAll = new FeatureSave_All();
							System.out.println("featuresave回傳的路徑"+FeaturesaveAll.getOutPath());
							Pre_FeaturesaveAll_Path = FeaturesaveAll.getOutPath();
							FileWriter fw4 = new FileWriter("javatoc");
							fw4.write(FeaturesaveAll.getOutPath());
							fw4.flush();
							fw4.close();
							Fet_Flag=1;
						}
						
						
						
						
						//-------------------------------------------------------------------------------------------------------------------
						
						
						if(allinone.getRadioB()=="隨機森林"){
							System.out.println("建立模型(隨機森林)執行中--------------------");
							Thread threadD = new Thread(new Runnable() { 
							@Override
								public void run() {
									try {	
										
										ALLINONE_RF_CeeatAll RF_CeeatAll = new ALLINONE_RF_CeeatAll();
									
									} catch (Exception i) {
										System.out.println(i);
									  
									}
									AllWaitdialog.setVisible(false);
								}
							});
							threadD.start();
							AllWaitdialog.setVisible(true);
							try{
								System.out.println("Current Thread4: "+ Thread.currentThread().getName());
								threadD.join();
								System.out.println("建立模型(隨機森林)執行完畢--------------------");
								Mod_Flag=1;
								Sta_Flag=4;
								
							}
					  
							catch(Exception ex){
								System.out.println("Exception has " +"been caught" + ex);
							}	
	
						}else if(allinone.getRadioB()=="子群組探勘統計方法分析"){
							System.out.println("建立模型(子群組)執行中--------------------");
							Thread threadD = new Thread(new Runnable() { 
							@Override
								public void run() {
									try {	
										
										ALLINONE_Cortana2_All Cortana_All = new ALLINONE_Cortana2_All();
									
									} catch (Exception i) {
										System.out.println(i);
									  
									}
									AllWaitdialog.setVisible(false);
								}
							});
							threadD.start();
							AllWaitdialog.setVisible(true);
							try{
								System.out.println("Current Thread4: "+ Thread.currentThread().getName());
								threadD.join();
								System.out.println("建立模型(子群組)執行完畢--------------------");
								Mod_Flag=1;
								Sta_Flag=4;
								
							}
					  
							catch(Exception ex){
								System.out.println("Exception has " +"been caught" + ex);
							}	
							
						}else{
							System.out.println("模型方法未選取");
						}
						
						FileWriter fw5 = new FileWriter("javatoc");
						data_cuwachfe_path=allinone.getPREFILE();//s是檔案路徑
						fw5.write(data_cuwachfe_path.toString());
						fw5.flush();
						fw5.close();
						
						//呼叫外部程式
						Runtime runtime3=Runtime.getRuntime();             
						Process process3 = runtime3.exec("Data_Cut_End3.exe");//file為執行外部程式的檔案名稱             
						System.out.println("預測檔切割執行中--------------------");
						
						
						
						Thread threadE = new Thread(new Runnable() { 
							@Override
							public void run() {
								try {
									process3.waitFor();
									//Thread.sleep(5000);
									System.out.println("預測檔切割執行完畢--------------------");
																	
								}catch(Exception v){
									System.out.println("預測檔切割執行錯誤");
								}
								AllWaitdialog.setVisible(false);
							}
						});
						threadE.start();
						AllWaitdialog.setVisible(true);
						
						try{
							System.out.println("Current Thread5: "+ Thread.currentThread().getName());
							
							threadE.join();
						}catch(Exception ex){
							System.out.println("Exception has " +"been caught" + ex);
						}
						
						BufferedReader bf3 = new BufferedReader(new InputStreamReader(new FileInputStream(new File("ctojava"))));
						FileWriter fw6 = new FileWriter("javatoc");
						fw6.write(bf3.readLine().toString());
						bf3.close();
						fw6.flush();
						fw6.close();
						
						//呼叫外部程式
						Runtime runtime4=Runtime.getRuntime();             
						Process process4 = runtime4.exec("Data_TransForm_All2_1.exe");//file為執行外部程式的檔案名稱             
						System.out.println("預測檔清洗與轉換執行中--------------------");
						
						Thread threadF = new Thread(new Runnable() { 
							@Override
							public void run() {
								try {
									InputStream stderr2 = process4.getErrorStream();
									InputStreamReader isr2 = new InputStreamReader(stderr2);
									BufferedReader br2 = new BufferedReader(isr2);
									String line = null;
									while((line = br2.readLine()) != null){
										//System.out.println(line);
										
									}
									process4.waitFor();
									//Thread.sleep(5000);
									br2.close();
									
								}catch(Exception v){
									System.out.println("預測檔清洗與轉換執行錯誤");
								}
								AllWaitdialog.setVisible(false);
							}
						});
						
						
						threadF.start();
						AllWaitdialog.setVisible(true);
						
						try{
							System.out.println("Current Thread6: "+ Thread.currentThread().getName());
							threadF.join();
							System.out.println("預測檔清洗與轉換執行完畢--------------------");
							
						}
				  
						catch(Exception ex){
							System.out.println("Exception has " +"been caught" + ex);
						}	
						
						BufferedReader bf4 = new BufferedReader(new InputStreamReader(new FileInputStream(new File("ctojava"))));
						FileWriter fw7 = new FileWriter("javatoc");
						//System.out.println("清洗給特徵 "+bf4.readLine().toString());
						fw7.write(bf4.readLine().toString());
						bf4.close();
						fw7.flush();
						fw7.close();
						if(allinone.getRadioB2()!="無須選擇"){
							
							System.out.println("丟給FeatureSave2_All "+Pre_FeaturesaveAll_Path);
							FeatureSave2_All featureSave2All = new FeatureSave2_All(Pre_FeaturesaveAll_Path);
							
							FileWriter fw8 = new FileWriter("javatoc");
							System.out.println("featureSave2All回傳結果 "+featureSave2All.getOutPath());
							fw8.write(featureSave2All.getOutPath());
							fw8.flush();
							fw8.close();
						}

		
						if(allinone.getRadioB()=="隨機森林"){
							System.out.println("評估預測(隨機森林)執行中--------------------");
							Thread threadG = new Thread(new Runnable() { 
							@Override
								public void run() {
									
									
									try {	
										
										ALLINONE_RF_PreAll2 RF_PreAll = new ALLINONE_RF_PreAll2();
									
									} catch (Exception i) {
										System.out.println(i);
									  
									}
									AllWaitdialog.setVisible(false);
								}
							});
							threadG.start();
							AllWaitdialog.setVisible(true);
							try{
								System.out.println("Current Thread7: "+ Thread.currentThread().getName());
								threadG.join();
								System.out.println("評估預測(隨機森林)執行完畢--------------------");
								Eva_Flag=1;
								ImageIcon image=new ImageIcon(ClickbtArray[7]);
								button[7].setIcon(image);
								JOptionPane.showMessageDialog(frame,"一鍵處理進行完畢");
								
							}
					  
							catch(Exception ex){
								System.out.println("Exception has " +"been caught" + ex);
							}
						
						}else if(allinone.getRadioB()=="子群組探勘統計方法分析"){
							
							Runtime runtime5=Runtime.getRuntime();             
							Process process5 = runtime5.exec("ALLINONE_SubGroup_Analysis_All.exe");//file為執行外部程式的檔案名稱             
							System.out.println("評估預測(子群組)執行中--------------------");
							Thread threadG = new Thread(new Runnable() { 
							@Override
								public void run() {
									
									try {	
										InputStream stderr3 = process5.getErrorStream();
										InputStreamReader isr3 = new InputStreamReader(stderr3);
										BufferedReader br3 = new BufferedReader(isr3);
										String line = null;
										while((line = br3.readLine()) != null){
											//System.out.println(line);
											
										}
										process5.waitFor();
										//Thread.sleep(5000);
										br3.close();
									
									} catch (Exception i) {
										System.out.println(i);
									  
									}
									AllWaitdialog.setVisible(false);
								}
							});
							threadG.start();
							AllWaitdialog.setVisible(true);
							try{
								System.out.println("Current Thread7: "+ Thread.currentThread().getName());
								threadG.join();
								System.out.println("評估預測(子群組)執行完畢--------------------");
								Eva_Flag=1;
								ImageIcon image=new ImageIcon(ClickbtArray[7]);
								button[7].setIcon(image);
								JOptionPane.showMessageDialog(frame,"一鍵處理進行完畢");
								
								
							}
					  
							catch(Exception ex){
								System.out.println("Exception has " +"been caught" + ex);
							}
						}else{
							System.out.println("模型方法未選取");
						}
						
						
						
					} catch (Exception v) {
					  v.printStackTrace();
					}
				
				}else if(opt==1){
					allinone.erroedeal();
				}
					
				
			}else{
				System.out.println("ERROR");
			}
		
		}
	}
	
	/*implement END*/
	
	
}