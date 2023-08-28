package com.qst.system.service.impl;

import com.qst.common.utils.uuid.IdUtils;
import com.qst.system.domain.Loans;
import com.qst.system.mapper.LoansChartMapper;
import com.qst.system.mapper.LoansMapper;
import com.qst.system.service.ILoansChartService;
import com.qst.system.service.ILoansService;
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
public class LoansChartServiceImpl implements ILoansChartService {
    @Autowired
    private LoansChartMapper loansMapper;

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

}