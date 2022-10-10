package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.AdmExpressage;

/**
 * 快速登记Service接口
 * 
 * @author ruoyi
 * @date 2022-09-23
 */
public interface IAdmExpressageService 
{
    /**
     * 查询快速登记
     * 
     * @param id 快速登记主键
     * @return 快速登记
     */
        AdmExpressage selectAdmExpressageById(Integer id);

    /**
     * 查询快速登记列表
     * 
     * @param admExpressage 快速登记
     * @return 快速登记集合
     * 收件管理
     */
    List<AdmExpressage> selectAdmExpressageList(AdmExpressage admExpressage);

    /**
     * 查询快速登记列表
     *
     * @param admExpressage 快速登记
     * @return 快速登记集合
     * 发件管理
     */
    List<AdmExpressage> selectAdmExpressageListS(AdmExpressage admExpressage);

    /**
     * 新增快速登记
     * 
     * @param admExpressage 快速登记
     * @return 结果
     */
    int insertAdmExpressage(AdmExpressage admExpressage);

    /**
     * 修改快速登记
     * 
     * @param admExpressage 快速登记
     * @return 结果
     */
    int updateAdmExpressage(AdmExpressage admExpressage);

    /**
     * 批量删除快速登记
     * 
     * @param ids 需要删除的快速登记主键集合
     * @return 结果
     */
    int deleteAdmExpressageByIds(Integer[] ids);

    /**
     * 删除快速登记信息
     * 
     * @param id 快速登记主键
     * @return 结果
     */
    int deleteAdmExpressageById(Integer id);
}
