package com.ruoyi.lawyer.service;

import java.util.List;
import com.ruoyi.lawyer.domain.CaseSealApplyfors;
import com.ruoyi.lawyer.domain.RecordFiles;

/**
 * 公函记录Service接口
 * 
 * @author ruoyi
 * @date 2022-10-03
 */
public interface ICaseSealApplyforService
{

    /**
     * 删除公函记录信息
     *
     * @param id 公函记录主键
     * @return 结果
     */
    public int deleteCaseSealApplyforById(String id);

    /**
     * 查询公函记录列表
     *
     * @param caseSealApplyfor 公函记录
     * @return 公函记录集合
     */
    public List<CaseSealApplyfors> selectCaseSealApplyforList(CaseSealApplyfors caseSealApplyfor);

    /**
     * 新增公函记录
     *
     * @param caseSealApplyfor 公函记录
     * @return 结果
     */
    public int insertCaseSealApplyfor(CaseSealApplyfors caseSealApplyfor);

    /**
     * 查询公函记录列表
     *
     * @param id 案件id
     * @return 公函记录集合
     */
    public List<CaseSealApplyfors> selectCaseSealApplyforList(String id);

    /**
     * 查询 批量生成文书 列表
     */
    List<RecordFiles> getProduction(String caseid);
}
