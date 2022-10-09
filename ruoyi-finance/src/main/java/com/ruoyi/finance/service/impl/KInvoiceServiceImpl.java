package com.ruoyi.finance.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.finance.domain.FinCaseLaw;
import com.ruoyi.finance.mapper.KInvoiceMapper;
import com.ruoyi.finance.domain.CwInvoice;
import com.ruoyi.finance.service.IKInvoiceService;

/**
 * 开具发票Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-06
 */
@Service
public class KInvoiceServiceImpl implements IKInvoiceService 
{
    @Autowired
    private KInvoiceMapper kInvoiceMapper;

    /**
     * 查询开具发票
     * 
     * @param id 开具发票主键
     * @return 开具发票
     */
    @Override
    public CwInvoice selectKInvoiceById(Integer id)
    {
        return kInvoiceMapper.selectKInvoiceById(id);
    }

    /**
     * 查询开具发票列表
     * 
     * @param kInvoice 开具发票
     * @return 开具发票
     */
    @Override
    public List<CwInvoice> selectKInvoiceList(CwInvoice kInvoice)
    {
        return kInvoiceMapper.selectKInvoiceList(kInvoice);
    }

    //查询案件类型
    @Override
    public List<CwInvoice> selectCaseCaseTypelist(CwInvoice kInvoice) {
        return kInvoiceMapper.selectCaseCaseTypelist(kInvoice);
    }

    /**
     * 新增开具发票
     * 
     * @param kInvoice 开具发票
     * @return 结果
     */
    @Transactional
    @Override
    public int insertKInvoice(CwInvoice kInvoice)
    {
        int rows = kInvoiceMapper.insertKInvoice(kInvoice);
        insertCaseLaw(kInvoice);
        return rows;
    }

    /**
     * 修改开具发票
     * 
     * @param kInvoice 开具发票
     * @return 结果
     */
    @Transactional
    @Override
    public int updateKInvoice(CwInvoice kInvoice)
    {
        kInvoiceMapper.deleteCaseLawById(kInvoice.getId());
        insertCaseLaw(kInvoice);
        return kInvoiceMapper.updateKInvoice(kInvoice);
    }

    /**
     * 批量删除开具发票
     * 
     * @param ids 需要删除的开具发票主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteKInvoiceByIds(Integer[] ids)
    {
        kInvoiceMapper.deleteCaseLawByIds(ids);
        return kInvoiceMapper.deleteKInvoiceByIds(ids);
    }

    /**
     * 删除开具发票信息
     * 
     * @param id 开具发票主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteKInvoiceById(Integer id)
    {
        kInvoiceMapper.deleteCaseLawById(id);
        return kInvoiceMapper.deleteKInvoiceById(id);
    }

    /**
     * 新增审批中心信息
     * 
     * @param kInvoice 开具发票对象
     */
    public void insertCaseLaw(CwInvoice kInvoice)
    {
        List<FinCaseLaw> caseLawList = kInvoice.getCaseLawList();
        Integer id = kInvoice.getId();
        if (StringUtils.isNotNull(caseLawList))
        {
            List<FinCaseLaw> list = new ArrayList<FinCaseLaw>();
            for (FinCaseLaw caseLaw : caseLawList)
            {
                caseLaw.setId(id);
                list.add(caseLaw);
            }
            if (list.size() > 0)
            {
                kInvoiceMapper.batchCaseLaw(list);
            }
        }
    }
}
