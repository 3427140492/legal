package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.domain.SystemUserRole;
import com.ruoyi.system.mapper.SystemUserMapper;
import com.ruoyi.system.domain.SystemUser;
import com.ruoyi.system.service.ISystemUserService;

/**
 * 系统用户Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-10
 */
@Service
public class SystemUserServiceImpl implements ISystemUserService 
{
    @Autowired
    private SystemUserMapper systemUserMapper;

    /**
     * 查询系统用户
     * 
     * @param id 系统用户主键
     * @return 系统用户
     */
    @Override
    public SystemUser selectSystemUserById(Integer id)
    {
        return systemUserMapper.selectSystemUserById(id);
    }

    /**
     * 查询系统用户列表
     * 
     * @param systemUser 系统用户
     * @return 系统用户
     */
    @Override
    public List<SystemUser> selectSystemUserList(SystemUser systemUser)
    {
        return systemUserMapper.selectSystemUserList(systemUser);
    }

    /**
     * 新增系统用户
     * 
     * @param systemUser 系统用户
     * @return 结果
     */
    @Transactional
    @Override
    public int insertSystemUser(SystemUser systemUser)
    {
        int rows = systemUserMapper.insertSystemUser(systemUser);
        insertSystemUserRole(systemUser);
        return rows;
    }

    /**
     * 修改系统用户
     * 
     * @param systemUser 系统用户
     * @return 结果
     */
    @Transactional
    @Override
    public int updateSystemUser(SystemUser systemUser)
    {
        systemUserMapper.deleteSystemUserRoleBySystemUserId(systemUser.getId());
        insertSystemUserRole(systemUser);
        return systemUserMapper.updateSystemUser(systemUser);
    }

    /**
     * 批量删除系统用户
     * 
     * @param ids 需要删除的系统用户主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSystemUserByIds(String[] ids)
    {
        systemUserMapper.deleteSystemUserRoleBySystemUserIds(ids);
        return systemUserMapper.deleteSystemUserByIds(ids);
    }

    /**
     * 删除系统用户信息
     * 
     * @param id 系统用户主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSystemUserById(Integer id)
    {
        systemUserMapper.deleteSystemUserRoleBySystemUserId(id);
        return systemUserMapper.deleteSystemUserById(id);
    }

    /**
     * 新增用户角色信息
     * 
     * @param systemUser 系统用户对象
     */
    public void insertSystemUserRole(SystemUser systemUser)
    {
        List<SystemUserRole> systemUserRoleList = systemUser.getSystemUserRoleList();
        Integer id = systemUser.getId();
        if (StringUtils.isNotNull(systemUserRoleList))
        {
            List<SystemUserRole> list = new ArrayList<SystemUserRole>();
            for (SystemUserRole systemUserRole : systemUserRoleList)
            {
                systemUserRole.setSystemUserId(id);
                list.add(systemUserRole);
            }
            if (list.size() > 0)
            {
                systemUserMapper.batchSystemUserRole(list);
            }
        }
    }
}
