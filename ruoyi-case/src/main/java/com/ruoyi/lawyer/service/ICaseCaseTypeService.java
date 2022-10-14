package com.ruoyi.lawyer.service;


import com.ruoyi.lawyer.domain.CaseCaseTypes;
import java.util.List;

/**
 * 案件类型Service接口
 * 
 * @author ruoyi
 * @date 2022-09-03
 */
public interface ICaseCaseTypeService
{

    /**
     * 查询
     *
     * @param id 案件id
     * @return 案件类型对象
     */
    public CaseCaseTypes getCaseTypeMin(Long id);

    /**
     * 查询案件类型列表
     *
     * @param caseCaseType 案件类型
     * @return 案件类型集合
     */
    public List<CaseCaseTypes> selectCaseCaseTypeList(CaseCaseTypes caseCaseType);

    /**
     * 查询案件类型子列表
     *
     * @param id 案件类型id
     * @return 案件类型集合
     */
    public List<CaseCaseTypes> selectCaseCaseTypeListById(Long id);
}
