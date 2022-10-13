package com.ruoyi.lawyer.mapper;

import com.ruoyi.lawyer.domain.HrEmps;

/**
 * 通讯录表Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-13
 */
public interface HrEmpsMapper {

    /**
     * 查询通讯录表
     *
     * @param id 通讯录表主键
     * @return 通讯录表
     */
    public HrEmps selectHrEmpById(Long id);

}