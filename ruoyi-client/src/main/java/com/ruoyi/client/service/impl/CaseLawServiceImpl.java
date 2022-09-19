package com.ruoyi.client.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.client.mapper.CaseLawMapper;
import com.ruoyi.client.domain.CaseLaw;
import com.ruoyi.client.service.ICaseLawService;

/**
 * 我的当事人Service业务层处理
 * 
 * @author gk
 * @date 2022-09-16
 */
@Service
public class CaseLawServiceImpl implements ICaseLawService 
{
    @Autowired
    private CaseLawMapper caseLawMapper;

    /**
     * 查询我的当事人
     * 
     * @param id 我的当事人主键
     * @return 我的当事人
     */
    @Override
    public CaseLaw selectCaseLawById(Integer id)
    {
        return caseLawMapper.selectCaseLawById(id);
    }

    /**
     * 查询我的当事人列表
     * 
     * @param caseLaw 我的当事人
     * @return 我的当事人
     */
    @Override
    public List<CaseLaw> selectCaseLawList(CaseLaw caseLaw)
    {
        return caseLawMapper.selectCaseLawList(caseLaw);
    }

    /**
     * 新增我的当事人
     * 
     * @param caseLaw 我的当事人
     * @return 结果
     */
    @Override
    public int insertCaseLaw(CaseLaw caseLaw)
    {
        return caseLawMapper.insertCaseLaw(caseLaw);
    }

    /**
     * 修改我的当事人
     * 
     * @param caseLaw 我的当事人
     * @return 结果
     */
    @Override
    public int updateCaseLaw(CaseLaw caseLaw)
    {
        return caseLawMapper.updateCaseLaw(caseLaw);
    }

    /**
     * 批量删除我的当事人
     * 
     * @param ids 需要删除的我的当事人主键
     * @return 结果
     */
    @Override
    public int deleteCaseLawByIds(Integer[] ids)
    {
        return caseLawMapper.deleteCaseLawByIds(ids);
    }

    /**
     * 删除我的当事人信息
     * 
     * @param id 我的当事人主键
     * @return 结果
     */
    @Override
    public int deleteCaseLawById(Integer id)
    {
        return caseLawMapper.deleteCaseLawById(id);
    }
}
