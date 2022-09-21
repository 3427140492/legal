package com.ruoyi.record.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
