package com.ruoyi.record.service.impl;

import java.util.List;

import com.ruoyi.record.domain.RecordCaseLaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.record.mapper.RecordArchiveslibraryMapper;
import com.ruoyi.record.domain.RecordArchiveslibrary;
import com.ruoyi.record.service.IRecordArchiveslibraryService;

/**
 * 档案借阅Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-10
 */
@Service
public class RecordArchiveslibraryServiceImpl implements IRecordArchiveslibraryService 
{
    @Autowired
    private RecordArchiveslibraryMapper recordArchiveslibraryMapper;

    /**
     * 查询档案借阅
     * 
     * @param id 档案借阅主键
     * @return 档案借阅
     */
    @Override
    public RecordArchiveslibrary selectRecordArchiveslibraryById(Integer id)
    {
        return recordArchiveslibraryMapper.selectRecordArchiveslibraryById(id);
    }

    /**
     * 查询档案借阅列表
     * 
     * @param recordArchiveslibrary 档案借阅
     * @return 档案借阅
     */
    @Override
    public List<RecordArchiveslibrary> selectRecordArchiveslibraryList(RecordArchiveslibrary recordArchiveslibrary)
    {
        return recordArchiveslibraryMapper.selectRecordArchiveslibraryList(recordArchiveslibrary);
    }

    /**
     * 新增档案借阅
     * 
     * @param recordArchiveslibrary 档案借阅
     * @return 结果
     */
    @Transactional
    @Override
    public int insertRecordArchiveslibrary(RecordArchiveslibrary recordArchiveslibrary)
    {
        int rows = recordArchiveslibraryMapper.insertRecordArchiveslibrary(recordArchiveslibrary);
        insertCaseLaw(recordArchiveslibrary);
        return rows;
    }

    /**
     * 修改档案借阅
     * 
     * @param recordArchiveslibrary 档案借阅
     * @return 结果
     */
    @Transactional
    @Override
    public int updateRecordArchiveslibrary(RecordArchiveslibrary recordArchiveslibrary)
    {
        recordArchiveslibraryMapper.deleteCaseLawById(recordArchiveslibrary.getId());
        insertCaseLaw(recordArchiveslibrary);
        return recordArchiveslibraryMapper.updateRecordArchiveslibrary(recordArchiveslibrary);
    }

    /**
     * 批量删除档案借阅
     * 
     * @param ids 需要删除的档案借阅主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteRecordArchiveslibraryByIds(Integer[] ids)
    {
        recordArchiveslibraryMapper.deleteCaseLawByIds(ids);
        return recordArchiveslibraryMapper.deleteRecordArchiveslibraryByIds(ids);
    }

    /**
     * 删除档案借阅信息
     * 
     * @param id 档案借阅主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteRecordArchiveslibraryById(Integer id)
    {
        recordArchiveslibraryMapper.deleteCaseLawById(id);
        return recordArchiveslibraryMapper.deleteRecordArchiveslibraryById(id);
    }

    //修改状态为已归还
    @Override
    public int updateYstatus(Integer id) {
        return recordArchiveslibraryMapper.updateYstatus(id);
    }

    //修改状态为未归还
    @Override
    public int updateNstatus(Integer nid) {
        return recordArchiveslibraryMapper.updateNstatus(nid);
    }

    /**
     * 新增审批中心信息
     * 
     * @param recordArchiveslibrary 档案借阅对象
     */
    public void insertCaseLaw(RecordArchiveslibrary recordArchiveslibrary)
    {
        List<RecordCaseLaw> caseLawList = recordArchiveslibrary.getCaseLawList();
        Integer id = recordArchiveslibrary.getId();
        if (StringUtils.isNotNull(caseLawList))
        {
            List<RecordCaseLaw> list = new ArrayList<RecordCaseLaw>();
            for (RecordCaseLaw caseLaw : caseLawList)
            {
                caseLaw.setId(id);
                list.add(caseLaw);
            }
            if (list.size() > 0)
            {
                recordArchiveslibraryMapper.batchCaseLaw(list);
            }
        }
    }
}
