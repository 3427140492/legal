import request from '@/utils/request'

// 查询收支类别列表
export function cwInoutlistType(query) {
  return request({
    url: '/lawyer/cwinouttype/list',
    method: 'get',
    params: query
  })
}
