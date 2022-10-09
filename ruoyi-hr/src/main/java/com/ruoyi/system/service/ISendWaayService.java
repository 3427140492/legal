package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SendWaay;

/**
 * 发送Service接口
 *
 * @author ruoyi
 * @date 2022-10-09
 */
public interface ISendWaayService
{
    /**
     * 查询发送
     *
     * @param id 发送主键
     * @return 发送
     */
    SendWaay selectSendWaayById(Integer id);

    /**
     * 查询发送列表
     *
     * @param sendWaay 发送
     * @return 发送集合
     */
    List<SendWaay> selectSendWaayList(SendWaay sendWaay);

    /**
     * 新增发送
     *
     * @param sendWaay 发送
     * @return 结果
     */
    int insertSendWaay(SendWaay sendWaay);

    /**
     * 修改发送
     *
     * @param sendWaay 发送
     * @return 结果
     */
    int updateSendWaay(SendWaay sendWaay);

    /**
     * 批量删除发送
     *
     * @param ids 需要删除的发送主键集合
     * @return 结果
     */
    int deleteSendWaayByIds(Integer[] ids);

    /**
     * 删除发送信息
     *
     * @param id 发送主键
     * @return 结果
     */
    int deleteSendWaayById(Integer id);
}
