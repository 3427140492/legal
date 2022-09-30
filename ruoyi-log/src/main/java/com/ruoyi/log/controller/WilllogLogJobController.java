package com.ruoyi.log.controller;

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
import com.ruoyi.log.domain.WilllogLogJob;
import com.ruoyi.log.service.IWilllogLogJobService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 全所日志Controller
 * 
 * @author ruoyi
 * @date 2022-09-29
 */
@RestController
@RequestMapping("/log/Willlog")
public class WilllogLogJobController extends BaseController
{
    @Autowired
    private IWilllogLogJobService willlogLogJobService;

    /**
     * 查询全所日志列表
     */
    @PreAuthorize("@ss.hasPermi('log:Willlog:list')")
    @GetMapping("/list")
    public TableDataInfo list(WilllogLogJob willlogLogJob)
    {
        startPage();
        List<WilllogLogJob> list = willlogLogJobService.selectWilllogLogJobList(willlogLogJob);
        return getDataTable(list);
    }

    /**
     * 导出全所日志列表
     */
    @PreAuthorize("@ss.hasPermi('log:Willlog:export')")
    @Log(title = "全所日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WilllogLogJob willlogLogJob)
    {
        List<WilllogLogJob> list = willlogLogJobService.selectWilllogLogJobList(willlogLogJob);
        ExcelUtil<WilllogLogJob> util = new ExcelUtil<WilllogLogJob>(WilllogLogJob.class);
        util.exportExcel(response, list, "全所日志数据");
    }

    /**
     * 获取全所日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('log:Willlog:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(willlogLogJobService.selectWilllogLogJobById(id));
    }

    /**
     * 新增全所日志
     */
    @PreAuthorize("@ss.hasPermi('log:Willlog:add')")
    @Log(title = "全所日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WilllogLogJob willlogLogJob)
    {
        return toAjax(willlogLogJobService.insertWilllogLogJob(willlogLogJob));
    }

    /**
     * 修改全所日志
     */
    @PreAuthorize("@ss.hasPermi('log:Willlog:edit')")
    @Log(title = "全所日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WilllogLogJob willlogLogJob)
    {
        return toAjax(willlogLogJobService.updateWilllogLogJob(willlogLogJob));
    }

    /**
     * 删除全所日志
     */
    @PreAuthorize("@ss.hasPermi('log:Willlog:remove')")
    @Log(title = "全所日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(willlogLogJobService.deleteWilllogLogJobByIds(ids));
    }
}
