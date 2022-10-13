package com.ruoyi.lawyer.service.impl;

import com.ruoyi.lawyer.domain.CwInvoiceExpressages;
import com.ruoyi.lawyer.mapper.CwInvoiceExpressagesMapper;
import com.ruoyi.lawyer.service.ICwInvoiceExpressageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 发票-快递业务中间Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-28
 */
@Service
public class CwInvoiceExpressageServiceImpls implements ICwInvoiceExpressageService {
    @Autowired
    private CwInvoiceExpressagesMapper cwInvoiceExpressageMapper;


    /**
     * 新增发票-快递业务中间
     *
     * @param cwInvoiceExpressage 发票-快递业务中间
     * @return 结果
     */
    @Override
    public int insertCwInvoiceExpressage(CwInvoiceExpressages cwInvoiceExpressage) {
        return cwInvoiceExpressageMapper.insertCwInvoiceExpressage(cwInvoiceExpressage);
    }

}