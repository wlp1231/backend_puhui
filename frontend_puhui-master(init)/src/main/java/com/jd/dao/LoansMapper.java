package com.jd.dao;


import com.jd.bean.Loans;

import java.util.List;

/**
 * 贷款Mapper接口
 *
 * @author qst
 * @date 2021-09-23
 */
public interface LoansMapper
{
    /**
     * 查询贷款
     *
     * @param loaId 贷款主键
     * @return 贷款
     */
    public Loans selectLoansByLoaId(Long loaId);

    /**
     * 查询贷款列表
     *
     * @param loans 贷款
     * @return 贷款集合
     */
    public List<Loans> selectLoansList(Loans loans);

    /**
     * 新增贷款
     *
     * @param loans 贷款
     * @return 结果
     */
    public int insertLoans(Loans loans);

    /**
     * 修改贷款
     *
     * @param loans 贷款
     * @return 结果
     */
    public int updateLoans(Loans loans);

    /**
     * 删除贷款
     *
     * @param loaId 贷款主键
     * @return 结果
     */
    public int deleteLoansByLoaId(Long loaId);

    /**
     * 批量删除贷款
     *
     * @param loaIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLoansByLoaIds(Long[] loaIds);

    /**
     * 查询审批贷款表
     * @param eId
     * @return
     */
    List<Loans> selectLoansById(Long eId);

    /**
     * 查询所有贷款
     * @return
     */
    List<Loans> selectLoansNoAll();

    /**
     * 平台审核通过，修改状态
     * @param exaLoaId
     */
    void updateLoansById(Long exaLoaId);

    /**
     * 平台审核通过，修改状态
     * @param exaLoaId
     */
    void updateLoansNoById(Long exaLoaId);

    /**
     * 查询银行贷款列表
     *
     * @param loans 贷款
     * @return 贷款集合
     */
    List<Loans> selectBankLoansList(Loans loans);

    /**
     * 银行通过审批
     * @param exaLoaId
     */
    void updateBankLoansById(Long exaLoaId);

    /**
     * 银行驳回审批
     * @param exaLoaId
     */
    void updateBankLoansNoById(Long exaLoaId);

    /**
     * 按贷款编号查找贷款ID
     * @param loaNo
     * @return
     */
    Long selectLoansByNo(String loaNo);

    /**
     * 查询所有贷款信息
     * @param comId
     * @return
     */
    List<Loans> selectLoansByComList(String comId);

    /**
     * 查询贷款审批进度
     * @param loaId
     * @return
     */
    List<Loans> selectLoansPlanList(int loaId);


    /**
     * 查询贷款数量
     * @param cId
     * @return
     */
    int selectLoansCount(String cId);

    /**
     * 查询待签约
     * @param comId
     */
    List<Loans> selectLoansSign(String comId);
}