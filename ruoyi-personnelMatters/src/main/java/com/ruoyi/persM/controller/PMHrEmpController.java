package com.ruoyi.persM.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.persM.domain.WorkExperience;
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
import com.ruoyi.persM.domain.PMHrEmp;
import com.ruoyi.persM.service.IPMHrEmpService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 人事档案Controller
 *
 * @author ruoyi
 * @date 2022-09-27
 */
@RestController
@RequestMapping("/persM/archives")
public class PMHrEmpController extends BaseController
{
    @Autowired
    private IPMHrEmpService pMHrEmpService;

    /**
     * 查询人事档案列表
     */
    @PreAuthorize("@ss.hasPermi('persM:archives:list')")
    @GetMapping("/list")
    public TableDataInfo list(PMHrEmp pMHrEmp)
    {
        startPage();
        List<PMHrEmp> list = pMHrEmpService.selectPMHrEmpList(pMHrEmp);
        return getDataTable(list);
    }

    /**
     * 导出人事档案列表
     */
    @PreAuthorize("@ss.hasPermi('persM:archives:export')")
    @Log(title = "人事档案", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PMHrEmp pMHrEmp)
    {
        List<PMHrEmp> list = pMHrEmpService.selectPMHrEmpList(pMHrEmp);
        ExcelUtil<PMHrEmp> util = new ExcelUtil<PMHrEmp>(PMHrEmp.class);
        util.exportExcel(response, list, "人事档案数据");
    }

    /**
     * 获取人事档案详细信息
     */
    @PreAuthorize("@ss.hasPermi('persM:archives:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(pMHrEmpService.selectPMHrEmpById(id));
    }

    /**
     * 新增人事档案
     */
    @PreAuthorize("@ss.hasPermi('persM:archives:add')")
    @Log(title = "人事档案", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PMHrEmp pMHrEmp)
    {
        return toAjax(pMHrEmpService.insertPMHrEmp(pMHrEmp));
    }

    /**
     * 修改人事档案
     */
    @PreAuthorize("@ss.hasPermi('persM:archives:edit')")
    @Log(title = "人事档案", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PMHrEmp pMHrEmp)
    {
        return toAjax(pMHrEmpService.updatePMHrEmp(pMHrEmp));
    }

    /**
     * 删除人事档案
     */
    @PreAuthorize("@ss.hasPermi('persM:archives:remove')")
    @Log(title = "人事档案", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pMHrEmpService.deletePMHrEmpByIds(ids));
    }

    /**
     * 获取工作经历信息
     */
    @PreAuthorize("@ss.hasPermi('persM:archives:queryWork')")
    @GetMapping(value = "queryWork/{id}")
    public TableDataInfo getInfoWork(@PathVariable("id") Long id)
    {
        List<WorkExperience> list = pMHrEmpService.selectWorkById(id);
        return getDataTable(list);
    }

    /**
     * 获取工作经历详细信息
     */
    @PreAuthorize("@ss.hasPermi('persM:archives:queryWorkQuery')")
    @GetMapping(value = "queryWorkQuery/{id}")
    public AjaxResult getInfoWorkQuery(@PathVariable("id") Long id)
    {
        return AjaxResult.success(pMHrEmpService.selectWorkExperienceById(id));
    }

}
