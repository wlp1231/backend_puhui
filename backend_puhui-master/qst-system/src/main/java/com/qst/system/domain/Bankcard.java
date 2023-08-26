package com.qst.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qst.common.annotation.Excel;
import com.qst.common.core.domain.BaseEntity;

/**
 * 银行卡识别信息对象 bankcard
 *
 * @author qst
 * @date 2022-06-28
 */
@ApiModel(description = "银行卡识别信息对象")
public class Bankcard extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty(value = "编号")
    private Long id;

    /** 银行名称 */
    @ApiModelProperty(value = "银行名称")
    @Excel(name = "银行名称")
    private String bankname;

    /** 银行卡号 */
    @ApiModelProperty(value = "银行卡号")
    @Excel(name = "银行卡号")
    private String bankcardno;

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
    public void setBankname(String bankname)
    {
        this.bankname = bankname;
    }

    public String getBankname()
    {
        return bankname;
    }
    public void setBankcardno(String bankcardno)
    {
        this.bankcardno = bankcardno;
    }

    public String getBankcardno()
    {
        return bankcardno;
    }
    public void setComid(Long comid)
    {
        this.comid = comid;
    }

    public Long getComid()
    {
        return comid;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("bankname", getBankname())
                .append("bankcardno", getBankcardno())
                .append("comid", getComid())
                .append("comName", getComName())
                .toString();
    }
}