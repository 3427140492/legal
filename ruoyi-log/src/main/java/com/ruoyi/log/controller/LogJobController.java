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
import com.ruoyi.log.domain.LogJob;
import com.ruoyi.log.service.ILogJobService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工作Controller
 * 
 * @author ruoyi
 * @date 2022-09-21
 */
@RestController
@RequestMapping("/log/job")
public class LogJobController extends BaseController
{
    @Autowired
    private ILogJobService logJobService;

    /**
     * 查询工作列表
     */
    @PreAuthorize("@ss.hasPermi('log:job:list')")
    @GetMapping("/list")
    public TableDataInfo list(LogJob logJob)
    {
        startPage();
        List<LogJob> list = logJobService.selectLogJobList(logJob);

        return getDataTable(list);
    }

    /**
     * 导出工作列表
     */
    @PreAuthorize("@ss.hasPermi('log:job:export')")
    @Log(title = "工作", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LogJob logJob)
    {
        List<LogJob> list = logJobService.selectLogJobList(logJob);
        ExcelUtil<LogJob> util = new ExcelUtil<LogJob>(LogJob.class);
        util.exportExcel(response, list, "工作数据");
    }

    /**
     *
     *
     *
     * 获取工作详细信息
     */
    @PreAuthorize("@ss.hasPermi('log:job:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(logJobService.selectLogJobById(id));
    }

    /**
     * 新增工作
     */
    @PreAuthorize("@ss.hasPermi('log:job:add')")
    @Log(title = "工作", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LogJob logJob)
    {
        return toAjax(logJobService.insertLogJob(logJob));
    }

    /**
     * 修改工作
     */
    @PreAuthorize("@ss.hasPermi('log:job:edit')")
    @Log(title = "工作", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LogJob logJob)
    {
        return toAjax(logJobService.updateLogJob(logJob));
    }

    /**
     * 删除工作
     */
    @PreAuthorize("@ss.hasPermi('log:job:remove')")
    @Log(title = "工作", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(logJobService.deleteLogJobByIds(ids));
    }
}
