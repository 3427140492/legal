import request from '@/utils/request'

// 新增发票
export function addInvoice(data) {
  return request({
    url: '/lawyer/invoice/addInvoice',
    method: 'post',
    data: data
  })
}

