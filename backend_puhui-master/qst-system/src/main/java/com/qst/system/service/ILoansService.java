package com.qst.system.service;

import java.util.List;
import com.qst.system.domain.Loans;

/**
 * 贷款Service接口
 *
 * @author qst
 * @date 2021-09-23
 */
public interface ILoansService
{
    /*根据企业编号统计贷款总次数*/
    public int countLoan(String loaComNo);

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
     * 批量删除贷款
     *
     * @param loaIds 需要删除的贷款主键集合
     * @return 结果
     */
    public int deleteLoansByLoaIds(Long[] loaIds);

    /**
     * 删除贷款信息
     *
     * @param loaId 贷款主键
     * @return 结果
     */
    public int deleteLoansByLoaId(Long loaId);

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
     * 银行放款修改贷款状态
     * @param senLoaId
     */
    void updateLoaSenState(Long senLoaId);

    /**
     * 交易金额
     */
    int selectLoaMoney();

    /**
     * 贷款数量
     */
    int selectLoaCount();

    /*通过贷款编号找到企业编号*/
    Long selectLoaComNo(String loaNo);
}