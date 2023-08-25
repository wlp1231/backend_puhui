import request from '@/utils/request'

// 查询身份证识别列表
export function listIdcard(query) {
  return request({
    url: '/system/idcard/list',
    method: 'get',
    params: query
  })
}

// 查询身份证识别详细
export function getIdcard(id) {
  return request({
    url: '/system/idcard/' + id,
    method: 'get'
  })
}

// 新增身份证识别
export function addIdcard(data) {
  return request({
    url: '/system/idcard',
    method: 'post',
    data: data
  })
}

// 修改身份证识别
export function updateIdcard(data) {
  return request({
    url: '/system/idcard',
    method: 'put',
    data: data
  })
}

// 删除身份证识别
export function delIdcard(id) {
  return request({
    url: '/system/idcard/' + id,
    method: 'delete'
  })
}

// 导出身份证识别
export function exportIdcard(query) {
  return request({
    url: '/system/idcard/export',
    method: 'get',
    params: query
  })
}

// 查询公司名称
export function getCompanyName() {
  return request({
    url: '/system/idcard/comName',
    method: 'get'
  })
}
