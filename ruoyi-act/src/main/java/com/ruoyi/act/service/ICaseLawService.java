package com.ruoyi.act.service;

import java.util.List;
import com.ruoyi.act.domain.CaseLaw;

/**
 * 我的申请Service接口
 * 
 * @author ruoyi
 * @date 2022-09-07
 */
public interface ICaseLawService 
{
    /**
     * 查询我的申请
     * 
     * @param id 我的申请主键
     * @return 我的申请
     */
    public CaseLaw selectCaseLawById(Integer id);

    /**
     * 查询我的申请列表
     * 
     * @param caseLaw 我的申请
     * @return 我的申请集合
     */
    public List<CaseLaw> selectCaseLawList(CaseLaw caseLaw);

    /**
     * 新增我的申请
     * 
     * @param caseLaw 我的申请
     * @return 结果
     */
    public int insertCaseLaw(CaseLaw caseLaw);

    /**
     * 修改我的申请
     * 
     * @param caseLaw 我的申请
     * @return 结果
     */
    public int updateCaseLaw(CaseLaw caseLaw);

    /**
     * 批量删除我的申请
     * 
     * @param ids 需要删除的我的申请主键集合
     * @return 结果
     */
    public int deleteCaseLawByIds(Integer[] ids);

    /**
     * 删除我的申请信息
     * 
     * @param id 我的申请主键
     * @return 结果
     */
    public int deleteCaseLawById(Integer id);
}
