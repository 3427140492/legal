package com.ruoyi.record.mapper;

import java.util.List;
import com.ruoyi.record.domain.RecordCaseLaw;

/**
 * 归档Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-15
 */
public interface RecordCaseLawMapper {


    /**
     * 查询归档列表
     *
     * @param caseLaw 归档
     * @return 归档集合
     */
    public List<RecordCaseLaw> selectCaseLawList(RecordCaseLaw caseLaw);


}
