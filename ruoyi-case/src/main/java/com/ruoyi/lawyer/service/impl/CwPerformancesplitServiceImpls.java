package com.ruoyi.lawyer.service.impl;

import java.util.List;

import com.ruoyi.lawyer.domain.CwPerformancesplits;
import com.ruoyi.lawyer.mapper.CwPerformancesplitsMapper;
import com.ruoyi.lawyer.service.ICwPerformancesplitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 律师业绩Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-29
 */
@Service
public class CwPerformancesplitServiceImpls implements ICwPerformancesplitService {

    @Autowired
    private CwPerformancesplitsMapper cwPerformancesplitMapper;

    /**
     * 查询律师业绩
     *
     * @param id 律师业绩主键
     * @return 律师业绩
     */
    @Override
    public List<CwPerformancesplits> selectCwPerformancesplitById(String id)
    {
        return cwPerformancesplitMapper.selectCwPerformancesplitById(id);
    }

    /**
     * 新增律师业绩
     *
     * @param list 律师业绩
     * @return 结果
     */
    @Override
    public int insertCwPerformancesplit(List<CwPerformancesplits> list) {
        return cwPerformancesplitMapper.insertCwPerformancesplit(list);
    }


    /**
     * 删除律师业绩信息
     *
     * @param id 律师业绩主键
     * @return 结果
     */
    @Override
    public int deleteCwPerformancesplitById(String id)
    {
        return cwPerformancesplitMapper.deleteCwPerformancesplitById(id);
    }

}
