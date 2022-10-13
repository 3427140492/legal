package com.ruoyi.lawyer.mapper;

import com.ruoyi.lawyer.domain.CwInouts;

/**
 * 收支Mapper接口
 * 
 * @author ck
 * @date 2022-09-25
 */
public interface CwInoutsMapper {

    /**
     * 新增收支
     *
     * @param cwInout 收支
     * @return 结果
     */
    public int insertCwInout(CwInouts cwInout);

}