import request from '@/utils/request'

// 查询level_reward列表
export function listReward(query) {
  return request({
    url: '/system/reward/list',
    method: 'get',
    params: query
  })
}

// 查询level_reward详细
export function getReward(rewardId) {
  return request({
    url: '/system/reward/' + rewardId,
    method: 'get'
  })
}

// 新增level_reward
export function addReward(data) {
  return request({
    url: '/system/reward',
    method: 'post',
    data: data
  })
}

// 修改level_reward
export function updateReward(data) {
  return request({
    url: '/system/reward',
    method: 'put',
    data: data
  })
}

// 删除level_reward
export function delReward(rewardId) {
  return request({
    url: '/system/reward/' + rewardId,
    method: 'delete'
  })
}

// 导出level_reward
export function exportReward(query) {
  return request({
    url: '/system/reward/export',
    method: 'get',
    params: query
  })
}