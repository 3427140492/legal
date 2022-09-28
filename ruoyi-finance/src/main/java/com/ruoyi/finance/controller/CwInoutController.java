package com.ruoyi.finance.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.finance.domain.CwInoutType;
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
import com.ruoyi.finance.domain.FinCwInout;
import com.ruoyi.finance.service.ICwInoutService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 全所收支明细Controller
 * 
 * @author ruoyi
 * @date 2022-09-22
 */
@RestController
@RequestMapping("/finance/payments")
public class CwInoutController extends BaseController
{
    @Autowired
    private ICwInoutService cwInoutService;

    /**
     * 查询全所收支明细列表
     */
    @PreAuthorize("@ss.hasPermi('finance:payments:list')")
    @GetMapping("/list")
    public TableDataInfo list(FinCwInout finCwInout)
    {
        startPage();
        List<FinCwInout> list = cwInoutService.selectCwInoutList(finCwInout);
        return getDataTable(list);
    }

    /**
     * 查询我的收支列表
     */
    @PreAuthorize("@ss.hasPermi('finance:payments:list')")
    @GetMapping("/mylist")
    public TableDataInfo mylist(FinCwInout finCwInout)
    {
        startPage();
        List<FinCwInout> list = cwInoutService.selectMyCwInout(finCwInout);
        return getDataTable(list);
    }


    @GetMapping("/sxlist")
    public TableDataInfo list2()
    {
        List<CwInoutType> list = (List<CwInoutType>) cwInoutService.selectCwInoutTypeList();
        return getDataTable(list);
    }


    /**
     * 导出全所收支明细列表
     */
    @PreAuthorize("@ss.hasPermi('finance:payments:export')")
    @Log(title = "全所收支明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FinCwInout finCwInout)
    {
        List<FinCwInout> list = cwInoutService.selectCwInoutList(finCwInout);
        ExcelUtil<FinCwInout> util = new ExcelUtil<FinCwInout>(FinCwInout.class);
        util.exportExcel(response, list, "全所收支明细数据");
    }

    /**
     * 获取全所收支明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:payments:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cwInoutService.selectCwInoutById(id));
    }

    /**
     * 新增全所收支明细
     */
    @PreAuthorize("@ss.hasPermi('finance:payments:add')")
    @Log(title = "全所收支明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FinCwInout finCwInout)
    {
        return toAjax(cwInoutService.insertCwInout(finCwInout));
    }

    /**
     * 修改全所收支明细
     */
    @PreAuthorize("@ss.hasPermi('finance:payments:edit')")
    @Log(title = "全所收支明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FinCwInout finCwInout)
    {
        return toAjax(cwInoutService.updateCwInout(finCwInout));
    }

    /**
     * 删除全所收支明细
     */
    @PreAuthorize("@ss.hasPermi('finance:payments:remove')")
    @Log(title = "全所收支明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(cwInoutService.deleteCwInoutByIds(ids));
    }
}
