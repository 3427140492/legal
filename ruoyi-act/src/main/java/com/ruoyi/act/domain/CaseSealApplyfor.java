package com.ruoyi.act.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用印申请对象 case_seal_applyfor
 * 
 * @author ruoyi
 * @date 2022-09-14
 */
public class CaseSealApplyfor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String caseNo;

    /** 申请领用日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请领用日期")
    private String applyforReceive;

    /** 审批状态 1,2,3 */
    @Excel(name = "审批状态 1,2,3")
    private String applyforApprovalStatus;

    /** 申请人  */
    @Excel(name = "申请人")
    private String applyforRecipient;

    /** 收函方 */
    @Excel(name = "收函方")
    private String sendPerson;

    /** 用印事项 */
    @Excel(name = "用印事项")
    private String applyforUsingTheItem;

    /** 案件id */
    @Excel(name = "案件id")
    private Integer caseid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer typeid;

    @Excel(name = "案件类型")
    private String typeName;

// -----
    @Excel(name = "提交时间")
    private String caseSubtime;

    /** 代理费 */
    @Excel(name = "代理费")
    private BigDecimal caseAgencyfee;

    /** 收案审批状态:1:审批中2:审批通过3:审批不通过 */
    @Excel(name = "收案审批状态:1:审批中2:审批通过3:审批不通过")
    private String caseApproveStatus;

    /** 案由 */
    @Excel(name = "案由")
    private String caseCause;

    /** 收费方式 */
    @Excel(name = "收费方式")
    private String caseChargeWay;

    /** 受理法院 */
    @Excel(name = "受理法院")
    private String caseCourt;

    /** 已开票金额 */
    @Excel(name = "已开票金额")
    private BigDecimal caseInvoiced;

    /** 诉讼地位 */
    @Excel(name = "诉讼地位")
    private String caseLawsuitStatus;

    /** 对方当事人 */
    @Excel(name = "对方当事人")
    private String caseOppositeParties;

    /** 已付金额 */
    @Excel(name = "已付金额")
    private BigDecimal casePaidsal;

    /** 备注 */
    @Excel(name = "备注")
    private String caseRemarks;

    /** 诉讼阶段 */
    @Excel(name = "诉讼阶段")
    private String caseStatus;

    /** 委托人 */
    @Excel(name = "委托人")
    private String caseWtr;


    /** $table.subTable.functionName信息 */
    private List<CaseFiles> caseFilesList;

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
    public void setApplyforReceive(String applyforReceive)
    {
        this.applyforReceive = applyforReceive;
    }

    public String getApplyforReceive()
    {
        return applyforReceive;
    }
    public void setApplyforApprovalStatus(String applyforApprovalStatus) 
    {
        this.applyforApprovalStatus = applyforApprovalStatus;
    }

    public String getApplyforApprovalStatus() 
    {
        return applyforApprovalStatus;
    }
    public void setApplyforRecipient(String applyforRecipient) 
    {
        this.applyforRecipient = applyforRecipient;
    }

    public String getApplyforRecipient() 
    {
        return applyforRecipient;
    }
    public void setSendPerson(String sendPerson) 
    {
        this.sendPerson = sendPerson;
    }

    public String getSendPerson() 
    {
        return sendPerson;
    }
    public void setApplyforUsingTheItem(String applyforUsingTheItem) 
    {
        this.applyforUsingTheItem = applyforUsingTheItem;
    }

    public String getApplyforUsingTheItem() 
    {
        return applyforUsingTheItem;
    }
    public void setCaseid(Integer caseid) 
    {
        this.caseid = caseid;
    }

    public Integer getCaseid() 
    {
        return caseid;
    }
    public void setTypeid(Integer typeid) 
    {
        this.typeid = typeid;
    }

    public Integer getTypeid() 
    {
        return typeid;
    }
    public void setTypeName(String typeName)
    {
        this.typeName = typeName;
    }

    public String getTypeName()
    {
        return typeName;
    }

    public List<CaseFiles> getCaseFilesList()
    {
        return caseFilesList;
    }

    public void setCaseFilesList(List<CaseFiles> caseFilesList)
    {
        this.caseFilesList = caseFilesList;
    }
