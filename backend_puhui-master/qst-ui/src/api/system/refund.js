import request from '@/utils/request'

// 查询还款列表
export function listRefund(query) {
  return request({
    url: '/system/refund/list',
    method: 'get',
    params: query
  })
}

// 查询还款详细
export function getRefund(refId) {
  return request({
    url: '/system/refund/' + refId,
    method: 'get'
  })
}

// 新增还款
export function addRefund(data) {
  return request({
    url: '/system/refund',
    method: 'post',
    data: data
  })
}

// 修改还款
export function updateRefund(data) {
  return request({
    url: '/system/refund',
    method: 'put',
    data: data
  })
}

// 删除还款
export function delRefund(refId) {
  return request({
    url: '/system/refund/' + refId,
    method: 'delete'
  })
}

// 导出还款
export function exportRefund(query) {
  return request({
    url: '/system/refund/export',
    method: 'get',
    params: query
  })
}