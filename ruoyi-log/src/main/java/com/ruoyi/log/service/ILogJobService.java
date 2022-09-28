package com.ruoyi.log.service;

import java.util.List;
import com.ruoyi.log.domain.LogJob;

/**
 * 工作Service接口
 * 
 * @author ruoyi
 * @date 2022-09-21
 */
public interface ILogJobService 
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
     * 批量删除工作
     * 
     * @param ids 需要删除的工作主键集合
     * @return 结果
     */
    public int deleteLogJobByIds(Integer[] ids);

    /**
     * 删除工作信息
     * 
     * @param id 工作主键
     * @return 结果
     */
    public int deleteLogJobById(Integer id);
}
