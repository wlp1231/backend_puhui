package com.qst.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qst.common.annotation.Excel;
import com.qst.common.core.domain.BaseEntity;

/**
 * 材料对象 materials
 *
 * @author zpbao
 * @date 2023-08-26
 */
@ApiModel("材料实体类")
public class Materials extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    @ApiModelProperty("主键")
    private Long lisId;

    /** 企业ID */
    @ApiModelProperty("企业ID")
    @Excel(name = "企业ID")
    private Long lisComId;

    /** 贷款ID */
    @ApiModelProperty("贷款ID")
    @Excel(name = "贷款ID")
    private Long lisLoaId;

    /** 身份证号 */
    @ApiModelProperty("身份证号")
    private String lisIdCard;

    /** 近六个月流水 */
    @ApiModelProperty("近六个月流水")
    @Excel(name = "近六个月流水")
    private String lisSixRunWater;

    /** 工作证明 */
    @ApiModelProperty("工作证明")
    @Excel(name = "工作证明")
    private String lisWork;

    /** 征信报告 */
    @ApiModelProperty("征信报告")
    @Excel(name = "征信报告")
    private String lisReport;

    /** 抵押物 */
    @ApiModelProperty("抵押物")
    @Excel(name = "抵押物")
    private String lisPawn;

    /** 删除标识 0 正常 2删除 */
    @ApiModelProperty("删除标识")
    private String lisDel;

    private Company company;

    public void setLisId(Long lisId)
    {
        this.lisId = lisId;
    }

    public Long getLisId()
    {
        return lisId;
    }
    public void setLisComId(Long lisComId)
    {
        this.lisComId = lisComId;
    }

    public Long getLisComId()
    {
        return lisComId;
    }
    public void setLisIdCard(String lisIdCard)
    {
        this.lisIdCard = lisIdCard;
    }

    public String getLisIdCard()
    {
        return lisIdCard;
    }
    public void setLisSixRunWater(String lisSixRunWater)
    {
        this.lisSixRunWater = lisSixRunWater;
    }

    public String getLisSixRunWater()
    {
        return lisSixRunWater;
    }
    public void setLisWork(String lisWork)
    {
        this.lisWork = lisWork;
    }

    public String getLisWork()
    {
        return lisWork;
    }
    public void setLisReport(String lisReport)
    {
        this.lisReport = lisReport;
    }

    public String getLisReport()
    {
        return lisReport;
    }
    public void setLisPawn(String lisPawn)
    {
        this.lisPawn = lisPawn;
    }

    public String getLisPawn()
    {
        return lisPawn;
    }
    public void setLisDel(String lisDel)
    {
        this.lisDel = lisDel;
    }

    public String getLisDel()
    {
        return lisDel;
    }

    public Long getLisLoaId() {
        return lisLoaId;
    }

    public void setLisLoaId(Long lisLoaId) {
        this.lisLoaId = lisLoaId;
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
                .append("lisId", getLisId())
                .append("lisComId", getLisComId())
                .append("lisLoaId", getLisLoaId())
                .append("lisIdCard", getLisIdCard())
                .append("lisSixRunWater", getLisSixRunWater())
                .append("lisWork", getLisWork())
                .append("lisReport", getLisReport())
                .append("lisPawn", getLisPawn())
                .append("lisDel", getLisDel())
                .toString();
    }
}