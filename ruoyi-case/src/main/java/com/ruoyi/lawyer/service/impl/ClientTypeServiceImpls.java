package com.ruoyi.lawyer.service.impl;

import java.util.List;
import com.ruoyi.lawyer.domain.ClientTypes;
import com.ruoyi.lawyer.mapper.ClientTypesMapper;
import com.ruoyi.lawyer.service.IClientTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 客户类型Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-08
 */
@Service
public class ClientTypeServiceImpls implements IClientTypeService {

    @Autowired
    private ClientTypesMapper clientTypeMapper;


    /**
     * 查询客户类型列表
     *
     * @param clientType 客户类型
     * @return 客户类型
     */
    @Override
    public List<ClientTypes> listClientType(ClientTypes clientType) {
        return clientTypeMapper.listClientType(clientType);
    }

}