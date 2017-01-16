package com.bupt.vouching.frame;

import java.security.MessageDigest;

/**
 * MD5加密工具
 * 
 * @author Hogan
 *
 */
public class MD5Util {
	
	/**
	 * MD5加密 生成32位md5码
	 * 
	 * @param inStr
	 * @return
	 * @throws Exception
	 */
	public static String md5Encode(String inStr) {
		MessageDigest md5 = null;
		StringBuffer hexValue = new StringBuffer();
		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] byteArray = inStr.getBytes("UTF-8");
			byte[] md5Bytes = md5.digest(byteArray);
			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16) {
					hexValue.append("0");
				}
				hexValue.append(Integer.toHexString(val));
			}
		} catch (Exception e) {
			return "";
		}
		return hexValue.toString();
	}

}
