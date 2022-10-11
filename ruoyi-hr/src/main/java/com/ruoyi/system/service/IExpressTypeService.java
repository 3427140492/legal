package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.ExpressType;

/**
 * 快递类型Service接口
 * 
 * @author ruoyi
 * @date 2022-10-11
 */
public interface IExpressTypeService 
{
    /**
     * 查询快递类型
     * 
     * @param id 快递类型主键
     * @return 快递类型
     */
        ExpressType selectExpressTypeById(Long id);

    /**
     * 查询快递类型列表
     * 
     * @param expressType 快递类型
     * @return 快递类型集合
     */
    List<ExpressType> selectExpressTypeList(ExpressType expressType);

    /**
     * 新增快递类型
     * 
     * @param expressType 快递类型
     * @return 结果
     */
    int insertExpressType(ExpressType expressType);

    /**
     * 修改快递类型
     * 
     * @param expressType 快递类型
     * @return 结果
     */
    int updateExpressType(ExpressType expressType);

    /**
     * 批量删除快递类型
     * 
     * @param ids 需要删除的快递类型主键集合
     * @return 结果
     */
    int deleteExpressTypeByIds(Long[] ids);

    /**
     * 删除快递类型信息
     * 
     * @param id 快递类型主键
     * @return 结果
     */
    int deleteExpressTypeById(Long id);
}
