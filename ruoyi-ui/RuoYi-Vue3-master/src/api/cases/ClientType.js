import request from '@/utils/request'

// 查询客户类型列表
export function selectClientType() {
    return request({
      url: '/case/clienttype/listClientType',
      method: 'get'
    })
  }