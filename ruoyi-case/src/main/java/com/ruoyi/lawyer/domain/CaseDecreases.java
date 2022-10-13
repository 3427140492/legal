package com.ruoyi.lawyer.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 减免申请对象 case_decrease
 * 
 * @author ck
 * @date 2022-09-20
 */
public class CaseDecreases extends BaseEntity
{
    private static final long serialVersionUID = 1L;


    private CaseLaws caseLaw;

    private String caseSubmitter;
    private String caseNo;

    /** $column.columnComment */
    private Long id;

    /** 案件信息表_外键 */
    @Excel(name = "案件信息表_外键")
    private Long caseLawId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String person;

    /** 申请减免金额 */
    @Excel(name = "申请减免金额")
    private Long decreaseMoney;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date applyDate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String reason;

    /** 1,2,3 */
    @Excel(name = "1,2,3")
    private Long status;

    public String getCaseSubmitter() {
        return caseSubmitter;
    }

    public void setCaseSubmitter(String caseSubmitter) {
        this.caseSubmitter = caseSubmitter;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public CaseLaws getCaseLaw() {
        return caseLaw;
    }

    public void setCaseLaw(CaseLaws caseLaw) {
        this.caseLaw = caseLaw;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCaseLawId(Long caseLawId) 
    {
        this.caseLawId = caseLawId;
    }

    public Long getCaseLawId() 
    {
        return caseLawId;
    }
    public void setPerson(String person) 
    {
        this.person = person;
    }

    public String getPerson() 
    {
        return person;
    }
    public void setDecreaseMoney(Long decreaseMoney) 
    {
        this.decreaseMoney = decreaseMoney;
    }

    public Long getDecreaseMoney() 
    {
        return decreaseMoney;
    }
    public void setApplyDate(Date applyDate) 
    {
        this.applyDate = applyDate;
    }

    public Date getApplyDate() 
    {
        return applyDate;
    }
    public void setReason(String reason) 
    {
        this.reason = reason;
    }

    public String getReason() 
    {
        return reason;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("caseLawId", getCaseLawId())
            .append("person", getPerson())
            .append("decreaseMoney", getDecreaseMoney())
            .append("applyDate", getApplyDate())
            .append("reason", getReason())
            .append("status", getStatus())
                .append("caseSubmitter",getCaseSubmitter())
                .append("caseNo",getCaseNo())
            .toString();
    }
}
