package com.ruoyi.lawyer.controller;

import java.util.List;

import com.ruoyi.lawyer.domain.Clients;
import com.ruoyi.lawyer.service.IClientService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 客户信息Controller
 * 
 * @author ruoyi
 * @date 2022-09-20
 */
@RestController
@RequestMapping("/case/client")
public class ClientControllers extends BaseController {

    @Autowired
    private IClientService clientService;

    /**
     * 查询客户信息 (是否存在)
     *
     * @param cardnum 客户身份证号
     * @return 客户信息
     */
    @PreAuthorize("@ss.hasPermi('case:basisfun:newcase:listCaseTyp')")
    @GetMapping("/getClientByCardnum")
    public TableDataInfo getClientByCardnum(String cardnum) {
        List<Clients> list = clientService.getClientByCardnum(cardnum);
        return getDataTable(list);
    }

    /**
     * 新增客户信息
     *
     * @param client 客户信息
     * @return 结果
     */
    @PreAuthorize("@ss.hasPermi('system:client:add')")
    @Log(title = "客户信息", businessType = BusinessType.INSERT)
    @PostMapping("/addClient")
    public AjaxResult addClient(@RequestBody Clients client) {
        return toAjax(clientService.addClient(client));
    }

    /**
     * 修改客户信息
     */
    @PreAuthorize("@ss.hasPermi('system:client:edit')")
    @Log(title = "客户信息", businessType = BusinessType.UPDATE)
    @PutMapping("/editClient")
    public AjaxResult editClient(@RequestBody Clients client) {
        return toAjax(clientService.editClient(client));
    }


}