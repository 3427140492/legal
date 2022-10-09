package com.ruoyi.finance.mapper;

import java.util.List;
import com.ruoyi.finance.domain.CwInvoice;
import com.ruoyi.finance.domain.FinCaseLaw;

/**
 * 开具发票Mapper接口
 * 
 * @author ruoyi
 * @date 2022-10-06
 */
public interface KInvoiceMapper 
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
     * 删除开具发票
     * 
     * @param id 开具发票主键
     * @return 结果
     */
    int deleteKInvoiceById(Integer id);

    /**
     * 批量删除开具发票
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteKInvoiceByIds(Integer[] ids);

    /**
     * 批量删除审批中心
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteCaseLawByIds(Integer[] ids);
    
    /**
     * 批量新增审批中心
     * 
     * @param caseLawList 审批中心列表
     * @return 结果
     */
    int batchCaseLaw(List<FinCaseLaw> caseLawList);
    

    /**
     * 通过开具发票主键删除审批中心信息
     * 
     * @param id 开具发票ID
     * @return 结果
     */
    int deleteCaseLawById(Integer id);
}
