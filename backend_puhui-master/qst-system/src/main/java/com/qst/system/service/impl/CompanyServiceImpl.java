package com.qst.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qst.system.mapper.CompanyMapper;
import com.qst.system.domain.Company;
import com.qst.system.service.ICompanyService;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author qst
 * @date 2021-09-10
 */
@Service
public class CompanyServiceImpl implements ICompanyService
{
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public List<Integer> selectAllComIds() {
        return companyMapper.selectAllComIds();
    }

    /**
     * 查询【请填写功能名称】
     *
     * @param comId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Company selectCompanyByComId(Long comId)
    {
        return companyMapper.selectCompanyByComId(comId);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param company 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Company> selectCompanyList(Company company)
    {
        return companyMapper.selectCompanyList(company);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param company 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCompany(Company company)
    {
        company.setComStatus("3");
        return companyMapper.insertCompany(company);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param company 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCompany(Company company)
    {
        return companyMapper.updateCompany(company);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param comIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCompanyByComIds(Long[] comIds)
    {
        return companyMapper.deleteCompanyByComIds(comIds);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param comId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteCompanyByComId(Long comId)
    {
        return companyMapper.deleteCompanyByComId(comId);
    }

    /**
     * 查询未实名企业
     **/
    @Override
    public List<Company> selectCompanyName() {
        return companyMapper.selectCompanyName();
    }

    /**
     * 实名后修改企业状态
     *
     * @param coId
     */
    @Override
    public void updateCompanyStatus(String coId) {
        companyMapper.updateCompanyStatus(coId);
    }

    /**
     * 查询已实名企业
     *
     * @return
     */
    @Override
    public List<Company> selectCompanyNameAutonym() {
        return companyMapper.selectCompanyNameAutonym();
    }

    /**
     * 查询企业数量
     *
     * @return
     */
    @Override
    public int selectCompanyCount() {
        return companyMapper.selectCompanyCount();
    }
}