package com.ruoyi.statistics.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.statistics.mapper.StatisticsMapper;
import com.ruoyi.statistics.domain.StatisticsCaseLaw;
import com.ruoyi.statistics.service.IStatisticsService;

/**
 * 收结案统计Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-15
 */
@Service
public class StatisticsServiceImpl implements IStatisticsService
{
    @Autowired
    private StatisticsMapper statisticsMapper;

    /**
     * 查询收结案统计
     * 
     * @param id 收结案统计主键
     * @return 收结案统计
     */
    @Override
    public StatisticsCaseLaw selectCaseLawById(Integer id)
    {
        return statisticsMapper.selectCaseLawById(id);
    }

    /**
     * 查询收结案统计列表
     * 
     * @param caseLaw 收结案统计
     * @return 收结案统计
     */
    @Override
    public List<StatisticsCaseLaw> selectCaseLawList(StatisticsCaseLaw caseLaw)
    {
        return statisticsMapper.selectCaseLawList(caseLaw);
    }


}
