package com.jd.dao;

import com.jd.bean.Risk;

/**
 * 风控Mapper接口
 */
public interface RiskMapper {

    /**
     * 新增风控
     */
    public int insertRisk(Risk risk);

}
