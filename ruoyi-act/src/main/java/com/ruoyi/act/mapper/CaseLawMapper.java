package com.ruoyi.act.mapper;

import java.util.List;
import com.ruoyi.act.domain.CaseLaw;
import com.ruoyi.act.domain.CaseCaseType;

/**
 * 我的申请Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-07
 */
public interface CaseLawMapper 
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
     * 删除我的申请
     * 
     * @param id 我的申请主键
     * @return 结果
     */
    public int deleteCaseLawById(Integer id);

    /**
     * 批量删除我的申请
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCaseLawByIds(Integer[] ids);

    /**
     * 批量删除案件类型
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCaseCaseTypeByIds(Integer[] ids);
    
    /**
     * 批量新增案件类型
     * 
     * @param caseCaseTypeList 案件类型列表
     * @return 结果
     */
    public int batchCaseCaseType(List<CaseCaseType> caseCaseTypeList);
    

    /**
     * 通过我的申请主键删除案件类型信息
     * 
     * @param id 我的申请ID
     * @return 结果
     */
    public int deleteCaseCaseTypeById(Integer id);
}
