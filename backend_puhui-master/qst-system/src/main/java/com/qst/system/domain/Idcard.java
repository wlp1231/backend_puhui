package com.qst.system.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qst.common.annotation.Excel;
import com.qst.common.core.domain.BaseEntity;

/**
 * 身份证识别对象 idcard
 *
 * @author qst
 * @date 2022-06-28
 */
@ApiModel(description = "身份证识别对象")
public class Idcard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty(value = "编号")
    private Long id;

    /** 姓名 */
    @ApiModelProperty(value = "姓名")
    @Excel(name = "姓名")
    private String username;

    /** 身份证号 */
    @ApiModelProperty(value = "身份证号")
    @Excel(name = "身份证号")
    private String idcard;

    /** 地址 */
    @ApiModelProperty(value = "地址")
    @Excel(name = "地址")
    private String address;

    /** 出生日期 */
    @ApiModelProperty(value = "出生日期")
    @Excel(name = "出生日期")
    private String birthday;

    /** 性别 */
    @ApiModelProperty(value = "性别")
    @Excel(name = "性别")
    private String gender;

    /** 民族 */
    @ApiModelProperty(value = "民族")
    @Excel(name = "民族")
    private String nation;

    /** 公司ID */
    @ApiModelProperty(value = "公司ID")
    @Excel(name = "公司ID")
    private Long comid;

    /** 企业名称 */
    @ApiModelProperty(value = "企业名称")
    @Excel(name = "企业名称")
    private String comName;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }
    public void setIdcard(String idcard)
    {
        this.idcard = idcard;
    }

    public String getIdcard()
    {
        return idcard;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getAddress()
    {
        return address;
    }
    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }

    public String getBirthday()
    {
        return birthday;
    }
    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public String getGender()
    {
        return gender;
    }
    public void setNation(String nation)
    {
        this.nation = nation;
    }

    public String getNation()
    {
        return nation;
    }
    public void setComid(Long comid)
    {
        this.comid = comid;
    }

    public Long getComid()
    {
        return comid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("username", getUsername())
                .append("idcard", getIdcard())
                .append("comName", getComName())
                .append("address", getAddress())
                .append("birthday", getBirthday())
                .append("gender", getGender())
                .append("nation", getNation())
                .append("comid", getComid())
                .toString();
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }
}