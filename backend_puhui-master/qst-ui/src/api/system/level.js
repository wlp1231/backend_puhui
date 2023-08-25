import request from '@/utils/request'

// 查询等级设置列表
export function listLevel(query) {
  return request({
    url: '/system/level/list',
    method: 'get',
    params: query
  })
}

// 查询等级设置详细
export function getLevel(levelId) {
  return request({
    url: '/system/level/' + levelId,
    method: 'get'
  })
}

// 新增等级设置
export function addLevel(data) {
  return request({
    url: '/system/level',
    method: 'post',
    data: data
  })
}

// 修改等级设置
export function updateLevel(data) {
  return request({
    url: '/system/level',
    method: 'put',
    data: data
  })
}

// 删除等级设置
export function delLevel(levelId) {
  return request({
    url: '/system/level/' + levelId,
    method: 'delete'
  })
}

// 导出等级设置
export function exportLevel(query) {
  return request({
    url: '/system/level/export',
    method: 'get',
    params: query
  })
}