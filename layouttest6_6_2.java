import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import org.jfree.data.category.CategoryDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;
import java.awt.Font; 
import java.io.*;
import java.io.BufferedReader;
import org.jfree.chart.ChartFactory;
import java.io.*;
import org.jfree.chart.*;
import org.jfree.data.statistics.*;
import org.jfree.chart.plot.PlotOrientation;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Scanner;
import java.awt.Font; //文字的import
import java.io.*;//開啟檔案的import

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.awt.Component; 
import javax.swing.border.EmptyBorder;
import java.util.*;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.util.ArrayList;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;


public class layouttest6_6_2{
	private static JPanel jpanel=new JPanel();
    private static final long serialVersionUID = -4316075518094318809L;   
    private static int numhead=0;
	private static JFreeChart jfreechart ;
	private static String Queue = null;
	private static String waittime = null;
	private static String idle = null;
	private static String Doc_code = null;
    public layouttest6_6_2(String title,String Queue,String waittime,String idle,String Doc_code){ 
		this.Queue = null;
		this.waittime = null;
		this.idle = null;
		this.Doc_code = null;
		this.Queue=Queue;
		this.waittime=waittime;
		this.idle=idle;
		this.Doc_code=Doc_code;
		jpanel.removeAll();
		jpanel.setLayout(new BorderLayout());
		
        jpanel.add(createDemoPanel(Queue,waittime,idle,Doc_code),BorderLayout.CENTER);   
		
        //jpanel.setPreferredSize(new Dimension(500, 270));   
       
    }   
                
