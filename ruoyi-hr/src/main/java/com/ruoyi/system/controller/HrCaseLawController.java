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
import com.ruoyi.system.domain.HrCaseLaw;
import com.ruoyi.system.service.IHrCaseLawService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 案件法律Controller
 * 
 * @author ruoyi
 * @date 2022-10-11
 */
@RestController
@RequestMapping("/hr/HrLaw")
public class HrCaseLawController extends BaseController
{
    @Autowired
    private IHrCaseLawService hrCaseLawService;

    /**
     * 查询案件法律列表
     */
    @PreAuthorize("@ss.hasPermi('hr:HrLaw:list')")
    @GetMapping("/list")
    public TableDataInfo list(HrCaseLaw hrCaseLaw)
    {
        startPage();
        List<HrCaseLaw> list = hrCaseLawService.selectHrCaseLawList(hrCaseLaw);
        return getDataTable(list);
    }

    /**
     * 导出案件法律列表
     */
    @PreAuthorize("@ss.hasPermi('hr:HrLaw:export')")
    @Log(title = "案件法律", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, HrCaseLaw hrCaseLaw)
    {
        List<HrCaseLaw> list = hrCaseLawService.selectHrCaseLawList(hrCaseLaw);
        ExcelUtil<HrCaseLaw> util = new ExcelUtil<HrCaseLaw>(HrCaseLaw.class);
        util.exportExcel(response, list, "案件法律数据");
    }

    /**
     * 获取案件法律详细信息
     */
    @PreAuthorize("@ss.hasPermi('hr:HrLaw:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(hrCaseLawService.selectHrCaseLawById(id));
    }

    /**
     * 新增案件法律
     */
    @PreAuthorize("@ss.hasPermi('hr:HrLaw:add')")
    @Log(title = "案件法律", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody HrCaseLaw hrCaseLaw)
    {
        return toAjax(hrCaseLawService.insertHrCaseLaw(hrCaseLaw));
    }

    /**
     * 修改案件法律
     */
    @PreAuthorize("@ss.hasPermi('hr:HrLaw:edit')")
    @Log(title = "案件法律", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody HrCaseLaw hrCaseLaw)
    {
        return toAjax(hrCaseLawService.updateHrCaseLaw(hrCaseLaw));
    }

    /**
     * 删除案件法律
     */
    @PreAuthorize("@ss.hasPermi('hr:HrLaw:remove')")
    @Log(title = "案件法律", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(hrCaseLawService.deleteHrCaseLawByIds(ids));
    }
}
