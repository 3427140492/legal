package com.ruoyi.log.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.log.domain.WClient;
import com.ruoyi.log.mapper.WilllogLogJobMapper;
import com.ruoyi.log.domain.WilllogLogJob;
import com.ruoyi.log.service.IWilllogLogJobService;

/**
 * 全所日志Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-29
 */
@Service
public class WilllogLogJobServiceImpl implements IWilllogLogJobService 
{
    @Autowired
    private WilllogLogJobMapper willlogLogJobMapper;

    /**
     * 查询全所日志
     * 
     * @param id 全所日志主键
     * @return 全所日志
     */
    @Override
    public WilllogLogJob selectWilllogLogJobById(Integer id)
    {
        return willlogLogJobMapper.selectWilllogLogJobById(id);
    }

    /**
     * 查询全所日志列表
     * 
     * @param willlogLogJob 全所日志
     * @return 全所日志
     */
    @Override
    public List<WilllogLogJob> selectWilllogLogJobList(WilllogLogJob willlogLogJob)
    {
        return willlogLogJobMapper.selectWilllogLogJobList(willlogLogJob);
    }

    /**
     * 新增全所日志
     * 
     * @param willlogLogJob 全所日志
     * @return 结果
     */
    @Transactional
    @Override
    public int insertWilllogLogJob(WilllogLogJob willlogLogJob)
    {
        int rows = willlogLogJobMapper.insertWilllogLogJob(willlogLogJob);
        insertClient(willlogLogJob);
        return rows;
    }

    /**
     * 修改全所日志
     * 
     * @param willlogLogJob 全所日志
     * @return 结果
     */
    @Transactional
    @Override
    public int updateWilllogLogJob(WilllogLogJob willlogLogJob)
    {
        willlogLogJobMapper.deleteClientById(willlogLogJob.getId());
        insertClient(willlogLogJob);
        return willlogLogJobMapper.updateWilllogLogJob(willlogLogJob);
    }

    /**
     * 批量删除全所日志
     * 
     * @param ids 需要删除的全所日志主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteWilllogLogJobByIds(Integer[] ids)
    {
        willlogLogJobMapper.deleteClientByIds(ids);
        return willlogLogJobMapper.deleteWilllogLogJobByIds(ids);
    }

    /**
     * 删除全所日志信息
     * 
     * @param id 全所日志主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteWilllogLogJobById(Integer id)
    {
        willlogLogJobMapper.deleteClientById(id);
        return willlogLogJobMapper.deleteWilllogLogJobById(id);
    }

    /**
     * 新增客户信息
     * 
     * @param willlogLogJob 全所日志对象
     */
    public void insertClient(WilllogLogJob willlogLogJob)
    {
        List<WClient> clientList = willlogLogJob.getClientList();
        Integer id = willlogLogJob.getId();
        if (StringUtils.isNotNull(clientList))
        {
            List<WClient> list = new ArrayList<WClient>();
            for (WClient client : clientList)
            {
                client.setId(id);
                list.add(client);
            }
            if (list.size() > 0)
            {
                willlogLogJobMapper.batchClient(list);
            }
        }
    }
}
