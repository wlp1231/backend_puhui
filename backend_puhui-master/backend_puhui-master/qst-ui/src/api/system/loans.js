import request from '@/utils/request'

// 查询贷款列表
export function listLoans(query) {
  return request({
    url: '/system/loans/list',
    method: 'get',
    params: query
  })
}

// 查询银行审批贷款表
export function listBankLoans(be) {
  return request({
    url: '/system/loans/bankLoans',
    method: 'get',
    params: be
  })
}

// 查询审批贷款表
export function listLoansById(eId) {
  return request({
    url: '/system/loans/loansById/'+eId,
    method: 'get'
  })
}


// 查询审批材料表
export function listMaterialsById(eId) {
  return request({
    url: '/system/loans/materialsById/'+eId,
    method: 'get'
  })
}

// 查询审批资质表
export function listAptitudeById(eId) {
  return request({
    url: '/system/loans/aptitudeById/'+eId,
    method: 'get'
  })
}

// 查询贷款详细
export function getLoans(loaId) {
  return request({
    url: '/system/loans/' + loaId,
    method: 'get'
  })
}

// 新增贷款
export function addLoans(data) {
  return request({
    url: '/system/loans',
    method: 'post',
    data: data
  })
}

// 修改贷款
export function updateLoans(data) {
  return request({
    url: '/system/loans',
    method: 'put',
    data: data
  })
}

// 查询银行
export function getBankNames() {
  return request({
    url: '/system/loans/bankName',
    method: 'get'
  })
}
// 查询贷款编号
export function getLoansNo() {
  return request({
    url: '/system/loans/loansNo',
    method: 'get'
  })
}




// 删除贷款
export function delLoans(loaId) {
  return request({
    url: '/system/loans/' + loaId,
    method: 'delete'
  })
}

// 导出贷款
export function exportLoans(query) {
  return request({
    url: '/system/loans/export',
    method: 'get',
    params: query
  })
}
