package com.ruoyi.statistics.service;

import java.util.List;
import com.ruoyi.statistics.domain.StatisticsCaseLaw;

/**
 * 收结案统计Service接口
 * 
 * @author ruoyi
 * @date 2022-09-15
 */
public interface IStatisticsService
{
    /**
     * 查询收结案统计
     * 
     * @param id 收结案统计主键
     * @return 收结案统计
     */
    public StatisticsCaseLaw selectCaseLawById(Integer id);

    /**
     * 查询收结案统计列表
     * 
     * @param caseLaw 收结案统计
     * @return 收结案统计集合
     */
    public List<StatisticsCaseLaw> selectCaseLawList(StatisticsCaseLaw caseLaw);



}
