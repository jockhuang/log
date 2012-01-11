package com.specl.log.util;

/**
 * @author wang xiaolei 2010-8-20
 * 
 */
public class StringUtil {

	/**
	 * 第一个字符为长度 的16进制表示 ,后面跟实际内容的字符串
	 */
	public static String encodeString(String content) {
		return String.format("%04x", content.getBytes().length) + content;
	}

	/**
	 * @param content
	 * @return
	 */
	public static String decodingString(String content) {
		int length = Integer.parseInt(content.substring(0, 4), 16);
		return content.substring(4, length + 4);
	}

	
}
