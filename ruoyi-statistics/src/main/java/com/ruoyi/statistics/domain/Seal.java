package com.ruoyi.statistics.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用印统计对象 case_seal_applyfor
 * 
 * @author ruoyi
 * @date 2022-09-21
 */
public class Seal extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String caseNo;

    /** 申请领用日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "领用日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String applyforReceive;

    /** 申请领用日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "领用开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String applyforReceive1;

    /** 申请领用日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "领用结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private String applyforReceive2;

    /** 审批状态 1,2,3 */
    @Excel(name = "审批状态 1,2,3")
    private String applyforApprovalStatus;

    /** 申请人 */
    @Excel(name = "申请人")
    private String applyforRecipient;

    /** 收函方 */
    private String sendPerson;

    /** 用印事项 */
    @Excel(name = "用印事项")
    private String applyforUsingTheItem;

    /** 案件id */
    @Excel(name = "案件id")
    private Long caseid;

    /** $column.columnComment */
    private Long typeid;

    /** $column.columnComment */
    private String empName;

    public String getEmpName() {
        return empName;
    }

    public String getApplyforReceive1() {
        return applyforReceive1;
    }

    public void setApplyforReceive1(String applyforReceive1) {
        this.applyforReceive1 = applyforReceive1;
    }

    public String getApplyforReceive2() {
        return applyforReceive2;
    }

    public void setApplyforReceive2(String applyforReceive2) {
        this.applyforReceive2 = applyforReceive2;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    /** 收结案统计信息 */
    private List<StatisticsCaseLaw> statisticsCaseLaw;

    private List<StatisticsHrEmp> statisticsHrEmpList;

    public List<StatisticsHrEmp> getStatisticsHrEmpList() {
        return statisticsHrEmpList;
    }

    public void setStatisticsHrEmpList(List<StatisticsHrEmp> statisticsHrEmpList) {
        this.statisticsHrEmpList = statisticsHrEmpList;
    }

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

    public List<StatisticsCaseLaw> getStatisticsCaseLaw() {
        return statisticsCaseLaw;
    }

    public void setStatisticsCaseLaw(List<StatisticsCaseLaw> statisticsCaseLaw) {
        this.statisticsCaseLaw = statisticsCaseLaw;
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
            .append("statisticsCaseLaw", getStatisticsCaseLaw())
                .append("statisticsHrEmpList",getStatisticsHrEmpList())
            .toString();
    }
}
