package com.Lu.omw.utils;

public class PatternUtils {
	private static String MOBILE_PATTERN = "^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";

	public static boolean isMobile(String phone) {
		if (phone == null) {
			throw new IllegalArgumentException("������ֻ����벻��Ϊ��");
		}
		if (phone.matches(MOBILE_PATTERN)) {
			return true;
		}
		return false;
	}
}
