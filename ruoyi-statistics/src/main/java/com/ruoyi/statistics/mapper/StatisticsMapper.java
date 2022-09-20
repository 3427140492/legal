package com.ruoyi.statistics.mapper;

import java.util.List;
import com.ruoyi.statistics.domain.CaseLaw;
import com.ruoyi.statistics.domain.HrEmp;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 收结案统计Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-15
 */
@Repository
@Mapper
public interface StatisticsMapper
{
    /**
     * 查询收结案统计
     * 
     * @param id 收结案统计主键
     * @return 收结案统计
     */
    CaseLaw selectCaseLawById(Integer id);

    /**
     * 查询收结案统计列表
     * 
     * @param caseLaw 收结案统计
     * @return 收结案统计集合
     */
    List<CaseLaw> selectCaseLawList(CaseLaw caseLaw);

    /**
     * 新增收结案统计
     * 
     * @param caseLaw 收结案统计
     * @return 结果
     */
    int insertCaseLaw(CaseLaw caseLaw);

    /**
     * 修改收结案统计
     * 
     * @param caseLaw 收结案统计
     * @return 结果
     */
    int updateCaseLaw(CaseLaw caseLaw);

    /**
     * 删除收结案统计
     * 
     * @param id 收结案统计主键
     * @return 结果
     */
    int deleteCaseLawById(Integer id);

    /**
     * 批量删除收结案统计
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteCaseLawByIds(Integer[] ids);

    /**
     * 批量删除${subTable.functionName}
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteHrEmpByIds(Integer[] ids);
    
    /**
     * 批量新增${subTable.functionName}
     * 
     * @param hrEmpList ${subTable.functionName}列表
     * @return 结果
     */
    int batchHrEmp(List<HrEmp> hrEmpList);
    

    /**
     * 通过收结案统计主键删除${subTable.functionName}信息
     * 
     * @param id 收结案统计ID
     * @return 结果
     */
    int deleteHrEmpById(Integer id);
}
