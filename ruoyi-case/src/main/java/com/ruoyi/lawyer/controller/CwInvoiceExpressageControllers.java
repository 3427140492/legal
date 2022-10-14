package com.ruoyi.lawyer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.lawyer.service.ICwInvoiceExpressageService;

/**
 * 发票-快递业务中间Controller
 * 
 * @author ruoyi
 * @date 2022-09-28
 */
@RestController
@RequestMapping("/lawyer/CwInvoiceExpressage")
public class CwInvoiceExpressageControllers extends BaseController {
    @Autowired
    private ICwInvoiceExpressageService cwInvoiceExpressageService;


//    /**
//     * 新增发票-快递业务中间
//     */
//    @PreAuthorize("@ss.hasPermi('lawyer:CwInvoiceExpressage:add')")
//    @Log(title = "发票-快递业务中间", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody CwInvoiceExpressage cwInvoiceExpressage) {
//        return toAjax(cwInvoiceExpressageService.insertCwInvoiceExpressage(cwInvoiceExpressage));
//    }

}