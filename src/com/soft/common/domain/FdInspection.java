package com.soft.common.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;
/**
 *  
 * @类名称：FdInspection 
 * @类描述：食品药品稽查行政处罚案件实体类
 * @创建人：段志芳 
 * @创建时间：2017年7月7日 上午10:29:53 
 * @修改人：段志芳 
 * @修改时间：2017年7月7日 上午10:29:53 
 * @修改备注： 
 * @version 1.0.0 
 *
 */
@SuppressWarnings("serial")
public class FdInspection extends BaseEntity {
	@Resource(name = "ID")
	private Long id;
	
	@Resource(name = "案卷编号")
	private String caseCode;
	
	@Resource(name = "案件名称")
	private String caseName;
	
	@Resource(name = "处罚单位")
	private String execOrg;
	
	@Resource(name = "案件类型")
	private String caseType;
	
	@Resource(name = "涉案物品")
	private String relatedThings;
	
	@Resource(name = "案件属性")
	private String caseAttr;
	
	@Resource(name = "环节")
	private String caseLink;
	
	@Resource(name = "行政处罚决定文书号")
	private String punishDoc;
	
	@Resource(name = "违法企业名称")
	private String orgName;
	
	@Resource(name = "违法企业组织机构代码")
	private String orgCode;
	
	@Resource(name = "违法企业地址")
	private String orgAddress;
	
	@Resource(name = "法定代表人姓名")
	private String corporationName;
	
	@Resource(name = "主要负责人")
	private String leader;
	
	@Resource(name = "涉税信息")
	private String taxInfo;
	
	@Resource(name = "主要违法事实")
	private String illegalFacts;
	
	@Resource(name = "行政处罚的种类和依据")
	private String punishKind;
	
	@Resource(name = "行政处罚的履行方式和期限")
	private String punishExec;
	
	@Resource(name = "立案时间")
	private Date filingTime;
	
	@Resource(name = "结案时间")
	private Date closeCaseTime;
	
	@Resource(name = "作出处罚的日期")
	private Date punishDate;
	
	@Resource(name = "没收物价值")
	private BigDecimal confiscateValue;
	
	@Resource(name = "案件总值(元)")
	private BigDecimal caseAmount;
	
	@Resource(name = "销毁物价值")
	private BigDecimal destroyValue;
	
	@Resource(name = "罚款金额(元)")
	private BigDecimal fine;
	
	@Resource(name = "票号")
	private String billNo;
	
	@Resource(name = "缴款日期")
	private Date paymentTime;
	
	@Resource(name = "案件来源")
	private String caseSource;
	
	@Resource(name = "案件来源时间")
	private Date caseSourceTime;
	
	@Resource(name = "groupNo")
	private String groupNo;
	
	//扩展字段
	private String caseTypeName;

	private String relatedThingsName;
	
	private String caseLinkName;
	
	private String caseSourceName;
	
	private String caseAttrName;
	
	private String execOrgName;
	
	private String filingYear;
	
	private String filingMonth;
	
	private Integer filingCount;
	
	public Long getId() {
		return id;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public String getCaseName() {
		return caseName;
	}

	public String getExecOrg() {
		return execOrg;
	}

	public String getCaseType() {
		return caseType;
	}

	public String getRelatedThings() {
		return relatedThings;
	}

	public String getCaseAttr() {
		return caseAttr;
	}

	public String getCaseLink() {
		return caseLink;
	}

	public String getPunishDoc() {
		return punishDoc;
	}

	public String getOrgName() {
		return orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public String getOrgAddress() {
		return orgAddress;
	}

	public String getCorporationName() {
		return corporationName;
	}

	public String getLeader() {
		return leader;
	}

	public String getTaxInfo() {
		return taxInfo;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public void setTaxInfo(String taxInfo) {
		this.taxInfo = taxInfo;
	}

	public String getIllegalFacts() {
		return illegalFacts;
	}

	public String getPunishKind() {
		return punishKind;
	}

	public String getPunishExec() {
		return punishExec;
	}

	public Date getFilingTime() {
		return filingTime;
	}

	public Date getCloseCaseTime() {
		return closeCaseTime;
	}

	public Date getPunishDate() {
		return punishDate;
	}

	public BigDecimal getConfiscateValue() {
		return confiscateValue;
	}

	public BigDecimal getCaseAmount() {
		return caseAmount;
	}

	public BigDecimal getDestroyValue() {
		return destroyValue;
	}

	public BigDecimal getFine() {
		return fine;
	}

	public String getBillNo() {
		return billNo;
	}

	public Date getPaymentTime() {
		return paymentTime;
	}

	public String getCaseSource() {
		return caseSource;
	}

	public Date getCaseSourceTime() {
		return caseSourceTime;
	}

	public String getGroupNo() {
		return groupNo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public void setExecOrg(String execOrg) {
		this.execOrg = execOrg;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public void setRelatedThings(String relatedThings) {
		this.relatedThings = relatedThings;
	}

	public void setCaseAttr(String caseAttr) {
		this.caseAttr = caseAttr;
	}

	public void setCaseLink(String caseLink) {
		this.caseLink = caseLink;
	}

	public void setPunishDoc(String punishDoc) {
		this.punishDoc = punishDoc;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}

	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}

	public void setIllegalFacts(String illegalFacts) {
		this.illegalFacts = illegalFacts;
	}

	public void setPunishKind(String punishKind) {
		this.punishKind = punishKind;
	}

	public void setPunishExec(String punishExec) {
		this.punishExec = punishExec;
	}

	public void setFilingTime(Date filingTime) {
		this.filingTime = filingTime;
	}

	public void setCloseCaseTime(Date closeCaseTime) {
		this.closeCaseTime = closeCaseTime;
	}

	public void setPunishDate(Date punishDate) {
		this.punishDate = punishDate;
	}

	public void setConfiscateValue(BigDecimal confiscateValue) {
		this.confiscateValue = confiscateValue;
	}

	public void setCaseAmount(BigDecimal caseAmount) {
		this.caseAmount = caseAmount;
	}

	public void setDestroyValue(BigDecimal destroyValue) {
		this.destroyValue = destroyValue;
	}

	public void setFine(BigDecimal fine) {
		this.fine = fine;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	public void setCaseSource(String caseSource) {
		this.caseSource = caseSource;
	}

	public void setCaseSourceTime(Date caseSourceTime) {
		this.caseSourceTime = caseSourceTime;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	public String getCaseTypeName() {
		return caseTypeName;
	}

	public void setCaseTypeName(String caseTypeName) {
		this.caseTypeName = caseTypeName;
	}

	public String getRelatedThingsName() {
		return relatedThingsName;
	}

	public String getCaseLinkName() {
		return caseLinkName;
	}

	public String getCaseSourceName() {
		return caseSourceName;
	}

	public void setRelatedThingsName(String relatedThingsName) {
		this.relatedThingsName = relatedThingsName;
	}

	public void setCaseLinkName(String caseLinkName) {
		this.caseLinkName = caseLinkName;
	}

	public void setCaseSourceName(String caseSourceName) {
		this.caseSourceName = caseSourceName;
	}

	public String getCaseAttrName() {
		return caseAttrName;
	}

	public void setCaseAttrName(String caseAttrName) {
		this.caseAttrName = caseAttrName;
	}

	public String getExecOrgName() {
		return execOrgName;
	}

	public void setExecOrgName(String execOrgName) {
		this.execOrgName = execOrgName;
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
