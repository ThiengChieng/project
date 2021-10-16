import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.nio.*;
import nl.liacs.subdisc.*;
import nl.liacs.subdisc.gui.*;
import nl.liacs.subdisc.gui.MiningWindow;
import nl.liacs.subdisc.FileHandler.Action;

public class Cortana2{
	private String saveFilePath;
	public Cortana2(String FilePath){
		int wa = 0,fa = 0,last = 0;
		String year = "";
		String OutFilePath = "";
		last = FilePath.lastIndexOf("\\");
		year = FilePath.substring(last-4, last);
		
//		wa = FilePath.indexOf("Wa_Ch");
//		fa = FilePath.indexOf("Feature");
		
		File f1 = new File(FilePath);
		//設定檔案位置
		FileHandler aLoader = new FileHandler(f1);
		Table aTable = aLoader.getTable();
		SearchParameters aSearchParameters = aLoader.getSearchParameters();
		MiningWindow miningWindow;
		ResultWindow resultWindow;
		XMLAutoRun xmlAutoRun = new XMLAutoRun();
		//宣告讀檔、建表、搜尋參數與設定畫面
		
		
		OutFilePath = "Hos_Data\\Doc_Train\\Model\\" + year + "\\";
		File OutPath = new File(OutFilePath);
		OutPath.mkdirs();
		
		
//		File OutPath = new File("Hos_Data\\Doc_Train\\Model\\SubDisc\\");
//		OutPath.mkdirs();
		
		saveFilePath = OutFilePath + aTable.getName() + "_SubDisc.txt";
		//System.out.println(saveFilePath);

		boolean enable = false;
		for (int i = 0; i < aTable.getNrColumns(); i++)
			aTable.getColumn(i).setIsEnabled(enable);
		
		int[] ChangeAble = {36, 37, 38, 39, 40, 41, 42, 43, 44};
		enable = true;
		for (int i : ChangeAble)
			aTable.getColumn(i).setIsEnabled(enable);
		
		int[] ChangeType = {36, 37, 38, 39, 41, 42, 43, 44};
		for (int i : ChangeType)
			aTable.getColumn(i).setType(AttributeType.fromString("nominal"));
		
		if (aTable == null){
			System.out.println("1");
			miningWindow = new MiningWindow();
		}else if (aSearchParameters == null){
			System.out.println("T2");
			miningWindow = new MiningWindow(aTable);
		}else{
			System.out.println("T3");
			miningWindow = new MiningWindow(aTable, aSearchParameters);
		}	
		new MetaDataWindow(miningWindow, aTable);
		
		
		miningWindow.setQualityMeasureMinimum("0.002");
		miningWindow.setTargetAttribute("診療分群");
		miningWindow.setMiscFieldName("10.0");
		xmlAutoRun.setClassValue(miningWindow.getMiscFieldName());
		System.out.println(miningWindow.getTargetAttributeName());
		
		miningWindow.subgroupDiscoveryActionPerformed();
		resultWindow = nl.liacs.subdisc.Process.getResultWindow();
		resultWindow.setPath(OutFilePath);
		resultWindow.jButtonSaveActionPerformed();
		
		miningWindow.setMiscFieldName("9.0");
		xmlAutoRun.setClassValue(miningWindow.getMiscFieldName());
		miningWindow.subgroupDiscoveryActionPerformed();
		resultWindow = nl.liacs.subdisc.Process.getResultWindow();
		resultWindow.setPath(OutFilePath);
		resultWindow.jButtonSaveActionPerformed();
		miningWindow.setMiscFieldName("8.0");
		xmlAutoRun.setClassValue(miningWindow.getMiscFieldName());
		miningWindow.subgroupDiscoveryActionPerformed();
		resultWindow = nl.liacs.subdisc.Process.getResultWindow();
		resultWindow.setPath(OutFilePath);
		resultWindow.jButtonSaveActionPerformed();
		xmlAutoRun.setFw(0);
	}
	public String getPath(){
		return saveFilePath;
	}

}