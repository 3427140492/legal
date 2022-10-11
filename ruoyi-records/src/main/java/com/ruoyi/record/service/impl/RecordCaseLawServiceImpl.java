package com.ruoyi.record.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.record.mapper.RecordCaseLawMapper;
import com.ruoyi.record.domain.RecordCaseLaw;
import com.ruoyi.record.service.IRecordCaseLawService;

/**
 * 归档Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-15
 */
@Service
public class RecordCaseLawServiceImpl implements IRecordCaseLawService
{
    @Autowired
    private RecordCaseLawMapper caseLawMapper;

    /**
     * 查询归档列表
     * 
     * @param caseLaw 归档
     * @return 归档
     */
    @Override
    public List<RecordCaseLaw> selectCaseLawList(RecordCaseLaw caseLaw)
    {
        return caseLawMapper.selectCaseLawList(caseLaw);
    }

    @Override
    public List<RecordCaseLaw> selectname(RecordCaseLaw caseLaw) {
        return caseLawMapper.selectname(caseLaw);
    }
}
