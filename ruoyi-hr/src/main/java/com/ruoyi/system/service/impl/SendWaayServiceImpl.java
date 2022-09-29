package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.AdmExpressage;
import com.ruoyi.system.domain.SendWaay;
import com.ruoyi.system.mapper.AdmExpressageMapper;
import com.ruoyi.system.mapper.SendWaayMapper;
import com.ruoyi.system.service.IAdmExpressageService;
import com.ruoyi.system.service.ISendWaayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class SendWaayServiceImpl implements ISendWaayService
{
    @Autowired
    private SendWaayMapper mapper;


    @Override
    public List<SendWaay> selectSendName()
    {
        return mapper.selectSendName();
    }

}
