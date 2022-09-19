package com.ruoyi.statistics.mapper;

import java.util.List;
import com.ruoyi.statistics.domain.CaseLaw;
import com.ruoyi.statistics.domain.HrEmp;

/**
 * 收结案统计Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-15
 */
public interface CaseLawMapper 
{
    /**
     * 查询收结案统计
     * 
     * @param id 收结案统计主键
     * @return 收结案统计
     */
    public CaseLaw selectCaseLawById(Integer id);

    /**
     * 查询收结案统计列表
     * 
     * @param caseLaw 收结案统计
     * @return 收结案统计集合
     */
    public List<CaseLaw> selectCaseLawList(CaseLaw caseLaw);

    /**
     * 新增收结案统计
     * 
     * @param caseLaw 收结案统计
     * @return 结果
     */
    public int insertCaseLaw(CaseLaw caseLaw);

    /**
     * 修改收结案统计
     * 
     * @param caseLaw 收结案统计
     * @return 结果
     */
    public int updateCaseLaw(CaseLaw caseLaw);

    /**
     * 删除收结案统计
     * 
     * @param id 收结案统计主键
     * @return 结果
     */
    public int deleteCaseLawById(Integer id);

    /**
     * 批量删除收结案统计
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCaseLawByIds(Integer[] ids);

    /**
     * 批量删除${subTable.functionName}
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHrEmpByIds(Integer[] ids);
    
    /**
     * 批量新增${subTable.functionName}
     * 
     * @param hrEmpList ${subTable.functionName}列表
     * @return 结果
     */
    public int batchHrEmp(List<HrEmp> hrEmpList);
    

    /**
     * 通过收结案统计主键删除${subTable.functionName}信息
     * 
     * @param id 收结案统计ID
     * @return 结果
     */
    public int deleteHrEmpById(Integer id);
}
