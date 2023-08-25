package com.qst.web.controller.monitor;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.qst.common.core.domain.AjaxResult;
import com.qst.framework.web.domain.Server;

/**
 * 服务器监控
 * 
 * @author qst
 */
@Api("服务器监控控制类")
@RestController
@RequestMapping("/monitor/server")
public class ServerController
{
    @ApiOperation("服务器监控查询")
    @PreAuthorize("@ss.hasPermi('monitor:server:list')")
    @GetMapping()
    public AjaxResult getInfo() throws Exception
    {
        Server server = new Server();
        server.copyTo();
        return AjaxResult.success(server);
    }
}
