package com.ruoyi.persM.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.persM.domain.HrLawyerIdentity;
import com.ruoyi.persM.mapper.PMHrEmpMapper;
import com.ruoyi.persM.domain.PMHrEmp;
import com.ruoyi.persM.service.IPMHrEmpService;

/**
 * 人事档案Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-27
 */
@Service
public class PMHrEmpServiceImpl implements IPMHrEmpService 
{
    @Autowired
    private PMHrEmpMapper pMHrEmpMapper;

    /**
     * 查询人事档案
     * 
     * @param id 人事档案主键
     * @return 人事档案
     */
    @Override
    public PMHrEmp selectPMHrEmpById(Long id)
    {
        return pMHrEmpMapper.selectPMHrEmpById(id);
    }

    /**
     * 查询人事档案列表
     * 
     * @param pMHrEmp 人事档案
     * @return 人事档案
     */
    @Override
    public List<PMHrEmp> selectPMHrEmpList(PMHrEmp pMHrEmp)
    {
        return pMHrEmpMapper.selectPMHrEmpList(pMHrEmp);
    }

    /**
     * 新增人事档案
     * 
     * @param pMHrEmp 人事档案
     * @return 结果
     */
    @Transactional
    @Override
    public int insertPMHrEmp(PMHrEmp pMHrEmp)
    {
        int rows = pMHrEmpMapper.insertPMHrEmp(pMHrEmp);
        insertHrLawyerIdentity(pMHrEmp);
        return rows;
    }

    /**
     * 修改人事档案
     * 
     * @param pMHrEmp 人事档案
     * @return 结果
     */
    @Transactional
    @Override
    public int updatePMHrEmp(PMHrEmp pMHrEmp)
    {
        pMHrEmpMapper.deleteHrLawyerIdentityByHrEmpId(pMHrEmp.getId());
        insertHrLawyerIdentity(pMHrEmp);
        return pMHrEmpMapper.updatePMHrEmp(pMHrEmp);
    }

    /**
     * 批量删除人事档案
     * 
     * @param ids 需要删除的人事档案主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deletePMHrEmpByIds(Long[] ids)
    {
        pMHrEmpMapper.deleteHrLawyerIdentityByHrEmpIds(ids);
        return pMHrEmpMapper.deletePMHrEmpByIds(ids);
    }

    /**
     * 删除人事档案信息
     * 
     * @param id 人事档案主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deletePMHrEmpById(Long id)
    {
        pMHrEmpMapper.deleteHrLawyerIdentityByHrEmpId(id);
        return pMHrEmpMapper.deletePMHrEmpById(id);
    }

    /**
     * 新增${subTable.functionName}信息
     * 
     * @param pMHrEmp 人事档案对象
     */
    public void insertHrLawyerIdentity(PMHrEmp pMHrEmp)
    {
        List<HrLawyerIdentity> hrLawyerIdentityList = pMHrEmp.getHrLawyerIdentityList();
        Long id = pMHrEmp.getId();
        if (StringUtils.isNotNull(hrLawyerIdentityList))
        {
            List<HrLawyerIdentity> list = new ArrayList<HrLawyerIdentity>();
            for (HrLawyerIdentity hrLawyerIdentity : hrLawyerIdentityList)
            {
                hrLawyerIdentity.setHrEmpId(id);
                list.add(hrLawyerIdentity);
            }
            if (list.size() > 0)
            {
                pMHrEmpMapper.batchHrLawyerIdentity(list);
            }
        }
    }
}
