package com.soft.util;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.soft.common.domain.TOrganization;
import com.soft.manager.service.TOrganizationService;

@Controller
public class CXOrganizationSelect extends TagBase {
	private static final long serialVersionUID = 1L;

	private String value;// 下拉框的值
	private String name;// 下拉框的名称
	private String id;// 下拉款的ID
	private boolean required;
	private boolean editable;
	private String orgIds;

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

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
	}

	@Override
	public void startTag(StringBuilder sb) throws Exception {
		TOrganizationService orgService = (TOrganizationService) MyApplicationListener
				.getSpringBean(TOrganizationService.class);
		List<TOrganization> dataList = orgService.getAllOrganization();
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
		prepareAttribute(sb, "data-options", "required:" + required
				+ ",editable:" + editable + onselect + panelHeight);
		prepareAttribute(sb, "class", "easyui-combobox");
		sb.append(">");
		// if (!required) {
		sb.append("<option value=''>&nbsp;</option>");
		// }
		String[] orgIdArray = null;
		if (Utils.isNotEmptyString(orgIds) && orgIds.contains(",")) {
			orgIdArray = orgIds.split(",");
		} else {
			orgIdArray = new String[] { orgIds };
		}
		for (TOrganization item : dataList) {
			String values = String.valueOf(item.getId());
			if (contains(orgIdArray, values)) {
				String labels = item.getOrgName();
				String selected = "";
				if (values.equals(value)) {
					selected = "selected = 'selected'";
				}

				sb.append("<option ").append(selected).append(" value='")
						.append(values).append("'>").append(labels)
						.append("</option>");
			}
		}
		sb.append("</select>");
		cleanArguments();
	}

	/**
	 * 
	 * contains(判断数组中是否包含某个字符串)
	 * 
	 * @param orgIdArray
	 * @param value
	 * @return boolean
	 * @exception
	 */
	private boolean contains(String[] orgIdArray, String value) {
		if (orgIdArray == null) {
			return false;
		}
		for (String id : orgIdArray) {
			if (id.equals(value)) {
				return true;
			}
		}
		return false;
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
