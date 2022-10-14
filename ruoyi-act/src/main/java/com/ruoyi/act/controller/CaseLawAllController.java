package com.ruoyi.act.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.act.domain.CaseLawAll;
import com.ruoyi.act.service.ICaseLawAllService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 审批中心Controller
 * 
 * @author ruoyi
 * @date 2022-09-27
 */
@RestController
@RequestMapping("/ruoyi-act/lawall")
public class CaseLawAllController extends BaseController
{
    @Autowired
    private ICaseLawAllService caseLawAllService;

    /**
     * 查询审批中心列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-act:application')")
    @GetMapping("/list")
    public TableDataInfo list(CaseLawAll caseLawAll)
    {
        startPage();
        List<CaseLawAll> list = caseLawAllService.selectCaseLawAllList(caseLawAll);
        return getDataTable(list);
    }

    /**
     * 导出审批中心列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-act:application')")
    @Log(title = "审批中心", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CaseLawAll caseLawAll)
    {
        List<CaseLawAll> list = caseLawAllService.selectCaseLawAllList(caseLawAll);
        ExcelUtil<CaseLawAll> util = new ExcelUtil<CaseLawAll>(CaseLawAll.class);
        util.exportExcel(response, list, "审批中心数据");
    }

    /**
     * 获取审批中心详细信息
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-act:application')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(caseLawAllService.selectCaseLawAllById(id));
    }

    /**
     * 新增审批中心
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-act:application')")
    @Log(title = "审批中心", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CaseLawAll caseLawAll)
    {
        return toAjax(caseLawAllService.insertCaseLawAll(caseLawAll));
    }

    /**
     * 修改审批中心
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-act:application')")
    @Log(title = "审批中心", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CaseLawAll caseLawAll)
    {
        return toAjax(caseLawAllService.updateCaseLawAll(caseLawAll));
    }

    /**
     * 删除审批中心
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-act:application')")
    @Log(title = "审批中心", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(caseLawAllService.deleteCaseLawAllByIds(ids));
    }
}
