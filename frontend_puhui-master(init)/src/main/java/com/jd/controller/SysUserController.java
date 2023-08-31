package com.jd.controller;

import com.jd.bean.SysUser;
import com.jd.service.ISysUserService;
import com.jd.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;


    /**
     * 注册
     *
     * @param sysUser
     * @return
     */
    @RequestMapping("/register")
    public String registerUser(SysUser sysUser,@RequestParam("sex") String sex) {
        //生成激活码
        String activeCode = IDUtils.getUUID();
        //给激活码赋值
        sysUser.setRemark(activeCode);
        sysUser.setStatus("2");
        int i = sysUserService.insertSysUser(sysUser);
        //邮件已发送
        System.out.println(sex);
        return "redirect:/login.html";
    }

    /**
     * 校验激活码
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/checkCode")
    public String checkCode(String code) {
        SysUser user = sysUserService.selectUserByActiveCode(code);
        //如果用户不等于null，把用户状态修改status=1
        if (user != null) {
            user.setStatus("0");
            //把code验证码清空，已经不需要了
            user.setRemark("");
            sysUserService.updateSysUser(user);
            return "redirect:/login.html";
        }
//注册失败
        return "/reg";
    }
}
