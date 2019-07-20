package com.soft.common.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

@SuppressWarnings("serial")
public class FdInvestigation extends BaseEntity {
	@Resource(name = "ID")
	private Long id;
	
	@Resource(name = "案卷编号")
	private String caseCode;
	
	@Resource(name = "案件类型")
	private String caseType;
	
	@Resource(name = "案件名称")
	private String caseName;
	
	@Resource(name = "承办人")
	private String undertakePerson;
	
	@Resource(name = "承办单位")
	private String undertakeOrg;
	
	@Resource(name = "违法事实")
	private String illegalFacts;
	
	@Resource(name = "涉案物品")
	private String relatedThings;
	
	@Resource(name = "涉案价值")
	private BigDecimal relatedValue;
	
	@Resource(name = "案件来源")
	private String caseSource;
	
	@Resource(name = "发案地域")
	private String caseArea;
	
	@Resource(name = "发案处所")
	private String casePlace;
	
	@Resource(name = "发案地址")
	private String caseAddress;
	
	@Resource(name = "涉案单位")
	private String relatedOrg;
	
	@Resource(name = "受案时间")
	private Date caseTime;
	
	@Resource(name = "立案时间")
	private Date filingTime;
	
	@Resource(name = "刑事拘留开始时间")
	private Date crimnalStart;
	
	@Resource(name = "刑事拘留结束时间")
	private Date crimnalEnd;
	
	@Resource(name = "监视居住处所")
	private String monitorPlace;
	
	@Resource(name = "监视居住开始时间")
	private Date monitorStart;
	
	@Resource(name = "监视居住结束时间")
	private Date monitorEnd;
	
	@Resource(name = "取保候审日期")
	private Date bailTime;
	
	@Resource(name="取保候审人")
	private String bailPerson;
	
	@Resource(name = "逮捕日期")
	private Date arrestDate;
	
	@Resource(name = "是否延长羁押")
	private boolean extendDetention;
	
	@Resource(name = "刑期")
	private String term;
	
	@Resource(name = "罚金")
	private BigDecimal fine;
	
	@Resource(name = "免于起诉:0--否，1--是")
	private boolean prosecution;
	
	@Resource(name = "撤销日期")
	private Date pevocationDate;

	//扩展字段
	private String caseTypeName;

	private String relatedThingsName;
	
	private String caseSourceName;
	
	private String caseAreaName;

	private String casePlaceName;
	
	private List<FdRelatedPerson> personList;
 
	private String personJson;
	
	private String filingYear;
	
	private String filingMonth;
	
	private Integer filingCount;
	
