package com.jd.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.jd.bean.*;
import com.jd.service.*;
import com.jd.utils.uuid.IdUtils_8;
import com.jd.utils.webcam.GsonUtils;
import com.jd.utils.webcam.HttpUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/lma")
public class LMAController {
    public String loaNo = "";
    public Company company = null;

    @Autowired
    private ILoansService loansService;

    @Autowired
    private IMaterialsService materialsService;

    @Autowired
    private IBankService bankService;

    @Autowired
    private IAptitudeService aptitudeService;

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private IComDetailsService comDetailsService;

    @Autowired
    private IRefundService refundService;

    @Autowired
    private IOverdueService overdueService;

    /*风控0122_引入风控的service*/
    @Autowired
    private IRiskService riskService;

    /**
     * 公司信息
     *
     * @return
     */
    @RequestMapping()
    public String insertLoansForm(HttpServletRequest request, String loaDown, Model model, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String pNo = (String) request.getSession().getAttribute("users");
//        company = companyService.selectCompanyLoginTel(pNo);
        if (!(pNo == null)) {
            //判断是邮箱还是手机号的正则表达式
            String em = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
            String ph = "^[1][34578]\\d{9}$";
            if (pNo.matches(em)) {
                company = companyService.selectCompanyLoginEma(pNo);
            } else if (pNo.matches(ph)) {
                company = companyService.selectCompanyLoginTel(pNo);
            }
            //查询逾期信息
            model.addAttribute("overdueAll", overdueService.selectOverdueComId(String.valueOf(company.getComId())));
//            查询签约信息
            model.addAttribute("sign", loansService.selectLoansSign(String.valueOf(company.getComId())));
            //查询原密码
            model.addAttribute("editPsw", company.getComPwd());
            //查询还款信息
            model.addAttribute("refundMessage", refundService.selectRefundListAll(String.valueOf(company.getComId())));
            //还款记录
            model.addAttribute("refundMegLog", refundService.selectRefundLog(String.valueOf(company.getComId())));
//            System.out.println(refundService.selectRefundLog(String.valueOf(company.getComId())));
            ComDetails comDetails = comDetailsService.selectComDetailsByCId(Long.valueOf(company.getComId()));
            if (!(comDetails == null)) {
                model.addAttribute("comDetails", comDetails);
                model.addAttribute("loaCount", loansService.selectLoansCount(String.valueOf(company.getComId())));
            } else {
                Provinces p = new Provinces();
                Cities c = new Cities();
                ComDetails com = new ComDetails(company, p, c);
                model.addAttribute("comDetails", com);
                model.addAttribute("loaCount", 0);
            }
            List<Loans> loansComList = loansService.selectLoansByComList(String.valueOf(company.getComId()));
            if (loansComList.isEmpty()) {
                model.addAttribute("loansLog", 0);
            }
            String faceState = companyService.getFaceMatchState(pNo);
            model.addAttribute("faceState",faceState);

            IdCard idCard = companyService.getIdCardByComId(company.getComId());
            if(idCard != null){
                model.addAttribute("idCard",idCard);
            }else{
                model.addAttribute("idCard",new IdCard());
            }
            BankCard bankCard = companyService.getBankCardByComId(company.getComId());
            if(bankCard != null){
                model.addAttribute("bankCard",bankCard);
            }else{
                model.addAttribute("bankCard",new BankCard());
            }

        } else {
            out.print("<script language=\"javascript\">alert('账号失效请重新登录');window.location.href='/login'</script>");
        }

        return loaDown;
    }


