package com.qst.system.service;

import com.qst.system.domain.Overdue;
import com.qst.system.domain.OverdueCompany;

import java.util.List;

/**
 * 逾期Service接口
 *
 * @author qst
 * @date 2021-09-28
 */
public interface IOverdueCompanyService
{


    /**
     * 查询逾期企业列表
     *
     * @param overdueCompany 逾期
     * @return 逾期集合
     */
    public List<OverdueCompany> selectOverdueCompanyList(OverdueCompany overdueCompany);

}
