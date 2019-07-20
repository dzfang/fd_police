package com.soft.common.domain;


public class ActionInfo {
	
    private String actionId;

    private Integer menuId;

    private String actionName;

    private String url;

    private String isChecked;
    
    private boolean isRzQyfqqr;
    
    
     
	public boolean isRzQyfqqr() {
		return isRzQyfqqr;
	}

	public void setRzQyfqqr(boolean isRzQyfqqr) {
		this.isRzQyfqqr = isRzQyfqqr;
	}

	public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

	public String getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(String isChecked) {
		this.isChecked = isChecked;
	}
    
}