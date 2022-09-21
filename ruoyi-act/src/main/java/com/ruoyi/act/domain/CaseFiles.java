package com.ruoyi.act.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * ${subTable.functionName}对象 case_files
 * 
 * @author ruoyi
 * @date 2022-09-14
 */
public class CaseFiles extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 用印申请外键 */
    @Excel(name = "用印申请外键")
    private Integer appid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String filename;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String filepath;

    /** 文件类型外键 */
    @Excel(name = "文件类型外键")
    private Integer typeid;

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }
    public void setAppid(Integer appid)
    {
        this.appid = appid;
    }

    public Integer getAppid()
    {
        return appid;
    }
    public void setFilename(String filename) 
    {
        this.filename = filename;
    }

    public String getFilename() 
    {
        return filename;
    }
    public void setFilepath(String filepath) 
    {
        this.filepath = filepath;
    }

    public String getFilepath() 
    {
        return filepath;
    }
    public void setTypeid(Integer typeid)
    {
        this.typeid = typeid;
    }

    public Integer getTypeid()
    {
        return typeid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("appid", getAppid())
            .append("filename", getFilename())
            .append("filepath", getFilepath())
            .append("typeid", getTypeid())
            .toString();
    }
}
