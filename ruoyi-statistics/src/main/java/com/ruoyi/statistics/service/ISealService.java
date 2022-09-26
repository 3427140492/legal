package com.ruoyi.statistics.service;

import java.util.List;
import com.ruoyi.statistics.domain.Seal;

/**
 * 用印统计Service接口
 * 
 * @author ruoyi
 * @date 2022-09-21
 */
public interface ISealService 
{
    /**
     * 查询用印统计
     * 
     * @param id 用印统计主键
     * @return 用印统计
     */
    public Seal selectSealById(Integer id);

    /**
     * 查询用印统计列表
     * 
     * @param seal 用印统计
     * @return 用印统计集合
     */
    public List<Seal> selectSealList(Seal seal);


}
