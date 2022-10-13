package com.ruoyi.lawyer.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 案件类型对象 case_case_type
 * 
 * @author ruoyi
 * @date 2022-09-03
 */
public class CaseCaseTypes extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String caseTypeName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long typePid;

    /** 最小金额 */
    @Excel(name = "最小金额")
    private BigDecimal typeMoneyMin;

    /** 最大金额 */
    @Excel(name = "最大金额")
    private BigDecimal typeMoneyMax;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCaseTypeName(String caseTypeName) 
    {
        this.caseTypeName = caseTypeName;
    }

    public String getCaseTypeName() 
    {
        return caseTypeName;
    }
    public void setTypePid(Long typePid) 
    {
        this.typePid = typePid;
    }

    public Long getTypePid() 
    {
        return typePid;
    }
    public void setTypeMoneyMin(BigDecimal typeMoneyMin) 
    {
        this.typeMoneyMin = typeMoneyMin;
    }

    public BigDecimal getTypeMoneyMin() 
    {
        return typeMoneyMin;
    }
    public void setTypeMoneyMax(BigDecimal typeMoneyMax) 
    {
        this.typeMoneyMax = typeMoneyMax;
    }

    public BigDecimal getTypeMoneyMax() 
    {
        return typeMoneyMax;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("caseTypeName", getCaseTypeName())
            .append("typePid", getTypePid())
            .append("typeMoneyMin", getTypeMoneyMin())
            .append("typeMoneyMax", getTypeMoneyMax())
            .toString();
    }
}