    /**
     * 提交贷款表
     *
     * @param loans
     * @return
     */
    @RequestMapping("/loans")
    @ResponseBody
    public String insertLoansForm(Loans loans, HttpServletRequest request) {
        String pNo = (String) request.getSession().getAttribute("users");
        company = companyService.selectCompanyLoginTel(pNo);
        //判断是邮箱还是手机号的正则表达式
        String em = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
        String ph = "^[1][34578]\\d{9}$";
        if (pNo.matches(em)) {
            company = companyService.selectCompanyLoginEma(pNo);
        } else if (pNo.matches(ph)) {
            company = companyService.selectCompanyLoginTel(pNo);
        }
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String d = dateFormat.format(date);
//        生成企业编号
        loaNo = IdUtils_8.getUUID_8() + d;
        loans.setLoaNo(loaNo);
//        贷款表创建时间
        loans.setLoaTime(date);
        loans.setLoaComNo(String.valueOf(company.getComId()));
//        int i = 1;
        //根据贷款表的银行id，查询对应银行的贷款期限
        long bankId = loans.getLoaBankId();
        Bank bank = bankService.selectBankByBankId(bankId);
        loans.setLoaLisId(Long.parseLong(bank.getBankLength()));

        int i = loansService.insertLoans(loans);
        /*风控0122_打印出需要的信息*/
        System.out.println("==贷款编号"+loans.getLoaNo());
        String loaNo = loans.getLoaNo();
        /*风控0122_通过企业编号找到企业名称*/
        String comName = companyService.selectCompanyByComId(Integer.parseInt(loans.getLoaComNo()));
        System.out.println("==企业编号"+comName);
        System.out.println("==贷款金额"+loans.getLoaMoney());
        Long loaMoney = loans.getLoaMoney();
        System.out.println("==贷款周期"+loans.getLoaLisId()); //目前前端还没有录入这一字段
        Long loaLisId = loans.getLoaLisId();
        if (i > 0) {
            /*风控0122_插入到风控risk表中*/
            Risk risk = new Risk(loaNo,comName,loaMoney,loaLisId,"0");
            System.out.println(risk);
            int abc = riskService.insertRisk(risk);
            System.out.println("risk插入成功");
            return "ok";
        } else {
            return "ng";
        }
    }

    /**
     * 实名认证
     *
     * @param request
     * @param comDetails
     * @return
     * @throws Exception
     */
    @RequestMapping("/att")
    @ResponseBody
    public boolean attCompany(MultipartHttpServletRequest request, ComDetails comDetails) {
        //获取上传的图片
        List<MultipartFile> files = request.getFiles("file");
        //无文件时直接返回页面
        if (files == null || files.isEmpty()) {
            return false;
        }
        //将企业Id存入详情表
        comDetails.setCoId(Long.valueOf(company.getComId()));
        //修改认证状态
        companyService.updateCompanyAttState(company.getComId());
        return comDetailsService.attCompanies(comDetails, files);
    }


    /*@RequestMapping( "/listWater")
    @ResponseBody
    public boolean addMaterials(Materials materials,@RequestParam("fileImg") MultipartFile fileImg) throws UnsupportedEncodingException {
        String filename = URLEncoder.encode(fileImg.getOriginalFilename(), "utf-8");
        System.out.println("获取对象"+fileImg.getOriginalFilename());

//        UploadUtils.doFileUpload(fileImg, materials, "lisSixRunWater");

            materialsService.insertMaterials(materials);
        return true;
    }*/

    /**
     * 提交材料表
     *
     * @param request
     * @param materials
     * @return
     * @throws Exception
     */
    @RequestMapping("/listWater")
    @ResponseBody
    public boolean addMaterials(MultipartHttpServletRequest request, Materials materials) {
        //获取贷款ID
        Long s = loansService.selectLoansByNo(loaNo);
        materials.setLisLoaId(s);
        materials.setLisComId(Long.valueOf(company.getComId()));
        List<MultipartFile> files = request.getFiles("file");

        //无文件时直接返回页面
        System.out.println(files);
        if (files == null || files.isEmpty()) {
            return false;
        }
        return materialsService.insert(materials, files);
    }

    /**
     * 提交资质表
     *
     * @param aptitude
     * @return
     */
    @RequestMapping("/apt")
    @ResponseBody
    public boolean insertAptForm(Aptitude aptitude) {
        aptitude.setAptLoaId(loansService.selectLoansByNo(loaNo));
        aptitude.setComId(Long.valueOf(company.getComId()));
        int i = 0;
        i = aptitudeService.insertAptitude(aptitude);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }


