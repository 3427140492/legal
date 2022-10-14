package com.ruoyi.lawyer.service;

import java.util.List;
import com.ruoyi.lawyer.domain.CaseDecreases;

/**
 * 减免申请Service接口
 * 
 * @author ck
 * @date 2022-09-21
 */
public interface ICaseDecreaseService 
{

    /**
     * 查询减免申请列表
     *
     * @return 减免申请集合
     */
    public List<CaseDecreases> selectCaseDecreaseList1(String caseSubmitter);


    /**
     * 查询减免申请列表
     *
     * @param caseDecrease 减免申请
     * @return 减免申请集合
     */
    public List<CaseDecreases> selectCaseDecreaseList(CaseDecreases caseDecrease);

    /**
     * 新增减免申请
     * 
     * @param caseDecrease 减免申请
     * @return 结果
     */
    public int insertCaseDecrease(CaseDecreases caseDecrease);

}
