package com.ruoyi.log.service;

import java.util.List;
import com.ruoyi.log.domain.WilllogLogJob;

/**
 * 全所日志Service接口
 * 
 * @author ruoyi
 * @date 2022-09-29
 */
public interface IWilllogLogJobService 
{
    /**
     * 查询全所日志
     * 
     * @param id 全所日志主键
     * @return 全所日志
     */
        WilllogLogJob selectWilllogLogJobById(Integer id);

    /**
     * 查询全所日志列表
     * 
     * @param willlogLogJob 全所日志
     * @return 全所日志集合
     */
    List<WilllogLogJob> selectWilllogLogJobList(WilllogLogJob willlogLogJob);

    /**
     * 新增全所日志
     * 
     * @param willlogLogJob 全所日志
     * @return 结果
     */
    int insertWilllogLogJob(WilllogLogJob willlogLogJob);

    /**
     * 修改全所日志
     * 
     * @param willlogLogJob 全所日志
     * @return 结果
     */
    int updateWilllogLogJob(WilllogLogJob willlogLogJob);

    /**
     * 批量删除全所日志
     * 
     * @param ids 需要删除的全所日志主键集合
     * @return 结果
     */
    int deleteWilllogLogJobByIds(Integer[] ids);

    /**
     * 删除全所日志信息
     * 
     * @param id 全所日志主键
     * @return 结果
     */
    int deleteWilllogLogJobById(Integer id);
}
