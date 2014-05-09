package com.tp.search.tool;

public class TypeFileUtil {

	
	public static String getUrlFileNameNoPoint(String fileName) {
		String[] result = fileName.split("\\.");
		if (result != null && result.length != 0) {
			return result[0];
		}
		return null;
	}
	
	public static String getUrlFileNameType(String fileName) {
		String[] result = fileName.split("\\.");
		if (result != null && result.length != 0) {
			return result[result.length-1];
		}
		return null;
	}
	
	public static String getUrlFileName(String fileName) {
		String[] result = fileName.split("\\.");
		if (result != null && result.length != 0) {
			return result[0];
		}
		return null;
	}
}
