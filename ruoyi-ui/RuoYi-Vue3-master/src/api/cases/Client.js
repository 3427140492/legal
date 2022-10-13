import request from '@/utils/request'



// 查询客户信息 (是否存在)
export function getClientByCardnum(query) {
    return request({
      url: '/case/client/getClientByCardnum',
      method: 'get',
      params: query
    })
}

// 新增客户信息
export function addClient(data) {
    return request({
      url: '/case/client/addClient',
      method: 'post',
      data: data
    })
}

// 修改客户信息
export function editClient(data) {
    return request({
      url: '/case/client/editClient',
      method: 'put',
      data: data
    })
}