package com.ruoyi.act.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.act.mapper.ApplyMapper;
import com.ruoyi.act.domain.Apply;
import com.ruoyi.act.service.IApplyService;

/**
 * 业务申请Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-21
 */
@Service
public class ApplyServiceImpl implements IApplyService 
{
    @Autowired
    private ApplyMapper applyMapper;

    /**
     * 查询业务申请
     * 
     * @param id 业务申请主键
     * @return 业务申请
     */
    @Override
    public Apply selectApplyById(Integer id)
    {
        return applyMapper.selectApplyById(id);
    }

    /**
     * 查询业务申请列表
     * 
     * @param apply 业务申请
     * @return 业务申请
     */
    @Override
    public List<Apply> selectApplyList(Apply apply)
    {
        return applyMapper.selectApplyList(apply);
    }

    /**
     * 新增业务申请
     * 
     * @param apply 业务申请
     * @return 结果
     */
    @Override
    public int insertApply(Apply apply)
    {
        return applyMapper.insertApply(apply);
    }

    /**
     * 修改业务申请
     * 
     * @param apply 业务申请
     * @return 结果
     */
    @Override
    public int updateApply(Apply apply)
    {
        return applyMapper.updateApply(apply);
    }

    /**
     * 批量删除业务申请
     * 
     * @param ids 需要删除的业务申请主键
     * @return 结果
     */
    @Override
    public int deleteApplyByIds(Integer[] ids)
    {
        return applyMapper.deleteApplyByIds(ids);
    }

    /**
     * 删除业务申请信息
     * 
     * @param id 业务申请主键
     * @return 结果
     */
    @Override
    public int deleteApplyById(Integer id)
    {
        return applyMapper.deleteApplyById(id);
    }
}
