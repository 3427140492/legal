import request from '@/utils/request'


// 新增收支
export function addInout(data) {
  return request({
    url: '/lawyer/cwinout/addInout',
    method: 'post',
    data: data
  })
}

