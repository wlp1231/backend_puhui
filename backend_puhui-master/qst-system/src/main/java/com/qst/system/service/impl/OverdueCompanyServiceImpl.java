package com.qst.system.service.impl;

import com.qst.system.domain.Overdue;
import com.qst.system.domain.OverdueCompany;
import com.qst.system.mapper.OverdueCompanyMapper;
import com.qst.system.mapper.OverdueMapper;
import com.qst.system.service.IOverdueCompanyService;
import com.qst.system.service.IOverdueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 逾期Service业务层处理
 *
 * @author qst
 * @date 2021-09-28
 */
@Service
public class OverdueCompanyServiceImpl implements IOverdueCompanyService
{
    @Autowired
    private OverdueCompanyMapper overdueCompanyMapper;


    /**
     * 查询逾期列表
     *
     * @param overdueCompany 逾期企业
     * @return 逾期
     */
    @Override
    public List<OverdueCompany> selectOverdueCompanyList(OverdueCompany overdueCompany) {
        return overdueCompanyMapper.selectOverdueCompanyList(overdueCompany);
    }
}
