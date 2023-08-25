import request from '@/utils/request'

// 查询分享数据管理列表
export function listRecord(query) {
  return request({
    url: '/system/record/list',
    method: 'get',
    params: query
  })
}

// 查询分享数据管理详细
export function getRecord(rId) {
  return request({
    url: '/system/record/' + rId,
    method: 'get'
  })
}

// 新增分享数据管理
export function addRecord(data) {
  return request({
    url: '/system/record',
    method: 'post',
    data: data
  })
}

// 修改分享数据管理
export function updateRecord(data) {
  return request({
    url: '/system/record',
    method: 'put',
    data: data
  })
}

// 删除分享数据管理
export function delRecord(rId) {
  return request({
    url: '/system/record/' + rId,
    method: 'delete'
  })
}

// 导出分享数据管理
export function exportRecord(query) {
  return request({
    url: '/system/record/export',
    method: 'get',
    params: query
  })
}

// 查询公司名称
export function getCompanyName() {
  return request({
    url: '/system/record/comName',
    method: 'get'
  })
}
