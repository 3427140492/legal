package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 公告通知对象 adm_notice
 * 
 * @author ruoyi
 * @date 2022-09-21
 */
public class AdmNotice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** $column.columnComment */
    private String noticeReleasetime;

    /** $column.columnComment */
    private String systemUserId;

    /** 时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date noticeNotificationtime;

    /**  */
    private Date noticeNotificationendtime;

    /** 公告内容 */
    private String noticeContent;

    /** 文件 */
    private String noticeFile;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setNoticeReleasetime(String noticeReleasetime) 
    {
        this.noticeReleasetime = noticeReleasetime;
    }

    public String getNoticeReleasetime() 
    {
        return noticeReleasetime;
    }
    public void setSystemUserId(String systemUserId) 
    {
        this.systemUserId = systemUserId;
    }

    public String getSystemUserId() 
    {
        return systemUserId;
    }
    public void setNoticeNotificationtime(Date noticeNotificationtime) 
    {
        this.noticeNotificationtime = noticeNotificationtime;
    }

    public Date getNoticeNotificationtime() 
    {
        return noticeNotificationtime;
    }
    public void setNoticeNotificationendtime(Date noticeNotificationendtime) 
    {
        this.noticeNotificationendtime = noticeNotificationendtime;
    }

    public Date getNoticeNotificationendtime() 
    {
        return noticeNotificationendtime;
    }
    public void setNoticeContent(String noticeContent) 
    {
        this.noticeContent = noticeContent;
    }

    public String getNoticeContent() 
    {
        return noticeContent;
    }
    public void setNoticeFile(String noticeFile) 
    {
        this.noticeFile = noticeFile;
    }

    public String getNoticeFile() 
    {
        return noticeFile;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("noticeReleasetime", getNoticeReleasetime())
            .append("systemUserId", getSystemUserId())
            .append("noticeNotificationtime", getNoticeNotificationtime())
            .append("noticeNotificationendtime", getNoticeNotificationendtime())
            .append("noticeContent", getNoticeContent())
            .append("noticeFile", getNoticeFile())
            .toString();
    }
}
