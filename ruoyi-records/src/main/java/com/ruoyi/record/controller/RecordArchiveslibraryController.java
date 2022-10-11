package com.ruoyi.record.controller;

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
import com.ruoyi.record.domain.RecordArchiveslibrary;
import com.ruoyi.record.service.IRecordArchiveslibraryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 档案借阅Controller
 * 
 * @author ruoyi
 * @date 2022-10-10
 */
@RestController
@RequestMapping("/record/archiveslibrary")
public class RecordArchiveslibraryController extends BaseController
{
    @Autowired
    private IRecordArchiveslibraryService recordArchiveslibraryService;

    /**
     * 查询档案借阅列表
     */
    @PreAuthorize("@ss.hasPermi('record:archiveslibrary:list')")
    @GetMapping("/list")
    public TableDataInfo list(RecordArchiveslibrary recordArchiveslibrary)
    {
        startPage();
        List<RecordArchiveslibrary> list = recordArchiveslibraryService.selectRecordArchiveslibraryList(recordArchiveslibrary);
        return getDataTable(list);
    }

    /**
     * 导出档案借阅列表
     */
    @PreAuthorize("@ss.hasPermi('record:archiveslibrary:export')")
    @Log(title = "档案借阅", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RecordArchiveslibrary recordArchiveslibrary)
    {
        List<RecordArchiveslibrary> list = recordArchiveslibraryService.selectRecordArchiveslibraryList(recordArchiveslibrary);
        ExcelUtil<RecordArchiveslibrary> util = new ExcelUtil<RecordArchiveslibrary>(RecordArchiveslibrary.class);
        util.exportExcel(response, list, "档案借阅数据");
    }

    /**
     * 获取档案借阅详细信息
     */
    @PreAuthorize("@ss.hasPermi('record:archiveslibrary:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(recordArchiveslibraryService.selectRecordArchiveslibraryById(id));
    }

    /**
     * 新增档案借阅
     */
    @PreAuthorize("@ss.hasPermi('record:archiveslibrary:add')")
    @Log(title = "档案借阅", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RecordArchiveslibrary recordArchiveslibrary)
    {
        return toAjax(recordArchiveslibraryService.insertRecordArchiveslibrary(recordArchiveslibrary));
    }

    /**
     * 修改档案借阅
     */
    @PreAuthorize("@ss.hasPermi('record:archiveslibrary:edit')")
    @Log(title = "档案借阅", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RecordArchiveslibrary recordArchiveslibrary)
    {
        return toAjax(recordArchiveslibraryService.updateRecordArchiveslibrary(recordArchiveslibrary));
    }


    /**
     * 删除档案借阅
     */
    @PreAuthorize("@ss.hasPermi('record:archiveslibrary:remove')")
    @Log(title = "档案借阅", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(recordArchiveslibraryService.deleteRecordArchiveslibraryByIds(ids));
    }
}
