package com.ruoyi.lawyer.service.impl;

import com.ruoyi.lawyer.domain.HrEmps;
import com.ruoyi.lawyer.mapper.HrEmpsMapper;
import com.ruoyi.lawyer.service.IHrEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 通讯录表Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-13
 */
@Service
public class HrEmpServiceImpls implements IHrEmpService {
    @Autowired
    private HrEmpsMapper hrEmpMapper;

    /**
     * 查询通讯录表
     *
     * @param id 通讯录表主键
     * @return 通讯录表
     */
    @Override
    public HrEmps selectHrEmpById(Long id) {
        return hrEmpMapper.selectHrEmpById(id);
    }

}