package com.ruoyi.lawyer.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 公函记录对象 case_seal_applyfor
 * 
 * @author ruoyi
 * @date 2022-10-03
 */
public class CaseSealApplyfors extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String caseNo;

    /** 申请领用日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请领用日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date applyforReceive;

    /** 审批状态 1,2,3 */
    @Excel(name = "审批状态 1,2,3")
    private String applyforApprovalStatus;

    /** 申请人 */
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
    private Long caseid;

    private String remark;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long typeid;

    /** 案件文件信息 */
    private CaseFiless caseFiles;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
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
    public void setApplyforReceive(Date applyforReceive) 
    {
        this.applyforReceive = applyforReceive;
    }

    public Date getApplyforReceive() 
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
    public void setCaseid(Long caseid) 
    {
        this.caseid = caseid;
    }

    public Long getCaseid() 
    {
        return caseid;
    }
    public void setTypeid(Long typeid) 
    {
        this.typeid = typeid;
    }

    public Long getTypeid() 
    {
        return typeid;
    }

    public CaseFiless getCaseFiles()
    {
        return caseFiles;
    }

    public void setCaseFiles(CaseFiless caseFiles)
    {
        this.caseFiles = caseFiles;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
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
            .append("caseFiles", getCaseFiles())
            .toString();
    }
}
