package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户角色对象 system_user_role
 * 
 * @author ruoyi
 * @date 2022-10-10
 */
public class SystemUserRole extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer systemUserId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long systemRoleId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String status;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setSystemUserId(Integer systemUserId)
    {
        this.systemUserId = systemUserId;
    }

    public Integer getSystemUserId()
    {
        return systemUserId;
    }
    public void setSystemRoleId(Long systemRoleId) 
    {
        this.systemRoleId = systemRoleId;
    }

    public Long getSystemRoleId() 
    {
        return systemRoleId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("systemUserId", getSystemUserId())
            .append("systemRoleId", getSystemRoleId())
            .append("status", getStatus())
            .toString();
    }
}
