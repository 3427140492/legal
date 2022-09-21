import request from '@/utils/request'

// 查询我的当事人列表
export function listWddsr(query) {
  return request({
    url: '/client/wddsr/list',
    method: 'get',
    params: query
  })
}

// 查询我的当事人详细
export function getWddsr(id) {
  return request({
    url: '/client/wddsr/' + id,
    method: 'get'
  })
}

// 新增我的当事人
export function addWddsr(data) {
  return request({
    url: '/client/wddsr',
    method: 'post',
    data: data
  })
}

// 修改我的当事人
export function updateWddsr(data) {
  return request({
    url: '/client/wddsr',
    method: 'put',
    data: data
  })
}

// 删除我的当事人
export function delWddsr(id) {
  return request({
    url: '/client/wddsr/' + id,
    method: 'delete'
  })
}
