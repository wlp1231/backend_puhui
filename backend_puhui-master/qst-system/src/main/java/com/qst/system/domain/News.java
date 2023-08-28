package com.qst.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qst.common.annotation.Excel;
import com.qst.common.core.domain.BaseEntity;

/**
 * 宣传信息管理对象 news
 *
 * @author qst
 * @date 2022-06-16
 */
@ApiModel(description = "宣传信息管理对象")
public class News extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    @ApiModelProperty(value = "主键id")
    private Long nId;

    /** 标题 */
    @ApiModelProperty(value = "标题")
    @Excel(name = "标题")
    private String newsTitle;

    /** 内容 */
    @ApiModelProperty(value = "内容")
    @Excel(name = "内容")
    private String newsDetail;

    /** 状态（0草稿 1发布 2隐藏） */
    @ApiModelProperty(value = "状态（0草稿 1发布 2隐藏）")
    @Excel(name = "状态", readConverterExp = "0=草稿,1=发布,2=隐藏")
    private String newsState;

    /** 新闻类型（字典值：1法律法规 2贷款知识 3公司新闻 4常见问题） */
    @ApiModelProperty(value = "新闻类型（字典值：1法律法规 2贷款知识 3公司新闻 4常见问题）")
    @Excel(name = "新闻类型", readConverterExp = "字=典值：1法律法规,2=贷款知识,3=公司新闻,4=常见问题")
    private String newsType;

    public void setnId(Long nId)
    {
        this.nId = nId;
    }

    public Long getnId()
    {
        return nId;
    }
    public void setNewsTitle(String newsTitle)
    {
        this.newsTitle = newsTitle;
    }

    public String getNewsTitle()
    {
        return newsTitle;
    }
    public void setNewsDetail(String newsDetail)
    {
        this.newsDetail = newsDetail;
    }

    public String getNewsDetail()
    {
        return newsDetail;
    }
    public void setNewsState(String newsState)
    {
        this.newsState = newsState;
    }

    public String getNewsState()
    {
        return newsState;
    }
    public void setNewsType(String newsType)
    {
        this.newsType = newsType;
    }

    public String getNewsType()
    {
        return newsType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("nId", getnId())
                .append("newsTitle", getNewsTitle())
                .append("newsDetail", getNewsDetail())
                .append("newsState", getNewsState())
                .append("newsType", getNewsType())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}