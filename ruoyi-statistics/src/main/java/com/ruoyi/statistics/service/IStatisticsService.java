package com.ruoyi.statistics.service;

import java.util.List;
import com.ruoyi.statistics.domain.StatisticsCaseLaw;
import org.springframework.stereotype.Repository;

/**
 * 收结案统计Service接口
 * 
 * @author ruoyi
 * @date 2022-09-15
 */
@Repository
public interface IStatisticsService
{
    /**
     * 查询收结案统计
     * 
     * @param id 收结案统计主键
     * @return 收结案统计
     */
    StatisticsCaseLaw selectCaseLawById(Integer id);

    /**
     * 查询收结案统计列表
     * 
     * @param statisticsCaseLaw 收结案统计
     * @return 收结案统计集合
     */
    List<StatisticsCaseLaw> selectCaseLawList(StatisticsCaseLaw statisticsCaseLaw);

    /**
     * 新增收结案统计
     * 
     * @param statisticsCaseLaw 收结案统计
     * @return 结果
     */
    int insertCaseLaw(StatisticsCaseLaw statisticsCaseLaw);

    /**
     * 修改收结案统计
     * 
     * @param statisticsCaseLaw 收结案统计
     * @return 结果
     */
    int updateCaseLaw(StatisticsCaseLaw statisticsCaseLaw);

    /**
     * 批量删除收结案统计
     * 
     * @param ids 需要删除的收结案统计主键集合
     * @return 结果
     */
    int deleteCaseLawByIds(Integer[] ids);

    /**
     * 删除收结案统计信息
     * 
     * @param id 收结案统计主键
     * @return 结果
     */
    int deleteCaseLawById(Integer id);
}
