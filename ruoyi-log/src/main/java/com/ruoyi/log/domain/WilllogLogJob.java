package com.ruoyi.log.domain;

import java.util.List;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 全所日志对象 log_job
 * 
 * @author ruoyi
 * @date 2022-09-29
 */
public class WilllogLogJob<WiClient> extends BaseEntity
{
    private static final long serialVersionUID = 1L;




    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String caseSubmitter;

    public String getCaseSubmitter() {
        return caseSubmitter;
    }

    public void setCaseSubmitter(String caseSubmitter) {
        this.caseSubmitter = caseSubmitter;
    }

    /** 客户 */
    @Excel(name = "客户")
    private String client;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String caseNo;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String logTypeLogname;


    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String caseTypeName;


    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getLogTypeLogname() {
        return logTypeLogname;
    }

    public void setLogTypeLogname(String logTypeLogname) {
        this.logTypeLogname = logTypeLogname;
    }

    public String getCaseTypeName() {
        return caseTypeName;
    }

    public void setCaseTypeName(String caseTypeName) {
        this.caseTypeName = caseTypeName;
    }

    /** ID */
    @Excel(name = "ID")
    private Integer id;

    /** $column.columnComment */
    private Integer whatisPerson;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer logtypeId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String caseLawId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer clientId;

    /** 开始时间 */
    @Excel(name = "开始时间")
    private String joblogStarttime;

    /** $column.columnComment */
    private String joblogEnddtime;

    /** 自报时间 */
    @Excel(name = "自报时间")
    private String joblogReportedtime;

    /** $column.columnComment */
    private Date joblogUpdatetime;

    /** $column.columnComment */
    private String joblogFile;

    /** 工作描述 */
    @Excel(name = "工作描述")
    private String joblogText;

    /** 工作状态 */
    @Excel(name = "工作状态")
    private String joblogStatus;

    /** 客户信息 */
    private List<WiClient> clientList;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setWhatisPerson(Integer whatisPerson) 
    {
        this.whatisPerson = whatisPerson;
    }

    public Integer getWhatisPerson() 
    {
        return whatisPerson;
    }
    public void setLogtypeId(Integer logtypeId) 
    {
        this.logtypeId = logtypeId;
    }

    public Integer getLogtypeId() 
    {
        return logtypeId;
    }
    public void setCaseLawId(String caseLawId) 
    {
        this.caseLawId = caseLawId;
    }

    public String getCaseLawId() 
    {
        return caseLawId;
    }
    public void setClientId(Integer clientId) 
    {
        this.clientId = clientId;
    }

    public Integer getClientId() 
    {
        return clientId;
    }
    public void setJoblogStarttime(String joblogStarttime) 
    {
        this.joblogStarttime = joblogStarttime;
    }

    public String getJoblogStarttime() 
    {
        return joblogStarttime;
    }
    public void setJoblogEnddtime(String joblogEnddtime) 
    {
        this.joblogEnddtime = joblogEnddtime;
    }

    public String getJoblogEnddtime() 
    {
        return joblogEnddtime;
    }
    public void setJoblogReportedtime(String joblogReportedtime) 
    {
        this.joblogReportedtime = joblogReportedtime;
    }

    public String getJoblogReportedtime() 
    {
        return joblogReportedtime;
    }
    public void setJoblogUpdatetime(Date joblogUpdatetime) 
    {
        this.joblogUpdatetime = joblogUpdatetime;
    }

    public Date getJoblogUpdatetime() 
    {
        return joblogUpdatetime;
    }
    public void setJoblogFile(String joblogFile) 
    {
        this.joblogFile = joblogFile;
    }

    public String getJoblogFile() 
    {
        return joblogFile;
    }
    public void setJoblogText(String joblogText) 
    {
        this.joblogText = joblogText;
    }

    public String getJoblogText() 
    {
        return joblogText;
    }
    public void setJoblogStatus(String joblogStatus) 
    {
        this.joblogStatus = joblogStatus;
    }

    public String getJoblogStatus() 
    {
        return joblogStatus;
    }

    public List<WiClient> getClientList()
    {
        return clientList;
    }

    public void setClientList(List<WiClient> clientList)
    {
        this.clientList = clientList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("whatisPerson", getWhatisPerson())
            .append("logtypeId", getLogtypeId())
            .append("caseLawId", getCaseLawId())
            .append("clientId", getClientId())
            .append("joblogStarttime", getJoblogStarttime())
            .append("joblogEnddtime", getJoblogEnddtime())
            .append("joblogReportedtime", getJoblogReportedtime())
            .append("joblogUpdatetime", getJoblogUpdatetime())
            .append("joblogFile", getJoblogFile())
            .append("joblogText", getJoblogText())
            .append("joblogStatus", getJoblogStatus())
            .append("clientList", getClientList())
            .toString();
    }
}
