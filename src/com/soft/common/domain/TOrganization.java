package com.soft.common.domain;

@SuppressWarnings("serial")
public class TOrganization extends BaseEntity {
    private Long id;

    private String orgName;

    private Long parentId;
  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
 
}