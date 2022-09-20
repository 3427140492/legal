package com.ruoyi.record.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.record.mapper.RecordMapper;
import com.ruoyi.record.domain.RecordCaseLaw;
import com.ruoyi.record.service.IRecordService;

/**
 * 归档Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-15
 */
@Service
public class RecordServiceImpl implements IRecordService
{
    @Autowired
    private RecordMapper recordMapper;

    /**
     * 查询归档列表
     * 
     * @param caseLaw 归档
     * @return 归档
     */
    @Override
    public List<RecordCaseLaw> selectCaseLawList(RecordCaseLaw caseLaw)
    {
        return recordMapper.selectCaseLawList(caseLaw);
    }
}
