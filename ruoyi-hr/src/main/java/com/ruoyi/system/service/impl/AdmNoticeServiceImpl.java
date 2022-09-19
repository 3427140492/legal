package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AdmNoticeMapper;
import com.ruoyi.system.domain.AdmNotice;
import com.ruoyi.system.service.IAdmNoticeService;

/**
 * 公告通知Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-15
 */
@Service
public class AdmNoticeServiceImpl implements IAdmNoticeService 
{
    @Autowired
    private AdmNoticeMapper admNoticeMapper;

    /**
     * 查询公告通知
     * 
     * @param id 公告通知主键
     * @return 公告通知
     */
    @Override
    public AdmNotice selectAdmNoticeById(Long id)
    {
        return admNoticeMapper.selectAdmNoticeById(id);
    }

    /**
     * 查询公告通知列表
     * 
     * @param admNotice 公告通知
     * @return 公告通知
     */
    @Override
    public List<AdmNotice> selectAdmNoticeList(AdmNotice admNotice)
    {
        return admNoticeMapper.selectAdmNoticeList(admNotice);
    }

    /**
     * 新增公告通知
     * 
     * @param admNotice 公告通知
     * @return 结果
     */
    @Override
    public int insertAdmNotice(AdmNotice admNotice)
    {
        return admNoticeMapper.insertAdmNotice(admNotice);
    }

    /**
     * 修改公告通知
     * 
     * @param admNotice 公告通知
     * @return 结果
     */
    @Override
    public int updateAdmNotice(AdmNotice admNotice)
    {
        return admNoticeMapper.updateAdmNotice(admNotice);
    }

    /**
     * 批量删除公告通知
     * 
     * @param ids 需要删除的公告通知主键
     * @return 结果
     */
    @Override
    public int deleteAdmNoticeByIds(Long[] ids)
    {
        return admNoticeMapper.deleteAdmNoticeByIds(ids);
    }

    /**
     * 删除公告通知信息
     * 
     * @param id 公告通知主键
     * @return 结果
     */
    @Override
    public int deleteAdmNoticeById(Long id)
    {
        return admNoticeMapper.deleteAdmNoticeById(id);
    }
}
