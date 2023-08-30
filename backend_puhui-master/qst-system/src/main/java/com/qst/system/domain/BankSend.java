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
 * 放款对象 bank_send
 *
 * @author qst
 * @date 2021-09-27
 */
@ApiModel("放款实体类")
public class BankSend extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("编号")
    private Long senId;

    /** 银行ID */
    @ApiModelProperty("银行ID")
    @Excel(name = "银行ID")
    private Long senBankId;

    /** 贷款ID */
    @ApiModelProperty("贷款ID")
    @Excel(name = "贷款ID")
    private Long senLoaId;

    /** 企业ID */
    @ApiModelProperty("企业ID")
    @Excel(name = "企业ID")
    private Long senComId;

    /** 放款账号 */
    @ApiModelProperty("放款账号")
    @Excel(name = "放款账号")
    private String senAccount;

    /** 放款用户姓名 */
    @ApiModelProperty("放款用户姓名")
    @Excel(name = "放款用户姓名")
    private String senUname;

    /** 放款用户证件号 */
    @ApiModelProperty("放款用户证件号")
    @Excel(name = "放款用户证件号")
    private String senUid;

    /** 放款金额 */
    @ApiModelProperty("放款金额")
    @Excel(name = "放款金额")
    private String senMoney;

    /** 放款时间 */
    @ApiModelProperty("放款时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "放款时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date senTime;

    /** 放款方式 */
    @ApiModelProperty("放款方式")
    @Excel(name = "放款方式")
    private String senMode;

    /** 放款状态 */
    @ApiModelProperty("放款状态")
    @Excel(name = "放款状态")
    private String senStatus;

    /** 删除标识 */
    @ApiModelProperty("删除标识")
    private String senDel;

    private Company company;

    private Loans loans;

    private Bank bank;

    public void setSenId(Long senId)
    {
        this.senId = senId;
    }

    public Long getSenId()
    {
        return senId;
    }
    public void setSenBankId(Long senBankId)
    {
        this.senBankId = senBankId;
    }

    public Long getSenBankId()
    {
        return senBankId;
    }
    public void setSenLoaId(Long senLoaId)
    {
        this.senLoaId = senLoaId;
    }

    public Long getSenLoaId()
    {
        return senLoaId;
    }
    public void setSenComId(Long senComId)
    {
        this.senComId = senComId;
    }

    public Long getSenComId()
    {
        return senComId;
    }
    public void setSenUname(String senUname)
    {
        this.senUname = senUname;
    }

    public String getSenUname()
    {
        return senUname;
    }
    public void setSenUid(String senUid)
    {
        this.senUid = senUid;
    }

    public String getSenUid()
    {
        return senUid;
    }
    public void setSenMoney(String senMoney)
    {
        this.senMoney = senMoney;
    }

    public String getSenMoney()
    {
        return senMoney;
    }
    public void setSenTime(Date senTime)
    {
        this.senTime = senTime;
    }

    public Date getSenTime()
    {
        return senTime;
    }
    public void setSenMode(String senMode)
    {
        this.senMode = senMode;
    }

    public String getSenMode()
    {
        return senMode;
    }
    public void setSenStatus(String senStatus)
    {
        this.senStatus = senStatus;
    }

    public String getSenStatus()
    {
        return senStatus;
    }
    public void setSenDel(String senDel)
    {
        this.senDel = senDel;
    }

    public String getSenDel()
    {
        return senDel;
    }

    public String getSenAccount() {
        return senAccount;
    }

    public void setSenAccount(String senAccount) {
        this.senAccount = senAccount;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Loans getLoans() {
        return loans;
    }

    public void setLoans(Loans loans) {
        this.loans = loans;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("senId", getSenId())
                .append("senBankId", getSenBankId())
                .append("senLoaId", getSenLoaId())
                .append("senComId", getSenComId())
                .append("senAccount", getSenAccount())
                .append("senUname", getSenUname())
                .append("senUid", getSenUid())
                .append("senMoney", getSenMoney())
                .append("senTime", getSenTime())
                .append("senMode", getSenMode())
                .append("senStatus", getSenStatus())
                .append("senDel", getSenDel())
                .toString();
    }
}