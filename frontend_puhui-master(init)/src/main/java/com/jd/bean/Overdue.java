package com.jd.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jd.bean.vo.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 逾期对象 overdue
 *
 * @author qst
 * @date 2021-09-28
 */
public class Overdue extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long oveId;

    /** 企业编号 */
//    @Excel(name = "企业编号")
    private String oveComNo;

    /** 企业编号 */
//    @Excel(name = "企业编号")
    private Long oveLoaId;

    /** 逾期金额 */
//    @Excel(name = "逾期金额")
    private Long oveMoney;

    /** 逾期开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "逾期开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date oveStartTime;

    /** 逾期结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "逾期结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date oveLastTime;

    /** 减少积分数 */
//    @Excel(name = "减少积分数")
    private Long oveCutCount;

    /** 额外利息 */
//    @Excel(name = "额外利息")
    private String oveAddInt;

    /** 删除标识 0 正常 2 删除 */
    private String oveDel;

    private String loaNo;

    public void setOveId(Long oveId)
    {
        this.oveId = oveId;
    }

    public Long getOveId()
    {
        return oveId;
    }
    public void setOveComNo(String oveComNo)
    {
        this.oveComNo = oveComNo;
    }

    public String getOveComNo()
    {
        return oveComNo;
    }
    public void setOveMoney(Long oveMoney)
    {
        this.oveMoney = oveMoney;
    }

    public Long getOveMoney()
    {
        return oveMoney;
    }
    public void setOveStartTime(Date oveStartTime)
    {
        this.oveStartTime = oveStartTime;
    }

    public Date getOveStartTime()
    {
        return oveStartTime;
    }
    public void setOveLastTime(Date oveLastTime)
    {
        this.oveLastTime = oveLastTime;
    }

    public Date getOveLastTime()
    {
        return oveLastTime;
    }
    public void setOveCutCount(Long oveCutCount)
    {
        this.oveCutCount = oveCutCount;
    }

    public Long getOveCutCount()
    {
        return oveCutCount;
    }
    public void setOveAddInt(String oveAddInt)
    {
        this.oveAddInt = oveAddInt;
    }

    public String getOveAddInt()
    {
        return oveAddInt;
    }
    public void setOveDel(String oveDel)
    {
        this.oveDel = oveDel;
    }

    public String getOveDel()
    {
        return oveDel;
    }

    public Long getOveLoaId() {
        return oveLoaId;
    }

    public void setOveLoaId(Long oveLoaId) {
        this.oveLoaId = oveLoaId;
    }

    public String getLoaNo() {
        return loaNo;
    }

    public void setLoaNo(String loaNo) {
        this.loaNo = loaNo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("oveId", getOveId())
                .append("oveComNo", getOveComNo())
                .append("oveLoaId", getOveLoaId())
                .append("oveMoney", getOveMoney())
                .append("oveStartTime", getOveStartTime())
                .append("oveLastTime", getOveLastTime())
                .append("oveCutCount", getOveCutCount())
                .append("oveAddInt", getOveAddInt())
                .append("loans", getLoaNo())
                .append("oveDel", getOveDel())
                .toString();
    }
}