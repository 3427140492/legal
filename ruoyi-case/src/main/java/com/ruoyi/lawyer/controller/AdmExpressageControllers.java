package com.ruoyi.lawyer.controller;

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
import com.ruoyi.lawyer.domain.AdmExpressages;
import com.ruoyi.lawyer.service.IAdmExpressageService;

/**
 * 快递业务Controller
 * 
 * @author ck
 * @date 2022-09-27
 */
@RestController
@RequestMapping("/lawyer/expressage")
public class AdmExpressageControllers extends BaseController {
    @Autowired
    private IAdmExpressageService admExpressageService;


    /**
     * 新增快递业务
     *
     * @param admExpressage 快递业务
     * @return 结果
     */
    @PreAuthorize("@ss.hasPermi('lawyer:expressage:add')")
    @Log(title = "快递业务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdmExpressages admExpressage) {
        return toAjax(admExpressageService.insertAdmExpressage(admExpressage));
    }

}