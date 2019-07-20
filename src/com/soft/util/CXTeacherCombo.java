package com.soft.util;

public class CXTeacherCombo extends TagBase {
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 控件值
	 */
	private String value;

	private String text;
	/**
	 * 控件ID
	 */
	private String id;
	/**
	 * 控件名称
	 */
	private String name;
	/**
	 * 是否多选
	 */
	private boolean multiple;
	/**
	 * 是否必输项
	 */
	private boolean required;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMultiple() {
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	@Override
	public void startTag(StringBuilder sb) {
		String strWidth = getWidth().toLowerCase();
		if (strWidth.endsWith("px")) {
			strWidth = strWidth.replaceAll("px", "");
		}
		int width = Integer.parseInt(strWidth);
		int panelWidth = width > 450 ? width : 450;
		// 创建input
		sb.append("<input ");
		prepareAttribute(sb, "id", id);
		prepareAttribute(sb, "type", "text");
		prepareAttribute(sb, "name", name);
		prepareAttribute(sb, "value", value);
		prepareAttribute(sb,"data-options","required:"+required+",icons: [{iconCls:'icon-clear',handler: function(e){combogridClear('"+id+"');}}]");
		sb.append(prepareAttributes());
		sb.append(">");

		sb.append("<input type='hidden' id='comboTeacherCode_" + id + "' />");
		sb.append("<input type='hidden' id='comboTeacherName_" + id + "' />");
		sb.append("<input type='hidden' id='comboTeacherText_" + id
				+ "'  value='" + (text == null ? "" : text) + "'/>");
		// 通过js初始化combogrid
		sb.append("<script>");
		sb.append("$(function(){");
		sb.append("combogridTeacher('" + id + "', " + multiple + ", "
				+ panelWidth + ");");
		sb.append("		});");
		sb.append("</script>");
	}
}
