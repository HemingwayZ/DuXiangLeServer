package com.zhm.duxiangle.utils;

public class TextUtils {
	/**
	 * �ж�һ���ַ����Ƿ�Ϊ�ջ��߿��ַ���
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
