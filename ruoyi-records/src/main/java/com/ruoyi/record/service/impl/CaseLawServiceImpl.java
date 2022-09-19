package com.ruoyi.record.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.record.mapper.CaseLawMapper;
import com.ruoyi.record.domain.CaseLaw;
import com.ruoyi.record.service.ICaseLawService;

/**
 * 归档Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-15
 */
@Service
public class CaseLawServiceImpl implements ICaseLawService
{
    @Autowired
    private CaseLawMapper caseLawMapper;

    /**
     * 查询归档列表
     * 
     * @param caseLaw 归档
     * @return 归档
     */
    @Override
    public List<CaseLaw> selectCaseLawList(CaseLaw caseLaw)
    {
        return caseLawMapper.selectCaseLawList(caseLaw);
    }
}
