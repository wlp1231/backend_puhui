package com.qst.system.service;

import com.qst.common.core.domain.entity.Cities;

import java.util.List;

/**
 * 市Service接口
 * 
 * @author qst
 * @date 2021-09-15
 */
public interface ICitiesService 
{
    /**
     * 按市ID查询
     * @param cCity 市的Id
     * @return
     */
    Cities selectCityById(String cCity);
    /**
     * 按省ID查询市
     * @return
     */
    List<Cities> selectCitiesByProId(String cTy);
    /**
     * 查询市
     * 
     * @param id 市主键
     * @return 市
     */
    public Cities selectCitiesById(Long id);

    /**
     * 查询市列表
     * 
     * @param cities 市
     * @return 市集合
     */
    public List<Cities> selectCitiesList(Cities cities);

    /**
     * 新增市
     * 
     * @param cities 市
     * @return 结果
     */
    public int insertCities(Cities cities);

    /**
     * 修改市
     * 
     * @param cities 市
     * @return 结果
     */
    public int updateCities(Cities cities);

    /**
     * 批量删除市
     * 
     * @param ids 需要删除的市主键集合
     * @return 结果
     */
    public int deleteCitiesByIds(Long[] ids);

    /**
     * 删除市信息
     * 
     * @param id 市主键
     * @return 结果
     */
    public int deleteCitiesById(Long id);
}
