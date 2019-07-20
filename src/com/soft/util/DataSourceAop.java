package com.soft.util;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class DataSourceAop {
	// @within在类上设置
	// @annotation在方法上进行设置
	@Pointcut("@within(com.soft.util.DataSourceSwitch)||@annotation(com.soft.util.DataSourceSwitch)")
	public void pointcut() {
	}

	@Before("pointcut()")
	public void doBefore(JoinPoint joinPoint) {
		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
		// 获取方法上的注解
		DataSourceSwitch annotationClass = method.getAnnotation(DataSourceSwitch.class);
		if (annotationClass == null) {
			// 获取类上面的注解
			annotationClass = joinPoint.getTarget().getClass().getAnnotation(DataSourceSwitch.class);
			if (annotationClass == null)
				return;
		}
		// 获取注解上的数据源的值的信息
		String dataSourceKey = annotationClass.dbType();
		if (dataSourceKey != null) {
			// 给当前的执行SQL的操作设置特殊的数据源的信息
			DataSourceContextHolder.setDbType(dataSourceKey);
		}
	}

	@After("pointcut()")
	public void after(JoinPoint point) {
		// 清理掉当前设置的数据源，让默认的数据源不受影响
		DataSourceContextHolder.clearDbType();
	}
}
