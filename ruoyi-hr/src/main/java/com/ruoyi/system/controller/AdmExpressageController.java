package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.domain.ExpressType;
import com.ruoyi.system.domain.SendWaay;
import com.ruoyi.system.service.IExpressTypeService;
import com.ruoyi.system.service.ISendWaayService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.AdmExpressage;
import com.ruoyi.system.service.IAdmExpressageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.ui.ModelMap;

/**
 * 快速登记Controller
 * 
 * @author ruoyi
 * @date 2022-09-23
 */
@RestController
@RequestMapping("/hr/expressage")
public class AdmExpressageController extends BaseController
{
    @Autowired
    private IAdmExpressageService admExpressageService;

    @Autowired
    private ISendWaayService sendWaayService;

    @Autowired
    private IExpressTypeService expressTypeService;

    @GetMapping("/slist")
    public TableDataInfo slist(){
        List<SendWaay> list = sendWaayService.selectSendName();
        return getDataTable(list);
    }

    @GetMapping("/exlist")
    public TableDataInfo exlist(){
        List<ExpressType> list = expressTypeService.selectExpressName();
        return getDataTable(list);
    }

    /**
     * 查询快速登记列表
     */
    @PreAuthorize("@ss.hasPermi('hr:expressage:list')")
    @GetMapping("/list")
    public TableDataInfo list(AdmExpressage admExpressage)
    {
        startPage();
        List<AdmExpressage> list = admExpressageService.selectAdmExpressageList(admExpressage);
        return getDataTable(list);
    }

    /**
     * 导出快速登记列表
     */
    @PreAuthorize("@ss.hasPermi('hr:expressage:export')")
    @Log(title = "快速登记", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdmExpressage admExpressage)
    {
        List<AdmExpressage> list = admExpressageService.selectAdmExpressageList(admExpressage);
        ExcelUtil<AdmExpressage> util = new ExcelUtil<AdmExpressage>(AdmExpressage.class);
        util.exportExcel(response, list, "快速登记数据");
    }

    /**
     * 获取快速登记详细信息
     */
    @PreAuthorize("@ss.hasPermi('hr:expressage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(admExpressageService.selectAdmExpressageById(id));
    }

    /**
     * 新增快速登记
     */
    @PreAuthorize("@ss.hasPermi('hr:expressage:add')")
    @Log(title = "快速登记", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdmExpressage admExpressage)
    {
        return toAjax(admExpressageService.insertAdmExpressage(admExpressage));
    }

    /**
     * 修改快速登记
     */
    @PreAuthorize("@ss.hasPermi('hr:expressage:edit')")
    @Log(title = "快速登记", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdmExpressage admExpressage)
    {
        return toAjax(admExpressageService.updateAdmExpressage(admExpressage));
    }

    /**
     * 删除快速登记
     */
    @PreAuthorize("@ss.hasPermi('hr:expressage:remove')")
    @Log(title = "快速登记", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(admExpressageService.deleteAdmExpressageByIds(ids));
    }
}
