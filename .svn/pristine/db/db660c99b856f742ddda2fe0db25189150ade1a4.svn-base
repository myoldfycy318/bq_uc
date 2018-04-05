package com.qbao.store.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * StringUtilTools 是一个字符串工具类，整合一些常用字符串处理函数
 * 
 * @Copyright lenovo
 * 
 * @Project egame-core-api
 * 
 * @Author Mac
 * 
 * @timer 2011-01-10
 * 
 * @Version 1.0.0
 * 
 * @JDK version used 6.0
 * 
 * @Modification history none
 * 
 * @Modified by none
 */
public class StringUtilTools {

	/** 无参的构造方法 */
	public StringUtilTools() {
		super();
	}

	/**
	 * 判断从数据库中取出来的数据是否为NULL
	 * 
	 * @param param
	 * @return
	 * @author ZengZS
	 * @time 2011-6-20
	 */
	public static Boolean isNullForFromDbToBean(String param) {
		if (param == null || param.trim().length() < 1 || "null".equals(param.trim().toLowerCase()) || "\"null\"".equals(param.trim().toLowerCase())) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * 按照某分隔符合并字符串数组
	 * 
	 * @param String source[]=源字符串数组
	 * 
	 * @param String separator=字符串分隔符
	 * 
	 * @return String=合并后字符串
	 * 
	 * @author Mac
	 * 
	 * @timer 2011-01-10
	 */
	public static String mergeString(String source[], String separator) {
		if (source == null || separator == null || source.length == 0)
			return "";
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < source.length; i++) {
			str.append(source[i]);
			if (i + 1 != source.length)
				str.append(separator);
		}
		return str.toString();
	}

	/**
	 * 按照某分隔符合并字符串列表集合
	 * 
	 * @param List <String> sourceList=源字符串数集合
	 * 
	 * @param String separator=字符串分隔符
	 * 
	 * @return String=合并后字符串
	 * 
	 * @author Mac
	 * 
	 * @timer 2011-01-10
	 */
	public static String mergeString(List<String> sourceList, String separator) {
		if (sourceList == null || separator == null || sourceList.size() == 0)
			return "";
		StringBuilder str = new StringBuilder();
		Iterator<String> iter = sourceList.iterator();
		while (iter.hasNext()) {
			str.append(iter.next());
			if (iter.hasNext() == false)
				str.append(separator);
		}
		return str.toString();
	}

	/**
	 * 根据分隔符统计字符串数量
	 * 
	 * @param String souce=源字符串
	 * 
	 * @param String separator=字符串分隔符
	 * 
	 * @return int=字符串数
	 * 
	 * @author Mac
	 * 
	 * @timer 2011-01-10
	 */
	public static int countForSonStringBySeparator(String souce, String separator) {
		if (souce == null || souce.length() == 0) {
			return 0;
		}
		StringTokenizer token = new StringTokenizer(souce, separator);
		return token.countTokens();
	}

	/**
	 * 统计字符串中包含某子字符串数量
	 * 
	 * @param String souce=源字符串
	 * 
	 * @param String sonString=子字符串
	 * 
	 * @return int=字符数
	 * 
	 * @author Mac
	 * 
	 * @timer 2011-01-10
	 */
	public static int countForSonStringByContain(String souce, String sonString) {
		// 查找某一字符串中souce，特定子串sonString的出现次数
		if (souce == null)
			return 0;
		int i = souce.length();
		souce = souce.replaceAll(sonString, "");// 反串中的字符sonString替换成""
		return (i - souce.length()) / sonString.length();
	}

	/**
	 * 根据分隔符拆分字符串并得到子字符串列表集合
	 * 
	 * @param String souce=源字符串
	 * 
	 * @param String separator=字符串分隔符
	 * 
	 * @return List<String>=子字符串集合
	 * 
	 * @author Mac
	 * 
	 * @timer 2011-01-10
	 */

	public static List<String> stringToListBySeparator(String souce, String separator) {
		if (souce == null || souce.length() == 0) {
			return null;
		}
		StringTokenizer token = new StringTokenizer(souce, separator);
		ArrayList<String> list = new ArrayList<String>(token.countTokens());
		while (token.hasMoreElements()) {
			list.add(token.nextToken());
		}

		return list;
	}

	/**
	 * 根据分隔符拆分字符串并得到子字符串数组集合
	 * 
	 * @param String souce=源字符串
	 * 
	 * @param String separator=字符串分隔符
	 * 
	 * @return String[]=子字符串数组
	 * 
	 * @author Mac
	 * 
	 * @timer 2011-01-10
	 */

	public static String[] stringToArrayBySeparator(String souce, String separator) {
		if (souce == null || souce.length() == 0) {
			return null;
		}
		StringTokenizer token = new StringTokenizer(souce, separator);
		String array[] = new String[token.countTokens()];
		int arrayFlag = 0;
		while (token.hasMoreElements()) {
			array[arrayFlag] = token.nextToken();
			arrayFlag = arrayFlag + 1;
		}
		return array;
	}

	/**
	 * 根据分隔符拆分字符串并得到子字符串数组集合
	 * 
	 * @param String souce=源字符串［key、value、key、value……］
	 * 
	 * @param String separator=字符串分隔符
	 * 
	 * @return String[]=子字符串数组
	 * 
	 * @author Mac
	 * 
	 * @timer 2011-01-10
	 */

