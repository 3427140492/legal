import request from '@/utils/request'

// 查询系统用户列表
export function listUser(query) {
  return request({
    url: '/hr/user/list',
    method: 'get',
    params: query
  })
}

// 查询系统用户详细
export function getUser(id) {
  return request({
    url: '/hr/user/' + id,
    method: 'get'
  })
}

// 新增系统用户
export function addUser(data) {
  return request({
    url: '/hr/user',
    method: 'post',
    data: data
  })
}

// 修改系统用户
export function updateUser(data) {
  return request({
    url: '/hr/user',
    method: 'put',
    data: data
  })
}

// 删除系统用户
export function delUser(id) {
  return request({
    url: '/hr/user/' + id,
    method: 'delete'
  })
}
