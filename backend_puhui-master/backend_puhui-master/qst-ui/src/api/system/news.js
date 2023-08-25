import request from '@/utils/request'

// 查询宣传信息管理列表
export function listNews(query) {
  return request({
    url: '/system/news/list',
    method: 'get',
    params: query
  })
}

// 查询宣传信息管理详细
export function getNews(nId) {
  return request({
    url: '/system/news/' + nId,
    method: 'get'
  })
}

// 新增宣传信息管理
export function addNews(data) {
  return request({
    url: '/system/news',
    method: 'post',
    data: data
  })
}

// 修改宣传信息管理
export function updateNews(data) {
  return request({
    url: '/system/news',
    method: 'put',
    data: data
  })
}

// 删除宣传信息管理
export function delNews(nId) {
  return request({
    url: '/system/news/' + nId,
    method: 'delete'
  })
}

// 导出宣传信息管理
export function exportNews(query) {
  return request({
    url: '/system/news/export',
    method: 'get',
    params: query
  })
}