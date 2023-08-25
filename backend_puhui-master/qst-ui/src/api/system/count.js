import request from '@/utils/request'

// 查询【企业】数量
export function comCount() {
  return request({
    url: '/system/count/companyCount',
    method: 'get'
  })
}

// 查询【通知】数量
export function noticeCount() {
  return request({
    url: '/system/count/noticeCount',
    method: 'get'
  })
}

// 查询【交易额】金额
export function loaMoney() {
  return request({
    url: '/system/count/loaMoney',
    method: 'get'
  })
}

// 查询【订单】金额
export function loaCount() {
  return request({
    url: '/system/count/loaCount',
    method: 'get'
  })
}

