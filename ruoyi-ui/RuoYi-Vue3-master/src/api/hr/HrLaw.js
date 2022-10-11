import request from '@/utils/request'

// 查询案件法律列表
export function listHrLaw(query) {
  return request({
    url: '/hr/HrLaw/list',
    method: 'get',
    params: query
  })
}

// 查询案件法律详细
export function getHrLaw(id) {
  return request({
    url: '/hr/HrLaw/' + id,
    method: 'get'
  })
}

// 新增案件法律
export function addHrLaw(data) {
  return request({
    url: '/hr/HrLaw',
    method: 'post',
    data: data
  })
}

// 修改案件法律
export function updateHrLaw(data) {
  return request({
    url: '/hr/HrLaw',
    method: 'put',
    data: data
  })
}

// 删除案件法律
export function delHrLaw(id) {
  return request({
    url: '/hr/HrLaw/' + id,
    method: 'delete'
  })
}
