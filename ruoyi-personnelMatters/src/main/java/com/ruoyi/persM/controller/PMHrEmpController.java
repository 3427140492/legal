package com.ruoyi.persM.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.persM.domain.Education;
import com.ruoyi.persM.domain.PMHrEmp;
import com.ruoyi.persM.domain.WorkExperience;
import com.ruoyi.persM.service.IPMHrEmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
	@DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        return toAjax(pMHrEmpService.deletePMHrEmpById(id));
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

    /**
     * 修改工作经历
     */
    @PreAuthorize("@ss.hasPermi('persM:archives:updateWork:edit')")
    @Log(title = "工作经历", businessType = BusinessType.UPDATE)
    @PutMapping(value = "updateWork")
    public AjaxResult edit(@RequestBody WorkExperience workExperience)
    {
        return toAjax(pMHrEmpService.updateWorkExperience(workExperience));
    }

    /**
     * 新增工作经历
     */
    @PreAuthorize("@ss.hasPermi('persM:archives:addWork:add')")
    @Log(title = "工作经历", businessType = BusinessType.INSERT)
    @PostMapping(value = "addWork")
    public AjaxResult add(@RequestBody WorkExperience  workExperience )
    {
        return toAjax(pMHrEmpService.insertWorkExperience(workExperience));
    }

    /**
     * 删除工作经历
     */
    @PreAuthorize("@ss.hasPermi('persM:archives:removeWork:remove')")
    @Log(title = "工作经历", businessType = BusinessType.DELETE)
    @DeleteMapping("removeWork/{id}")
    public AjaxResult removeWork(@PathVariable Integer id)
    {
        return toAjax(pMHrEmpService.deleteWorkExperienceByIds(id));
    }

    /**
     * 获取教育经历信息
     */
    @PreAuthorize("@ss.hasPermi('persM:archives:Degree')")
    @GetMapping(value = "Degree/{id}")
    public TableDataInfo getInfoDegree(@PathVariable("id") Long id)
    {
        List<Education> list = pMHrEmpService.selectEducationById(id);
        return getDataTable(list);
    }

    /**
     * 查询人事档案列表
     */
    @PreAuthorize("@ss.hasPermi('persM:archives:Userlist')")
    @GetMapping("/Userlist")
    public TableDataInfo Userlist()
    {
        List<PMHrEmp> list = pMHrEmpService.selectPMHrEmpUserList();
        return getDataTable(list);
    }

}
