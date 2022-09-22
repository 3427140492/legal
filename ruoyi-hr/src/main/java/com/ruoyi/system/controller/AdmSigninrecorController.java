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
import com.ruoyi.system.domain.AdmSigninrecor;
import com.ruoyi.system.service.IAdmSigninrecorService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 签到Controller
 * 
 * @author ruoyi
 * @date 2022-09-22
 */
@RestController
@RequestMapping("/hr/signinrecord")
public class AdmSigninrecorController extends BaseController
{
    @Autowired
    private IAdmSigninrecorService admSigninrecorService;

    /**
     * 查询签到列表
     */
    @PreAuthorize("@ss.hasPermi('hr:signinrecord:list')")
    @GetMapping("/list")
    public TableDataInfo list(AdmSigninrecor admSigninrecor)
    {
        startPage();
        List<AdmSigninrecor> list = admSigninrecorService.selectAdmSigninrecorList(admSigninrecor);
        return getDataTable(list);
    }

    /**
     * 导出签到列表
     */
    @PreAuthorize("@ss.hasPermi('hr:signinrecord:export')")
    @Log(title = "签到", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdmSigninrecor admSigninrecor)
    {
        List<AdmSigninrecor> list = admSigninrecorService.selectAdmSigninrecorList(admSigninrecor);
        ExcelUtil<AdmSigninrecor> util = new ExcelUtil<AdmSigninrecor>(AdmSigninrecor.class);
        util.exportExcel(response, list, "签到数据");
    }

    /**
     * 获取签到详细信息
     */
    @PreAuthorize("@ss.hasPermi('hr:signinrecord:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(admSigninrecorService.selectAdmSigninrecorById(id));
    }

    /**
     * 新增签到
     */
    @PreAuthorize("@ss.hasPermi('hr:signinrecord:add')")
    @Log(title = "签到", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdmSigninrecor admSigninrecor)
    {
        return toAjax(admSigninrecorService.insertAdmSigninrecor(admSigninrecor));
    }

    /**
     * 修改签到
     */
    @PreAuthorize("@ss.hasPermi('hr:signinrecord:edit')")
    @Log(title = "签到", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdmSigninrecor admSigninrecor)
    {
        return toAjax(admSigninrecorService.updateAdmSigninrecor(admSigninrecor));
    }

    /**
     * 删除签到
     */
    @PreAuthorize("@ss.hasPermi('hr:signinrecord:remove')")
    @Log(title = "签到", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(admSigninrecorService.deleteAdmSigninrecorByIds(ids));
    }
}
