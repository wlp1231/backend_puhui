package com.jd.service.impl;
import com.jd.bean.Aptitude;
import com.jd.dao.AptitudeMapper;
import com.jd.service.IAptitudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资质Service业务层处理
 * 
 * @author qst
 * @date 2021-09-23
 */
@Service
public class AptitudeServiceImpl implements IAptitudeService
{
    @Autowired
    private AptitudeMapper aptitudeMapper;

    /**
     * 查询资质
     * 
     * @param aptId 资质主键
     * @return 资质
     */
    @Override
    public Aptitude selectAptitudeByAptId(Long aptId)
    {
        return aptitudeMapper.selectAptitudeByAptId(aptId);
    }

    /**
     * 查询资质列表
     * 
     * @param aptitude 资质
     * @return 资质
     */
    @Override
    public List<Aptitude> selectAptitudeList(Aptitude aptitude)
    {
        return aptitudeMapper.selectAptitudeList(aptitude);
    }

    /**
     * 新增资质
     * 
     * @param aptitude 资质
     * @return 结果
     */
    @Override
    public int insertAptitude(Aptitude aptitude)
    {
        return aptitudeMapper.insertAptitude(aptitude);
    }

    /**
     * 修改资质
     * 
     * @param aptitude 资质
     * @return 结果
     */
    @Override
    public int updateAptitude(Aptitude aptitude)
    {
        return aptitudeMapper.updateAptitude(aptitude);
    }

    /**
     * 批量删除资质
     * 
     * @param aptIds 需要删除的资质主键
     * @return 结果
     */
    @Override
    public int deleteAptitudeByAptIds(Long[] aptIds)
    {
        return aptitudeMapper.deleteAptitudeByAptIds(aptIds);
    }

    /**
     * 删除资质信息
     * 
     * @param aptId 资质主键
     * @return 结果
     */
    @Override
    public int deleteAptitudeByAptId(Long aptId)
    {
        return aptitudeMapper.deleteAptitudeByAptId(aptId);
    }

    /**
     * 审批资质数据
     *
     * @param eId
     * @return
     */
    @Override
    public List<Aptitude> selectAptitudeById(Long eId) {
        return aptitudeMapper.selectAptitudeById(eId);
    }
}
