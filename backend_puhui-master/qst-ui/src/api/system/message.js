import request from '@/utils/request'

// 查询留言管理列表
export function listMessage(query) {
  return request({
    url: '/system/message/list',
    method: 'get',
    params: query
  })
}

// 查询留言管理详细
export function getMessage(mId) {
  return request({
    url: '/system/message/' + mId,
    method: 'get'
  })
}

// 新增留言管理
export function addMessage(data) {
  return request({
    url: '/system/message',
    method: 'post',
    data: data
  })
}

// 修改留言管理
export function updateMessage(data) {
  return request({
    url: '/system/message',
    method: 'put',
    data: data
  })
}

// 删除留言管理
export function delMessage(mId) {
  return request({
    url: '/system/message/' + mId,
    method: 'delete'
  })
}

// 导出留言管理
export function exportMessage(query) {
  return request({
    url: '/system/message/export',
    method: 'get',
    params: query
  })
}

// 查询公司名称
export function getCompanyName() {
  return request({
    url: '/system/message/comName',
    method: 'get'
  })
}
