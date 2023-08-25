package com.qst.system.service.impl;

import java.util.List;

import com.qst.common.core.domain.entity.Provinces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qst.system.mapper.ProvincesMapper;
import com.qst.system.service.IProvincesService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author qst
 * @date 2021-09-15
 */
@Service
public class ProvincesServiceImpl implements IProvincesService 
{
    @Autowired
    private ProvincesMapper provincesMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Provinces selectProvincesById(Long id)
    {
        return provincesMapper.selectProvincesById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param provinces 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Provinces> selectProvincesList(Provinces provinces)
    {
        return provincesMapper.selectProvincesList(provinces);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param provinces 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertProvinces(Provinces provinces)
    {
        return provincesMapper.insertProvinces(provinces);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param provinces 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateProvinces(Provinces provinces)
    {
        return provincesMapper.updateProvinces(provinces);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteProvincesByIds(Long[] ids)
    {
        return provincesMapper.deleteProvincesByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteProvincesById(Long id)
    {
        return provincesMapper.deleteProvincesById(id);
    }

    /**
     * 查询全部省
     *
     * @return
     */
    @Override
    public List<Provinces> selectProvincesAll() {
        return provincesMapper.selectProvincesAll();
    }

    /**
     * 按省ID查询
     *
     * @param cPro 省的Id
     * @return
     */
    @Override
    public Provinces selectProById(String cPro) {
        return provincesMapper.selectProById(cPro);
    }

    @Override
    public List<Provinces> selectProvincesAndCity() {
        return provincesMapper.selectProvincesAndCity();
    }
}
