import request from '@/utils/request'

// 查询放款列表
export function listSend(query) {
  return request({
    url: '/system/send/list',
    method: 'get',
    params: query
  })
}

// 用户状态修改
export function changeSendStatus(senId,senBankId,senLoaId,senComId, senStatus) {
  const data = {
    senId,
    senBankId,
    senLoaId,
    senComId,
    senStatus
  }
  return request({
    url: '/system/send/changeSendStatus',
    method: 'put',
    data: data
  })
}

// 查询放款详细
export function getSend(senId) {
  return request({
    url: '/system/send/' + senId,
    method: 'get'
  })
}

// 新增放款
export function addSend(data) {
  return request({
    url: '/system/send',
    method: 'post',
    data: data
  })
}

// 修改放款
export function updateSend(data) {
  return request({
    url: '/system/send',
    method: 'put',
    data: data
  })
}

// 删除放款
export function delSend(senId) {
  return request({
    url: '/system/send/' + senId,
    method: 'delete'
  })
}

// 导出放款
export function exportSend(query) {
  return request({
    url: '/system/send/export',
    method: 'get',
    params: query
  })
}
