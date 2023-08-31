package com.jd.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 区对象 areas
 * 
 * @author qst
 * @date 2021-09-22
 */
public class Areas
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 区ID */
//    @Excel(name = "区ID")
    private String areaid;

    /** 区名 */
//    @Excel(name = "区名")
    private String area;

    /** 市ID */
//    @Excel(name = "市ID")
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
