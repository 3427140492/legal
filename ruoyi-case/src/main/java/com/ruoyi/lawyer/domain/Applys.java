package com.ruoyi.lawyer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 操作记录对象 apply
 *
 * @author ruoyi
 * @date 2022-10-09
 */
public class Applys extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String caseNo;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String submiter;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String type;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String leader;

    /** 1,2,3 */
    @Excel(name = "1,2,3")
    private Long status;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long wid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String file;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String wtr;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setCaseNo(String caseNo)
    {
        this.caseNo = caseNo;
    }

    public String getCaseNo()
    {
        return caseNo;
    }
    public void setSubmiter(String submiter)
    {
        this.submiter = submiter;
    }

    public String getSubmiter()
    {
        return submiter;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setLeader(String leader)
    {
        this.leader = leader;
    }

    public String getLeader()
    {
        return leader;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }
    public void setWid(Long wid)
    {
        this.wid = wid;
    }

    public Long getWid()
    {
        return wid;
    }
    public void setFile(String file)
    {
        this.file = file;
    }

    public String getFile()
    {
        return file;
    }
    public void setWtr(String wtr)
    {
        this.wtr = wtr;
    }

    public String getWtr()
    {
        return wtr;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("caseNo", getCaseNo())
                .append("submiter", getSubmiter())
                .append("type", getType())
                .append("leader", getLeader())
                .append("status", getStatus())
                .append("wid", getWid())
                .append("remark", getRemark())
                .append("file", getFile())
                .append("wtr", getWtr())
                .toString();
    }
}