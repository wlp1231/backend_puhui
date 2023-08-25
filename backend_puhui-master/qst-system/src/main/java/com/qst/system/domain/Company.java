package com.qst.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qst.common.annotation.Excel;
import com.qst.common.core.domain.BaseEntity;

/**
 * 对象 company
 *
 * @author qst
 * @date 2021-09-10
 */
@ApiModel("公司实体类")
public class Company extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @ApiModelProperty("主键")
    private Long comId;

    /** 企业编号 */
    @ApiModelProperty("企业编号")
    @Excel(name = "企业编号")
    private String comNo;

    /** 企业名称 */
    @ApiModelProperty("企业名称")
    @Excel(name = "企业名称")
    private String comName;

    /** 邮箱 */
    @ApiModelProperty("邮箱")
    @Excel(name = "邮箱")
    private String comEmail;

    /** 电话 */
    @ApiModelProperty("电话")
    @Excel(name = "电话")
    private String comPhone;

    /** 密码 */
    @ApiModelProperty("密码")
    @Excel(name = "密码")
    private String comPwd;

    /** 备注、激活码 */
    @ApiModelProperty("备注")
    @Excel(name = "备注、激活码")
    private String comRemark;

    /** 0 正常，1 停用，2 未激活，3 未实名 */
    @ApiModelProperty("公司状态")
    @Excel(name = "0 正常，1 停用，2 未激活，3 未实名")
    private String comStatus;

    /** 0 存在， 2删除 */
    @ApiModelProperty("状态位")
    @Excel(name = "0 存在， 2删除")
    private String comDel;

    public void setComId(Long comId)
    {
        this.comId = comId;
    }

    public Long getComId()
    {
        return comId;
    }
    public void setComNo(String comNo)
    {
        this.comNo = comNo;
    }

    public String getComNo()
    {
        return comNo;
    }
    public void setComName(String comName)
    {
        this.comName = comName;
    }

    public String getComName()
    {
        return comName;
    }
    public void setComEmail(String comEmail)
    {
        this.comEmail = comEmail;
    }

    public String getComEmail()
    {
        return comEmail;
    }
    public void setComPhone(String comPhone)
    {
        this.comPhone = comPhone;
    }

    public String getComPhone()
    {
        return comPhone;
    }
    public void setComPwd(String comPwd)
    {
        this.comPwd = comPwd;
    }

    public String getComPwd()
    {
        return comPwd;
    }
    public void setComRemark(String comRemark)
    {
        this.comRemark = comRemark;
    }

    public String getComRemark()
    {
        return comRemark;
    }
    public void setComStatus(String comStatus)
    {
        this.comStatus = comStatus;
    }

    public String getComStatus()
    {
        return comStatus;
    }
    public void setComDel(String comDel)
    {
        this.comDel = comDel;
    }

    public String getComDel()
    {
        return comDel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("comId", getComId())
                .append("comNo", getComNo())
                .append("comName", getComName())
                .append("comEmail", getComEmail())
                .append("comPhone", getComPhone())
                .append("comPwd", getComPwd())
                .append("comRemark", getComRemark())
                .append("comStatus", getComStatus())
                .append("comDel", getComDel())
                .toString();
    }
}