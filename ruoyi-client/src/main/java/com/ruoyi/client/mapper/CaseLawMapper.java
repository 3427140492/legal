package com.ruoyi.client.mapper;

import java.util.List;
import com.ruoyi.client.domain.CaseLaw;

/**
 * 我的当事人Mapper接口
 * 
 * @author gk
 * @date 2022-09-16
 */
public interface CaseLawMapper 
{
    /**
     * 查询我的当事人
     * 
     * @param id 我的当事人主键
     * @return 我的当事人
     */
    public CaseLaw selectCaseLawById(Integer id);

    /**
     * 查询我的当事人列表
     * 
     * @param caseLaw 我的当事人
     * @return 我的当事人集合
     */
    public List<CaseLaw> selectCaseLawList(CaseLaw caseLaw);

    /**
     * 新增我的当事人
     * 
     * @param caseLaw 我的当事人
     * @return 结果
     */
    public int insertCaseLaw(CaseLaw caseLaw);

    /**
     * 修改我的当事人
     * 
     * @param caseLaw 我的当事人
     * @return 结果
     */
    public int updateCaseLaw(CaseLaw caseLaw);

    /**
     * 删除我的当事人
     * 
     * @param id 我的当事人主键
     * @return 结果
     */
    public int deleteCaseLawById(Integer id);

    /**
     * 批量删除我的当事人
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCaseLawByIds(Integer[] ids);
}
