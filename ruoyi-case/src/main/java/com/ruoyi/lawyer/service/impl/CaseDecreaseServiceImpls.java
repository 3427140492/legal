package com.ruoyi.lawyer.service.impl;

import java.util.List;

import com.ruoyi.lawyer.domain.CaseDecreases;
import com.ruoyi.lawyer.mapper.CaseDecreasesMapper;
import com.ruoyi.lawyer.service.ICaseDecreaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 减免申请Service业务层处理
 * 
 * @author ck
 * @date 2022-09-21
 */
@Service
public class CaseDecreaseServiceImpls implements ICaseDecreaseService {
    @Autowired
    private CaseDecreasesMapper caseDecreaseMapper;

    /**
     * 查询减免申请列表
     *
     * @param caseSubmitter
     * @return 减免申请集合
     */
    @Override
    public List<CaseDecreases> selectCaseDecreaseList1(String caseSubmitter) {
        return caseDecreaseMapper.selectCaseDecreaseList1(caseSubmitter);
    }

    /**
     * 查询减免申请列表
     *
     * @param caseDecrease 减免申请
     * @return 减免申请
     */
    @Override
    public List<CaseDecreases> selectCaseDecreaseList(CaseDecreases caseDecrease)
    {
        return caseDecreaseMapper.selectCaseDecreaseList(caseDecrease);
    }

    /**
     * 新增减免申请
     *
     * @param caseDecrease 减免申请
     * @return 结果
     */
    @Override
    public int insertCaseDecrease(CaseDecreases caseDecrease) {
        return caseDecreaseMapper.insertCaseDecrease(caseDecrease);
    }
}