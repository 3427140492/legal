package com.ruoyi.record.controller;

import java.util.List;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.record.domain.RecordCaseLaw;
import com.ruoyi.record.service.IRecordCaseLawService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 归档Controller
 * 
 * @author ruoyi
 * @date 2022-09-15
 */
@RestController
@RequestMapping("/record/law")
public class RecordCaseLawController extends BaseController
{
    @Autowired
    private IRecordCaseLawService caseLawService;

    /**
     * 查询归档列表
     */
    @PreAuthorize("@ss.hasPermi('record:law:list')")
    @GetMapping("/selectCaseLawList")
    public TableDataInfo selectCaseLawList(RecordCaseLaw caseLaw)
    {
        startPage();
        List<RecordCaseLaw> list = caseLawService.selectCaseLawList(caseLaw);
        return getDataTable(list);
    }
    //查询借档人
    @GetMapping("/selectNameList")
    public TableDataInfo selectname(RecordCaseLaw caseLaw)
    {
        startPage();
        List<RecordCaseLaw> list = caseLawService.selectname(caseLaw);
        return getDataTable(list);
    }

    //查询文件夹
    @GetMapping("/selectfolderList")
    public TableDataInfo selectfolder(RecordCaseLaw caseLaw)
    {
        List<RecordCaseLaw> list = caseLawService.selectfolder(caseLaw);
        return getDataTable(list);
    }

    /**
     * 新增文件夹
     */
    @PreAuthorize("@ss.hasPermi('system:folder:add')")
    @Log(title = "文件夹", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RecordCaseLaw recordFolder)
    {
        return toAjax(caseLawService.insertRecordFolder(recordFolder));
    }
}
