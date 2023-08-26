package com.qst.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qst.common.annotation.Excel;
import com.qst.common.core.domain.BaseEntity;

/**
 * 资质对象 aptitude
 *
 * @author zpbao
 * @date 2023-08-26
 */
@ApiModel("资质实体类")
public class Aptitude extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @ApiModelProperty("资质编号")
    private Long aptId;

    /** 企业ID */
    @ApiModelProperty("企业ID")
    @Excel(name = "企业ID")
    private Long comId;

    /** 企业名称 */
    @ApiModelProperty("企业名称")
    @Excel(name = "企业名称")
    private String comName;

    /** 贷款ID */
    @ApiModelProperty("贷款ID")
    @Excel(name = "贷款ID")
    private Long aptLoaId;

    /** 信用额度 */
    @ApiModelProperty("信用额度")
    @Excel(name = "信用额度")
    private String aptCredit;

    /** 抵押物 */
    @ApiModelProperty("抵押物")
    @Excel(name = "抵押物")
    private String aptPawn;

    /** 贷款记录 */
    @ApiModelProperty("贷款记录")
    @Excel(name = "贷款记录")
    private String aptLoansRecord;

    /** 企业市值 */
    @ApiModelProperty("企业市值")
    @Excel(name = "企业市值")
    private String aptComValue;

    /** 删除标识 0 正常 2 删除 */
    @ApiModelProperty("删除标识")
    @Excel(name = "删除标识 0 正常 2 删除")
    private String aptDel;

    private Company company;

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

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("aptId", getAptId())
                .append("comId", getComId())
                .append("comName",getComName())
                .append("aptLoaId", getAptLoaId())
                .append("aptCredit", getAptCredit())
                .append("aptPawn", getAptPawn())
                .append("aptLoansRecord", getAptLoansRecord())
                .append("aptComValue", getAptComValue())
                .append("aptDel", getAptDel())
                .toString();
    }
}