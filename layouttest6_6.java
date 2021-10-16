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
import java.awt.Font; //文字的import
import java.io.*;//開啟檔案的import
import java.io.BufferedReader;
import org.jfree.chart.ChartFactory;
import java.io.*;
import org.jfree.chart.*;
import org.jfree.data.statistics.*;
import org.jfree.chart.plot.PlotOrientation;
import java.util.ArrayList;
public class layouttest6_6{
	private static JPanel jpanel=new JPanel();
    private static final long serialVersionUID = -4316075518094318809L;   
    private static int numhead=0;
	private static JFreeChart jfreechart ;
	private static String Queue = null;
	private static String waittime = null;
	private static String idle = null;
    public layouttest6_6(String title,String Queue,String waittime,String idle){ 
		this.Queue = null;
		this.waittime = null;
		this.idle = null;
		this.Queue=Queue;
		this.waittime=waittime;
		this.idle=idle;
		jpanel.removeAll();
		jpanel.setLayout(new BorderLayout());
		
        jpanel.add(createDemoPanel(Queue,waittime,idle),BorderLayout.CENTER);   
		
        //jpanel.setPreferredSize(new Dimension(500, 270));   
       
    }   
                
    private JPanel createDemoPanel(String Queue,String waittime,String idle)   
    {   this.Queue = null;
		this.waittime = null;
		this.idle = null;
		this.Queue=Queue;
		this.waittime=waittime;
		this.idle=idle;
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(3,1));
		//panel.setPreferredSize(new Dimension(1920, 1000));   
		JFreeChart jfreechart = createChart(createDataset(0,Queue),0); 
		JFreeChart jfreechart2 = createChart(createDataset(1,waittime),1);
		JFreeChart jfreechart3 = createChart(createDataset(2,idle),2); 		
		ChartPanel chartpane = new ChartPanel(jfreechart);
		ChartPanel chartpane2 = new ChartPanel(jfreechart2);
		ChartPanel chartpane3 = new ChartPanel(jfreechart3);
		
		panel.add(chartpane);
		panel.add(chartpane2);
		panel.add(chartpane3);
		return panel;  			
		
        
         
    }   
                
    private JFreeChart createChart(DefaultCategoryDataset categorydataset,int jud)   
    {   
		if(jud==0){
			jfreechart= ChartFactory.createBarChart("Queue_length",   
                "month", "people", categorydataset, PlotOrientation.VERTICAL,   
                true, true, false);   
		}else if(jud==1){
			jfreechart= ChartFactory.createBarChart("Waitting_time",   
                "month", "Waitting_time mintues", categorydataset, PlotOrientation.VERTICAL,   
                true, true, false);   
		}else if(jud==2){
			jfreechart= ChartFactory.createBarChart("Total diagnosis time for a single doctor",   
                "month", "Diagnosis_time mintues", categorydataset, PlotOrientation.VERTICAL,   
                true, true, false);
		}
         
		CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();   
		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();   
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());   
		BarRenderer barrenderer = (BarRenderer) categoryplot.getRenderer();   
		barrenderer.setDrawBarOutline(false);  
		   
		GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F, Color.blue,   
					0.0F, 0.0F, new Color(0, 0, 64));   
		GradientPaint gradientpaint1 = new GradientPaint(0.0F, 0.0F,   
					Color.red, 0.0F, 0.0F, new Color(64, 0, 0));
		GradientPaint gradientpaint2 = new GradientPaint(0.0F, 0.0F, Color.green,   
					0.0F, 0.0F, new Color(0, 64, 0));  
		GradientPaint gradientpaint3 = new GradientPaint(0.0F, 0.0F,   
					new Color(255, 255, 0), 0.0F, 0.0F, new Color(64, 64, 0));
			 
			 
		barrenderer.setSeriesPaint(0, gradientpaint);   
		barrenderer.setSeriesPaint(1, gradientpaint1); 
		barrenderer.setSeriesPaint(2, gradientpaint2);
		barrenderer.setSeriesPaint(3, gradientpaint3);
		
		
    
        return jfreechart;   
    }   
                
    private DefaultCategoryDataset createDataset(int jud2,String file){
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
					
					dataset.addValue(Double.parseDouble(Input.get(i)[0]), "VGHKSA_MaxQueue", Input.get(i)[4]);
					dataset.addValue(Double.parseDouble(Input.get(i)[1]), "Analysis_MaxQueue", Input.get(i)[4]);
					dataset.addValue(Double.parseDouble(Input.get(i)[2]), "VGHKSA_AveQueue", Input.get(i)[4]);
					dataset.addValue(Double.parseDouble(Input.get(i)[3]), "Analysis_AveQueue", Input.get(i)[4]);
					
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
					
					dataset.addValue(Double.parseDouble(Input.get(i)[0]), "VGHKSA_MaxIdel", Input.get(i)[4]);
					dataset.addValue(Double.parseDouble(Input.get(i)[1]), "Analysis_MaxIdel", Input.get(i)[4]);
					dataset.addValue(Double.parseDouble(Input.get(i)[2]), "VGHKSA_AveIdel", Input.get(i)[4]);
					dataset.addValue(Double.parseDouble(Input.get(i)[3]), "Analysis_AveIdel", Input.get(i)[4]);
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
					
					dataset.addValue(Double.parseDouble(Input.get(i)[0]), "VGHKSA_MaxWait", Input.get(i)[4]);
					dataset.addValue(Double.parseDouble(Input.get(i)[1]), "Analysis_MaxWait", Input.get(i)[4]);
					dataset.addValue(Double.parseDouble(Input.get(i)[2]), "VGHKSA_AveWait", Input.get(i)[4]);
					dataset.addValue(Double.parseDouble(Input.get(i)[3]), "Analysis_AveWait", Input.get(i)[4]);
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