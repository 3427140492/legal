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
import com.ruoyi.system.domain.AdmComplain;
import com.ruoyi.system.service.IAdmComplainService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 建议投诉Controller
 * 
 * @author ruoyi
 * @date 2022-09-21
 */
@RestController
@RequestMapping("/hr/complain")
public class AdmComplainController extends BaseController
{
    @Autowired
    private IAdmComplainService admComplainService;

    /**
     * 查询建议投诉列表
     */
    @PreAuthorize("@ss.hasPermi('hr:complain:list')")
    @GetMapping("/list")
    public TableDataInfo list(AdmComplain admComplain)
    {
        startPage();
        List<AdmComplain> list = admComplainService.selectAdmComplainList(admComplain);
        return getDataTable(list);
    }

    /**
     * 导出建议投诉列表
     */
    @PreAuthorize("@ss.hasPermi('hr:complain:export')")
    @Log(title = "建议投诉", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdmComplain admComplain)
    {
        List<AdmComplain> list = admComplainService.selectAdmComplainList(admComplain);
        ExcelUtil<AdmComplain> util = new ExcelUtil<AdmComplain>(AdmComplain.class);
        util.exportExcel(response, list, "建议投诉数据");
    }

    /**
     * 获取建议投诉详细信息
     */
    @PreAuthorize("@ss.hasPermi('hr:complain:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(admComplainService.selectAdmComplainById(id));
    }

    /**
     * 新增建议投诉
     */
    @PreAuthorize("@ss.hasPermi('hr:complain:add')")
    @Log(title = "建议投诉", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdmComplain admComplain)
    {
        return toAjax(admComplainService.insertAdmComplain(admComplain));
    }

    /**
     * 修改建议投诉
     */
    @PreAuthorize("@ss.hasPermi('hr:complain:edit')")
    @Log(title = "建议投诉", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdmComplain admComplain)
    {
        return toAjax(admComplainService.updateAdmComplain(admComplain));
    }

    /**
     * 删除建议投诉
     */
    @PreAuthorize("@ss.hasPermi('hr:complain:remove')")
    @Log(title = "建议投诉", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(admComplainService.deleteAdmComplainByIds(ids));
    }
}
