import request from '@/utils/request'

// 查询贷款列表
export function listLoans(query) {
  return request({
    url: '/system/loanschart/list',
    method: 'get',
    params: query
  })
}

