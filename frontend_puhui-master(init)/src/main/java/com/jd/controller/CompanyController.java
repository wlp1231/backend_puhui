package com.jd.controller;

import com.alibaba.fastjson.JSONArray;
import com.jd.bean.Activity;
import com.jd.bean.Company;
import com.jd.bean.Message;
import com.jd.bean.News;
import com.jd.service.ActivityService;
import com.jd.service.ICompanyService;
import com.jd.service.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    ActivityService activityService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private ICompanyService companyService;

    public static final Logger log = LoggerFactory.getLogger(CompanyController.class);


    @RequestMapping("/loginTel")
    @ResponseBody
    public String loginPhone(String tel) {
        int i = 0;
        //判断是邮箱还是手机号的正则表达式
        String em = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        String ph = "^[1][34578]\\d{9}$";
        if (tel.matches(ph)) {
            i = companyService.selectCompanyTelById(tel);
        } else if (tel.matches(em)) {
            i = companyService.selectCompanyEmaById(tel);
        }
        if (i > 0) {
            return "ok";
        }
        log.info("账号已激活");
        return "ng";
    }


    /*
     * 登录功能实现
     */
    @RequestMapping(value = "/Login.do", method = RequestMethod.POST)
    public String login(Company company, @RequestParam("kaptcha") String kaptcha, HttpServletResponse response, HttpServletRequest request) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        //获得系统验证码
        String kaptchaExpected = (String) request.getSession().getAttribute(
                com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
//        获得输入的账号密码
        String phoneNo = company.getComPhone();
        String psd = company.getComPwd();
        if (kaptcha == null || !kaptcha.equals(kaptchaExpected)) {
            log.info("验证码错误");
            out.print("<script language=\"javascript\">alert('验证码错误');window.location.href='/login'</script>");
        } else {
            //判断是邮箱还是手机号的正则表达式
            String em = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
            String ph = "^[1][34578]\\d{9}$";
            if (phoneNo.matches(ph)) {//手机号登录
                Company ph_member = companyService.selectCompanyByPhone(phoneNo, psd);
                if (ph_member == null) {
                    log.info("密码错误");
                    out.print("<script language=\"javascript\">alert('手机号或密码错误');window.location.href='/login'</script>");
                } else {
                    System.out.println("密码正确");
                    request.getSession().setAttribute("users", phoneNo);//用户名存入该用户的session 中
                    return "redirect:/index-2.html";
                }
            } else if (phoneNo.matches(em)) {//邮箱登录
                Company em_member = companyService.selectCompanyByEmail(phoneNo, psd);
                if (em_member == null) {
//                    return "redirect:/login.html";
                    out.print("<script language=\"javascript\">alert('邮箱账号或密码错误');window.location.href='/login'</script>");
                } else {
                    request.getSession().setAttribute("users", phoneNo);//用户名存入该用户的session 中
                    return "index-2";
                }
            } else{
                log.info("账号或密码格式都不正确!!");
                out.print("<script language=\"javascript\">alert('手机号或密码错误');window.location.href='/login'</script>");
            }
        }
        return "login";
    }

    //    注册验证码校验
    @RequestMapping("/myIdentify")
    @ResponseBody
    public String myIdentify(String idfy, HttpServletRequest request) {
        String kaptchaExpected = (String) request.getSession().getAttribute("identifyCode");
        System.out.println(idfy);
        if (!idfy.equalsIgnoreCase(kaptchaExpected)) {
            return "ng";
        } else {
            return "ok";
        }
    }

    /**
     * 注册
     *
     * @param company
     * @return
     */
    @RequestMapping("/register")
    public void registerCompany(Company company, HttpServletResponse response) throws IOException {
        Date d = new Date();
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        //生成激活码
        String activeCode = IDUtils.getUUID();
        //给激活码赋值
        company.setComRemark(activeCode);
        company.setComStatus("2");
        company.setComTime(d);
        int i = companyService.insertCompany(company);
        //邮件已发送
        out.print("<script language=\"javascript\">alert('请前往邮箱进行验证');window.location.href='/login'</script>");
    }

    /**
     * 校验激活码
     *
     * @param code
     * @return
     */
    @RequestMapping(value = "/checkCode")
    public String selectCompanyByActiveCode(String code) {
        Company company = companyService.selectCompanyByActiveCode(code);
        //如果用户不等于null，把用户状态修改status=0
        if (company != null) {
            company.setComStatus("3");
            //把code验证码清空，已经不需要了
            company.setComRemark("");
            companyService.updateCompany(company);
            return "redirect:/login.html";
        }
//注册失败
        return "/reg";
    }

    @RequestMapping("/name")
    @ResponseBody
    public String findCompanyComNo(@RequestParam("comNo") String comNo) {
        int i = companyService.findCompanyComNo(comNo);
        if (i > 0) {
            return "no";
        }
        return "ok";
    }

    @RequestMapping("/phone")
    @ResponseBody
    public String findCompanyComPhone(@RequestParam("comPhone") String comPhone) {
        int i = companyService.findCompanyComPhone(comPhone);
        if (i > 0) {
            return "no";
        }
        return "ok";
    }

    @GetMapping("/actlist")
    public String actlist(Model model) {
        return "actlist";
    }
    @PostMapping("/actlist")
    @ResponseBody
    public String actlist() {
        JSONArray ja = new JSONArray();

        List<Activity> activityList = activityService.showActivies();
        System.out.println("---------------------------------------");
        System.out.println(activityList);

        for (Activity a: activityList) {
            ja.add(a);
        }
        return ja.toString();
    }
    @RequestMapping("/act")
    @ResponseBody
    public String findById(@RequestParam("id") int id){
        System.out.println(id);
//        int id = Integer.parseInt(ids);
        Activity activity = activityService.findById(id);
        System.out.println(activity);
//        System.out.println(activity.getAct_title());
        JSONArray ja = new JSONArray();
        ja.add(activity);
        System.out.println(ja);
        return ja.toString();
    }

    @RequestMapping("/rate")
    public String rate(){
        return "rate";
    }
    @RequestMapping("/news")
    public String news(@RequestParam("id") int id, @RequestParam("type") int type){
        return "news";
    }
    @RequestMapping("/getNewsByType")
    @ResponseBody
    public String getNewsByType(@RequestParam("type") int type){
        List<News> list = newsService.findByType(type);
        JSONArray ja = new JSONArray();
        for (News n: list) {
            ja.add(n);
        }
        return ja.toString();
    }

    @RequestMapping("/getNewsById")
    @ResponseBody
    public String getNewsById(@RequestParam("id") int id){
        List<News> list = newsService.findById(id);
        JSONArray ja = new JSONArray();
        for (News n: list) {
            ja.add(n);
        }
        return ja.toString();
    }
    @GetMapping("/showMessage")
    public String showMessage(){
        return "messagelist";
    }



    public String getComNo(String phoneNo){
        String comNo = companyService.getComNo(phoneNo);
        return comNo;
    }




    @PostMapping("/showMessage")
    @ResponseBody
    public String showMessages(HttpServletRequest request) {
        String phoneNo = (String) request.getSession().getAttribute("users");
        String comNo = getComNo(phoneNo);
        JSONArray ja = new JSONArray();
        List<Message> messages = activityService.showMessages(comNo);
        System.out.println("---------------------------------------");
        System.out.println(messages);

        for (Message a: messages) {
            System.out.println(a);
            ja.add(a);
        }
        return ja.toString();
    }

    @GetMapping("/isLogin")
    @ResponseBody
    public String getComId(HttpServletRequest request){
        String phoneNo = (String) request.getSession().getAttribute("users");
        if(phoneNo!="" || phoneNo!=null){
            String comNo = companyService.getComNo(phoneNo);
            return comNo;
        }else{
            return "";
        }

    }

    @GetMapping("/vite")
    public String vite(@RequestParam("comId") int id){
        System.out.println(new Date());
        companyService.vite(id, new Date());
        return "redirect:/index";
    }

    @GetMapping("/messagetj")
    @ResponseBody
    public String messageTj(@RequestParam("msg") String msg, HttpServletRequest request){
        String phoneNo = (String) request.getSession().getAttribute("users");
        String comId = getComNo(phoneNo);
        int r = companyService.messagetj(msg, comId, new Date());
        if(r>0){
            return "留言成功";
        }else{
            return "留言失败，请稍后再试！";
        }

    }
    @GetMapping("/actshow")
    public String actshow(){
        return "actlist";
    }
    @GetMapping("/rateshow")
    public String rateshow(){
        return "rate";
    }
    @GetMapping("/newsshow")
    public String newsshow(){
        return "news";
    }

}