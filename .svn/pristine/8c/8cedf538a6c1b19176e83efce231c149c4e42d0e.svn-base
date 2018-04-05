package com.bqiong.usercenter.util.pbkdf2;

import java.security.GeneralSecurityException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author 94841
 *
 */
public class Pbkdf2Util
{
	private static final Logger log = LoggerFactory.getLogger(Pbkdf2Util.class);
	
	public static final String ALGORITHM = "PBKDF2WithHmacSHA1";
	
	public static final int ITERATION_COUNT = 10000;
	
	public static final int KEY_SIZE = 512;
	
	/**
	 * 
	 * @param salt
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String password,String salt)
	{
		try
		{
			byte[] saltByte = Base64.decodeBase64(salt.getBytes());
		    byte[] hash = hashPassword(password.toCharArray(), saltByte);
		    String pwdHashStr = new String(Base64.encodeBase64(hash));
		    return pwdHashStr;
		}catch(Exception e)
		{
			log.error("pbkdf2 encrypt error!", e);
			return null;
		}
	}

	public static byte[] hashPassword(char[] password, byte[] salt)
	        throws GeneralSecurityException 
	{
	    return hashPassword(password, salt, ITERATION_COUNT, KEY_SIZE);
	}

	public static byte[] hashPassword(char[] password, byte[] salt,
	        int iterationCount, int keySize) throws GeneralSecurityException
	{
	    try 
	    {
	        PBEKeySpec spec = new PBEKeySpec(password, salt, iterationCount, keySize);
	        SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
	        return factory.generateSecret(spec).getEncoded();
	    } catch (IllegalArgumentException e) {
	        throw new GeneralSecurityException("key size " + keySize, e);
	    }
	}


	public static void main(String[] args) throws Exception
	{
		//"PBKDF2WithHmacSHA1","111111","1AJkGIaY8BTllt4kjURKSw==",10000,64
		String encryptPassword=Pbkdf2Util.encrypt("1AJkGIaY8BTllt4kjURKSw==", "111111"); 
		System.out.println(encryptPassword);
	}

}