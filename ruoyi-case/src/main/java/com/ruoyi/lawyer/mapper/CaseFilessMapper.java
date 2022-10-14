package com.ruoyi.lawyer.mapper;

import java.util.List;
import com.ruoyi.lawyer.domain.CaseFiless;

/**
 * 案件文件Mapper接口
 * 
 * @author ruoyi
 * @date 2022-10-10
 */
public interface CaseFilessMapper
{
    /**
     * 查询案件文件
     * 
     * @param id 案件文件主键
     * @return 案件文件
     */
    public CaseFiless selectCaseFilesById(Long id);

    /**
     * 查询案件文件列表
     * 
     * @param caseFiles 案件文件
     * @return 案件文件集合
     */
    public List<CaseFiless> selectCaseFilesList(CaseFiless caseFiles);

    /**
     * 新增案件文件
     * 
     * @param caseFiles 案件文件
     * @return 结果
     */
    public int insertCaseFiles(CaseFiless caseFiles);

    /**
     * 修改案件文件
     * 
     * @param caseFiles 案件文件
     * @return 结果
     */
    public int updateCaseFiles(CaseFiless caseFiles);

    /**
     * 删除案件文件
     * 
     * @param id 案件文件主键
     * @return 结果
     */
    public int deleteCaseFilesById(Long id);

    /**
     * 批量删除案件文件
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCaseFilesByIds(Long[] ids);
}
