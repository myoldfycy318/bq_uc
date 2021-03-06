package com.bqiong.usercenter.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 * 
 * @author Frank.Zhou
 *
 */
public class MD5 {
	// md5小写摘要
	public static String md5Encode(String str) {
		StringBuffer buf = new StringBuffer();
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(str.getBytes("UTF-8"));
			byte bytes[] = md5.digest();
			for (int i = 0; i < bytes.length; i++) {
				String s = Integer.toHexString(bytes[i] & 0xff);
				if (s.length() == 1) {
					buf.append("0");
				}
				buf.append(s);
			}
		} catch (Exception ex) {
		}
		return buf.toString();
	}

	/**
	 * input 加密内容 algorithm 加密方式
	 * */
	public static String getHashCode(byte[] input, String algorithm)
			throws NoSuchAlgorithmException {
		MessageDigest md = null;
		md = MessageDigest.getInstance(algorithm);
		md.update(input);
		byte[] b = md.digest();
		StringBuilder output = new StringBuilder(32);
		for (int i = 0; i < b.length; i++) {
			String temp = Integer.toHexString(b[i] & 0xff);
			if (temp.length() < 2) {
				output.append("0");
			}
			output.append(temp);
		}
		return output.toString();
	}

    public static void main(String[] args) {
        String str = "appCode=H0000001&isAdult=1&time=2016-09-02 14:43:44&userId=123456789&zoneId=1";
        System.out.println(MD5.md5Encode(str));
    }
}
