package com.tp.search;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.tp.search.dpi.MoveChangeMoreDpiFileName;
import com.tp.search.tool.CopyFileUtil;
import com.tp.search.tool.SearchImageFile;
import com.tp.search.tool.SearchSrcFile;
import com.tp.search.tool.SearchXMLFile;
/**
 * 
 * projectpath = 工程的 root 目录
 * 
 * 功能 ： 删除 png 图片 没有在xml 和 java 中 使用到。
 * 		  将png 文件  备份 到 movePath  中。 文件路径和工程路径相同
 * 
 * 回退 方法： 将 备份目录中的 文件夹 复制到  res 中 即可 完成 回退 
 * 
 * 主要逻辑：  判断 png 文件名 是否在项目中存在。 
 * 
 * 使用是 请清理掉 目标目录 确保 目录中没有内容
 * 
 * @author tp
 *
 */
public class SearchMain {

	static String projectpath ="E:/svn_shouji_baseline2.1/VLinkClient";
	static String movePath ="D:/move_png"; // 
	
	public static void main(String[] args) throws IOException {
		File destFile = new File(movePath) ;
		if (destFile.exists()) { // 备份 以前的数据
			File bakFile = new File(movePath+"/"+System.currentTimeMillis());
			bakFile.mkdirs();
			//destFile.renameTo(bakFile);
			CopyFileUtil.copyDirectory(movePath, movePath+"_bak/"+System.currentTimeMillis(), true);
		}
		
		new File(movePath).mkdirs();
		
		
		SearchSrcFile.searchJavaFile(projectpath+"/src");
		SearchImageFile.searchJavaFile(projectpath+"/res");
		SearchXMLFile.searchXMLFile(projectpath);
		
		

		SearchSrcFile.searchImgExists();
		
		SearchXMLFile.searchImgExists();
			
		System.out.println("＝＝＝＝＝＝＝＝＝＝结果over＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝");
		
		File file = new File(movePath +"/move_log.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		for (String pngName:SearchImageFile.pngImgNames) {
			 bw.write(pngName);
			 bw.newLine();
			 bw.flush();
			 System.out.println("未用到 ：" + pngName);
			 MoveChangeMoreDpiFileName.moveImage(projectpath+"/res",movePath, pngName+".png");
		}
		bw.flush();
		bw.close();
	}
}
