import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listProvinces(query) {
  return request({
    url: '/system/provinces/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getProvinces(id) {
  return request({
    url: '/system/provinces/' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addProvinces(data) {
  return request({
    url: '/system/provinces',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateProvinces(data) {
  return request({
    url: '/system/provinces',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delProvinces(id) {
  return request({
    url: '/system/provinces/' + id,
    method: 'delete'
  })
}

// 导出【请填写功能名称】
export function exportProvinces(query) {
  return request({
    url: '/system/provinces/export',
    method: 'get',
    params: query
  })
}