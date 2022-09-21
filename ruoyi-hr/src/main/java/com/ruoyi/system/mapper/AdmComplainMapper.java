package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.AdmComplain;
import com.ruoyi.system.domain.HrClient;

/**
 * 建议投诉Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-21
 */
public interface AdmComplainMapper 
{
    /**
     * 查询建议投诉
     * 
     * @param id 建议投诉主键
     * @return 建议投诉
     */
        AdmComplain selectAdmComplainById(Integer id);

    /**
     * 查询建议投诉列表
     * 
     * @param admComplain 建议投诉
     * @return 建议投诉集合
     */
    List<AdmComplain> selectAdmComplainList(AdmComplain admComplain);

    /**
     * 新增建议投诉
     * 
     * @param admComplain 建议投诉
     * @return 结果
     */
    int insertAdmComplain(AdmComplain admComplain);

    /**
     * 修改建议投诉
     * 
     * @param admComplain 建议投诉
     * @return 结果
     */
    int updateAdmComplain(AdmComplain admComplain);

    /**
     * 删除建议投诉
     * 
     * @param id 建议投诉主键
     * @return 结果
     */
    int deleteAdmComplainById(Integer id);

    /**
     * 批量删除建议投诉
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteAdmComplainByIds(Integer[] ids);

    /**
     * 批量删除客户
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteClientByIds(Integer[] ids);
    
    /**
     * 批量新增客户
     * 
     * @param hrClientList 客户列表
     * @return 结果
     */
    int batchClient(List<HrClient> hrClientList);
    

    /**
     * 通过建议投诉主键删除客户信息
     * 
     * @param id 建议投诉ID
     * @return 结果
     */
    int deleteClientById(Integer id);
}
