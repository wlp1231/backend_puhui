import request from '@/utils/request'

// 查询逾期列表
export function listOverdue(query) {
  return request({
    url: '/system/overdue/list',
    method: 'get',
    params: query
  })
}

// 查询逾期详细
export function getOverdue(oveId) {
  return request({
    url: '/system/overdue/' + oveId,
    method: 'get'
  })
}

// 新增逾期
export function addOverdue(data) {
  return request({
    url: '/system/overdue',
    method: 'post',
    data: data
  })
}

// 修改逾期
export function updateOverdue(data) {
  return request({
    url: '/system/overdue',
    method: 'put',
    data: data
  })
}

// 删除逾期
export function delOverdue(oveId) {
  return request({
    url: '/system/overdue/' + oveId,
    method: 'delete'
  })
}

// 导出逾期
export function exportOverdue(query) {
  return request({
    url: '/system/overdue/export',
    method: 'get',
    params: query
  })
}