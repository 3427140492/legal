package com.ruoyi.lawyer.service.impl;

import com.ruoyi.lawyer.domain.AdmExpressages;
import com.ruoyi.lawyer.domain.CwInvoiceExpressages;
import com.ruoyi.lawyer.mapper.AdmExpressagesMapper;
import com.ruoyi.lawyer.mapper.CwInvoiceExpressagesMapper;
import com.ruoyi.lawyer.domain.CwInvoices;
import com.ruoyi.lawyer.mapper.CwInvoicesMapper;
import com.ruoyi.lawyer.service.ICwInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 发票Service业务层处理
 * 
 * @author ck
 * @date 2022-09-27
 */
@Service
public class CwInvoiceServiceImpls implements ICwInvoiceService {
    @Autowired
    private CwInvoicesMapper cwInvoiceMapper;

    @Autowired
    private AdmExpressagesMapper admExpressageMapper;

    @Autowired
    private CwInvoiceExpressagesMapper cwInvoiceExpressageMapper;

    /**
     * 新增发票
     *
     * @param cwInvoice 发票
     * @return 结果
     */
    @Override
    public int insertCwInvoice(CwInvoices cwInvoice, AdmExpressages admExpressage) {
        int i = cwInvoiceMapper.insertCwInvoice(cwInvoice);
        int i2 = 0;
        if (i > 0){
            int i1 = admExpressageMapper.insertAdmExpressage(admExpressage);
            if (i1 > 0){
                CwInvoiceExpressages cwInvoiceExpressage = new CwInvoiceExpressages();
                cwInvoiceExpressage.setCwInvoiceId(Long.valueOf(cwInvoice.getId()));
                cwInvoiceExpressage.setAdmExpressageId(Long.valueOf(admExpressage.getId()));
                System.out.println(cwInvoiceExpressage.toString());
                i2 = cwInvoiceExpressageMapper.insertCwInvoiceExpressage(cwInvoiceExpressage);
            }
        }
        return i2;
    }

}