package com.ruoyi.lawyer.service.impl;

import com.ruoyi.lawyer.domain.AdmExpressages;
import com.ruoyi.lawyer.mapper.AdmExpressagesMapper;
import com.ruoyi.lawyer.service.IAdmExpressageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 快递业务Service业务层处理
 * 
 * @author ck
 * @date 2022-09-27
 */
@Service
public class AdmExpressageServiceImpls implements IAdmExpressageService {
    @Autowired
    private AdmExpressagesMapper admExpressageMapper;


    /**
     * 新增快递业务
     *
     * @param admExpressage 快递业务
     * @return 结果
     */
    @Override
    public int insertAdmExpressage(AdmExpressages admExpressage) {
        return admExpressageMapper.insertAdmExpressage(admExpressage);
    }

}
