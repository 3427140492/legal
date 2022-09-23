import request from '@/utils/request'

// 查询收支明细列表
export function listInout(query) {
  return request({
    url: '/record/inout/list',
    method: 'get',
    params: query
  })
}

// 查询收支明细详细
export function getInout(id) {
  return request({
    url: '/record/inout/' + id,
    method: 'get'
  })
}

// 新增收支明细
export function addInout(data) {
  return request({
    url: '/record/inout',
    method: 'post',
    data: data
  })
}

// 修改收支明细
export function updateInout(data) {
  return request({
    url: '/record/inout',
    method: 'put',
    data: data
  })
}

// 删除收支明细
export function delInout(id) {
  return request({
    url: '/record/inout/' + id,
    method: 'delete'
  })
}
