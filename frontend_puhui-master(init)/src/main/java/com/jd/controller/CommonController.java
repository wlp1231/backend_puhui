package com.jd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

@Controller
public class CommonController {

    /**
     * 页面跳转
     * @param pg
     * @return
     */
    @RequestMapping("/{pg}")
    public String goReturn(@PathVariable String pg){
        return pg;
    }

    @RequestMapping("/signOut")
    public String signOut(HttpSession session, SessionStatus sessionStatus){
        session.invalidate();
        sessionStatus.setComplete();
        System.out.println("清除session");
        return "login";
    }
}
