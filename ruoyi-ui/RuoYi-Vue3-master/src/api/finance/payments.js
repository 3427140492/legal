import request from '@/utils/request'

// 查询全所收支明细列表
export function listPayments(query) {
  return request({
    url: '/finance/payments/list',
    method: 'get',
    params: query
  })
}

// 查询我的收支
export function mylistPayments(query) {
  return request({
    url: '/finance/payments/mylist',
    method: 'get',
    params: query
  })
}

// 查询案件类型
export function listType(query) {
  return request({
    url: '/finance/payments/anlxlist',
    method: 'get',
    params: query
  })
}

// 查询全所收支明细列表树形菜单
export function cwInoutlistType() {
  return request({
    url: '/finance/payments/sxlist',
    method: 'get'
  })
}

// 查询全所收支明细详细
export function getPayments(id) {
  return request({
    url: '/finance/payments/' + id,
    method: 'get'
  })
}

// 新增全所收支明细
export function addPayments(data) {
  return request({
    url: '/finance/payments',
    method: 'post',
    data: data
  })
}

// 修改全所收支明细
export function updatePayments(data) {
  return request({
    url: '/finance/payments',
    method: 'put',
    data: data
  })
}

// 删除全所收支明细
export function delPayments(id) {
  return request({
    url: '/finance/payments/' + id,
    method: 'delete'
  })
}
