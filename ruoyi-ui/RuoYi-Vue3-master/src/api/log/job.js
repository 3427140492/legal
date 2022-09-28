import request from '@/utils/request'

// 查询工作列表
export function listJob(query) {
  return request({
    url: '/log/job/list',
    method: 'get',
    params: query
  })
}

// 查询工作详细
export function getJob(id) {
  return request({
    url: '/log/job/' + id,
    method: 'get'
  })
}

// 新增工作
export function addJob(data) {
  return request({
    url: '/log/job',
    method: 'post',
    data: data
  })
}

// 修改工作
export function updateJob(data) {
  return request({
    url: '/log/job',
    method: 'put',
    data: data
  })
}

// 删除工作
export function delJob(id) {
  return request({
    url: '/log/job/' + id,
    method: 'delete'
  })
}
