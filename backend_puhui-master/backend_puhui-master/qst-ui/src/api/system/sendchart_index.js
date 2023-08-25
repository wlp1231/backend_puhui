import request from '@/utils/request'

// 查询【首页中的 折线图的数据】数量
export function findData() {
  return request({
    url: '/system/sendchart/chart',
    method: 'get'
  })
}
