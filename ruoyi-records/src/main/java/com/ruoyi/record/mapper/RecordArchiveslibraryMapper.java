package com.ruoyi.record.mapper;

import java.util.List;
import com.ruoyi.record.domain.RecordArchiveslibrary;
import com.ruoyi.record.domain.RecordCaseLaw;

/**
 * 档案借阅Mapper接口
 * 
 * @author ruoyi
 * @date 2022-10-10
 */
public interface RecordArchiveslibraryMapper 
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
     * 删除档案借阅
     * 
     * @param id 档案借阅主键
     * @return 结果
     */
    int deleteRecordArchiveslibraryById(Integer id);

    /**
     * 批量删除档案借阅
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteRecordArchiveslibraryByIds(Integer[] ids);

    /**
     * 批量删除审批中心
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteCaseLawByIds(Integer[] ids);
    
    /**
     * 批量新增审批中心
     * 
     * @param caseLawList 审批中心列表
     * @return 结果
     */
    int batchCaseLaw(List<RecordCaseLaw> caseLawList);
    

    /**
     * 通过档案借阅主键删除审批中心信息
     * 
     * @param id 档案借阅ID
     * @return 结果
     */
    int deleteCaseLawById(Integer id);

    //修改状态为已归还
    int updateYstatus(Integer id);

    //修改状态为已归还
    int updateNstatus(Integer nid);
}
