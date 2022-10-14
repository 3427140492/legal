package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.AdmExpressage;
import com.ruoyi.system.domain.SendWaay;

/**
 * 快速登记Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-23
 */
public interface AdmExpressageMapper 
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
     * 收件管理
     */
    int insertAdmExpressage(AdmExpressage admExpressage);

    /**
     * 新增快速登记
     *
     * @param admExpressage 快速登记
     * @return 结果
     * 发件管理
     */
    int FinsertAdmExpressage(AdmExpressage admExpressage);

    /**
     * 修改快速登记
     * 
     * @param admExpressage 快速登记
     * @return 结果
     */
    int updateAdmExpressage(AdmExpressage admExpressage);

    /**
     * 删除快速登记
     * 
     * @param id 快速登记主键
     * @return 结果
     */
    int deleteAdmExpressageById(Integer id);

    /**
     * 批量删除快速登记
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteAdmExpressageByIds(Integer[] ids);

    /**
     * 批量删除发送
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteSendWaayByIds(Integer[] ids);
    
    /**
     * 批量新增发送
     * 
     * @param sendWaayList 发送列表
     * @return 结果
     */
    int batchSendWaay(List<SendWaay> sendWaayList);
    

    /**
     * 通过快速登记主键删除发送信息
     * 
     * @param id 快速登记ID
     * @return 结果
     */
    int deleteSendWaayById(Integer id);
}
