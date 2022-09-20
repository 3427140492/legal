package com.ruoyi.web.controller.lvshi;

import com.ruoyi.client.domain.CaseLaw;
import com.ruoyi.client.service.ICaseLawService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 我的当事人Controller
 * 
 * @author gk
 * @date 2022-09-16
 */
@RestController
@RequestMapping("/lvshi/caselaw")
public class CaseLawController extends BaseController
{
    @Autowired
    private ICaseLawService caseLawService;

    /**
     * 查询我的当事人列表
     */
    @PreAuthorize("@ss.hasPermi('client:wddsr:list')")
    @GetMapping("/list")
    public TableDataInfo list(CaseLaw caseLaw)
    {
        startPage();
        List<CaseLaw> list = caseLawService.selectCaseLawList(caseLaw);
        return getDataTable(list);
    }

    /**
     * 导出我的当事人列表
     */
    @PreAuthorize("@ss.hasPermi('client:wddsr:export')")
    @Log(title = "我的当事人", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CaseLaw caseLaw)
    {
        List<CaseLaw> list = caseLawService.selectCaseLawList(caseLaw);
        ExcelUtil<CaseLaw> util = new ExcelUtil<CaseLaw>(CaseLaw.class);
        util.exportExcel(response, list, "我的当事人数据");
    }

    /**
     * 获取我的当事人详细信息
     */
    @PreAuthorize("@ss.hasPermi('client:wddsr:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(caseLawService.selectCaseLawById(id));
    }

    /**
     * 新增我的当事人
     */
    @PreAuthorize("@ss.hasPermi('client:wddsr:add')")
    @Log(title = "我的当事人", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CaseLaw caseLaw)
    {
        return toAjax(caseLawService.insertCaseLaw(caseLaw));
    }

    /**
     * 修改我的当事人
     */
    @PreAuthorize("@ss.hasPermi('client:wddsr:edit')")
    @Log(title = "我的当事人", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CaseLaw caseLaw)
    {
        return toAjax(caseLawService.updateCaseLaw(caseLaw));
    }

    /**
     * 删除我的当事人
     */
    @PreAuthorize("@ss.hasPermi('client:wddsr:remove')")
    @Log(title = "我的当事人", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(caseLawService.deleteCaseLawByIds(ids));
    }
}
