import request from '@/utils/request'

// 查询友情链接管理列表
export function listLinks(query) {
  return request({
    url: '/system/links/list',
    method: 'get',
    params: query
  })
}

// 查询友情链接管理详细
export function getLinks(linkId) {
  return request({
    url: '/system/links/' + linkId,
    method: 'get'
  })
}

// 新增友情链接管理
export function addLinks(data) {
  return request({
    url: '/system/links',
    method: 'post',
    data: data
  })
}

// 修改友情链接管理
export function updateLinks(data) {
  return request({
    url: '/system/links',
    method: 'put',
    data: data
  })
}

// 删除友情链接管理
export function delLinks(linkId) {
  return request({
    url: '/system/links/' + linkId,
    method: 'delete'
  })
}

// 导出友情链接管理
export function exportLinks(query) {
  return request({
    url: '/system/links/export',
    method: 'get',
    params: query
  })
}