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
import com.ruoyi.lawyer.domain.CwInouts;
import com.ruoyi.lawyer.service.ICwInoutService;

/**
 * 收支Controller
 * 
 * @author ck
 * @date 2022-09-25
 */
@RestController
@RequestMapping("/lawyer/cwinout")
public class CwInoutControllers extends BaseController {

    @Autowired
    private ICwInoutService cwInoutService;

    /**
     * 新增收支
     */
    @PreAuthorize("@ss.hasPermi('system:收支:add')")
    @Log(title = "收支", businessType = BusinessType.INSERT)
    @PostMapping("/addInout")
    public AjaxResult add(@RequestBody CwInouts cwInout) {
        return toAjax(cwInoutService.insertCwInout(cwInout));
    }

}
