import request from '@/utils/request'

// 查询发送列表
export function listSendwaay(query) {
  return request({
    url: '/hr/sendwaay/list',
    method: 'get',
    params: query
  })
}

// 查询发送详细
export function getSendwaay(id) {
  return request({
    url: '/hr/sendwaay/' + id,
    method: 'get'
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
