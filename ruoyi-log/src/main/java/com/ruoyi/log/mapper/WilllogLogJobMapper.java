package com.ruoyi.log.mapper;

import java.util.List;
import com.ruoyi.log.domain.WilllogLogJob;
import com.ruoyi.log.domain.WClient;

/**
 * 全所日志Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-29
 */
public interface WilllogLogJobMapper 
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
     * 删除全所日志
     * 
     * @param id 全所日志主键
     * @return 结果
     */
    int deleteWilllogLogJobById(Integer id);

    /**
     * 批量删除全所日志
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteWilllogLogJobByIds(Integer[] ids);

    /**
     * 批量删除客户
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteClientByIds(Integer[] ids);
    
    /**
     * 批量新增客户
     * 
     * @param clientList 客户列表
     * @return 结果
     */
    int batchClient(List<WClient> clientList);
    

    /**
     * 通过全所日志主键删除客户信息
     * 
     * @param id 全所日志ID
     * @return 结果
     */
    int deleteClientById(Integer id);
}
