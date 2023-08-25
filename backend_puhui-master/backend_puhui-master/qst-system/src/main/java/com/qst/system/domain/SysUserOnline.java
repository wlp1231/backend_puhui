package com.qst.system.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 当前在线会话
 * 
 * @author qst
 */
@ApiModel("当前在线会话实体类")
public class SysUserOnline
{
    /** 会话编号 */
    @ApiModelProperty("会话编号")
    private String tokenId;

    /** 部门名称 */
    @ApiModelProperty("部门名称")
    private String deptName;

    /** 用户名称 */
    @ApiModelProperty("用户名称")
    private String userName;

    /** 登录IP地址 */
    @ApiModelProperty("登录IP地址")
    private String ipaddr;

    /** 登录地址 */
    @ApiModelProperty("登录地址")
    private String loginLocation;

    /** 浏览器类型 */
    @ApiModelProperty("浏览器类型")
    private String browser;

    /** 操作系统 */
    @ApiModelProperty("操作系统")
    private String os;

    /** 登录时间 */
    @ApiModelProperty("登录时间")
    private Long loginTime;

    public String getTokenId()
    {
        return tokenId;
    }

    public void setTokenId(String tokenId)
    {
        this.tokenId = tokenId;
    }

    public String getDeptName()
    {
        return deptName;
    }

    public void setDeptName(String deptName)
    {
        this.deptName = deptName;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getIpaddr()
    {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr)
    {
        this.ipaddr = ipaddr;
    }

    public String getLoginLocation()
    {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation)
    {
        this.loginLocation = loginLocation;
    }

    public String getBrowser()
    {
        return browser;
    }

    public void setBrowser(String browser)
    {
        this.browser = browser;
    }

    public String getOs()
    {
        return os;
    }

    public void setOs(String os)
    {
        this.os = os;
    }

    public Long getLoginTime()
    {
        return loginTime;
    }

    public void setLoginTime(Long loginTime)
    {
        this.loginTime = loginTime;
    }
}
