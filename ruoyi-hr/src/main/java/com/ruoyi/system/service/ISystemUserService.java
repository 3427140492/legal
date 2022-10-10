package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SystemUser;

/**
 * 系统用户Service接口
 * 
 * @author ruoyi
 * @date 2022-10-10
 */
public interface ISystemUserService 
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
     * 批量删除系统用户
     * 
     * @param ids 需要删除的系统用户主键集合
     * @return 结果
     */
    int deleteSystemUserByIds(String[] ids);

    /**
     * 删除系统用户信息
     * 
     * @param id 系统用户主键
     * @return 结果
     */
    int deleteSystemUserById(Integer id);
}
