package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.AdmSigninrecor;

/**
 * 签到Service接口
 * 
 * @author ruoyi
 * @date 2022-09-22
 */
public interface IAdmSigninrecorService 
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
     * 批量删除签到
     * 
     * @param ids 需要删除的签到主键集合
     * @return 结果
     */
    int deleteAdmSigninrecorByIds(Integer[] ids);

    /**
     * 删除签到信息
     * 
     * @param id 签到主键
     * @return 结果
     */
    int deleteAdmSigninrecorById(Integer id);
}
