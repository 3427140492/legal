package com.ruoyi.record.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.record.domain.RecordCaseLaw;
import com.ruoyi.record.mapper.RecordDocumentMapper;
import com.ruoyi.record.domain.RecordDocument;
import com.ruoyi.record.service.IRecordDocumentService;

/**
 * 全所电子归档Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-15
 */
@Service
public class RecordDocumentServiceImpl implements IRecordDocumentService 
{
    @Autowired
    private RecordDocumentMapper recordDocumentMapper;

    /**
     * 查询全所电子归档
     * 
     * @param id 全所电子归档主键
     * @return 全所电子归档
     */
    @Override
    public RecordDocument selectRecordDocumentById(Integer id)
    {
        return recordDocumentMapper.selectRecordDocumentById(id);
    }

    /**
     * 查询全所电子归档列表
     * 
     * @param recordDocument 全所电子归档
     * @return 全所电子归档
     */
    @Override
    public List<RecordDocument> selectRecordDocumentList(RecordDocument recordDocument)
    {
        return recordDocumentMapper.selectRecordDocumentList(recordDocument);
    }

    /**
     * 新增全所电子归档
     * 
     * @param recordDocument 全所电子归档
     * @return 结果
     */
    @Transactional
    @Override
    public int insertRecordDocument(RecordDocument recordDocument)
    {
        int rows = recordDocumentMapper.insertRecordDocument(recordDocument);
        insertCaseLaw(recordDocument);
        return rows;
    }

    /**
     * 修改全所电子归档
     *
     * @param recordDocument 全所电子归档
     * @return 结果
     */
    @Transactional
    @Override
    public int updateRecordDocument(RecordDocument recordDocument)
    {
        recordDocumentMapper.deleteCaseLawById(recordDocument.getId());
        insertCaseLaw(recordDocument);
        return recordDocumentMapper.updateRecordDocument(recordDocument);
    }

    /**
     * 批量删除全所电子归档
     * 
     * @param ids 需要删除的全所电子归档主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteRecordDocumentByIds(Integer[] ids)
    {
        recordDocumentMapper.deleteCaseLawByIds(ids);
        return recordDocumentMapper.deleteRecordDocumentByIds(ids);
    }

    /**
     * 删除全所电子归档信息
     * 
     * @param id 全所电子归档主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteRecordDocumentById(Integer id)
    {
        recordDocumentMapper.deleteCaseLawById(id);
        return recordDocumentMapper.deleteRecordDocumentById(id);
    }

    /**
     * 新增${subTable.functionName}信息
     * 
     * @param recordDocument 全所电子归档对象
     */
    public void insertCaseLaw(RecordDocument recordDocument)
    {
        List<RecordCaseLaw> caseLawList = recordDocument.getCaseLawList();
        Integer id = recordDocument.getId();
        if (StringUtils.isNotNull(caseLawList))
        {
            List<RecordCaseLaw> list = new ArrayList<RecordCaseLaw>();
            for (RecordCaseLaw caseLaw : caseLawList)
            {
                caseLaw.setId(id);
                list.add(caseLaw);
            }
            if (list.size() > 0)
            {
                recordDocumentMapper.batchCaseLaw(list);
            }
        }
    }
}
