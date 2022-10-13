package com.ruoyi.lawyer.service.impl;

import com.ruoyi.lawyer.domain.CaseCaseTypes;
import com.ruoyi.lawyer.mapper.CaseCaseTypesMapper;
import com.ruoyi.lawyer.service.ICaseCaseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 案件类型Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-03
 */
@Service
public class CaseCaseTypeServiceImpl implements ICaseCaseTypeService {

    @Autowired
    private CaseCaseTypesMapper caseCaseTypeMapper;

    /**
     * 查询
     *
     * @param id 案件id
     * @return 案件类型对象
     */
    @Override
    public CaseCaseTypes getCaseTypeMin(Long id) {
        return caseCaseTypeMapper.getCaseTypeMin(id);
    }

    /**
     * 查询案件类型列表
     *
     *
     * @param caseCaseType 案件类型
     * @return 案件类型
     */
    @Override
    public List<CaseCaseTypes> selectCaseCaseTypeList(CaseCaseTypes caseCaseType) {
        return caseCaseTypeMapper.selectCaseCaseTypeList(caseCaseType);
    }

    /**
     * 查询案件类型子列表
     *
     *
     * @param id 案件类型id
     * @return 案件类型集合
     */
    @Override
    public List<CaseCaseTypes> selectCaseCaseTypeListById(Long id) {
        return caseCaseTypeMapper.selectCaseCaseTypeListById(id);
    }

}