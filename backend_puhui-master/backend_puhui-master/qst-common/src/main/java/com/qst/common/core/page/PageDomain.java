package com.qst.common.core.page;

import com.qst.common.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页数据
 * 
 * @author qst
 */
@ApiModel("分页实体类")
public class PageDomain
{
    /** 当前记录起始索引 */
    @ApiModelProperty("当前页数")
    private Integer pageNum;

    /** 每页显示记录数 */
    @ApiModelProperty("每页显示记录数")
    private Integer pageSize;

    /** 排序列 */
    @ApiModelProperty("排序列")
    private String orderByColumn;

    /** 排序的方向desc或者asc */
    @ApiModelProperty("排序的方向")
    private String isAsc = "asc";

    public String getOrderBy()
    {
        if (StringUtils.isEmpty(orderByColumn))
        {
            return "";
        }
        return StringUtils.toUnderScoreCase(orderByColumn) + " " + isAsc;
    }

    public Integer getPageNum()
    {
        return pageNum;
    }

    public void setPageNum(Integer pageNum)
    {
        this.pageNum = pageNum;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    public String getOrderByColumn()
    {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn)
    {
        this.orderByColumn = orderByColumn;
    }

    public String getIsAsc()
    {
        return isAsc;
    }

    public void setIsAsc(String isAsc)
    {
        if (StringUtils.isNotEmpty(isAsc))
        {
            // 兼容前端排序类型
            if ("ascending".equals(isAsc))
            {
                isAsc = "asc";
            }
            else if ("descending".equals(isAsc))
            {
                isAsc = "desc";
            }
            this.isAsc = isAsc;
        }
    }
}
