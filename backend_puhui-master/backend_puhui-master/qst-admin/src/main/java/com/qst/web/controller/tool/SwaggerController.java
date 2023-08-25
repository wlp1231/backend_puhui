package com.qst.web.controller.tool;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.qst.common.core.controller.BaseController;

/**
 * swagger 接口
 * 
 * @author qst
 */
@Api("swagger控制类")
@Controller
@RequestMapping("/tool/swagger")
public class SwaggerController extends BaseController
{
    @ApiOperation("swagger页面")
    @PreAuthorize("@ss.hasPermi('tool:swagger:view')")
    @GetMapping()
    public String index()
    {
        return redirect("/swagger-ui.html");
    }
}
