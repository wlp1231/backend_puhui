package com.jd.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jd.bean.vo.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 还款对象 refund
 *
 * @author Yushun Xiang
 * @date 2021-09-27
 */
public class Refund extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long refId;

    /** 企业编号 */
//    @Excel(name = "企业编号")
    private String refComNo;

    /** 银行id */
//    @Excel(name = "银行id")
    private Long refBankId;

    /** 贷款ID */
//    @Excel(name = "贷款ID")
    private Long refLoId;

    /** 逾期ID */
//    @Excel(name = "逾期ID")
    private Long refOveId;

    /** 还款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @Excel(name = "还款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date refTime;

    /** 还款金额 */
//    @Excel(name = "还款金额")
    private Long refMoney;

    /** 还款方式 */
//    @Excel(name = "还款方式")
    private String refMode;

    /** 还款状态 */
//    @Excel(name = "还款状态")
    private String refState;

    /** 删除标识 0 正常 2 删除 */
    private String refDel;

    private String loaNo;

    private String bankName;

    private Date loaEndTime;

    /** 实际还款时间 */
    private Date refActTime;

    public Date getRefActTime() {
        return refActTime;
    }

    public void setRefActTime(Date refActTime) {
        this.refActTime = refActTime;
    }

    public void setRefId(Long refId)
    {
        this.refId = refId;
    }

    public Long getRefId()
    {
        return refId;
    }
    public void setRefComNo(String refComNo)
    {
        this.refComNo = refComNo;
    }

    public String getRefComNo()
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

    public String getLoaNo() {
        return loaNo;
    }

    public void setLoaNo(String loaNo) {
        this.loaNo = loaNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Date getLoaEndTime() {
        return loaEndTime;
    }

    public void setLoaEndTime(Date loaEndTime) {
        this.loaEndTime = loaEndTime;
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
                .append("bankName", getBankName())
                .append("loaEndTime", getLoaEndTime())
                .toString();
    }
}