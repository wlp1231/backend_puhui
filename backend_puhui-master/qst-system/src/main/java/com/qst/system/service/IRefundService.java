package com.qst.system.service;

import java.util.List;
import com.qst.system.domain.Refund;

/**
 * 还款Service接口
 *
 * @author qst
 * @date 2021-09-27
 */
public interface IRefundService
{
    /*根据企业编号统计还款次数*/
    public int countRefund(String refComNo);

    /**
     * 查询还款
     *
     * @param refId 还款主键
     * @return 还款
     */
    public Refund selectRefundByRefId(Long refId);

    /**
     * 查询还款列表
     *
     * @param refund 还款
     * @return 还款集合
     */
    public List<Refund> selectRefundList(Refund refund);

    /**
     * 新增还款
     *
     * @param refund 还款
     * @return 结果
     */
    public int insertRefund(Refund refund);

    /**
     * 修改还款
     *
     * @param refund 还款
     * @return 结果
     */
    public int updateRefund(Refund refund);

    /**
     * 批量删除还款
     *
     * @param refIds 需要删除的还款主键集合
     * @return 结果
     */
    public int deleteRefundByRefIds(Long[] refIds);

    /**
     * 删除还款信息
     *
     * @param refId 还款主键
     * @return 结果
     */
    public int deleteRefundByRefId(Long refId);
}