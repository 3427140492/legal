package com.ruoyi.lawyer.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.lawyer.domain.CaseDecreases;
import com.ruoyi.lawyer.service.ICaseDecreaseService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 减免申请Controller
 * 
 * @author ck
 * @date 2022-09-21
 */
@RestController
@RequestMapping("/case/decrease")
public class CaseDecreaseControllers extends BaseController {
    @Autowired
    private ICaseDecreaseService caseDecreaseService;

    /**
     * 查询减免申请列表
     */
    @PreAuthorize("@ss.hasPermi('system:decrease:list')")
    @GetMapping("/list1/{caseSubmitter}")
    public TableDataInfo list1(@PathVariable("caseSubmitter") String caseSubmitter)
    {
        startPage();
        List<CaseDecreases> list = caseDecreaseService.selectCaseDecreaseList1(caseSubmitter);
        return getDataTable(list);
    }

    /**
     * 查询减免申请列表
     */
    @PreAuthorize("@ss.hasPermi('system:decrease:list')")
    @GetMapping("/list")
    public TableDataInfo list(CaseDecreases caseDecrease)
    {
        startPage();
        List<CaseDecreases> list = caseDecreaseService.selectCaseDecreaseList(caseDecrease);
        return getDataTable(list);
    }

    /**
     * 新增减免申请
     */
    @PreAuthorize("@ss.hasPermi('cases:decrease:add')")
    @Log(title = "减免申请", businessType = BusinessType.INSERT)
    @PostMapping("/addDecrease")
    public AjaxResult add(@RequestBody CaseDecreases caseDecrease) {
        return toAjax(caseDecreaseService.insertCaseDecrease(caseDecrease));
    }

}
