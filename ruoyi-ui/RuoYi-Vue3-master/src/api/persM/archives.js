import request from '@/utils/request'

// 查询人事档案列表
export function listArchives(query) {
  return request({
    url: '/persM/archives/list',
    method: 'get',
    params: query
  })
}

// 查询人事档案详细
export function getArchives(id) {
  return request({
    url: '/persM/archives/' + id,
    method: 'get'
  })
}

// 新增人事档案
export function addArchives(data) {
  return request({
    url: '/persM/archives',
    method: 'post',
    data: data
  })
}

// 修改人事档案
export function updateArchives(data) {
  return request({
    url: '/persM/archives',
    method: 'put',
    data: data
  })
}

// 删除人事档案
export function delArchives(id) {
  return request({
    url: '/persM/archives/' + id,
    method: 'delete'
  })
}
