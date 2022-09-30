import request from '@/utils/request'

// 查询我的申请列表
export function listApplication(query) {
  return request({
    url: '/ruoyi-act/application/list',
    method: 'get',
    params: query
  })
}

// 查询我的申请列表下拉
export function listApplicationxl() {
  return request({
    url: '/ruoyi-act/application/xl',
    method: 'get'
  })
}
// 查询我的申请详细
export function getApplication(id) {
  return request({
    url: '/ruoyi-act/application/' + id,
    method: 'get'
  })
}

// 新增我的申请
export function addApplication(data) {
  return request({
    url: '/ruoyi-act/application',
    method: 'post',
    data: data
  })
}

// 修改我的申请
export function updateApplication(data) {
  return request({
    url: '/ruoyi-act/application',
    method: 'put',
    data: data
  })
}

// 删除我的申请
export function delApplication(id) {
  return request({
    url: '/ruoyi-act/application/' + id,
    method: 'delete'
  })
}
