package com.ruoyi.persM.controller;

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
import com.ruoyi.persM.domain.TeamVo;
import com.ruoyi.persM.service.ITeamVoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 团队Controller
 *
 * @author ruoyi
 * @date 2022-10-11
 */
@RestController
@RequestMapping("/team/team")
public class TeamVoController extends BaseController
{
    @Autowired
    private ITeamVoService teamVoService;

    /**
     * 查询团队列表
     */
    @PreAuthorize("@ss.hasPermi('team:team:list')")
    @GetMapping("/list")
    public TableDataInfo list(TeamVo teamVo)
    {
        startPage();
        List<TeamVo> list = teamVoService.selectTeamVoList(teamVo);
        return getDataTable(list);
    }

    /**
     * 导出团队列表
     */
    @PreAuthorize("@ss.hasPermi('team:team:export')")
    @Log(title = "团队", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TeamVo teamVo)
    {
        List<TeamVo> list = teamVoService.selectTeamVoList(teamVo);
        ExcelUtil<TeamVo> util = new ExcelUtil<TeamVo>(TeamVo.class);
        util.exportExcel(response, list, "团队数据");
    }

    /**
     * 获取团队详细信息
     */
    @PreAuthorize("@ss.hasPermi('team:team:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(teamVoService.selectTeamVoById(id));
    }

    /**
     * 新增团队
     */
    @PreAuthorize("@ss.hasPermi('team:team:add')")
    @Log(title = "团队", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TeamVo teamVo)
    {
        return toAjax(teamVoService.insertTeamVo(teamVo));
    }

    /**
     * 修改团队
     */
    @PreAuthorize("@ss.hasPermi('team:team:edit')")
    @Log(title = "团队", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TeamVo teamVo)
    {
        return toAjax(teamVoService.updateTeamVo(teamVo));
    }

    /**
     * 删除团队
     */
    @PreAuthorize("@ss.hasPermi('team:team:remove')")
    @Log(title = "团队", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(teamVoService.deleteTeamVoByIds(ids));
    }
}
