package com.ruoyi.finance.mapper;

import java.util.List;
import com.ruoyi.finance.domain.CwInvoice;
import com.ruoyi.finance.domain.FinCaseLaw;

/**
 * 我的发票Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-29
 */
public interface CwInvoiceMapper 
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
     * 删除我的发票
     * 
     * @param id 我的发票主键
     * @return 结果
     */
    public int deleteCwInvoiceById(Integer id);

    /**
     * 批量删除我的发票
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCwInvoiceByIds(Integer[] ids);

    /**
     * 批量删除归档
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCaseLawByIds(Integer[] ids);
    
    /**
     * 批量新增归档
     * 
     * @param caseLawList 归档列表
     * @return 结果
     */
    public int batchCaseLaw(List<FinCaseLaw> caseLawList);
    

    /**
     * 通过我的发票主键删除归档信息
     * 
     * @param id 我的发票ID
     * @return 结果
     */
    public int deleteCaseLawById(Integer id);
}
