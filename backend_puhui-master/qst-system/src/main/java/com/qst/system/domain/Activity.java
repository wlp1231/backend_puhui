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
 * 活动管理对象 activity
 *
 * @author zpbao
 * @date 2023-08-28
 */
@ApiModel(description = "活动管理对象")
public class Activity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    @ApiModelProperty(value = "主键id")
    private Long aId;

    /** 开始时间 */
    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date beginDate;

    /** 结束时间 */
    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 活动标题 */
    @ApiModelProperty(value = "活动标题")
    @Excel(name = "活动标题")
    private String actTitle;

    /** 活动内容 */
    @ApiModelProperty(value = "活动内容")
    @Excel(name = "活动内容")
    private String actDetail;

    /** 活动状态（0草稿 1进行中 2结束） */
    @ApiModelProperty(value = "活动状态（0草稿 1进行中 2结束）")
    @Excel(name = "活动状态", readConverterExp = "0=草稿,1=进行中,2=结束")
    private String actState;

    public void setaId(Long aId)
    {
        this.aId = aId;
    }

    public Long getaId()
    {
        return aId;
    }
    public void setBeginDate(Date beginDate)
    {
        this.beginDate = beginDate;
    }

    public Date getBeginDate()
    {
        return beginDate;
    }
    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }
    public void setActTitle(String actTitle)
    {
        this.actTitle = actTitle;
    }

    public String getActTitle()
    {
        return actTitle;
    }
    public void setActDetail(String actDetail)
    {
        this.actDetail = actDetail;
    }

    public String getActDetail()
    {
        return actDetail;
    }
    public void setActState(String actState)
    {
        this.actState = actState;
    }

    public String getActState()
    {
        return actState;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("aId", getaId())
                .append("beginDate", getBeginDate())
                .append("endDate", getEndDate())
                .append("actTitle", getActTitle())
                .append("actDetail", getActDetail())
                .append("actState", getActState())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}