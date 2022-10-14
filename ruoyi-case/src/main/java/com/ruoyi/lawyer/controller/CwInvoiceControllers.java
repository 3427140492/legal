package com.ruoyi.lawyer.controller;

import com.ruoyi.lawyer.domain.AdmExpressages;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.lawyer.domain.CwInvoices;
import com.ruoyi.lawyer.service.ICwInvoiceService;

/**
 * 发票Controller
 * 
 * @author ck
 * @date 2022-09-27
 */
@RestController
@RequestMapping("/lawyer/invoice")
public class CwInvoiceControllers extends BaseController {
    @Autowired
    private ICwInvoiceService cwInvoiceService;


    /**
     * 新增发票
     *
     * @param cwInvoice 发票
     * @return 结果
     */
    @PreAuthorize("@ss.hasPermi('lawyer:invoice:add')")
    @Log(title = "发票", businessType = BusinessType.INSERT)
    @PostMapping("/addInvoice")
    public AjaxResult addInvoice(@RequestBody CwInvoices cwInvoice, @RequestBody AdmExpressages admExpressage) {
        System.out.println(cwInvoice.toString());
        System.out.println(admExpressage.toString());
        return toAjax(cwInvoiceService.insertCwInvoice(cwInvoice,admExpressage));
    }

}
