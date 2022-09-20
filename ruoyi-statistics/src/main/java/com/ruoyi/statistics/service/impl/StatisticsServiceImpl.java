package com.ruoyi.statistics.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.statistics.domain.HrEmp;
import com.ruoyi.statistics.mapper.StatisticsMapper;
import com.ruoyi.statistics.domain.CaseLaw;
import com.ruoyi.statistics.service.IStatisticsService;

/**
 * 收结案统计Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-15
 */
@Service
public class StatisticsServiceImpl implements IStatisticsService
{
    @Autowired
    private StatisticsMapper statisticsMapper;

    /**
     * 查询收结案统计
     * 
     * @param id 收结案统计主键
     * @return 收结案统计
     */
    @Override
    public CaseLaw selectCaseLawById(Integer id)
    {
        return statisticsMapper.selectCaseLawById(id);
    }

    /**
     * 查询收结案统计列表
     * 
     * @param caseLaw 收结案统计
     * @return 收结案统计
     */
    @Override
    public List<CaseLaw> selectCaseLawList(CaseLaw caseLaw)
    {
        return statisticsMapper.selectCaseLawList(caseLaw);
    }

    /**
     * 新增收结案统计
     * 
     * @param caseLaw 收结案统计
     * @return 结果
     */
    @Transactional
    @Override
    public int insertCaseLaw(CaseLaw caseLaw)
    {
        int rows = statisticsMapper.insertCaseLaw(caseLaw);
        insertHrEmp(caseLaw);
        return rows;
    }

    /**
     * 修改收结案统计
     * 
     * @param caseLaw 收结案统计
     * @return 结果
     */
    @Transactional
    @Override
    public int updateCaseLaw(CaseLaw caseLaw)
    {
        statisticsMapper.deleteHrEmpById(caseLaw.getId());
        insertHrEmp(caseLaw);
        return statisticsMapper.updateCaseLaw(caseLaw);
    }

    /**
     * 批量删除收结案统计
     * 
     * @param ids 需要删除的收结案统计主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCaseLawByIds(Integer[] ids)
    {
        statisticsMapper.deleteHrEmpByIds(ids);
        return statisticsMapper.deleteCaseLawByIds(ids);
    }

    /**
     * 删除收结案统计信息
     * 
     * @param id 收结案统计主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteCaseLawById(Integer id)
    {
        statisticsMapper.deleteHrEmpById(id);
        return statisticsMapper.deleteCaseLawById(id);
    }

    /**
     * 新增${subTable.functionName}信息
     * 
     * @param caseLaw 收结案统计对象
     */
    public void insertHrEmp(CaseLaw caseLaw)
    {
        List<HrEmp> hrEmpList = caseLaw.getHrEmpList();
        Integer id = caseLaw.getId();
        if (StringUtils.isNotNull(hrEmpList))
        {
            List<HrEmp> list = new ArrayList<HrEmp>();
            for (HrEmp hrEmp : hrEmpList)
            {
                hrEmp.setId(id);
                list.add(hrEmp);
            }
            if (list.size() > 0)
            {
                statisticsMapper.batchHrEmp(list);
            }
        }
    }
}
