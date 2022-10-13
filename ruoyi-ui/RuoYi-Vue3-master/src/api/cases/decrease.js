import request from '@/utils/request'

// 查询减免申请列表
// export function listDecrease1(caseSubmitter) {
//   return request({
//     url: '/case/decrease/list1/' + caseSubmitter,
//     method: 'get',
//   })
// }

// 查询减免申请列表
export function listDecrease(query) {
  console.log(query);
  return request({
    url: '/case/decrease/list',
    method: 'get',
    params: query
  })
}

// 新增减免申请
export function addDecrease(data) {
  return request({
    url: '/case/decrease/addDecrease',
    method: 'post',
    data: data
  })

}
