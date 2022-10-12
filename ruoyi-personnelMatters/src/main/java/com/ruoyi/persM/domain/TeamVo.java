package com.ruoyi.persM.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 团队对象 hr_team
 * 
 * @author ruoyi
 * @date 2022-10-11
 */
public class TeamVo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 团队名称 */
    @Excel(name = "团队名称")
    private String teamName;

    /** 团队负责人 */
    @Excel(name = "团队负责人")
    private String teamPrincipal;

    /** 团队成员 */
    @Excel(name = "团队成员")
    private String teamMember;

    /** 团队介绍 */
    @Excel(name = "团队介绍")
    private String teamIntro;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTeamName(String teamName) 
    {
        this.teamName = teamName;
    }

    public String getTeamName() 
    {
        return teamName;
    }
    public void setTeamPrincipal(String teamPrincipal) 
    {
        this.teamPrincipal = teamPrincipal;
    }

    public String getTeamPrincipal() 
    {
        return teamPrincipal;
    }
    public void setTeamMember(String teamMember) 
    {
        this.teamMember = teamMember;
    }

    public String getTeamMember() 
    {
        return teamMember;
    }
    public void setTeamIntro(String teamIntro) 
    {
        this.teamIntro = teamIntro;
    }

    public String getTeamIntro() 
    {
        return teamIntro;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("teamName", getTeamName())
            .append("teamPrincipal", getTeamPrincipal())
            .append("teamMember", getTeamMember())
            .append("teamIntro", getTeamIntro())
            .toString();
    }
}
