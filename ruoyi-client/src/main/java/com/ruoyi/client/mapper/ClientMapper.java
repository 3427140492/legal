package com.ruoyi.client.mapper;

import java.util.List;
import com.ruoyi.client.domain.Client;

/**
 * 客户管理Mapper接口
 * 
 * @author gk
 * @date 2022-09-14
 */
public interface ClientMapper 
{
    /**
     * 查询客户管理
     * 
     * @param id 客户管理主键
     * @return 客户管理
     */
    public Client selectClientById(Long id);

    /**
     * 查询客户管理列表
     * 
     * @param client 客户管理
     * @return 客户管理集合
     */
    public List<Client> selectClientList(Client client);

    /**
     * 新增客户管理
     * 
     * @param client 客户管理
     * @return 结果
     */
    public int insertClient(Client client);

    /**
     * 修改客户管理
     * 
     * @param client 客户管理
     * @return 结果
     */
    public int updateClient(Client client);

    /**
     * 删除客户管理
     * 
     * @param id 客户管理主键
     * @return 结果
     */
    public int deleteClientById(Long id);

    /**
     * 批量删除客户管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteClientByIds(Long[] ids);
}
