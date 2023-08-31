package com.jd.service;

import com.jd.bean.BankCard;
import com.jd.bean.Company;
import com.jd.bean.IdCard;

import java.util.Date;
import java.util.List;

public interface ICompanyService {
    /**
     * 按邮箱登录
     * @return
     */
    Company selectCompanyByEmail(String email, String psw);

    /**
     * 按电话登录
     * @return
     */
    Company selectCompanyByPhone(String phone, String psw);

    /**
     * 新增用户信息
     *
     * @param company 用户信息
     * @return 结果
     */
    int insertCompany(Company company);

    /**
     * 根据激活码查询用户信息
     *
     * @param remark 激活码
     * @return 用户信息
     */
    Company selectCompanyByActiveCode(String remark);

    /**
     * 修改企业信息
     *
     * @param company 用户信息
     * @return 结果
     */
    int updateCompany(Company company);

    /**
     * 检测企业编号
     * @param comNo
     * @return
     */
    int findCompanyComNo(String comNo);

    /**
     * 检测企业电话
     * @param comPhone
     * @return
     */
    int findCompanyComPhone(String comPhone);

    /**
     * 查询账号是否验证
     * @param tel
     * @return
     */
    int selectCompanyTelById(String tel);

    /**
     * 查询账号是否验证
     * @param ema
     * @return
     */
    int selectCompanyEmaById(String ema);

    /**
     * 按电话号码查
     * @param pNo
     * @return
     */
    Company selectCompanyLoginTel(String pNo);

    /**
     * 按邮箱查
     * @param eNo
     * @return
     */
    Company selectCompanyLoginEma(String eNo);

    /**
     * 修改密码
     * @param psw
     * @return
     */
    int updateCompanyPsw(int cId,String psw);

    /**
     * 修改认证状态
     * @param cId
     * @return
     */
    void updateCompanyAttState(int cId);

    //人脸识别
    int faceMatch(String pNo, String face, int status, Date date, int ComId);

    /*风控0122_增加通过编号查询企业名称的方法*/
    /**
     * 通过企业编号查询企业名称
     * @param comId
     * @return
     */
    String selectCompanyByComId(int comId);

    //查询人脸识别认证状态
    String getFaceMatchState(String pNo);

    String bankSave(String bankName, String bankCardNo, int comId);

    String idCardSave(String userName, String idCard, String address, String birthday, String gender, String nation, String period, int comId);

    String getComNo(String phoneNo);

    void vite(int id, Date date);

    int messagetj(String msg, String comId, Date date);

    IdCard getIdCardByComId(int comId);

    BankCard getBankCardByComId(int comId);

    List<IdCard> getUserIdInfo(int comId);

    String getStep(int comId);

    int setStep(int comId);

    int updateStep(String step, int comId);

    int giveScore(int comId, int i, Date date);

    int getcredit(int comId);

    int updateloan(String file, String nid);
}