package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.HrCaseLawMapper;
import com.ruoyi.system.domain.HrCaseLaw;
import com.ruoyi.system.service.IHrCaseLawService;

/**
 * 案件法律Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-10-11
 */
@Service
public class HrCaseLawServiceImpl implements IHrCaseLawService 
{
    @Autowired
    private HrCaseLawMapper hrCaseLawMapper;

    /**
     * 查询案件法律
     * 
     * @param id 案件法律主键
     * @return 案件法律
     */
    @Override
    public HrCaseLaw selectHrCaseLawById(Integer id)
    {
        return hrCaseLawMapper.selectHrCaseLawById(id);
    }

    /**
     * 查询案件法律列表
     * 
     * @param hrCaseLaw 案件法律
     * @return 案件法律
     */
    @Override
    public List<HrCaseLaw> selectHrCaseLawList(HrCaseLaw hrCaseLaw)
    {
        return hrCaseLawMapper.selectHrCaseLawList(hrCaseLaw);
    }

    /**
     * 新增案件法律
     * 
     * @param hrCaseLaw 案件法律
     * @return 结果
     */
    @Override
    public int insertHrCaseLaw(HrCaseLaw hrCaseLaw)
    {
        return hrCaseLawMapper.insertHrCaseLaw(hrCaseLaw);
    }

    /**
     * 修改案件法律
     * 
     * @param hrCaseLaw 案件法律
     * @return 结果
     */
    @Override
    public int updateHrCaseLaw(HrCaseLaw hrCaseLaw)
    {
        return hrCaseLawMapper.updateHrCaseLaw(hrCaseLaw);
    }

    /**
     * 批量删除案件法律
     * 
     * @param ids 需要删除的案件法律主键
     * @return 结果
     */
    @Override
    public int deleteHrCaseLawByIds(Integer[] ids)
    {
        return hrCaseLawMapper.deleteHrCaseLawByIds(ids);
    }

    /**
     * 删除案件法律信息
     * 
     * @param id 案件法律主键
     * @return 结果
     */
    @Override
    public int deleteHrCaseLawById(Integer id)
    {
        return hrCaseLawMapper.deleteHrCaseLawById(id);
    }
}
