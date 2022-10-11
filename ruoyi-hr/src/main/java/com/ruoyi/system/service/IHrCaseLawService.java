package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.HrCaseLaw;

/**
 * 案件法律Service接口
 * 
 * @author ruoyi
 * @date 2022-10-11
 */
public interface IHrCaseLawService 
{
    /**
     * 查询案件法律
     * 
     * @param id 案件法律主键
     * @return 案件法律
     */
        HrCaseLaw selectHrCaseLawById(Integer id);

    /**
     * 查询案件法律列表
     * 
     * @param hrCaseLaw 案件法律
     * @return 案件法律集合
     */
    List<HrCaseLaw> selectHrCaseLawList(HrCaseLaw hrCaseLaw);

    /**
     * 新增案件法律
     * 
     * @param hrCaseLaw 案件法律
     * @return 结果
     */
    int insertHrCaseLaw(HrCaseLaw hrCaseLaw);

    /**
     * 修改案件法律
     * 
     * @param hrCaseLaw 案件法律
     * @return 结果
     */
    int updateHrCaseLaw(HrCaseLaw hrCaseLaw);

    /**
     * 批量删除案件法律
     * 
     * @param ids 需要删除的案件法律主键集合
     * @return 结果
     */
    int deleteHrCaseLawByIds(Integer[] ids);

    /**
     * 删除案件法律信息
     * 
     * @param id 案件法律主键
     * @return 结果
     */
    int deleteHrCaseLawById(Integer id);
}
