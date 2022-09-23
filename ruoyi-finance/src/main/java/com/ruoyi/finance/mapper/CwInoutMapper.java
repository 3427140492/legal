package com.ruoyi.finance.mapper;

import java.util.List;
import com.ruoyi.finance.domain.FinCwInout;
import com.ruoyi.finance.domain.CwInoutType;

/**
 * 全所收支明细Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-22
 */
public interface CwInoutMapper 
{
    /**
     * 查询全所收支明细
     * 
     * @param id 全所收支明细主键
     * @return 全所收支明细
     */
    public FinCwInout selectCwInoutById(String id);

    /**
     * 查询全所收支明细列表
     * 
     * @param finCwInout 全所收支明细
     * @return 全所收支明细集合
     */
    public List<FinCwInout> selectCwInoutList(FinCwInout finCwInout);

    /**
     * 新增全所收支明细
     * 
     * @param finCwInout 全所收支明细
     * @return 结果
     */
    public int insertCwInout(FinCwInout finCwInout);

    /**
     * 修改全所收支明细
     * 
     * @param finCwInout 全所收支明细
     * @return 结果
     */
    public int updateCwInout(FinCwInout finCwInout);

    /**
     * 删除全所收支明细
     * 
     * @param id 全所收支明细主键
     * @return 结果
     */
    public int deleteCwInoutById(String id);

    /**
     * 批量删除全所收支明细
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCwInoutByIds(String[] ids);

    /**
     * 批量删除${subTable.functionName}
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCwInoutTypeByIds(String[] ids);
    
    /**
     * 批量新增${subTable.functionName}
     * 
     * @param cwInoutTypeList ${subTable.functionName}列表
     * @return 结果
     */
    public int batchCwInoutType(List<CwInoutType> cwInoutTypeList);
    

    /**
     * 通过全所收支明细主键删除${subTable.functionName}信息
     * 
     * @param id 全所收支明细ID
     * @return 结果
     */
    public int deleteCwInoutTypeById(String id);
}
