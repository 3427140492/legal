package com.ruoyi.lawyer.mapper;

import java.util.List;
import com.ruoyi.lawyer.domain.CaseSealApplyfors;
import com.ruoyi.lawyer.domain.RecordFiles;

/**
 * 公函记录Mapper接口
 * 
 * @author ruoyi
 * @date 2022-10-03
 */
public interface CaseSealApplyforsMapper
{
    /**
     * 查询公函记录列表
     *
     * @param caseSealApplyfor 公函记录
     * @return 公函记录集合
     */
    public List<CaseSealApplyfors> selectCaseSealApplyforList2(CaseSealApplyfors caseSealApplyfor);



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
     * 删除公函记录
     *
     * @param id 公函记录主键
     * @return 结果
     */
    public int deleteCaseSealApplyforById(Long id);

    /**
     * 查询 批量生成文书 列表
     */
    List<RecordFiles> getProduction(String caseid);
}
