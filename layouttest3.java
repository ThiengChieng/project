import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;

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

public class layouttest3 {
	
	public static ChartPanel chartPanel;
    public static JPanel jPanel;
	public static JFreeChart chart;

    //private static final long serialVersionUID = 1L;

    public layouttest3(String title) {
        
		
		
        CategoryDataset dataset = createDataset();
        chart= createChart(dataset,title);
        chartPanel = new ChartPanel(chart);
        chartPanel.setFillZoomRectangle(true);
		chartPanel.setDomainZoomable(true);
        chartPanel.setMouseWheelEnabled(true); // 讓滑鼠滾輪可以放大縮小
        chartPanel.setPreferredSize(new Dimension(200, 280));
        //setContentPane(chartPanel);
		
    }

    public static CategoryDataset createDataset() {

        String series1 = "A";
        String series2 = "B";
        String series3 = "C";

        String category1 = "Category 1";

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(1.0, series1, category1);
        dataset.addValue(7.0, series2, category1);
        dataset.addValue(100.0, series3, category1);

        return dataset;
    }

    public static JFreeChart createChart(CategoryDataset dataset,String title) {

        // 建立長條圖
        JFreeChart chart = ChartFactory.createBarChart(title, "Y label", "X label", dataset,PlotOrientation.VERTICAL, true, true, false);

        // 設定背景顏色
        chart.setBackgroundPaint(Color.white);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        // 設定Y軸數值
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        // 畫出長條圖
        GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.blue, 0.0f,
                0.0f, new Color(0, 0, 64));
        GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.green, 0.0f,
                0.0f, new Color(0, 64, 0));
        GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.red, 0.0f,
                0.0f, new Color(64, 0, 0));
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);

        return chart;
    }

    /*public static void main(String[] args) {
        layouttest3 chart = new layouttest3("Test");
        chart.pack(); // 動態設定元件大小
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }*/
	public static ChartPanel CALLPIC(){
		return chartPanel;
	}
}