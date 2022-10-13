package com.ruoyi.lawyer.domain;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 律师业绩对象 cw_performancesplit
 * 
 * @author ruoyi
 * @date 2022-09-29
 */
public class CwPerformancesplits extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String empName;

    /** $column.columnComment */
    private String id;

    /** 案件id */
    @Excel(name = "案件id")
    private Long caseLawId;

    /** 员工id */
    @Excel(name = "员工id")
    private Long hrEmpId;

    /** 承办律师类型 主办律师 协助律师 督办律师
     * 实习律师 */
    @Excel(name = "承办律师类型 主办律师 协助律师 督办律师 实习律师")
    private String caseAttorney;

    /** 业绩分成 */
    @Excel(name = "业绩分成")
    private Long cwPerformancesplit;

    /** 分成方式 */
    @Excel(name = "分成方式")
    private String cwSplitType;

    /** 实际提成金额 */
    @Excel(name = "实际提成金额")
    private BigDecimal cwRealSplitMoney;

    /** 1代表案件  2代表咨询代书 */
    @Excel(name = "1代表案件  2代表咨询代书")
    private String type;


    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
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
    public void setHrEmpId(Long hrEmpId) 
    {
        this.hrEmpId = hrEmpId;
    }

    public Long getHrEmpId() 
    {
        return hrEmpId;
    }
    public void setCaseAttorney(String caseAttorney) 
    {
        this.caseAttorney = caseAttorney;
    }

    public String getCaseAttorney() 
    {
        return caseAttorney;
    }
    public void setCwPerformancesplit(Long cwPerformancesplit) 
    {
        this.cwPerformancesplit = cwPerformancesplit;
    }

    public Long getCwPerformancesplit() 
    {
        return cwPerformancesplit;
    }
    public void setCwSplitType(String cwSplitType) 
    {
        this.cwSplitType = cwSplitType;
    }

    public String getCwSplitType() 
    {
        return cwSplitType;
    }
    public void setCwRealSplitMoney(BigDecimal cwRealSplitMoney) 
    {
        this.cwRealSplitMoney = cwRealSplitMoney;
    }

    public BigDecimal getCwRealSplitMoney() 
    {
        return cwRealSplitMoney;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("caseLawId", getCaseLawId())
            .append("hrEmpId", getHrEmpId())
            .append("caseAttorney", getCaseAttorney())
            .append("cwPerformancesplit", getCwPerformancesplit())
            .append("cwSplitType", getCwSplitType())
            .append("cwRealSplitMoney", getCwRealSplitMoney())
            .append("type", getType())
            .append("empName",getEmpName())
            .toString();
    }
}
