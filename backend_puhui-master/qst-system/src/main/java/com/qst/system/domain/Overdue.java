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
 * 逾期对象 overdue
 *
 * @author qst
 * @date 2021-09-28
 */
@ApiModel("逾期实体类")
public class Overdue extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id  */
    @ApiModelProperty("主键")
    private Long oveId;

    /** 企业编号 */
    @ApiModelProperty("企业编号")
    @Excel(name = "企业编号")
    private Long oveComNo;

    /** 企业编号 */
    @ApiModelProperty("企业编号")
    @Excel(name = "企业编号")
    private Long oveLoaId;

    /** 逾期金额 */
    @ApiModelProperty("逾期金额")
    @Excel(name = "逾期金额")
    private Long oveMoney;

    /** 逾期开始时间 */
    @ApiModelProperty("逾期开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "逾期开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date oveStartTime;

    /** 逾期结束时间 */
    @ApiModelProperty("逾期结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "逾期结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date oveLastTime;

    /** 减少积分数 */
    @ApiModelProperty("减少积分数")
    @Excel(name = "减少积分数")
    private Long oveCutCount;

    /** 额外利息 */
    @ApiModelProperty("额外利息")
    @Excel(name = "额外利息")
    private String oveAddInt;

    /** 删除标识 0 正常 2 删除 */
    @ApiModelProperty("删除标识")
    private String oveDel;

    private Company company;

    private Loans loans;

    public void setOveId(Long oveId)
    {
        this.oveId = oveId;
    }

    public Long getOveId()
    {
        return oveId;
    }
    public void setOveComNo(Long oveComNo)
    {
        this.oveComNo = oveComNo;
    }

    public Long getOveComNo()
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
                .append("oveDel", getOveDel())
                .toString();
    }
}
