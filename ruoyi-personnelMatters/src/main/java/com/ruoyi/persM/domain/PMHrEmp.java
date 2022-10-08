package com.ruoyi.persM.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 人事档案对象 hr_emp
 *
 * @author ruoyi
 * @date 2022-09-27
 */
public class PMHrEmp extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String empName;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date empBirthdate;

    /** 性别 */
    @Excel(name = "性别")
    private String empSex;

    /** 是否在职 */
    @Excel(name = "是否在职")
    private String empWorkstatus;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String empPaperstype;

    /** 证件号 */
    @Excel(name = "证件号")
    private String empPapersnum;

    /** 最高学历 */
    @Excel(name = "最高学历")
    private String empHigthEducation;

    /** 最高学位 */
    @Excel(name = "最高学位")
    private String empHigthDegree;

    /** 所内身份 */
    @Excel(name = "所内身份")
    private String empPosition;

    /** 民族 */
    @Excel(name = "民族")
    private String hrNationId;

    /** 政治面貌 */
    @Excel(name = "政治面貌")
    private String hrPoliticalId;

    /** 婚姻情况 */
    @Excel(name = "婚姻情况")
    private String empMaritalsta;

    /** 门禁号 */
    @Excel(name = "门禁号")
    private String empEntrancenum;

    /** 专业部 */
    @Excel(name = "专业部")
    private String hrSpecialtyId;

    /** 执业类别 */
    @Excel(name = "执业类别")
    private String hrPractiseId;

    /** 执业证号码 */
    @Excel(name = "执业证号码")
    private String empPracnum;

    /** 首次执业时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "首次执业时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date empPracdate;

    /** 首次执业地 */
    @Excel(name = "首次执业地")
    private String empPracsite;

    /** 资格证类别
     */
    @Excel(name = "资格证类别 ")
    private String hrCertifyId;

    /** 资格证号码 */
    @Excel(name = "资格证号码")
    private String empCertifynum;

    /** 资格证取得方式 */
    @Excel(name = "资格证取得方式")
    private String empGainway;

    /** 资格证取得地 */
    @Excel(name = "资格证取得地")
    private String empGainsite;

    /** 个人介绍 */
    @Excel(name = "个人介绍")
    private String empIntroduce;

    /** 入职时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入职时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String empEntrydate;

    /** 社保号码 */
    @Excel(name = "社保号码")
    private String empSocialNum;

    /** 合同开始 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合同开始", width = 30, dateFormat = "yyyy-MM-dd")
    private Date empContStartdate;

    /** 合同截止 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "合同截止", width = 30, dateFormat = "yyyy-MM-dd")
    private String empContEnddate;

    /** 是否参保 */
    @Excel(name = "是否参保")
    private String empInsured;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String empPhone;

    /** 照片 */
    @Excel(name = "照片")
    private String empPicture;

    /** 联系地址 */
    @Excel(name = "联系地址")
    private String empSite;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String empContactPhone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String empEmail;

    /** 邮政编码 */
    @Excel(name = "邮政编码")
    private String empPostcode;

    /** QQ号码 */
    @Excel(name = "QQ号码")
    private String empQq;

    /** 微信号 */
    @Excel(name = "微信号")
    private String empWechat;

    /** 身份 */
    @Excel(name = "身份")
    private String identityName;

    /** $table.subTable.functionName信息 */
    private List<HrLawyerIdentity> hrLawyerIdentityList;

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
    public void setEmpBirthdate(Date empBirthdate)
    {
        this.empBirthdate = empBirthdate;
    }

    public Date getEmpBirthdate()
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
    public void setEmpPracdate(Date empPracdate)
    {
        this.empPracdate = empPracdate;
    }

    public Date getEmpPracdate()
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
    public void setEmpContStartdate(Date empContStartdate)
    {
        this.empContStartdate = empContStartdate;
    }

    public Date getEmpContStartdate()
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

    public List<HrLawyerIdentity> getHrLawyerIdentityList()
    {
        return hrLawyerIdentityList;
    }

    public String getIdentityName() {
        return identityName;
    }

    public void setIdentityName(String identityName) {
        this.identityName = identityName;
    }

    public void setHrLawyerIdentityList(List<HrLawyerIdentity> hrLawyerIdentityList)
    {
        this.hrLawyerIdentityList = hrLawyerIdentityList;
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
                .append("hrLawyerIdentityList", getHrLawyerIdentityList())
                .append("identityName",getIdentityName())
                .toString();
    }
}
