import request from '@/utils/request'

// 查询用户等级列表
export function listLevel(query) {
  return request({
    url: '/system/scorelevel/list',
    method: 'get',
    params: query
  })
}

// 查询用户等级详细
export function getLevel(scoreId) {
  return request({
    url: '/system/scorelevel/' + scoreId,
    method: 'get'
  })
}

// 新增用户等级
export function addLevel(data) {
  return request({
    url: '/system/scorelevel',
    method: 'post',
    data: data
  })
}

// 修改用户等级
export function updateLevel(data) {
  return request({
    url: '/system/scorelevel',
    method: 'put',
    data: data
  })
}

// 删除用户等级
export function delLevel(scoreId) {
  return request({
    url: '/system/scorelevel/' + scoreId,
    method: 'delete'
  })
}

// 导出用户等级
export function exportLevel(query) {
  return request({
    url: '/system/scorelevel/export',
    method: 'get',
    params: query
  })
}

// 查询资质详细
export function getCompanyName() {
  return request({
    url: '/system/scorelevel/comName',
    method: 'get'
  })
}
