package com.ruoyi.log.mapper;

import java.util.List;
import com.ruoyi.log.domain.LogJob;
import com.ruoyi.log.domain.LogClient;

/**
 * 工作Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-21
 */
public interface LogJobMapper 
{
    /**
     * 查询工作
     * 
     * @param id 工作主键
     * @return 工作
     */
    public LogJob selectLogJobById(Integer id);

    /**
     * 查询工作列表
     * 
     * @param logJob 工作
     * @return 工作集合
     */
    public List<LogJob> selectLogJobList(LogJob logJob);

    /**
     * 新增工作
     * 
     * @param logJob 工作
     * @return 结果
     */
    public int insertLogJob(LogJob logJob);

    /**
     * 修改工作
     * 
     * @param logJob 工作
     * @return 结果
     */
    public int updateLogJob(LogJob logJob);

    /**
     * 删除工作
     * 
     * @param id 工作主键
     * @return 结果
     */
    public int deleteLogJobById(Integer id);

    /**
     * 批量删除工作
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLogJobByIds(Integer[] ids);

    /**
     * 批量删除客户
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteClientByIds(Integer[] ids);
    
    /**
     * 批量新增客户
     * 
     * @param clientList 客户列表
     * @return 结果
     */
    public int batchClient(List<LogClient> clientList);
    

    /**
     * 通过工作主键删除客户信息
     * 
     * @param id 工作ID
     * @return 结果
     */
    public int deleteClientById(Integer id);
}