//-------
    public void setCaseSubtime(String caseSubtime)
{
    this.caseSubtime = caseSubtime;
}

    public String getCaseSubtime()
    {
        return caseSubtime;
    }
    public void setCaseAgencyfee(BigDecimal caseAgencyfee)
{
    this.caseAgencyfee = caseAgencyfee;
}

    public BigDecimal getCaseAgencyfee()
    {
        return caseAgencyfee;
    }
    public void setCaseApproveStatus(String caseApproveStatus)
{
    this.caseApproveStatus = caseApproveStatus;
}

    public String getCaseApproveStatus()
    {
        return caseApproveStatus;
    }
    public void setCaseCause(String caseCause)
    {
        this.caseCause = caseCause;
    }

    public String getCaseCause()
    {
        return caseCause;
    }
    public void setCaseChargeWay(String caseChargeWay)
{
    this.caseChargeWay = caseChargeWay;
}

    public String getCaseChargeWay()
    {
        return caseChargeWay;
    }
    public void setCaseCourt(String caseCourt)
{
    this.caseCourt = caseCourt;
}

    public String getCaseCourt()
    {
        return caseCourt;
    }
    public void setCaseInvoiced(BigDecimal caseInvoiced)
{
    this.caseInvoiced = caseInvoiced;
}

    public BigDecimal getCaseInvoiced()
    {
        return caseInvoiced;
    }
    public void setCaseLawsuitStatus(String caseLawsuitStatus)
    {
        this.caseLawsuitStatus = caseLawsuitStatus;
    }

    public String getCaseLawsuitStatus()
    {
        return caseLawsuitStatus;
    }
    public void setCaseOppositeParties(String caseOppositeParties)
    {
        this.caseOppositeParties = caseOppositeParties;
    }

    public String getCaseOppositeParties()
    {
        return caseOppositeParties;
    }
    public void setCasePaidsal(BigDecimal casePaidsal)
    {
        this.casePaidsal = casePaidsal;
    }

    public BigDecimal getCasePaidsal()
    {
        return casePaidsal;
    }
    public void setCaseRemarks(String caseRemarks)
    {
        this.caseRemarks = caseRemarks;
    }

    public String getCaseRemarks()
    {
        return caseRemarks;
    }
    public void setCaseStatus(String caseStatus)
    {
        this.caseStatus = caseStatus;
    }

    public String getCaseStatus()
    {
        return caseStatus;
    }
    public void setCaseWtr(String caseWtr)
    {
        this.caseWtr = caseWtr;
    }

    public String getCaseWtr()
    {
        return caseWtr;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("caseNo", getCaseNo())
            .append("applyforReceive", getApplyforReceive())
            .append("applyforApprovalStatus", getApplyforApprovalStatus())
            .append("applyforRecipient", getApplyforRecipient())
            .append("sendPerson", getSendPerson())
            .append("applyforUsingTheItem", getApplyforUsingTheItem())
            .append("caseid", getCaseid())
            .append("remark", getRemark())
            .append("typeid", getTypeid())
            .append("typeName", getTypeName())
            .append("caseFilesList", getCaseFilesList())
                .append("caseAgencyfee", getCaseAgencyfee())
                .append("caseApproveStatus", getCaseApproveStatus())
                .append("caseCause", getCaseCause())
                .append("caseChargeWay", getCaseChargeWay())
                .append("caseCourt", getCaseCourt())
                .append("caseInvoiced", getCaseInvoiced())
                .append("caseLawsuitStatus", getCaseLawsuitStatus())
                .append("caseOppositeParties", getCaseOppositeParties())
                .append("casePaidsal", getCasePaidsal())
                .append("caseRemarks", getCaseRemarks())
                .append("caseStatus", getCaseStatus())
                .append("caseWtr", getCaseWtr())
                .append("caseSubtime",getCaseSubtime())
            .toString();
    }
}
