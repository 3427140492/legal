package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.AdmSigninrecor;
import com.ruoyi.system.domain.SystemUser;

/**
 * 签到Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-22
 */
public interface AdmSigninrecorMapper 
{
    /**
     * 查询签到
     * 
     * @param id 签到主键
     * @return 签到
     */
        AdmSigninrecor selectAdmSigninrecorById(Integer id);

    /**
     * 查询签到列表
     * 
     * @param admSigninrecor 签到
     * @return 签到集合
     */
    List<AdmSigninrecor> selectAdmSigninrecorList(AdmSigninrecor admSigninrecor);

    /**
     * 新增签到
     * 
     * @param admSigninrecor 签到
     * @return 结果
     */
    int insertAdmSigninrecor(AdmSigninrecor admSigninrecor);

    /**
     * 修改签到
     * 
     * @param admSigninrecor 签到
     * @return 结果
     */
    int updateAdmSigninrecor(AdmSigninrecor admSigninrecor);

    /**
     * 删除签到
     * 
     * @param id 签到主键
     * @return 结果
     */
    int deleteAdmSigninrecorById(Integer id);

    /**
     * 批量删除签到
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteAdmSigninrecorByIds(Integer[] ids);

    /**
     * 批量删除系统用户
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSystemUserByIds(Integer[] ids);
    
    /**
     * 批量新增系统用户
     * 
     * @param systemUserList 系统用户列表
     * @return 结果
     */
    int batchSystemUser(List<SystemUser> systemUserList);
    

    /**
     * 通过签到主键删除系统用户信息
     * 
     * @param id 签到ID
     * @return 结果
     */
    int deleteSystemUserById(Integer id);
}
