import java.io.*;
import javax.swing.*;
import javax.swing.table.*;
import javafx.application.Application;
import java.awt.Font; 

public class readtxt_3 {
	
	private JTable jTable1;
	private JScrollPane jScrollPane;
	private DefaultTableModel tableModel;
	private Font font1,font2;

	private static int numhead=0;
	
	public readtxt_3(String fileName) {
		
		System.out.println(fileName);
		
		
		try{ 
			BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
			String line = null;
			line = bf.readLine();
			String [] head = line.split(",");
			Object [][] silencer = {};
			numhead=head.length;
			System.out.println(numhead);
			tableModel = new DefaultTableModel(silencer,head);
			jTable1 = new JTable(tableModel);
			
			System.out.println("有讀取");
			jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			
			
			jTable1.setRowHeight(30);//?置表格行?
			
			font1 = new Font("PLAIN", 0, 25);
			jTable1.setFont(font1);
			jTable1.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 30));
			
			
			jScrollPane = new JScrollPane(jTable1);
			while((line = bf.readLine()) != null){
				tableModel.addRow(line.split(","));
				
				//System.out.println(x);
				//x++;
				
			}
			for(int i=0;i<numhead;i++){
				jTable1.getColumnModel().getColumn(i).setPreferredWidth(200);
			}
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public JScrollPane CALLTABLE(){
		return jScrollPane;
	}
	public String getColumnName(int i) {
		//System.out.println(jTable1.getModel().getColumnName(i));
        return jTable1.getModel().getColumnName(i);
    }
	public int ColumnCount() {
        return numhead;
    }
	
	
}