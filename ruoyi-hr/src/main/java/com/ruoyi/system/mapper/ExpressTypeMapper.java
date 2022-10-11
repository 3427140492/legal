package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.ExpressType;

/**
 * 快递类型Mapper接口
 * 
 * @author ruoyi
 * @date 2022-10-11
 */
public interface ExpressTypeMapper 
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
     * 删除快递类型
     * 
     * @param id 快递类型主键
     * @return 结果
     */
    int deleteExpressTypeById(Long id);

    /**
     * 批量删除快递类型
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteExpressTypeByIds(Long[] ids);
}
