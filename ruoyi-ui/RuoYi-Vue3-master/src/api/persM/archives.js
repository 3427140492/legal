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


// 查询工作经历
export function getWorkArchives(id) {
  return request({
    url: '/persM/archives/queryWork/' + id,
    method: 'get'
  })
}

// 查询工作经历
export function getWork(id) {
  return request({
    url: '/persM/archives/queryWorkQuery/' + id,
    method: 'get'
  })
}


// 新增工作经历
export function addWorkhistory(data) {
  return request({
    url: '/persM/archives/addWork',
    method: 'post',
    data: data
  })
}

// 修改工作经历
export function updateWorkhistory(data) {
  return request({
    url: '/persM/archives/updateWork',
    method: 'put',
    data: data
  })
}

// 删除工作经历
export function delWorkhistory(id) {
  return request({
    url: '/persM/archives/removeWork/' + id,
    method: 'delete'
  })
}

// 查询教育经历详细
export function getDegree(id) {
  return request({
    url: '/persM/archives/Degree/' + id,
    method: 'get'
  })
}