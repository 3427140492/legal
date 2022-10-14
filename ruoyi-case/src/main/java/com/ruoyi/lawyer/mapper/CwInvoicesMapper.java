package com.ruoyi.lawyer.mapper;

import com.ruoyi.lawyer.domain.CwInvoices;

/**
 * 发票Mapper接口
 * 
 * @author ck
 * @date 2022-09-27
 */
public interface CwInvoicesMapper {

    /**
     * 新增发票
     *
     * @param cwInvoice 发票
     * @return 结果
     */
    public int insertCwInvoice(CwInvoices cwInvoice);
}