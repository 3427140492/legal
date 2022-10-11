package com.ruoyi.persM.mapper;

import java.util.List;
import com.ruoyi.persM.domain.TeamVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 团队Mapper接口
 *
 * @author ruoyi
 * @date 2022-10-11
 */
@Mapper
public interface TeamVoMapper
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
     *
     *
     * @param teamVo 团队
     * @return 结果
     */
    public int updateTeamVo(TeamVo teamVo);

    /**
     * 删除团队
     *
     * @param id 团队主键
     * @return 结果
     */
    public int deleteTeamVoById(Long id);

    /**
     * 批量删除团队
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeamVoByIds(Long[] ids);
}
