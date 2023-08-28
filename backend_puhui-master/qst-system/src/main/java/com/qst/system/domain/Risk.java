package com.qst.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qst.common.annotation.Excel;
import com.qst.common.core.domain.BaseEntity;

/**
 * 风控管理对象 risk
 *
 * @author qst
 * @date 2022-01-24
 */
@ApiModel(description = "风控信息")
public class Risk extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    @ApiModelProperty(value = "主键id")
    private Long riskId;

    /** 贷款编号 */
    @Excel(name = "贷款编号")
    @ApiModelProperty(value = "贷款编号")
    private String loaNo;

    /** 企业名称 */
    @ApiModelProperty(value = "企业名称")
    @Excel(name = "企业名称")
    private String comName;

    /** 贷款金额 */
    @ApiModelProperty(value = "贷款金额")
    @Excel(name = "贷款金额")
    private Long loaMoney;

    /** 贷款周期 */
    @ApiModelProperty(value = "贷款周期")
    @Excel(name = "贷款周期")
    private Long loaLisId;

    /** 抵押价值 */
    @ApiModelProperty(value = "抵押价值")
    @Excel(name = "抵押价值")
    private String aptPrice;

    /** 月最小值 */
    @ApiModelProperty(value = "月最小值")
    @Excel(name = "月最小值")
    private Integer monthAccountMin;

    /** 总负债值 */
    @ApiModelProperty(value = "总负债值")
    @Excel(name = "总负债值")
    private String grossLiabilities;

    /** 额度值 */
    @ApiModelProperty(value = "额度值")
    @Excel(name = "额度值")
    private String creditLine;

    /*征信状态*/
    @ApiModelProperty(value = "征信状态")
    @Excel(name = "征信状态")
    private String creInvesState;

    public String getCreInvesState() {
        return creInvesState;
    }

    public void setCreInvesState(String creInvesState) {
        this.creInvesState = creInvesState;
    }

    /** 是否删除 */
    @Excel(name = "是否删除")
    private String riskDel;

    public void setRiskId(Long riskId)
    {
        this.riskId = riskId;
    }

    public Long getRiskId()
    {
        return riskId;
    }
    public void setLoaNo(String loaNo)
    {
        this.loaNo = loaNo;
    }

    public String getLoaNo()
    {
        return loaNo;
    }
    public void setComName(String comName)
    {
        this.comName = comName;
    }

    public String getComName()
    {
        return comName;
    }
    public void setLoaMoney(Long loaMoney)
    {
        this.loaMoney = loaMoney;
    }

    public Long getLoaMoney()
    {
        return loaMoney;
    }
    public void setLoaLisId(Long loaLisId)
    {
        this.loaLisId = loaLisId;
    }

    public Long getLoaLisId()
    {
        return loaLisId;
    }
    public void setAptPrice(String aptPrice)
    {
        this.aptPrice = aptPrice;
    }

    public String getAptPrice()
    {
        return aptPrice;
    }
    public void setMonthAccountMin(Integer monthAccountMin)
    {
        this.monthAccountMin = monthAccountMin;
    }

    public Integer getMonthAccountMin()
    {
        return monthAccountMin;
    }
    public void setGrossLiabilities(String grossLiabilities)
    {
        this.grossLiabilities = grossLiabilities;
    }

    public String getGrossLiabilities()
    {
        return grossLiabilities;
    }
    public void setCreditLine(String creditLine)
    {
        this.creditLine = creditLine;
    }

    public String getCreditLine()
    {
        return creditLine;
    }
    public void setRiskDel(String riskDel)
    {
        this.riskDel = riskDel;
    }

    public String getRiskDel()
    {
        return riskDel;
    }

    @Override
    public String toString() {
        return "Risk{" +
                "riskId=" + riskId +
                ", loaNo='" + loaNo + '\'' +
                ", comName='" + comName + '\'' +
                ", loaMoney=" + loaMoney +
                ", loaLisId=" + loaLisId +
                ", aptPrice='" + aptPrice + '\'' +
                ", monthAccountMin=" + monthAccountMin +
                ", grossLiabilities='" + grossLiabilities + '\'' +
                ", creditLine='" + creditLine + '\'' +
                ", creInvesState='" + creInvesState + '\'' +
                ", riskDel='" + riskDel + '\'' +
                '}';
    }

    public Risk() {
    }

    public Risk(Long riskId, String creInvesState) {
        this.riskId = riskId;
        this.creInvesState = creInvesState;
    }
}