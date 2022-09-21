import request from '@/utils/request'

// 查询全所电子归档列表
export function listDocument(query) {
  return request({
    url: '/record/document/list',
    method: 'get',
    params: query
  })
}

// 查询全所电子归档详细
export function getDocument(id) {
  return request({
    url: '/record/document/' + id,
    method: 'get'
  })
}

// 新增全所电子归档
export function addDocument(data) {
  return request({
    url: '/record/document',
    method: 'post',
    data: data
  })
}

// 修改全所电子归档
export function updateDocument(data) {
  return request({
    url: '/record/document',
    method: 'put',
    data: data
  })
}

// 删除全所电子归档
export function delDocument(id) {
  return request({
    url: '/record/document/' + id,
    method: 'delete'
  })
}
