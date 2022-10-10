package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SystemUser;
import com.ruoyi.system.domain.SystemUserRole;

/**
 * 系统用户Mapper接口
 * 
 * @author ruoyi
 * @date 2022-10-10
 */
public interface SystemUserMapper 
{
    /**
     * 查询系统用户
     * 
     * @param id 系统用户主键
     * @return 系统用户
     */
        SystemUser selectSystemUserById(Integer id);

    /**
     * 查询系统用户列表
     * 
     * @param systemUser 系统用户
     * @return 系统用户集合
     */
    List<SystemUser> selectSystemUserList(SystemUser systemUser);

    /**
     * 新增系统用户
     * 
     * @param systemUser 系统用户
     * @return 结果
     */
    int insertSystemUser(SystemUser systemUser);

    /**
     * 修改系统用户
     * 
     * @param systemUser 系统用户
     * @return 结果
     */
    int updateSystemUser(SystemUser systemUser);

    /**
     * 删除系统用户
     * 
     * @param id 系统用户主键
     * @return 结果
     */
    int deleteSystemUserById(Integer id);

    /**
     * 批量删除系统用户
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSystemUserByIds(String[] ids);

    /**
     * 批量删除用户角色
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSystemUserRoleBySystemUserIds(String[] ids);
    
    /**
     * 批量新增用户角色
     * 
     * @param systemUserRoleList 用户角色列表
     * @return 结果
     */
    int batchSystemUserRole(List<SystemUserRole> systemUserRoleList);
    

    /**
     * 通过系统用户主键删除用户角色信息
     * 
     * @param id 系统用户ID
     * @return 结果
     */
    int deleteSystemUserRoleBySystemUserId(Integer id);
}
