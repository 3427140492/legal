package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.AdmNotice;

/**
 * 公告通知Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-15
 */
public interface AdmNoticeMapper 
{
    /**
     * 查询公告通知
     * 
     * @param id 公告通知主键
     * @return 公告通知
     */
    public AdmNotice selectAdmNoticeById(Long id);

    /**
     * 查询公告通知列表
     * 
     * @param admNotice 公告通知
     * @return 公告通知集合
     */
    public List<AdmNotice> selectAdmNoticeList(AdmNotice admNotice);

    /**
     * 新增公告通知
     * 
     * @param admNotice 公告通知
     * @return 结果
     */
    public int insertAdmNotice(AdmNotice admNotice);

    /**
     * 修改公告通知
     * 
     * @param admNotice 公告通知
     * @return 结果
     */
    public int updateAdmNotice(AdmNotice admNotice);

    /**
     * 删除公告通知
     * 
     * @param id 公告通知主键
     * @return 结果
     */
    public int deleteAdmNoticeById(Long id);

    /**
     * 批量删除公告通知
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAdmNoticeByIds(Long[] ids);
}
