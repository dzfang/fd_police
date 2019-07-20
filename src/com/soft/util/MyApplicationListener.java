package com.soft.util;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.WebApplicationContext;

public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {

	private static WebApplicationContext CONTEXT;
	
	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ContextRefreshedEvent) {
			WebApplicationContext context = (WebApplicationContext) ((ContextRefreshedEvent) event).getSource();
			CONTEXT = context;
		}

	}

	@SuppressWarnings("unchecked")
	public static <T> T getSpringBean(String key) {
		return (T) CONTEXT.getBean(key);
	}

	public static <T> T getSpringBean(Class<T> key) {
		return CONTEXT.getBean(key);
	}
}
