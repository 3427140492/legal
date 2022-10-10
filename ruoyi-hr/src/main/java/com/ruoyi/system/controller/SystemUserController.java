package com.ruoyi.system.controller;

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
import com.ruoyi.system.domain.SystemUser;
import com.ruoyi.system.service.ISystemUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 系统用户Controller
 * 
 * @author ruoyi
 * @date 2022-10-10
 */
@RestController
@RequestMapping("/hr/user")
public class SystemUserController extends BaseController
{
    @Autowired
    private ISystemUserService systemUserService;

    /**
     * 查询系统用户列表
     */
    @PreAuthorize("@ss.hasPermi('hr:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(SystemUser systemUser)
    {
        startPage();
        List<SystemUser> list = systemUserService.selectSystemUserList(systemUser);
        return getDataTable(list);
    }

    /**
     * 导出系统用户列表
     */
    @PreAuthorize("@ss.hasPermi('hr:user:export')")
    @Log(title = "系统用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SystemUser systemUser)
    {
        List<SystemUser> list = systemUserService.selectSystemUserList(systemUser);
        ExcelUtil<SystemUser> util = new ExcelUtil<SystemUser>(SystemUser.class);
        util.exportExcel(response, list, "系统用户数据");
    }

    /**
     * 获取系统用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('hr:user:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(systemUserService.selectSystemUserById(id));
    }

    /**
     * 新增系统用户
     */
    @PreAuthorize("@ss.hasPermi('hr:user:add')")
    @Log(title = "系统用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SystemUser systemUser)
    {
        return toAjax(systemUserService.insertSystemUser(systemUser));
    }

    /**
     * 修改系统用户
     */
    @PreAuthorize("@ss.hasPermi('hr:user:edit')")
    @Log(title = "系统用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SystemUser systemUser)
    {
        return toAjax(systemUserService.updateSystemUser(systemUser));
    }

    /**
     * 删除系统用户
     */
    @PreAuthorize("@ss.hasPermi('hr:user:remove')")
    @Log(title = "系统用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(systemUserService.deleteSystemUserByIds(ids));
    }
}
