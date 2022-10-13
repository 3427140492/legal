package com.ruoyi.lawyer.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 快递业务对象 adm_expressage
 * 
 * @author ck
 * @date 2022-09-27
 */
public class AdmExpressages extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 发件人 */
    @Excel(name = "发件人")
    private String systemUserAddresser;

    /** 收件人 */
    @Excel(name = "收件人")
    private String systemUserRecipients;

    /** 收件单位 */
    @Excel(name = "收件单位")
    private String expressageSendaunit;

    /** 发件日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发件日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expressageSendadate;

    /** 收件日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收件日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expressageDateofreceipt;

    /** 领件时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "领件时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expressageBringDate;

    /** 快递方式 */
    @Excel(name = "快递方式")
    private Long expressageSendWaayId;

    /** 快件类型 */
    @Excel(name = "快件类型")
    private Long expressageExpressTypeId;

    /** 快递单号 */
    @Excel(name = "快递单号")
    private Long expressageCourierNumber;

    /** 文件 */
    @Excel(name = "文件")
    private String expressageFile;

    /** 领件说明 */
    @Excel(name = "领件说明")
    private String expressageCollarnote;

    /** 备注说明 */
    @Excel(name = "备注说明")
    private String expressageRemark;

    /** 领件状态 2 已领取 1 未领取 */
    @Excel(name = "领件状态 2 已领取 1 未领取")
    private Long takestatus;

    /** 快递费 */
    @Excel(name = "快递费")
    private BigDecimal expressFee;

    /** 领件人 */
    @Excel(name = "领件人")
    private String collarPerson;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String expressageTel;

    /** 快递信息类型 S 收件 F 发件 */
    @Excel(name = "快递信息类型 S 收件 F 发件")
    private String expressageType;

    /** 案件id 外键 */
    @Excel(name = "案件id 外键")
    private String caseNo;

    /** 物流公司 */
    @Excel(name = "物流公司")
    private String logistics;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSystemUserAddresser(String systemUserAddresser) 
    {
        this.systemUserAddresser = systemUserAddresser;
    }

    public String getSystemUserAddresser() 
    {
        return systemUserAddresser;
    }
    public void setSystemUserRecipients(String systemUserRecipients) 
    {
        this.systemUserRecipients = systemUserRecipients;
    }

    public String getSystemUserRecipients() 
    {
        return systemUserRecipients;
    }
    public void setExpressageSendaunit(String expressageSendaunit) 
    {
        this.expressageSendaunit = expressageSendaunit;
    }

    public String getExpressageSendaunit() 
    {
        return expressageSendaunit;
    }
    public void setExpressageSendadate(Date expressageSendadate) 
    {
        this.expressageSendadate = expressageSendadate;
    }

    public Date getExpressageSendadate() 
    {
        return expressageSendadate;
    }
    public void setExpressageDateofreceipt(Date expressageDateofreceipt) 
    {
        this.expressageDateofreceipt = expressageDateofreceipt;
    }

    public Date getExpressageDateofreceipt() 
    {
        return expressageDateofreceipt;
    }
    public void setExpressageBringDate(Date expressageBringDate) 
    {
        this.expressageBringDate = expressageBringDate;
    }

    public Date getExpressageBringDate() 
    {
        return expressageBringDate;
    }
    public void setExpressageSendWaayId(Long expressageSendWaayId) 
    {
        this.expressageSendWaayId = expressageSendWaayId;
    }

    public Long getExpressageSendWaayId() 
    {
        return expressageSendWaayId;
    }
    public void setExpressageExpressTypeId(Long expressageExpressTypeId) 
    {
        this.expressageExpressTypeId = expressageExpressTypeId;
    }

    public Long getExpressageExpressTypeId() 
    {
        return expressageExpressTypeId;
    }
    public void setExpressageCourierNumber(Long expressageCourierNumber) 
    {
        this.expressageCourierNumber = expressageCourierNumber;
    }

    public Long getExpressageCourierNumber() 
    {
        return expressageCourierNumber;
    }
    public void setExpressageFile(String expressageFile) 
    {
        this.expressageFile = expressageFile;
    }

    public String getExpressageFile() 
    {
        return expressageFile;
    }
    public void setExpressageCollarnote(String expressageCollarnote) 
    {
        this.expressageCollarnote = expressageCollarnote;
    }

    public String getExpressageCollarnote() 
    {
        return expressageCollarnote;
    }
    public void setExpressageRemark(String expressageRemark) 
    {
        this.expressageRemark = expressageRemark;
    }

    public String getExpressageRemark() 
    {
        return expressageRemark;
    }
    public void setTakestatus(Long takestatus) 
    {
        this.takestatus = takestatus;
    }

    public Long getTakestatus() 
    {
        return takestatus;
    }
    public void setExpressFee(BigDecimal expressFee) 
    {
        this.expressFee = expressFee;
    }

    public BigDecimal getExpressFee() 
    {
        return expressFee;
    }
    public void setCollarPerson(String collarPerson) 
    {
        this.collarPerson = collarPerson;
    }

    public String getCollarPerson() 
    {
        return collarPerson;
    }
    public void setExpressageTel(String expressageTel) 
    {
        this.expressageTel = expressageTel;
    }

    public String getExpressageTel() 
    {
        return expressageTel;
    }
    public void setExpressageType(String expressageType) 
    {
        this.expressageType = expressageType;
    }

    public String getExpressageType() 
    {
        return expressageType;
    }
    public void setCaseNo(String caseNo) 
    {
        this.caseNo = caseNo;
    }

    public String getCaseNo() 
    {
        return caseNo;
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("systemUserAddresser", getSystemUserAddresser())
            .append("systemUserRecipients", getSystemUserRecipients())
            .append("expressageSendaunit", getExpressageSendaunit())
            .append("expressageSendadate", getExpressageSendadate())
            .append("expressageDateofreceipt", getExpressageDateofreceipt())
            .append("expressageBringDate", getExpressageBringDate())
            .append("expressageSendWaayId", getExpressageSendWaayId())
            .append("expressageExpressTypeId", getExpressageExpressTypeId())
            .append("expressageCourierNumber", getExpressageCourierNumber())
            .append("expressageFile", getExpressageFile())
            .append("expressageCollarnote", getExpressageCollarnote())
            .append("expressageRemark", getExpressageRemark())
            .append("takestatus", getTakestatus())
            .append("expressFee", getExpressFee())
            .append("collarPerson", getCollarPerson())
            .append("expressageTel", getExpressageTel())
            .append("expressageType", getExpressageType())
            .append("caseNo", getCaseNo())
            .append("logistics",getLogistics())
            .toString();
    }
}
