package com.ruoyi.lawyer.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户信息对象 client
 * 
 * @author ruoyi
 * @date 2022-09-06
 */
public class Clients extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 客户  */
    @Excel(name = "客户")
    private String client;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phone;

    /** 客户类型 */
    @Excel(name = "客户类型")
    private Long type;

    /** 身份特征 */
    @Excel(name = "身份特征")
    private String typeinfo;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String cardnum;

    /** 主要负责人 */
    @Excel(name = "主要负责人")
    private String principal;

    /** 业务联系人 */
    @Excel(name = "业务联系人")
    private String contact;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** 职务 */
    @Excel(name = "职务")
    private String duty;

    /** 地区影响力 */
    @Excel(name = "地区影响力")
    private String affectedareas;

    /** 固定电话 */
    @Excel(name = "固定电话")
    private String tel;

    /** 所属地区 */
    @Excel(name = "所属地区")
    private String pertainarea;

    /** 客户状态 */
    @Excel(name = "客户状态")
    private Long status;

    /** 法定代表人 */
    @Excel(name = "法定代表人")
    private String legalperson;

    /** 法人联系电话*/
    @Excel(name = "法人联系电话")
    private String legalpersontel;

    /** 相关文件 */
    @Excel(name = "相关文件")
    private String file;

    /** 民族 */
    @Excel(name = "民族")
    private String nation;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 出生日期 */
    @Excel(name = "出生日期")
    private String birth;

    /** 详细地址 */
    @Excel(name = "详细地址")
    private String address;

    /** 详细地址 */
    @Excel(name = "备注")
    private String remark;

    /** 添加日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "添加日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date addtime;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long sysUserId;

    /** 状态 */
    @Excel(name = "状态")
    private Long assign;

    /**  */
    @Excel(name = "")
    private String fileurl;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setClient(String client) 
    {
        this.client = client;
    }

    public String getClient() 
    {
        return client;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setTypeinfo(String typeinfo) 
    {
        this.typeinfo = typeinfo;
    }

    public String getTypeinfo() 
    {
        return typeinfo;
    }
    public void setCardnum(String cardnum) 
    {
        this.cardnum = cardnum;
    }

    public String getCardnum() 
    {
        return cardnum;
    }
    public void setPrincipal(String principal) 
    {
        this.principal = principal;
    }

    public String getPrincipal() 
    {
        return principal;
    }
    public void setContact(String contact) 
    {
        this.contact = contact;
    }

    public String getContact() 
    {
        return contact;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setDuty(String duty) 
    {
        this.duty = duty;
    }

    public String getDuty() 
    {
        return duty;
    }
    public void setAffectedareas(String affectedareas) 
    {
        this.affectedareas = affectedareas;
    }

    public String getAffectedareas() 
    {
        return affectedareas;
    }
    public void setTel(String tel) 
    {
        this.tel = tel;
    }

    public String getTel() 
    {
        return tel;
    }
    public void setPertainarea(String pertainarea) 
    {
        this.pertainarea = pertainarea;
    }

    public String getPertainarea() 
    {
        return pertainarea;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setLegalperson(String legalperson) 
    {
        this.legalperson = legalperson;
    }

    public String getLegalperson() 
    {
        return legalperson;
    }
    public void setLegalpersontel(String legalpersontel) 
    {
        this.legalpersontel = legalpersontel;
    }

    public String getLegalpersontel() 
    {
        return legalpersontel;
    }
    public void setFile(String file) 
    {
        this.file = file;
    }

    public String getFile() 
    {
        return file;
    }
    public void setNation(String nation) 
    {
        this.nation = nation;
    }

    public String getNation() 
    {
        return nation;
    }
    public void setSex(String sex) 
    {
        this.sex = sex;
    }

    public String getSex() 
    {
        return sex;
    }
    public void setBirth(String birth) 
    {
        this.birth = birth;
    }

    public String getBirth() 
    {
        return birth;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setAddtime(Date addtime) 
    {
        this.addtime = addtime;
    }

    public Date getAddtime() 
    {
        return addtime;
    }

    public Long getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(Long sysUserId) {
        this.sysUserId = sysUserId;
    }

    public void setAssign(Long assign)
    {
        this.assign = assign;
    }

    public Long getAssign() 
    {
        return assign;
    }
    public void setFileurl(String fileurl) 
    {
        this.fileurl = fileurl;
    }

    public String getFileurl() 
    {
        return fileurl;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("client", getClient())
            .append("phone", getPhone())
            .append("type", getType())
            .append("typeinfo", getTypeinfo())
            .append("cardnum", getCardnum())
            .append("principal", getPrincipal())
            .append("contact", getContact())
            .append("email", getEmail())
            .append("duty", getDuty())
            .append("affectedareas", getAffectedareas())
            .append("tel", getTel())
            .append("pertainarea", getPertainarea())
            .append("status", getStatus())
            .append("legalperson", getLegalperson())
            .append("legalpersontel", getLegalpersontel())
            .append("file", getFile())
            .append("nation", getNation())
            .append("sex", getSex())
            .append("birth", getBirth())
            .append("address", getAddress())
            .append("remark", getRemark())
            .append("addtime", getAddtime())
            .append("sysUserId", getSysUserId())
            .append("assign", getAssign())
            .append("fileurl", getFileurl())
            .toString();
    }
}
