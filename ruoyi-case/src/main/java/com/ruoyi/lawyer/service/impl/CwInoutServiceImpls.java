package com.ruoyi.lawyer.service.impl;

import com.ruoyi.lawyer.domain.CwInouts;
import com.ruoyi.lawyer.service.ICwInoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lawyer.mapper.CwInoutsMapper;

/**
 * 收支Service业务层处理
 * 
 * @author ck
 * @date 2022-09-25
 */
@Service
public class CwInoutServiceImpls implements ICwInoutService {

    @Autowired
    private CwInoutsMapper cwInoutMapper;

    /**
     * 新增收支
     *
     * @param cwInout 收支
     * @return 结果
     */
    @Override
    public int insertCwInout(CwInouts cwInout) {
        return cwInoutMapper.insertCwInout(cwInout);
    }

}
