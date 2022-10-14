import request from '@/utils/request'

// 查询档案借阅列表
export function listArchiveslibrary(query) {
  return request({
    url: '/record/archiveslibrary/list',
    method: 'get',
    params: query
  })
}

// 查询档案借阅详细
export function getArchiveslibrary(id) {
  return request({
    url: '/record/archiveslibrary/' + id,
    method: 'get'
  })
}


// 新增档案借阅
export function addArchiveslibrary(data) {
  return request({
    url: '/record/archiveslibrary',
    method: 'post',
    data: data
  })
}

// 修改档案借阅
export function updateArchiveslibrary(data) {
  return request({
    url: '/record/archiveslibrary',
    method: 'put',
    data: data
  })
}

// 删除档案借阅
export function delArchiveslibrary(id) {
  return request({
    url: '/record/archiveslibrary/' + id,
    method: 'delete'
  })
}

// 修改状态为Y
export function updatey(id) {
  return request({
    url: '/record/archiveslibrary/' + id,
    method: 'put'
  })
}

// 修改状态为n
export function updaten(nid) {
  return request({
    url: '/record/archiveslibrary/xgn/' + nid,
    method: 'put'
  })
}
