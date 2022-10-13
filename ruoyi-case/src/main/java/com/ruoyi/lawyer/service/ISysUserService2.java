package com.ruoyi.lawyer.service;

import com.ruoyi.lawyer.domain.SysUser2s;

import java.util.List;


/**
 * 用户信息Service接口
 * 
 * @author ruoyi
 * @date 2022-09-16
 */
public interface ISysUserService2
{
    /**
     * 查询用户信息
     * ----
     * @return 用户信息
     */
    public List<SysUser2s> selectSysUserByHrEmp();
}
