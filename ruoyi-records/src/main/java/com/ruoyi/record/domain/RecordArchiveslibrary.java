package com.ruoyi.record.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 档案借阅对象 record_archiveslibrary
 * 
 * @author ruoyi
 * @date 2022-10-10
 */
public class RecordArchiveslibrary extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;


    /** 案件id 外键 */
    @Excel(name = "案件id 外键")
    private Long caseLawId;

    /** 档案号 */
    @Excel(name = "档案号")
    private String recordNum;

    /** 档案名称 */
    @Excel(name = "档案名称")
    private String recordName;

    /** 借档人 */
    @Excel(name = "借档人")
    private String borrowFilePeople;

    /** 借出时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "借出时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date borrowDate;

    /** 归还时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "归还时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date giveBackDate;

    /** 归还状态 Y 已归还 N 未归还 */
    @Excel(name = "归还状态 Y 已归还 N 未归还")
    private String borrowStatus;

    private String empName;

    private String caseNo;

    private String searchType;

    private String serchText;

    private String Sertime;

    public String getSertime() {
        return Sertime;
    }

    public void setSertime(String sertime) {
        Sertime = sertime;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSerchText() {
        return serchText;
    }

    public void setSerchText(String serchText) {
        this.serchText = serchText;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    /** 审批中心信息 */
    private List<RecordCaseLaw> caseLawList;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setCaseLawId(Long caseLawId) 
    {
        this.caseLawId = caseLawId;
    }

    public Long getCaseLawId() 
    {
        return caseLawId;
    }
    public void setRecordNum(String recordNum) 
    {
        this.recordNum = recordNum;
    }

    public String getRecordNum() 
    {
        return recordNum;
    }
    public void setRecordName(String recordName) 
    {
        this.recordName = recordName;
    }

    public String getRecordName() 
    {
        return recordName;
    }
    public void setBorrowFilePeople(String borrowFilePeople) 
    {
        this.borrowFilePeople = borrowFilePeople;
    }

    public String getBorrowFilePeople() 
    {
        return borrowFilePeople;
    }
    public void setBorrowDate(Date borrowDate) 
    {
        this.borrowDate = borrowDate;
    }

    public Date getBorrowDate() 
    {
        return borrowDate;
    }
    public void setGiveBackDate(Date giveBackDate) 
    {
        this.giveBackDate = giveBackDate;
    }

    public Date getGiveBackDate() 
    {
        return giveBackDate;
    }
    public void setBorrowStatus(String borrowStatus) 
    {
        this.borrowStatus = borrowStatus;
    }

    public String getBorrowStatus() 
    {
        return borrowStatus;
    }

    public List<RecordCaseLaw> getCaseLawList()
    {
        return caseLawList;
    }

    public void setCaseLawList(List<RecordCaseLaw> caseLawList)
    {
        this.caseLawList = caseLawList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("caseLawId", getCaseLawId())
            .append("recordNum", getRecordNum())
            .append("recordName", getRecordName())
            .append("borrowFilePeople", getBorrowFilePeople())
            .append("borrowDate", getBorrowDate())
            .append("giveBackDate", getGiveBackDate())
            .append("remark", getRemark())
            .append("borrowStatus", getBorrowStatus())
            .append("caseLawList", getCaseLawList())
            .toString();
    }
}
