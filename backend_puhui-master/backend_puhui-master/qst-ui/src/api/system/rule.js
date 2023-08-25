import request from '@/utils/request'

// 查询分享规则管理列表
export function listRule(query) {
  return request({
    url: '/system/rule/list',
    method: 'get',
    params: query
  })
}

// 查询分享规则管理详细
export function getRule(sId) {
  return request({
    url: '/system/rule/' + sId,
    method: 'get'
  })
}

// 新增分享规则管理
export function addRule(data) {
  return request({
    url: '/system/rule',
    method: 'post',
    data: data
  })
}

// 修改分享规则管理
export function updateRule(data) {
  return request({
    url: '/system/rule',
    method: 'put',
    data: data
  })
}

// 删除分享规则管理
export function delRule(sId) {
  return request({
    url: '/system/rule/' + sId,
    method: 'delete'
  })
}

// 导出分享规则管理
export function exportRule(query) {
  return request({
    url: '/system/rule/export',
    method: 'get',
    params: query
  })
}