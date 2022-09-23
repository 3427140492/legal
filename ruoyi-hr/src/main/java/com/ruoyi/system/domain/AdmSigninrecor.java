package com.ruoyi.system.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 签到对象 adm_signinrecor
 * 
 * @author ruoyi
 * @date 2022-09-22
 */
public class AdmSigninrecor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 用户id 外键 */
    @Excel(name = "用户id 外键")
    private Integer systemUserId;

    /** 签到类型 */
    @Excel(name = "签到类型")
    private Integer classify;

    /** 签到日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "签到日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date signinrecordSign;

    /** 签到地点 */
    @Excel(name = "签到地点")
    private String signinrecordSite;

    /** 签到类型 D 签到 T 签退 */
    @Excel(name = "签到类型 D 签到 T 签退")
    private String signinStatus;


    /** $column.columnComment */
    @Excel(name = "")
    private String userRealname;


    /** 系统用户信息 */
    private List<SystemUser> systemUserList;

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
    public void setClassify(Integer classify) 
    {
        this.classify = classify;
    }

    public Integer getClassify() 
    {
        return classify;
    }
    public void setSigninrecordSign(Date signinrecordSign) 
    {
        this.signinrecordSign = signinrecordSign;
    }

    public Date getSigninrecordSign() 
    {
        return signinrecordSign;
    }
    public void setSigninrecordSite(String signinrecordSite) 
    {
        this.signinrecordSite = signinrecordSite;
    }

    public String getSigninrecordSite() 
    {
        return signinrecordSite;
    }
    public void setSigninStatus(String signinStatus) 
    {
        this.signinStatus = signinStatus;
    }

    public String getSigninStatus() 
    {
        return signinStatus;
    }

    public List<SystemUser> getSystemUserList()
    {
        return systemUserList;
    }

    public void setSystemUserList(List<SystemUser> systemUserList)
    {
        this.systemUserList = systemUserList;
    }

    public void setUserRealname(String userRealname)
    {
        this.userRealname = userRealname;
    }

    public String getUserRealname()
    {
        return userRealname;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("systemUserId", getSystemUserId())
            .append("classify", getClassify())
            .append("signinrecordSign", getSigninrecordSign())
            .append("signinrecordSite", getSigninrecordSite())
            .append("signinStatus", getSigninStatus())
            .append("systemUserList", getSystemUserList())
            .append("userRealname", getUserRealname())
            .toString();
    }
}
