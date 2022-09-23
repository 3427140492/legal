package com.ruoyi.finance.domain;

import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 全所收支明细对象 cw_inout
 * 
 * @author ruoyi
 * @date 2022-09-22
 */
public class FinCwInout extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 收支类别id */
    @Excel(name = "收支类别id")
    private Long cwInoutTypeId;

    /** 发生日期 */
    @Excel(name = "发生日期")
    private String cwInoutDate;

    /** 收支人 */
    @Excel(name = "收支人")
    private String cwInoutPerson;

    /** 收支金额 */
    @Excel(name = "收支金额")
    private Long cwInoutMoney;

    /** 支付方式 */
    @Excel(name = "支付方式")
    private Long cwPayType;

    /** 案件id */
    @Excel(name = "案件id")
    private Long caseLawId;

    /** 录入日期 */
    @Excel(name = "录入日期")
    private String cwEnteringdate;

    /** 备注 */
    @Excel(name = "备注")
    private String cwRemark;

    /** 付款人 */
    @Excel(name = "付款人")
    private String cwPayer;

    /** 类别名称 */
    @Excel(name = "类别名称")
    private String cwName;

    /** 案号 */
    @Excel(name = "案号")
    private String caseNo;

    /** 已付金额 */
    @Excel(name = "已付金额")
    private String casePaidsal;

    /** 提交人 */
    @Excel(name = "提交人")
    private String empName;

    /** $table.subTable.functionName信息 */
    private List<CwInoutType> cwInoutTypeList;

    public String getCwName() {
        return cwName;
    }

    public void setCwName(String cwName) {
        this.cwName = cwName;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getCasePaidsal() {
        return casePaidsal;
    }

    public void setCasePaidsal(String casePaidsal) {
        this.casePaidsal = casePaidsal;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setCwInoutTypeId(Long cwInoutTypeId) 
    {
        this.cwInoutTypeId = cwInoutTypeId;
    }

    public Long getCwInoutTypeId() 
    {
        return cwInoutTypeId;
    }
    public void setCwInoutDate(String cwInoutDate) 
    {
        this.cwInoutDate = cwInoutDate;
    }

    public String getCwInoutDate() 
    {
        return cwInoutDate;
    }
    public void setCwInoutPerson(String cwInoutPerson) 
    {
        this.cwInoutPerson = cwInoutPerson;
    }

    public String getCwInoutPerson() 
    {
        return cwInoutPerson;
    }
    public void setCwInoutMoney(Long cwInoutMoney) 
    {
        this.cwInoutMoney = cwInoutMoney;
    }

    public Long getCwInoutMoney() 
    {
        return cwInoutMoney;
    }
    public void setCwPayType(Long cwPayType) 
    {
        this.cwPayType = cwPayType;
    }

    public Long getCwPayType() 
    {
        return cwPayType;
    }
    public void setCaseLawId(Long caseLawId) 
    {
        this.caseLawId = caseLawId;
    }

    public Long getCaseLawId() 
    {
        return caseLawId;
    }
    public void setCwEnteringdate(String cwEnteringdate) 
    {
        this.cwEnteringdate = cwEnteringdate;
    }

    public String getCwEnteringdate() 
    {
        return cwEnteringdate;
    }
    public void setCwRemark(String cwRemark) 
    {
        this.cwRemark = cwRemark;
    }

    public String getCwRemark() 
    {
        return cwRemark;
    }
    public void setCwPayer(String cwPayer) 
    {
        this.cwPayer = cwPayer;
    }

    public String getCwPayer() 
    {
        return cwPayer;
    }

    public List<CwInoutType> getCwInoutTypeList()
    {
        return cwInoutTypeList;
    }

    public void setCwInoutTypeList(List<CwInoutType> cwInoutTypeList)
    {
        this.cwInoutTypeList = cwInoutTypeList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("cwInoutTypeId", getCwInoutTypeId())
            .append("cwInoutDate", getCwInoutDate())
            .append("cwInoutPerson", getCwInoutPerson())
            .append("cwInoutMoney", getCwInoutMoney())
            .append("cwPayType", getCwPayType())
            .append("caseLawId", getCaseLawId())
            .append("cwEnteringdate", getCwEnteringdate())
            .append("cwRemark", getCwRemark())
            .append("cwPayer", getCwPayer())
            .append("cwInoutTypeList", getCwInoutTypeList())
            .toString();
    }
}
