package com.ruoyi.persM.mapper;

import com.ruoyi.persM.domain.Education;
import com.ruoyi.persM.domain.HrLawyerIdentity;
import com.ruoyi.persM.domain.PMHrEmp;
import com.ruoyi.persM.domain.WorkExperience;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 人事档案Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-27
 */
@Mapper
public interface PMHrEmpMapper 
{
    /**
     * 查询人事档案
     * 
     * @param id 人事档案主键
     * @return 人事档案
     */
    public PMHrEmp selectPMHrEmpById(Long id);

    /**
     * 查询人事档案列表
     *
     * @param pMHrEmp 人事档案
     * @return 人事档案集合
     */
    public List<PMHrEmp> selectPMHrEmpList(PMHrEmp pMHrEmp);

    /**
     * 新增【请填写功能名称】
     *
     * @param hrLawyerIdentity 【请填写功能名称】
     * @return 结果
     */
    public int insertHrLawyerIdentity(HrLawyerIdentity hrLawyerIdentity);



    /**
     * 新增人事档案
     *
     * @param pMHrEmp 人事档案
     * @return 结果
     */
    public int insertPMHrEmp(PMHrEmp pMHrEmp);

    /**
     * 修改人事档案
     *
     * @param pMHrEmp 人事档案
     * @return 结果
     */
    public int updatePMHrEmp(PMHrEmp pMHrEmp);

    /**
     * 删除人事档案
     *
     * @param id 人事档案主键
     * @return 结果
     */
    public int deletePMHrEmpById(Long id);

    /**
     * 批量删除人事档案
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePMHrEmpByIds(Long[] ids);

    /**
     * 批量删除${subTable.functionName}
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHrLawyerIdentityByHrEmpIds(Long[] ids);



    /**
     * 通过人事档案主键删除${subTable.functionName}信息
     *
     * @param id 人事档案ID
     * @return 结果
     */
    public int deleteHrLawyerIdentityByHrEmpId(Long id);

    /**
     * 查询工作经历
     *
     * @param id 人事档案主键
     * @return 人事档案
     */
    public List<WorkExperience> selectWorkById(Long id);

    public WorkExperience selectWorkExperienceById(Long id);

    public int updateWorkExperience(WorkExperience workExperience);

    public int insertWorkExperience(WorkExperience workExperience);

    public int deleteWorkExperienceByIds(Integer id);

    public List<Education> selectEducationById(Long id);

    public List<PMHrEmp> selectPMHrEmpUserList();
}
