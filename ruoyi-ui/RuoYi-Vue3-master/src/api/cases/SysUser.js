import request from '@/utils/request'

// 查询系统用户表列表 (所有律师信息)
export function selectSysUserByHrEmp(query) {
  return request({
    url: '/case/sysuser/selectSysUserByHrEmp',
    method: 'get',
    params: query
  })
}