package com.ruoyi.lawyer.controller;

import java.util.List;

import com.ruoyi.lawyer.domain.CaseLaws;
import com.ruoyi.lawyer.service.ICaseLawService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 案件法律信息Controller
 * 
 * @author ruoyi
 * @date 2022-09-20
 */
@RestController
@RequestMapping("/case/caselaw")
public class CaseLawControllers extends BaseController {

    @Autowired
    private ICaseLawService caseLawService;


    /**
     * 查询 咨询与代理 列表
     *
     * @param caseLaw 查询参数
     * @return 咨询与代理 列表
     */
    @PreAuthorize("@ss.hasPermi('system:law:list')")
    @GetMapping("/consultagency")
    public TableDataInfo consultagency(CaseLaws caseLaw)
    {
        startPage();
        List<CaseLaws> list = caseLawService.consultagency(caseLaw);
        return getDataTable(list);
    }

    /**
     * 查询 回收案件 列表
     *
     * @param caseLaw 查询参数
     * @return 回收案件 列表
     */
    @PreAuthorize("@ss.hasPermi('system:law:list')")
    @GetMapping("/selectCaseRecycleList")
    public TableDataInfo selectCaseRecycleList(CaseLaws caseLaw)
    {
        startPage();
        List<CaseLaws> list = caseLawService.selectCaseRecycleList(caseLaw);
        return getDataTable(list);
    }


    /**
     * 结清 款项发票
     *
//     * @param list 案件参数
     */
    @PreAuthorize("@ss.hasPermi('system:law:list')")
//    @Log(title = "律师业绩", businessType = BusinessType.UPDATE)
    @PutMapping("/setPayment")
    public AjaxResult setPayment(@RequestBody String caseLaw)
    {
        System.out.println(caseLaw.toString());
        return toAjax(1);
//        return getDataTable(list);
    }

    /**
     * 查询 我的案件汇总 列表
     *
     * @param caseLaw 查询参数
     * @return 我的个人案件列表
     */
    @PreAuthorize("@ss.hasPermi('system:law:list')")
    @GetMapping("/myCaseSummary")
    public TableDataInfo myCaseSummary(CaseLaws caseLaw)
    {
        startPage();
        List<CaseLaws> list = caseLawService.myCaseSummary(caseLaw);
        return getDataTable(list);
    }

    /**
     * 查询 我的待收款案件 年份
     *
     * @return 年份集合
     */
    @PreAuthorize("@ss.hasPermi('system:law:list')")
    @GetMapping("/myStayYear")
    public TableDataInfo myStayYear()
    {
        List<CaseLaws> list = caseLawService.myStayYear();
        return getDataTable(list);
    }

    /**
     * 查询 我的待收款案件 列表
     *
     * @param caseLaw 查询参数
     * @return 我的个人案件列表
     */
    @PreAuthorize("@ss.hasPermi('system:law:list')")
    @GetMapping("/myStayProceedsCase")
    public TableDataInfo myStayProceedsCase(CaseLaws caseLaw)
    {
        startPage();
        List<CaseLaws> list = caseLawService.myStayProceedsCase(caseLaw);
        return getDataTable(list);
    }

    /**
     * 查询 共享案件 列表
     *
     * @param caseLaw 查询参数
     * @return 我的个人案件列表
     */
    @PreAuthorize("@ss.hasPermi('system:law:list')")
    @GetMapping("/shareTheCaseList")
    public TableDataInfo shareTheCaseList(CaseLaws caseLaw)
    {
        startPage();
        List<CaseLaws> list = caseLawService.shareTheCaseList(caseLaw);
        return getDataTable(list);
    }

    /**
     * 查询 我的合办案件 列表
     *
     * @param caseLaw 查询参数
     * @return 我的个人案件列表
     */
    @PreAuthorize("@ss.hasPermi('system:law:list')")
    @GetMapping("/selectMyCaseLawLists")
    public TableDataInfo selectMyCaseLawLists(CaseLaws caseLaw)
    {
        startPage();
        List<CaseLaws> list = caseLawService.selectMyCaseLawLists(caseLaw);
        return getDataTable(list);
    }

    /**
     * 查询案件信息
     *
     * @param id 案件信息主键
     * @return 案件信息
     */
    @PreAuthorize("@ss.hasPermi('system:law:query')")
    @GetMapping(value = "getInfo/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return AjaxResult.success(caseLawService.selectCaseLawById(id));
    }

