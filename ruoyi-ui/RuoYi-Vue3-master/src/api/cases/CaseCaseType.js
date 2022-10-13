import request from '@/utils/request'


// 查询
export function getCaseTypeMin(id) {
  return request({
    url: '/case/casetype/getCaseTypeMin/' + id,
    method: 'get'
  })
}

// 查询案件类型列表
export function listCaseType(query) {
    return request({
      url: '/case/casetype/listCaseType',
      method: 'get',
      params: query
    })
}

// 查询案件类型子列表
export function getType(id) {
  return request({
    url: '/case/casetype/getType/' + id,
    method: 'get'
  })
}

