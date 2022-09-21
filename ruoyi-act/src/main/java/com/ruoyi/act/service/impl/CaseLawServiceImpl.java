package com.ruoyi.act.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.act.domain.CaseCaseType;
import com.ruoyi.act.mapper.CaseLawMapper;
import com.ruoyi.act.domain.CaseLaw;
import com.ruoyi.act.service.ICaseLawService;

/**
 * 我的申请Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-07
 */
@Service
public class CaseLawServiceImpl implements ICaseLawService 
{
    @Autowired
    private CaseLawMapper caseLawMapper;

    /**
     * 查询我的申请
     * 
     * @param id 我的申请主键
     * @return 我的申请
     */
    @Override
    public CaseLaw selectCaseLawById(Integer id)
    {
        return caseLawMapper.selectCaseLawById(id);
    }

    /**
     * 查询我的申请列表
     * 
     * @param caseLaw 我的申请
     * @return 我的申请
     */
    @Override
    public List<CaseLaw> selectCaseLawList(CaseLaw caseLaw)
    {
        return caseLawMapper.selectCaseLawList(caseLaw);
    }

    /**
     * 新增我的申请
     * 
     * @param caseLaw 我的申请
     * @return 结果
     */
    @Transactional
    @Override
    public int insertCaseLaw(CaseLaw caseLaw)
    {
        int rows = caseLawMapper.insertCaseLaw(caseLaw);
        insertCaseCaseType(caseLaw);
        return rows;
    }

    /**
     * 修改我的申请
     * 
     * @param caseLaw 我的申请
     * @return 结果
     */
    @Transactional
    @Override
    public int updateCaseLaw(CaseLaw caseLaw)
    {
        caseLawMapper.deleteCaseCaseTypeById(caseLaw.getId());
        insertCaseCaseType(caseLaw);
        return caseLawMapper.updateCaseLaw(caseLaw);
    }

    /**
     * 批量删除我的申请
     * 
     * @param ids 需要删除的我的申请主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCaseLawByIds(Integer[] ids)
    {
        caseLawMapper.deleteCaseCaseTypeByIds(ids);
        return caseLawMapper.deleteCaseLawByIds(ids);
    }

    /**
     * 删除我的申请信息
     * 
     * @param id 我的申请主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCaseLawById(Integer id)
    {
        caseLawMapper.deleteCaseCaseTypeById(id);
        return caseLawMapper.deleteCaseLawById(id);
    }

    /**
     * 新增案件类型信息
     * 
     * @param caseLaw 我的申请对象
     */
    public void insertCaseCaseType(CaseLaw caseLaw)
    {
        List<CaseCaseType> caseCaseTypeList = caseLaw.getCaseCaseTypeList();
        Integer id = caseLaw.getId();
        if (StringUtils.isNotNull(caseCaseTypeList))
        {
            List<CaseCaseType> list = new ArrayList<CaseCaseType>();
            for (CaseCaseType caseCaseType : caseCaseTypeList)
            {
                caseCaseType.setId(id);
                list.add(caseCaseType);
            }
            if (list.size() > 0)
            {
                caseLawMapper.batchCaseCaseType(list);
            }
        }
    }
}
