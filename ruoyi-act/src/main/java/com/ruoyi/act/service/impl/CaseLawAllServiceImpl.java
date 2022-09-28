package com.ruoyi.act.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.act.mapper.CaseLawAllMapper;
import com.ruoyi.act.domain.CaseLawAll;
import com.ruoyi.act.service.ICaseLawAllService;

/**
 * 审批中心Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-27
 */
@Service
public class CaseLawAllServiceImpl implements ICaseLawAllService 
{
    @Autowired
    private CaseLawAllMapper caseLawAllMapper;

    /**
     * 查询审批中心
     * 
     * @param id 审批中心主键
     * @return 审批中心
     */
    @Override
    public CaseLawAll selectCaseLawAllById(Integer id)
    {
        return caseLawAllMapper.selectCaseLawAllById(id);
    }

    /**
     * 查询审批中心列表
     * 
     * @param caseLawAll 审批中心
     * @return 审批中心
     */
    @Override
    public List<CaseLawAll> selectCaseLawAllList(CaseLawAll caseLawAll)
    {
        return caseLawAllMapper.selectCaseLawAllList(caseLawAll);
    }

    /**
     * 新增审批中心
     * 
     * @param caseLawAll 审批中心
     * @return 结果
     */
    @Override
    public int insertCaseLawAll(CaseLawAll caseLawAll)
    {
        return caseLawAllMapper.insertCaseLawAll(caseLawAll);
    }

    /**
     * 修改审批中心
     * 
     * @param caseLawAll 审批中心
     * @return 结果
     */
    @Override
    public int updateCaseLawAll(CaseLawAll caseLawAll)
    {
        return caseLawAllMapper.updateCaseLawAll(caseLawAll);
    }

    /**
     * 批量删除审批中心
     * 
     * @param ids 需要删除的审批中心主键
     * @return 结果
     */
    @Override
    public int deleteCaseLawAllByIds(Integer[] ids)
    {
        return caseLawAllMapper.deleteCaseLawAllByIds(ids);
    }

    /**
     * 删除审批中心信息
     * 
     * @param id 审批中心主键
     * @return 结果
     */
    @Override
    public int deleteCaseLawAllById(Integer id)
    {
        return caseLawAllMapper.deleteCaseLawAllById(id);
    }
}
