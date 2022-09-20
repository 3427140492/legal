package com.ruoyi.client.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.client.mapper.ClientsMapper;
import com.ruoyi.client.domain.ClientCaseLaw;
import com.ruoyi.client.service.IClientsService;

/**
 * 我的当事人Service业务层处理
 * 
 * @author gk
 * @date 2022-09-16
 */
@Service
public class ClientsServiceImpl implements IClientsService
{
    @Autowired
    private ClientsMapper clientsMapper;

    /**
     * 查询我的当事人
     * 
     * @param id 我的当事人主键
     * @return 我的当事人
     */
    @Override
    public ClientCaseLaw selectCaseLawById(Integer id)
    {
        return clientsMapper.selectCaseLawById(id);
    }

    /**
     * 查询我的当事人列表
     * 
     * @param caseLaw 我的当事人
     * @return 我的当事人
     */
    @Override
    public List<ClientCaseLaw> selectCaseLawList(ClientCaseLaw caseLaw)
    {
        return clientsMapper.selectCaseLawList(caseLaw);
    }

    /**
     * 新增我的当事人
     * 
     * @param caseLaw 我的当事人
     * @return 结果
     */
    @Override
    public int insertCaseLaw(ClientCaseLaw caseLaw)
    {
        return clientsMapper.insertCaseLaw(caseLaw);
    }

    /**
     * 修改我的当事人
     * 
     * @param caseLaw 我的当事人
     * @return 结果
     */
    @Override
    public int updateCaseLaw(ClientCaseLaw caseLaw)
    {
        return clientsMapper.updateCaseLaw(caseLaw);
    }

    /**
     * 批量删除我的当事人
     * 
     * @param ids 需要删除的我的当事人主键
     * @return 结果
     */
    @Override
    public int deleteCaseLawByIds(Integer[] ids)
    {
        return clientsMapper.deleteCaseLawByIds(ids);
    }

    /**
     * 删除我的当事人信息
     * 
     * @param id 我的当事人主键
     * @return 结果
     */
    @Override
    public int deleteCaseLawById(Integer id)
    {
        return clientsMapper.deleteCaseLawById(id);
    }
}
