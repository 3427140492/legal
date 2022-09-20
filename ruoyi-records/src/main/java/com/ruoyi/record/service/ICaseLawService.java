package com.ruoyi.record.service;

import java.util.List;
import com.ruoyi.record.domain.CaseLaw;

/**
 * 归档Service接口
 * 
 * @author ruoyi
 * @date 2022-09-15
 */
public interface ICaseLawService 
{
    /**
     * 查询归档列表
     * 
     * @param caseLaw 归档
     * @return 归档集合
     */
    List<CaseLaw> selectCaseLawList(CaseLaw caseLaw);
}
