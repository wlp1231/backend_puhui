package com.qst.system.service.impl;

import java.util.List;

import com.qst.common.core.domain.entity.Cities;
import com.qst.system.mapper.CitiesMapper;
import com.qst.system.service.ICitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 市Service业务层处理
 * 
 * @author qst
 * @date 2021-09-15
 */
@Service
public class CitiesServiceImpl implements ICitiesService
{
    @Autowired
    private CitiesMapper citiesMapper;

    /**
     * 按市ID查询
     *
     * @param cCity 市的Id
     * @return
     */
    @Override
    public Cities selectCityById(String cCity) {
        return citiesMapper.selectCityById(cCity);
    }

    /**
     * 按省ID查询市
     *
     * @return
     */
    @Override
    public List<Cities> selectCitiesByProId(String cTy) {
        return citiesMapper.selectCitiesByProId(cTy);
    }

    /**
     * 查询市
     * 
     * @param id 市主键
     * @return 市
     */
    @Override
    public Cities selectCitiesById(Long id)
    {
        return citiesMapper.selectCitiesById(id);
    }

    /**
     * 查询市列表
     * 
     * @param cities 市
     * @return 市
     */
    @Override
    public List<Cities> selectCitiesList(Cities cities)
    {
        return citiesMapper.selectCitiesList(cities);
    }

    /**
     * 新增市
     * 
     * @param cities 市
     * @return 结果
     */
    @Override
    public int insertCities(Cities cities)
    {
        return citiesMapper.insertCities(cities);
    }

    /**
     * 修改市
     * 
     * @param cities 市
     * @return 结果
     */
    @Override
    public int updateCities(Cities cities)
    {
        return citiesMapper.updateCities(cities);
    }

    /**
     * 批量删除市
     * 
     * @param ids 需要删除的市主键
     * @return 结果
     */
    @Override
    public int deleteCitiesByIds(Long[] ids)
    {
        return citiesMapper.deleteCitiesByIds(ids);
    }

    /**
     * 删除市信息
     * 
     * @param id 市主键
     * @return 结果
     */
    @Override
    public int deleteCitiesById(Long id)
    {
        return citiesMapper.deleteCitiesById(id);
    }
}