	public Long getId() {
		return id;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public String getCaseType() {
		return caseType;
	}

	public String getCaseName() {
		return caseName;
	}

	public String getUndertakePerson() {
		return undertakePerson;
	}

	public String getUndertakeOrg() {
		return undertakeOrg;
	}

	public String getIllegalFacts() {
		return illegalFacts;
	}

	public String getRelatedThings() {
		return relatedThings;
	}

	public BigDecimal getRelatedValue() {
		return relatedValue;
	}

	public String getCaseSource() {
		return caseSource;
	}

	public String getCaseArea() {
		return caseArea;
	}

	public String getCasePlace() {
		return casePlace;
	}

	public String getCaseAddress() {
		return caseAddress;
	}

	public String getRelatedOrg() {
		return relatedOrg;
	}

	public Date getCaseTime() {
		return caseTime;
	}

	public Date getFilingTime() {
		return filingTime;
	}

	public Date getCrimnalStart() {
		return crimnalStart;
	}

	public Date getCrimnalEnd() {
		return crimnalEnd;
	}

	public String getMonitorPlace() {
		return monitorPlace;
	}

	public Date getMonitorStart() {
		return monitorStart;
	}

	public Date getMonitorEnd() {
		return monitorEnd;
	}

	public Date getBailTime() {
		return bailTime;
	}

	public String getBailPerson() {
		return bailPerson;
	}

	public Date getArrestDate() {
		return arrestDate;
	}

	public boolean isExtendDetention() {
		return extendDetention;
	}

	public String getTerm() {
		return term;
	}

	public BigDecimal getFine() {
		return fine;
	}

	public boolean isProsecution() {
		return prosecution;
	}

	public Date getPevocationDate() {
		return pevocationDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	
	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public void setUndertakePerson(String undertakePerson) {
		this.undertakePerson = undertakePerson;
	}

	public void setUndertakeOrg(String undertakeOrg) {
		this.undertakeOrg = undertakeOrg;
	}

	public void setIllegalFacts(String illegalFacts) {
		this.illegalFacts = illegalFacts;
	}

	public void setRelatedThings(String relatedThings) {
		this.relatedThings = relatedThings;
	}

	public void setRelatedValue(BigDecimal relatedValue) {
		this.relatedValue = relatedValue;
	}

	public void setCaseSource(String caseSource) {
		this.caseSource = caseSource;
	}

	public void setCaseArea(String caseArea) {
		this.caseArea = caseArea;
	}

	public void setCasePlace(String casePlace) {
		this.casePlace = casePlace;
	}

	public void setCaseAddress(String caseAddress) {
		this.caseAddress = caseAddress;
	}

	public void setRelatedOrg(String relatedOrg) {
		this.relatedOrg = relatedOrg;
	}

	public void setCaseTime(Date caseTime) {
		this.caseTime = caseTime;
	}

	public void setFilingTime(Date filingTime) {
		this.filingTime = filingTime;
	}

	public void setCrimnalStart(Date crimnalStart) {
		this.crimnalStart = crimnalStart;
	}

	public void setCrimnalEnd(Date crimnalEnd) {
		this.crimnalEnd = crimnalEnd;
	}

	public void setMonitorPlace(String monitorPlace) {
		this.monitorPlace = monitorPlace;
	}

	public void setMonitorStart(Date monitorStart) {
		this.monitorStart = monitorStart;
	}

	public void setMonitorEnd(Date monitorEnd) {
		this.monitorEnd = monitorEnd;
	}

	public void setBailTime(Date bailTime) {
		this.bailTime = bailTime;
	}

	public void setBailPerson(String bailPerson) {
		this.bailPerson = bailPerson;
	}

	public void setArrestDate(Date arrestDate) {
		this.arrestDate = arrestDate;
	}

	public void setExtendDetention(boolean extendDetention) {
		this.extendDetention = extendDetention;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public void setFine(BigDecimal fine) {
		this.fine = fine;
	}

	public void setProsecution(boolean prosecution) {
		this.prosecution = prosecution;
	}

	public void setPevocationDate(Date pevocationDate) {
		this.pevocationDate = pevocationDate;
	}

	public String getCaseTypeName() {
		return caseTypeName;
	}

	public String getRelatedThingsName() {
		return relatedThingsName;
	}

	public String getCaseSourceName() {
		return caseSourceName;
	}

	public String getCaseAreaName() {
		return caseAreaName;
	}

	public String getCasePlaceName() {
		return casePlaceName;
	}

	public void setCaseTypeName(String caseTypeName) {
		this.caseTypeName = caseTypeName;
	}

	public void setRelatedThingsName(String relatedThingsName) {
		this.relatedThingsName = relatedThingsName;
	}

	public void setCaseSourceName(String caseSourceName) {
		this.caseSourceName = caseSourceName;
	}

	public void setCaseAreaName(String caseAreaName) {
		this.caseAreaName = caseAreaName;
	}

	public void setCasePlaceName(String casePlaceName) {
		this.casePlaceName = casePlaceName;
	}

	public List<FdRelatedPerson> getPersonList() {
		return personList;
	}

	public String getPersonJson() {
		return personJson;
	}

	public void setPersonList(List<FdRelatedPerson> personList) {
		this.personList = personList;
	}

	public void setPersonJson(String personJson) {
		this.personJson = personJson;
	}

	public String getFilingYear() {
		return filingYear;
	}

	public String getFilingMonth() {
		return filingMonth;
	}

	public void setFilingYear(String filingYear) {
		this.filingYear = filingYear;
	}

	public void setFilingMonth(String filingMonth) {
		this.filingMonth = filingMonth;
	}

	public Integer getFilingCount() {
		return filingCount;
	}

	public void setFilingCount(Integer filingCount) {
		this.filingCount = filingCount;
	}
 
}
