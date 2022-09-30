package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 快递类型对象 express_type
 *
 * @author ruoyi
 * @date 2022-09-29
 */
public class ExpressType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 序号 */
    @Excel(name = "序号")
    private String sorting;

    /** 快件类型 */
    @Excel(name = "快件类型")
    private String expressName;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
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
    public void setExpressName(String expressName)
    {
        this.expressName = expressName;
    }

    public String getExpressName()
    {
        return expressName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("sorting", getSorting())
                .append("expressName", getExpressName())
                .toString();
    }
}
