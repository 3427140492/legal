package com.ruoyi.finance.service;

import java.util.List;
import com.ruoyi.finance.domain.CwInvoice;

/**
 * 我的发票Service接口
 * 
 * @author ruoyi
 * @date 2022-09-29
 */
public interface ICwInvoiceService 
{
    /**
     * 查询我的发票
     * 
     * @param id 我的发票主键
     * @return 我的发票
     */
    public CwInvoice selectCwInvoiceById(Integer id);

    /**
     * 查询我的发票列表
     * 
     * @param cwInvoice 我的发票
     * @return 我的发票集合
     */
    public List<CwInvoice> selectCwInvoiceList(CwInvoice cwInvoice);

    /**
     * 新增我的发票
     * 
     * @param cwInvoice 我的发票
     * @return 结果
     */
    public int insertCwInvoice(CwInvoice cwInvoice);

    /**
     * 修改我的发票
     * 
     * @param cwInvoice 我的发票
     * @return 结果
     */
    public int updateCwInvoice(CwInvoice cwInvoice);

    /**
     * 批量删除我的发票
     * 
     * @param ids 需要删除的我的发票主键集合
     * @return 结果
     */
    public int deleteCwInvoiceByIds(Integer[] ids);

    /**
     * 删除我的发票信息
     * 
     * @param id 我的发票主键
     * @return 结果
     */
    public int deleteCwInvoiceById(Integer id);
}
