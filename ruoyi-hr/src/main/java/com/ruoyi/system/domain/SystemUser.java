package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 系统用户对象 system_user
 * 
 * @author ruoyi
 * @date 2022-09-22
 */
public class SystemUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String userName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String userPwd;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String userRealname;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String userPhone;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String userEmail;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date userCreatedate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date userPreDate;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String userLoginNum;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hrEmpId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String userStatus;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setUserPwd(String userPwd) 
    {
        this.userPwd = userPwd;
    }

    public String getUserPwd() 
    {
        return userPwd;
    }
    public void setUserRealname(String userRealname) 
    {
        this.userRealname = userRealname;
    }

    public String getUserRealname() 
    {
        return userRealname;
    }
    public void setUserPhone(String userPhone) 
    {
        this.userPhone = userPhone;
    }

    public String getUserPhone() 
    {
        return userPhone;
    }
    public void setUserEmail(String userEmail) 
    {
        this.userEmail = userEmail;
    }

    public String getUserEmail() 
    {
        return userEmail;
    }
    public void setUserCreatedate(Date userCreatedate) 
    {
        this.userCreatedate = userCreatedate;
    }

    public Date getUserCreatedate() 
    {
        return userCreatedate;
    }
    public void setUserPreDate(Date userPreDate) 
    {
        this.userPreDate = userPreDate;
    }

    public Date getUserPreDate() 
    {
        return userPreDate;
    }
    public void setUserLoginNum(String userLoginNum) 
    {
        this.userLoginNum = userLoginNum;
    }

    public String getUserLoginNum() 
    {
        return userLoginNum;
    }
    public void setHrEmpId(String hrEmpId) 
    {
        this.hrEmpId = hrEmpId;
    }

    public String getHrEmpId() 
    {
        return hrEmpId;
    }
    public void setUserStatus(String userStatus) 
    {
        this.userStatus = userStatus;
    }

    public String getUserStatus() 
    {
        return userStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userName", getUserName())
            .append("userPwd", getUserPwd())
            .append("userRealname", getUserRealname())
            .append("userPhone", getUserPhone())
            .append("userEmail", getUserEmail())
            .append("userCreatedate", getUserCreatedate())
            .append("userPreDate", getUserPreDate())
            .append("userLoginNum", getUserLoginNum())
            .append("hrEmpId", getHrEmpId())
            .append("userStatus", getUserStatus())
            .toString();
    }
}
