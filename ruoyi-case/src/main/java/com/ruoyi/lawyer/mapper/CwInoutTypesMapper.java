package com.ruoyi.lawyer.mapper;

import java.util.List;
import com.ruoyi.lawyer.domain.CwInoutTypes;

/**
 * 收支类别Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-24
 */
public interface CwInoutTypesMapper {
    /**
     * 查询收支类别
     *
     * @param id 收支类别主键
     * @return 收支类别
     */
    public List<CwInoutTypes> selectCwInoutTypeById(String id);

    /**
     * 查询收支类别列表
     *
     * @param cwInoutType 收支类别
     * @return 收支类别集合
     */
    public List<CwInoutTypes> selectCwInoutTypeList(CwInoutTypes cwInoutType);

}