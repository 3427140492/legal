package com.ruoyi.lawyer.service;

import java.util.List;
import com.ruoyi.lawyer.domain.ClientTypes;

/**
 * 客户类型Service接口
 * 
 * @author ruoyi
 * @date 2022-09-08
 */
public interface IClientTypeService 
{

    /**
     * 查询客户类型列表
     *
     * @param clientType 客户类型
     * @return 客户类型集合
     */
    public List<ClientTypes> listClientType(ClientTypes clientType);

}
