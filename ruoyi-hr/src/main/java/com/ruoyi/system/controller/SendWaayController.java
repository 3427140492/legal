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
import com.ruoyi.system.domain.SendWaay;
import com.ruoyi.system.service.ISendWaayService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 发送Controller
 *
 * @author ruoyi
 * @date 2022-10-09
 */
@RestController
@RequestMapping("/hr/sendwaay")
public class SendWaayController extends BaseController
{
    @Autowired
    private ISendWaayService sendWaayService;

    /**
     * 查询发送列表
     */
    @PreAuthorize("@ss.hasPermi('hr:sendwaay:list')")
    @GetMapping("/list")
    public TableDataInfo list(SendWaay sendWaay)
    {
        startPage();
        List<SendWaay> list = sendWaayService.selectSendWaayList(sendWaay);
        return getDataTable(list);
    }

    /**
     * 导出发送列表
     */
    @PreAuthorize("@ss.hasPermi('hr:sendwaay:export')")
    @Log(title = "发送", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SendWaay sendWaay)
    {
        List<SendWaay> list = sendWaayService.selectSendWaayList(sendWaay);
        ExcelUtil<SendWaay> util = new ExcelUtil<SendWaay>(SendWaay.class);
        util.exportExcel(response, list, "发送数据");
    }

    /**
     * 获取发送详细信息
     */
    @PreAuthorize("@ss.hasPermi('hr:sendwaay:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(sendWaayService.selectSendWaayById(id));
    }

    /**
     * 新增发送
     */
    @PreAuthorize("@ss.hasPermi('hr:sendwaay:add')")
    @Log(title = "发送", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SendWaay sendWaay)
    {
        return toAjax(sendWaayService.insertSendWaay(sendWaay));
    }

    /**
     * 修改发送
     */
    @PreAuthorize("@ss.hasPermi('hr:sendwaay:edit')")
    @Log(title = "发送", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SendWaay sendWaay)
    {
        return toAjax(sendWaayService.updateSendWaay(sendWaay));
    }

    /**
     * 删除发送
     */
    @PreAuthorize("@ss.hasPermi('hr:sendwaay:remove')")
    @Log(title = "发送", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(sendWaayService.deleteSendWaayByIds(ids));
    }
}
