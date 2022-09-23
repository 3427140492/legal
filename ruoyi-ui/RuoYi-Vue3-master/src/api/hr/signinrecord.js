import request from '@/utils/request'

// 查询签到列表
export function listSigninrecord(query) {
  return request({
    url: '/hr/signinrecord/list',
    method: 'get',
    params: query
  })
}

// 查询签到详细
export function getSigninrecord(id) {
  return request({
    url: '/hr/signinrecord/' + id,
    method: 'get'
  })
}

// 新增签到
export function addSigninrecord(data) {
  return request({
    url: '/hr/signinrecord',
    method: 'post',
    data: data
  })
}

// 修改签到
export function updateSigninrecord(data) {
  return request({
    url: '/hr/signinrecord',
    method: 'put',
    data: data
  })
}

// 删除签到
export function delSigninrecord(id) {
  return request({
    url: '/hr/signinrecord/' + id,
    method: 'delete'
  })
}
