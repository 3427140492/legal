package com.ruoyi.lawyer.mapper;


import com.ruoyi.lawyer.domain.Clients;
import java.util.List;

/**
 * 客户信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-04
 */
public interface ClientsMapper
{

    /*
    * 修改客户状态
    * */
    public int cuStatus(Integer caseNo);

    /**
     * 查询客户信息 (是否存在)
     *
     * @param cardnum 客户身份证号
     * @return 客户信息
     */
    public List<Clients> getClientByCardnum(String cardnum);


    /**
     * 新增客户信息
     *
     * @param client 客户信息
     * @return 结果
     */
    public int addClient(Clients client);

    /**
     * 修改客户信息
     *
     * @param client 客户信息
     * @return 结果
     */
    public int editClient(Clients client);


}
