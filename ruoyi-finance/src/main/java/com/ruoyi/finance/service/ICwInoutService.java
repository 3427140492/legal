package com.ruoyi.finance.service;

import java.util.List;

import com.ruoyi.finance.domain.CwInoutType;
import com.ruoyi.finance.domain.FinCwInout;

/**
 * 全所收支明细Service接口
 * 
 * @author ruoyi
 * @date 2022-09-22
 */
public interface ICwInoutService 
{
    public Object selectCwInoutTypeList() ;
    public List<Object> getChildren(String id);

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
     * 批量删除全所收支明细
     * 
     * @param ids 需要删除的全所收支明细主键集合
     * @return 结果
     */
    public int deleteCwInoutByIds(String[] ids);

    /**
     * 删除全所收支明细信息
     * 
     * @param id 全所收支明细主键
     * @return 结果
     */
    public int deleteCwInoutById(String id);
}
