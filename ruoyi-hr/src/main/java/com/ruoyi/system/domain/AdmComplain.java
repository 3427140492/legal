package com.ruoyi.system.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 建议投诉对象 adm_complain
 * 
 * @author ruoyi
 * @date 2022-09-21
 */
public class AdmComplain extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 投诉内容 */
    @Excel(name = "投诉内容")
    private String complainContent;

    /** 案件id 外键 */
    @Excel(name = "案件id 外键")
    private Integer lawCaseId;

    /** 客户id 外键 */
    @Excel(name = "客户id 外键")
    private Integer clientId;

    /** 提交日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date complainSubmitTime;

    /** 回复数 */
    @Excel(name = "回复数")
    private Integer complainReplyNumber;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;


    /** 案件 */
    @Excel(name = "案件信息")
    private String caseno;

    /** 案件 */
    @Excel(name = "客户名称")
    private String clientname;


    private String client;

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

    public void setCaseno(String caseno)
    {
        this.caseno = caseno;
    }

    public String getCaseno()
    {
        return caseno;
    }

    public void setClientname(String clientname)
    {
        this.clientname = clientname;
    }

    public String getClientname()
    {
        return clientname;
    }



    /** 客户信息 */
    private List<HrClient> hrClientList;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setComplainContent(String complainContent) 
    {
        this.complainContent = complainContent;
    }

    public String getComplainContent() 
    {
        return complainContent;
    }
    public void setLawCaseId(Integer lawCaseId) 
    {
        this.lawCaseId = lawCaseId;
    }

    public Integer getLawCaseId() 
    {
        return lawCaseId;
    }
    public void setClientId(Integer clientId) 
    {
        this.clientId = clientId;
    }

    public Integer getClientId() 
    {
        return clientId;
    }
    public void setComplainSubmitTime(Date complainSubmitTime) 
    {
        this.complainSubmitTime = complainSubmitTime;
    }

    public Date getComplainSubmitTime() 
    {
        return complainSubmitTime;
    }
    public void setComplainReplyNumber(Integer complainReplyNumber) 
    {
        this.complainReplyNumber = complainReplyNumber;
    }

    public Integer getComplainReplyNumber() 
    {
        return complainReplyNumber;
    }

    public List<HrClient> getClientList()
    {
        return hrClientList;
    }

    public void setClientList(List<HrClient> hrClientList)
    {
        this.hrClientList = hrClientList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("complainContent", getComplainContent())
            .append("lawCaseId", getLawCaseId())
            .append("clientId", getClientId())
            .append("complainSubmitTime", getComplainSubmitTime())
            .append("complainReplyNumber", getComplainReplyNumber())
            .append("clientList", getClientList())
            .append("phone", getPhone())
            .append("caseno", getCaseno())
            .toString();
    }
}
