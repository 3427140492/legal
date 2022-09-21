import request from '@/utils/request'

// 查询归档列表
export function selectCaseLawList() {
  return request({
    url: '/record/law/selectCaseLawList',
    method: 'get',
  })
}
