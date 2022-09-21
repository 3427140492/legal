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
import com.ruoyi.act.domain.CaseSealApplyfor;
import com.ruoyi.act.service.ICaseSealApplyforService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用印申请Controller
 * 
 * @author ruoyi
 * @date 2022-09-14
 */
@RestController
@RequestMapping("/ruoyi-act/applyfor")
public class CaseSealApplyforController extends BaseController
{
    @Autowired
    private ICaseSealApplyforService caseSealApplyforService;

    /**
     * 查询用印申请列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-act:applyfor:list')")
    @GetMapping("/list")
    public TableDataInfo list(CaseSealApplyfor caseSealApplyfor)
    {
        startPage();
        List<CaseSealApplyfor> list = caseSealApplyforService.selectCaseSealApplyforList(caseSealApplyfor);
        return getDataTable(list);
    }

    /**
     * 导出用印申请列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-act:applyfor:export')")
    @Log(title = "用印申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CaseSealApplyfor caseSealApplyfor)
    {
        List<CaseSealApplyfor> list = caseSealApplyforService.selectCaseSealApplyforList(caseSealApplyfor);
        ExcelUtil<CaseSealApplyfor> util = new ExcelUtil<CaseSealApplyfor>(CaseSealApplyfor.class);
        util.exportExcel(response, list, "用印申请数据");
    }

    /**
     * 获取用印申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-act:applyfor:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(caseSealApplyforService.selectCaseSealApplyforById(id));
    }

    /**
     * 新增用印申请
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-act:applyfor:add')")
    @Log(title = "用印申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CaseSealApplyfor caseSealApplyfor)
    {
        return toAjax(caseSealApplyforService.insertCaseSealApplyfor(caseSealApplyfor));
    }

    /**
     * 修改用印申请
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-act:applyfor:edit')")
    @Log(title = "用印申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CaseSealApplyfor caseSealApplyfor)
    {
        return toAjax(caseSealApplyforService.updateCaseSealApplyfor(caseSealApplyfor));
    }

    /**
     * 删除用印申请
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-act:applyfor:remove')")
    @Log(title = "用印申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(caseSealApplyforService.deleteCaseSealApplyforByIds(ids));
    }
}
