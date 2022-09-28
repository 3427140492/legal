package com.ruoyi.statistics.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.statistics.domain.StatisticsCaseLaw;
import com.ruoyi.statistics.mapper.SealMapper;
import com.ruoyi.statistics.domain.Seal;
import com.ruoyi.statistics.service.ISealService;

/**
 * 用印统计Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-21
 */
@Service
public class SealServiceImpl implements ISealService 
{
    @Autowired
    private SealMapper sealMapper;

    /**
     * 查询用印统计
     * 
     * @param id 用印统计主键
     * @return 用印统计
     */
    @Override
    public Seal selectSealById(Integer id)
    {
        return sealMapper.selectSealById(id);
    }

    /**
     * 查询用印统计列表
     * 
     * @param seal 用印统计
     * @return 用印统计
     */
    @Override
    public List<Seal> selectSealList(Seal seal)
    {
        return sealMapper.selectSealList(seal);
    }


}
