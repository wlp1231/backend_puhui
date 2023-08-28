package com.qst.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qst.common.annotation.Excel;
import com.qst.common.core.domain.BaseEntity;

/**
 * 分享规则管理对象 share_rule
 *
 * @author qst
 * @date 2022-06-14
 */
@ApiModel(description = "分享规则管理对象")
public class ShareRule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    @ApiModelProperty(value = "主键id")
    private Long sId;

    /** 分享次数开始 */
    @ApiModelProperty(value = "分享次数开始")
    @Excel(name = "分享次数开始")
    private Long beginNum;

    /** 分享次数结束 */
    @ApiModelProperty(value = "分享次数结束")
    @Excel(name = "分享次数结束")
    private Long endNum;

    /** 该分享次数范围内奖励说明 */
    @ApiModelProperty(value = "该分享次数范围内奖励说明")
    @Excel(name = "该分享次数范围内奖励说明")
    private String shareReward;

    public void setsId(Long sId)
    {
        this.sId = sId;
    }

    public Long getsId()
    {
        return sId;
    }
    public void setBeginNum(Long beginNum)
    {
        this.beginNum = beginNum;
    }

    public Long getBeginNum()
    {
        return beginNum;
    }
    public void setEndNum(Long endNum)
    {
        this.endNum = endNum;
    }

    public Long getEndNum()
    {
        return endNum;
    }
    public void setShareReward(String shareReward)
    {
        this.shareReward = shareReward;
    }

    public String getShareReward()
    {
        return shareReward;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("sId", getsId())
                .append("beginNum", getBeginNum())
                .append("endNum", getEndNum())
                .append("shareReward", getShareReward())
                .toString();
    }
}