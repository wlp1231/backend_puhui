import request from '@/utils/request'

// 查询用户积分列表
export function listScore(query) {
  return request({
    url: '/system/score/list',
    method: 'get',
    params: query
  })
}

// 查询用户积分详细
export function getScore(scoreId) {
  return request({
    url: '/system/score/' + scoreId,
    method: 'get'
  })
}

// 新增用户积分
export function addScore(data) {
  return request({
    url: '/system/score',
    method: 'post',
    data: data
  })
}

// 修改用户积分
export function updateScore(data) {
  return request({
    url: '/system/score',
    method: 'put',
    data: data
  })
}

// 删除用户积分
export function delScore(scoreId) {
  return request({
    url: '/system/score/' + scoreId,
    method: 'delete'
  })
}

// 导出用户积分
export function exportScore(query) {
  return request({
    url: '/system/score/export',
    method: 'get',
    params: query
  })
}

// 查询公司名称
export function getCompanyName() {
  return request({
    url: '/system/score/comName',
    method: 'get'
  })
}
