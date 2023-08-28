package com.qst.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qst.system.mapper.FacecheckMapper;
import com.qst.system.domain.Facecheck;
import com.qst.system.service.IFacecheckService;

/**
 * 人脸识别记录Service业务层处理
 *
 * @author zpbao
 * @date 2023-08-26
 */
@Service
public class FacecheckServiceImpl implements IFacecheckService
{
    @Autowired
    private FacecheckMapper facecheckMapper;

    /**
     * 查询人脸识别记录
     *
     * @param id 人脸识别记录主键
     * @return 人脸识别记录
     */
    @Override
    public Facecheck selectFacecheckById(Long id)
    {
        return facecheckMapper.selectFacecheckById(id);
    }

    /**
     * 查询人脸识别记录列表
     *
     * @param facecheck 人脸识别记录
     * @return 人脸识别记录
     */
    @Override
    public List<Facecheck> selectFacecheckList(Facecheck facecheck)
    {
        return facecheckMapper.selectFacecheckList(facecheck);
    }

    /**
     * 新增人脸识别记录
     *
     * @param facecheck 人脸识别记录
     * @return 结果
     */
    @Override
    public int insertFacecheck(Facecheck facecheck)
    {
        return facecheckMapper.insertFacecheck(facecheck);
    }

    /**
     * 修改人脸识别记录
     *
     * @param facecheck 人脸识别记录
     * @return 结果
     */
    @Override
    public int updateFacecheck(Facecheck facecheck)
    {
        return facecheckMapper.updateFacecheck(facecheck);
    }

    /**
     * 批量删除人脸识别记录
     *
     * @param ids 需要删除的人脸识别记录主键
     * @return 结果
     */
    @Override
    public int deleteFacecheckByIds(Long[] ids)
    {
        return facecheckMapper.deleteFacecheckByIds(ids);
    }

    /**
     * 删除人脸识别记录信息
     *
     * @param id 人脸识别记录主键
     * @return 结果
     */
    @Override
    public int deleteFacecheckById(Long id)
    {
        return facecheckMapper.deleteFacecheckById(id);
    }
}