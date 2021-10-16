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
import java.util.Random;
import org.jfree.chart.*;
import org.jfree.data.statistics.*;
import org.jfree.chart.plot.PlotOrientation;
public class layouttest6{
	public static JPanel panel;
	public layouttest6 (String title) {
		panel = new JPanel();
		panel.setLayout( new GridLayout(4,1) );
		//JFrame frame = new JFrame("Many charts same frame");
		//frame.setLayout( new GridLayout(4,1) );
		
		double[] value = new double[100];
        Random generator = new Random();
        for (int i=1; i < 5; i++) {
			value[i] = generator.nextDouble();
			int number = 10;
		
			HistogramDataset dataset = new HistogramDataset();
			dataset.setType(HistogramType.RELATIVE_FREQUENCY);
			dataset.addSeries("number"+i,value,number);
		
		
			JFreeChart barChart1 = 
				ChartFactory.createHistogram("Number"+i,"", "", dataset,
						PlotOrientation.VERTICAL, true, true, false);
						
		
			//frame.getContentPane().add(new ChartPanel(barChart1));
			panel.add(new ChartPanel(barChart1));
		}
		/*JFreeChart barChart2 = 
			ChartFactory.createHistogram("Histogram2","", "", dataset,
					PlotOrientation.VERTICAL, true, true, false);*/

		//frame.getContentPane().add(new ChartPanel(barChart2));

		//frame.pack();

		//frame.setVisible(true);
	
	}
	public static JPanel CALLPIC(){
		return panel;
	}
	
	
}