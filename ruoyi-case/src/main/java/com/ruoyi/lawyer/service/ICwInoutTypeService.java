package com.ruoyi.lawyer.service;

import java.util.List;
import com.ruoyi.lawyer.domain.CwInoutTypes;

/**
 * 收支类别Service接口
 * 
 * @author ruoyi
 * @date 2022-09-24
 */
public interface ICwInoutTypeService 
{
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
    public Object selectCwInoutTypeList(CwInoutTypes cwInoutType);

}
