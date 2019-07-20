package com.soft.util;


import java.util.List;

import org.springframework.stereotype.Controller;

import com.soft.common.domain.CodeData;
import com.soft.manager.service.CodeDataService;

@Controller
public class CXSelect extends TagBase {
    private static final long serialVersionUID = 1L;
    
	private String value;// 下拉框的值
	private String name;//下拉框的名称
	private String id;// 下拉款的ID
	private String codeType;// 编码类型
	private String sqlWhere;// where条件
	private boolean required;
	private boolean editable;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	@Override
	public void startTag(StringBuilder sb) throws Exception {
		CodeDataService xtbmService = (CodeDataService)MyApplicationListener.getSpringBean(CodeDataService.class);
		List<CodeData> dataList = xtbmService.getCodeDataList(codeType, null, sqlWhere);
		String panelHeight = "";
		String onselect = "";
		if (dataList.size() < 10){
			panelHeight = ",panelHeight:'auto'";
		}
		if (Utils.isNotEmptyString(getOnselect())) {        	
			onselect = ",onSelect:function(rec){"+getOnselect()+"(rec)}";
		}
		sb.append("<select");
        prepareAttribute(sb, "id", getId());
        prepareAttribute(sb, "name", Utils.isEmptyString(name) ? id : name);
        sb.append(prepareAttributes());
    	prepareAttribute(sb, "data-options",  "required:"+required+",editable:"+editable+onselect+panelHeight);
        prepareAttribute(sb, "class", "easyui-combobox");
		sb.append(">");
//        if (!required) {        	
        	sb.append("<option value=''>&nbsp;</option>");
//        }
		List<CodeData> codeList = dataList;
		for (CodeData bm : codeList) {
			String values = bm.getCode1();
			String labels = bm.getValue();
			String selected = "";
			if (values.equals(value)) {
				selected = "selected = 'selected'";
			}

			sb.append("<option ").append(selected).append(" value='")
					.append(values).append("'>").append(labels)
					.append("</option>");
		}
		sb.append("</select>");
        cleanArguments();
    }

    /**
     * 清扫变量,解决tomcat7引起的缓存问题、
     */
    public void cleanArguments()
    {
        value = null;
        name = null;
    }

	public boolean getRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}

	public boolean getEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}


}
