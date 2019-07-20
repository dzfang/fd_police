package com.soft.util;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.soft.common.domain.RoleInfo;
import com.soft.manager.service.RoleInfoService;

@Controller
public class CXRoleSelect extends TagBase {
	private static final long serialVersionUID = 1L;

	private String value;// 下拉框的值
	private String name;// 下拉框的名称
	private String id;// 下拉款的ID
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

	@Override
	public void startTag(StringBuilder sb) throws Exception {
		RoleInfoService roleInfoService = (RoleInfoService) MyApplicationListener
				.getSpringBean(RoleInfoService.class);
		List<RoleInfo> dataList = roleInfoService.getAllRoles();
		String panelHeight = "";
		String onselect = "";
		if (dataList.size() < 10) {
			panelHeight = ",panelHeight:'auto'";
		}
		if (Utils.isNotEmptyString(getOnselect())) {
			onselect = ",onSelect:function(rec){" + getOnselect() + "(rec)}";
		}
		sb.append("<select");
		prepareAttribute(sb, "id", getId());
		prepareAttribute(sb, "name", Utils.isEmptyString(name) ? id : name);
		sb.append(prepareAttributes());
		prepareAttribute(sb, "data-options", "editable:" + editable + onselect
				+ panelHeight);
		prepareAttribute(sb, "class", "easyui-combobox");
		sb.append(">");
		if (!required) {
			sb.append("<option value=''>&nbsp;</option>");
		}
		for (RoleInfo item : dataList) {
			String values = String.valueOf(item.getRoleId());
			String labels = item.getRoleName();
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
	public void cleanArguments() {
		value = null;
		name = null;
	}

	public boolean getRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public boolean getEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

}
