package com.qst.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qst.common.annotation.Excel;
import com.qst.common.core.domain.BaseEntity;

/**
 * 用户等级对象 score_level
 *
 * @author qst
 * @date 2022-06-27
 */
@ApiModel(description = "用户积分对象")
public class ScoreLevel extends BaseEntity
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

    /** 等级 */
    @ApiModelProperty(value = "等级")
    @Excel(name = "等级")
    private String levelDetail;

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
    public void setLevelDetail(String levelDetail)
    {
        this.levelDetail = levelDetail;
    }

    public String getLevelDetail()
    {
        return levelDetail;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("scoreId", getScoreId())
                .append("comId", getComId())
                .append("comName", getComName())
                .append("scoreNum", getScoreNum())
                .append("levelDetail", getLevelDetail())
                .append("updateTime", getUpdateTime())
                .toString();
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }
}