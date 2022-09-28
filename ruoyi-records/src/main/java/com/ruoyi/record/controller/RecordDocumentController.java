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
import com.ruoyi.record.domain.RecordDocument;
import com.ruoyi.record.service.IRecordDocumentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 全所电子归档Controller
 *
 * @author ruoyi
 * @date 2022-09-27
 */
@RestController
@RequestMapping("/record/document")
public class RecordDocumentController extends BaseController
{
    @Autowired
    private IRecordDocumentService recordDocumentService;

    /**
     * 查询全所电子归档列表
     */
    @PreAuthorize("@ss.hasPermi('record:document:list')")
    @GetMapping("/list")
    public TableDataInfo list(RecordDocument recordDocument)
    {
        startPage();
        List<RecordDocument> list = recordDocumentService.selectRecordDocumentList(recordDocument);
        return getDataTable(list);
    }

    /**
     * 导出全所电子归档列表
     */
    @PreAuthorize("@ss.hasPermi('record:document:export')")
    @Log(title = "全所电子归档", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RecordDocument recordDocument)
    {
        List<RecordDocument> list = recordDocumentService.selectRecordDocumentList(recordDocument);
        ExcelUtil<RecordDocument> util = new ExcelUtil<RecordDocument>(RecordDocument.class);
        util.exportExcel(response, list, "全所电子归档数据");
    }

    /**
     * 获取全所电子归档详细信息
     */
    @PreAuthorize("@ss.hasPermi('record:document:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(recordDocumentService.selectRecordDocumentById(id));
    }

    /**
     * 新增全所电子归档
     */
    @PreAuthorize("@ss.hasPermi('record:document:add')")
    @Log(title = "全所电子归档", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RecordDocument recordDocument)
    {
        return toAjax(recordDocumentService.insertRecordDocument(recordDocument));
    }

    /**
     * 修改全所电子归档
     */
    @PreAuthorize("@ss.hasPermi('record:document:edit')")
    @Log(title = "全所电子归档", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RecordDocument recordDocument)
    {
        return toAjax(recordDocumentService.updateRecordDocument(recordDocument));
    }

    /**
     * 删除全所电子归档
     */
    @PreAuthorize("@ss.hasPermi('record:document:remove')")
    @Log(title = "全所电子归档", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(recordDocumentService.deleteRecordDocumentByIds(ids));
    }
}
