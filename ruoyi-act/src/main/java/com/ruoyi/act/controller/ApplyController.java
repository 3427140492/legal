package com.ruoyi.act.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.act.domain.ActHiComment;
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
import com.ruoyi.act.domain.Apply;
import com.ruoyi.act.service.IApplyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 业务申请Controller
 * 
 * @author ruoyi
 * @date 2022-09-21
 */
@RestController
@RequestMapping("/ruoyi-act/apply")
public class ApplyController extends BaseController
{
    @Autowired
    private IApplyService applyService;

    /**
     * 查询业务申请列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-act:application')")
    @GetMapping("/list")
    public TableDataInfo list(Apply apply)
    {
        startPage();
        List<Apply> list = applyService.selectApplyList(apply);
        return getDataTable(list);
    }

    /**
     * 查询业务申请回显表
     */
//    @PreAuthorize("@ss.hasPermi('ruoyi-act:apply:hxb')")
//    @GetMapping("/hxb")
//    public TableDataInfo selapphxb(ActHiComment actHiComment)
//    {
//        List<ActHiComment> list = applyService.selapphxb(actHiComment);
//        return getDataTable(list);
//    }



    /**
     * 导出业务申请列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-act:application')")
    @Log(title = "业务申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Apply apply)
    {
        List<Apply> list = applyService.selectApplyList(apply);
        ExcelUtil<Apply> util = new ExcelUtil<Apply>(Apply.class);
        util.exportExcel(response, list, "业务申请数据");
    }

    /**
     * 获取业务申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-act:application')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(applyService.selectApplyById(id));
    }

    /**
     * 新增业务申请
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-act:application')")
    @Log(title = "业务申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Apply apply)
    {
        return toAjax(applyService.insertApply(apply));
    }

    /**
     * 修改业务申请
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-act:application')")
    @Log(title = "业务申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Apply apply)
    {
        return toAjax(applyService.updateApply(apply));
    }

    /**
     * 删除业务申请
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-act:application')")
    @Log(title = "业务申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(applyService.deleteApplyByIds(ids));
    }
}
