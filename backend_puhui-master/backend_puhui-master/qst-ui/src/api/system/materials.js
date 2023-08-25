import request from '@/utils/request'

// 查询材料列表
export function listMaterials(query) {
  return request({
    url: '/system/materials/list',
    method: 'get',
    params: query
  })
}

// 查询材料详细
export function getMaterials(lisId) {
  return request({
    url: '/system/materials/' + lisId,
    method: 'get'
  })
}

// 新增材料
export function addMaterials(data) {
  return request({
    url: '/system/materials',
    method: 'post',
    data: data
  })
}

// 修改材料
export function updateMaterials(data) {
  return request({
    url: '/system/materials',
    method: 'put',
    data: data
  })
}

// 删除材料
export function delMaterials(lisId) {
  return request({
    url: '/system/materials/' + lisId,
    method: 'delete'
  })
}

// 导出材料
export function exportMaterials(query) {
  return request({
    url: '/system/materials/export',
    method: 'get',
    params: query
  })
}