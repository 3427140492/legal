package com.ruoyi.statistics.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.statistics.domain.StatisticsCaseLaw;
import com.ruoyi.statistics.service.IStatisticsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 收结案统计Controller
 * 
 * @author ruoyi
 * @date 2022-09-15
 */
@RestController
@RequestMapping("/ruoyi-statistics/Closed")
public class StatCaseLawController extends BaseController
{
    @Autowired
    private IStatisticsService caseLawService;

    /**
     * 查询收结案统计列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-statistics:Closed:list')")
    @GetMapping("/list")
    public TableDataInfo list(StatisticsCaseLaw caseLaw)
    {
        startPage();
        List<StatisticsCaseLaw> list = caseLawService.selectCaseLawList(caseLaw);
        return getDataTable(list);
    }

    /**
     * 导出收结案统计列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-statistics:Closed:export')")
    @Log(title = "收结案统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StatisticsCaseLaw caseLaw)
    {
        List<StatisticsCaseLaw> list = caseLawService.selectCaseLawList(caseLaw);
        ExcelUtil<StatisticsCaseLaw> util = new ExcelUtil<StatisticsCaseLaw>(StatisticsCaseLaw.class);
        util.exportExcel(response, list, "收结案统计数据");
    }

    /**
     * 获取收结案统计详细信息
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-statistics:Closed:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(caseLawService.selectCaseLawById(id));
    }
}
