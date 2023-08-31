package com.jd.controller;

import com.jd.bean.SysNotice;
import com.jd.service.ISysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/me")
public class MessageController {

    @Autowired
    private ISysNoticeService sysNoticeService;

    @RequestMapping()
    public String mes(String mgs, Model model){

        model.addAttribute("message",  sysNoticeService.selectNoticeByAll());
        return mgs;
    }

    @RequestMapping("/notice")
    @ResponseBody
    public SysNotice mesNotice(Long noId){
        return sysNoticeService.selectNoticeById(noId);
    }

    @RequestMapping("/megCount")
    @ResponseBody
    public int megCount(){
        return sysNoticeService.selectNoticeCount();
    }
}