    private JPanel createDemoPanel(String Queue,String waittime,String idle,String Doc_code)   
    {   this.Queue = null;
		this.waittime = null;
		this.idle = null;
		this.Doc_code = null;
		this.Queue=Queue;
		this.waittime=waittime;
		this.idle=idle;
		this.Doc_code=Doc_code;
		JPanel panel=new JPanel();
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel();
		JPanel panel4=new JPanel();
		JPanel panel5=new JPanel();
		JPanel panel6=new JPanel();
		
		panel.setLayout(new GridLayout(3,2));
		panel1.setLayout(new BorderLayout());
		panel2.setLayout(new BorderLayout());
		panel3.setLayout(new BorderLayout());
		panel4.setLayout(new BorderLayout());
		panel5.setLayout(new BorderLayout());
		panel6.setLayout(new BorderLayout());
		
		//panel.setPreferredSize(new Dimension(1920, 1000));   
		JFreeChart jfreechart = createChart(createDataset(0,Queue,0),0,0,Doc_code); 
		JFreeChart jfreechart2 = createChart(createDataset(0,Queue,1),0,1,Doc_code);
		JFreeChart jfreechart3 = createChart(createDataset(1,waittime,0),1,0,Doc_code);
		JFreeChart jfreechart4 = createChart(createDataset(1,waittime,1),1,1,Doc_code);
		JFreeChart jfreechart5 = createChart(createDataset(2,idle,0),2,0,Doc_code); 
		JFreeChart jfreechart6 = createChart(createDataset(2,idle,1),2,1,Doc_code);	
		
		
		
		ChartPanel chartpane = new ChartPanel(jfreechart);
		ChartPanel chartpane2 = new ChartPanel(jfreechart2);
		ChartPanel chartpane3 = new ChartPanel(jfreechart3);
		ChartPanel chartpane4 = new ChartPanel(jfreechart4);
		ChartPanel chartpane5 = new ChartPanel(jfreechart5);
		ChartPanel chartpane6 = new ChartPanel(jfreechart6);
		
		panel1.add(Box.createRigidArea(new Dimension(5, 5)),BorderLayout.WEST);
		panel1.add(Box.createRigidArea(new Dimension(5, 5)),BorderLayout.EAST);
		panel1.add(Box.createRigidArea(new Dimension(5, 5)),BorderLayout.NORTH);
		panel1.add(Box.createRigidArea(new Dimension(5, 5)),BorderLayout.SOUTH);
		panel1.add(chartpane,BorderLayout.CENTER);
		
		panel2.add(Box.createRigidArea(new Dimension(5, 5)),BorderLayout.WEST);
		panel2.add(Box.createRigidArea(new Dimension(5, 5)),BorderLayout.EAST);
		panel2.add(Box.createRigidArea(new Dimension(5, 5)),BorderLayout.NORTH);
		panel2.add(Box.createRigidArea(new Dimension(5, 5)),BorderLayout.SOUTH);
		panel2.add(chartpane2,BorderLayout.CENTER);
		
		panel3.add(Box.createRigidArea(new Dimension(5, 5)),BorderLayout.WEST);
		panel3.add(Box.createRigidArea(new Dimension(5, 5)),BorderLayout.EAST);
		panel3.add(Box.createRigidArea(new Dimension(5, 5)),BorderLayout.NORTH);
		panel3.add(Box.createRigidArea(new Dimension(5, 5)),BorderLayout.SOUTH);
		panel3.add(chartpane3,BorderLayout.CENTER);
		
		panel4.add(Box.createRigidArea(new Dimension(5, 5)),BorderLayout.WEST);
		panel4.add(Box.createRigidArea(new Dimension(5, 5)),BorderLayout.EAST);
		panel4.add(Box.createRigidArea(new Dimension(5, 5)),BorderLayout.NORTH);
		panel4.add(Box.createRigidArea(new Dimension(5, 5)),BorderLayout.SOUTH);
		panel4.add(chartpane4,BorderLayout.CENTER);
		
		panel5.add(Box.createRigidArea(new Dimension(5, 5)),BorderLayout.WEST);
		panel5.add(Box.createRigidArea(new Dimension(5, 5)),BorderLayout.EAST);
		panel5.add(Box.createRigidArea(new Dimension(5, 5)),BorderLayout.NORTH);
		panel5.add(Box.createRigidArea(new Dimension(5, 5)),BorderLayout.SOUTH);
		panel5.add(chartpane5,BorderLayout.CENTER);
		
		panel6.add(Box.createRigidArea(new Dimension(5, 5)),BorderLayout.WEST);
		panel6.add(Box.createRigidArea(new Dimension(5, 5)),BorderLayout.EAST);
		panel6.add(Box.createRigidArea(new Dimension(5, 5)),BorderLayout.NORTH);
		panel6.add(Box.createRigidArea(new Dimension(5, 5)),BorderLayout.SOUTH);
		panel6.add(chartpane6,BorderLayout.CENTER);
		
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel4);
		panel.add(panel5);
		panel.add(panel6);
		return panel;  			
		
        
         
    }   
                
    private JFreeChart createChart(DefaultCategoryDataset categorydataset,int jud,int max_ave,String Doc_code)   
    {   
		if(jud==0){
			if(max_ave==0){
				jfreechart= ChartFactory.createBarChart("Average Queue Length ("+Doc_code+")",   
                "Month", "Number of patient", categorydataset, PlotOrientation.VERTICAL,   
                true, true, false); 
				
			}else{
				jfreechart= ChartFactory.createBarChart("Max Queue Length("+Doc_code+")",   
                "Month", "Number of patient", categorydataset, PlotOrientation.VERTICAL,   
                true, true, false);   
			}
			
		}else if(jud==1){
			if(max_ave==0){
				jfreechart= ChartFactory.createBarChart("Average Waitting Time ("+Doc_code+")",   
                "Month", "Minute", categorydataset, PlotOrientation.VERTICAL,   
                true, true, false);   
			}else{
				jfreechart= ChartFactory.createBarChart("Max Waitting Time ("+Doc_code+")",   
                "Month", "Minute", categorydataset, PlotOrientation.VERTICAL,   
                true, true, false);   
			} 
		}else if(jud==2){
			if(max_ave==0){
				jfreechart= ChartFactory.createBarChart("Average Idle Time ("+Doc_code+")",   
                "Month", "Minute", categorydataset, PlotOrientation.VERTICAL,   
                true, true, false);   
			}else{
				jfreechart= ChartFactory.createBarChart("Max Idle Time ("+Doc_code+")",   
                "Month", "Minute", categorydataset, PlotOrientation.VERTICAL,   
                true, true, false);   
			}
		}
         
		CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();   
		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();   
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());   
		BarRenderer barrenderer = (BarRenderer) categoryplot.getRenderer();   
		barrenderer.setDrawBarOutline(false);  
		barrenderer.setItemMargin(0.0);
		GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F, new Color(5, 108, 242),   
					0.0F, 0.0F, new Color(3, 49, 140));   
		GradientPaint gradientpaint1 = new GradientPaint(0.0F, 0.0F,   
					new Color(255, 140, 100), 0.0F, 0.0F, new Color(255, 109, 90));
		 
		barrenderer.setSeriesPaint(0, gradientpaint);   
		barrenderer.setSeriesPaint(1, gradientpaint1); 
		
		
		CategoryPlot plot = jfreechart.getCategoryPlot(); 

		Font font1 = new Font("Times New Roman", Font.PLAIN,25);
		Font font2 = new Font("Times New Roman", Font.PLAIN,25);
		Font font3 = new Font("Times New Roman", Font.BOLD,35);		
		

		plot.getDomainAxis().setLabelFont(font2);//x軸標題 
		plot.getRangeAxis().setLabelFont(font2);//y軸標題  

		CategoryAxis axisDomain = plot.getDomainAxis(); 
		ValueAxis axisRange = plot.getRangeAxis(); 

		axisDomain.setTickLabelFont(font1);//x軸 

		axisRange.setTickLabelFont(font1); 	//y軸
		jfreechart.getTitle().setFont(font3);//標題
        return jfreechart;   
    }   
                
    private DefaultCategoryDataset createDataset(int jud2,String file,int Max_Ave){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ArrayList<String[]> Input = null;
		ArrayList<String[]> Input2 = null;
		ArrayList<String[]> Input3 = null;
		ArrayList<String[]> Input4 = null;
		ArrayList<String[]> Input5 = null;
		
		
		if(jud2==0){
			Input = new ArrayList<String[]>();
			Input2 = new ArrayList<String[]>();
			Input3 = new ArrayList<String[]>();
			Input4 = new ArrayList<String[]>();
			Input5 = new ArrayList<String[]>();
			try{
				BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file))));
				
				String line = null;
				
				
				
				System.out.println("有讀取");
				
				line = bf.readLine();
				
				System.out.println(line);
				
				while((line = bf.readLine()) != null){
					//System.out.println(line);
					Input.add(line.split(","));
					Input2.add(line.split(","));
					Input3.add(line.split(","));
					Input4.add(line.split(","));
					Input5.add(line.split(","));
				}
				
				for(int i=0;i<Input.size();i++){
					System.out.println(Input.get(i)[0]);
					System.out.println(Input.get(i)[1]);
					System.out.println(Input.get(i)[2]);
					System.out.println(Input.get(i)[3]);
					System.out.println(Input.get(i)[4]);
					if(Max_Ave==0){
						dataset.addValue(Double.parseDouble(Input.get(i)[2]), "VGHKSA_Average Queue Length", Input.get(i)[4]);
						dataset.addValue(Double.parseDouble(Input.get(i)[3]), "Analysis_Average Queue Length", Input.get(i)[4]);
					}else{
						dataset.addValue(Double.parseDouble(Input.get(i)[0]), "VGHKSA_Max Queue Length", Input.get(i)[4]);
						dataset.addValue(Double.parseDouble(Input.get(i)[1]), "Analysis_Max Queue Length", Input.get(i)[4]);
					}
					
					
					
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if(jud2==1){
			Input = new ArrayList<String[]>();
			Input2 = new ArrayList<String[]>();
			Input3 = new ArrayList<String[]>();
			Input4 = new ArrayList<String[]>();
			Input5 = new ArrayList<String[]>();
			try{
				BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file))));
				
				String line = null;
				
				
				
				System.out.println("有讀取");
				
				line = bf.readLine();
				
				System.out.println(line);
				
				while((line = bf.readLine()) != null){
					//System.out.println(line);
					Input.add(line.split(","));
					Input2.add(line.split(","));
					Input3.add(line.split(","));
					Input4.add(line.split(","));
					Input5.add(line.split(","));
				}
				
				for(int i=0;i<Input.size();i++){
					System.out.println(Input.get(i)[0]);
					
					System.out.println(Input.get(i)[1]);
					System.out.println(Input.get(i)[2]);
					
					System.out.println(Input.get(i)[3]);
					System.out.println(Input.get(i)[4]);
					
					if(Max_Ave==0){
						dataset.addValue(Double.parseDouble(Input.get(i)[2]), "VGHKSA_Average Waitting Time", Input.get(i)[4]);
						dataset.addValue(Double.parseDouble(Input.get(i)[3]), "Analysis_Average Waitting Time", Input.get(i)[4]);
					}else{
						dataset.addValue(Double.parseDouble(Input.get(i)[0]), "VGHKSA_Max Waitting Time", Input.get(i)[4]);
						dataset.addValue(Double.parseDouble(Input.get(i)[1]), "Analysis_Max Waitting Time", Input.get(i)[4]);
					}
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}else if(jud2==2){
			Input = new ArrayList<String[]>();
			Input2 = new ArrayList<String[]>();
			Input3 = new ArrayList<String[]>();
			Input4 = new ArrayList<String[]>();
			Input5 = new ArrayList<String[]>();
			try{
				BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file))));
				
				String line = null;
				
				
				
				System.out.println("有讀取");
				
				line = bf.readLine();
				
				System.out.println(line);
				
				while((line = bf.readLine()) != null){
					//System.out.println(line);
					Input.add(line.split(","));
					Input2.add(line.split(","));
					Input3.add(line.split(","));
					Input4.add(line.split(","));
					Input5.add(line.split(","));
				}
				
				for(int i=0;i<Input.size();i++){
					System.out.println(Input.get(i)[0]);
					
					System.out.println(Input.get(i)[1]);
					System.out.println(Input.get(i)[2]);
					
					System.out.println(Input.get(i)[3]);
					System.out.println(Input.get(i)[4]);
					
					if(Max_Ave==0){
						dataset.addValue(Double.parseDouble(Input.get(i)[2]), "VGHKSA_Average Idle Time", Input.get(i)[4]);
						dataset.addValue(Double.parseDouble(Input.get(i)[3]), "Analysis_Average Idle Time", Input.get(i)[4]);
					}else{
						dataset.addValue(Double.parseDouble(Input.get(i)[0]), "VGHKSA_Max Idle Time", Input.get(i)[4]);
						dataset.addValue(Double.parseDouble(Input.get(i)[1]), "Analysis_Max Idle Time", Input.get(i)[4]);
					}
					
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		return dataset;  
		
       
         
    }   
	public static JPanel CALLPIC(){
		return jpanel;
	}
	
	
}