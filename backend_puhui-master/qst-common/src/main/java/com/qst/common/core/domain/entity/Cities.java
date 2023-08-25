package com.qst.common.core.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qst.common.annotation.Excel;
import com.qst.common.core.domain.BaseEntity;

/**
 * 市对象 cities
 * 
 * @author qst
 * @date 2021-09-15
 */
@ApiModel("市实体类")
public class Cities extends BaseEntity
{
    private static final long serialVersionUID = 1L;


    @ApiModelProperty("市主键")
    private Long id;


    @ApiModelProperty("市id")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String cityid;


    @ApiModelProperty("市名")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String city;

    /** $column.columnComment */
    @ApiModelProperty("所属省id")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String provinceid;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCityid(String cityid) 
    {
        this.cityid = cityid;
    }

    public String getCityid() 
    {
        return cityid;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setProvinceid(String provinceid) 
    {
        this.provinceid = provinceid;
    }

    public String getProvinceid() 
    {
        return provinceid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("cityid", getCityid())
            .append("city", getCity())
            .append("provinceid", getProvinceid())
            .toString();
    }
}
