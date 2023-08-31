package com.jd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FaviconController {

    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
        //解决浏览器访问不到favicon的问题，添加此controller后日志不报错
    }
}

