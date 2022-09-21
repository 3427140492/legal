package com.ruoyi.act.mapper;

import java.util.List;
import com.ruoyi.act.domain.CaseCaseType;

/**
 * 案件类型Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-13
 */
public interface CaseCaseTypeMapper 
{
    /**
     * 查询案件类型
     * 
     * @param id 案件类型主键
     * @return 案件类型
     */
    public CaseCaseType selectCaseCaseTypeById(Integer id);

    /**
     * 查询案件类型列表
     * 
     * @param caseCaseType 案件类型
     * @return 案件类型集合
     */
    public List<CaseCaseType> selectCaseCaseTypeList(CaseCaseType caseCaseType);

    /**
     * 新增案件类型
     * 
     * @param caseCaseType 案件类型
     * @return 结果
     */
    public int insertCaseCaseType(CaseCaseType caseCaseType);

    /**
     * 修改案件类型
     * 
     * @param caseCaseType 案件类型
     * @return 结果
     */
    public int updateCaseCaseType(CaseCaseType caseCaseType);

    /**
     * 删除案件类型
     * 
     * @param id 案件类型主键
     * @return 结果
     */
    public int deleteCaseCaseTypeById(Integer id);

    /**
     * 批量删除案件类型
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCaseCaseTypeByIds(Integer[] ids);
}
