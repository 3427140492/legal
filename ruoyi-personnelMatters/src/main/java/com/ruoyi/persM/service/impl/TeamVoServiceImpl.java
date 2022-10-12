package com.ruoyi.persM.service.impl;

import com.ruoyi.persM.domain.TeamVo;
import com.ruoyi.persM.mapper.TeamVoMapper;
import com.ruoyi.persM.service.ITeamVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 团队Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-11
 */
@Service
public class TeamVoServiceImpl implements ITeamVoService 
{
    @Autowired
    private TeamVoMapper teamVoMapper;

    /**
     * 查询团队
     * 
     * @param id 团队主键
     * @return 团队
     */
    @Override
    public TeamVo selectTeamVoById(Long id)
    {
        return teamVoMapper.selectTeamVoById(id);
    }

    /**
     * 查询团队列表
     * 
     * @param teamVo 团队
     * @return 团队
     */
    @Override
    public List<TeamVo> selectTeamVoList(TeamVo teamVo)
    {
        return teamVoMapper.selectTeamVoList(teamVo);
    }

    /**
     * 新增团队
     * 
     * @param teamVo 团队
     * @return 结果
     */
    @Override
    public int insertTeamVo(TeamVo teamVo)
    {
        return teamVoMapper.insertTeamVo(teamVo);
    }

    /**
     * 修改团队
     * 
     * @param teamVo 团队
     * @return 结果
     */
    @Override
    public int updateTeamVo(TeamVo teamVo)
    {
        return teamVoMapper.updateTeamVo(teamVo);
    }

    /**
     * 批量删除团队
     * 
     * @param ids 需要删除的团队主键
     * @return 结果
     */
    @Override
    public int deleteTeamVoByIds(Long[] ids)
    {
        return teamVoMapper.deleteTeamVoByIds(ids);
    }

    /**
     * 删除团队信息
     * 
     * @param id 团队主键
     * @return 结果
     */
    @Override
    public int deleteTeamVoById(Long id)
    {
        return teamVoMapper.deleteTeamVoById(id);
    }
}
