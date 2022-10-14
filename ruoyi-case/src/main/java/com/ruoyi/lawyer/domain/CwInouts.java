package com.ruoyi.lawyer.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 收支对象 cw_inout
 * 
 * @author ck
 * @date 2022-09-25
 */
public class CwInouts extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 收支类别id */
    @Excel(name = "收支类别id")
    private Long cwInoutTypeId;

    /** 发生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cwInoutDate;

    /** 收支人 */
    @Excel(name = "收支人")
    private String cwInoutPerson;

    /** 收支金额 */
    @Excel(name = "收支金额")
    private Long cwInoutMoney;

    /** 支付方式 */
    @Excel(name = "支付方式")
    private Long cwPayType;

    /** 案件id */
    @Excel(name = "案件id")
    private Long caseLawId;

    /** 录入日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "录入日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cwEnteringdate;

    /** 备注 */
    @Excel(name = "备注")
    private String cwRemark;

    /** 付款人 */
    @Excel(name = "付款人")
    private String cwPayer;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setCwInoutTypeId(Long cwInoutTypeId) 
    {
        this.cwInoutTypeId = cwInoutTypeId;
    }

    public Long getCwInoutTypeId() 
    {
        return cwInoutTypeId;
    }
    public void setCwInoutDate(Date cwInoutDate) 
    {
        this.cwInoutDate = cwInoutDate;
    }

    public Date getCwInoutDate() 
    {
        return cwInoutDate;
    }
    public void setCwInoutPerson(String cwInoutPerson) 
    {
        this.cwInoutPerson = cwInoutPerson;
    }

    public String getCwInoutPerson() 
    {
        return cwInoutPerson;
    }
    public void setCwInoutMoney(Long cwInoutMoney) 
    {
        this.cwInoutMoney = cwInoutMoney;
    }

    public Long getCwInoutMoney() 
    {
        return cwInoutMoney;
    }
    public void setCwPayType(Long cwPayType) 
    {
        this.cwPayType = cwPayType;
    }

    public Long getCwPayType() 
    {
        return cwPayType;
    }
    public void setCaseLawId(Long caseLawId) 
    {
        this.caseLawId = caseLawId;
    }

    public Long getCaseLawId() 
    {
        return caseLawId;
    }
    public void setCwEnteringdate(Date cwEnteringdate) 
    {
        this.cwEnteringdate = cwEnteringdate;
    }

    public Date getCwEnteringdate() 
    {
        return cwEnteringdate;
    }
    public void setCwRemark(String cwRemark) 
    {
        this.cwRemark = cwRemark;
    }

    public String getCwRemark() 
    {
        return cwRemark;
    }
    public void setCwPayer(String cwPayer) 
    {
        this.cwPayer = cwPayer;
    }

    public String getCwPayer() 
    {
        return cwPayer;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("cwInoutTypeId", getCwInoutTypeId())
            .append("cwInoutDate", getCwInoutDate())
            .append("cwInoutPerson", getCwInoutPerson())
            .append("cwInoutMoney", getCwInoutMoney())
            .append("cwPayType", getCwPayType())
            .append("caseLawId", getCaseLawId())
            .append("cwEnteringdate", getCwEnteringdate())
            .append("cwRemark", getCwRemark())
            .append("cwPayer", getCwPayer())
            .toString();
    }
}
