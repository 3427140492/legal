package com.ruoyi.client.service;

import java.util.List;
import com.ruoyi.client.domain.CaseLaw;

/**
 * 我的当事人Service接口
 * 
 * @author gk
 * @date 2022-09-16
 */
public interface ICaseLawService 
{
    /**
     * 查询我的当事人
     * 
     * @param id 我的当事人主键
     * @return 我的当事人
     */
    CaseLaw selectCaseLawById(Integer id);

    /**
     * 查询我的当事人列表
     * 
     * @param caseLaw 我的当事人
     * @return 我的当事人集合
     */
    List<CaseLaw> selectCaseLawList(CaseLaw caseLaw);

    /**
     * 新增我的当事人
     * 
     * @param caseLaw 我的当事人
     * @return 结果
     */
    int insertCaseLaw(CaseLaw caseLaw);

    /**
     * 修改我的当事人
     * 
     * @param caseLaw 我的当事人
     * @return 结果
     */
    int updateCaseLaw(CaseLaw caseLaw);

    /**
     * 批量删除我的当事人
     * 
     * @param ids 需要删除的我的当事人主键集合
     * @return 结果
     */
    int deleteCaseLawByIds(Integer[] ids);

    /**
     * 删除我的当事人信息
     * 
     * @param id 我的当事人主键
     * @return 结果
     */
    int deleteCaseLawById(Integer id);
}