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
import com.ruoyi.system.domain.AdmNotice;
import com.ruoyi.system.service.IAdmNoticeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 公告通知Controller
 * 
 * @author ruoyi
 * @date 2022-09-15
 */
@RestController
@RequestMapping("/hr/notice")
public class AdmNoticeController extends BaseController
{
    @Autowired
    private IAdmNoticeService admNoticeService;

    /**
     * 查询公告通知列表
     */
    @PreAuthorize("@ss.hasPermi('hr:notice:list')")
    @GetMapping("/list")
    public TableDataInfo list(AdmNotice admNotice)
    {
        startPage();
        List<AdmNotice> list = admNoticeService.selectAdmNoticeList(admNotice);
        return getDataTable(list);
    }

    /**
     * 导出公告通知列表
     */
    @PreAuthorize("@ss.hasPermi('hr:notice:export')")
    @Log(title = "公告通知", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdmNotice admNotice)
    {
        List<AdmNotice> list = admNoticeService.selectAdmNoticeList(admNotice);
        ExcelUtil<AdmNotice> util = new ExcelUtil<AdmNotice>(AdmNotice.class);
        util.exportExcel(response, list, "公告通知数据");
    }

    /**
     * 获取公告通知详细信息
     */
    @PreAuthorize("@ss.hasPermi('hr:notice:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(admNoticeService.selectAdmNoticeById(id));
    }

    /**
     * 新增公告通知
     */
    @PreAuthorize("@ss.hasPermi('hr:notice:add')")
    @Log(title = "公告通知", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdmNotice admNotice)
    {
        return toAjax(admNoticeService.insertAdmNotice(admNotice));
    }

    /**
     * 修改公告通知
     */
    @PreAuthorize("@ss.hasPermi('hr:notice:edit')")
    @Log(title = "公告通知", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdmNotice admNotice)
    {
        return toAjax(admNoticeService.updateAdmNotice(admNotice));
    }

    /**
     * 删除公告通知
     */
    @PreAuthorize("@ss.hasPermi('hr:notice:remove')")
    @Log(title = "公告通知", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(admNoticeService.deleteAdmNoticeByIds(ids));
    }
}
