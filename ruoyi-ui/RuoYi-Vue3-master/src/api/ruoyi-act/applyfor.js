import request from '@/utils/request'

// 查询用印申请列表
export function listApplyfor(query) {
  return request({
    url: '/ruoyi-act/applyfor/list',
    method: 'get',
    params: query
  })
}

// 查询用印申请详细
export function getApplyfor(id) {
  return request({
    url: '/ruoyi-act/applyfor/' + id,
    method: 'get'
  })
}

// 新增用印申请
export function addApplyfor(data) {
  return request({
    url: '/ruoyi-act/applyfor',
    method: 'post',
    data: data
  })
}

// 修改用印申请
export function updateApplyfor(data) {
  return request({
    url: '/ruoyi-act/applyfor',
    method: 'put',
    data: data
  })
}

// 删除用印申请
export function delApplyfor(id) {
  return request({
    url: '/ruoyi-act/applyfor/' + id,
    method: 'delete'
  })
}
