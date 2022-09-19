package com.ruoyi.record.mapper;

import java.util.List;
import com.ruoyi.record.domain.RecordDocument;
import com.ruoyi.record.domain.CaseLaw;

/**
 * 全所电子归档Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-15
 */
public interface RecordDocumentMapper 
{
    /**
     * 查询全所电子归档
     * 
     * @param id 全所电子归档主键
     * @return 全所电子归档
     */
    public RecordDocument selectRecordDocumentById(Integer id);

    /**
     * 查询全所电子归档列表
     * 
     * @param recordDocument 全所电子归档
     * @return 全所电子归档集合
     */
    public List<RecordDocument> selectRecordDocumentList(RecordDocument recordDocument);

    /**
     * 新增全所电子归档
     * 
     * @param recordDocument 全所电子归档
     * @return 结果
     */
    public int insertRecordDocument(RecordDocument recordDocument);

    /**
     * 修改全所电子归档
     * 
     * @param recordDocument 全所电子归档
     * @return 结果
     */
    public int updateRecordDocument(RecordDocument recordDocument);

    /**
     * 删除全所电子归档
     * 
     * @param id 全所电子归档主键
     * @return 结果
     */
    public int deleteRecordDocumentById(Integer id);

    /**
     * 批量删除全所电子归档
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRecordDocumentByIds(Integer[] ids);

    /**
     * 批量删除${subTable.functionName}
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCaseLawByIds(Integer[] ids);
    
    /**
     * 批量新增${subTable.functionName}
     * 
     * @param caseLawList ${subTable.functionName}列表
     * @return 结果
     */
    public int batchCaseLaw(List<CaseLaw> caseLawList);
    

    /**
     * 通过全所电子归档主键删除${subTable.functionName}信息
     * 
     * @param id 全所电子归档ID
     * @return 结果
     */
    public int deleteCaseLawById(Integer id);
}
