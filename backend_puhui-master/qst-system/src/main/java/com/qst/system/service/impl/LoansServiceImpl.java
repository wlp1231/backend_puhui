package com.qst.system.service.impl;

import java.util.List;

import com.qst.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qst.system.mapper.LoansMapper;
import com.qst.system.domain.Loans;
import com.qst.system.service.ILoansService;

/**
 * 贷款Service业务层处理
 *
 * @author qst
 * @date 2021-09-23
 */
@Service
public class LoansServiceImpl implements ILoansService {
    @Autowired
    private LoansMapper loansMapper;

    @Override
    public int countLoan(String loaComNo) {
        return loansMapper.countLoan(loaComNo);
    }

    /**
     * 查询贷款
     *
     * @param loaId 贷款主键
     * @return 贷款
     */
    @Override
    public Loans selectLoansByLoaId(Long loaId) {
        return loansMapper.selectLoansByLoaId(loaId);
    }

    /**
     * 查询贷款列表
     *
     * @param loans 贷款
     * @return 贷款
     */
    @Override
    public List<Loans> selectLoansList(Loans loans) {
        return loansMapper.selectLoansList(loans);
    }

    /**
     * 新增贷款
     *
     * @param loans 贷款
     * @return 结果
     */
    @Override
    public int insertLoans(Loans loans) {
        loans.setLoaNo(IdUtils.getUUID_8());
        return loansMapper.insertLoans(loans);
    }

    /**
     * 修改贷款
     *
     * @param loans 贷款
     * @return 结果
     */
    @Override
    public int updateLoans(Loans loans) {
        return loansMapper.updateLoans(loans);
    }

    /**
     * 批量删除贷款
     *
     * @param loaIds 需要删除的贷款主键
     * @return 结果
     */
    @Override
    public int deleteLoansByLoaIds(Long[] loaIds) {
        return loansMapper.deleteLoansByLoaIds(loaIds);
    }

    /**
     * 删除贷款信息
     *
     * @param loaId 贷款主键
     * @return 结果
     */
    @Override
    public int deleteLoansByLoaId(Long loaId) {
        return loansMapper.deleteLoansByLoaId(loaId);
    }

    /**
     * 查询审批贷款表
     *
     * @param eId
     * @return
     */
    @Override
    public List<Loans> selectLoansById(Long eId) {
        return loansMapper.selectLoansById(eId);
    }

    /**
     * 查询所有贷款
     *
     * @return
     */
    @Override
    public List<Loans> selectLoansNoAll() {
        return loansMapper.selectLoansNoAll();
    }

    /**
     * 平台审核通过，修改状态
     *
     * @param exaLoaId
     */
    @Override
    public void updateLoansById(Long exaLoaId) {
        loansMapper.updateLoansById(exaLoaId);
    }

    /**
     * 平台审核通过，修改状态
     *
     * @param exaLoaId
     */
    @Override
    public void updateLoansNoById(Long exaLoaId) {
        loansMapper.updateLoansNoById(exaLoaId);
    }

    /**
     * 查询银行贷款列表
     *
     * @param loans 贷款
     * @return 贷款集合
     */
    @Override
    public List<Loans> selectBankLoansList(Loans loans) {
        return loansMapper.selectBankLoansList(loans);
    }

    /**
     * 银行通过审批
     *
     * @param exaLoaId
     */
    @Override
    public void updateBankLoansById(Long exaLoaId) {
        loansMapper.updateBankLoansById(exaLoaId);
    }

    /**
     * 银行驳回审批
     *
     * @param exaLoaId
     */
    @Override
    public void updateBankLoansNoById(Long exaLoaId) {
        loansMapper.updateBankLoansNoById(exaLoaId);
    }

    /**
     * 银行放款修改贷款状态
     *
     * @param senLoaId
     */
    @Override
    public void updateLoaSenState(Long senLoaId) {
        loansMapper.updateLoaSenState(senLoaId);
    }

    /**
     * 交易金额
     */
    @Override
    public int selectLoaMoney() {
        return loansMapper.selectLoaMoney();
    }

    /**
     * 贷款数量
     */
    @Override
    public int selectLoaCount() {
        return loansMapper.selectLoaCount();
    }

    @Override
    public Long selectLoaComNo(String loaNo) {
        return loansMapper.selectLoaComNo(loaNo);
    }
}