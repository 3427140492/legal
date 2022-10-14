package com.ruoyi.lawyer.service.impl;

import com.ruoyi.lawyer.domain.CaseLaws;
import com.ruoyi.lawyer.mapper.*;
import com.ruoyi.lawyer.service.ICaseLawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 案件法律信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-04
 */
@Service
public class CaseLawServiceImpls implements ICaseLawService
{
    @Autowired
    private CaseLawsMapper caseLawMapper;

    @Autowired
    private ClientsMapper clientMapper;

    @Autowired
    private CaseDecreasesMapper caseDecreaseMapper;

    @Autowired
    private CaseSealApplyforsMapper caseSealApplyforMapper;

    @Autowired
    private ApplysMapper applyMapper;

    /**
     * 查询 咨询与代理 列表
     *
     * @param caseLaw 查询参数
     * @return 咨询与代理 列表
     */
    @Override
    public List<CaseLaws> consultagency(CaseLaws caseLaw) {
        return caseLawMapper.consultagency(caseLaw);
    }

    /**
     * 查询 回收案件 列表
     *
     * @param caseLaw 查询参数
     * @return 回收案件 列表
     */
    @Override
    public List<CaseLaws> selectCaseRecycleList(CaseLaws caseLaw) {
        return caseLawMapper.selectCaseRecycleList(caseLaw);
    }

    /**
     * 查询 我的案件汇总 列表
     *
     * @param caseLaw 查询参数
     * @return 我的个人案件列表
     */
    @Override
    public List<CaseLaws> myCaseSummary(CaseLaws caseLaw) {
        return caseLawMapper.myCaseSummary(caseLaw);
    }

    /**
     * 查询 我的待收款案件 年份
     *
     * @return 年份集合
     */
    @Override
    public List<CaseLaws> myStayYear() {
        return caseLawMapper.myStayYear();
    }

    /**
     * 查询 我的待收款案件 列表
     *
     * @param caseLaw 查询参数
     * @return 我的个人案件列表
     */
    @Override
    public List<CaseLaws> myStayProceedsCase(CaseLaws caseLaw) {
        return caseLawMapper.myStayProceedsCase(caseLaw);
    }

    /**
     * 查询 共享案件 列表
     *
     * @param caseLaw 查询参数
     * @return 我的个人案件列表
     */
    @Override
    public List<CaseLaws> shareTheCaseList(CaseLaws caseLaw) {
        return caseLawMapper.shareTheCaseList(caseLaw);
    }

    /**
     * 查询 我的合办案件 列表
     *
     * @param caseLaw 查询参数
     * @return 我的个人案件列表
     */
    @Override
    public List<CaseLaws> selectMyCaseLawLists(CaseLaws caseLaw) {
        return caseLawMapper.selectMyCaseLawLists(caseLaw);
    }

    /**
     * 查询案件信息
     *
     * @param id 案件信息主键
     * @return 案件信息
     */
    @Override
    public CaseLaws selectCaseLawById(Integer id)
    {
        return caseLawMapper.selectCaseLawById(id);
    }

    /**
     * 案件列表 (代开发票)
     *
     * @param caseLaw 查询参数
     * @return 结果
     */
    @Override
    public List<CaseLaws> selectInvoiceCaseLawList(CaseLaws caseLaw) {
        return caseLawMapper.selectInvoiceCaseLawList(caseLaw);
    }

    /**
     * 修改结案信息
     *
     * @param caseLaw 案件法律信息
     * @return 结果
     */
    @Override
    public int LawEnd(CaseLaws caseLaw) {
        clientMapper.cuStatus(caseLaw.getId()); // 修改客户状态
        return caseLawMapper.updateCaseLaw(caseLaw);
    }

    /**
     * 修改案件信息
     *
     * @param caseLaw 案件信息
     * @return 结果
     */
    @Override
    public int updateCaseLaw(CaseLaws caseLaw)
    {
        caseDecreaseMapper.deleteCaseDecreaseById(Long.valueOf(caseLaw.getId()));
        caseSealApplyforMapper.deleteCaseSealApplyforById(Long.valueOf(caseLaw.getId()));
        applyMapper.deleteApplyById(String.valueOf(caseLaw.getId()));
        return caseLawMapper.updateCaseLaw(caseLaw);
    }

    /**
     * 获取案件信息详细信息 (添加收款记录)
     *
     * @param id 案件信息主键
     * @return 案件信息
     */
    @Override
    public CaseLaws getCaseLawInfo(String id) {
        return caseLawMapper.getCaseLawInfo(id);
    }

    /**
     * 查询 我的个人案件 列表
     *
     * @param caseLaw 查询参数
     * @return 我的个人案件列表
     */
    @Override
    public List<CaseLaws> selectMyCaseLawList(CaseLaws caseLaw) {
        return caseLawMapper.selectMyCaseLawList(caseLaw);
    }

    /**
     * 查询案件信息 (减免申请数据)
     *
     * @param id 案件id
     * @return 结果
     */
    @Override
    public CaseLaws getCLAndDecById(Long id) {
        return caseLawMapper.getCLAndDecById(id);
    }

    /**
     * 新增案件法律信息
     *
     * @param caseLaw 案件法律信息
     * @return 结果
     */
    @Override
    public int addCaseLaw(CaseLaws caseLaw)
    {
        return caseLawMapper.addCaseLaw(caseLaw);
    }


    /**
     * 查询案件id
     *
     * @return 案件法律信息id
     */
    public List<CaseLaws> getCaseLawId()
    {
        return caseLawMapper.getCaseLawId();
    }

    /**
     * 案件法律信息列表 (条件查询)
     *
     * @param caseLaw 参数
     * @return 案件法律信息集合
     */
    @Override
    public List<CaseLaws> selectCaseLawParamList(CaseLaws caseLaw)
    {
        return caseLawMapper.selectCaseLawParamList(caseLaw);
    }

}
