import request from '@/utils/request'

// 查询我的发票列表
export function listInvoice(query) {
  return request({
    url: '/finance/invoice/list',
    method: 'get',
    params: query
  })
}

// 查询我的发票详细
export function getInvoice(id) {
  return request({
    url: '/finance/invoice/' + id,
    method: 'get'
  })
}

// 新增我的发票
export function addInvoice(data) {
  return request({
    url: '/finance/invoice',
    method: 'post',
    data: data
  })
}

// 修改我的发票
export function updateInvoice(data) {
  return request({
    url: '/finance/invoice',
    method: 'put',
    data: data
  })
}

// 删除我的发票
export function delInvoice(id) {
  return request({
    url: '/finance/invoice/' + id,
    method: 'delete'
  })
}
