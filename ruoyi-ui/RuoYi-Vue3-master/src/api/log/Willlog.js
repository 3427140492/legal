import request from '@/utils/request'

// 查询全所日志列表
export function listWilllog(query) {
  return request({
    url: '/log/Willlog/list',
    method: 'get',
    params: query
  })
}

// 查询全所日志详细
export function getWilllog(id) {
  return request({
    url: '/log/Willlog/' + id,
    method: 'get'
  })
}

// 新增全所日志
export function addWilllog(data) {
  return request({
    url: '/log/Willlog',
    method: 'post',
    data: data
  })
}

// 修改全所日志
export function updateWilllog(data) {
  return request({
    url: '/log/Willlog',
    method: 'put',
    data: data
  })
}

// 删除全所日志
export function delWilllog(id) {
  return request({
    url: '/log/Willlog/' + id,
    method: 'delete'
  })
}
