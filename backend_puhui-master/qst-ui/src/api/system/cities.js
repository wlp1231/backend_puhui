import request from '@/utils/request'

// 查询市列表
export function listCities(query) {
  return request({
    url: '/common/cities/list',
    method: 'get',
    params: query
  })
}

// 查询市详细
export function getCities(id) {
  return request({
    url: '/common/cities/' + id,
    method: 'get'
  })
}

// 新增市
export function addCities(data) {
  return request({
    url: '/common/cities',
    method: 'post',
    data: data
  })
}

// 修改市
export function updateCities(data) {
  return request({
    url: '/common/cities',
    method: 'put',
    data: data
  })
}

// 删除市
export function delCities(id) {
  return request({
    url: '/common/cities/' + id,
    method: 'delete'
  })
}

// 导出市
export function exportCities(query) {
  return request({
    url: '/common/cities/export',
    method: 'get',
    params: query
  })
}