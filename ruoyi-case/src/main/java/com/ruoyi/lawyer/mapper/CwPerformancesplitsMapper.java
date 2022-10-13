package com.ruoyi.lawyer.mapper;

import java.util.List;
import com.ruoyi.lawyer.domain.CwPerformancesplits;

/**
 * 律师业绩Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-29
 */
public interface CwPerformancesplitsMapper
{

    /**
     * 查询律师业绩
     *
     * @param id 律师业绩主键
     * @return 律师业绩
     */
    public List<CwPerformancesplits> selectCwPerformancesplitById(String id);



    /**
     * 新增律师业绩
     * 
     * @param list 律师业绩
     * @return 结果
     */
    public int insertCwPerformancesplit(List<CwPerformancesplits> list);


    /**
     * 删除律师业绩
     *
     * @param id 律师业绩主键
     * @return 结果
     */
    public int deleteCwPerformancesplitById(String id);
}
