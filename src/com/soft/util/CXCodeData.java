package com.soft.util;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.stereotype.Controller;

@Controller
public class CXCodeData extends TagSupport {
    private static final long serialVersionUID = 1L;
    
	private String value;// 值
	private String code2;// 值
	private String codeType;// 编码类型
	
	private static LogService logger = LogService.getLogger(CXSelect.class);

	@Override
	public int doEndTag() throws JspException {
		StringBuffer sb = new StringBuffer();
		JspWriter out = this.pageContext.getOut();
		try {
			List<String> values = Utils.isNotEmptyString(value)  ? Arrays.asList(value.split(",")) : new ArrayList<String>();
			sb.append("<label>");
			for(String code1 : values){
				if( code2 != null) {
					sb.append(CodeDataUtil.getCodeDataValue(codeType,code1,code2) + " ");
				} else {
					sb.append(CodeDataUtil.getCodeDataValue(codeType,code1) + " ");	
				}					
			}	
			sb.append("</label>");
			out.print(sb.toString());
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {

		}
		return super.doEndTag();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodeType() {
		return codeType;
	}

	
	public String getCode2() {
		return code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
}

