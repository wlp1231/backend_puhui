package com.jd.bean;

import com.jd.bean.vo.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 资质对象 aptitude
 * 
 * @author qst
 * @date 2021-09-23
 */
public class Aptitude extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long aptId;

    /** 企业ID */
//    @Excel(name = "企业ID")
    private Long comId;

    /** 贷款ID */
//    @Excel(name = "贷款ID")
    private Long aptLoaId;

    /** 信用额度 */
//    @Excel(name = "信用额度")
    private String aptCredit;

    /** 抵押物 */
//    @Excel(name = "抵押物")
    private String aptPawn;

    /** 贷款记录 */
//    @Excel(name = "贷款记录")
    private String aptLoansRecord;

    /** 企业市值 */
//    @Excel(name = "企业市值")
    private String aptComValue;

    /** 删除标识 0 正常 2 删除 */
//    @Excel(name = "删除标识 0 正常 2 删除")
    private String aptDel;

    public void setAptId(Long aptId) 
    {
        this.aptId = aptId;
    }

    public Long getAptId() 
    {
        return aptId;
    }
    public void setComId(Long comId) 
    {
        this.comId = comId;
    }

    public Long getComId() 
    {
        return comId;
    }
    public void setAptLoaId(Long aptLoaId) 
    {
        this.aptLoaId = aptLoaId;
    }

    public Long getAptLoaId() 
    {
        return aptLoaId;
    }
    public void setAptCredit(String aptCredit) 
    {
        this.aptCredit = aptCredit;
    }

    public String getAptCredit() 
    {
        return aptCredit;
    }
    public void setAptPawn(String aptPawn) 
    {
        this.aptPawn = aptPawn;
    }

    public String getAptPawn() 
    {
        return aptPawn;
    }
    public void setAptLoansRecord(String aptLoansRecord) 
    {
        this.aptLoansRecord = aptLoansRecord;
    }

    public String getAptLoansRecord() 
    {
        return aptLoansRecord;
    }
    public void setAptComValue(String aptComValue) 
    {
        this.aptComValue = aptComValue;
    }

    public String getAptComValue() 
    {
        return aptComValue;
    }
    public void setAptDel(String aptDel) 
    {
        this.aptDel = aptDel;
    }

    public String getAptDel() 
    {
        return aptDel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("aptId", getAptId())
            .append("comId", getComId())
            .append("aptLoaId", getAptLoaId())
            .append("aptCredit", getAptCredit())
            .append("aptPawn", getAptPawn())
            .append("aptLoansRecord", getAptLoansRecord())
            .append("aptComValue", getAptComValue())
            .append("aptDel", getAptDel())
            .toString();
    }
}
