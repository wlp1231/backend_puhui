package com.jd.dao;

import com.jd.bean.Bank;

import java.util.List;

/**
 * 银行Mapper接口
 * 
 * @author qst
 * @date 2021-09-22
 */
public interface BankMapper 
{
    /**
     * 查询银行
     * 
     * @param bankId 银行主键
     * @return 银行
     */
    public Bank selectBankByBankId(Long bankId);

    /**
     * 查询银行列表
     * 
     * @param bank 银行
     * @return 银行集合
     */
    public List<Bank> selectBankList(Bank bank);

    /**
     * 新增银行
     * 
     * @param bank 银行
     * @return 结果
     */
    public int insertBank(Bank bank);

    /**
     * 修改银行
     * 
     * @param bank 银行
     * @return 结果
     */
    public int updateBank(Bank bank);

    /**
     * 删除银行
     * 
     * @param bankId 银行主键
     * @return 结果
     */
    public int deleteBankByBankId(Long bankId);

    /**
     * 批量删除银行
     * 
     * @param bankIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBankByBankIds(Long[] bankIds);

    /**
     * 查询所有银行
     * @return
     */
    List<Bank> selectBankNames();
    /**
     * 查询银行利率
     * @param bId
     * @return
     */
    String selectBankRateById(Long bId);
}
