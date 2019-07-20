package com.soft.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import com.soft.manager.Constants;

@SuppressWarnings("serial")
public class CXConstants extends TagSupport {
	// 创建logger对象
	private static LogService logger = LogService.getLogger(CXConstants.class);
	// 获取Constants类类名
	public String clazz = Constants.class.getName();
	// 请求范围
	protected String scope = null;
	// 变量名
	protected String var = null;

	/**
	 * 重写父类方法
	 */
	public int doStartTag() throws JspException {
		@SuppressWarnings("rawtypes")
		Class c = null;
		// 获取请求范围
		int toScope = PageContext.PAGE_SCOPE;
		if (scope != null) {
			toScope = getScope(scope);
		}
		try {
			// 创建Class对象
			c = Class.forName(clazz);
		} catch (ClassNotFoundException cnf) {
			logger.error("ClassNotFound - maybe a type ?");
			throw new JspException(cnf.getMessage());
		}

		try {
			// 判断参数var是否存在，若不存在，抛出异常
			if (var == null || var.length() == 0) {
				throw new JspException("常量参数var必须填写！");
			} else {
				try {
					// 获得参数var的常量值
					Object value = c.getField(var).get(this);
					// 将值保存到页面上下文中
					pageContext.setAttribute(c.getField(var).getName(), value,
							toScope);
				} catch (NoSuchFieldException nsf) {
					logger.error(nsf.getMessage());
					throw new JspException(nsf);
				}
			}
		} catch (IllegalAccessException iae) {
			logger.error("Illegal Access Exception - maybe a classloader issue ?");
			throw new JspException(iae);
		}
		return (SKIP_BODY);
	}

	/**
	 * @jsp.attribute
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getScope() {
		return (this.scope);
	}

	/**
	 * @jsp.attribute
	 */
	public void setVar(String var) {
		this.var = var;
	}

	public String getVar() {
		return (this.var);
	}

	/**
	 * 释放资源
	 */
	public void release() {
		super.release();
		clazz = null;
		scope = Constants.class.getName();
	}

	/**
	 * 创建Map对象，保存请求范围信息
	 */
	private static final Map<String, Integer> scopes = new HashMap<String, Integer>();
	static {
		scopes.put("page", new Integer(PageContext.PAGE_SCOPE));
		scopes.put("request", new Integer(PageContext.REQUEST_SCOPE));
		scopes.put("session", new Integer(PageContext.SESSION_SCOPE));
		scopes.put("application", new Integer(PageContext.APPLICATION_SCOPE));
	}

	/**
	 * 
	 * getScope(获取请求范围)
	 * 
	 * @param scopeName
	 *            请求范围名称
	 * @return
	 * @throws JspException
	 *             返回请求范围的int值 int
	 * @exception
	 */
	public int getScope(String scopeName) throws JspException {
		Integer scope = (Integer) scopes.get(scopeName.toLowerCase());

		if (scope == null) {
			throw new JspException("Scope '" + scopeName
					+ "' not a valid option");
		}

		return scope.intValue();
	}

}
