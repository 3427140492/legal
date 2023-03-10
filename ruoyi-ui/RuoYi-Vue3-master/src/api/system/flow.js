import request from '@/utils/request'

// 查询floww列表
export function listFlow(query) {
  return request({
    url: '/system/flow/list',
    method: 'get',
    params: query
  })
}

// 查询floww详细
export function getFlow(id) {
  return request({
    url: '/system/flow/' + id,
    method: 'get'
  })
}

// 新增floww
export function addFlow(data) {
  return request({
    url: '/system/flow',
    method: 'post',
    data: data
  })
}

// 修改floww
export function updateFlow(data) {
  return request({
    url: '/system/flow',
    method: 'put',
    data: data
  })
}

// 删除floww
export function delFlow(id) {
  return request({
    url: '/system/flow/' + id,
    method: 'delete'
  })
}
