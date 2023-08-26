package com.qst.system.mapper;


import java.util.List;
import com.qst.system.domain.Idcard;

/**
 * 身份证识别Mapper接口
 *
 * @author qst
 * @date 2022-06-28
 */
public interface IdcardMapper
{
    /**
     * 查询身份证识别
     *
     * @param id 身份证识别主键
     * @return 身份证识别
     */
    public Idcard selectIdcardById(Long id);

    /**
     * 查询身份证识别列表
     *
     * @param idcard 身份证识别
     * @return 身份证识别集合
     */
    public List<Idcard> selectIdcardList(Idcard idcard);

    /**
     * 新增身份证识别
     *
     * @param idcard 身份证识别
     * @return 结果
     */
    public int insertIdcard(Idcard idcard);

    /**
     * 修改身份证识别
     *
     * @param idcard 身份证识别
     * @return 结果
     */
    public int updateIdcard(Idcard idcard);

    /**
     * 删除身份证识别
     *
     * @param id 身份证识别主键
     * @return 结果
     */
    public int deleteIdcardById(Long id);

    /**
     * 批量删除身份证识别
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteIdcardByIds(Long[] ids);
}