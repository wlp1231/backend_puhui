package com.qst.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qst.system.mapper.IdcardMapper;
import com.qst.system.domain.Idcard;
import com.qst.system.service.IIdcardService;

/**
 * 身份证识别Service业务层处理
 *
 * @author qst
 * @date 2022-06-28
 */
@Service
public class IdcardServiceImpl implements IIdcardService
{
    @Autowired
    private IdcardMapper idcardMapper;

    /**
     * 查询身份证识别
     *
     * @param id 身份证识别主键
     * @return 身份证识别
     */
    @Override
    public Idcard selectIdcardById(Long id)
    {
        return idcardMapper.selectIdcardById(id);
    }

    /**
     * 查询身份证识别列表
     *
     * @param idcard 身份证识别
     * @return 身份证识别
     */
    @Override
    public List<Idcard> selectIdcardList(Idcard idcard)
    {
        return idcardMapper.selectIdcardList(idcard);
    }

    /**
     * 新增身份证识别
     *
     * @param idcard 身份证识别
     * @return 结果
     */
    @Override
    public int insertIdcard(Idcard idcard)
    {
        return idcardMapper.insertIdcard(idcard);
    }

    /**
     * 修改身份证识别
     *
     * @param idcard 身份证识别
     * @return 结果
     */
    @Override
    public int updateIdcard(Idcard idcard)
    {
        return idcardMapper.updateIdcard(idcard);
    }

    /**
     * 批量删除身份证识别
     *
     * @param ids 需要删除的身份证识别主键
     * @return 结果
     */
    @Override
    public int deleteIdcardByIds(Long[] ids)
    {
        return idcardMapper.deleteIdcardByIds(ids);
    }

    /**
     * 删除身份证识别信息
     *
     * @param id 身份证识别主键
     * @return 结果
     */
    @Override
    public int deleteIdcardById(Long id)
    {
        return idcardMapper.deleteIdcardById(id);
    }
}