	public static String[][] stringToTwoArrayBySeparator(String souce, String separator) {
		if (souce == null || souce.length() == 0) {
			return null;
		}
		System.out.println(souce);
		System.out.println(separator);
		StringTokenizer token = new StringTokenizer(souce, separator);
		String array[][] = new String[token.countTokens() / 2][];
		int arrayFlag = 0;
		while (token.hasMoreElements()) {
			array[arrayFlag][0] = token.nextToken();
			array[arrayFlag][1] = token.nextToken();
			arrayFlag = arrayFlag + 1;
		}
		return array;
	}

	/**
	 * 将源字符串转换成指定编码格式的字符串 （一般用于不知道自己本身编码格式情况）
	 * 
	 * @param String souce=源字符串
	 * 
	 * @param String targetEncoding= 目标编码格式
	 * 
	 * @@return String=字符串
	 * 
	 * @author Mac
	 * 
	 * @timer 2011-01-10
	 */
	public static String convertCharacterCode(String source, String targetEncoding) throws UnsupportedEncodingException {
		if (source != null)
			return new String(source.getBytes("GBK"), targetEncoding);
		return "";
	}

	/**
	 * 将指定编码格式的源字符串转换成目标编码格式的字符串
	 * 
	 * @param String souce=源字符串
	 * 
	 * @param String sourceEncoding=原编码格式
	 * 
	 * @param String targetEncoding= 目标编码格式
	 * 
	 * @return String=字符串
	 * 
	 * @author Mac
	 * 
	 * @timer 2011-01-10
	 */
	public static String convertCharacterCode(String source, String sourceEncoding, String targetEncoding) throws UnsupportedEncodingException {
		if (source != null)
			return source = new String(source.getBytes(sourceEncoding), targetEncoding);
		return "";
	}

	/**
	 * 将指定编码格式的源字符串转换成UNICODE格式的字符串
	 * 
	 * @param String souce=源字符串
	 * 
	 * @return String=UNICODE格式的字符串
	 * 
	 * @author Mac
	 * 
	 * @timer 2011-01-10
	 */
	public static String convertCharacterCode(String source){
		StringBuilder target = new StringBuilder("");
		char array[] = source.toCharArray();
		for(char element:array){
			target.append("\\u"+Integer.toHexString(element & 0xffff));
		}
		return target.toString();
	}
	
	/**
	 * 根据长度要求获取随机数字字符串
	 * 
	 * @param int length=数字字符串长度
	 * 
	 * @return String=数字字符串
	 * 
	 * @author Mac
	 * 
	 * @timer 2011-01-10
	 */

	public static String getRandomNumericString(int length) {
		Random rd = new Random();
		StringBuilder rstr = new StringBuilder();
		for (int i = 0; i < length; i++) {
			rstr.append(rd.nextInt(10));
		}
		return rstr.toString();
	}

	/**
	 * 根据长度要求获取随机英文字符串
	 * 
	 * @param int length=英文字符串长度
	 * 
	 * @param int formatType=英文字符串格式（1=小写字母,2=大写字母，3=大小写混合）
	 * 
	 * @return String=英文字符串
	 * 
	 * @author Mac
	 * 
	 * @timer 2011-01-10
	 */

	public static String getRandomEnString(int length, int formatType) {
		int i = 0;
		Random r = new Random();
		StringBuffer code = new StringBuffer();
		String exChars = null;// exChars：排除特殊字符
		while (i < length) {
			if (formatType == 1) {
				int t = r.nextInt(123);
				if ((t >= 97) && (exChars == null || exChars.indexOf((char) t) < 0)) {
					code.append((char) t);
					i++;
				}
			} else if (formatType == 2) {
				int t = r.nextInt(91);
				if ((t >= 65) && (exChars == null || exChars.indexOf((char) t) < 0)) {
					code.append((char) t);
					i++;
				}
			} else if (formatType == 3) {
				int t = r.nextInt(123);
				if ((t >= 97 || (t >= 65 && t <= 90)) && (exChars == null || exChars.indexOf((char) t) < 0)) {
					code.append((char) t);
					i++;
				}
			}
		}
		return code.toString();
	}

	/**
	 * 根据长度要求获取随机数字字母混合字符串
	 * 
	 * @param int length=混合字符串长度
	 * 
	 * @return String=混合字符串
	 * 
	 * @author Mac
	 * 
	 * @timer 2011-01-10
	 */

	public static String getRandomString(int length) {
		int i = 0;
		String exChars = null;// exChars：排除特殊字符
		Random r = new Random();
		StringBuffer code = new StringBuffer();
		while (i < length) {
			int t = r.nextInt(123);
			if ((t >= 97 || (t >= 65 && t <= 90) || (t >= 48 && t <= 57)) && (exChars == null || exChars.indexOf((char) t) < 0)) {
				code.append((char) t);
				i++;
			}
		}
		return code.toString();
	}


	public static void main(String args[]) {
		String source = "我们是中华儿女";
		System.out.println(StringUtilTools.convertCharacterCode(source));

	}
}
