package com.ruoyi.lawyer.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.lawyer.domain.CwInoutTypes;
import com.ruoyi.lawyer.service.ICwInoutTypeService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 收支类别Controller
 * 
 * @author ruoyi
 * @date 2022-09-24
 */
@RestController
@RequestMapping("/lawyer/cwinouttype")
public class CwInoutTypeControllers extends BaseController
{
    @Autowired
    private ICwInoutTypeService cwInoutTypeService;


    /**
     * 查询收支类别列表
     */
    @PreAuthorize("@ss.hasPermi('system:type:list')")
    @GetMapping("/list")
    public TableDataInfo list(CwInoutTypes cwInoutType)
    {
        List<CwInoutTypes> list = (List<CwInoutTypes>) cwInoutTypeService.selectCwInoutTypeList(cwInoutType);
        return getDataTable(list);
    }




    /**
     * 获取收支类别详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:type:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(cwInoutTypeService.selectCwInoutTypeById(id));
    }






}
