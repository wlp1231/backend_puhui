import request from '@/utils/request'
import {praseStrEmpty} from "@/utils/ruoyi";

// 查询企业详情列表
export function listDetails(query) {
  return request({
    url: '/system/details/list',
    method: 'get',
    params: query
  })
}

// 查询企业名
export function getDetails(cId) {
  return request({
    url: '/system/details/' + praseStrEmpty(cId),
    method: 'get'
  })
}

// 查询企业名
export function getDetailsCompanyName(coId) {
  return request({
    url: '/system/details/comName/' + praseStrEmpty(coId),
    method: 'get'
  })
}

// 查询企业
export function getCompanyName() {
  return request({
    url: '/system/details/companyName',
    method: 'get'
  })
}

// 查询省
export function getPros() {
  return request({
    url: '/system/details/pro',
    method: 'get'
  })
}
// 查询市
export function getCities(cTy) {
  return request({
    url: '/system/details/cit' + praseStrEmpty(cTy),
    method: 'get'
  })
}
//查询公司注册省
export function getProCompany(cPro,cCity,cArea){
  return request({
    url: '/system/details/proCom/' + praseStrEmpty(cPro)+"/"+praseStrEmpty(cCity)+"/"+praseStrEmpty(cArea),
    method: 'get'
  })
}
//查询公司注册市
export function getCityCompany(cCity){
  return request({
    url: '/system/details/proCity/' + praseStrEmpty(cCity),
    method: 'get'
  })
}
//查询公司注册市
export function getAreaCompany(cArea){
  return request({
    url: '/system/details/proArea/' + praseStrEmpty(cArea),
    method: 'get'
  })
}

// 查企业详情查询
export function getCompanies(cId) {
  return request({
    url: '/system/details/companies/' + cId,
    method: 'get'
  })
}

// 新增企业详情
export function addDetails(data) {
  return request({
    url: '/system/details',
    method: 'post',
    data: data
  })
}

// 修改企业详情
export function updateDetails(data) {
  return request({
    url: '/system/details',
    method: 'put',
    data: data
  })
}

// 删除企业详情
export function delDetails(cId) {
  return request({
    url: '/system/details/' + cId,
    method: 'delete'
  })
}

// 导出企业详情
export function exportDetails(query) {
  return request({
    url: '/system/details/export',
    method: 'get',
    params: query
  })
}
