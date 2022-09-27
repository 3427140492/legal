package com.ruoyi.record.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 全所电子归档对象 record_document
 *
 * @author ruoyi
 * @date 2022-09-27
 */
public class RecordDocument extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文档ID */
    private Integer id;

    /** 律师ID */
    @Excel(name = "律师ID")
    private Integer caseLawId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Integer systemUserId;

    /** 最后更新日期 */
    @Excel(name = "最后更新日期")
    private String upddate;

    /** 案号 */
    @Excel(name = "案号")
    private String caseNo;

    /** 提交人 */
    @Excel(name = "提交人")
    private String caseSubmitter;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer folderId;

    /** 归档文件 */
    @Excel(name = "归档文件")
    private String documentFile;

    /** 归档信息 */
    private List<RecordCaseLaw> caseLawList;

    private String searchType;

    private String searchStr;

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getCaseSubmitter() {
        return caseSubmitter;
    }

    public void setCaseSubmitter(String caseSubmitter) {
        this.caseSubmitter = caseSubmitter;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }


    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setCaseLawId(Integer caseLawId)
    {
        this.caseLawId = caseLawId;
    }

    public Integer getCaseLawId()
    {
        return caseLawId;
    }
    public void setSystemUserId(Integer systemUserId)
    {
        this.systemUserId = systemUserId;
    }

    public Integer getSystemUserId()
    {
        return systemUserId;
    }
    public void setUpddate(String upddate)
    {
        this.upddate = upddate;
    }

    public String getUpddate()
    {
        return upddate;
    }
    public void setFolderId(Integer folderId)
    {
        this.folderId = folderId;
    }

    public Integer getFolderId()
    {
        return folderId;
    }
    public void setDocumentFile(String documentFile)
    {
        this.documentFile = documentFile;
    }

    public String getDocumentFile()
    {
        return documentFile;
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
                .append("systemUserId", getSystemUserId())
                .append("upddate", getUpddate())
                .append("folderId", getFolderId())
                .append("documentFile", getDocumentFile())
                .append("caseLawList", getCaseLawList())
                .toString();
    }
}
