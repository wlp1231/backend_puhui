package com.qst.system.service;

import com.qst.system.domain.BankSend;

import java.util.List;

/**
 * 放款Service接口
 *
 * @author qst
 * @date 2021-09-27
 */
public interface IBankSendChartService
{    /**
 * 查询放款列表
 *
 * @param bankSend 放款
 * @return 放款集合
 */
public List<BankSend> selectBankSendList(BankSend bankSend);


}