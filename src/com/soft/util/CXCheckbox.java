package com.soft.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.soft.common.domain.CodeData;
import com.soft.manager.service.CodeDataService;

@Controller
public class CXCheckbox extends TagBase {
    private static final long serialVersionUID = 1L;
    
	private String value;// 复选框的值
	private String name;// 复选框的名称
	private String codeType;// 编码类型
	private String isRadio;// 是否单选
	private String sqlWhere;// where条件
	
	@Override
	public void startTag(StringBuilder sb) {
		CodeDataService codeDataService = (CodeDataService)MyApplicationListener.getSpringBean(CodeDataService.class);
		
		List<CodeData> dataList = codeDataService.getCodeDataList(codeType, null, sqlWhere);
		List<CodeData> codeList = dataList;
		List<String> values = Utils.isNotEmptyString(value) 
								? Arrays.asList(value.split(","))
								: new ArrayList<String>();
		for (CodeData bm : codeList) {
			String code = bm.getCode1();
			String labels = bm.getValue();
			boolean checked = false;
			if (values.contains(code)) {
				checked = true;
			}
			
			String type = (Utils.isNotEmptyString(isRadio) && isRadio.equalsIgnoreCase("true")) ? "radio" : "checkbox";
			String onselect = "";
			if (Utils.isNotEmptyString(getOnselect())) {        	
				onselect = " onclick='"+getOnselect()+"'";
			}
			
			sb.append("<input ");
			prepareAttribute(sb, "type", type);
	        prepareAttribute(sb, "name", name);
	        prepareAttribute(sb, "value", code);
	        if (checked) {
	        	sb.append(" checked");
	        }
	        sb.append(onselect);
	        sb.append(prepareAttributes());
	        sb.append("/>");
	        sb.append(labels);
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String checkboxName) {
		this.name = checkboxName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getIsRadio() {
		return isRadio;
	}

	public void setIsRadio(String isRadio) {
		this.isRadio = isRadio;
	}

	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}
	
}
