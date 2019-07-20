package com.soft.util;

/**
 * 系统共通配置
 * 
 * @author Administrator
 */
public class Config {

	/**
	 * 上传文件基础路径
	 */
	private static String uploadBasepath;

	/**
	 * 上传用户图片路径
	 */
	private static String uploadUserpath;
 
	/**
	 * 默认密码
	 */
	private static String defaultPassword;
 
	/**
	 * 极光推送客户端appKey
	 */
	private static String appKey = "";

	/**
	 * 极光推送客户端secretKey
	 */
	private static String secretKey = "";

	/**
	 * sorlUrl
	 */
	private static String sorlUrl = "";
	
	/**
	 * @return the uploadBasepath
	 */
	public static String getUploadBasepath() {
		return uploadBasepath;
	}

	/**
	 * @param uploadBasepath
	 *            the uploadBasepath to set
	 */
	public static void setUploadBasepath(String uploadBasepath) {
		Config.uploadBasepath = uploadBasepath;
	}

	/**
	 * @return the uploadUserpath
	 */
	public static String getUploadUserpath() {
		return uploadUserpath;
	}

	/**
	 * @param uploadUserpath
	 *            the uploadUserpath to set
	 */
	public static void setUploadUserpath(String uploadUserpath) {
		Config.uploadUserpath = uploadUserpath;
	}
 
	public static String getDefaultPassword() {
		return defaultPassword;
	}

	public static void setDefaultPassword(String defaultPassword) {
		Config.defaultPassword = defaultPassword;
	}
 
	/**
	 * @return the appKey
	 */
	public static String getAppKey() {
		return appKey;
	}

	/**
	 * @param appKey the appKey to set
	 */
	public static void setAppKey(String appKey) {
		Config.appKey = appKey;
	}

	/**
	 * @return the secretKey
	 */
	public static String getSecretKey() {
		return secretKey;
	}

	/**
	 * @param secretKey the secretKey to set
	 */
	public static void setSecretKey(String secretKey) {
		Config.secretKey = secretKey;
	}

	/**
	 * @return the sorlUrl
	 */
	public static String getSorlUrl() {
		return sorlUrl;
	}

	/**
	 * @param sorlUrl the sorlUrl to set
	 */
	public static void setSorlUrl(String sorlUrl) {
		Config.sorlUrl = sorlUrl;
	}
}
