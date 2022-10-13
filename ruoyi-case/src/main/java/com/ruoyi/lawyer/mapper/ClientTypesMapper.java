package com.ruoyi.lawyer.mapper;

import com.ruoyi.lawyer.domain.ClientTypes;

import java.util.List;

/**
 * 客户类型Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-08
 */
public interface ClientTypesMapper {

    /**
     * 查询客户类型列表
     *
     * @param clientType 客户类型
     * @return 客户类型集合
     */
    public List<ClientTypes> listClientType(ClientTypes clientType);

}