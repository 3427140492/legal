import request from '@/utils/request'

// 查询律师业绩详细
export function getPerformancesplit(id) {
  return request({
    url: '/lawyer/performancesplit/' + id,
    method: 'get'
  })
}

// 新增律师业绩
export function addPerformancesplit(data) {
  return request({
    url: '/lawyer/performancesplit',
    method: 'post',
    data: data
  })
}

// 删除律师业绩
export function delPerformancesplit(id) {
  return request({
    url: '/lawyer/performancesplit/' + id,
    method: 'delete'
  })
}
