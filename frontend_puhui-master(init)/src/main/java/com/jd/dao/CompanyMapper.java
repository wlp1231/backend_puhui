package com.jd.dao;

import com.jd.bean.BankCard;
import com.jd.bean.Company;
import com.jd.bean.IdCard;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 向公司注册表插入信息
 *
 * @author zpbao
 * @version 1.0.0
 * @date 2023/08/30
 */
public interface CompanyMapper {

    /**
     * 按邮箱登录
     * @return
     */
    Company selectCompanyByEmail(@Param("email") String email, @Param("psw") String psw);

    /**
     * 按电话登录
     * @return
     */
    Company selectCompanyByPhone(@Param("phone") String phone, @Param("psw") String psw);

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
    int updateCompanyPsw(@Param("cId") int cId,@Param("psw") String psw);

    /**
     * 修改认证状态
     * @param cId
     * @return
     */
    void updateCompanyAttState(int cId);

    /*风控0122_增加通过编号查询企业名称的方法*/
    /**
     * 通过企业编号查询企业名称
     * @param comId
     * @return
     */
    String selectComByComId(int comId);

    int faceMatch(String pNo, String face, int status, Date date, int comId);

    int getFaceMatchState(String pNo);

    int bankSave(String bankName, String bankCardNo, int comId);

    int idCardSave(String userName, String idCard, String address, String birthday, String gender, String nation,  String period, int comId);

    String getComNo(String phoneNo);

    void vite(@Param("id") int id,@Param("date") Date date);

    void viteAdd(@Param("id") int id,@Param("date") Date date);

    int getViteCount(int id);

    int messagetj(String s, String msg, Date date);

    IdCard getIdCardByComId(int comId);

    BankCard getBankCardByComId(int comId);

    List<IdCard> getUserIdInfo(int comId);

    String getStep(int comId);

    int setStep(int comId);

    int updateStep(@Param("step") String step,@Param("comId") int comId);

    int giveScore(int comId, int i, Date date);

    int getcredit(int comId);

    int updateloan(@Param("file") String file,@Param("nid") String nid);
}