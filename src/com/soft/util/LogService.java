package com.soft.util;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 写日志的共通
* @page Core
* @module Ice
* @author Ice框架组
* @date 2012-11-27
* @version 1.0
*/
public class LogService
{
    /**
     * 以包名为key的所有日志记录对象
     */
    public static Map<String, LogService> logServices = new HashMap<String, LogService>();

    private Logger logger4j = null;


    /**
     * 根据包名实例化log4j对象
     * @param moduleName
     */
    private LogService(String moduleName)
    {
        logger4j = LoggerFactory.getLogger(moduleName);
        
    }

    /**
     * 根据类名获取日志对象
     * @param clazz 类型
     * @return 日志对象
     */
    public static LogService getLogger(Class<?> clazz)
    {
        //com.hoperun.ec.bmp
        String packageName = clazz.getPackage().getName();
        if (logServices.containsKey(packageName))
        {
            return logServices.get(packageName);
        }
        else
        {
            LogService logService = new LogService(packageName);
            logServices.put(packageName, logService);
            return logService;
        }
    }

    /**
     *
     * <p>记录性能日志。</p>
     *
     * @param funcDesc 功能描述
     * @param flag 起始标识：0、开始；1、结束。
     */
    public void performance(String funcDesc, String flag)
    {
        String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
        StringBuffer sb = new StringBuffer();
        sb.append(formateTime(pattern, Calendar.getInstance().getTime()));
        sb.append(" 功能：");
        sb.append(funcDesc);
        sb.append(" 起始标识：");
        if ("0".equals(flag))
        {
            sb.append("开始");
        }
        else
        {
            sb.append("结束");
        }
        logger4j.info(sb.toString());
    }

    /**
     * 记录异常日志
     * @param code 异常代码
     * @param description 异常描述
     * @param e 抛出异常内容
     * @param object 异常操作对象
     */
    public void error(String code, String description, Throwable e, Object object)
    {

        //String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
        StringBuffer sb = new StringBuffer();
        //sb.append(formateTime(pattern, Calendar.getInstance().getTime()));
        if (code != null) {
            sb.append(String.format("C:[%s] ", code));
        }
        sb.append("- ");
        sb.append(description);
        if(object!=null){
                sb.append(String.format(" P:[%s] ", object.toString()));
        }
        if (null != e)
        {   
            sb.append("\n");
            sb.append(e.toString());
            sb.append(Arrays.toString(e.getStackTrace()));
        }

        logger4j.error(sb.toString());
    }
    /**
     * 记录异常日志
     * @param source 错误来源
     * @param code 异常代码
     * @param description 异常描述
     * @param user 用户信息
     * @param e 抛出异常内容
     * @param object 异常操作对象
     */
    public void error(String source, String code, String description, String user, 
    		Throwable e, Object object)
    {

        //String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
        StringBuffer sb = new StringBuffer();
        if(source!=null) {
        	
        		sb.append(String.format("S:[%s] ",source));
        }
		if (code != null) {
			sb.append(String.format("C:[%s] ", code));
		}
        sb.append("- ");
        sb.append(description);
        if(object!=null){
        		sb.append(String.format(" P:[%s] ", object.toString()));
        }
        if(user!=null){
        		sb.append(String.format("U:[%s] ", user));
        }
        if (null != e)
        {
        		sb.append("\n");
            sb.append(e.toString());
            sb.append(Arrays.toString(e.getStackTrace()));
        }
        logger4j.error(sb.toString());
    }
    /**
     * 记录异常日志
     * @param source 错误来源（通常是类名）
     * @param method 错误来源（通常是方法名）
     * @param code 异常代码
     * @param description 异常描述
     * @param user 用户信息
     * @param e 抛出异常内容
     * @param object 异常操作对象
     */
    public void error(String source, String method, String code, String description, String user, 
            Throwable e, Object object)
    {

        //String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
        StringBuffer sb = new StringBuffer();
        if(source!=null) {
            
                sb.append(String.format("S:[%s] ",source));
        }
        if (code != null) {
            sb.append(String.format("C:[%s] ", code));
        }
        sb.append("- ");
        sb.append(description);
        if(object!=null){
                sb.append(String.format(" P:[%s] ", object.toString()));
        }
        if(user!=null){
                sb.append(String.format("U:[%s] ", user));
        }
        if (null != e)
        {
                sb.append("\n");
            sb.append(e.toString());
            sb.append(Arrays.toString(e.getStackTrace()));
        }
        logger4j.error(sb.toString());
    }
    /**
     * 记录异常日志
     * @param source 错误来源，通常是类名
     * @param method 方法名
     * @param description 错误描述
     * @param e exception
     */
    public void errorLog(String source, String method, String description, 
            Throwable e)
    {

        //String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
        StringBuffer sb = new StringBuffer();
        if(source!=null) {
            
                sb.append(String.format("S:[%s] ",source));
        }
        sb.append("- ");
        sb.append(description);
        if (null != e)
        {
                sb.append("\n");
            sb.append(e.toString());
            sb.append(Arrays.toString(e.getStackTrace()));
        }
        logger4j.error(sb.toString());
    }
    /**
     * 记录异常日志
     * @param description 异常描述
     * @param e 抛出异常内容
     * @param object 异常操作对象
     */
    public void error(String description, Throwable e)
    {
        //String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
        StringBuffer sb = new StringBuffer();
        //sb.append(formateTime(pattern, Calendar.getInstance().getTime()));
        sb.append(description);
        sb.append(" ]\n");
        if (null != e)
        {
            sb.append(e.toString());
            sb.append(Arrays.toString(e.getStackTrace()));
        }

        logger4j.error(sb.toString());
    }

