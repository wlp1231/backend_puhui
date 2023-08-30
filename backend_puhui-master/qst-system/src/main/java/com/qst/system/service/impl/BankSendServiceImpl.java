package com.qst.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qst.system.mapper.BankSendMapper;
import com.qst.system.domain.BankSend;
import com.qst.system.service.IBankSendService;

/**
 * 放款Service业务层处理
 *
 * @author qst
 * @date 2021-09-27
 */
@Service
public class BankSendServiceImpl implements IBankSendService
{
    @Autowired
    private BankSendMapper bankSendMapper;

    /**
     * 查询放款
     *
     * @param senId 放款主键
     * @return 放款
     */
    @Override
    public BankSend selectBankSendBySenId(Long senId)
    {
        return bankSendMapper.selectBankSendBySenId(senId);
    }

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

    /**
     * 新增放款
     *
     * @param bankSend 放款
     * @return 结果
     */
    @Override
    public int insertBankSend(BankSend bankSend)
    {
        return bankSendMapper.insertBankSend(bankSend);
    }

    /**
     * 修改放款
     *
     * @param bankSend 放款
     * @return 结果
     */
    @Override
    public int updateBankSend(BankSend bankSend)
    {
        return bankSendMapper.updateBankSend(bankSend);
    }

    /**
     * 批量删除放款
     *
     * @param senIds 需要删除的放款主键
     * @return 结果
     */
    @Override
    public int deleteBankSendBySenIds(Long[] senIds)
    {
        return bankSendMapper.deleteBankSendBySenIds(senIds);
    }

    /**
     * 删除放款信息
     *
     * @param senId 放款主键
     * @return 结果
     */
    @Override
    public int deleteBankSendBySenId(Long senId)
    {
        return bankSendMapper.deleteBankSendBySenId(senId);
    }
}