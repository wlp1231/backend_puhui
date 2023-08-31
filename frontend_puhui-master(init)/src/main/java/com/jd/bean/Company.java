package com.jd.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 公司基本信息
 *
 * @author zpbao
 * @version 1.0.0
 * @date 2023/08/30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    /**企业ID*/
    private int comId;

    /**企业注册代码*/
    private String comNo;

    /**企业名称*/
    private String comName;

    /**企业邮箱*/
    private String comEmail;

    /**企业电话*/
    private String comPhone;

    /**企业密码*/
    private String comPwd;

    /**注册时间*/
    private Date comTime;

    /**企业激活码*/
    private String comRemark;

    /**企业状态 0 正常 1停用 2 未激活 */
    private String comStatus;

    /**企业删除标识 0存在 2删除*/
    private String comDel;

    /**企业详情*/
    private ComDetails comDetails;
}