package com.ruoyi.lawyer.service;

import com.ruoyi.lawyer.domain.AdmExpressages;

/**
 * 快递业务Service接口
 * 
 * @author ck
 * @date 2022-09-27
 */
public interface IAdmExpressageService {

    /**
     * 新增快递业务
     *
     * @param admExpressage 快递业务
     * @return 结果
     */
    public int insertAdmExpressage(AdmExpressages admExpressage);

}