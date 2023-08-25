import request from '@/utils/request'

// 查询银行卡识别信息列表
export function listBankcard(query) {
  return request({
    url: '/system/bankcard/list',
    method: 'get',
    params: query
  })
}

// 查询银行卡识别信息详细
export function getBankcard(id) {
  return request({
    url: '/system/bankcard/' + id,
    method: 'get'
  })
}

// 新增银行卡识别信息
export function addBankcard(data) {
  return request({
    url: '/system/bankcard',
    method: 'post',
    data: data
  })
}

// 修改银行卡识别信息
export function updateBankcard(data) {
  return request({
    url: '/system/bankcard',
    method: 'put',
    data: data
  })
}

// 删除银行卡识别信息
export function delBankcard(id) {
  return request({
    url: '/system/bankcard/' + id,
    method: 'delete'
  })
}

// 导出银行卡识别信息
export function exportBankcard(query) {
  return request({
    url: '/system/bankcard/export',
    method: 'get',
    params: query
  })
}

// 查询公司名称
export function getCompanyName() {
  return request({
    url: '/system/bankcard/comName',
    method: 'get'
  })
}
