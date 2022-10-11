package com.ruoyi.persM.service;

import com.ruoyi.persM.domain.TeamVo;

import java.util.List;

/**
 * 团队Service接口
 * 
 * @author ruoyi
 * @date 2022-10-11
 */
public interface ITeamVoService 
{
    /**
     * 查询团队
     * 
     * @param id 团队主键
     * @return 团队
     */
    public TeamVo selectTeamVoById(Long id);

    /**
     * 查询团队列表
     * 
     * @param teamVo 团队
     * @return 团队集合
     */
    public List<TeamVo> selectTeamVoList(TeamVo teamVo);

    /**
     * 新增团队
     * 
     * @param teamVo 团队
     * @return 结果
     */
    public int insertTeamVo(TeamVo teamVo);

    /**
     * 修改团队
     * 
     * @param teamVo 团队
     * @return 结果
     */
    public int updateTeamVo(TeamVo teamVo);

    /**
     * 批量删除团队
     * 
     * @param ids 需要删除的团队主键集合
     * @return 结果
     */
    public int deleteTeamVoByIds(Long[] ids);

    /**
     * 删除团队信息
     * 
     * @param id 团队主键
     * @return 结果
     */
    public int deleteTeamVoById(Long id);
}
