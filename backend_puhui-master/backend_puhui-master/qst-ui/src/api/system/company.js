import request from '@/utils/request'

// 查询【企业】列表
export function listCompany(query) {
  return request({
    url: '/system/company/list',
    method: 'get',
    params: query
  })
}

// 查询【企业】详细
export function getCompany(comId) {
  return request({
    url: '/system/company/' + comId,
    method: 'get'
  })
}

// 新增【企业】
export function addCompany(data) {
  return request({
    url: '/system/company',
    method: 'post',
    data: data
  })
}

// 修改【企业】
export function updateCompany(data) {
  return request({
    url: '/system/company',
    method: 'put',
    data: data
  })
}

// 删除【企业】
export function delCompany(comId) {
  return request({
    url: '/system/company/' + comId,
    method: 'delete'
  })
}

// 导出【企业】
export function exportCompany(query) {
  return request({
    url: '/system/company/export',
    method: 'get',
    params: query
  })
}
