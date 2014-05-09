package com.tp.search.tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Logger;

/**
 * 
 * 搜索所有的 xml 文件
 * 
 * @author tp
 * 
 */
public class SearchXMLFile {

	 
	public static List<File> xmlFiles  = new ArrayList<>();
	
	private  static void getAndPrintFile(File file) {
		if (file.isDirectory()) {
			//System.out.println(file.toString());
			File[] fa = file.listFiles();
			if (fa.length > 0) {
				for (int i = 0; i < fa.length; i++) {
					getAndPrintFile(fa[i]); // 递归调用
				}
			}
		} else{
			
			if (TypeFileUtil.getUrlFileNameType(file.getName())!=null && TypeFileUtil.getUrlFileNameType(file.getName()).toLowerCase().equals("xml")) {
				System.out.println(file.getName());
				xmlFiles.add(file);	
			}
		}
	}

	
	public static  List<File> searchXMLFile(String srcPath){
		
		File file =  new File(srcPath);
		
		getAndPrintFile(file);
		
		return xmlFiles;
		
	}
	
	
	/**
	 * 搜索img 是否被用到 
	 * 简单 匹配
	 */
	public static void searchImgExists(){
		
		
		
		Set<String> loopSet = new HashSet<String>();
		loopSet.addAll(SearchImageFile.pngImgNames);
		
		for (String pngName:loopSet) {
			 System.out.println("++==" + pngName);
			 loopFile(pngName);
		}
	}
	
	
	private static void loopFile(String pngName){
		for (int i = 0; i < xmlFiles.size(); i++) {
				
			try {
				File f = xmlFiles.get(i);
				//System.out.println("处理："+f.getName() +" 图片："+ pngName);
				
				BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
				String line ="";
				while ( (line= bufferedReader.readLine())!=null){
					
					/*if (f.getName().contains("send_msg")) {
						bufferedWriter.write(line);
						bufferedWriter.newLine();
						bufferedWriter.flush();
					}*/
					
					
					 if (isExists(line, pngName)) {
						 System.out.println("===="+f.getName() +" 存在引用 "+pngName);
						 SearchImageFile.pngImgNames.remove(pngName);// 删除掉
						 //结束循环
						 return;
					 }
				}
				
				bufferedReader.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			 
			
		}
	}
	
	
	static  File logFile ;
	static  BufferedWriter bufferedWriter  ;
	private static boolean isExists(String line,String pngName) throws IOException{
		
		if (line==null) {
			return false;
		}
		
		if (logFile ==null) {
			logFile = new File("D:/move_png/move_png_xml.txt");
			bufferedWriter = new BufferedWriter(new FileWriter(logFile));
		}
		
		
		//pngName = "R\\.drawable\\."+pngName;
		if (line.contains(pngName)) { // 包内涵
			bufferedWriter.write(line +" === "+ pngName );
			bufferedWriter.newLine();
			bufferedWriter.flush();
			return true;
		}
		
		return false;
	}
	
	
}
