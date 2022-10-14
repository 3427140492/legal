import request from '@/utils/request'

// 查询通讯录表 (条件查询)
export function getEmp(id) {
    return request({
      url: '/case/hremp/getEmp/' + id,
      method: 'get'
    })
  }