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
import com.ruoyi.act.domain.CaseLaw;
import com.ruoyi.act.service.ICaseLawService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 我的申请Controller
 * 
 * @author ruoyi
 * @date 2022-09-07
 */
@RestController
@RequestMapping("/ruoyi-act/application")
public class CaseLawController extends BaseController
{
    @Autowired
    private ICaseLawService caseLawService;

    /**
     * 查询我的申请列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-act:application:list')")
    @GetMapping("/list")
    public TableDataInfo list(CaseLaw caseLaw)
    {
        startPage();
        List<CaseLaw> list = caseLawService.selectCaseLawList(caseLaw);
        return getDataTable(list);
    }

    /**
     * 导出我的申请列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-act:application:export')")
    @Log(title = "我的申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CaseLaw caseLaw)
    {
        List<CaseLaw> list = caseLawService.selectCaseLawList(caseLaw);
        ExcelUtil<CaseLaw> util = new ExcelUtil<CaseLaw>(CaseLaw.class);
        util.exportExcel(response, list, "我的申请数据");
    }

    /**
     * 获取我的申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-act:application:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(caseLawService.selectCaseLawById(id));
    }

    /**
     * 新增我的申请
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-act:application:add')")
    @Log(title = "我的申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CaseLaw caseLaw)
    {
        return toAjax(caseLawService.insertCaseLaw(caseLaw));
    }

    /**
     * 修改我的申请
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-act:application:edit')")
    @Log(title = "我的申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CaseLaw caseLaw)
    {
        return toAjax(caseLawService.updateCaseLaw(caseLaw));
    }

    /**
     * 删除我的申请
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-act:application:remove')")
    @Log(title = "我的申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(caseLawService.deleteCaseLawByIds(ids));
    }
}
