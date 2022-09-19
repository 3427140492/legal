package com.ruoyi.client.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 客户管理对象 client
 * 
 * @author gk
 * @date 2022-09-14
 */
public class Client extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 委托人 */
    @Excel(name = "委托人")
    private String client;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String typeinfo;

    /** 身份证号码  */
    @Excel(name = "身份证号码 ")
    private String cardnum;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String principal;

    /** 联络人 */
    @Excel(name = "联络人")
    private String contact;

    /** 邮件 */
    @Excel(name = "邮件")
    private String email;

    /** 职责 */
    @Excel(name = "职责")
    private String duty;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String affectedareas;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String tel;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String pertainarea;

    /** 身份 */
    @Excel(name = "身份")
    private Long status;

    /** 法人代表 */
    @Excel(name = "法人代表")
    private String legalperson;

    /** 法方 */
    @Excel(name = "法方")
    private String legalpersontel;

    /** 文件  */
    @Excel(name = "文件 ")
    private String file;

    /** 民族 */
    @Excel(name = "民族")
    private String nation;

    /** 性别 */
    @Excel(name = "性别")
    private String sex;

    /** 出生 */
    @Excel(name = "出生")
    private String birth;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 发布时间 */
    @Excel(name = "发布时间")
    private String addtime;

    /** 系统用户 */
    @Excel(name = "系统用户")
    private Long systemUserId;

    /** 状态 */
    @Excel(name = "状态")
    private Long assign;

    /** 文件地址  */
    @Excel(name = "文件地址 ")
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
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
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
    public void setAddtime(String addtime) 
    {
        this.addtime = addtime;
    }

    public String getAddtime() 
    {
        return addtime;
    }
    public void setSystemUserId(Long systemUserId) 
    {
        this.systemUserId = systemUserId;
    }

    public Long getSystemUserId() 
    {
        return systemUserId;
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
            .append("systemUserId", getSystemUserId())
            .append("assign", getAssign())
            .append("fileurl", getFileurl())
            .toString();
    }
}
