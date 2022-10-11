package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ExpressTypeMapper;
import com.ruoyi.system.domain.ExpressType;
import com.ruoyi.system.service.IExpressTypeService;

/**
 * 快递类型Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-11
 */
@Service
public class ExpressTypeServiceImpl implements IExpressTypeService 
{
    @Autowired
    private ExpressTypeMapper expressTypeMapper;

    /**
     * 查询快递类型
     * 
     * @param id 快递类型主键
     * @return 快递类型
     */
    @Override
    public ExpressType selectExpressTypeById(Long id)
    {
        return expressTypeMapper.selectExpressTypeById(id);
    }

    /**
     * 查询快递类型列表
     * 
     * @param expressType 快递类型
     * @return 快递类型
     */
    @Override
    public List<ExpressType> selectExpressTypeList(ExpressType expressType)
    {
        return expressTypeMapper.selectExpressTypeList(expressType);
    }

    /**
     * 新增快递类型
     * 
     * @param expressType 快递类型
     * @return 结果
     */
    @Override
    public int insertExpressType(ExpressType expressType)
    {
        return expressTypeMapper.insertExpressType(expressType);
    }

    /**
     * 修改快递类型
     * 
     * @param expressType 快递类型
     * @return 结果
     */
    @Override
    public int updateExpressType(ExpressType expressType)
    {
        return expressTypeMapper.updateExpressType(expressType);
    }

    /**
     * 批量删除快递类型
     * 
     * @param ids 需要删除的快递类型主键
     * @return 结果
     */
    @Override
    public int deleteExpressTypeByIds(Long[] ids)
    {
        return expressTypeMapper.deleteExpressTypeByIds(ids);
    }

    /**
     * 删除快递类型信息
     * 
     * @param id 快递类型主键
     * @return 结果
     */
    @Override
    public int deleteExpressTypeById(Long id)
    {
        return expressTypeMapper.deleteExpressTypeById(id);
    }
}
