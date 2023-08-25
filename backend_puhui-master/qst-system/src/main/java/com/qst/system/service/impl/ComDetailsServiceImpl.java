package com.qst.system.service.impl;

import java.util.List;

import com.qst.system.domain.ComDetails;
import org.springframework.stereotype.Service;
import com.qst.system.mapper.ComDetailsMapper;
import com.qst.system.service.IComDetailsService;

import javax.annotation.Resource;

/**
 * 企业详情Service业务层处理
 *
 * @author wen
 * @date 2021-09-11
 */
@Service
public class ComDetailsServiceImpl implements IComDetailsService
{
    @Resource
    private ComDetailsMapper comDetailsMapper;

    /**
     * 查询企业详情
     *
     * @param cId 企业详情主键
     * @return 企业详情
     */
    @Override
    public ComDetails selectComDetailsByCId(Long cId)
    {
        return comDetailsMapper.selectComDetailsByCId(cId);
    }

    /**
     * 查询企业详情列表
     *
     * @param comDetails 企业详情
     * @return 企业详情
     */
    @Override
    public List<ComDetails> selectComDetailsList(ComDetails comDetails)
    {
        return comDetailsMapper.selectComDetailsList(comDetails);
    }

    /**
     * 新增企业详情
     *
     * @param comDetails 企业详情
     * @return 结果
     */
    @Override
    public int insertComDetails(ComDetails comDetails)
    {
        return comDetailsMapper.insertComDetails(comDetails);
    }

    /**
     * 修改企业详情
     *
     * @param comDetails 企业详情
     * @return 结果
     */
    @Override
    public int updateComDetails(ComDetails comDetails)
    {
        return comDetailsMapper.updateComDetails(comDetails);
    }

    /**
     * 批量删除企业详情
     *
     * @param cIds 需要删除的企业详情主键
     * @return 结果
     */
    @Override
    public int deleteComDetailsByCIds(Long[] cIds)
    {
        return comDetailsMapper.deleteComDetailsByCIds(cIds);
    }

    /**
     * 删除企业详情信息
     *
     * @param cId 企业详情主键
     * @return 结果
     */
    @Override
    public int deleteComDetailsByCId(Long cId)
    {
        return comDetailsMapper.deleteComDetailsByCId(cId);
    }
}