import request from '@/utils/request'

// 新增快递业务
export function addExpressage(data) {
  return request({
    url: '/lawyer/expressage',
    method: 'post',
    data: data
  })
}
