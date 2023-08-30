package com.jd.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 贷款对象 loans
 *
 * @author qst
 * @date 2021-09-23
 */
public class Loans{

    /** id */
    private Long loaId;

    /**贷款编号**/
//    @Excel(name = "贷款编号")
    private String loaNo;

    /** 企业编号 */
//    @Excel(name = "企业编号")
    private String loaComNo;

    /** 银行id */
//    @Excel(name = "银行id")
    private Long loaBankId;

    /** 材料ID */
//    @Excel(name = "材料ID")
    private Long loaLisId;

    /** 贷款用途 */
//    @Excel(name = "贷款用途")
    private String loaUse;

    /** 贷款积分 */
//    @Excel(name = "贷款积分")
    private String loaIntegral;

    /** 贷款利率 */
//    @Excel(name = "贷款利率")
    private String loaRate;

    /** 贷款状态 */
//    @Excel(name = "贷款状态")
    private String loaState;

    private String loaBank;

    /** 贷款时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @Excel(name = "贷款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date loaTime;

    /** 贷款金额 */
//    @Excel(name = "贷款金额")
    private Long loaMoney;

    /** 还款时间 */
//    @Excel(name = "还款时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loaEndTime;

    /**收款账户*/
    private String bAccountNo;

    /**收款人*/
    private String bName;

    /**开户行*/
    private String bBankName;

    /**身份证号*/
    private String bIdCard;

    public String getbAccountNo() {
        return bAccountNo;
    }

    public void setbAccountNo(String bAccountNo) {
        this.bAccountNo = bAccountNo;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getbBankName() {
        return bBankName;
    }

    public void setbBankName(String bBankName) {
        this.bBankName = bBankName;
    }

    public String getbIdCard() {
        return bIdCard;
    }

    public void setbIdCard(String bIdCard) {
        this.bIdCard = bIdCard;
    }



    /** 删除标识 0 正常 2 删除 */
    private String loaDel;

    private String exaName;

    private Date exaTime;

    private String exaRemark;

    public void setLoaId(Long loaId)
    {
        this.loaId = loaId;
    }

    public Long getLoaId()
    {
        return loaId;
    }
    public void setLoaComNo(String loaComNo)
    {
        this.loaComNo = loaComNo;
    }

    public String getLoaComNo()
    {
        return loaComNo;
    }
    public void setLoaBankId(Long loaBankId)
    {
        this.loaBankId = loaBankId;
    }

    public Long getLoaBankId()
    {
        return loaBankId;
    }
    public void setLoaLisId(Long loaLisId)
    {
        this.loaLisId = loaLisId;
    }

    public Long getLoaLisId()
    {
        return loaLisId;
    }
    public void setLoaUse(String loaUse)
    {
        this.loaUse = loaUse;
    }

    public String getLoaUse()
    {
        return loaUse;
    }
    public void setLoaIntegral(String loaIntegral)
    {
        this.loaIntegral = loaIntegral;
    }

    public String getLoaIntegral()
    {
        return loaIntegral;
    }
    public void setLoaRate(String loaRate)
    {
        this.loaRate = loaRate;
    }

    public String getLoaRate()
    {
        return loaRate;
    }
    public void setLoaState(String loaState)
    {
        this.loaState = loaState;
    }

    public String getLoaState()
    {
        return loaState;
    }
    public void setLoaTime(Date loaTime)
    {
        this.loaTime = loaTime;
    }

    public Date getLoaTime()
    {
        return loaTime;
    }
    public void setLoaMoney(Long loaMoney)
    {
        this.loaMoney = loaMoney;
    }

    public Long getLoaMoney()
    {
        return loaMoney;
    }
    public void setLoaDel(String loaDel)
    {
        this.loaDel = loaDel;
    }

    public String getLoaDel()
    {
        return loaDel;
    }

    public String getLoaNo() {
        return loaNo;
    }

    public void setLoaNo(String loaNo) {
        this.loaNo = loaNo;
    }

    public String getLoaBank() {
        return loaBank;
    }

    public void setLoaBank(String loaBank) {
        this.loaBank = loaBank;
    }

    public String getExaName() {
        return exaName;
    }

    public void setExaName(String exaName) {
        this.exaName = exaName;
    }

    public Date getExaTime() {
        return exaTime;
    }

    public void setExaTime(Date exaTime) {
        this.exaTime = exaTime;
    }

    public String getExaRemark() {
        return exaRemark;
    }

    public void setExaRemark(String exaRemark) {
        this.exaRemark = exaRemark;
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
                .append("loaId", getLoaId())
                .append("loaNo", getLoaNo())
                .append("loaComNo", getLoaComNo())
                .append("loaBankId", getLoaBankId())
                .append("loaLisId", getLoaLisId())
                .append("loaUse", getLoaUse())
                .append("loaIntegral", getLoaIntegral())
                .append("loaRate", getLoaRate())
                .append("loaState", getLoaState())
                .append("loaTime", getLoaTime())
                .append("loaMoney", getLoaMoney())
                .append("loaEndTime", getLoaEndTime())
                .append("loaDel", getLoaDel())
                .append("loaBank", getLoaBank())
                .append("exaName", getExaName())
                .append("exaTime", getExaTime())
                .append("exaRemark", getExaRemark())
                .toString();
    }
}