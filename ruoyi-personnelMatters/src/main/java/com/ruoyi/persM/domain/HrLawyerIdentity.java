package com.ruoyi.persM.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * ${subTable.functionName}对象 hr_lawyer_identity
 * 
 * @author ruoyi
 * @date 2022-09-27
 */
public class HrLawyerIdentity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String identityName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String identityTutor;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String identityType;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date identityStartdate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date identityEnddate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String identityNum;

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
    public void setIdentityName(String identityName) 
    {
        this.identityName = identityName;
    }

    public String getIdentityName() 
    {
        return identityName;
    }
    public void setIdentityTutor(String identityTutor) 
    {
        this.identityTutor = identityTutor;
    }

    public String getIdentityTutor() 
    {
        return identityTutor;
    }
    public void setIdentityType(String identityType) 
    {
        this.identityType = identityType;
    }

    public String getIdentityType() 
    {
        return identityType;
    }
    public void setIdentityStartdate(Date identityStartdate) 
    {
        this.identityStartdate = identityStartdate;
    }

    public Date getIdentityStartdate() 
    {
        return identityStartdate;
    }
    public void setIdentityEnddate(Date identityEnddate) 
    {
        this.identityEnddate = identityEnddate;
    }

    public Date getIdentityEnddate() 
    {
        return identityEnddate;
    }
    public void setIdentityNum(String identityNum) 
    {
        this.identityNum = identityNum;
    }

    public String getIdentityNum() 
    {
        return identityNum;
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
            .append("identityName", getIdentityName())
            .append("identityTutor", getIdentityTutor())
            .append("identityType", getIdentityType())
            .append("identityStartdate", getIdentityStartdate())
            .append("identityEnddate", getIdentityEnddate())
            .append("identityNum", getIdentityNum())
            .append("hrEmpId", getHrEmpId())
            .toString();
    }
}
