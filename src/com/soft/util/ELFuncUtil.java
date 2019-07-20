package com.soft.util;

public class ELFuncUtil {
	public static boolean permission(String action, String operate) {
		return Utils.isNotEmptyString(action) && action.indexOf(operate) >= 0;
	}
}