    /**
     * 记录异常日志
     * @param description 异常描述
     *
     */
    public void error(String description)
    {
        logger4j.error(description);
    }

    /**
     * 记录警告日志
     * @param code 警告代码
     * @param description 异常描述
     * @param e 抛出异常内容
     * @param object 异常操作对象
     */
    public void warn(String code, String description, Throwable e, Object object)
    {

        //String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
        StringBuffer sb = new StringBuffer();
        //sb.append(formateTime(pattern, Calendar.getInstance().getTime()));
        sb.append(" 警告编号：[");
        sb.append(code);
        sb.append("] - ");
        sb.append(description);
        sb.append(" parameters [ ");
        // modify by 葛永山 begin
        // 此处可能会引发空指针异常
        //sb.append(object.toString());
        sb.append(object);
        // modify by 葛永山 end
        sb.append(" ]\n");
        if (null != e)
        {
            sb.append(e.toString());
            sb.append(Arrays.toString(e.getStackTrace()));
        }

        logger4j.warn(sb.toString());
    }

    /**
     * 记录警告日志
     * @param description 异常描述
     */
    public void warn(String description)
    {
        logger4j.warn(description);
    }

    /**
     * 记录信息日志
     * @param description 信息描述
     * @param object 信息操作对象
     */
    public void info(String description, Object object)
    {

        //String pattern = "yyyy-MM-dd HH:mm:ss.SSS";
        StringBuffer sb = new StringBuffer();
        //sb.append(formateTime(pattern, Calendar.getInstance().getTime()));
        sb.append(description);
        sb.append(" P:[ ");
        // modify by 葛永山 begin
        // 此处可能会引发空指针异常
        //sb.append(object.toString());
        sb.append(object);
        // modify by 葛永山 end
        sb.append(" ]\n");
        logger4j.info(sb.toString());
    }

    /**
     * 记录信息日志
     * @param description 信息描述
     */
    public void info(String description)
    {
        logger4j.info(description);
    }
    /**
     * 记录信息日志，包括来源和用户信息
     * @param source 来源
     * @param description 信息描述
     * @param user 用户标识
     */
    public void info(String source,String description,String user) {
    		String infoString = String.format("S:[%s] %s U:[%s]", source,description,user);
    		logger4j.info(infoString);
    }

    /**
     *
     * <p>请记述函数的功能概要。</p>
     *
     * @param pattern
     * @param date
     * @return
     */
    private String formateTime(String pattern, Date date)
    {
        return new SimpleDateFormat(pattern).format(date);
    }
    
    public void debug(String message)
    {
        logger4j.debug(message);
    }
    
    public void debug(String message, Throwable t)
    {
        logger4j.debug(message, t);
    }
    
    public boolean isDebugEnabled()
    {
        return logger4j.isDebugEnabled();
    }
    
    public boolean isInfoEnabled()
    {
        return logger4j.isInfoEnabled();
    }
    
}
