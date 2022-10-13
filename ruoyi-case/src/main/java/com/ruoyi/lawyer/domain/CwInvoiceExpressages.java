package com.ruoyi.lawyer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 发票-快递业务中间对象 cw_invoice_expressage
 * 
 * @author ruoyi
 * @date 2022-09-28
 */
public class CwInvoiceExpressages extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 发票id */
    @Excel(name = "发票id")
    private Long cwInvoiceId;

    /** 快递id */
    @Excel(name = "快递id")
    private Long admExpressageId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setCwInvoiceId(Long cwInvoiceId) 
    {
        this.cwInvoiceId = cwInvoiceId;
    }

    public Long getCwInvoiceId()
    {
        return cwInvoiceId;
    }
    public void setAdmExpressageId(Long admExpressageId) 
    {
        this.admExpressageId = admExpressageId;
    }

    public Long getAdmExpressageId() 
    {
        return admExpressageId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("cwInvoiceId", getCwInvoiceId())
            .append("admExpressageId", getAdmExpressageId())
            .toString();
    }
}
