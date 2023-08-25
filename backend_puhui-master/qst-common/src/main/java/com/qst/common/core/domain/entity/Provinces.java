package com.qst.common.core.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qst.common.annotation.Excel;
import com.qst.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 省对象 provinces
 * 
 * @author qst
 * @date 2021-09-15
 */
@ApiModel("省实体类")
public class Provinces extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("省主键")
    private Long id;

    /** 省ID */
    @ApiModelProperty("省id")
    @Excel(name = "省ID")
    private String provinceid;

    /** 省名称 */
    @ApiModelProperty("省名称")
    @Excel(name = "省名称")
    private String province;

    //2022/08/12  增加vue 省市联动数据
    private List<Cities> citiesList;

    public List<Cities> getCitiesList() {
        return citiesList;
    }

    public void setCitiesList(List<Cities> citiesList) {
        this.citiesList = citiesList;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setProvinceid(String provinceid) 
    {
        this.provinceid = provinceid;
    }

    public String getProvinceid() 
    {
        return provinceid;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("provinceid", getProvinceid())
            .append("province", getProvince())
            .append("cities",getCitiesList())
            .toString();
    }
}
