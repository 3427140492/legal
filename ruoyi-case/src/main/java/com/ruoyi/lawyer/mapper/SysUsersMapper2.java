package com.ruoyi.lawyer.mapper;

import com.ruoyi.lawyer.domain.SysUser2s;

import java.util.List;


/**
 * 用户信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-16
 */
public interface SysUsersMapper2
{

    /**
     * 查询用户信息
     * ----
     * @return 用户信息
     */
    public List<SysUser2s> selectSysUserByHrEmp();

}
