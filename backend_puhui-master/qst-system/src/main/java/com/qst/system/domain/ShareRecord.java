package com.qst.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.qst.common.annotation.Excel;
import com.qst.common.core.domain.BaseEntity;

/**
 * 分享数据管理对象 share_record
 *
 * @author qst
 * @date 2022-06-14
 */
@ApiModel(description = "分享数据管理对象")
public class ShareRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    @ApiModelProperty(value = "主键id")
    private Long rId;

    /** 企业id */
    @ApiModelProperty(value = "企业id")
    @Excel(name = "企业id")
    private Long comId;


    /** 企业名称 */
    @ApiModelProperty(value = "企业名称")
    @Excel(name = "企业名称")
    private String comName;

    /** 分享次数 */
    @ApiModelProperty(value = "分享次数")
    @Excel(name = "分享次数")
    private Long shareNum;

    public void setrId(Long rId)
    {
        this.rId = rId;
    }

    public Long getrId()
    {
        return rId;
    }
    public void setComId(Long comId)
    {
        this.comId = comId;
    }

    public Long getComId()
    {
        return comId;
    }
    public void setShareNum(Long shareNum)
    {
        this.shareNum = shareNum;
    }

    public Long getShareNum()
    {
        return shareNum;
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
                .append("rId", getrId())
                .append("comId", getComId())
                .append("comName", getComName())
                .append("shareNum", getShareNum())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}