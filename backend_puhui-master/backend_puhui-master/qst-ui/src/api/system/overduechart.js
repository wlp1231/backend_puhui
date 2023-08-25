import request from '@/utils/request'

// 查询逾期列表
export function listOverdue(query) {
  return request({
    url: '/system/overduechart/list',
    method: 'get',
    params: query
  })
}
