package com.ruoyi.system.service.impl;

import com.ruoyi.system.domain.ExpressType;
import com.ruoyi.system.mapper.ExpressTypeMapper;
import com.ruoyi.system.service.IExpressTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ExpressTypeServiceImpl implements IExpressTypeService
{
    @Autowired
    private ExpressTypeMapper mapper;


    @Override
    public List<ExpressType> selectExpressName()
    {
        return mapper.selectExpressName();
    }

}
