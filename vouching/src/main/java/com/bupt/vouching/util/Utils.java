package com.bupt.vouching.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 * 
 * @author Hogan
 * 
 */
public class Utils {
	
	public static String emailRegex = "\\w([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w([-.]\\w+)*";

	/**
	 * 字符串为空或空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNullOrBlank(String... str) {
		if(str != null){
			for (String e : str) {
				if (e == null || "".equals(e)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 生成32bit的验证码
	 * 
	 * @return
	 */
	public static String UUID() {
		return java.util.UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 校验Email的格式
	 * 
	 * @param email
	 * @return
	 */
	public static boolean EmailIsValid(String email) {
		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

}
