package com.qst.system.mapper;

import java.util.List;
import com.qst.system.domain.BankSend;

/**
 * 放款Mapper接口
 *
 * @author qst
 * @date 2021-09-27
 */
public interface BankSendMapper
{
    /**
     * 查询放款
     *
     * @param senId 放款主键
     * @return 放款
     */
    public BankSend selectBankSendBySenId(Long senId);

    /**
     * 查询放款列表
     *
     * @param bankSend 放款
     * @return 放款集合
     */
    public List<BankSend> selectBankSendList(BankSend bankSend);

    /**
     * 新增放款
     *
     * @param bankSend 放款
     * @return 结果
     */
    public int insertBankSend(BankSend bankSend);

    /**
     * 修改放款
     *
     * @param bankSend 放款
     * @return 结果
     */
    public int updateBankSend(BankSend bankSend);

    /**
     * 删除放款
     *
     * @param senId 放款主键
     * @return 结果
     */
    public int deleteBankSendBySenId(Long senId);

    /**
     * 批量删除放款
     *
     * @param senIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBankSendBySenIds(Long[] senIds);
}