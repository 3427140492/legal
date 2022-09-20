import request from '@/utils/request'

// 查询公告通知列表
export function listNotice(query) {
  return request({
    url: '/hr/notice/list',
    method: 'get',
    params: query
  })
}

// 查询公告通知详细
export function getNotice(id) {
  return request({
    url: '/hr/notice/' + id,
    method: 'get'
  })
}

// 新增公告通知
export function addNotice(data) {
  return request({
    url: '/hr/notice',
    method: 'post',
    data: data
  })
}

// 修改公告通知
export function updateNotice(data) {
  return request({
    url: '/hr/notice',
    method: 'put',
    data: data
  })
}

// 删除公告通知
export function delNotice(id) {
  return request({
    url: '/hr/notice/' + id,
    method: 'delete'
  })
}
