import request from '@/utils/request'

// 查询区列表
export function listAreas(query) {
  return request({
    url: '/common/areas/list',
    method: 'get',
    params: query
  })
}

// 查询区详细
export function getAreas(id) {
  return request({
    url: '/common/areas/' + id,
    method: 'get'
  })
}

// 新增区
export function addAreas(data) {
  return request({
    url: '/common/areas',
    method: 'post',
    data: data
  })
}

// 修改区
export function updateAreas(data) {
  return request({
    url: '/common/areas',
    method: 'put',
    data: data
  })
}

// 删除区
export function delAreas(id) {
  return request({
    url: '/common/areas/' + id,
    method: 'delete'
  })
}

// 导出区
export function exportAreas(query) {
  return request({
    url: '/common/areas/export',
    method: 'get',
    params: query
  })
}