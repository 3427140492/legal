package com.ruoyi.lawyer.controller;

import java.util.List;

import com.ruoyi.lawyer.domain.RecordFiles;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.lawyer.domain.CaseSealApplyfors;
import com.ruoyi.lawyer.service.ICaseSealApplyforService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 公函记录Controller
 * 
 * @author ruoyi
 * @date 2022-10-03
 */
@RestController
@RequestMapping("/lawyer/applyfor")
public class CaseSealApplyforControllers extends BaseController {

    @Autowired
    private ICaseSealApplyforService caseSealApplyforService;

    /**
     * 查询 批量生成文书 列表
     */
    @PreAuthorize("@ss.hasPermi('lawyer:applyfor:list')")
    @GetMapping("/getProduction/{caseid}")
    public TableDataInfo getProduction(@PathVariable("caseid") String caseid)
    {
        List<RecordFiles> list = caseSealApplyforService.getProduction(caseid);
        return getDataTable(list);
    }

    /**
     * 删除公函记录
     */
    @PreAuthorize("@ss.hasPermi('lawyer:applyfor:remove')")
    @Log(title = "公函记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable("id") String id)
    {
        return toAjax(caseSealApplyforService.deleteCaseSealApplyforById(id));
    }

    /**
     * 查询公函记录列表
     */
    @PreAuthorize("@ss.hasPermi('lawyer:applyfor:list')")
    @GetMapping("/list")
    public TableDataInfo list(CaseSealApplyfors caseSealApplyfor)
    {
        startPage();
        List<CaseSealApplyfors> list = caseSealApplyforService.selectCaseSealApplyforList(caseSealApplyfor);
        return getDataTable(list);
    }

    /**
     * 新增公函记录
     */
    @PreAuthorize("@ss.hasPermi('lawyer:applyfor:add')")
    @Log(title = "公函记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CaseSealApplyfors caseSealApplyfor)
    {
        return toAjax(caseSealApplyforService.insertCaseSealApplyfor(caseSealApplyfor));
    }

    /**
     * 查询公函记录列表
     */
    @PreAuthorize("@ss.hasPermi('lawyer:applyfor:list')")
    @GetMapping("/list/{id}")
    public TableDataInfo list(@PathVariable("id") String id) {
        List<CaseSealApplyfors> list = caseSealApplyforService.selectCaseSealApplyforList(id);
        return getDataTable(list);
    }

}