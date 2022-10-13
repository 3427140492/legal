package com.ruoyi.lawyer.service;

import com.ruoyi.lawyer.domain.CwInvoiceExpressages;

/**
 * 发票-快递业务中间Service接口
 * 
 * @author ruoyi
 * @date 2022-09-28
 */
public interface ICwInvoiceExpressageService {

    /**
     * 新增发票-快递业务中间
     *
     * @param cwInvoiceExpressage 发票-快递业务中间
     * @return 结果
     */
    public int insertCwInvoiceExpressage(CwInvoiceExpressages cwInvoiceExpressage);

}