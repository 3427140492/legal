import request from '@/utils/request'

// 查询收结案统计列表
export function listClosed(query) {
  return request({
    url: '/ruoyi-statistics/Closed/list',
    method: 'get',
    params: query
  })
}

// 查询收结案统计详细
export function getClosed(id) {
  return request({
    url: '/ruoyi-statistics/Closed/' + id,
    method: 'get'
  })
}

// 新增收结案统计
export function addClosed(data) {
  return request({
    url: '/ruoyi-statistics/Closed',
    method: 'post',
    data: data
  })
}

// 修改收结案统计
export function updateClosed(data) {
  return request({
    url: '/ruoyi-statistics/Closed',
    method: 'put',
    data: data
  })
}

// 删除收结案统计
export function delClosed(id) {
  return request({
    url: '/ruoyi-statistics/Closed/' + id,
    method: 'delete'
  })
}


export function listType(query) {
  return request({
    url: '/ruoyi-statistics/Closed/Typelist',
    method: 'get',
    params: query
  })
}
