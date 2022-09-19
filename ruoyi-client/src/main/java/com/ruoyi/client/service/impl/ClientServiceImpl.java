package com.ruoyi.client.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.client.mapper.ClientMapper;
import com.ruoyi.client.domain.Client;
import com.ruoyi.client.service.IClientService;

/**
 * 客户管理Service业务层处理
 * 
 * @author gk
 * @date 2022-09-14
 */
@Service
public class ClientServiceImpl implements IClientService 
{
    @Autowired
    private ClientMapper clientMapper;

    /**
     * 查询客户管理
     * 
     * @param id 客户管理主键
     * @return 客户管理
     */
    @Override
    public Client selectClientById(Long id)
    {
        return clientMapper.selectClientById(id);
    }

    /**
     * 查询客户管理列表
     * 
     * @param client 客户管理
     * @return 客户管理
     */
    @Override
    public List<Client> selectClientList(Client client)
    {
        return clientMapper.selectClientList(client);
    }

    /**
     * 新增客户管理
     * 
     * @param client 客户管理
     * @return 结果
     */
    @Override
    public int insertClient(Client client)
    {
        return clientMapper.insertClient(client);
    }

    /**
     * 修改客户管理
     * 
     * @param client 客户管理
     * @return 结果
     */
    @Override
    public int updateClient(Client client)
    {
        return clientMapper.updateClient(client);
    }

    /**
     * 批量删除客户管理
     * 
     * @param ids 需要删除的客户管理主键
     * @return 结果
     */
    @Override
    public int deleteClientByIds(Long[] ids)
    {
        return clientMapper.deleteClientByIds(ids);
    }

    /**
     * 删除客户管理信息
     * 
     * @param id 客户管理主键
     * @return 结果
     */
    @Override
    public int deleteClientById(Long id)
    {
        return clientMapper.deleteClientById(id);
    }
}
