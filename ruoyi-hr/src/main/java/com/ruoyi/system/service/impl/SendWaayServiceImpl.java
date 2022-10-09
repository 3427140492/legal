package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SendWaayMapper;
import com.ruoyi.system.domain.SendWaay;
import com.ruoyi.system.service.ISendWaayService;

/**
 * 发送Service业务层处理
 *
 * @author ruoyi
 * @date 2022-10-09
 */
@Service
public class SendWaayServiceImpl implements ISendWaayService
{
    @Autowired
    private SendWaayMapper sendWaayMapper;

    /**
     * 查询发送
     *
     * @param id 发送主键
     * @return 发送
     */
    @Override
    public SendWaay selectSendWaayById(Integer id)
    {
        return sendWaayMapper.selectSendWaayById(id);
    }

    /**
     * 查询发送列表
     *
     * @param sendWaay 发送
     * @return 发送
     */
    @Override
    public List<SendWaay> selectSendWaayList(SendWaay sendWaay)
    {
        return sendWaayMapper.selectSendWaayList(sendWaay);
    }

    /**
     * 新增发送
     *
     * @param sendWaay 发送
     * @return 结果
     */
    @Override
    public int insertSendWaay(SendWaay sendWaay)
    {
        return sendWaayMapper.insertSendWaay(sendWaay);
    }

    /**
     * 修改发送
     *
     * @param sendWaay 发送
     * @return 结果
     */
    @Override
    public int updateSendWaay(SendWaay sendWaay)
    {
        return sendWaayMapper.updateSendWaay(sendWaay);
    }

    /**
     * 批量删除发送
     *
     * @param ids 需要删除的发送主键
     * @return 结果
     */
    @Override
    public int deleteSendWaayByIds(Integer[] ids)
    {
        return sendWaayMapper.deleteSendWaayByIds(ids);
    }

    /**
     * 删除发送信息
     *
     * @param id 发送主键
     * @return 结果
     */
    @Override
    public int deleteSendWaayById(Integer id)
    {
        return sendWaayMapper.deleteSendWaayById(id);
    }
}
