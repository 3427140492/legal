package com.ruoyi.lawyer.service.impl;


import com.ruoyi.lawyer.domain.Clients;
import com.ruoyi.lawyer.mapper.ClientsMapper;
import com.ruoyi.lawyer.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 客户信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-04
 */
@Service
public class ClientServiceImpls implements IClientService
{
    @Autowired
    private ClientsMapper clientMapper;


    /**
     * 查询客户信息 (是否存在)
     *
     * @param cardnum 客户身份证号
     * @return 客户信息
     */
    @Override
    public List<Clients> getClientByCardnum(String cardnum) {
        return clientMapper.getClientByCardnum(cardnum);
    }


    /**
     * 新增客户信息
     *
     * @param client 客户信息
     * @return 结果
     */
    @Override
    public int addClient(Clients client)
    {
        return clientMapper.addClient(client);
    }

    /**
     * 修改客户信息
     *
     * @param client 客户信息
     * @return 结果
     */
    @Override
    public int editClient(Clients client)
    {
        return clientMapper.editClient(client);
    }

}
