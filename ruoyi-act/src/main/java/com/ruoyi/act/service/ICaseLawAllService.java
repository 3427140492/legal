package com.ruoyi.act.service;

import java.util.List;
import com.ruoyi.act.domain.CaseLawAll;

/**
 * 审批中心Service接口
 * 
 * @author ruoyi
 * @date 2022-09-27
 */
public interface ICaseLawAllService 
{
    /**
     * 查询审批中心
     * 
     * @param id 审批中心主键
     * @return 审批中心
     */
        CaseLawAll selectCaseLawAllById(Integer id);

    /**
     * 查询审批中心列表
     * 
     * @param caseLawAll 审批中心
     * @return 审批中心集合
     */
    List<CaseLawAll> selectCaseLawAllList(CaseLawAll caseLawAll);

    /**
     * 新增审批中心
     * 
     * @param caseLawAll 审批中心
     * @return 结果
     */
    int insertCaseLawAll(CaseLawAll caseLawAll);

    /**
     * 修改审批中心
     * 
     * @param caseLawAll 审批中心
     * @return 结果
     */
    int updateCaseLawAll(CaseLawAll caseLawAll);

    /**
     * 批量删除审批中心
     * 
     * @param ids 需要删除的审批中心主键集合
     * @return 结果
     */
    int deleteCaseLawAllByIds(Integer[] ids);

    /**
     * 删除审批中心信息
     * 
     * @param id 审批中心主键
     * @return 结果
     */
    int deleteCaseLawAllById(Integer id);
}
