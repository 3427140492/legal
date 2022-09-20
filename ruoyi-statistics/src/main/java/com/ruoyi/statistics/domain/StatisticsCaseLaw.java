package com.ruoyi.statistics.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 收结案统计对象 case_law
 * 
 * @author ruoyi
 * @date 2022-09-15
 */
public class StatisticsCaseLaw extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 案号 */
    @Excel(name = "案号")
    private String caseNo;

    /** 对方当事人 */
    @Excel(name = "对方当事人")
    private String caseOppositeParties;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date caseSubtime;

    /** 案由 */
    @Excel(name = "案由")
    private String caseCause;

    /** 收案时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "收案时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date collectionTime;

    /** 受理机关 */
    @Excel(name = "受理机关")
    private String caseAccept;

    /** 诉讼标的 */
    @Excel(name = "诉讼标的")
    private Long caseLawsuitobj;

    /** 代理费 */
    @Excel(name = "代理费")
    private BigDecimal caseAgencyfee;

    /** 杂费 */
    @Excel(name = "杂费")
    private BigDecimal CasePaidsal;

    /** 已开票金额 */
    @Excel(name = "已开票金额")
    private BigDecimal caseInvoiced;

    /** 归档状态1,2 */
    @Excel(name = "归档状态1,2")
    private String caseRecord;

    /** 归档号 */
    @Excel(name = "归档号")
    private String caseRecordNum;

    /** 结案方式(0=默认，1=调解，2=判决，3=撤诉，4=其他方式) */
    @Excel(name = "结案方式(0=默认，1=调解，2=判决，3=撤诉，4=其他方式)")
    private String caseSettleType;



    /** $table.subTable.functionName信息 */
    private List<HrEmp> hrEmpList;

    private List<StatisticsClient> statisticsClientList;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getCaseOppositeParties() {
        return caseOppositeParties;
    }

    public void setCaseOppositeParties(String caseOppositeParties) {
        this.caseOppositeParties = caseOppositeParties;
    }

    public Date getCaseSubtime() {
        return caseSubtime;
    }

    public void setCaseSubtime(Date caseSubtime) {
        this.caseSubtime = caseSubtime;
    }

    public String getCaseCause() {
        return caseCause;
    }

    public void setCaseCause(String caseCause) {
        this.caseCause = caseCause;
    }

    public Date getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Date collectionTime) {
        this.collectionTime = collectionTime;
    }

    public String getCaseAccept() {
        return caseAccept;
    }

    public void setCaseAccept(String caseAccept) {
        this.caseAccept = caseAccept;
    }

    public Long getCaseLawsuitobj() {
        return caseLawsuitobj;
    }

    public void setCaseLawsuitobj(Long caseLawsuitobj) {
        this.caseLawsuitobj = caseLawsuitobj;
    }

    public BigDecimal getCaseAgencyfee() {
        return caseAgencyfee;
    }

    public void setCaseAgencyfee(BigDecimal caseAgencyfee) {
        this.caseAgencyfee = caseAgencyfee;
    }

    public BigDecimal getCasePaidsal() {
        return CasePaidsal;
    }

    public void setCasePaidsal(BigDecimal CasePaidsal) {
        this.CasePaidsal = CasePaidsal;
    }

    public BigDecimal getCaseInvoiced() {
        return caseInvoiced;
    }

    public void setCaseInvoiced(BigDecimal caseInvoiced) {
        this.caseInvoiced = caseInvoiced;
    }

    public String getCaseRecord() {
        return caseRecord;
    }

    public void setCaseRecord(String caseRecord) {
        this.caseRecord = caseRecord;
    }

    public String getCaseRecordNum() {
        return caseRecordNum;
    }

    public void setCaseRecordNum(String caseRecordNum) {
        this.caseRecordNum = caseRecordNum;
    }

    public String getCaseSettleType() {
        return caseSettleType;
    }

    public void setCaseSettleType(String caseSettleType) {
        this.caseSettleType = caseSettleType;
    }

    public List<HrEmp> getHrEmpList() {
        return hrEmpList;
    }

    public void setHrEmpList(List<HrEmp> hrEmpList) {
        this.hrEmpList = hrEmpList;
    }

    public List<StatisticsClient> getClientList() {
        return statisticsClientList;
    }

    public void setClientList(List<StatisticsClient> statisticsClientList) {
        this.statisticsClientList = statisticsClientList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("caseNo", getCaseNo())
            .append("caseOppositeParties", getCaseOppositeParties())
            .append("caseCause", getCaseCause())
            .append("caseAccept", getCaseAccept())
            .append("caseLawsuitobj", getCaseLawsuitobj())
            .append("caseAgencyfee", getCaseAgencyfee())
            .append("casePaidsal", getCasePaidsal())
            .append("caseInvoiced", getCaseInvoiced())
            .append("caseSubtime", getCaseSubtime())
            .append("collectionTime", getCollectionTime())
            .append("caseRecord", getCaseRecord())
            .append("caseSettleType", getCaseSettleType())
            .append("caseRecordNum", getCaseRecordNum())
            .append("hrEmpList", getHrEmpList())
            .append("ClientList", getClientList())
            .toString();
    }
}
