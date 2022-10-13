package com.ruoyi.lawyer.service.impl;

import java.util.List;

import com.ruoyi.lawyer.domain.RecordFiles;
import com.ruoyi.lawyer.mapper.CaseFilessMapper;
import com.ruoyi.lawyer.domain.CaseFiless;
import com.ruoyi.lawyer.domain.CaseSealApplyfors;
import com.ruoyi.lawyer.mapper.CaseSealApplyforsMapper;
import com.ruoyi.lawyer.service.ICaseSealApplyforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 公函记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-03
 */
@Service
public class CaseSealApplyforServiceImpls implements ICaseSealApplyforService {
    @Autowired
    private CaseSealApplyforsMapper caseSealApplyforMapper;

    @Autowired
    private CaseFilessMapper caseFilesMapper;

    /**
     * 删除公函记录信息
     *
     * @param id 公函记录主键
     * @return 结果
     */
    @Override
    public int deleteCaseSealApplyforById(String id)
    {
        return caseSealApplyforMapper.deleteCaseSealApplyforById(Long.valueOf(id));
    }

    /**
     * 查询公函记录列表
     *
     * @param caseSealApplyfor 公函记录
     * @return 公函记录
     */
    @Override
    public List<CaseSealApplyfors> selectCaseSealApplyforList(CaseSealApplyfors caseSealApplyfor)
    {

        return caseSealApplyforMapper.selectCaseSealApplyforList2(caseSealApplyfor);
    }

    /**
     * 新增公函记录
     *
     * @param caseSealApplyfor 公函记录
     * @return 结果
     */
    @Override
    public int insertCaseSealApplyfor(CaseSealApplyfors caseSealApplyfor)
    {
        int i = caseSealApplyforMapper.insertCaseSealApplyfor(caseSealApplyfor);
        if(i > 0){
            CaseFiless caseFiles = new CaseFiless();
            caseFiles.setAppid(Long.valueOf(caseSealApplyfor.getId()));
            caseFiles.setFilename(caseSealApplyfor.getApplyforUsingTheItem());
            caseFiles.setTypeid(caseSealApplyfor.getTypeid());
            caseFilesMapper.insertCaseFiles(caseFiles);
        }
        return i;
    }

    /**
     * 查询公函记录列表
     *
     * @param id 案件id
     * @return 公函记录集合
     */
    public List<CaseSealApplyfors> selectCaseSealApplyforList(String id) {
        return caseSealApplyforMapper.selectCaseSealApplyforList(id);
    }

    /**
     * 查询 批量生成文书 列表
     */
    @Override
    public List<RecordFiles> getProduction(String caseid) {
        return caseSealApplyforMapper.getProduction(caseid);
    }

}