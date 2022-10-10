package com.ruoyi.persM.service;

import java.util.List;
import com.ruoyi.persM.domain.PMHrEmp;
import com.ruoyi.persM.domain.WorkExperience;

/**
 * 人事档案Service接口
 *
 * @author ruoyi
 * @date 2022-09-27
 */
public interface IPMHrEmpService
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
     * 批量删除人事档案
     *
     * @param ids 需要删除的人事档案主键集合
     * @return 结果
     */
    public int deletePMHrEmpByIds(Long[] ids);

    /**
     * 删除人事档案信息
     *
     * @param id 人事档案主键
     * @return 结果
     */
    public int deletePMHrEmpById(Long id);

    /**
     * 查询工作经历
     *
     * @param id 人事档案主键
     * @return 人事档案
     */
    public List<WorkExperience> selectWorkById(Long id);

    /**
     * 查询工作经历
     *
     * @param id 人事档案主键
     * @return 人事档案
     */
    public WorkExperience selectWorkExperienceById(Long id);

    /**
     * 修改工作经历
     *
     * @param WorkExperience 工作经历
     * @return 结果
     */
    public int updateWorkExperience(WorkExperience workExperience);

    /**
     * 新增工作经历
     *
     * @param WorkExperience 人事档案
     * @return 结果
     */
    public int insertWorkExperience(WorkExperience workExperience);

    public int deleteWorkExperienceByIds(Integer id);
}
