import request from '@/utils/request'

// 查询还款列表
export function listRefund(query) {
  return request({
    url: '/system/refundchart/list',
    method: 'get',
    params: query
  })
}
