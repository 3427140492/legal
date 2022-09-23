package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.domain.SystemUser;
import com.ruoyi.system.mapper.AdmSigninrecorMapper;
import com.ruoyi.system.domain.AdmSigninrecor;
import com.ruoyi.system.service.IAdmSigninrecorService;

/**
 * 签到Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-22
 */
@Service
public class AdmSigninrecorServiceImpl implements IAdmSigninrecorService 
{
    @Autowired
    private AdmSigninrecorMapper admSigninrecorMapper;

    /**
     * 查询签到
     * 
     * @param id 签到主键
     * @return 签到
     */
    @Override
    public AdmSigninrecor selectAdmSigninrecorById(Integer id)
    {
        return admSigninrecorMapper.selectAdmSigninrecorById(id);
    }

    /**
     * 查询签到列表
     * 
     * @param admSigninrecor 签到
     * @return 签到
     */
    @Override
    public List<AdmSigninrecor> selectAdmSigninrecorList(AdmSigninrecor admSigninrecor)
    {
        return admSigninrecorMapper.selectAdmSigninrecorList(admSigninrecor);
    }

    /**
     * 新增签到
     * 
     * @param admSigninrecor 签到
     * @return 结果
     */
    @Transactional
    @Override
    public int insertAdmSigninrecor(AdmSigninrecor admSigninrecor)
    {
        int rows = admSigninrecorMapper.insertAdmSigninrecor(admSigninrecor);
        insertSystemUser(admSigninrecor);
        return rows;
    }

    /**
     * 修改签到
     * 
     * @param admSigninrecor 签到
     * @return 结果
     */
    @Transactional
    @Override
    public int updateAdmSigninrecor(AdmSigninrecor admSigninrecor)
    {
        admSigninrecorMapper.deleteSystemUserById(admSigninrecor.getId());
        insertSystemUser(admSigninrecor);
        return admSigninrecorMapper.updateAdmSigninrecor(admSigninrecor);
    }

    /**
     * 批量删除签到
     * 
     * @param ids 需要删除的签到主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteAdmSigninrecorByIds(Integer[] ids)
    {
        admSigninrecorMapper.deleteSystemUserByIds(ids);
        return admSigninrecorMapper.deleteAdmSigninrecorByIds(ids);
    }

    /**
     * 删除签到信息
     * 
     * @param id 签到主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteAdmSigninrecorById(Integer id)
    {
        admSigninrecorMapper.deleteSystemUserById(id);
        return admSigninrecorMapper.deleteAdmSigninrecorById(id);
    }

    /**
     * 新增系统用户信息
     * 
     * @param admSigninrecor 签到对象
     */
    public void insertSystemUser(AdmSigninrecor admSigninrecor)
    {
        List<SystemUser> systemUserList = admSigninrecor.getSystemUserList();
        Integer id = admSigninrecor.getId();
        if (StringUtils.isNotNull(systemUserList))
        {
            List<SystemUser> list = new ArrayList<SystemUser>();
            for (SystemUser systemUser : systemUserList)
            {
                systemUser.setId(id);
                list.add(systemUser);
            }
            if (list.size() > 0)
            {
                admSigninrecorMapper.batchSystemUser(list);
            }
        }
    }
}
