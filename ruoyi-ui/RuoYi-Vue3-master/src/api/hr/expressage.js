import request from '@/utils/request'

// 查询快速登记列表
export function listExpressage(query) {
  return request({
    url: '/hr/expressage/list',
    method: 'get',
    params: query
  })
}

//快递公司
export function sendList(query) {
  return request({
    url: '/hr/expressage/slist',
    method: 'get',
    params: query
  })
}

//快递类型
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



// 新增发送
export function addSendwaay(data) {
  return request({
    url: '/hr/sendwaay',
    method: 'post',
    data: data
  })
}

// 修改发送
export function updateSendwaay(data) {
  return request({
    url: '/hr/sendwaay',
    method: 'put',
    data: data
  })
}

// 删除发送
export function delSendwaay(id) {
  return request({
    url: '/hr/sendwaay/' + id,
    method: 'delete'
  })
}



