package com.ruoyi.lawyer.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 文件记录对象 record_file
 * 
 * @author ruoyi
 * @date 2022-10-10
 */
public class RecordFiles extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long rid;

    private String file_name;

    private String filename;

    private String file_path;

    private Long tid;

    private String caseid;

    public String getCaseid() {
        return caseid;
    }

    public void setCaseid(String caseid) {
        this.caseid = caseid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "RecordFile{" +
                "rid=" + rid +
                ", file_name='" + file_name + '\'' +
                ", filename=" + filename +
                ", file_path='" + file_path + '\'' +
                ", tid=" + tid +
                ", caseid='" + caseid + '\'' +
                '}';
    }
}
