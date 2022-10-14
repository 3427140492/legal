// 案件回收站
<template>
   <div class="app-container">
      <el-form :model="formInline" :inline="true" style="margin-left: 0.2%;">
         <el-form-item label="">
               <el-input v-model="formInline.content"  style="width: 210px;" clearable />&nbsp;&nbsp;
               <el-select v-model="formInline.param" placeholder="请选择类型" style="width: 180px;" clearable >
                  <el-option label="委托人/嫌疑人搜索" value="caseWtr" />
                  <el-option label="对方当事人搜索" value="caseOppositeParties" />
                  <el-option label="案号搜索" value="caseNo" />
                  <el-option label="案由搜索" value="caseCause" />
                  <el-option label="承办律师搜索" value="caseAttorney" />
                  <el-option label="受理机关搜索" value="caseAccept" />
               </el-select>&nbsp;&nbsp;
               <el-select v-model="formInline.typeId" placeholder="案件类型" style="width: 180px;" clearable >
                  <el-option v-for="t in caseTypeList" :key="t.id" :label="t.caseTypeName" :value="t.id"/>
               </el-select>        
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

      <el-table :data="tableList" border style="width: 100%" >
         <el-table-column label="案号" align="center" prop="caseNo" resizable/>
         <el-table-column label="案由" prop="caseCause" align="center" resizable show-overflow-tooltip/>
         <el-table-column label="收案日期" prop="collectionTime" sortable align="center"/>
         <el-table-column label="委托人/当事人" align="center" prop="caseWtr" />
         <el-table-column label="对方当事人" align="center" prop="caseOppositeParties" />
         <el-table-column label="承办律师" align="center" prop="caseAttorney" width="220" resizable show-overflow-tooltip/>
         <el-table-column label="代理费(元)" align="center" prop="caseAgencyfee" />
         <el-table-column label="操作" align="center" fixed="right">
            <template #default="scope">
               <el-button @click="dialog6Fun(scope.row.id)">查看</el-button>
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
import { selectCaseRecycleList } from "@/api/cases/CaseLaw.js";
import { listCaseType } from "@/api/cases/CaseCaseType.js";
import { ArrowLeft } from '@element-plus/icons-vue';
import type { TabsPaneContext } from 'element-plus';
import { ElMessage } from 'element-plus';
import { RefreshRight } from '@element-plus/icons-vue';
import dialog6 from '../dialog/dialog6.vue';

const labelPosition = ref('top'); // form表单样式
const { proxy } = getCurrentInstance();
const tableList = ref([]); // 个人案件列表
const caseTypeList = ref([]); // 客户类型列表
const formInline = reactive({}); // 条件查询form
const queryParams = reactive({
   pageSize: 10,
   pageNum: 1,
   total: 0,
   id: 0,
}); // 条件查询参数
const propsEmitDialog6 = ref();
// 统一暴露属性,方法
defineExpose({
    getList
})

/* 打开dialog6 */
function dialog6Fun(id){
   propsEmitDialog6.value.propsEmitRef(id);
}

/* 条件查询 */
function queryParamsSubmit(){
   getQueryParams();
   selectCaseRecycleList(proxy.addDateRange(queryParams)).then(response => {
      queryParams.total = response.total;
      tableList.value =  response.rows;
   });
}

/* 获取条件查询方法 */
function getQueryParams(){
   if(formInline.param == 'caseWtr'){
      queryParams.caseWtr = formInline.content;
   }
   if(formInline.param == 'caseOppositeParties'){
      queryParams.caseOppositeParties = formInline.content;
   }
   if(formInline.param == 'caseNo'){
      queryParams.caseNo = formInline.content;
   }
   if(formInline.param == 'caseCause'){
      queryParams.caseCause = formInline.content;
   }
   if(formInline.param == 'caseAttorney'){
      queryParams.caseAttorney = formInline.content;
   }
   if(formInline.param == 'caseAccept'){
      queryParams.caseAccept = formInline.content;
   }
   queryParams.caseCaseTypeId = formInline.typeId;
}

/* 查询列表 */
function getList(){
   selectCaseRecycleList(proxy.addDateRange(queryParams)).then(response => {
      queryParams.total = response.total;
      tableList.value =  response.rows;
   })
   listCaseType().then(response => {
      caseTypeList.value = response.rows;
   })
}

/* 重置查询表单 */
function resetForm(){
   queryParams.caseWtr = '';
   queryParams.caseOppositeParties = '';
   queryParams.caseNo = '';
   queryParams.caseCause = '';
   queryParams.caseAttorney = '';
   queryParams.caseAccept = '';
   queryParams.caseCaseTypeId = '';

   formInline.content = '';
   formInline.param = '';
   formInline.caseApproveStatus = '';
   formInline.typeId = '';
   formInline.caseSettleStatus = '';
   formInline.sortParam = '';
   getList();
}
</script>