    /**
     * 案件列表 (代开发票)
     *
     * @param caseLaw 查询参数
     * @return 结果
     */
    @PreAuthorize("@ss.hasPermi('system:law:query')")
    @GetMapping(value = "/selectInvoiceCaseLawList")
    public TableDataInfo selectInvoiceCaseLawList(CaseLaws caseLaw)
    {
        startPage();
        List<CaseLaws> list = caseLawService.selectInvoiceCaseLawList(caseLaw);
        return getDataTable(list);
    }


    /**
     * 修改结案信息
     *
     * @param caseLaw 案件法律信息
     * @return 结果
     */
    @PreAuthorize("@ss.hasPermi('system:law:edit')")
    @Log(title = "案件信息", businessType = BusinessType.UPDATE)
    @PutMapping("/LawEnd")
    public AjaxResult LawEnd(@RequestBody CaseLaws caseLaw)
    {
        return toAjax(caseLawService.LawEnd(caseLaw));
    }

    /**
     * 修改案件信息
     *
     * @param caseLaw 案件法律信息
     * @return 结果
     */
    @PreAuthorize("@ss.hasPermi('system:law:edit')")
    @Log(title = "案件信息", businessType = BusinessType.UPDATE)
    @PostMapping("/editCaseLaw")
    public AjaxResult editCaseLaw(@RequestBody CaseLaws caseLaw)
    {
        return toAjax(caseLawService.updateCaseLaw(caseLaw));
    }


    /**
     * 获取案件信息详细信息 (添加收款记录)
     *
     * @param id 案件信息主键
     * @return 案件信息
     */
    @PreAuthorize("@ss.hasPermi('system:law:query')")
    @GetMapping(value = "/getCaseLawInfo/{id}")
    public AjaxResult getCaseLawInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(caseLawService.getCaseLawInfo(id));
    }


    /**
     * 查询 我的个人案件 列表
     *
     * @param caseLaw 查询参数
     * @return 我的个人案件列表
     */
    @PreAuthorize("@ss.hasPermi('system:law:list')")
    @GetMapping("/selectMyCaseLawList")
    public TableDataInfo selectMyCaseLawList(CaseLaws caseLaw)
    {
        startPage();
        List<CaseLaws> list = caseLawService.selectMyCaseLawList(caseLaw);
        return getDataTable(list);
    }


    /**
     * 查询案件信息 (减免申请数据)
     *
     * @param id 案件id
     * @return 结果
     */
    @PreAuthorize("@ss.hasPermi('case:basisfun:newcase:listCaseTyp')")
    @GetMapping("/getCLAndDecById/{id}")
    public AjaxResult getCLAndDecById(@PathVariable("id") Long id) {
        return AjaxResult.success(caseLawService.getCLAndDecById(id));
    }


    /**
     * 案件法律信息列表 (条件查询)
     *
     * @param caseLaw 参数
     * @return 案件法律信息集合
     */
    @PreAuthorize("@ss.hasPermi('case:basisfun:newcase:listCaseTyp')")
    @GetMapping("/selectCaseLawParamsList")
    public TableDataInfo selectCaseLawParamsList(CaseLaws caseLaw) {
        List<CaseLaws> list = caseLawService.selectCaseLawParamList(caseLaw);
        return getDataTable(list);
    }

    /**
     * 查询案件id
     *
     * @return 案件法律信息集合
     */
    @PreAuthorize("@ss.hasPermi('case:basisfun:newcase:listCaseTyp')")
    @GetMapping("/getCaseLawId")
    public TableDataInfo getCaseLawId() {
        List<CaseLaws> list = caseLawService.getCaseLawId();
        return getDataTable(list);
    }

    /**
     * 新增案件法律信息
     *
     * @param caseLaw 案件法律信息
     * @return 结果
     */
    @PreAuthorize("@ss.hasPermi('system:client:add')")
    @Log(title = "增案件法律信息", businessType = BusinessType.INSERT)
    @PostMapping("/addCaseLaw")
    public AjaxResult addCaseLaw(@RequestBody CaseLaws caseLaw) {
        return toAjax(caseLawService.addCaseLaw(caseLaw));
    }

}
