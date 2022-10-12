package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.domain.SendWaay;
import com.ruoyi.system.mapper.AdmExpressageMapper;
import com.ruoyi.system.domain.AdmExpressage;
import com.ruoyi.system.service.IAdmExpressageService;

/**
 * 快速登记Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-23
 */
@Service
public class AdmExpressageServiceImpl implements IAdmExpressageService 
{
    @Autowired
    private AdmExpressageMapper admExpressageMapper;

    /**
     * 查询快速登记
     * 
     * @param id 快速登记主键
     * @return 快速登记
     */
    @Override
    public AdmExpressage selectAdmExpressageById(Integer id)
    {
        return admExpressageMapper.selectAdmExpressageById(id);
    }

    /**
     * 查询快速登记列表
     * 
     * @param admExpressage 快速登记
     * @return 快速登记
     * 收件管理
     */
    @Override
    public List<AdmExpressage> selectAdmExpressageList(AdmExpressage admExpressage)
    {
        return admExpressageMapper.selectAdmExpressageList(admExpressage);
    }

    /**
     * 查询快速登记列表
     *
     * @param admExpressage 快速登记
     * @return 快速登记
     * 发件管理
     */
    @Override
    public List<AdmExpressage> selectAdmExpressageListS(AdmExpressage admExpressage)
    {
        return admExpressageMapper.selectAdmExpressageListS(admExpressage);
    }

    /**
     * 新增快速登记
     * 
     * @param admExpressage 快速登记
     * @return 结果
     */
    @Transactional
    @Override
    public int insertAdmExpressage(AdmExpressage admExpressage)
    {
        int rows = admExpressageMapper.insertAdmExpressage(admExpressage);
        insertSendWaay(admExpressage);
        return rows;
    }

    /**
     * 修改快速登记
     * 
     * @param admExpressage 快速登记
     * @return 结果
     */
    @Transactional
    @Override
    public int updateAdmExpressage(AdmExpressage admExpressage)
    {
//        admExpressageMapper.deleteSendWaayById(admExpressage.getId());
//        insertSendWaay(admExpressage);
        return admExpressageMapper.updateAdmExpressage(admExpressage);
    }

    /**
     * 批量删除快速登记
     * 
     * @param ids 需要删除的快速登记主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteAdmExpressageByIds(Integer[] ids)
    {
        admExpressageMapper.deleteSendWaayByIds(ids);
        return admExpressageMapper.deleteAdmExpressageByIds(ids);
    }

    /**
     * 删除快速登记信息
     * 
     * @param id 快速登记主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteAdmExpressageById(Integer id)
    {
        admExpressageMapper.deleteSendWaayById(id);
        return admExpressageMapper.deleteAdmExpressageById(id);
    }

    /**
     * 新增发送信息
     * 
     * @param admExpressage 快速登记对象
     */
    public void insertSendWaay(AdmExpressage admExpressage)
    {
        List<SendWaay> sendWaayList = admExpressage.getSendWaayList();
        Integer id = admExpressage.getId();
        if (StringUtils.isNotNull(sendWaayList))
        {
            List<SendWaay> list = new ArrayList<SendWaay>();
            for (SendWaay sendWaay : sendWaayList)
            {
                sendWaay.setId(id);
                list.add(sendWaay);
            }
            if (list.size() > 0)
            {
                admExpressageMapper.batchSendWaay(list);
            }
        }
    }
}
