package com.ruoyi.act.domain;

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
    private Integer caseid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer typeid;

    @Excel(name = "案件类型")
    private String typeName;

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
            .toString();
    }
}
