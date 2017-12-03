package com.run.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RandomGUID {

	protected static Log logger = LogFactory.getLog(RandomGUID.class);

	private static SecureRandom mySecureRand;

	private static String s_id;
	
	

	static {
		mySecureRand = new SecureRandom();
		
		try {
			s_id = InetAddress.getLocalHost().toString();
		} catch (UnknownHostException e) {
			logger.error(e.getMessage(), e);
		}

	}
	/**
	 *通过二次处理，返回32位随机MD5字符串，示例：c51049b473d919ffa9e4e4c1559cf857
	* @MethodName: getRandomMD5 
	* @description : TODO
	* @author：柳发勇 
	* @date： 2014-3-28 下午5:36:24
	* @return String
	 */
	public static String getRandomMD5() {

		String valueBeforeMD5 = "";
		MessageDigest md5 = null;
		StringBuffer sbValueBeforeMD5 = new StringBuffer();

		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error: " + e);
		}

		try {
			long time = System.currentTimeMillis();
			
			long rand = mySecureRand.nextLong();
			
			sbValueBeforeMD5.append(s_id).append(":").append(Long.toString(time)).append(":").append(Long.toString(rand));

			valueBeforeMD5 = sbValueBeforeMD5.toString();
			
			md5.update(valueBeforeMD5.getBytes());

			byte[] array = md5.digest();
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < array.length; ++j) {
				int b = array[j] & 0xFF;
				if (b < 0x10)
					sb.append('0');
				sb.append(Integer.toHexString(b));
			}

			return sb.toString();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return "";
	}
	
	/**
	 * 对getRandomMD5方法的再处理，返回32位随机MD5字符串，示例：{CB4624-BB30-D43D-2EF3-964D92C7}
	* @MethodName: getRandomGUID 
	* @description : TODO
	* @author：柳发勇 
	* @date： 2014-3-28 下午5:37:36
	* @return String
	 */
	public static String getRandomGUID() {
        /*String raw = getRandomMD5().toUpperCase();
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append(raw.substring(0, 6));
        sb.append("-");
        sb.append(raw.substring(6, 10));
        sb.append("-");
        sb.append(raw.substring(10, 14));
        sb.append("-");
        sb.append(raw.substring(14, 18));
        sb.append("-");
        sb.append(raw.substring(18,26));
        sb.append("}");*/

        return getRandomMD5();
    }
	
	public static int getRandomIntegerGUID(){
		String t = String.valueOf(Math.abs(new Random().nextLong()));
		return Integer.parseInt(t.substring(0,6)); 
	}
	public static String getWXRandomID(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		Integer s = new Random().nextInt(1000);
		StringBuffer rad = new StringBuffer("WX");
		rad.append(sdf.format(new Date()));
		if (s.toString().length() == 3) {
			rad.append(s.toString());
		} else if (s.toString().length() == 2) {
			rad.append("0" + s.toString());
		} else if (s.toString().length() == 1) {
			rad.append("00" + s.toString());
		}
		return rad.toString();
	}
	public static void main(String[] args) {
		
		System.out.println("1-" + RandomGUID.getRandomMD5());
		
		System.out.println("1-" + RandomGUID.getRandomGUID().length());
		
		System.out.println("2-" + RandomGUID.getWXRandomID());
	}
}
