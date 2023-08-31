package com.jd.bean;

import com.jd.bean.vo.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 【请填写功能名称】对象 provinces
 * 
 * @author qst
 * @date 2021-09-15
 */
public class Provinces extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 省ID */
//    @Excel(name = "省ID")
    private String provinceid;

    /** 省名称 */
//    @Excel(name = "省名称")
    private String province;

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
            .toString();
    }
}
