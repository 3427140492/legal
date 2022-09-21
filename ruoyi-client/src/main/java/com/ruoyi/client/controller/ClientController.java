package com.ruoyi.client.controller;

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
import com.ruoyi.client.domain.Client;
import com.ruoyi.client.service.IClientService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 客户管理Controller
 * 
 * @author gk
 * @date 2022-09-14
 */
@RestController
@RequestMapping("/client/client")
public class ClientController extends BaseController
{
    @Autowired
    private IClientService clientService;

    /**
     * 查询客户管理列表
     */
    @PreAuthorize("@ss.hasPermi('client:client:list')")
    @GetMapping("/list")
    public TableDataInfo list(Client client)
    {
        startPage();
        List<Client> list = clientService.selectClientList(client);
        return getDataTable(list);
    }

    /**
     * 导出客户管理列表
     */
    @PreAuthorize("@ss.hasPermi('client:client:export')")
    @Log(title = "客户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Client client)
    {
        List<Client> list = clientService.selectClientList(client);
        ExcelUtil<Client> util = new ExcelUtil<Client>(Client.class);
        util.exportExcel(response, list, "客户管理数据");
    }

    /**
     * 获取客户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('client:client:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(clientService.selectClientById(id));
    }

    /**
     * 新增客户管理
     */
    @PreAuthorize("@ss.hasPermi('client:client:add')")
    @Log(title = "客户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Client client)
    {
        return toAjax(clientService.insertClient(client));
    }

    /**
     * 修改客户管理
     */
    @PreAuthorize("@ss.hasPermi('client:client:edit')")
    @Log(title = "客户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Client client)
    {
        return toAjax(clientService.updateClient(client));
    }

    /**
     * 删除客户管理
     */
    @PreAuthorize("@ss.hasPermi('client:client:remove')")
    @Log(title = "客户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(clientService.deleteClientByIds(ids));
    }
}
