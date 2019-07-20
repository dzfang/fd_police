package com.soft.util;

import com.soft.common.domain.CodeData;
import com.soft.manager.service.CodeDataService;

public class CodeDataUtil {

	public static String getCodeDataValue(String type, String code1) {
		CodeDataService xtbmService = (CodeDataService)MyApplicationListener.getSpringBean(CodeDataService.class);
		return xtbmService.getCodeDataValue(type, code1);
	}

	/**
	 * 获取值
	 * @param type 编码类型
	 * @param code1 编码
	 * @param code2 编码
	 * @return 
	 */
	public static String getCodeDataValue(String type, String code1, String code2){
		CodeDataService xtbmService = (CodeDataService)MyApplicationListener.getSpringBean(CodeDataService.class);
		return xtbmService.getCodeDataValue(type, code1, code2);
	}

	/**
	 * 获取编码Bean
	 * @param type 编码类型
	 * @param code1 编码
	 * @return 
	 */
	public static CodeData getCodeDataBean(String type, String code1){
		CodeDataService xtbmService = (CodeDataService)MyApplicationListener.getSpringBean(CodeDataService.class);
		return xtbmService.getCodeDataBean(type, code1);
	}
	/**
	 * 
	 * getCodeByValue(根据Value获得Code)  
	 * @param type
	 * @param value
	 * @return  
	 * String 
	 * @exception
	 */
	public static String getCodeByValue(String type, String value) {
		CodeDataService xtbmService = (CodeDataService)MyApplicationListener.getSpringBean(CodeDataService.class);
		return xtbmService.getCodeByValue(type, value);
	}
}
