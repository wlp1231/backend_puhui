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
 * 留言管理对象 message
 *
 * @author qst
 * @date 2022-06-14
 */
@ApiModel(description = "留言管理对象")
public class Message extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    @ApiModelProperty(value = "主键id")
    private Long mId;

    /** 企业ID */
    @ApiModelProperty(value = "企业ID")
    @Excel(name = "企业ID")
    private Long comId;


    /** 企业名称 */
    @ApiModelProperty(value = "企业名称")
    @Excel(name = "企业名称")
    private String comName;

    /** 留言内容 */
    @ApiModelProperty(value = "留言内容")
    @Excel(name = "留言内容")
    private String msgDetail;

    /** 留言时间 */
    @ApiModelProperty(value = "留言时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "留言时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date msgTime;

    /** 回复内容 */
    @ApiModelProperty(value = "回复内容")
    @Excel(name = "回复内容")
    private String respDetail;

    /** 回复时间 */
    @ApiModelProperty(value = "回复时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "回复时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date respTime;

    /** 留言状态（0未回复 1已回复） */
    @ApiModelProperty(value = "留言状态（0未回复 1已回复）")
    @Excel(name = "留言状态", readConverterExp = "0=未回复,1=已回复")
    private String msgState;

    public void setmId(Long mId)
    {
        this.mId = mId;
    }

    public Long getmId()
    {
        return mId;
    }
    public void setComId(Long comId)
    {
        this.comId = comId;
    }

    public Long getComId()
    {
        return comId;
    }
    public void setMsgDetail(String msgDetail)
    {
        this.msgDetail = msgDetail;
    }

    public String getMsgDetail()
    {
        return msgDetail;
    }
    public void setMsgTime(Date msgTime)
    {
        this.msgTime = msgTime;
    }

    public Date getMsgTime()
    {
        return msgTime;
    }
    public void setRespDetail(String respDetail)
    {
        this.respDetail = respDetail;
    }

    public String getRespDetail()
    {
        return respDetail;
    }
    public void setRespTime(Date respTime)
    {
        this.respTime = respTime;
    }

    public Date getRespTime()
    {
        return respTime;
    }
    public void setMsgState(String msgState)
    {
        this.msgState = msgState;
    }

    public String getMsgState()
    {
        return msgState;
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
                .append("mId", getmId())
                .append("comId", getComId())
                .append("comName", getComName())
                .append("msgDetail", getMsgDetail())
                .append("msgTime", getMsgTime())
                .append("respDetail", getRespDetail())
                .append("respTime", getRespTime())
                .append("msgState", getMsgState())
                .toString();
    }
}