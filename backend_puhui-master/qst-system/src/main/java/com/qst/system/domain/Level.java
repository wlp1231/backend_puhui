package com.qst.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qst.common.annotation.Excel;
import com.qst.common.core.domain.BaseEntity;

/**
 * 等级设置对象 level
 *
 * @author qst
 * @date 2022-06-27
 */
@ApiModel(description = "等级设置对象")
public class Level extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    @ApiModelProperty(value = "主键id")
    private Long levelId;

    /** 开始分数 */
    @ApiModelProperty(value = "开始分数")
    @Excel(name = "开始分数")
    private Long scoreStart;

    /** 结束分数 */
    @ApiModelProperty(value = "结束分数")
    @Excel(name = "结束分数")
    private Long scoreEnd;

    /** 等级 */
    @ApiModelProperty(value = "等级")
    @Excel(name = "等级")
    private String levelDetail;

    public void setLevelId(Long levelId)
    {
        this.levelId = levelId;
    }

    public Long getLevelId()
    {
        return levelId;
    }
    public void setScoreStart(Long scoreStart)
    {
        this.scoreStart = scoreStart;
    }

    public Long getScoreStart()
    {
        return scoreStart;
    }
    public void setScoreEnd(Long scoreEnd)
    {
        this.scoreEnd = scoreEnd;
    }

    public Long getScoreEnd()
    {
        return scoreEnd;
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
                .append("levelId", getLevelId())
                .append("scoreStart", getScoreStart())
                .append("scoreEnd", getScoreEnd())
                .append("levelDetail", getLevelDetail())
                .append("remark", getRemark())
                .toString();
    }
}