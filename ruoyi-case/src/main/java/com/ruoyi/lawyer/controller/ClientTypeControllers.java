package com.ruoyi.lawyer.controller;

import java.util.List;

import com.ruoyi.lawyer.domain.ClientTypes;
import com.ruoyi.lawyer.service.IClientTypeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 客户类型Controller
 * 
 * @author ruoyi
 * @date 2022-09-20
 */
@RestController
@RequestMapping("/case/clienttype")
public class ClientTypeControllers extends BaseController
{
    @Autowired
    private IClientTypeService clientTypeService;

    /**
     * 查询客户类型列表
     *
     * @param clientType 客户类型
     * @return 客户类型集合
     */
    @PreAuthorize("@ss.hasPermi('case:basisfun:newcase:listCaseTyp')")
    @GetMapping("/listClientType")
    public TableDataInfo listClientType(ClientTypes clientType)
    {
        List<ClientTypes> clientTypes = clientTypeService.listClientType(clientType);
        return getDataTable(clientTypes);
    }

}
