package com.qst.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qst.common.annotation.Excel;
import com.qst.common.core.domain.BaseEntity;

/**
 * level_reward对象 level_reward
 *
 * @author zpbao
 * @date 2023-08-28
 */
@ApiModel(description = "等级奖励对象")
public class LevelReward extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    @ApiModelProperty(value = "主键id")
    private Long rewardId;

    /** 等级 */
    @ApiModelProperty(value = "等级")
    @Excel(name = "等级")
    private String levelDetail;

    /** 奖励内容 */
    @ApiModelProperty(value = "奖励内容")
    @Excel(name = "奖励内容")
    private String rewardDetail;

    public void setRewardId(Long rewardId)
    {
        this.rewardId = rewardId;
    }

    public Long getRewardId()
    {
        return rewardId;
    }
    public void setLevelDetail(String levelDetail)
    {
        this.levelDetail = levelDetail;
    }

    public String getLevelDetail()
    {
        return levelDetail;
    }
    public void setRewardDetail(String rewardDetail)
    {
        this.rewardDetail = rewardDetail;
    }

    public String getRewardDetail()
    {
        return rewardDetail;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("rewardId", getRewardId())
                .append("levelDetail", getLevelDetail())
                .append("rewardDetail", getRewardDetail())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}