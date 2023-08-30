package com.jd.service.impl;

import com.jd.bean.Loans;
import com.jd.dao.LoansMapper;
import com.jd.service.ILoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * 按贷款编号查找贷款ID
     *
     * @param loaNo
     * @return
     */
    @Override
    public Long selectLoansByNo(String loaNo) {
        return loansMapper.selectLoansByNo(loaNo);
    }

    /**
     * 查询所有贷款信息
     *
     * @param comId
     * @return
     */
    @Override
    public List<Loans> selectLoansByComList(String comId) {
        return loansMapper.selectLoansByComList(comId);
    }

    /**
     * 查询贷款审批进度
     *
     * @param loaId
     * @return
     */
    @Override
    public List<Loans> selectLoansPlanList(int loaId) {
        return loansMapper.selectLoansPlanList(loaId);
    }

    /**
     * 查询贷款数量
     *
     * @param cId
     * @return
     */
    @Override
    public int selectLoansCount(String cId) {
        return loansMapper.selectLoansCount(cId);
    }

    /**
     * 查询待签约
     *
     * @param comId
     */
    @Override
    public List<Loans> selectLoansSign(String comId) {
        return loansMapper.selectLoansSign(comId);
    }


}