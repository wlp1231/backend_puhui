package com.qst.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qst.common.annotation.Excel;
import com.qst.common.core.domain.BaseEntity;

/**
 * 人脸识别记录对象 facecheck
 *
 * @author zpbao
 * @date 2023-08-26
 */
@ApiModel("人脸识别实体类")
public class Facecheck extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    @Excel(name = "编号")
    private Long id;

    /** 企业电话 */
    @ApiModelProperty("企业电话")
    @Excel(name = "企业电话")
    private String tel;

    /** 照片信息 */
    @ApiModelProperty("照片信息")
    @Excel(name = "照片信息")
    private String photo;

    /** 验证状态 */
    @ApiModelProperty("验证状态")
    @Excel(name = "验证状态")
    private Long status;

    /** 比对时间 */
    @ApiModelProperty("比对时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "比对时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date time;

    /* 2022.7.14 增加企业id*/
    @ApiModelProperty("企业id")
    private int comId;
    /* 2022.7.14 增加企业 一对一*/
    private Company company;

    public int getComId() {
        return comId;
    }

    public void setComId(int comId) {
        this.comId = comId;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setTel(String tel)
    {
        this.tel = tel;
    }

    public String getTel()
    {
        return tel;
    }
    public void setPhoto(String photo)
    {
        this.photo = photo;
    }

    public String getPhoto()
    {
        return photo;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }
    public void setTime(Date time)
    {
        this.time = time;
    }

    public Date getTime()
    {
        return time;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("tel", getTel())
                .append("photo", getPhoto())
                .append("status", getStatus())
                .append("time", getTime())
                .toString();
    }
}