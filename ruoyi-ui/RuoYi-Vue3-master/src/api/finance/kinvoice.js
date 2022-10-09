import request from '@/utils/request'

// 查询开具发票列表
export function listKinvoice(query) {
  return request({
    url: '/finance/kinvoice/list',
    method: 'get',
    params: query
  })
}

// 查询案件类型
export function listType(query) {
  return request({
    url: '/finance/kinvoice/anlxlist',
    method: 'get',
    params: query
  })
}

// 查询开具发票详细
export function getKinvoice(id) {
  return request({
    url: '/finance/kinvoice/' + id,
    method: 'get'
  })
}

// 新增开具发票
export function addKinvoice(data) {
  return request({
    url: '/finance/kinvoice',
    method: 'post',
    data: data
  })
}

// 修改开具发票
export function updateKinvoice(data) {
  return request({
    url: '/finance/kinvoice',
    method: 'put',
    data: data
  })
}

// 删除开具发票
export function delKinvoice(id) {
  return request({
    url: '/finance/kinvoice/' + id,
    method: 'delete'
  })
}
