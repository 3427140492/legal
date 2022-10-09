package com.ruoyi.persM.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 工作经历对象 hr_workhistory
 *
 * @author ruoyi
 * @date 2022-10-06
 */
public class WorkExperience extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 起止时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "起止时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date workhistoryStartdate;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date workhistoryEnddate;

    /** 工作单位 */
    @Excel(name = "工作单位")
    private String workhistoryWorkunit;

    /** 工作职务 */
    @Excel(name = "工作职务")
    private String workhistoryJobPosition;

    /** 工作类别 */
    @Excel(name = "工作类别")
    private String workhistoryWorktype;

    /** 单位性质 */
    @Excel(name = "单位性质")
    private String workhistoryWorknature;

    /** 所在地 */
    @Excel(name = "所在地")
    private String workhistorySite;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long hrEmpId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setWorkhistoryStartdate(Date workhistoryStartdate)
    {
        this.workhistoryStartdate = workhistoryStartdate;
    }

    public Date getWorkhistoryStartdate()
    {
        return workhistoryStartdate;
    }
    public void setWorkhistoryEnddate(Date workhistoryEnddate)
    {
        this.workhistoryEnddate = workhistoryEnddate;
    }

    public Date getWorkhistoryEnddate()
    {
        return workhistoryEnddate;
    }
    public void setWorkhistoryWorkunit(String workhistoryWorkunit)
    {
        this.workhistoryWorkunit = workhistoryWorkunit;
    }

    public String getWorkhistoryWorkunit()
    {
        return workhistoryWorkunit;
    }
    public void setWorkhistoryJobPosition(String workhistoryJobPosition)
    {
        this.workhistoryJobPosition = workhistoryJobPosition;
    }

    public String getWorkhistoryJobPosition()
    {
        return workhistoryJobPosition;
    }
    public void setWorkhistoryWorktype(String workhistoryWorktype)
    {
        this.workhistoryWorktype = workhistoryWorktype;
    }

    public String getWorkhistoryWorktype()
    {
        return workhistoryWorktype;
    }
    public void setWorkhistoryWorknature(String workhistoryWorknature)
    {
        this.workhistoryWorknature = workhistoryWorknature;
    }

    public String getWorkhistoryWorknature()
    {
        return workhistoryWorknature;
    }
    public void setWorkhistorySite(String workhistorySite)
    {
        this.workhistorySite = workhistorySite;
    }

    public String getWorkhistorySite()
    {
        return workhistorySite;
    }
    public void setHrEmpId(Long hrEmpId)
    {
        this.hrEmpId = hrEmpId;
    }

    public Long getHrEmpId()
    {
        return hrEmpId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("workhistoryStartdate", getWorkhistoryStartdate())
                .append("workhistoryEnddate", getWorkhistoryEnddate())
                .append("workhistoryWorkunit", getWorkhistoryWorkunit())
                .append("workhistoryJobPosition", getWorkhistoryJobPosition())
                .append("workhistoryWorktype", getWorkhistoryWorktype())
                .append("workhistoryWorknature", getWorkhistoryWorknature())
                .append("workhistorySite", getWorkhistorySite())
                .append("hrEmpId", getHrEmpId())
                .toString();
    }
}
