package com.ruoyi.statistics.controller;

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
import com.ruoyi.statistics.domain.Seal;
import com.ruoyi.statistics.service.ISealService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用印统计Controller
 * 
 * @author ruoyi
 * @date 2022-09-21
 */
@RestController
@RequestMapping("/Seal/withTheSeal")
public class SealController extends BaseController
{
    @Autowired
    private ISealService sealService;

    /**
     * 查询用印统计列表
     */
    @PreAuthorize("@ss.hasPermi('Seal:withTheSeal:list')")
    @GetMapping("/list")
    public TableDataInfo list(Seal seal)
    {
        startPage();
        List<Seal> list = sealService.selectSealList(seal);
        return getDataTable(list);
    }

    /**
     * 导出用印统计列表
     */
    @PreAuthorize("@ss.hasPermi('Seal:withTheSeal:export')")
    @Log(title = "用印统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Seal seal)
    {
        List<Seal> list = sealService.selectSealList(seal);
        ExcelUtil<Seal> util = new ExcelUtil<Seal>(Seal.class);
        util.exportExcel(response, list, "用印统计数据");
    }

    /**
     * 获取用印统计详细信息
     */
    @PreAuthorize("@ss.hasPermi('Seal:withTheSeal:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(sealService.selectSealById(id));
    }

}
