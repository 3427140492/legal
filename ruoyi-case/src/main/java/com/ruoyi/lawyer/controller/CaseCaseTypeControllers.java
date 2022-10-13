package com.ruoyi.lawyer.controller;

import java.util.List;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.lawyer.domain.CaseCaseTypes;
import com.ruoyi.lawyer.service.ICaseCaseTypeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 案件类型 Controller
 * 
 * @author ruoyi
 * @date 2022-09-20
 */
@RestController
@RequestMapping("/case/casetype")
public class CaseCaseTypeControllers extends BaseController
{
    @Autowired
    private ICaseCaseTypeService caseCaseTypeService;

    /**
     * 查询
     *
     * @param id 案件id
     * @return 案件类型对象
     */
    @PreAuthorize("@ss.hasPermi('system:type:list')")
    @GetMapping("/getCaseTypeMin/{id}")
    public AjaxResult getCaseTypeMin(@PathVariable("id") Long id)
    {
        return AjaxResult.success(caseCaseTypeService.getCaseTypeMin(id));
    }

    /**
     * 查询案件类型列表
     *
     * @param caseCaseType 案件类型
     * @return 案件类型集合
     */
    @PreAuthorize("@ss.hasPermi('system:type:list')")
    @GetMapping("/listCaseType")
    public TableDataInfo list(CaseCaseTypes caseCaseType)
    {
        List<CaseCaseTypes> list = caseCaseTypeService.selectCaseCaseTypeList(caseCaseType);
        return getDataTable(list);
    }

    /**
     * 查询案件类型子列表
     *
     * @param id 案件类型id
     * @return 案件类型集合
     */
    @PreAuthorize("@ss.hasPermi('system:type:query')")
    @GetMapping(value = "getType/{id}")
    public TableDataInfo getType(@PathVariable("id") Long id)
    {
        List<CaseCaseTypes> list = caseCaseTypeService.selectCaseCaseTypeListById(id);
        return getDataTable(list);
    }


}
