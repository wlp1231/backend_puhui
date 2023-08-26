package com.qst.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qst.common.annotation.Excel;
import com.qst.common.core.domain.BaseEntity;

/**
 * 用户积分对象 score
 *
 * @author qst
 * @date 2022-06-27
 */
@ApiModel(description = "用户积分对象")
public class Score extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    @ApiModelProperty(value = "主键id")
    private Long scoreId;

    /** 企业ID */
    @ApiModelProperty(value = "企业ID")
    @Excel(name = "企业ID")
    private Long comId;

    /** 企业名称 */
    @ApiModelProperty(value = "企业名称")
    @Excel(name = "企业名称")
    private String comName;

    /** 积分值 */
    @ApiModelProperty(value = "积分值")
    @Excel(name = "积分值")
    private Long scoreNum;

    /** 更新详情 */
    @ApiModelProperty(value = "更新详情")
    @Excel(name = "更新详情")
    private String updateDetail;

    public void setScoreId(Long scoreId)
    {
        this.scoreId = scoreId;
    }

    public Long getScoreId()
    {
        return scoreId;
    }
    public void setComId(Long comId)
    {
        this.comId = comId;
    }

    public Long getComId()
    {
        return comId;
    }
    public void setScoreNum(Long scoreNum)
    {
        this.scoreNum = scoreNum;
    }

    public Long getScoreNum()
    {
        return scoreNum;
    }
    public void setUpdateDetail(String updateDetail)
    {
        this.updateDetail = updateDetail;
    }

    public String getUpdateDetail()
    {
        return updateDetail;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("scoreId", getScoreId())
                .append("comId", getComId())
                .append("comName", getComName())
                .append("scoreNum", getScoreNum())
                .append("updateTime", getUpdateTime())
                .append("updateDetail", getUpdateDetail())
                .toString();
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }
}