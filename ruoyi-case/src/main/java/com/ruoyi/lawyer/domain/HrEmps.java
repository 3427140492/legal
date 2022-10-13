package com.ruoyi.lawyer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 通讯录表对象 hr_emp
 * 
 * @author ruoyi
 * @date 2022-09-16
 */
public class HrEmps extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empBirthdate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empSex;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empWorkstatus;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empPaperstype;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empPapersnum;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empHigthEducation;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empHigthDegree;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empPosition;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hrNationId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hrPoliticalId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empMaritalsta;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empEntrancenum;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hrSpecialtyId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hrPractiseId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empPracnum;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empPracdate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empPracsite;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hrCertifyId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empCertifynum;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empGainway;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empGainsite;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empIntroduce;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empEntrydate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empSocialNum;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empContStartdate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empContEnddate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empInsured;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empPhone;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empPicture;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empSite;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empContactPhone;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empEmail;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empPostcode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empQq;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String empWechat;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setEmpName(String empName) 
    {
        this.empName = empName;
    }

    public String getEmpName() 
    {
        return empName;
    }
    public void setEmpBirthdate(String empBirthdate) 
    {
        this.empBirthdate = empBirthdate;
    }

    public String getEmpBirthdate() 
    {
        return empBirthdate;
    }
    public void setEmpSex(String empSex) 
    {
        this.empSex = empSex;
    }

    public String getEmpSex() 
    {
        return empSex;
    }
    public void setEmpWorkstatus(String empWorkstatus) 
    {
        this.empWorkstatus = empWorkstatus;
    }

    public String getEmpWorkstatus() 
    {
        return empWorkstatus;
    }
    public void setEmpPaperstype(String empPaperstype) 
    {
        this.empPaperstype = empPaperstype;
    }

    public String getEmpPaperstype() 
    {
        return empPaperstype;
    }
    public void setEmpPapersnum(String empPapersnum) 
    {
        this.empPapersnum = empPapersnum;
    }

    public String getEmpPapersnum() 
    {
        return empPapersnum;
    }
    public void setEmpHigthEducation(String empHigthEducation) 
    {
        this.empHigthEducation = empHigthEducation;
    }

    public String getEmpHigthEducation() 
    {
        return empHigthEducation;
    }
    public void setEmpHigthDegree(String empHigthDegree) 
    {
        this.empHigthDegree = empHigthDegree;
    }

    public String getEmpHigthDegree() 
    {
        return empHigthDegree;
    }
    public void setEmpPosition(String empPosition) 
    {
        this.empPosition = empPosition;
    }

    public String getEmpPosition() 
    {
        return empPosition;
    }
    public void setHrNationId(String hrNationId) 
    {
        this.hrNationId = hrNationId;
    }

    public String getHrNationId() 
    {
        return hrNationId;
    }
    public void setHrPoliticalId(String hrPoliticalId) 
    {
        this.hrPoliticalId = hrPoliticalId;
    }

    public String getHrPoliticalId() 
    {
        return hrPoliticalId;
    }
    public void setEmpMaritalsta(String empMaritalsta) 
    {
        this.empMaritalsta = empMaritalsta;
    }

    public String getEmpMaritalsta() 
    {
        return empMaritalsta;
    }
    public void setEmpEntrancenum(String empEntrancenum) 
    {
        this.empEntrancenum = empEntrancenum;
    }

    public String getEmpEntrancenum() 
    {
        return empEntrancenum;
    }
    public void setHrSpecialtyId(String hrSpecialtyId) 
    {
        this.hrSpecialtyId = hrSpecialtyId;
    }

    public String getHrSpecialtyId() 
    {
        return hrSpecialtyId;
    }
    public void setHrPractiseId(String hrPractiseId) 
    {
        this.hrPractiseId = hrPractiseId;
    }

    public String getHrPractiseId() 
    {
        return hrPractiseId;
    }
    public void setEmpPracnum(String empPracnum) 
    {
        this.empPracnum = empPracnum;
    }

    public String getEmpPracnum() 
    {
        return empPracnum;
    }
    public void setEmpPracdate(String empPracdate) 
    {
        this.empPracdate = empPracdate;
    }

    public String getEmpPracdate() 
    {
        return empPracdate;
    }
    public void setEmpPracsite(String empPracsite) 
    {
        this.empPracsite = empPracsite;
    }

    public String getEmpPracsite() 
    {
        return empPracsite;
    }
    public void setHrCertifyId(String hrCertifyId) 
    {
        this.hrCertifyId = hrCertifyId;
    }

    public String getHrCertifyId() 
    {
        return hrCertifyId;
    }
    public void setEmpCertifynum(String empCertifynum) 
    {
        this.empCertifynum = empCertifynum;
    }

    public String getEmpCertifynum() 
    {
        return empCertifynum;
    }
    public void setEmpGainway(String empGainway) 
    {
        this.empGainway = empGainway;
    }

    public String getEmpGainway() 
    {
        return empGainway;
    }
    public void setEmpGainsite(String empGainsite) 
    {
        this.empGainsite = empGainsite;
    }

    public String getEmpGainsite() 
    {
        return empGainsite;
    }
    public void setEmpIntroduce(String empIntroduce) 
    {
        this.empIntroduce = empIntroduce;
    }

    public String getEmpIntroduce() 
    {
        return empIntroduce;
    }
    public void setEmpEntrydate(String empEntrydate) 
    {
        this.empEntrydate = empEntrydate;
    }

    public String getEmpEntrydate() 
    {
        return empEntrydate;
    }
    public void setEmpSocialNum(String empSocialNum) 
    {
        this.empSocialNum = empSocialNum;
    }

    public String getEmpSocialNum() 
    {
        return empSocialNum;
    }
    public void setEmpContStartdate(String empContStartdate) 
    {
        this.empContStartdate = empContStartdate;
    }

    public String getEmpContStartdate() 
    {
        return empContStartdate;
    }
    public void setEmpContEnddate(String empContEnddate) 
    {
        this.empContEnddate = empContEnddate;
    }

    public String getEmpContEnddate() 
    {
        return empContEnddate;
    }
    public void setEmpInsured(String empInsured) 
    {
        this.empInsured = empInsured;
    }

    public String getEmpInsured() 
    {
        return empInsured;
    }
    public void setEmpPhone(String empPhone) 
    {
        this.empPhone = empPhone;
    }

    public String getEmpPhone() 
    {
        return empPhone;
    }
    public void setEmpPicture(String empPicture) 
    {
        this.empPicture = empPicture;
    }

    public String getEmpPicture() 
    {
        return empPicture;
    }
    public void setEmpSite(String empSite) 
    {
        this.empSite = empSite;
    }

    public String getEmpSite() 
    {
        return empSite;
    }
    public void setEmpContactPhone(String empContactPhone) 
    {
        this.empContactPhone = empContactPhone;
    }

    public String getEmpContactPhone() 
    {
        return empContactPhone;
    }
    public void setEmpEmail(String empEmail) 
    {
        this.empEmail = empEmail;
    }

    public String getEmpEmail() 
    {
        return empEmail;
    }
    public void setEmpPostcode(String empPostcode) 
    {
        this.empPostcode = empPostcode;
    }

    public String getEmpPostcode() 
    {
        return empPostcode;
    }
    public void setEmpQq(String empQq) 
    {
        this.empQq = empQq;
    }

    public String getEmpQq() 
    {
        return empQq;
    }
    public void setEmpWechat(String empWechat) 
    {
        this.empWechat = empWechat;
    }

    public String getEmpWechat() 
    {
        return empWechat;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("empName", getEmpName())
            .append("empBirthdate", getEmpBirthdate())
            .append("empSex", getEmpSex())
            .append("empWorkstatus", getEmpWorkstatus())
            .append("empPaperstype", getEmpPaperstype())
            .append("empPapersnum", getEmpPapersnum())
            .append("empHigthEducation", getEmpHigthEducation())
            .append("empHigthDegree", getEmpHigthDegree())
            .append("empPosition", getEmpPosition())
            .append("hrNationId", getHrNationId())
            .append("hrPoliticalId", getHrPoliticalId())
            .append("empMaritalsta", getEmpMaritalsta())
            .append("empEntrancenum", getEmpEntrancenum())
            .append("hrSpecialtyId", getHrSpecialtyId())
            .append("hrPractiseId", getHrPractiseId())
            .append("empPracnum", getEmpPracnum())
            .append("empPracdate", getEmpPracdate())
            .append("empPracsite", getEmpPracsite())
            .append("hrCertifyId", getHrCertifyId())
            .append("empCertifynum", getEmpCertifynum())
            .append("empGainway", getEmpGainway())
            .append("empGainsite", getEmpGainsite())
            .append("empIntroduce", getEmpIntroduce())
            .append("empEntrydate", getEmpEntrydate())
            .append("empSocialNum", getEmpSocialNum())
            .append("empContStartdate", getEmpContStartdate())
            .append("empContEnddate", getEmpContEnddate())
            .append("empInsured", getEmpInsured())
            .append("empPhone", getEmpPhone())
            .append("empPicture", getEmpPicture())
            .append("empSite", getEmpSite())
            .append("empContactPhone", getEmpContactPhone())
            .append("empEmail", getEmpEmail())
            .append("empPostcode", getEmpPostcode())
            .append("empQq", getEmpQq())
            .append("empWechat", getEmpWechat())
            .toString();
    }
}
