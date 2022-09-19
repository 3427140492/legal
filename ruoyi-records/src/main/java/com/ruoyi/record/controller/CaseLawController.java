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
import com.ruoyi.record.domain.CaseLaw;
import com.ruoyi.record.service.ICaseLawService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 归档Controller
 * 
 * @author ruoyi
 * @date 2022-09-15
 */
@RestController
@RequestMapping("/record/law")
public class CaseLawController extends BaseController
{
    @Autowired
    private ICaseLawService caseLawService;

    /**
     * 查询归档列表
     */
    @PreAuthorize("@ss.hasPermi('record:law:list')")
    @GetMapping("/selectCaseLawList")
    public TableDataInfo selectCaseLawList(CaseLaw caseLaw)
    {
        startPage();
        List<CaseLaw> list = caseLawService.selectCaseLawList(caseLaw);
        return getDataTable(list);
    }
}
