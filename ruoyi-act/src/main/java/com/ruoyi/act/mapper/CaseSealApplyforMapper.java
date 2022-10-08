package com.ruoyi.act.mapper;

import java.util.List;
import com.ruoyi.act.domain.CaseSealApplyfor;
import com.ruoyi.act.domain.CaseFiles;
import com.ruoyi.act.domain.FileType;

/**
 * 用印申请Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-14
 */
public interface CaseSealApplyforMapper 
{
    /**
     * 查询用印申请
     * 
     * @param id 用印申请主键
     * @return 用印申请
     */
    public CaseSealApplyfor selectCaseSealApplyforById(Integer id);

    /**
     * 查询用印申请列表
     * 
     * @param caseSealApplyfor 用印申请
     * @return 用印申请集合
     */
    public List<CaseSealApplyfor> selectCaseSealApplyforList(CaseSealApplyfor caseSealApplyfor);

    /**
     * 查询用印申请列表下拉
     *
     * @return 用印申请下拉集合
     */
    public List<FileType> listApplyforxl(FileType  fileType);

    /**
     * 新增用印申请
     * 
     * @param caseSealApplyfor 用印申请
     * @return 结果
     */
    public int insertCaseSealApplyfor(CaseSealApplyfor caseSealApplyfor);

    /**
     * 修改用印申请
     * 
     * @param caseSealApplyfor 用印申请
     * @return 结果
     */
    public int updateCaseSealApplyfor(CaseSealApplyfor caseSealApplyfor);

    /**
     * 删除用印申请
     * 
     * @param id 用印申请主键
     * @return 结果
     */
    public int deleteCaseSealApplyforById(Integer id);

    /**
     * 批量删除用印申请
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCaseSealApplyforByIds(Integer[] ids);

    /**
     * 批量删除${subTable.functionName}
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCaseFilesByIds(Integer[] ids);
    
    /**
     * 批量新增${subTable.functionName}
     * 
     * @param caseFilesList ${subTable.functionName}列表
     * @return 结果
     */
    public int batchCaseFiles(List<CaseFiles> caseFilesList);
    

    /**
     * 通过用印申请主键删除${subTable.functionName}信息
     * 
     * @param id 用印申请ID
     * @return 结果
     */
    public int deleteCaseFilesById(Integer id);
}
