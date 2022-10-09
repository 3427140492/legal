package com.ruoyi.finance.controller;

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
import com.ruoyi.finance.domain.CwInvoice;
import com.ruoyi.finance.service.ICwInvoiceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 我的发票Controller
 * 
 * @author ruoyi
 * @date 2022-09-29
 */
@RestController
@RequestMapping("/finance/invoice")
public class CwInvoiceController extends BaseController
{
    @Autowired
    private ICwInvoiceService cwInvoiceService;

    /**
     * 查询我的发票列表
     */
    @PreAuthorize("@ss.hasPermi('finance:invoice:list')")
    @GetMapping("/list")
    public TableDataInfo list(CwInvoice cwInvoice)
    {
        startPage();
        List<CwInvoice> list = cwInvoiceService.selectCwInvoiceList(cwInvoice);
        return getDataTable(list);
    }

    /**
     * 查询案件类型
     */
    @PreAuthorize("@ss.hasPermi('finance:payments:anlxlist')")
    @GetMapping("/anlxlist")
    public TableDataInfo anlxlist(CwInvoice cwInvoice)
    {
        List<CwInvoice> list = cwInvoiceService.selectCaseCaseTypelist(cwInvoice);
        return getDataTable(list);
    }

    /**
     * 导出我的发票列表
     */
    @PreAuthorize("@ss.hasPermi('finance:invoice:export')")
    @Log(title = "我的发票", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CwInvoice cwInvoice)
    {
        List<CwInvoice> list = cwInvoiceService.selectCwInvoiceList(cwInvoice);
        ExcelUtil<CwInvoice> util = new ExcelUtil<CwInvoice>(CwInvoice.class);
        util.exportExcel(response, list, "我的发票数据");
    }

    /**
     * 获取我的发票详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:invoice:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(cwInvoiceService.selectCwInvoiceById(id));
    }

    /**
     * 新增我的发票
     */
    @PreAuthorize("@ss.hasPermi('finance:invoice:add')")
    @Log(title = "我的发票", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CwInvoice cwInvoice)
    {
        return toAjax(cwInvoiceService.insertCwInvoice(cwInvoice));
    }

    /**
     * 修改我的发票
     */
    @PreAuthorize("@ss.hasPermi('finance:invoice:edit')")
    @Log(title = "我的发票", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CwInvoice cwInvoice)
    {
        return toAjax(cwInvoiceService.updateCwInvoice(cwInvoice));
    }

    /**
     * 删除我的发票
     */
    @PreAuthorize("@ss.hasPermi('finance:invoice:remove')")
    @Log(title = "我的发票", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(cwInvoiceService.deleteCwInvoiceByIds(ids));
    }
}
