package com.ruoyi.record.service;

import java.util.List;
import com.ruoyi.record.domain.RecordArchiveslibrary;

/**
 * 档案借阅Service接口
 * 
 * @author ruoyi
 * @date 2022-10-10
 */
public interface IRecordArchiveslibraryService 
{
    /**
     * 查询档案借阅
     * 
     * @param id 档案借阅主键
     * @return 档案借阅
     */
        RecordArchiveslibrary selectRecordArchiveslibraryById(Integer id);

    /**
     * 查询档案借阅列表
     * 
     * @param recordArchiveslibrary 档案借阅
     * @return 档案借阅集合
     */
    List<RecordArchiveslibrary> selectRecordArchiveslibraryList(RecordArchiveslibrary recordArchiveslibrary);

    /**
     * 新增档案借阅
     * 
     * @param recordArchiveslibrary 档案借阅
     * @return 结果
     */
    int insertRecordArchiveslibrary(RecordArchiveslibrary recordArchiveslibrary);

    /**
     * 修改档案借阅
     * 
     * @param recordArchiveslibrary 档案借阅
     * @return 结果
     */
    int updateRecordArchiveslibrary(RecordArchiveslibrary recordArchiveslibrary);

    /**
     * 批量删除档案借阅
     * 
     * @param ids 需要删除的档案借阅主键集合
     * @return 结果
     */
    int deleteRecordArchiveslibraryByIds(Integer[] ids);

    /**
     * 删除档案借阅信息
     * 
     * @param id 档案借阅主键
     * @return 结果
     */
    int deleteRecordArchiveslibraryById(Integer id);

    //修改状态为已归还
    int updateYstatus(Integer id);
}
