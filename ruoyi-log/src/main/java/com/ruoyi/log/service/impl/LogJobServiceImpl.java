package com.ruoyi.log.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.log.domain.LogClient;
import com.ruoyi.log.mapper.LogJobMapper;
import com.ruoyi.log.domain.LogJob;
import com.ruoyi.log.service.ILogJobService;

/**
 * 工作Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-21
 */
@Service
public class LogJobServiceImpl implements ILogJobService 
{
    @Autowired
    private LogJobMapper logJobMapper;

    /**
     * 查询工作
     * 
     * @param id 工作主键
     * @return 工作
     */
    @Override
    public LogJob selectLogJobById(Integer id)
    {
        return logJobMapper.selectLogJobById(id);
    }

    /**
     * 查询工作列表
     * 
     * @param logJob 工作
     * @return 工作
     */
    @Override
    public List<LogJob> selectLogJobList(LogJob logJob)
    {
        return logJobMapper.selectLogJobList(logJob);
    }

    /**
     * 新增工作
     * 
     * @param logJob 工作
     * @return 结果
     */
    @Transactional
    @Override
    public int insertLogJob(LogJob logJob)
    {
        int rows = logJobMapper.insertLogJob(logJob);
        insertClient(logJob);
        return rows;
    }

    /**
     * 修改工作
     * 
     * @param logJob 工作
     * @return 结果
     */
    @Transactional
    @Override
    public int updateLogJob(LogJob logJob)
    {
        logJobMapper.deleteClientById(logJob.getId());
        insertClient(logJob);
        return logJobMapper.updateLogJob(logJob);
    }

    /**
     * 批量删除工作
     * 
     * @param ids 需要删除的工作主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteLogJobByIds(Integer[] ids)
    {
        logJobMapper.deleteClientByIds(ids);
        return logJobMapper.deleteLogJobByIds(ids);
    }

    /**
     * 删除工作信息
     * 
     * @param id 工作主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteLogJobById(Integer id)
    {
        logJobMapper.deleteClientById(id);
        return logJobMapper.deleteLogJobById(id);
    }

    /**
     * 新增客户信息
     * 
     * @param logJob 工作对象
     */
    public void insertClient(LogJob logJob)
    {
        List<LogClient> clientList = logJob.getClientList();
        Integer id = logJob.getId();
        if (StringUtils.isNotNull(clientList))
        {
            List<LogClient> list = new ArrayList<LogClient>();
            for (LogClient client : clientList)
            {
                client.setId(id);
                list.add(client);
            }
            if (list.size() > 0)
            {
                logJobMapper.batchClient(list);
            }
        }
    }
}
