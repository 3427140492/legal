import request from '@/utils/request'

// 查询快递类型列表
export function listExpress(query) {
  return request({
    url: '/hr/express/list',
    method: 'get',
    params: query
  })
}

// 查询快递类型详细
export function getExpress(id) {
  return request({
    url: '/hr/express/' + id,
    method: 'get'
  })
}

// 新增快递类型
export function addExpress(data) {
  return request({
    url: '/hr/express',
    method: 'post',
    data: data
  })
}

// 修改快递类型
export function updateExpress(data) {
  return request({
    url: '/hr/express',
    method: 'put',
    data: data
  })
}

// 删除快递类型
export function delExpress(id) {
  return request({
    url: '/hr/express/' + id,
    method: 'delete'
  })
}
