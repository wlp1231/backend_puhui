import request from '@/utils/request'

// 查询平台信用管理列表
export function listCredit(query) {
  return request({
    url: '/system/credit/list',
    method: 'get',
    params: query
  })
}

// 查询平台信用管理详细
export function getCredit(comId) {
  return request({
    url: '/system/credit/' + comId,
    method: 'get'
  })
}

// 新增平台信用管理
export function addCredit(data) {
  return request({
    url: '/system/credit',
    method: 'post',
    data: data
  })
}

// 修改平台信用管理
export function updateCredit(data) {
  return request({
    url: '/system/credit',
    method: 'put',
    data: data
  })
}

// 删除平台信用管理
export function delCredit(comId) {
  return request({
    url: '/system/credit/' + comId,
    method: 'delete'
  })
}

// 导出平台信用管理
export function exportCredit(query) {
  return request({
    url: '/system/credit/export',
    method: 'get',
    params: query
  })
}