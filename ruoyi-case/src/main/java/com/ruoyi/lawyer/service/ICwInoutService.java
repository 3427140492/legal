package com.ruoyi.lawyer.service;

import com.ruoyi.lawyer.domain.CwInouts;

/**
 * 收支Service接口
 * 
 * @author ck
 * @date 2022-09-25
 */
public interface ICwInoutService {


    /**
     * 新增收支
     *
     * @param cwInout 收支
     * @return 结果
     */
    public int insertCwInout(CwInouts cwInout);

}