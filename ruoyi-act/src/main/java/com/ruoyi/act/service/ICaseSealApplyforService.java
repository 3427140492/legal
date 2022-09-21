package com.ruoyi.act.service;

import java.util.List;
import com.ruoyi.act.domain.CaseSealApplyfor;

/**
 * 用印申请Service接口
 * 
 * @author ruoyi
 * @date 2022-09-14
 */
public interface ICaseSealApplyforService 
{
    /**
     * 查询用印申请
     * 
     * @param id 用印申请主键
     * @return 用印申请
     */
    public CaseSealApplyfor selectCaseSealApplyforById(Integer id);

    /**
     * 查询用印申请列表
     * 
     * @param caseSealApplyfor 用印申请
     * @return 用印申请集合
     */
    public List<CaseSealApplyfor> selectCaseSealApplyforList(CaseSealApplyfor caseSealApplyfor);

    /**
     * 新增用印申请
     * 
     * @param caseSealApplyfor 用印申请
     * @return 结果
     */
    public int insertCaseSealApplyfor(CaseSealApplyfor caseSealApplyfor);

    /**
     * 修改用印申请
     * 
     * @param caseSealApplyfor 用印申请
     * @return 结果
     */
    public int updateCaseSealApplyfor(CaseSealApplyfor caseSealApplyfor);

    /**
     * 批量删除用印申请
     * 
     * @param ids 需要删除的用印申请主键集合
     * @return 结果
     */
    public int deleteCaseSealApplyforByIds(Integer[] ids);

    /**
     * 删除用印申请信息
     * 
     * @param id 用印申请主键
     * @return 结果
     */
    public int deleteCaseSealApplyforById(Integer id);
}
