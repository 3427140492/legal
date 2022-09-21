package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.HrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.mapper.AdmComplainMapper;
import com.ruoyi.system.domain.AdmComplain;
import com.ruoyi.system.service.IAdmComplainService;

/**
 * 建议投诉Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-21
 */
@Service
public class AdmComplainServiceImpl implements IAdmComplainService 
{
    @Autowired
    private AdmComplainMapper admComplainMapper;

    /**
     * 查询建议投诉
     * 
     * @param id 建议投诉主键
     * @return 建议投诉
     */
    @Override
    public AdmComplain selectAdmComplainById(Integer id)
    {
        return admComplainMapper.selectAdmComplainById(id);
    }

    /**
     * 查询建议投诉列表
     * 
     * @param admComplain 建议投诉
     * @return 建议投诉
     */
    @Override
    public List<AdmComplain> selectAdmComplainList(AdmComplain admComplain)
    {
        return admComplainMapper.selectAdmComplainList(admComplain);
    }

    /**
     * 新增建议投诉
     * 
     * @param admComplain 建议投诉
     * @return 结果
     */
    @Transactional
    @Override
    public int insertAdmComplain(AdmComplain admComplain)
    {
        int rows = admComplainMapper.insertAdmComplain(admComplain);
        insertClient(admComplain);
        return rows;
    }

    /**
     * 修改建议投诉
     * 
     * @param admComplain 建议投诉
     * @return 结果
     */
    @Transactional
    @Override
    public int updateAdmComplain(AdmComplain admComplain)
    {
        admComplainMapper.deleteClientById(admComplain.getId());
        insertClient(admComplain);
        return admComplainMapper.updateAdmComplain(admComplain);
    }

    /**
     * 批量删除建议投诉
     * 
     * @param ids 需要删除的建议投诉主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteAdmComplainByIds(Integer[] ids)
    {
        admComplainMapper.deleteClientByIds(ids);
        return admComplainMapper.deleteAdmComplainByIds(ids);
    }

    /**
     * 删除建议投诉信息
     * 
     * @param id 建议投诉主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteAdmComplainById(Integer id)
    {
        admComplainMapper.deleteClientById(id);
        return admComplainMapper.deleteAdmComplainById(id);
    }

    /**
     * 新增客户信息
     * 
     * @param admComplain 建议投诉对象
     */
    public void insertClient(AdmComplain admComplain)
    {
        List<HrClient> hrClientList = admComplain.getClientList();
        Integer id = admComplain.getId();
        if (StringUtils.isNotNull(hrClientList))
        {
            List<HrClient> list = new ArrayList<HrClient>();
            for (HrClient hrClient : hrClientList)
            {
                hrClient.setId(id);
                list.add(hrClient);
            }
            if (list.size() > 0)
            {
                admComplainMapper.batchClient(list);
            }
        }
    }
}
