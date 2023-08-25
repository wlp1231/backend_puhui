import request from '@/utils/request'

// 查询资质列表
export function listAptitude(query) {
  return request({
    url: '/system/aptitude/list',
    method: 'get',
    params: query
  })
}

// 查询资质详细
export function getAptitude(aptId) {
  return request({
    url: '/system/aptitude/' + aptId,
    method: 'get'
  })
}

// 查询资质详细
export function getAptitudeCompanyName() {
  return request({
    url: '/system/aptitude/comName',
    method: 'get'
  })
}

// 新增资质
export function addAptitude(data) {
  return request({
    url: '/system/aptitude',
    method: 'post',
    data: data
  })
}

// 修改资质
export function updateAptitude(data) {
  return request({
    url: '/system/aptitude',
    method: 'put',
    data: data
  })
}

// 删除资质
export function delAptitude(aptId) {
  return request({
    url: '/system/aptitude/' + aptId,
    method: 'delete'
  })
}

// 导出资质
export function exportAptitude(query) {
  return request({
    url: '/system/aptitude/export',
    method: 'get',
    params: query
  })
}
