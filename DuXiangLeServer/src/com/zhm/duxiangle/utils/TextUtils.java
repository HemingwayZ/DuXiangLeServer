package com.zhm.duxiangle.utils;

public class TextUtils {
	/**
	 * 判断一个字符串是否为空或者空字符串
	 * 
	 * @param text
	 * @return
	 */
	public static boolean isEmpty(String text) {
		if (text == null) {
			return true;
		}
		if ("".equals(text.trim())) {
			return true;
		}
		return false;
	}
}
