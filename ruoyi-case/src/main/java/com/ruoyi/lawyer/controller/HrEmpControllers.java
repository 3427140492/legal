package com.ruoyi.lawyer.controller;

import com.ruoyi.lawyer.service.IHrEmpService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;


/**
 * 律师信息Controller
 * 
 * @author ruoyi
 * @date 2022-09-20
 */
@RestController
@RequestMapping("/case/hremp")
public class HrEmpControllers extends BaseController {

    @Autowired
    private IHrEmpService hrEmpService;

    /**
     * 查询通讯录表 (条件查询)
     *
     * @param id 通讯录表主键
     * @return 通讯录表
     */
    @PreAuthorize("@ss.hasPermi('system:emp:query')")
    @GetMapping(value = "/getEmp/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(hrEmpService.selectHrEmpById(id));
    }

}