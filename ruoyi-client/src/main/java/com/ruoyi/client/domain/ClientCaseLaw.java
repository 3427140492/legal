package com.ruoyi.client.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 我的当事人对象 case_law
 * 
 * @author gk
 * @date 2022-09-16
 */
public class ClientCaseLaw extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 案号 */
    @Excel(name = "案号")
    private String caseNo;

    /** 对方当事人 */
    @Excel(name = "对方当事人")
    private String caseOppositeParties;

    /** 对方当事人证件号 */
    @Excel(name = "对方当事人证件号")
    private String caseOppositeCardnum;

    /** 第三方 */
    @Excel(name = "第三方")
    private String caseThirdParty;

    /** 嫌疑人 */
    @Excel(name = "嫌疑人")
    private String caseSuspect;

    /** 案件冲突 */
    @Excel(name = "案件冲突")
    private String caseClash;

    /** 案由 */
    @Excel(name = "案由")
    private String caseCause;

    /** 受理机关 */
    @Excel(name = "受理机关")
    private String caseAccept;

    /** 诉讼标的 */
    @Excel(name = "诉讼标的")
    private Long caseLawsuitobj;

    /** 补助Y/N */
    @Excel(name = "补助Y/N")
    private String caseSubsidy;

    /** 补助金额 */
    @Excel(name = "补助金额")
    private BigDecimal caseSubsidysal;

    /** 结案情况Y?N */
    @Excel(name = "结案情况Y?N")
    private String caseSettleStatus;

    /** 案件进度A待/B在办/C结案 */
    @Excel(name = "案件进度A待/B在办/C结案")
    private String casePlan;

    /** 收案审批状态:1:审批中2:审批通过3:审批不通过 */
    @Excel(name = "收案审批状态:1:审批中2:审批通过3:审批不通过")
    private String caseApproveStatus;

    /** 代理费 */
    @Excel(name = "代理费")
    private BigDecimal caseAgencyfee;

    /** 杂费 */
    @Excel(name = "杂费")
    private BigDecimal caseProxysal;

    /** 办理地区 */
    @Excel(name = "办理地区")
    private String caseTransactionRegion;

    /** 是否指派Y?N */
    @Excel(name = "是否指派Y?N")
    private String caseAssign;

    /** 已付金额 */
    @Excel(name = "已付金额")
    private BigDecimal casePaidsal;

    /** 未付金额 */
    @Excel(name = "未付金额")
    private BigDecimal caseUnpaidsal;

    /** 已开票金额 */
    @Excel(name = "已开票金额")
    private BigDecimal caseInvoiced;

    /** 未开票金额 */
    @Excel(name = "未开票金额")
    private BigDecimal caseNotinvoiced;

    /** 专属案号 */
    @Excel(name = "专属案号")
    private String casePropernum;

    /** 受理法院 */
    @Excel(name = "受理法院")
    private String caseCourt;

    /** 档案号 */
    @Excel(name = "档案号")
    private String caseFilenumber;

    /** 相关文件 */
    @Excel(name = "相关文件")
    private String caseCorrelationFile;

    /** 提交人 */
    @Excel(name = "提交人")
    private String caseSubmitter;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date caseSubtime;

    /** 案件回收 */
    @Excel(name = "案件回收")
    private String caseRecycle;

    /** 备注 */
    @Excel(name = "备注")
    private String caseRemarks;

    /** 客户id 外键 */
    @Excel(name = "客户id 外键")
    private Long clientId;

    /** 承办律师 */
    @Excel(name = "承办律师")
    private String caseAttorney;

    /** 诉讼阶段 */
    @Excel(name = "诉讼阶段")
    private String caseStatus;

    /** 诉讼地位 */
    @Excel(name = "诉讼地位")
    private String caseLawsuitStatus;

    /** 收费方式 */
    @Excel(name = "收费方式")
    private String caseChargeWay;

    /** 收案审批人 */
    @Excel(name = "收案审批人")
    private String systemApprovalId;

    /** 案件类型id 外键 */
    @Excel(name = "案件类型id 外键")
    private String caseCaseTypeId;

    /** 案件文书id 外键 */
    @Excel(name = "案件文书id 外键")
    private Long caseSealApplyforId;

    /** 主办律师id 外键 */
    @Excel(name = "主办律师id 外键")
    private Long hrEmpId;

    /** 收案时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收案时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date collectionTime;

    /** 委托人 */
    @Excel(name = "委托人")
    private String caseParties;

    /** 风险费 */
    @Excel(name = "风险费")
    private Long caseFxmoney;

    /** 委托人 */
    @Excel(name = "委托人")
    private String caseWtr;

    /** 合同状态1,2 */
    @Excel(name = "合同状态1,2")
    private String caseContract;

    /** 归档状态1,2 */
    @Excel(name = "归档状态1,2")
    private String caseRecord;

    /** 是否分享1,2 */
    @Excel(name = "是否分享1,2")
    private String caseShare;

    /** 结案方式(0=默认，1=调解，2=判决，3=撤诉，4=其他方式) */
    @Excel(name = "结案方式(0=默认，1=调解，2=判决，3=撤诉，4=其他方式)")
    private String caseSettleType;

    /** 结案审批时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结案审批时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date caseApprovalEndtime;

    /** 归档号 */
    @Excel(name = "归档号")
    private String caseRecordNum;

    /** 服务人次 */
    @Excel(name = "服务人次")
    private Long standard;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setCaseNo(String caseNo) 
    {
        this.caseNo = caseNo;
    }

    public String getCaseNo() 
    {
        return caseNo;
    }
    public void setCaseOppositeParties(String caseOppositeParties) 
    {
        this.caseOppositeParties = caseOppositeParties;
    }

    public String getCaseOppositeParties() 
    {
        return caseOppositeParties;
    }
    public void setCaseOppositeCardnum(String caseOppositeCardnum) 
    {
        this.caseOppositeCardnum = caseOppositeCardnum;
    }

    public String getCaseOppositeCardnum() 
    {
        return caseOppositeCardnum;
    }
    public void setCaseThirdParty(String caseThirdParty) 
    {
        this.caseThirdParty = caseThirdParty;
    }

    public String getCaseThirdParty() 
    {
        return caseThirdParty;
    }
    public void setCaseSuspect(String caseSuspect) 
    {
        this.caseSuspect = caseSuspect;
    }

    public String getCaseSuspect() 
    {
        return caseSuspect;
    }
    public void setCaseClash(String caseClash) 
    {
        this.caseClash = caseClash;
    }

    public String getCaseClash() 
    {
        return caseClash;
    }
    public void setCaseCause(String caseCause) 
    {
        this.caseCause = caseCause;
    }

    public String getCaseCause() 
    {
        return caseCause;
    }
    public void setCaseAccept(String caseAccept) 
    {
        this.caseAccept = caseAccept;
    }

    public String getCaseAccept() 
    {
        return caseAccept;
    }
    public void setCaseLawsuitobj(Long caseLawsuitobj) 
    {
        this.caseLawsuitobj = caseLawsuitobj;
    }

    public Long getCaseLawsuitobj() 
    {
        return caseLawsuitobj;
    }
    public void setCaseSubsidy(String caseSubsidy) 
    {
        this.caseSubsidy = caseSubsidy;
    }

    public String getCaseSubsidy() 
    {
        return caseSubsidy;
    }
    public void setCaseSubsidysal(BigDecimal caseSubsidysal) 
    {
        this.caseSubsidysal = caseSubsidysal;
    }

    public BigDecimal getCaseSubsidysal() 
    {
        return caseSubsidysal;
    }
    public void setCaseSettleStatus(String caseSettleStatus) 
    {
        this.caseSettleStatus = caseSettleStatus;
    }

    public String getCaseSettleStatus() 
    {
        return caseSettleStatus;
    }
    public void setCasePlan(String casePlan) 
    {
        this.casePlan = casePlan;
    }

    public String getCasePlan() 
    {
        return casePlan;
    }
    public void setCaseApproveStatus(String caseApproveStatus) 
    {
        this.caseApproveStatus = caseApproveStatus;
    }

    public String getCaseApproveStatus() 
    {
        return caseApproveStatus;
    }
    public void setCaseAgencyfee(BigDecimal caseAgencyfee) 
    {
        this.caseAgencyfee = caseAgencyfee;
    }

    public BigDecimal getCaseAgencyfee() 
    {
        return caseAgencyfee;
    }
    public void setCaseProxysal(BigDecimal caseProxysal) 
    {
        this.caseProxysal = caseProxysal;
    }

    public BigDecimal getCaseProxysal() 
    {
        return caseProxysal;
    }
    public void setCaseTransactionRegion(String caseTransactionRegion) 
    {
        this.caseTransactionRegion = caseTransactionRegion;
    }

    public String getCaseTransactionRegion() 
    {
        return caseTransactionRegion;
    }
    public void setCaseAssign(String caseAssign) 
    {
        this.caseAssign = caseAssign;
    }

    public String getCaseAssign() 
    {
        return caseAssign;
    }
    public void setCasePaidsal(BigDecimal casePaidsal) 
    {
        this.casePaidsal = casePaidsal;
    }

    public BigDecimal getCasePaidsal() 
    {
        return casePaidsal;
    }
    public void setCaseUnpaidsal(BigDecimal caseUnpaidsal) 
    {
        this.caseUnpaidsal = caseUnpaidsal;
    }

    public BigDecimal getCaseUnpaidsal() 
    {
        return caseUnpaidsal;
    }
    public void setCaseInvoiced(BigDecimal caseInvoiced) 
    {
        this.caseInvoiced = caseInvoiced;
    }

    public BigDecimal getCaseInvoiced() 
    {
        return caseInvoiced;
    }
    public void setCaseNotinvoiced(BigDecimal caseNotinvoiced) 
    {
        this.caseNotinvoiced = caseNotinvoiced;
    }

    public BigDecimal getCaseNotinvoiced() 
    {
        return caseNotinvoiced;
    }
    public void setCasePropernum(String casePropernum) 
    {
        this.casePropernum = casePropernum;
    }

    public String getCasePropernum() 
    {
        return casePropernum;
    }
    public void setCaseCourt(String caseCourt) 
    {
        this.caseCourt = caseCourt;
    }

    public String getCaseCourt() 
    {
        return caseCourt;
    }
    public void setCaseFilenumber(String caseFilenumber) 
    {
        this.caseFilenumber = caseFilenumber;
    }

    public String getCaseFilenumber() 
    {
        return caseFilenumber;
    }
    public void setCaseCorrelationFile(String caseCorrelationFile) 
    {
        this.caseCorrelationFile = caseCorrelationFile;
    }

    public String getCaseCorrelationFile() 
    {
        return caseCorrelationFile;
    }
    public void setCaseSubmitter(String caseSubmitter) 
    {
        this.caseSubmitter = caseSubmitter;
    }

    public String getCaseSubmitter() 
    {
        return caseSubmitter;
    }
    public void setCaseSubtime(Date caseSubtime) 
    {
        this.caseSubtime = caseSubtime;
    }

    public Date getCaseSubtime() 
    {
        return caseSubtime;
    }
    public void setCaseRecycle(String caseRecycle) 
    {
        this.caseRecycle = caseRecycle;
    }

    public String getCaseRecycle() 
    {
        return caseRecycle;
    }
    public void setCaseRemarks(String caseRemarks) 
    {
        this.caseRemarks = caseRemarks;
    }

    public String getCaseRemarks() 
    {
        return caseRemarks;
    }
    public void setClientId(Long clientId) 
    {
        this.clientId = clientId;
    }

    public Long getClientId() 
    {
        return clientId;
    }
    public void setCaseAttorney(String caseAttorney) 
    {
        this.caseAttorney = caseAttorney;
    }

    public String getCaseAttorney() 
    {
        return caseAttorney;
    }
    public void setCaseStatus(String caseStatus) 
    {
        this.caseStatus = caseStatus;
    }

    public String getCaseStatus() 
    {
        return caseStatus;
    }
    public void setCaseLawsuitStatus(String caseLawsuitStatus) 
    {
        this.caseLawsuitStatus = caseLawsuitStatus;
    }

    public String getCaseLawsuitStatus() 
    {
        return caseLawsuitStatus;
    }
    public void setCaseChargeWay(String caseChargeWay) 
    {
        this.caseChargeWay = caseChargeWay;
    }

    public String getCaseChargeWay() 
    {
        return caseChargeWay;
    }
    public void setSystemApprovalId(String systemApprovalId) 
    {
        this.systemApprovalId = systemApprovalId;
    }

    public String getSystemApprovalId() 
    {
        return systemApprovalId;
    }
    public void setCaseCaseTypeId(String caseCaseTypeId) 
    {
        this.caseCaseTypeId = caseCaseTypeId;
    }

    public String getCaseCaseTypeId() 
    {
        return caseCaseTypeId;
    }
    public void setCaseSealApplyforId(Long caseSealApplyforId) 
    {
        this.caseSealApplyforId = caseSealApplyforId;
    }

    public Long getCaseSealApplyforId() 
    {
        return caseSealApplyforId;
    }
    public void setHrEmpId(Long hrEmpId) 
    {
        this.hrEmpId = hrEmpId;
    }

    public Long getHrEmpId() 
    {
        return hrEmpId;
    }
    public void setCollectionTime(Date collectionTime) 
    {
        this.collectionTime = collectionTime;
    }

    public Date getCollectionTime() 
    {
        return collectionTime;
    }
    public void setCaseParties(String caseParties) 
    {
        this.caseParties = caseParties;
    }

    public String getCaseParties() 
    {
        return caseParties;
    }
    public void setCaseFxmoney(Long caseFxmoney) 
    {
        this.caseFxmoney = caseFxmoney;
    }

    public Long getCaseFxmoney() 
    {
        return caseFxmoney;
    }
    public void setCaseWtr(String caseWtr) 
    {
        this.caseWtr = caseWtr;
    }

    public String getCaseWtr() 
    {
        return caseWtr;
    }
    public void setCaseContract(String caseContract) 
    {
        this.caseContract = caseContract;
    }

    public String getCaseContract() 
    {
        return caseContract;
    }
    public void setCaseRecord(String caseRecord) 
    {
        this.caseRecord = caseRecord;
    }

    public String getCaseRecord() 
    {
        return caseRecord;
    }
    public void setCaseShare(String caseShare) 
    {
        this.caseShare = caseShare;
    }

    public String getCaseShare() 
    {
        return caseShare;
    }
    public void setCaseSettleType(String caseSettleType) 
    {
        this.caseSettleType = caseSettleType;
    }

    public String getCaseSettleType() 
    {
        return caseSettleType;
    }
    public void setCaseApprovalEndtime(Date caseApprovalEndtime) 
    {
        this.caseApprovalEndtime = caseApprovalEndtime;
    }

    public Date getCaseApprovalEndtime() 
    {
        return caseApprovalEndtime;
    }
    public void setCaseRecordNum(String caseRecordNum) 
    {
        this.caseRecordNum = caseRecordNum;
    }

    public String getCaseRecordNum() 
    {
        return caseRecordNum;
    }
    public void setStandard(Long standard) 
    {
        this.standard = standard;
    }

    public Long getStandard() 
    {
        return standard;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("caseNo", getCaseNo())
            .append("caseOppositeParties", getCaseOppositeParties())
            .append("caseOppositeCardnum", getCaseOppositeCardnum())
            .append("caseThirdParty", getCaseThirdParty())
            .append("caseSuspect", getCaseSuspect())
            .append("caseClash", getCaseClash())
            .append("caseCause", getCaseCause())
            .append("caseAccept", getCaseAccept())
            .append("caseLawsuitobj", getCaseLawsuitobj())
            .append("caseSubsidy", getCaseSubsidy())
            .append("caseSubsidysal", getCaseSubsidysal())
            .append("caseSettleStatus", getCaseSettleStatus())
            .append("casePlan", getCasePlan())
            .append("caseApproveStatus", getCaseApproveStatus())
            .append("caseAgencyfee", getCaseAgencyfee())
            .append("caseProxysal", getCaseProxysal())
            .append("caseTransactionRegion", getCaseTransactionRegion())
            .append("caseAssign", getCaseAssign())
            .append("casePaidsal", getCasePaidsal())
            .append("caseUnpaidsal", getCaseUnpaidsal())
            .append("caseInvoiced", getCaseInvoiced())
            .append("caseNotinvoiced", getCaseNotinvoiced())
            .append("casePropernum", getCasePropernum())
            .append("caseCourt", getCaseCourt())
            .append("caseFilenumber", getCaseFilenumber())
            .append("caseCorrelationFile", getCaseCorrelationFile())
            .append("caseSubmitter", getCaseSubmitter())
            .append("caseSubtime", getCaseSubtime())
            .append("caseRecycle", getCaseRecycle())
            .append("caseRemarks", getCaseRemarks())
            .append("clientId", getClientId())
            .append("caseAttorney", getCaseAttorney())
            .append("caseStatus", getCaseStatus())
            .append("caseLawsuitStatus", getCaseLawsuitStatus())
            .append("caseChargeWay", getCaseChargeWay())
            .append("systemApprovalId", getSystemApprovalId())
            .append("caseCaseTypeId", getCaseCaseTypeId())
            .append("caseSealApplyforId", getCaseSealApplyforId())
            .append("hrEmpId", getHrEmpId())
            .append("collectionTime", getCollectionTime())
            .append("caseParties", getCaseParties())
            .append("caseFxmoney", getCaseFxmoney())
            .append("caseWtr", getCaseWtr())
            .append("caseContract", getCaseContract())
            .append("caseRecord", getCaseRecord())
            .append("caseShare", getCaseShare())
            .append("caseSettleType", getCaseSettleType())
            .append("caseApprovalEndtime", getCaseApprovalEndtime())
            .append("caseRecordNum", getCaseRecordNum())
            .append("standard", getStandard())
            .toString();
    }
}
