package com.ruoyi.act.mapper;

import java.util.List;

import com.ruoyi.act.domain.ActHiComment;
import com.ruoyi.act.domain.Apply;

/**
 * 业务申请Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-21
 */
public interface ApplyMapper 
{
    /**
     * 查询业务申请
     * 
     * @param id 业务申请主键
     * @return 业务申请
     */
    public Apply selectApplyById(Integer id);

    /**
     * 查询业务申请列表
     * 
     * @param apply 业务申请
     * @return 业务申请集合
     */
    public List<Apply> selectApplyList(Apply apply);

    /**
     * 查询业务申请回显表
     *
     * @return 业务申请回显表
     */
//    public List<ActHiComment> selapphxb(ActHiComment actHiComment);

    /**
     * 新增业务申请
     * 
     * @param apply 业务申请
     * @return 结果
     */
    public int insertApply(Apply apply);

    /**
     * 修改业务申请
     * 
     * @param apply 业务申请
     * @return 结果
     */
    public int updateApply(Apply apply);

    /**
     * 删除业务申请
     * 
     * @param id 业务申请主键
     * @return 结果
     */
    public int deleteApplyById(Integer id);

    /**
     * 批量删除业务申请
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteApplyByIds(Integer[] ids);
}
