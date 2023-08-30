package com.qst.system.service.impl;

import com.qst.system.domain.BankSend;
import com.qst.system.mapper.BankSendChartMapper;
import com.qst.system.mapper.BankSendMapper;
import com.qst.system.service.IBankSendChartService;
import com.qst.system.service.IBankSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 放款Service业务层处理
 *
 * @author qst
 * @date 2021-09-27
 */
@Service
public class BankSendChartServiceImpl implements IBankSendChartService
{
    @Autowired
    private BankSendChartMapper bankSendMapper;
    /**
     * 查询放款列表
     *
     * @param bankSend 放款
     * @return 放款
     */
    @Override
    public List<BankSend> selectBankSendList(BankSend bankSend)
    {
        return bankSendMapper.selectBankSendList(bankSend);
    }

}