package com.qst.system.mapper;

import com.qst.system.domain.BankSend;

import java.util.List;

/**
 * 放款Mapper接口
 *
 * @author qst
 * @date 2021-09-27
 */
public interface BankSendChartMapper
{
    /**
     * 查询放款列表
     *
     * @param bankSend 放款
     * @return 放款集合
     */
    public List<BankSend> selectBankSendList(BankSend bankSend);

}