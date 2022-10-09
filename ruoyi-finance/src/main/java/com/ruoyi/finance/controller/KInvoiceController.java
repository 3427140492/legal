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
import com.ruoyi.finance.service.IKInvoiceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 开具发票Controller
 * 
 * @author ruoyi
 * @date 2022-10-06
 */
@RestController
@RequestMapping("/finance/kinvoice")
public class KInvoiceController extends BaseController
{
    @Autowired
    private IKInvoiceService kInvoiceService;

    /**
     * 查询开具发票列表
     */
    @PreAuthorize("@ss.hasPermi('finance:kinvoice:list')")
    @GetMapping("/list")
    public TableDataInfo list(CwInvoice kInvoice)
    {
        startPage();
        List<CwInvoice> list = kInvoiceService.selectKInvoiceList(kInvoice);
        return getDataTable(list);
    }

    /**
     * 查询案件类型
     */
    @PreAuthorize("@ss.hasPermi('finance:payments:anlxlist')")
    @GetMapping("/anlxlist")
    public TableDataInfo anlxlist(CwInvoice kInvoice)
    {
        List<CwInvoice> list = kInvoiceService.selectCaseCaseTypelist(kInvoice);
        return getDataTable(list);
    }

    /**
     * 导出开具发票列表
     */
    @PreAuthorize("@ss.hasPermi('finance:kinvoice:export')")
    @Log(title = "开具发票", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CwInvoice kInvoice)
    {
        List<CwInvoice> list = kInvoiceService.selectKInvoiceList(kInvoice);
        ExcelUtil<CwInvoice> util = new ExcelUtil<CwInvoice>(CwInvoice.class);
        util.exportExcel(response, list, "开具发票数据");
    }

    /**
     * 获取开具发票详细信息
     */
    @PreAuthorize("@ss.hasPermi('finance:kinvoice:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(kInvoiceService.selectKInvoiceById(id));
    }

    /**
     * 新增开具发票
     */
    @PreAuthorize("@ss.hasPermi('finance:kinvoice:add')")
    @Log(title = "开具发票", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CwInvoice kInvoice)
    {
        return toAjax(kInvoiceService.insertKInvoice(kInvoice));
    }

    /**
     * 修改开具发票
     */
    @PreAuthorize("@ss.hasPermi('finance:kinvoice:edit')")
    @Log(title = "开具发票", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CwInvoice kInvoice)
    {
        return toAjax(kInvoiceService.updateKInvoice(kInvoice));
    }

    /**
     * 删除开具发票
     */
    @PreAuthorize("@ss.hasPermi('finance:kinvoice:remove')")
    @Log(title = "开具发票", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(kInvoiceService.deleteKInvoiceByIds(ids));
    }
}
