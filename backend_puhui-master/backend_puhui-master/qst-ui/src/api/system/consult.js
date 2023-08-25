import request from '@/utils/request'

// 查询咨询列表
export function listConsult(query) {
  return request({
    url: '/system/consult/list',
    method: 'get',
    params: query
  })
}

// 查询咨询详细
export function getConsult(conId) {
  return request({
    url: '/system/consult/' + conId,
    method: 'get'
  })
}

// 新增咨询
export function addConsult(data) {
  return request({
    url: '/system/consult',
    method: 'post',
    data: data
  })
}

// 修改咨询
export function updateConsult(data) {
  return request({
    url: '/system/consult',
    method: 'put',
    data: data
  })
}

// 删除咨询
export function delConsult(conId) {
  return request({
    url: '/system/consult/' + conId,
    method: 'delete'
  })
}

// 导出咨询
export function exportConsult(query) {
  return request({
    url: '/system/consult/export',
    method: 'get',
    params: query
  })
}