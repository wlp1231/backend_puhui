package com.jd.service.impl;

import com.jd.bean.Risk;
import com.jd.dao.RiskMapper;
import com.jd.service.IRiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 风控业务层处理
 */
@Service
public class RiskServiceImpl implements IRiskService {

    @Autowired
    private RiskMapper riskMapper;

    @Override
    public int insertRisk(Risk risk) {
        return riskMapper.insertRisk(risk);
    }
}
