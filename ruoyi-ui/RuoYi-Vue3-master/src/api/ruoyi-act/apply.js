import request from '@/utils/request'

// 查询业务申请列表
export function listApply(query) {
  return request({
    url: '/ruoyi-act/apply/list',
    method: 'get',
    params: query
  })
}

//查询业务申请回显表
// export function selapphxb(query) {
//   return request({
//     url: '/ruoyi-act/apply/hxb',
//     method: 'get',  
//     params: query
//   })
// }

// 查询业务申请详细
export function getApply(id) {
  return request({
    url: '/ruoyi-act/apply/' + id,
    method: 'get'
  })
}

// 新增业务申请
export function addApply(data) {
  return request({
    url: '/ruoyi-act/apply',
    method: 'post',
    data: data
  })
}

// 修改业务申请
export function updateApply(data) {
  return request({
    url: '/ruoyi-act/apply',
    method: 'put',
    data: data
  })
}

// 删除业务申请
export function delApply(id) {
  return request({
    url: '/ruoyi-act/apply/' + id,
    method: 'delete'
  })
}
