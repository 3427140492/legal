import request from '@/utils/request'

// 查询建议投诉列表
export function listComplain(query) {
  return request({
    url: '/hr/complain/list',
    method: 'get',
    params: query
  })
}

// 查询建议投诉详细
export function getComplain(id) {
  return request({
    url: '/hr/complain/' + id,
    method: 'get'
  })
}

// 新增建议投诉
export function addComplain(data) {
  return request({
    url: '/hr/complain',
    method: 'post',
    data: data
  })
}

// 修改建议投诉
export function updateComplain(data) {
  return request({
    url: '/hr/complain',
    method: 'put',
    data: data
  })
}

// 删除建议投诉
export function delComplain(id) {
  return request({
    url: '/hr/complain/' + id,
    method: 'delete'
  })
}
