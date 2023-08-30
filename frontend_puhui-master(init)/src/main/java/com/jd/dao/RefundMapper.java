package com.jd.dao;

import com.jd.bean.Refund;

import java.util.List;

/**
 * 还款Mapper接口
 *
 * @author Yushun Xiang
 * @date 2021-09-27
 */
public interface RefundMapper
{
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
     * 删除还款
     *
     * @param refId 还款主键
     * @return 结果
     */
    public int deleteRefundByRefId(Long refId);

    /**
     * 批量删除还款
     *
     * @param refIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRefundByRefIds(Long[] refIds);

    /**
     * 查询还款信息
     * @param cId
     * @return
     */
    List<Refund> selectRefundListAll(String cId);

    /**
     * 查询还款记录
     * @param cId
     * @return
     */
    List<Refund> selectRefundLog(String cId);
}