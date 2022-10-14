package com.ruoyi.lawyer.controller;

import java.util.List;
import com.ruoyi.lawyer.domain.SysUser2s;
import com.ruoyi.lawyer.service.ISysUserService2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户信息Controller
 * 
 * @author ruoyi
 * @date 2022-09-20
 */
@RestController
@RequestMapping("/case/sysuser")
public class SysCUserControllers extends BaseController {

    @Autowired
    private ISysUserService2 sysUserService;

    /**
     * 查询系统用户表列表 (所有律师信息)
     * @return 系统用户表集合
     */
    @PreAuthorize("@ss.hasPermi('case:basisfun:newcase:listCaseTyp')")
    @GetMapping("/selectSysUserByHrEmp")
    public TableDataInfo selectSysUserByHrEmp(){
        startPage();
        List<SysUser2s> list = sysUserService.selectSysUserByHrEmp();
        return getDataTable(list);
    }

}