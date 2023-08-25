import request from '@/utils/request'

// 查询人脸识别记录列表
export function listFacecheck(query) {
  return request({
    url: '/system/facecheck/list',
    method: 'get',
    params: query
  })
}

// 查询人脸识别记录详细
export function getFacecheck(id) {
  return request({
    url: '/system/facecheck/' + id,
    method: 'get'
  })
}

// 新增人脸识别记录
export function addFacecheck(data) {
  return request({
    url: '/system/facecheck',
    method: 'post',
    data: data
  })
}

// 修改人脸识别记录
export function updateFacecheck(data) {
  return request({
    url: '/system/facecheck',
    method: 'put',
    data: data
  })
}

// 删除人脸识别记录
export function delFacecheck(id) {
  return request({
    url: '/system/facecheck/' + id,
    method: 'delete'
  })
}

// 导出人脸识别记录
export function exportFacecheck(query) {
  return request({
    url: '/system/facecheck/export',
    method: 'get',
    params: query
  })
}