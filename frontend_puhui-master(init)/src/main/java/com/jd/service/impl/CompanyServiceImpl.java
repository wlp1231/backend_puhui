package com.jd.service.impl;

import com.jd.bean.BankCard;
import com.jd.bean.Company;
import com.jd.bean.IdCard;
import com.jd.common.service.MailService;
import com.jd.dao.CompanyMapper;
import com.jd.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CompanyServiceImpl implements ICompanyService {

    @Resource
    private CompanyMapper companyMapper;

    @Autowired
    private MailService mailService;

    /**
     * 按邮箱登录
     *
     * @param email
     * @param psw
     * @return
     */
    @Override
    public Company selectCompanyByEmail(String email, String psw) {
        return companyMapper.selectCompanyByEmail(email, psw);
    }

    /**
     * 按电话登录
     *
     * @param phone
     * @param psw
     * @return
     */
    @Override
    public Company selectCompanyByPhone(String phone, String psw) {
        return companyMapper.selectCompanyByPhone(phone, psw);
    }

    /**
     * 新增用户信息
     *
     * @param company 用户信息
     * @return 结果
     */
    @Override
    public int insertCompany(Company company) {
        int i = companyMapper.insertCompany(company);
        //获取激活码
        String code = company.getComRemark();
        //定义主题
        String subject = "来自普惠金融的注册激活邮件";
        //激活链接
        String conText = "<h5>欢迎注册普惠金融账号，请点击下方链接进行邮箱账号激活</h5><a href=\"http://localhost:9090/company/checkCode?code=" + code + "\" >激活请点击:" + code + "</a>";
        //发送激活邮件
        mailService.sendMimeMail(company.getComEmail(), subject, conText);
        return i;
    }

    /**
     * 根据激活码查询用户信息
     *
     * @param remark 激活码
     * @return 用户信息
     */
    @Override
    public Company selectCompanyByActiveCode(String remark) {
        return companyMapper.selectCompanyByActiveCode(remark);
    }

    /**
     * 修改企业信息
     *
     * @param company 用户信息
     * @return 结果
     */
    @Override
    public int updateCompany(Company company) {
        return companyMapper.updateCompany(company);
    }

    /**
     * 检测企业编号
     *
     * @param comNo
     * @return
     */
    @Override
    public int findCompanyComNo(String comNo) {
        return companyMapper.findCompanyComNo(comNo);
    }

    /**
     * 检测企业电话
     *
     * @param comPhone
     * @return
     */
    @Override
    public int findCompanyComPhone(String comPhone){
        return companyMapper.findCompanyComPhone(comPhone);
    }

    /**
     * 查询账号是否验证
     *
     * @param tel
     * @return
     */
    @Override
    public int selectCompanyTelById(String tel) {
        return companyMapper.selectCompanyTelById(tel);
    }

    /**
     * 查询账号是否验证
     *
     * @param ema
     * @return
     */
    @Override
    public int selectCompanyEmaById(String ema) {
        return companyMapper.selectCompanyEmaById(ema);
    }

    /**
     * 按电话号码查
     *
     * @param pNo
     * @return
     */
    @Override
    public Company selectCompanyLoginTel(String pNo) {
        return companyMapper.selectCompanyLoginTel(pNo);
    }

    /**
     * 按邮箱查
     *
     * @param eNo
     * @return
     */
    @Override
    public Company selectCompanyLoginEma(String eNo) {
        return companyMapper.selectCompanyLoginEma(eNo);
    }

    /**
     * 修改密码
     *
     * @param cId
     * @param psw
     * @return
     */
    @Override
    public int updateCompanyPsw(int cId, String psw) {
        return companyMapper.updateCompanyPsw(cId, psw);
    }

    /**
     * 修改认证状态
     *
     * @param cId
     * @return
     */
    @Override
    public void updateCompanyAttState(int cId) {
        companyMapper.updateCompanyAttState(cId);
    }


    /*风控0122_XXX*/

    /**
     * 通过企业编号查询企业名称
     * @param comId
     * @return
     */
    @Override
    public String selectCompanyByComId(int comId) {
        return companyMapper.selectComByComId(comId);
    }

    @Override
    public int faceMatch(String pNo, String face, int status, Date date, int comId) {

        return companyMapper.faceMatch(pNo, face, status, date, comId);
    }

    @Override
    public String getFaceMatchState(String pNo) {
        int count =  companyMapper.getFaceMatchState(pNo);
        if(count > 0){
            return "已通过";
        }else{
            return "未认证";
        }
    }

    @Override
    public String bankSave(String bankName, String bankCardNo, int comId) {
        int count = companyMapper.bankSave(bankName, bankCardNo, comId);
        if(count > 0){
            return "已添加成功";
        }else{
            return "未添加成功";
        }
    }

    @Override
    public String idCardSave(String userName, String idCard, String address, String birthday, String gender, String nation,  String period, int comId) {
        int count = companyMapper.idCardSave(userName, idCard, address, birthday, gender, nation, period, comId);
        System.out.println(period);
        if(count > 0){
            return "已添加成功";
        }else{
            return "未添加成功";
        }
    }

    @Override
    public String getComNo(String phoneNo) {
        return companyMapper.getComNo(phoneNo);
    }

    @Override
    public void vite(int id, Date date) {
        int count = companyMapper.getViteCount(id);
        if(count > 0){
            companyMapper.vite(id,date);
        }else{
            companyMapper.viteAdd(id, date);
        }

    }

    @Override
    public int messagetj(String msg, String comId, Date date) {
        return companyMapper.messagetj(msg, comId, date);
    }

    @Override
    public IdCard getIdCardByComId(int comId) {
        return companyMapper.getIdCardByComId(comId);
    }

    @Override
    public BankCard getBankCardByComId(int comId) {
        return companyMapper.getBankCardByComId(comId);
    }

    @Override
    public List<IdCard> getUserIdInfo(int comId) {
        return companyMapper.getUserIdInfo(comId);
    }

    @Override
    public String getStep(int comId) {
        return companyMapper.getStep(comId);
    }

    @Override
    public int setStep(int comId) {
        return companyMapper.setStep(comId);
    }

    @Override
    public int updateStep(String step, int comId) {
        return companyMapper.updateStep(step, comId);
    }

    @Override
    public int giveScore(int comId, int i, Date date) {
        return companyMapper.giveScore(comId, i, date);
    }

    @Override
    public int getcredit(int comId) {
        return companyMapper.getcredit(comId);
    }

    @Override
    public int updateloan(String file, String nid) {
        return companyMapper.updateloan(file, nid);
    }

}