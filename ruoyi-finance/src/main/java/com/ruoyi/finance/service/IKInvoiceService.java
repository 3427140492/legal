package com.ruoyi.finance.service;

import java.util.List;
import com.ruoyi.finance.domain.CwInvoice;

/**
 * 开具发票Service接口
 * 
 * @author ruoyi
 * @date 2022-10-06
 */
public interface IKInvoiceService 
{
    /**
     * 查询开具发票
     * 
     * @param id 开具发票主键
     * @return 开具发票
     */
    CwInvoice selectKInvoiceById(Integer id);

    /**
     * 查询开具发票列表
     * 
     * @param kInvoice 开具发票
     * @return 开具发票集合
     */
    List<CwInvoice> selectKInvoiceList(CwInvoice kInvoice);

    //查询案件类型
    public List<CwInvoice> selectCaseCaseTypelist(CwInvoice kInvoice);

    /**
     * 新增开具发票
     * 
     * @param kInvoice 开具发票
     * @return 结果
     */
    int insertKInvoice(CwInvoice kInvoice);

    /**
     * 修改开具发票
     * 
     * @param kInvoice 开具发票
     * @return 结果
     */
    int updateKInvoice(CwInvoice kInvoice);

    /**
     * 批量删除开具发票
     * 
     * @param ids 需要删除的开具发票主键集合
     * @return 结果
     */
    int deleteKInvoiceByIds(Integer[] ids);

    /**
     * 删除开具发票信息
     * 
     * @param id 开具发票主键
     * @return 结果
     */
    int deleteKInvoiceById(Integer id);
}
