import request from '@/utils/request'

// 查询审批列表
export function listExamine(query) {
  return request({
    url: '/system/examine/list',
    method: 'get',
    params: query
  })
}


// 查询审批详细
export function getExamine(exaId) {
  return request({
    url: '/system/examine/' + exaId,
    method: 'get'
  })
}

// 平台通过审批
export function addExamine(data) {
  return request({
    url: '/system/examine',
    method: 'post',
    data: data
  })
}
// 平台驳回审批
export function addNoExamine(data) {
  return request({
    url: '/system/examine/no',
    method: 'post',
    data: data
  })
}

// 平台通过审批
export function addBankExamine(data) {
  return request({
    url: '/system/examine/bank',
    method: 'post',
    data: data
  })
}
// 平台驳回审批
export function addBankNoExamine(data) {
  return request({
    url: '/system/examine/bankNo',
    method: 'post',
    data: data
  })
}

// 修改审批
export function updateExamine(data) {
  return request({
    url: '/system/examine',
    method: 'put',
    data: data
  })
}

// 删除审批
export function delExamine(exaId) {
  return request({
    url: '/system/examine/' + exaId,
    method: 'delete'
  })
}

// 导出审批
export function exportExamine(query) {
  return request({
    url: '/system/examine/export',
    method: 'get',
    params: query
  })
}
