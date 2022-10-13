package com.ruoyi.lawyer.service;

import com.ruoyi.lawyer.domain.HrEmps;

/**
 * 通讯录表Service接口
 * 
 * @author ruoyi
 * @date 2022-09-13
 */
public interface IHrEmpService {
    /**
     * 查询通讯录表
     *
     * @param id 通讯录表主键
     * @return 通讯录表
     */
    public HrEmps selectHrEmpById(Long id);

}