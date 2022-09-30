package com.ruoyi.system.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 快速登记对象 adm_expressage
 * 
 * @author ruoyi
 * @date 2022-09-23
 */
public class AdmExpressage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

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
    private Integer expressageSendWaayId;

    /** 快件类型 */
    @Excel(name = "快件类型")
    private Integer expressageExpressTypeId;

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
    private Integer takestatus;

    /** 快递费 */
    @Excel(name = "快递费")
    private Integer expressFee;

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

    /** 快递方式 */
    @Excel(name = "快递方式")
    private String sendName;

    /** 快件类型 */
    @Excel(name = "快件类型")
    private String expressName;

    private String selectStr;

    private String selectType;

    public void setSelectType(String selectType)
    {
        this.selectType = selectType;
    }

    public String getSelectType()
    {
        return selectType;
    }

    public void setSelectStr(String selectStr)
    {
        this.selectStr = selectStr;
    }

    public String getSelectStr()
    {
        return selectStr;
    }

    public void setSendName(String sendName)
    {
        this.sendName = sendName;
    }

    public String getSendName()
    {
        return sendName;
    }

    public void setexpressName(String expressName)
    {
        this.expressName = expressName;
    }

    public String getexpressName()
    {
        return expressName;
    }


    /** 发送信息 */
    private List<SendWaay> sendWaayList;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
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
    public void setExpressageSendWaayId(Integer expressageSendWaayId) 
    {
        this.expressageSendWaayId = expressageSendWaayId;
    }

    public Integer getExpressageSendWaayId() 
    {
        return expressageSendWaayId;
    }
    public void setExpressageExpressTypeId(Integer expressageExpressTypeId) 
    {
        this.expressageExpressTypeId = expressageExpressTypeId;
    }

    public Integer getExpressageExpressTypeId() 
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
    public void setTakestatus(Integer takestatus) 
    {
        this.takestatus = takestatus;
    }

    public Integer getTakestatus() 
    {
        return takestatus;
    }
    public void setExpressFee(Integer expressFee) 
    {
        this.expressFee = expressFee;
    }

    public Integer getExpressFee() 
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

    public List<SendWaay> getSendWaayList()
    {
        return sendWaayList;
    }

    public void setSendWaayList(List<SendWaay> sendWaayList)
    {
        this.sendWaayList = sendWaayList;
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
            .append("sendWaayList", getSendWaayList())
            .append("sendName", getSendName())
            .toString();
    }
}
