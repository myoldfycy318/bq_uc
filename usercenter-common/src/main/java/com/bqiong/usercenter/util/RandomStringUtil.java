package com.bqiong.usercenter.util;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Utility that generates a random-value ASCII string.
 * 
 * @author Ryan Heaton
 * @author Dave Syer
 */
public class RandomStringUtil {

	private static final char[] DEFAULT_CODEC_SALT = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
	
	private static final char[] DEFAULT_CODEC_NAME = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	
	private static final char[] DEFAULT_CODEC_NUM = "0123456789".toCharArray();
	
	private static Random random = new SecureRandom();
	
	private static int SALT_LENGTH = 32;
	
	private static final int NAME_LENGTH = 8;

	/**
	 * Create a generator of random strings of the length provided
	 * 
	 * @param SALT_LENGTH
	 *            the length of the strings generated
	 */

	public static String generateSalt() {
		byte[] verifierBytes = new byte[SALT_LENGTH];
		random.nextBytes(verifierBytes);
		return getRandomString(verifierBytes, DEFAULT_CODEC_SALT);
	}
	
	/**
	 * 
	 *  函数名称 : generateStringByLength
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@param length
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年8月3日 下午3:35:44	修改人：  niuzan
	 *  	描述	：
	 *
	 */
	public static String generateStringByLength(int length) {
		byte[] verifierBytes = new byte[length];
		random.nextBytes(verifierBytes);
		return getRandomString(verifierBytes, DEFAULT_CODEC_SALT);
	}
	
	public static String generateName()
	{
		byte[] verifierBytes = new byte[NAME_LENGTH];
		random.nextBytes(verifierBytes);
		return getRandomString(verifierBytes, DEFAULT_CODEC_NAME);
	}
	
	/**
	 * Convert these random bytes to a verifier string. The length of the byte
	 * array can be {@link #setLength(int) configured}. The default
	 * implementation mods the bytes to fit into the ASCII letters 1-9, A-Z, a-z
	 * .
	 * 
	 * @param verifierBytes
	 *            The bytes.
	 * @param chars TODO
	 * @return The string.
	 */
	private static String getRandomString(byte[] verifierBytes, char[] charArray) {
		char[] chars = new char[verifierBytes.length];
		for (int i = 0; i < verifierBytes.length; i++) {
			chars[i] = charArray[((verifierBytes[i] & 0xFF) % charArray.length)];
		}
		return new String(chars);
	}
	
	public static String generateVerifyToken() 
	{
		byte[] verifierBytes = new byte[8];
		random.nextBytes(verifierBytes);
		return getRandomString(verifierBytes, DEFAULT_CODEC_SALT);
	}
	
	public static String generateCode(int length)
	{
		byte[] verifierBytes = new byte[length];
		random.nextBytes(verifierBytes);
		return getRandomString(verifierBytes, DEFAULT_CODEC_NUM);
	}
	
	/**
	 * 
	 *  函数名称 : getRandomMobileSuffix
	 *  功能描述 :  
	 *  参数及返回值说明：
	 *  	@return
	 *
	 *  修改记录：
	 *  	日期 ：2016年7月22日 上午11:38:42	修改人：  
	 *  	描述	：生成8位长度的字符串，与前面的3个字符串拼接。
	 *
	 */
	public static String getRandomMobileSuffix()
    {
		Random r = new Random();
		int i = r.nextInt(89999999) + 10000000;
        return String.valueOf(i);
    }
	
}
