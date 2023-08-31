package com.jd.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jd.bean.vo.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 审批对象 examine
 * 
 * @author qst
 * @date 2021-09-23
 */
public class Examine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long exaId;

    /** 贷款ID */
//    @Excel(name = "贷款ID")
    private Long exaLoaId;

    /** 审批人 */
//    @Excel(name = "审批人")
    private String exaName;

    /** 审批时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
//    @Excel(name = "审批时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date exaTime;

    /** 审批状态 0 通过，1 驳回 */
//    @Excel(name = "审批状态 0 通过，1 驳回")
    private String exaState;

    /** 审批理由 */
//    @Excel(name = "审批理由")
    private String exaRemark;

    /** 删除标识 0 正常 2 删除 */
    private String exaDel;

    public void setExaId(Long exaId) 
    {
        this.exaId = exaId;
    }

    public Long getExaId() 
    {
        return exaId;
    }
    public void setExaLoaId(Long exaLoaId) 
    {
        this.exaLoaId = exaLoaId;
    }

    public Long getExaLoaId() 
    {
        return exaLoaId;
    }
    public void setExaName(String exaName) 
    {
        this.exaName = exaName;
    }

    public String getExaName() 
    {
        return exaName;
    }
    public void setExaTime(Date exaTime) 
    {
        this.exaTime = exaTime;
    }

    public Date getExaTime() 
    {
        return exaTime;
    }
    public void setExaState(String exaState) 
    {
        this.exaState = exaState;
    }

    public String getExaState() 
    {
        return exaState;
    }
    public void setExaRemark(String exaRemark) 
    {
        this.exaRemark = exaRemark;
    }

    public String getExaRemark() 
    {
        return exaRemark;
    }
    public void setExaDel(String exaDel) 
    {
        this.exaDel = exaDel;
    }

    public String getExaDel() 
    {
        return exaDel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("exaId", getExaId())
            .append("exaLoaId", getExaLoaId())
            .append("exaName", getExaName())
            .append("exaTime", getExaTime())
            .append("exaState", getExaState())
            .append("exaRemark", getExaRemark())
            .append("exaDel", getExaDel())
            .toString();
    }
}
