package com.ruoyi.lawyer.service;

import com.ruoyi.lawyer.domain.AdmExpressages;
import com.ruoyi.lawyer.domain.CwInvoices;

/**
 * 发票Service接口
 * 
 * @author ck
 * @date 2022-09-27
 */
public interface ICwInvoiceService 
{

    /**
     * 新增发票
     * 
     * @param cwInvoice 发票
     * @return 结果
     */
    public int insertCwInvoice(CwInvoices cwInvoice, AdmExpressages admExpressage);


}
