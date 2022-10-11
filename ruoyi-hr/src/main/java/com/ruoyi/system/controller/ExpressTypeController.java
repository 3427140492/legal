package com.ruoyi.system.controller;

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
import com.ruoyi.system.domain.ExpressType;
import com.ruoyi.system.service.IExpressTypeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 快递类型Controller
 * 
 * @author ruoyi
 * @date 2022-10-11
 */
@RestController
@RequestMapping("/hr/express")
public class ExpressTypeController extends BaseController
{
    @Autowired
    private IExpressTypeService expressTypeService;

    /**
     * 查询快递类型列表
     */
    @PreAuthorize("@ss.hasPermi('hr:express:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExpressType expressType)
    {
        startPage();
        List<ExpressType> list = expressTypeService.selectExpressTypeList(expressType);
        return getDataTable(list);
    }

    /**
     * 导出快递类型列表
     */
    @PreAuthorize("@ss.hasPermi('hr:express:export')")
    @Log(title = "快递类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ExpressType expressType)
    {
        List<ExpressType> list = expressTypeService.selectExpressTypeList(expressType);
        ExcelUtil<ExpressType> util = new ExcelUtil<ExpressType>(ExpressType.class);
        util.exportExcel(response, list, "快递类型数据");
    }

    /**
     * 获取快递类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('hr:express:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(expressTypeService.selectExpressTypeById(id));
    }

    /**
     * 新增快递类型
     */
    @PreAuthorize("@ss.hasPermi('hr:express:add')")
    @Log(title = "快递类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ExpressType expressType)
    {
        return toAjax(expressTypeService.insertExpressType(expressType));
    }

    /**
     * 修改快递类型
     */
    @PreAuthorize("@ss.hasPermi('hr:express:edit')")
    @Log(title = "快递类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ExpressType expressType)
    {
        return toAjax(expressTypeService.updateExpressType(expressType));
    }

    /**
     * 删除快递类型
     */
    @PreAuthorize("@ss.hasPermi('hr:express:remove')")
    @Log(title = "快递类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(expressTypeService.deleteExpressTypeByIds(ids));
    }
}
