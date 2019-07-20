package com.soft.manager.controller;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import com.soft.util.BinderDateFormat;

/**
 * controller的基类
 */
public class BaseController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	/** 
     * 将一个 Map 对象转化为一个 JavaBean 
     * @param type 要转化的类型 
     * @param map 包含属性值的 map 
     * @return 转化出来的 JavaBean 对象 
     * @throws IntrospectionException 如果分析类属性失败 
     * @throws IllegalAccessException 如果实例化 JavaBean 失败 
     * @throws InstantiationException 如果实例化 JavaBean 失败 
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败 
     */  
	@SuppressWarnings("rawtypes")  
    public static Object convertMap(Class type, Map map)  
            throws IntrospectionException, IllegalAccessException,  
            InstantiationException, InvocationTargetException {  
        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性  
        Object obj = type.newInstance(); // 创建 JavaBean 对象  
  
        // 给 JavaBean 对象的属性赋值  
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();  
        for (int i = 0; i< propertyDescriptors.length; i++) {  
            PropertyDescriptor descriptor = propertyDescriptors[i];  
            String propertyName = descriptor.getName();  
  
            if (map.containsKey(propertyName)) {              	
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。  
                Object value = map.get(propertyName);  
  
                Object[] args = new Object[1];  
                
                /**
                 * 日期类型转换
                 */
				if(descriptor.getPropertyType().equals(Date.class)){
					if(value==null){
						args[0]=null;
					}
					else{
						args[0]=new Date(Long.parseLong(value.toString()));
					}
					
			         descriptor.getWriteMethod().invoke(obj, args)    ;   	
                }
				/**
                 * BigDecimal类型转换
                 */
				else if(descriptor.getPropertyType().equals(BigDecimal.class)){
					if(value==null){
						args[0]=null;
					}
					else{
						args[0]= BigDecimal.valueOf(Double.parseDouble(value.toString()));
					}
					
			         descriptor.getWriteMethod().invoke(obj, args)    ;   	
                }

                /**
                 * short类型转换
                 */
				else if(descriptor.getPropertyType().equals(Short.class)){
                	if(value==null){
                		args[0]=null;
                	}else{
                		args[0]=new Short(value.toString());
                	}
                	descriptor.getWriteMethod().invoke(obj, args);
				}
				/**
				 * 数组类型转换
				 */
				else if(descriptor.getPropertyType().isArray()){
					if(value==null){
						args[0]=null;
					}else{
						ArrayList<?> list = (ArrayList)value;
						//只支持String数组
						args[0]= list.toArray(new String[list.size()]);
					}
					descriptor.getWriteMethod().invoke(obj, args);
				}				
				else{
					args[0] = value;  
	                
	                descriptor.getWriteMethod().invoke(obj, args);  
				}
            }  
        }  
        return obj;  
    }  
    
    /**
     * Set up a custom property editor for converting form inputs to real
     * objects
     *
     * @param request the current request
     * @param binder the data binder
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request,
            ServletRequestDataBinder binder) {
        BinderDateFormat dateFormat = new BinderDateFormat();
        binder.registerCustomEditor(Date.class, null,
                new CustomDateEditor(dateFormat, true));
    }
    
    protected ModelAndView exceptionSolve(String sysErrorMsg) {
		ModelAndView mv = new ModelAndView("redirect:error.do");
		mv.addObject("messageId", sysErrorMsg);
		return mv;
	}

	public Logger getLogger() {
		return logger;
	}
}