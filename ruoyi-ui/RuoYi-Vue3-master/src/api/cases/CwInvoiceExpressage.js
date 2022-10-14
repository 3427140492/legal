import request from '@/utils/request'

// 新增发票-快递业务中间
export function addCwInvoiceExpressage(data) {
  return request({
    url: '/lawyer/CwInvoiceExpressage',
    method: 'post',
    data: data
  })
}
