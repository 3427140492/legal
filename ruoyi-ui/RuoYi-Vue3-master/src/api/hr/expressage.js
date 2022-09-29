import request from '@/utils/request'

// 查询快速登记列表
export function listExpressage(query) {
  return request({
    url: '/hr/expressage/list',
    method: 'get',
    params: query
  })
}

export function sendList(query) {
  return request({
    url: '/hr/expressage/slist',
    method: 'get',
    params: query
  })
}

export function expressList(query) {
  return request({
    url: '/hr/expressage/exlist',
    method: 'get',
    params: query
  })
}



// 查询快速登记详细
export function getExpressage(id) {
  return request({
    url: '/hr/expressage/' + id,
    method: 'get'
  })
}

// 新增快速登记
export function addExpressage(data) {
  return request({
    url: '/hr/expressage',
    method: 'post',
    data: data
  })
}

// 修改快速登记
export function updateExpressage(data) {
  return request({
    url: '/hr/expressage',
    method: 'put',
    data: data
  })
}

// 删除快速登记
export function delExpressage(id) {
  return request({
    url: '/hr/expressage/' + id,
    method: 'delete'
  })
}
