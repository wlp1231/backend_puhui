package com.jd.service;
import com.jd.bean.ComDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 企业详情Service接口
 *
 * @author zpbao
 * @date 2023-08-30
 */
public interface IComDetailsService
{
    /**
     * 查询企业详情
     *
     * @param cId 企业详情主键
     * @return 企业详情
     */
    public ComDetails selectComDetailsByCId(Long cId);

    /**
     * 查询企业详情列表
     *
     * @param comDetails 企业详情
     * @return 企业详情集合
     */
    public List<ComDetails> selectComDetailsList(ComDetails comDetails);

    /**
     * 新增企业详情
     *
     * @param comDetails 企业详情
     * @return 结果
     */
    public int insertComDetails(ComDetails comDetails);

    /**
     * 修改企业详情
     *
     * @param comDetails 企业详情
     * @return 结果
     */
    public int updateComDetails(ComDetails comDetails);

    /**
     * 批量删除企业详情
     *
     * @param cIds 需要删除的企业详情主键集合
     * @return 结果
     */
    public int deleteComDetailsByCIds(Long[] cIds);

    /**
     * 删除企业详情信息
     *
     * @param cId 企业详情主键
     * @return 结果
     */
    public int deleteComDetailsByCId(Long cId);

    /**
     * 实名认证
     * @param files
     * @return
     */
    public boolean attCompanies(ComDetails comDetails, List<MultipartFile> files);
}