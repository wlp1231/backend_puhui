package com.qst.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qst.common.annotation.Excel;
import com.qst.common.core.domain.BaseEntity;

/**
 * 还款对象 refund
 *
 * @author qst
 * @date 2021-09-27
 */
@ApiModel("还款实体类")
public class Refund extends BaseEntity
{
    private static final long serialVersionUID = 1L;



    // 2022.1.22 add ly ,增加 实际还款时间
    @ApiModelProperty("实际还款时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date refActTime; // 实际还款时间

    public Date getRefActTime() {
        return refActTime;
    }

    public void setRefActTime(Date refActTime) {
        this.refActTime = refActTime;
    }



    /** id */
    @ApiModelProperty("主键")
    private Long refId;

    /** 企业编号 */
    @ApiModelProperty("企业编号")
    @Excel(name = "企业编号")
    private Long refComNo;

    /** 银行id */
    @ApiModelProperty("银行id")
    @Excel(name = "银行id")
    private Long refBankId;

    /** 贷款ID */
    @ApiModelProperty("贷款ID")
    @Excel(name = "贷款ID")
    private Long refLoId;

    /** 逾期ID */
    @ApiModelProperty("逾期ID")
    @Excel(name = "逾期ID")
    private Long refOveId;

    /** 还款时间 */
    @ApiModelProperty("还款时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "还款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date refTime;

    /** 还款金额 */
    @ApiModelProperty("还款金额")
    @Excel(name = "还款金额")
    private Long refMoney;

    /** 还款方式 */
    @ApiModelProperty("还款方式")
    @Excel(name = "还款方式")
    private String refMode;

    /** 还款状态 */
    @ApiModelProperty("还款状态")
    @Excel(name = "还款状态")
    private String refState;

    /** 删除标识 0 正常 2 删除 */
    @ApiModelProperty("删除标识")
    private String refDel;

    private Company company;

    private Bank bank;

    private Loans loans;




    public void setRefId(Long refId)
    {
        this.refId = refId;
    }

    public Long getRefId()
    {
        return refId;
    }
    public void setRefComNo(Long refComNo)
    {
        this.refComNo = refComNo;
    }

    public Long getRefComNo()
    {
        return refComNo;
    }
    public void setRefBankId(Long refBankId)
    {
        this.refBankId = refBankId;
    }

    public Long getRefBankId()
    {
        return refBankId;
    }
    public void setRefLoId(Long refLoId)
    {
        this.refLoId = refLoId;
    }

    public Long getRefLoId()
    {
        return refLoId;
    }
    public void setRefOveId(Long refOveId)
    {
        this.refOveId = refOveId;
    }

    public Long getRefOveId()
    {
        return refOveId;
    }
    public void setRefTime(Date refTime)
    {
        this.refTime = refTime;
    }

    public Date getRefTime()
    {
        return refTime;
    }
    public void setRefMoney(Long refMoney)
    {
        this.refMoney = refMoney;
    }

    public Long getRefMoney()
    {
        return refMoney;
    }
    public void setRefMode(String refMode)
    {
        this.refMode = refMode;
    }

    public String getRefMode()
    {
        return refMode;
    }
    public void setRefState(String refState)
    {
        this.refState = refState;
    }

    public String getRefState()
    {
        return refState;
    }
    public void setRefDel(String refDel)
    {
        this.refDel = refDel;
    }

    public String getRefDel()
    {
        return refDel;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Loans getLoans() {
        return loans;
    }

    public void setLoans(Loans loans) {
        this.loans = loans;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("refId", getRefId())
                .append("refComNo", getRefComNo())
                .append("refBankId", getRefBankId())
                .append("refLoId", getRefLoId())
                .append("refOveId", getRefOveId())
                .append("refTime", getRefTime())
                .append("refMoney", getRefMoney())
                .append("refMode", getRefMode())
                .append("refState", getRefState())
                .append("refDel", getRefDel())
                .toString();
    }
}