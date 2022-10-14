package com.ruoyi.lawyer.mapper;

import com.ruoyi.lawyer.domain.Applys;

/**
 * 操作记录Mapper接口
 * 
 * @author ruoyi
 * @date 2022-10-09
 */
public interface ApplysMapper
{

    /**
     * 新增操作记录
     * 
     * @param apply 操作记录
     * @return 结果
     */
    public int insertApply(Applys apply);

    /**
     * 删除操作记录
     * 
     * @param id 操作记录主键
     * @return 结果
     */
    public int deleteApplyById(String id);

}
