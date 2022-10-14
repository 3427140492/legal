package com.ruoyi.lawyer.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 发票对象 cw_invoice
 * 
 * @author ck
 * @date 2022-09-27
 */
public class CwInvoices extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 发票抬头 */
    @Excel(name = "发票抬头")
    private String cwInvoiceTitle;

    /** 发票金额 */
    @Excel(name = "发票金额")
    private Long cwInvoiceMoney;

    /** 申请日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cwApplicationDate;

    /** 开票日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开票日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cwInvoiceDate;

    /** 开票状态 */
    @Excel(name = "开票状态")
    private String cwInvoiceStatus;

    /** 发票号 */
    @Excel(name = "发票号")
    private String cwInvoiceNumber;

    /** 开具类型 */
    @Excel(name = "开具类型")
    private Long cwOpenType;

    /** 发票类型 */
    @Excel(name = "发票类型")
    private Long cwInvoiceType;

    /** 开票项目 */
    @Excel(name = "开票项目")
    private Long cwInvoiceItem;

    /** 纳税人识别号 */
    @Excel(name = "纳税人识别号")
    private String cwTaxpayerIdentityNumber;

    /** 基本开户银行 */
    @Excel(name = "基本开户银行")
    private String cwBasicBankofdeposit;

    /** 注册地址 */
    @Excel(name = "注册地址")
    private String cwRegisteredAddress;

    /** 固定电话 */
    @Excel(name = "固定电话")
    private String cwFixedlineTelephone;

    /** 税务登记证副本(文件id) */
    @Excel(name = "税务登记证副本(文件id)")
    private String recordFileId;

    /** 案件表外键id */
    @Excel(name = "案件表外键id")
    private String caseLawId;

    /** 申请备注 */
    @Excel(name = "申请备注")
    private String cwApplicationRemark;

    /** 开票备注 */
    @Excel(name = "开票备注")
    private String cwInvoiceRemark;

    /** 基本开户账号 */
    @Excel(name = "基本开户账号")
    private String cwAccountNumber;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setCwInvoiceTitle(String cwInvoiceTitle) 
    {
        this.cwInvoiceTitle = cwInvoiceTitle;
    }

    public String getCwInvoiceTitle() 
    {
        return cwInvoiceTitle;
    }
    public void setCwInvoiceMoney(Long cwInvoiceMoney) 
    {
        this.cwInvoiceMoney = cwInvoiceMoney;
    }

    public Long getCwInvoiceMoney() 
    {
        return cwInvoiceMoney;
    }
    public void setCwApplicationDate(Date cwApplicationDate) 
    {
        this.cwApplicationDate = cwApplicationDate;
    }

    public Date getCwApplicationDate() 
    {
        return cwApplicationDate;
    }
    public void setCwInvoiceDate(Date cwInvoiceDate) 
    {
        this.cwInvoiceDate = cwInvoiceDate;
    }

    public Date getCwInvoiceDate() 
    {
        return cwInvoiceDate;
    }
    public void setCwInvoiceStatus(String cwInvoiceStatus) 
    {
        this.cwInvoiceStatus = cwInvoiceStatus;
    }

    public String getCwInvoiceStatus() 
    {
        return cwInvoiceStatus;
    }
    public void setCwInvoiceNumber(String cwInvoiceNumber) 
    {
        this.cwInvoiceNumber = cwInvoiceNumber;
    }

    public String getCwInvoiceNumber() 
    {
        return cwInvoiceNumber;
    }
    public void setCwOpenType(Long cwOpenType) 
    {
        this.cwOpenType = cwOpenType;
    }

    public Long getCwOpenType() 
    {
        return cwOpenType;
    }
    public void setCwInvoiceType(Long cwInvoiceType) 
    {
        this.cwInvoiceType = cwInvoiceType;
    }

    public Long getCwInvoiceType() 
    {
        return cwInvoiceType;
    }
    public void setCwInvoiceItem(Long cwInvoiceItem) 
    {
        this.cwInvoiceItem = cwInvoiceItem;
    }

    public Long getCwInvoiceItem() 
    {
        return cwInvoiceItem;
    }
    public void setCwTaxpayerIdentityNumber(String cwTaxpayerIdentityNumber) 
    {
        this.cwTaxpayerIdentityNumber = cwTaxpayerIdentityNumber;
    }

    public String getCwTaxpayerIdentityNumber() 
    {
        return cwTaxpayerIdentityNumber;
    }
    public void setCwBasicBankofdeposit(String cwBasicBankofdeposit) 
    {
        this.cwBasicBankofdeposit = cwBasicBankofdeposit;
    }

    public String getCwBasicBankofdeposit() 
    {
        return cwBasicBankofdeposit;
    }
    public void setCwRegisteredAddress(String cwRegisteredAddress) 
    {
        this.cwRegisteredAddress = cwRegisteredAddress;
    }

    public String getCwRegisteredAddress() 
    {
        return cwRegisteredAddress;
    }
    public void setCwFixedlineTelephone(String cwFixedlineTelephone) 
    {
        this.cwFixedlineTelephone = cwFixedlineTelephone;
    }

    public String getCwFixedlineTelephone() 
    {
        return cwFixedlineTelephone;
    }
    public void setRecordFileId(String recordFileId) 
    {
        this.recordFileId = recordFileId;
    }

    public String getRecordFileId() 
    {
        return recordFileId;
    }
    public void setCaseLawId(String caseLawId) 
    {
        this.caseLawId = caseLawId;
    }

    public String getCaseLawId() 
    {
        return caseLawId;
    }
    public void setCwApplicationRemark(String cwApplicationRemark) 
    {
        this.cwApplicationRemark = cwApplicationRemark;
    }

    public String getCwApplicationRemark() 
    {
        return cwApplicationRemark;
    }
    public void setCwInvoiceRemark(String cwInvoiceRemark) 
    {
        this.cwInvoiceRemark = cwInvoiceRemark;
    }

    public String getCwInvoiceRemark() 
    {
        return cwInvoiceRemark;
    }
    public void setCwAccountNumber(String cwAccountNumber) 
    {
        this.cwAccountNumber = cwAccountNumber;
    }

    public String getCwAccountNumber() 
    {
        return cwAccountNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("cwInvoiceTitle", getCwInvoiceTitle())
            .append("cwInvoiceMoney", getCwInvoiceMoney())
            .append("cwApplicationDate", getCwApplicationDate())
            .append("cwInvoiceDate", getCwInvoiceDate())
            .append("cwInvoiceStatus", getCwInvoiceStatus())
            .append("cwInvoiceNumber", getCwInvoiceNumber())
            .append("cwOpenType", getCwOpenType())
            .append("cwInvoiceType", getCwInvoiceType())
            .append("cwInvoiceItem", getCwInvoiceItem())
            .append("cwTaxpayerIdentityNumber", getCwTaxpayerIdentityNumber())
            .append("cwBasicBankofdeposit", getCwBasicBankofdeposit())
            .append("cwRegisteredAddress", getCwRegisteredAddress())
            .append("cwFixedlineTelephone", getCwFixedlineTelephone())
            .append("recordFileId", getRecordFileId())
            .append("caseLawId", getCaseLawId())
            .append("cwApplicationRemark", getCwApplicationRemark())
            .append("cwInvoiceRemark", getCwInvoiceRemark())
            .append("cwAccountNumber", getCwAccountNumber())
            .toString();
    }
}
