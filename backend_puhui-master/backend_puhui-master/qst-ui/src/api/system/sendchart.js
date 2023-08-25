import request from '@/utils/request'

// 查询放款列表
export function listSend(query) {
  return request({
    url: '/system/sendchart/list',
    method: 'get',
    params: query
  })
}


