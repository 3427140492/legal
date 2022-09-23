package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 发送对象 send_waay
 * 
 * @author ruoyi
 * @date 2022-09-23
 */
public class SendWaay extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 序号 */
    @Excel(name = "序号")
    private String sorting;

    /** 快递方式 */
    @Excel(name = "快递方式")
    private String sendName;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setSorting(String sorting) 
    {
        this.sorting = sorting;
    }

    public String getSorting() 
    {
        return sorting;
    }
    public void setSendName(String sendName) 
    {
        this.sendName = sendName;
    }

    public String getSendName() 
    {
        return sendName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("sorting", getSorting())
            .append("sendName", getSendName())
            .toString();
    }
}
