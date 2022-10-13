package com.ruoyi.lawyer.mapper;


import com.ruoyi.lawyer.domain.CaseLaws;
import java.util.List;

/**
 * 案件法律信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-09-04
 */
public interface CaseLawsMapper {

    /**
     * 查询 咨询与代理 列表
     *
     * @param caseLaw 查询参数
     * @return 咨询与代理 列表
     */
    public List<CaseLaws> consultagency(CaseLaws caseLaw);

    /**
     * 查询 回收案件 列表
     *
     * @param caseLaw 查询参数
     * @return 回收案件 列表
     */
    List<CaseLaws> selectCaseRecycleList(CaseLaws caseLaw);


    /**
     * 查询 我的案件汇总 列表
     *
     * @param caseLaw 查询参数
     * @return 我的个人案件列表
     */
    public List<CaseLaws> myCaseSummary(CaseLaws caseLaw);

    /**
     * 查询 我的待收款案件 年份
     *
     * @return 年份集合
     */
    public List<CaseLaws> myStayYear();

    /**
     * 查询 我的待收款案件 列表
     *
     * @param caseLaw 查询参数
     * @return 我的个人案件列表
     */
    public List<CaseLaws> myStayProceedsCase(CaseLaws caseLaw);

    /**
     * 查询 共享案件 列表
     *
     * @param caseLaw 查询参数
     * @return 我的个人案件列表
     */
    public List<CaseLaws> shareTheCaseList(CaseLaws caseLaw);


    /**
     * 查询 我的合办案件 列表
     *
     * @param caseLaw 查询参数
     * @return 我的个人案件列表
     */
    public List<CaseLaws> selectMyCaseLawLists(CaseLaws caseLaw);

    /**
     * 查询案件信息
     *
     * @param id 案件信息主键
     * @return 案件信息
     */
    public CaseLaws selectCaseLawById(Integer id);

    /**
     * 案件列表 (代开发票)
     *
     * @param caseLaw 查询参数
     * @return 结果
     */
    List<CaseLaws> selectInvoiceCaseLawList(CaseLaws caseLaw);

    /**
     * 修改案件信息
     *
     * @param caseLaw 案件信息
     * @return 结果
     */
    public int updateCaseLaw(CaseLaws caseLaw);

    /**
     * 获取案件信息详细信息 (添加收款记录)
     *
     * @param id 案件信息主键
     * @return 案件信息
     */
    public CaseLaws getCaseLawInfo(String id);

    /**
     * 查询案件id
     *
     * @return 案件法律id
     */
    public CaseLaws getCLAndDecById(Long id);

    /**
     * 查询案件id
     *
     * @return 案件法律id
     */
    public List<CaseLaws> getCaseLawId();

    /**
     * 案件法律信息列表 (条件查询)
     *
     * @param caseLaw 参数
     * @return 案件法律信息集合
     */
    public List<CaseLaws> selectCaseLawParamList(CaseLaws caseLaw);

    /**
     * 新增案件法律信息
     *
     * @param caseLaw 案件法律信息
     * @return 结果
     */
    public int addCaseLaw(CaseLaws caseLaw);


    /**
     * 查询 我的个人案件 列表
     *
     * @param caseLaw 查询参数
     * @return 我的个人案件列表
     */
    public List<CaseLaws> selectMyCaseLawList(CaseLaws caseLaw);


}