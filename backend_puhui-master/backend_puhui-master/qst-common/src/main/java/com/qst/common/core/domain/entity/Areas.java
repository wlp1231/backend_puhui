package com.qst.common.core.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qst.common.annotation.Excel;
import com.qst.common.core.domain.BaseEntity;

/**
 * 区对象 areas
 * 
 * @author qst
 * @date 2021-09-22
 */
@ApiModel("区实体类")
public class Areas extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("区主键")
    private Long id;

    /** 区ID */
    @ApiModelProperty("区id")
    @Excel(name = "区ID")
    private String areaid;

    /** 区名 */
    @ApiModelProperty("区名")
    @Excel(name = "区名")
    private String area;

    /** 市ID */
    @Excel(name = "市ID")
    @ApiModelProperty("市ID")
    private String cityid;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAreaid(String areaid) 
    {
        this.areaid = areaid;
    }

    public String getAreaid() 
    {
        return areaid;
    }
    public void setArea(String area) 
    {
        this.area = area;
    }

    public String getArea() 
    {
        return area;
    }
    public void setCityid(String cityid) 
    {
        this.cityid = cityid;
    }

    public String getCityid() 
    {
        return cityid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("areaid", getAreaid())
            .append("area", getArea())
            .append("cityid", getCityid())
            .toString();
    }
}
