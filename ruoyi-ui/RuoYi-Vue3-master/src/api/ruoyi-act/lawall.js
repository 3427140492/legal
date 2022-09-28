import request from '@/utils/request'

// 查询审批中心列表
export function listLawall(query) {
  return request({
    url: '/ruoyi-act/lawall/list',
    method: 'get',
    params: query
  })
}

// 查询审批中心详细
export function getLawall(id) {
  return request({
    url: '/ruoyi-act/lawall/' + id,
    method: 'get'
  })
}

// 新增审批中心
export function addLawall(data) {
  return request({
    url: '/ruoyi-act/lawall',
    method: 'post',
    data: data
  })
}

// 修改审批中心
export function updateLawall(data) {
  return request({
    url: '/ruoyi-act/lawall',
    method: 'put',
    data: data
  })
}

// 删除审批中心
export function delLawall(id) {
  return request({
    url: '/ruoyi-act/lawall/' + id,
    method: 'delete'
  })
}
