package com.ruoyi.lawyer.service.impl;

import java.util.List;
import com.ruoyi.lawyer.domain.SysUser2s;
import com.ruoyi.lawyer.mapper.SysUsersMapper2;
import com.ruoyi.lawyer.service.ISysUserService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 用户信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-16
 */
@Service
public class SysUserServiceImpl2s implements ISysUserService2
{
    @Autowired
    private SysUsersMapper2 sysUserMapper;

    /**
     * 查询用户信息
     * ----
     * @return 用户信息
     */
    @Override
    public List<SysUser2s> selectSysUserByHrEmp()
    {
        return sysUserMapper.selectSysUserByHrEmp();
    }
}
