package com.qst.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qst.common.annotation.Excel;
import com.qst.common.core.domain.BaseEntity;

/**
 * 友情链接管理对象 links
 *
 * @author qst
 * @date 2022-06-16
 */
@ApiModel(description = "友情链接管理对象")
public class Links extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    @ApiModelProperty(value = "主键id")
    private Long linkId;

    /** 链接名称 */
    @ApiModelProperty(value = "链接名称")
    @Excel(name = "链接名称")
    private String linkName;

    /** 网址 */
    @ApiModelProperty(value = "网址")
    @Excel(name = "网址")
    private String linkUrl;

    /** 状态（1显示 2隐藏） */
    @ApiModelProperty(value = "状态（1显示 2隐藏）")
    @Excel(name = "状态", readConverterExp = "1=显示,2=隐藏")
    private String linkState;

    /** 排序号 */
    @ApiModelProperty(value = "排序号")
    @Excel(name = "排序号")
    private Integer linkOrder;

    public void setLinkId(Long linkId)
    {
        this.linkId = linkId;
    }

    public Long getLinkId()
    {
        return linkId;
    }
    public void setLinkName(String linkName)
    {
        this.linkName = linkName;
    }

    public String getLinkName()
    {
        return linkName;
    }
    public void setLinkUrl(String linkUrl)
    {
        this.linkUrl = linkUrl;
    }

    public String getLinkUrl()
    {
        return linkUrl;
    }
    public void setLinkState(String linkState)
    {
        this.linkState = linkState;
    }

    public String getLinkState()
    {
        return linkState;
    }
    public void setLinkOrder(Integer linkOrder)
    {
        this.linkOrder = linkOrder;
    }

    public Integer getLinkOrder()
    {
        return linkOrder;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("linkId", getLinkId())
                .append("linkName", getLinkName())
                .append("linkUrl", getLinkUrl())
                .append("linkState", getLinkState())
                .append("linkOrder", getLinkOrder())
                .toString();
    }
}