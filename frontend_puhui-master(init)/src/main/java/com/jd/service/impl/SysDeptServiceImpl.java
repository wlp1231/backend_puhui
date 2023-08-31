package com.jd.service.impl;

import com.jd.bean.SysDept;
import com.jd.dao.SysDeptMapper;
import com.jd.service.ISysDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 部门Service业务层处理
 * 
 * @author qst
 * @date 2021-09-04
 */
@Service
public class SysDeptServiceImpl implements ISysDeptService
{
    @Resource
    private SysDeptMapper sysDeptMapper;

    /**
     * 查询部门
     * 
     * @param deptId 部门主键
     * @return 部门
     */
    @Override
    public SysDept selectSysDeptByDeptId(Long deptId)
    {
        return sysDeptMapper.selectSysDeptByDeptId(deptId);
    }

    /**
     * 查询部门列表
     * 
     * @param sysDept 部门
     * @return 部门
     */
    @Override
    public List<SysDept> selectSysDeptList(SysDept sysDept)
    {
        return sysDeptMapper.selectSysDeptList(sysDept);
    }

    /**
     * 新增部门
     * 
     * @param sysDept 部门
     * @return 结果
     */
    @Override
    public int insertSysDept(SysDept sysDept)
    {
        return sysDeptMapper.insertSysDept(sysDept);
    }

    /**
     * 修改部门
     * 
     * @param sysDept 部门
     * @return 结果
     */
    @Override
    public int updateSysDept(SysDept sysDept)
    {
        return sysDeptMapper.updateSysDept(sysDept);
    }

    /**
     * 批量删除部门
     * 
     * @param deptIds 需要删除的部门主键
     * @return 结果
     */
    @Override
    public int deleteSysDeptByDeptIds(Long[] deptIds)
    {
        return sysDeptMapper.deleteSysDeptByDeptIds(deptIds);
    }

    /**
     * 删除部门信息
     * 
     * @param deptId 部门主键
     * @return 结果
     */
    @Override
    public int deleteSysDeptByDeptId(Long deptId)
    {
        return sysDeptMapper.deleteSysDeptByDeptId(deptId);
    }
}
