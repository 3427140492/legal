package com.ruoyi.client.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.client.mapper.ClientCaseLawMapper;
import com.ruoyi.client.domain.ClientCaseLaw;
import com.ruoyi.client.service.IClientCaseLawService;

/**
 * 我的当事人Service业务层处理
 * 
 * @author gk
 * @date 2022-09-16
 */
@Service
public class ClientCaseLawServiceImpl implements IClientCaseLawService
{
    @Autowired
    private ClientCaseLawMapper clientcaseLawMapper;

    /**
     * 查询我的当事人
     * 
     * @param id 我的当事人主键
     * @return 我的当事人
     */
    @Override
    public ClientCaseLaw selectCaseLawById(Integer id)
    {
        return clientcaseLawMapper.selectCaseLawById(id);
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
        return clientcaseLawMapper.selectCaseLawList(caseLaw);
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
        return clientcaseLawMapper.insertCaseLaw(caseLaw);
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
        return clientcaseLawMapper.updateCaseLaw(caseLaw);
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
        return clientcaseLawMapper.deleteCaseLawByIds(ids);
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
        return clientcaseLawMapper.deleteCaseLawById(id);
    }
}
