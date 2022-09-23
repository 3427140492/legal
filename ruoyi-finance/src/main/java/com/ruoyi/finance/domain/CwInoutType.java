package com.ruoyi.finance.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * ${subTable.functionName}对象 cw_inout_type
 * 
 * @author ruoyi
 * @date 2022-09-22
 */
public class CwInoutType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 类别名称 */
    @Excel(name = "类别名称")
    private String cwName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long cwPid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String type;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setCwName(String cwName) 
    {
        this.cwName = cwName;
    }

    public String getCwName() 
    {
        return cwName;
    }
    public void setCwPid(Long cwPid) 
    {
        this.cwPid = cwPid;
    }

    public Long getCwPid() 
    {
        return cwPid;
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
            .append("cwName", getCwName())
            .append("cwPid", getCwPid())
            .append("type", getType())
            .toString();
    }
}
