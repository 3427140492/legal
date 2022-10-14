package com.ruoyi.lawyer.controller;

import java.util.List;

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
import com.ruoyi.lawyer.domain.CwPerformancesplits;
import com.ruoyi.lawyer.service.ICwPerformancesplitService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 律师业绩Controller
 * 
 * @author ruoyi
 * @date 2022-09-29
 */
@RestController
@RequestMapping("/lawyer/performancesplit")
public class CwPerformancesplitControllers extends BaseController
{
    @Autowired
    private ICwPerformancesplitService cwPerformancesplitService;

    /**
     * 获取律师业绩详细信息
     */
    @PreAuthorize("@ss.hasPermi('lawyer:performancesplit:query')")
    @GetMapping(value = "/{id}")
    public TableDataInfo getInfo(@PathVariable("id") String id)
    {
        List<CwPerformancesplits> list = cwPerformancesplitService.selectCwPerformancesplitById(id);
        return getDataTable(list);
    }


    /**
     * 新增律师业绩
     */
    @PreAuthorize("@ss.hasPermi('lawyer:performancesplit:add')")
    @Log(title = "律师业绩", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody List<CwPerformancesplits> list)
    {
        return toAjax(cwPerformancesplitService.insertCwPerformancesplit(list));
    }



    /**
     * 删除律师业绩信息
     *
     * @param id 律师业绩主键
     * @return 结果
     */
    @PreAuthorize("@ss.hasPermi('lawyer:performancesplit:remove')")
    @Log(title = "律师业绩", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable String id)
    {
        int num = cwPerformancesplitService.deleteCwPerformancesplitById(id);
        return toAjax(num == 0 ? 1 : num);
    }
}
