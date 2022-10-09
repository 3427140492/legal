package com.ruoyi.finance.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.finance.domain.FinCaseLaw;
import com.ruoyi.finance.mapper.CwInvoiceMapper;
import com.ruoyi.finance.domain.CwInvoice;
import com.ruoyi.finance.service.ICwInvoiceService;

/**
 * 我的发票Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-29
 */
@Service
public class CwInvoiceServiceImpl implements ICwInvoiceService 
{
    @Autowired
    private CwInvoiceMapper cwInvoiceMapper;

    /**
     * 查询我的发票
     * 
     * @param id 我的发票主键
     * @return 我的发票
     */
    @Override
    public CwInvoice selectCwInvoiceById(Integer id)
    {
        return cwInvoiceMapper.selectCwInvoiceById(id);
    }

    /**
     * 查询我的发票列表
     * 
     * @param cwInvoice 我的发票
     * @return 我的发票
     */
    @Override
    public List<CwInvoice> selectCwInvoiceList(CwInvoice cwInvoice)
    {
        return cwInvoiceMapper.selectCwInvoiceList(cwInvoice);
    }

    @Override
    public List<CwInvoice> selectCaseCaseTypelist(CwInvoice cwInvoice) {

        return cwInvoiceMapper.selectCaseCaseTypelist(cwInvoice);
    }

    /**
     * 新增我的发票
     * 
     * @param cwInvoice 我的发票
     * @return 结果
     */
    @Transactional
    @Override
    public int insertCwInvoice(CwInvoice cwInvoice)
    {
        int rows = cwInvoiceMapper.insertCwInvoice(cwInvoice);
        insertCaseLaw(cwInvoice);
        return rows;
    }

    /**
     * 修改我的发票
     * 
     * @param cwInvoice 我的发票
     * @return 结果
     */
    @Transactional
    @Override
    public int updateCwInvoice(CwInvoice cwInvoice)
    {
        cwInvoiceMapper.deleteCaseLawById(cwInvoice.getId());
        insertCaseLaw(cwInvoice);
        return cwInvoiceMapper.updateCwInvoice(cwInvoice);
    }

    /**
     * 批量删除我的发票
     * 
     * @param ids 需要删除的我的发票主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCwInvoiceByIds(Integer[] ids)
    {
        cwInvoiceMapper.deleteCaseLawByIds(ids);
        return cwInvoiceMapper.deleteCwInvoiceByIds(ids);
    }

    /**
     * 删除我的发票信息
     * 
     * @param id 我的发票主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCwInvoiceById(Integer id)
    {
        cwInvoiceMapper.deleteCaseLawById(id);
        return cwInvoiceMapper.deleteCwInvoiceById(id);
    }

    /**
     * 新增归档信息
     * 
     * @param cwInvoice 我的发票对象
     */
    public void insertCaseLaw(CwInvoice cwInvoice)
    {
        List<FinCaseLaw> caseLawList = cwInvoice.getCaseLawList();
        Integer id = cwInvoice.getId();
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
                cwInvoiceMapper.batchCaseLaw(list);
            }
        }
    }
}
