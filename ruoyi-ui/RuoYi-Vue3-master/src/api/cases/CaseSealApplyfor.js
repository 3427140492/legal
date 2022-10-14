import request from '@/utils/request'

// 查询 批量生成文书 列表
export function getProduction(caseid) {
  return request({
    url: '/lawyer/applyfor/getProduction/' + caseid,
    method: 'get'
  })
}

// 删除公函记录
export function delApplyfor(id) {
  return request({
    url: '/lawyer/applyfor/' + id,
    method: 'delete'
  })
}

// 查询公函记录列表
export function listApplyfor2(query) {
  return request({
    url: '/lawyer/applyfor/list',
    method: 'get',
    params: query
  })
}

// 新增公函记录
export function addApplyfor(data) {
  return request({
    url: '/lawyer/applyfor',
    method: 'post',
    data: data
  })
}

// 查询公函记录列表
export function listApplyfor(id) {
  return request({
    url: '/lawyer/applyfor/list/' + id,
    method: 'get',
  })
}

