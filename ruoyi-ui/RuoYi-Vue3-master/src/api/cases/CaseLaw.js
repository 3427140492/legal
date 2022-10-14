import request from '@/utils/request'

/**
 * 我的案件
 */
// 查询我的个人案件
export function selectMyCaseLawList(query) {
  return request({
    url: '/case/caselaw/selectMyCaseLawList',
    method: 'get',
    params: query
  })
}

// 查询我的合办案件
export function selectMyCaseLawLists(query) {
  return request({
    url: '/case/caselaw/selectMyCaseLawLists',
    method: 'get',
    params: query
  })
}

// 查询共享案件
export function shareTheCaseList(query) {
  return request({
    url: '/case/caselaw/shareTheCaseList',
    method: 'get',
    params: query
  })
}

// 查询我的待收款案件
export function myStayProceedsCase(query) {
  return request({
    url: '/case/caselaw/myStayProceedsCase',
    method: 'get',
    params: query
  })
}

// 结清 款项发票
export function setPayment(data) {
  return request({
    url: '/case/caselaw/setPayment',
    method: 'put',
    params: data
  })
}

// 查询 回收案件 列表
export function selectCaseRecycleList(query) {
  return request({
    url: '/case/caselaw/selectCaseRecycleList',
    method: 'get',
    params: query
  })
}

// 查询 咨询与代理 列表
export function consultagency(query) {
  return request({
    url: '/case/caselaw/consultagency',
    method: 'get',
    params: query
  })
}






// 查询我的待收款案件 年份
export function myStayYear() {
  return request({
    url: '/case/caselaw/myStayYear',
    method: 'get',
  })
}

// 查询我的案件汇总
export function myCaseSummary(query) {
  return request({
    url: '/case/caselaw/myCaseSummary',
    method: 'get',
    params: query
  })
}



// 获取案件信息详细信息 (添加收款记录)
export function getCaseLawInfo(id) {
  return request({
    url: '/case/caselaw/getCaseLawInfo/' + id,
    method: 'get'
  })
}

// 修改案件信息
export function editCaseLaw(data) {
  return request({
    url: '/case/caselaw/editCaseLaw',
    method: 'post',
    data: data
  })
}

// 修改结案信息 (修改客户状态)
export function LawEnd(data) {
  return request({
    url: '/case/caselaw/LawEnd',
    method: 'put',
    data: data
  })
}


// 查询案件列表 (代开发票)
export function selectInvoiceCaseLawList(query) {
  return request({
    url: '/case/caselaw/selectInvoiceCaseLawList',
    method: 'get',
    params: query
  })
}

// 查询案件信息详细
export function getLaw(id) {
  return request({
    url: '/case/caselaw/getInfo/' + id,
    method: 'get'
  })
}




// 查询案件信息 (减免申请数据)
export function getCLAndDecById(id) {
  return request({
    url: '/case/caselaw/getCLAndDecById/' + id,
    method: 'get'
  })
}

// 案件法律信息列表 (条件查询)
export function selectCaseLawParamsList(query) {
    return request({
      url: '/case/caselaw/selectCaseLawParamsList',
      method: 'get',
      params: query
    })
}

// 查询案件信息id
export function getCaseLawId() {
    return request({
      url: '/case/caselaw/getCaseLawId',
      method: 'get'
    })
}

// 新增案件法律信息
export function addCaseLaw(data) {
    return request({
      url: '/case/caselaw/addCaseLaw',
      method: 'post',
      data: data
    })
}
