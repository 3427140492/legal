package com.ruoyi.act.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.act.domain.CaseFiles;
import com.ruoyi.act.mapper.CaseSealApplyforMapper;
import com.ruoyi.act.domain.CaseSealApplyfor;
import com.ruoyi.act.service.ICaseSealApplyforService;

/**
 * 用印申请Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-14
 */
@Service
public class CaseSealApplyforServiceImpl implements ICaseSealApplyforService 
{
    @Autowired
    private CaseSealApplyforMapper caseSealApplyforMapper;

    /**
     * 查询用印申请
     * 
     * @param id 用印申请主键
     * @return 用印申请
     */
    @Override
    public CaseSealApplyfor selectCaseSealApplyforById(Integer id)
    {
        return caseSealApplyforMapper.selectCaseSealApplyforById(id);
    }

    /**
     * 查询用印申请列表
     * 
     * @param caseSealApplyfor 用印申请
     * @return 用印申请
     */
    @Override
    public List<CaseSealApplyfor> selectCaseSealApplyforList(CaseSealApplyfor caseSealApplyfor)
    {
        return caseSealApplyforMapper.selectCaseSealApplyforList(caseSealApplyfor);
    }

    /**
     * 新增用印申请
     * 
     * @param caseSealApplyfor 用印申请
     * @return 结果
     */
    @Transactional
    @Override
    public int insertCaseSealApplyfor(CaseSealApplyfor caseSealApplyfor)
    {
        int rows = caseSealApplyforMapper.insertCaseSealApplyfor(caseSealApplyfor);
        insertCaseFiles(caseSealApplyfor);
        return rows;
    }

    /**
     * 修改用印申请
     * 
     * @param caseSealApplyfor 用印申请
     * @return 结果
     */
    @Transactional
    @Override
    public int updateCaseSealApplyfor(CaseSealApplyfor caseSealApplyfor)
    {
        caseSealApplyforMapper.deleteCaseFilesById(caseSealApplyfor.getId());
        insertCaseFiles(caseSealApplyfor);
        return caseSealApplyforMapper.updateCaseSealApplyfor(caseSealApplyfor);
    }

    /**
     * 批量删除用印申请
     * 
     * @param ids 需要删除的用印申请主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCaseSealApplyforByIds(Integer[] ids)
    {
        caseSealApplyforMapper.deleteCaseFilesByIds(ids);
        return caseSealApplyforMapper.deleteCaseSealApplyforByIds(ids);
    }

    /**
     * 删除用印申请信息
     * 
     * @param id 用印申请主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCaseSealApplyforById(Integer id)
    {
        caseSealApplyforMapper.deleteCaseFilesById(id);
        return caseSealApplyforMapper.deleteCaseSealApplyforById(id);
    }

    /**
     * 新增${subTable.functionName}信息
     * 
     * @param caseSealApplyfor 用印申请对象
     */
    public void insertCaseFiles(CaseSealApplyfor caseSealApplyfor)
    {
        List<CaseFiles> caseFilesList = caseSealApplyfor.getCaseFilesList();
        Integer id = caseSealApplyfor.getId();
        if (StringUtils.isNotNull(caseFilesList))
        {
            List<CaseFiles> list = new ArrayList<CaseFiles>();
            for (CaseFiles caseFiles : caseFilesList)
            {
                caseFiles.setId(id);
                list.add(caseFiles);
            }
            if (list.size() > 0)
            {
                caseSealApplyforMapper.batchCaseFiles(list);
            }
        }
    }
}
