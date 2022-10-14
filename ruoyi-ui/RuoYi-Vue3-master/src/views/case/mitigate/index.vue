// 费用减免申请
<template>
   <div class="app-container"><br>
      <el-form :model="formInline" :inline="true" style="margin-left: 0.2%;">
         <el-form-item label="">
               <el-input v-model="formInline.content"  style="width: 210px;" clearable />&nbsp;&nbsp;
               <el-select v-model="formInline.param" placeholder="请选择类型" style="width: 180px;" clearable >
                  <el-option label="案号搜索" value="caseNo" />
                  <el-option label="申请人搜索" value="person" />
               </el-select>&nbsp;&nbsp;
               <el-select v-model="queryParams.status" placeholder="选择收案状态" style="width: 180px;" clearable >
                  <el-option label="审批中" value="1" />
                  <el-option label="审批通过" value="2" />
                  <el-option label="审批未通过" value="3" />
               </el-select>&nbsp;&nbsp;
         </el-form-item>
         <el-form-item>
               <el-button type="primary" icon="Search" @click="queryParamsSubmit">检索</el-button>
               <el-button icon="Refresh" @click="resetForm">重置</el-button>
         </el-form-item>
         <el-form-item style="float: right;">
               <el-tooltip
                  class="box-item"
                  effect="dark"
                  content="刷新"
                  placement="top"
                  >
                  <el-button :icon="RefreshRight" @click="getList" circle />
               </el-tooltip>
         </el-form-item>
      </el-form>

      <el-table :data="tableList" border style="width: 100%; font-weight: 600;" >
        <el-table-column label="案号" align="center" prop="caseLaw.caseNo"/>
        <el-table-column label="申请人" align="center" prop="caseLaw.caseWtr" />
        <el-table-column label="减免金额" align="center" prop="decreaseMoney" />
        <el-table-column label="申请日期" align="center" prop="applyDate" />
        <el-table-column label="审批状态" align="center" prop="status">
            <template #default="scope">
               <span>{{scope.row.status == '1' ? '审批中' : scope.row.status == '2' ? '审批通过' : '审批未通过'}}</span>
            </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
            <template #default="scope">
               <el-button @click="dialog6Fun(scope.row.caseLawId)">详情</el-button>
            </template>
        </el-table-column>
      </el-table>
      <div style="float: right; margin-top: -10px;">
         <pagination
            v-show="queryParams.total > 0"
            :total="queryParams.total"
            v-model:page="queryParams.pageNum"
            v-model:limit="queryParams.pageSize"
            @pagination="getList"
         />
      </div>

      <dialog6 ref="propsEmitDialog6"/>
   </div>
</template>

<script lang="ts" setup>
import useUserStore from '@/store/modules/user';
import { getEmp } from "@/api/cases/HrEmp.js";
import { listDecrease, listDecrease1 } from "@/api/cases/decrease.js";
import { ArrowLeft } from '@element-plus/icons-vue';
import type { TabsPaneContext } from 'element-plus';
import { ElMessage } from 'element-plus';
import { RefreshRight } from '@element-plus/icons-vue';

import dialog6 from '../dialog/dialog6.vue';

const userStore = useUserStore();
const { proxy } = getCurrentInstance();
const tableList = ref([]); // 个人案件列表
const formInline = reactive({}); // 条件查询form
const queryParams = reactive({
   pageSize: 10,
   pageNum: 1,
   total: 0
}); // 条件查询参数
const propsEmitDialog6 = ref();

/* 打开dialog6 */
function dialog6Fun(id){
   propsEmitDialog6.value.propsEmitRef(id);
}

/* 条件查询 */
function queryParamsSubmit(){
   getQueryParams();
   listDecrease(queryParams).then(resp => {
      queryParams.total = resp.total;
      tableList.value =  resp.rows;
   })
}

/* 查询列表 */
function getList(){
   userStore.getInfo().then((response) => {
      getEmp(response.user.hrEmpId).then(res => {
        if(res.code == 200){
            queryParams.caseSubmitter = res.data.empName;
            listDecrease(queryParams).then(resp => {
               queryParams.total = resp.total;
               tableList.value =  resp.rows;
            })
        }
      })
   })
}
getList();

/* 获取条件查询方法 */
function getQueryParams(){
   if(formInline.param == 'caseNo'){
      queryParams.caseNo = formInline.content;
   }
   if(formInline.param == 'person'){
      queryParams.person = formInline.content;
   }
}

/* 重置查询表单 */
function resetForm(){
   queryParams.caseNo = '';
   queryParams.person = '';
   queryParams.status = '';
   formInline.content = '';
   formInline.param = '';
   getList();
}

// listDecrease1(res.data.empName).then(resp => {
//       queryParams.total = resp.total;
//       tableList.value =  resp.rows;
// })
</script>



