package com.ruoyi.persM.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 教育经历对象 hr_degree
 * 
 * @author ruoyi
 * @date 2022-10-10
 */
public class Education extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date degreeStartdate;

    /** 结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date degreeEnddate;

    /** 毕业学校 */
    @Excel(name = "毕业学校")
    private String degreeSchool;

    /** 主修专业 */
    @Excel(name = "主修专业")
    private String degreeMajor;

    /** 获得学历 */
    @Excel(name = "获得学历")
    private String degreeEducation;

    /** 获得学位 */
    @Excel(name = "获得学位")
    private String degreeDegrees;

    /** 证书编号 */
    @Excel(name = "证书编号")
    private String degreeCrednum;

    /** 全日制 */
    @Excel(name = "全日制")
    private String degreeFulltime;

    /** 所在地 */
    @Excel(name = "所在地")
    private String degreeSite;

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
    public void setDegreeStartdate(Date degreeStartdate) 
    {
        this.degreeStartdate = degreeStartdate;
    }

    public Date getDegreeStartdate() 
    {
        return degreeStartdate;
    }
    public void setDegreeEnddate(Date degreeEnddate) 
    {
        this.degreeEnddate = degreeEnddate;
    }

    public Date getDegreeEnddate() 
    {
        return degreeEnddate;
    }
    public void setDegreeSchool(String degreeSchool) 
    {
        this.degreeSchool = degreeSchool;
    }

    public String getDegreeSchool() 
    {
        return degreeSchool;
    }
    public void setDegreeMajor(String degreeMajor) 
    {
        this.degreeMajor = degreeMajor;
    }

    public String getDegreeMajor() 
    {
        return degreeMajor;
    }
    public void setDegreeEducation(String degreeEducation) 
    {
        this.degreeEducation = degreeEducation;
    }

    public String getDegreeEducation() 
    {
        return degreeEducation;
    }
    public void setDegreeDegrees(String degreeDegrees) 
    {
        this.degreeDegrees = degreeDegrees;
    }

    public String getDegreeDegrees() 
    {
        return degreeDegrees;
    }
    public void setDegreeCrednum(String degreeCrednum) 
    {
        this.degreeCrednum = degreeCrednum;
    }

    public String getDegreeCrednum() 
    {
        return degreeCrednum;
    }
    public void setDegreeFulltime(String degreeFulltime) 
    {
        this.degreeFulltime = degreeFulltime;
    }

    public String getDegreeFulltime() 
    {
        return degreeFulltime;
    }
    public void setDegreeSite(String degreeSite) 
    {
        this.degreeSite = degreeSite;
    }

    public String getDegreeSite() 
    {
        return degreeSite;
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
            .append("degreeStartdate", getDegreeStartdate())
            .append("degreeEnddate", getDegreeEnddate())
            .append("degreeSchool", getDegreeSchool())
            .append("degreeMajor", getDegreeMajor())
            .append("degreeEducation", getDegreeEducation())
            .append("degreeDegrees", getDegreeDegrees())
            .append("degreeCrednum", getDegreeCrednum())
            .append("degreeFulltime", getDegreeFulltime())
            .append("degreeSite", getDegreeSite())
            .append("hrEmpId", getHrEmpId())
            .toString();
    }
}
