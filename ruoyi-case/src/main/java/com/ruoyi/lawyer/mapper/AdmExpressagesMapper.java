package com.ruoyi.lawyer.mapper;

import com.ruoyi.lawyer.domain.AdmExpressages;

/**
 * 快递业务Mapper接口
 * 
 * @author ck
 * @date 2022-09-27
 */
public interface AdmExpressagesMapper {

    /**
     * 新增快递业务
     *
     * @param admExpressage 快递业务
     * @return 结果
     */
    public int insertAdmExpressage(AdmExpressages admExpressage);

}