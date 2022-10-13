package com.ruoyi.lawyer.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.lawyer.domain.CwInoutTypes;
import com.ruoyi.lawyer.mapper.CwInoutTypesMapper;
import com.ruoyi.lawyer.service.ICwInoutTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 收支类别Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-09-24
 */
@Service
public class CwInoutTypeServiceImpls implements ICwInoutTypeService {

    @Autowired
    private CwInoutTypesMapper cwInoutTypeMapper;

    /**
     * 查询收支类别
     *
     * @param id 收支类别主键
     * @return 收支类别
     */
    @Override
    public List<CwInoutTypes> selectCwInoutTypeById(String id) {
        return cwInoutTypeMapper.selectCwInoutTypeById(id);
    }

    /**
     * 查询收支类别列表
     *
     * @param cwInoutType 收支类别
     * @return 收支类别
     */
    @Override
    public Object selectCwInoutTypeList(CwInoutTypes cwInoutType) {
        List<CwInoutTypes> treeList = cwInoutTypeMapper.selectCwInoutTypeList(cwInoutType);
        List<Map<String, Object>> list = new ArrayList<>();
        for (CwInoutTypes inoutType : treeList) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("id",inoutType.getId());
            map.put("cwName",inoutType.getCwName());
//            map.put("cwPid",inoutType.getCwPid());
            map.put("children", getChildren(inoutType.getId()));
            list.add(map);
        }
        return list;
    }

    public List<Object> getChildren(String id) {
        List<Object> list = new ArrayList<>();
        List<CwInoutTypes> treeList = cwInoutTypeMapper.selectCwInoutTypeById(id);
        for (CwInoutTypes cwInoutType : treeList) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("id",cwInoutType.getId());
            map.put("cwName",cwInoutType.getCwName());
            map.put("cwPid",cwInoutType.getCwPid());
            map.put("children", getChildren(cwInoutType.getId()));
            list.add(map);
        }
        return list;
    }

}