    @RequestMapping("/materials")
    @ResponseBody
    public String insertMaterialsForm(Materials materials, MultipartFile upFile[], HttpServletRequest request) throws IOException {
//        1.上传文件到 服务器的upload的文件夹
        String uploadPath = "upload";
        //上传实际位置
        String path = request.getSession().getServletContext().getRealPath(uploadPath);
        //使用逗号隔开
        StringBuffer stringBuffer = new StringBuffer();
        for (MultipartFile file : upFile) {
            if (!file.isEmpty()) {
                //为了避免文件重复
                stringBuffer.append(file.getOriginalFilename());
                stringBuffer.append(",");

                File filePath = new File(path, file.getOriginalFilename());
                if (!filePath.exists()) {
                    //创建目录
                    filePath.mkdirs();
                }
                file.transferTo(filePath);
            }
        }

        if (stringBuffer.length() > 0) {
            materials.setLisPawn(uploadPath);
            int i = 1;
//                    materialsService.insertMaterials(materials);
            System.out.println("i" + i);
            if (i > 0) {
                return "ok";
            } else {
                return "ng";
            }
        } else {
            return "ng";
        }
    }


    @RequestMapping("/upload")
    @ResponseBody
    public String uploadForm(MultipartFile upFile[], HttpServletRequest request) throws IOException {
//        1.上传文件到 服务器的upload的文件夹
        String uploadPath = "upload";
        //上传实际位置
        String path = request.getSession().getServletContext().getRealPath(uploadPath);
        //使用逗号隔开
        StringBuffer stringBuffer = new StringBuffer();
        for (MultipartFile file : upFile) {
            if (!file.isEmpty()) {
                //为了避免文件重复
                stringBuffer.append(file.getOriginalFilename());
                stringBuffer.append(",");

                File filePath = new File(path, file.getOriginalFilename());
                if (!filePath.exists()) {
                    //创建目录
                    filePath.mkdirs();
                }
                file.transferTo(filePath);
            }
        }

        return "ng";
    }

    /**
     * 查询银行列表
     *
     * @return
     */
    @RequestMapping("/banks")
    @ResponseBody
    public List<Bank> findBankAll() {
        List<Bank> bankList = bankService.selectBankNames();
        return bankList;
    }

    /**
     * 查询贷款列表
     *
     * @return
     */
    @RequestMapping("/queryLoans")
    @ResponseBody
    public List<Loans> findLoansAll() {
        List<Loans> loansComList = loansService.selectLoansByComList(String.valueOf(company.getComId()));
        return loansComList;
    }


    /**
     * 查询贷款进度
     *
     * @return
     */
    @RequestMapping("/loansPlan")
    @ResponseBody
    public List<Loans> findLoansPlan(int loaId) {
        List<Loans> loansPlan = loansService.selectLoansPlanList(loaId);
        return loansPlan;
    }

    /**
     * 查询利率
     *
     * @param bId
     * @return
     */
    @RequestMapping("/rateById")
    @ResponseBody
    public String findBankRate(Long bId) {
        return bankService.selectBankRateById(bId);
    }

    /**
     * 修改密码
     *
     * @return
     */
    @RequestMapping("/editPsw")
    @ResponseBody
    public boolean editPswCompanyById(String psw) {
        int i = companyService.updateCompanyPsw(company.getComId(), psw);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 银行信息
     *
     * @return
     */
    @RequestMapping("/bankSign")
    @ResponseBody
    public Bank findBankMessage(Long bId) {
        return bankService.selectBankByBankId(bId);
    }


    /**
     * 判断是否实名认证
     *
     * @return
     */
    @RequestMapping("/queryAtt")
    @ResponseBody
    public boolean queryCompanyAtt() {
        if (company.getComStatus().equals("3")){
            return true;
        }else {
            return false;
        }
    }


    @RequestMapping("/faceAccessToken")
    @ResponseBody
    public String faceAccessToken() {
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + "aKUKGnj7l0Dvy7SykdeYwEQd"
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + "Beb6k6MbUS5sS8KZtXH2QTb6vRrb10pt";
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            System.err.println("result:" + result);
            JSONObject jsonObject = new JSONObject(result);
            String access_token = jsonObject.getString("access_token");
            System.out.println(access_token);
            faceAccessToken = access_token;
            return "获取faceAccessToken："+faceAccessToken;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
            return "获取faceAccessToken失败";
        }
    }
    String faceAccessToken = "24.f794754083268a985b8aaa8b0e127b90.2592000.1664028632.282335-25530708";


