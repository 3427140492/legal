import request from '@/utils/request'

// 查询客户列表
export function listClient(query) {
  return request({
    url: '/system/client/list',
    method: 'get',
    params: query
  })
}

// 查询客户详细
export function getClient(id) {
  return request({
    url: '/system/client/' + id,
    method: 'get'
  })
}

// 新增客户
export function addClient(data) {
  return request({
    url: '/system/client',
    method: 'post',
    data: data
  })
}

// 修改客户
export function updateClient(data) {
  return request({
    url: '/system/client',
    method: 'put',
    data: data
  })
}

// 删除客户
export function delClient(id) {
  return request({
    url: '/system/client/' + id,
    method: 'delete'
  })
}
