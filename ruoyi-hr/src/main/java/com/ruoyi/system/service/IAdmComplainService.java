package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.AdmComplain;

/**
 * 建议投诉Service接口
 * 
 * @author ruoyi
 * @date 2022-09-21
 */
public interface IAdmComplainService 
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
     * 批量删除建议投诉
     * 
     * @param ids 需要删除的建议投诉主键集合
     * @return 结果
     */
    int deleteAdmComplainByIds(Integer[] ids);

    /**
     * 删除建议投诉信息
     * 
     * @param id 建议投诉主键
     * @return 结果
     */
    int deleteAdmComplainById(Integer id);
}
