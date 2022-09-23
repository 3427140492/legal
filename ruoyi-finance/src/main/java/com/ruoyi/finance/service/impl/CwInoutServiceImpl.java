package com.ruoyi.finance.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.finance.domain.CwInoutType;
import com.ruoyi.finance.mapper.CwInoutMapper;
import com.ruoyi.finance.domain.FinCwInout;
import com.ruoyi.finance.service.ICwInoutService;

/**
 * 全所收支明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-22
 */
@Service
public class CwInoutServiceImpl implements ICwInoutService 
{
    @Autowired
    private CwInoutMapper cwInoutMapper;

    /**
     * 查询全所收支明细
     * 
     * @param id 全所收支明细主键
     * @return 全所收支明细
     */
    @Override
    public FinCwInout selectCwInoutById(String id)
    {
        return cwInoutMapper.selectCwInoutById(id);
    }

    /**
     * 查询全所收支明细列表
     * 
     * @param finCwInout 全所收支明细
     * @return 全所收支明细
     */
    @Override
    public List<FinCwInout> selectCwInoutList(FinCwInout finCwInout)
    {
        return cwInoutMapper.selectCwInoutList(finCwInout);
    }

    /**
     * 新增全所收支明细
     * 
     * @param finCwInout 全所收支明细
     * @return 结果
     */
    @Transactional
    @Override
    public int insertCwInout(FinCwInout finCwInout)
    {
        int rows = cwInoutMapper.insertCwInout(finCwInout);
        insertCwInoutType(finCwInout);
        return rows;
    }

    /**
     * 修改全所收支明细
     * 
     * @param finCwInout 全所收支明细
     * @return 结果
     */
    @Transactional
    @Override
    public int updateCwInout(FinCwInout finCwInout)
    {
        cwInoutMapper.deleteCwInoutTypeById(finCwInout.getId());
        insertCwInoutType(finCwInout);
        return cwInoutMapper.updateCwInout(finCwInout);
    }

    /**
     * 批量删除全所收支明细
     * 
     * @param ids 需要删除的全所收支明细主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCwInoutByIds(String[] ids)
    {
        cwInoutMapper.deleteCwInoutTypeByIds(ids);
        return cwInoutMapper.deleteCwInoutByIds(ids);
    }

    /**
     * 删除全所收支明细信息
     * 
     * @param id 全所收支明细主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCwInoutById(String id)
    {
        cwInoutMapper.deleteCwInoutTypeById(id);
        return cwInoutMapper.deleteCwInoutById(id);
    }

    /**
     * 新增${subTable.functionName}信息
     * 
     * @param finCwInout 全所收支明细对象
     */
    public void insertCwInoutType(FinCwInout finCwInout)
    {
        List<CwInoutType> cwInoutTypeList = finCwInout.getCwInoutTypeList();
        String id = finCwInout.getId();
        if (StringUtils.isNotNull(cwInoutTypeList))
        {
            List<CwInoutType> list = new ArrayList<CwInoutType>();
            for (CwInoutType cwInoutType : cwInoutTypeList)
            {
                cwInoutType.setId(id);
                list.add(cwInoutType);
            }
            if (list.size() > 0)
            {
                cwInoutMapper.batchCwInoutType(list);
            }
        }
    }
}