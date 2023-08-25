package com.qst.web.controller.system;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.qst.common.config.QstConfig;
import com.qst.common.utils.StringUtils;

/**
 * 首页
 *
 * @author qst
 */
@Api("首页控制类")
@RestController
public class SysIndexController
{
    /** 系统基础配置 */
    @Autowired
    private QstConfig qstConfig;

    /**
     * 访问首页，提示语
     */
    @ApiOperation("首页显示")
    @RequestMapping("/")
    public String index()
    {
        return StringUtils.format("欢迎使用{}后台管理框架，当前版本：v{}，请通过前端地址访问。", qstConfig.getName(), qstConfig.getVersion());
    }
}
