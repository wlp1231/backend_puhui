package com.qst.system.mapper;

import java.util.List;
import com.qst.system.domain.Company;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author qst
 * @date 2021-09-10
 */
public interface CompanyMapper
{
    /*查询所有的企业id*/
    public List<Integer> selectAllComIds();

    /**
     * 查询【请填写功能名称】
     *
     * @param comId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Company selectCompanyByComId(Long comId);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param company 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Company> selectCompanyList(Company company);

    /**
     * 新增【请填写功能名称】
     *
     * @param company 【请填写功能名称】
     * @return 结果
     */
    public int insertCompany(Company company);

    /**
     * 修改【请填写功能名称】
     *
     * @param company 【请填写功能名称】
     * @return 结果
     */
    public int updateCompany(Company company);

    /**
     * 删除【请填写功能名称】
     *
     * @param comId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteCompanyByComId(Long comId);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param comIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCompanyByComIds(Long[] comIds);

    /**查询未实名企业**/
    List<Company> selectCompanyName();

    /**
     * 实名企业修改状态
     * @param coId
     */
    void updateCompanyStatus(String coId);

    /**
     * 查询已实名企业
     * @return
     */
    List<Company> selectCompanyNameAutonym();

    /**
     * 查询企业数量
     * @return
     */
    int selectCompanyCount();
}