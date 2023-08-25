import request from '@/utils/request'

// 查询审批列表
export function listExamine(query) {
  return request({
    url: '/system/examinechart/list',
    method: 'get',
    params: query
  })
}
