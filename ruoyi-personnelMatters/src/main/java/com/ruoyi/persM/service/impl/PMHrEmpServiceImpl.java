package com.ruoyi.persM.service.impl;

import java.util.List;

import com.ruoyi.persM.domain.Education;
import com.ruoyi.persM.domain.WorkExperience;
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
        int num = 0;
        if(rows > 0){
            HrLawyerIdentity hr = new HrLawyerIdentity();
            hr.setHrEmpId(pMHrEmp.getId());
            hr.setIdentityName(pMHrEmp.getIdentityName());
            num = pMHrEmpMapper.insertHrLawyerIdentity(hr);
        }
        return num;
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
        //pMHrEmpMapper.deleteHrLawyerIdentityByHrEmpId(pMHrEmp.getId());
        //insertHrLawyerIdentity(pMHrEmp);
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
     * 查询人事档案
     *
     * @param id 人事档案主键
     * @return 人事档案
     */
    @Override
    public List<WorkExperience> selectWorkById(Long id)
    {
        return pMHrEmpMapper.selectWorkById(id);
    }

    @Override
    public WorkExperience selectWorkExperienceById(Long id) {
        return pMHrEmpMapper.selectWorkExperienceById(id);
    }

    /**
     * 修改工作经历
     *
     * @param workExperience 工作经历
     * @return 结果
     */
    @Transactional
    @Override
    public int updateWorkExperience(WorkExperience workExperience)
    {
        return pMHrEmpMapper.updateWorkExperience(workExperience);
    }

    /**
     * 新增工作经历
     *
     * @param workExperience workExperience 工作经历
     * @return 结果
     */
    @Transactional
    @Override
    public int insertWorkExperience(WorkExperience workExperience)
    {
        return pMHrEmpMapper.insertWorkExperience(workExperience);
    }

    @Override
    public int deleteWorkExperienceByIds(Integer id) {
        return pMHrEmpMapper.deleteWorkExperienceByIds(id);
    }

    @Override
    public List<Education> selectEducationById(Long id) {
        return pMHrEmpMapper.selectEducationById(id);
    }
}
