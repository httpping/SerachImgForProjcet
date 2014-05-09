package com.tp.search.tool;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Logger;

/**
 * 
 * 搜索所有的 png 图片 文件
 * 
 * @author tp
 * 
 */
public class SearchImageFile {


	public static Set<String> pngImgNames  = new HashSet();
	
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
			//System.out.println(TypeFileUtil.getUrlFileNameType(file.getName()));
			 if (TypeFileUtil.getUrlFileNameType(file.getName())!=null && TypeFileUtil.getUrlFileNameType(file.getName()).toLowerCase().equals("png")) {
				System.out.println(TypeFileUtil.getUrlFileName(file.getName()));
				pngImgNames.add(TypeFileUtil.getUrlFileName(file.getName()));
			 }
			
		}
	}

	
	public static  Set<String> searchJavaFile(String srcPath){
		
		File file =  new File(srcPath);
		getAndPrintFile(file);
		return pngImgNames;
		
	}
	
	
	
}
