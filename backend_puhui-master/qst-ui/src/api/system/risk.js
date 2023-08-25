import request from '@/utils/request'

// 查询风控管理列表
export function listRisk(query) {
  return request({
    url: '/system/risk/list',
    method: 'get',
    params: query
  })
}

// 查询风控管理详细
export function getRisk(riskId) {
  return request({
    url: '/system/risk/' + riskId,
    method: 'get'
  })
}

// 新增风控管理
export function addRisk(data) {
  return request({
    url: '/system/risk',
    method: 'post',
    data: data
  })
}

// 修改风控管理
export function updateRisk(data) {
  return request({
    url: '/system/risk',
    method: 'put',
    data: data
  })
}

// 删除风控管理
export function delRisk(riskId) {
  return request({
    url: '/system/risk/' + riskId,
    method: 'delete'
  })
}

// 计算额度
export function calcRisk(riskId) {
  return request({
    url: '/system/risk/calc/' + riskId,
    method: 'get'
  })
}

// 查询征信
export function investRisk(riskId) {
  return request({
    url: '/system/risk/invest/' + riskId,
    method: 'get'
  })
}