    @RequestMapping("/faceMatch")
    @ResponseBody
    public String faceMatch(String face, HttpServletRequest request) {
        String pNo = (String) request.getSession().getAttribute("users");
//        System.out.println(pNo);

        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
        Map<String,Object> map = new HashMap<>();
        String img = face;

        map.put("image", img);
        map.put("image_type", "BASE64");

        map.put("face_type", "LIVE");
        map.put("quality_control", "LOW");
        map.put("group_id_list","customers");
        map.put("user_id",company.getComPhone());

        String param = GsonUtils.toJson(map);

//        String accessToken = "24.5c2a079587946e50d9db944b2e5b9495.2592000.1645174929.282335-25530708";
        String result = null;
        int score = 0;
        try {
            result = HttpUtil.post(url, faceAccessToken, "application/json", param);
            HashMap r = JSON.parseObject(result,HashMap.class);
            String s = r.get("result").toString();
            HashMap r2 = JSON.parseObject(s,HashMap.class);
            String s2 = r2.get("user_list").toString();
            List list = JSON.parseObject(s2, ArrayList.class);
            String s3 = list.get(0).toString();
            HashMap r3 = JSON.parseObject(s3,HashMap.class);
            String s4 = r3.get("score").toString();
            String[] s5 = s4.split("\\.");
            score = Integer.valueOf(s5[0]);


        } catch (Exception e) {
            e.printStackTrace();
        }
        int status = 0;
        if(score>=80){
            status = 1;
        }else{
            status = 0;
        }
        System.out.println(company.getComId());
        System.out.println("sddsafdsafsadfdsafsads");
        int r = companyService.faceMatch(pNo, face, status, new Date(),company.getComId());
        System.out.println(r);
        if(r>0){
            return result;
        }else{
            return null;
        }

    }

