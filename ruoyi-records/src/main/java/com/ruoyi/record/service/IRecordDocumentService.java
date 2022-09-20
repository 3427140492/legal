package com.ruoyi.record.service;

import java.util.List;
import com.ruoyi.record.domain.RecordDocument;

/**
 * 全所电子归档Service接口
 * 
 * @author ruoyi
 * @date 2022-09-15
 */
public interface IRecordDocumentService 
{
    /**
     * 查询全所电子归档
     * 
     * @param id 全所电子归档主键
     * @return 全所电子归档
     */
    RecordDocument selectRecordDocumentById(Integer id);

    /**
     * 查询全所电子归档列表
     * 
     * @param recordDocument 全所电子归档
     * @return 全所电子归档集合
     */
    List<RecordDocument> selectRecordDocumentList(RecordDocument recordDocument);

    /**
     * 新增全所电子归档
     * 
     * @param recordDocument 全所电子归档
     * @return 结果
     */
    int insertRecordDocument(RecordDocument recordDocument);

    /**
     * 修改全所电子归档
     * 
     * @param recordDocument 全所电子归档
     * @return 结果
     */
    int updateRecordDocument(RecordDocument recordDocument);

    /**
     * 批量删除全所电子归档
     * 
     * @param ids 需要删除的全所电子归档主键集合
     * @return 结果
     */
    int deleteRecordDocumentByIds(Integer[] ids);

    /**
     * 删除全所电子归档信息
     * 
     * @param id 全所电子归档主键
     * @return 结果
     */
    int deleteRecordDocumentById(Integer id);
}
