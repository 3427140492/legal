import request from '@/utils/request'

// 查询用印统计列表
export function listWithTheSeal(query) {
  return request({
    url: '/Seal/withTheSeal/list',
    method: 'get',
    params: query
  })
}

// 查询用印统计详细
export function getWithTheSeal(id) {
  return request({
    url: '/Seal/withTheSeal/' + id,
    method: 'get'
  })
}

// 新增用印统计
export function addWithTheSeal(data) {
  return request({
    url: '/Seal/withTheSeal',
    method: 'post',
    data: data
  })
}

// 修改用印统计
export function updateWithTheSeal(data) {
  return request({
    url: '/Seal/withTheSeal',
    method: 'put',
    data: data
  })
}

// 删除用印统计
export function delWithTheSeal(id) {
  return request({
    url: '/Seal/withTheSeal/' + id,
    method: 'delete'
  })
}