    @RequestMapping("/faceReg")
    @ResponseBody
    public String faceReg(String face, HttpServletRequest request) {
        String pNo = (String) request.getSession().getAttribute("users");
        System.out.println(pNo);
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        Map<String,Object> map = new HashMap<>();
        String img = face;

        map.put("image", img);
        map.put("image_type", "BASE64");

        map.put("face_type", "LIVE");
        map.put("quality_control", "LOW");
        map.put("group_id","customers");
        map.put("user_id",company.getComPhone());
        map.put("user_info", company.getComPhone());

        String param = GsonUtils.toJson(map);

//        String accessToken = "24.5c2a079587946e50d9db944b2e5b9495.2592000.1645174929.282335-25530708";
        String result = null;
        try {
            result = HttpUtil.post(url, faceAccessToken, "application/json", param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }





    @RequestMapping("/accessToken")
    @ResponseBody
    public String accessToken() {
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + "ZFPeWE1ISYhFj1Fxq5fEY2p6"
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + "u1Yidv3rFTCQqKAwkxiBCrFmwbhjLGzX";
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            System.err.println("result:" + result);
            JSONObject jsonObject = new JSONObject(result);
            String access_token = jsonObject.getString("access_token");
            System.out.println(access_token);
            word_accessToken = access_token;
            return "获取word_accessToken："+word_accessToken;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
            return "获取word_accessToken失败";
        }

    }

    String word_accessToken = "24.c4263442cccc9b3a8b5dc5690cdff99a.2592000.1664028880.282335-25566341";
    @RequestMapping("/bankCardCheck")
    @ResponseBody
    public String bankCardCheck(String bankCard, HttpServletRequest request) {
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/bankcard";
        System.out.println("abcdefg");
        try {
            String imgStr = bankCard;
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            System.out.println(imgStr);
            String param = "image=" + imgParam;
            String result = HttpUtil.post(url, word_accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "safdsafas";
    }

    @RequestMapping("/bankCardSave")
    @ResponseBody
    public String bankCardSave(String bankName, String bankCardNo, HttpServletRequest request) {
        String pNo = (String) request.getSession().getAttribute("users");
        String result = companyService.bankSave(bankName, bankCardNo, company.getComId());
        return result;
    }

    @RequestMapping("/idCardCheck")
    @ResponseBody
    public String idCardCheck(String idCard, HttpServletRequest request) {
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
        try {
            String imgStr = idCard;
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            System.out.println(imgStr);
            String param = "id_card_side=" + "front" + "&image=" + imgParam;
            String result = HttpUtil.post(url, word_accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "safdsafas";
    }

    @RequestMapping("/idCardCheckBack")
    @ResponseBody
    public String idCardCheck2(String idCard, HttpServletRequest request) {
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
        try {
            String imgStr = idCard;
            System.out.println(imgStr);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            String param = "id_card_side=" + "back" + "&image=" + imgParam;
            String result = HttpUtil.post(url, word_accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "safdsafas";
    }

    @RequestMapping("/idCardSave")
    @ResponseBody
    public String idCardSave(String userName, String idCard,String address, String birthday, String gender, String nation, String period, HttpServletRequest request) {
        String result = companyService.idCardSave(userName, idCard, address,birthday,gender,nation, period, company.getComId());
        System.out.println(period);
        return result;
    }
    @RequestMapping("/getUserIdInfo")
    @ResponseBody
    public String getUserIdInfo(HttpServletRequest request) {
        System.out.println(company.getComId());
        List<IdCard> idCard = companyService.getUserIdInfo(company.getComId());
        JSONArray ja = new JSONArray();
        ja.add(idCard.get(idCard.size()-1));

        return ja.toString();
    }

    @RequestMapping("/getBankCardInfo")
    @ResponseBody
    public String getBankCardInfo(HttpServletRequest request) {
        System.out.println(company.getComId());
        BankCard bankCard = companyService.getBankCardByComId(company.getComId());
        JSONArray ja = new JSONArray();
        ja.add(bankCard);

        return ja.toString();
    }

    @RequestMapping("/getStep")
    @ResponseBody
    public String getStep(HttpServletRequest request) {
        System.out.println(company.getComId());
        String step = companyService.getStep(company.getComId());
        System.out.println(step);
        if(step == null || step == ""){
            return "0";
        }else{
            return step;
        }
    }

    @RequestMapping("/setStep")
    @ResponseBody
    public int setStep(HttpServletRequest request) {
        int r = companyService.setStep(company.getComId());
        System.out.println(r);
        if(r>0){
            return 1;
        }else{
            return 0;
        }


    }
    @RequestMapping("/updateStep")
    @ResponseBody
    public int updateStep(String step, HttpServletRequest request) {
        System.out.println("sadfdsafsadfsadf");
        System.out.println(step);
        int r = companyService.updateStep(step, company.getComId());
        System.out.println(r);
        if(r>0){
            return 1;
        }else{
            return 0;
        }


    }
    @RequestMapping("/getComId")
    @ResponseBody
    public int getComId(HttpServletRequest request){
        return company.getComId();
    }
    @RequestMapping("/giveScore")
    @ResponseBody
    public int giveScore(HttpServletRequest request){
        return companyService.giveScore(company.getComId(), 200, new Date());
    }

    @RequestMapping("/getcredit")
    @ResponseBody
    public int getcredit(HttpServletRequest request){
        System.out.println(company.getComId());
        return companyService.getcredit(company.getComId());
    }


    @RequestMapping(value = { "/loadfile" }, method = RequestMethod.POST)
    @ResponseBody
    public String loadfile(HttpServletRequest request, @RequestParam("files") MultipartFile files) throws IOException {

        Date date = new Date();
        String strDateFormat = "yyyyMMddHHmmssSSS";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);

        String fileName = sdf.format(date)+ files.getOriginalFilename();
        String filepath = "D:/qst/uploadPath/upload/sign/"+fileName;
        File file = new File(filepath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if(!files.isEmpty()){
            byte [] bytes = files.getBytes();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
            bufferedOutputStream.write(bytes);
            bufferedOutputStream.close();
        }
        return "/profile/upload/sign/"+fileName;
    }

    @RequestMapping("/updateloan")
    @ResponseBody
    public int updateloan(String file, String nid, HttpServletRequest request) {
        System.out.println(file);
        System.out.println(nid);
        return companyService.updateloan(file, nid);
    }




}