// 全所案件
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
                  <el-option label="归档号搜索" value="caseRecordNum" />
               </el-select>&nbsp;&nbsp;
               <el-select v-model="formInline.caseApproveStatus" placeholder="选择收案状态" style="width: 180px;" clearable >
                  <el-option label="审批中" value="1" />
                  <el-option label="审批通过" value="2" />
                  <el-option label="审批未通过" value="3" />
               </el-select>&nbsp;&nbsp;
               <el-select v-model="formInline.typeId" placeholder="案件类型" style="width: 180px;" clearable >
                  <el-option v-for="t in caseTypeList" :key="t.id" :label="t.caseTypeName" :value="t.id"/>
               </el-select>&nbsp;&nbsp;
               <el-select v-model="formInline.caseSettleStatus" placeholder="选中案件状态" style="width: 180px;" clearable >
                  <el-option label="未结案" value="N" />
                  <el-option label="已结案" value="Y" />
               </el-select>
               &nbsp;&nbsp;
               <el-select v-model="formInline.sortParam" placeholder="自定义排序" style="width: 180px;" clearable >
                  <el-option label="登记时间从高到低" value="1" />
                  <el-option label="登记时间从低到高" value="2" />
                  <el-option label="收案时间从高到低" value="3" />
                  <el-option label="收案时间从低到高" value="4" />
                  <el-option label="案号从高到低" value="5" />
                  <el-option label="案号从低到高" value="6" />
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
         <el-table-column label="案号" align="center" prop="caseNo" width="190" resizable/>
         <el-table-column label="收案日期" prop="collectionTime" sortable/>
         <el-table-column label="委托人" align="center" prop="caseWtr" />
         <el-table-column label="对方当事人" align="center" prop="caseOppositeParties" />
         <el-table-column label="承办律师" align="center" prop="caseAttorney" width="220" resizable show-overflow-tooltip/>
         <el-table-column label="结案" align="center">
               <template #default="scope">
                  <span>{{ scope.row.caseSettleStatus == 'Y' ? '已结案' : '未结案' }}</span>
               </template>
         </el-table-column>
         <el-table-column label="代理费(元)" align="center" prop="caseAgencyfee" />
         <el-table-column label="已付款(元)" align="center" prop="casePaidsal" />
         <el-table-column label="已开票(元)" align="center" prop="" style="color: #79bbff;">
               <template #default="scope">
                  <span style="color: #F65F5F;">{{ scope.row.caseInvoiced }}</span>
               </template>
         </el-table-column>
         <el-table-column label="收案审批" align="center">
               <template #default="scope">
                  <span style="color: #79bbff;">{{ scope.row.caseApproveStatus == '1' ? '审批中' : scope.row.caseApproveStatus == '2' ? '审批通过' : '审批不通过' }}</span>
               </template>
         </el-table-column>
         <el-table-column label="收案审批人" align="center">
               <template #default="scope">
                  <span>{{ scope.row.systemApprovalId == null ? '-' : scope.row.systemApprovalId }}</span>
               </template>
         </el-table-column>
         <el-table-column label="合同" align="center">
            <template #default="scope">
               <span style="color: #EC4343;" v-if="scope.row.caseContract == '1'">✕</span>
               <span style="color: #79bbff;" v-if="scope.row.caseContract == '2'">✓</span>
            </template>
         </el-table-column>
         <el-table-column label="档案" align="center">
             <template #default="scope">
               <span style="color: #EC4343;" v-if="scope.row.caseRecord == '1'">✕</span>
               <span style="color: #79bbff;" v-if="scope.row.caseRecord == '2'">✓</span>
            </template>
         </el-table-column>
         <el-table-column label="操作" align="center" fixed="right">
               <template #default="scope">
                  <el-dropdown trigger="click">
                     <el-button @click="openClick(scope.row)">操作<el-icon class="el-icon--right"><arrow-down /></el-icon></el-button>
                  <template #dropdown>
                     <el-dropdown-menu v-show="down.show">
                           <el-dropdown-item @click="dialog1Fun(scope.row.caseSettleStatus,scope.row.id)">费用减免申</el-dropdown-item>
                           <el-dropdown-item @click="dialog2Fun(scope.row.id)">添加收款记录</el-dropdown-item>
                           <el-dropdown-item @click="editSaseShare(scope.row.caseShare)">{{ scope.row.caseShare == '1' ? '共享' : '取消共享' }}</el-dropdown-item>
                           <el-dropdown-item @click="dialog6Fun(scope.row.id)">详情</el-dropdown-item>
                     </el-dropdown-menu>

                     <el-dropdown-menu v-show="down.show2">
                           <el-dropdown-item @click="dialog1Fun(scope.row.caseSettleStatus,scope.row.id)">费用减免申请</el-dropdown-item>
                           <el-dropdown-item @click="dialog3Fun(scope.row.caseSettleStatus,scope.row.id)">{{scope.row.caseSettleStatus == 'Y' ? '已结案' : '结案'}}</el-dropdown-item>
                           <el-dropdown-item @click="dialog2Fun(scope.row.id)">添加收款记录</el-dropdown-item>
                           <el-dropdown-item @click="dialog4Fun">申请发票</el-dropdown-item>
                           <el-dropdown-item @click="editCaseContract(scope.row.caseContract)">{{scope.row.caseContract == '1' ? '合同归档' : '取消合同归档'}}</el-dropdown-item>
                           <el-dropdown-item @click="editCaseRecord(scope.row.caseRecord)">{{scope.row.caseRecord == '1' ? '档案归档' : '取消档案归档'}}</el-dropdown-item>
                           <el-dropdown-item @click="editSaseShare(scope.row.caseShare)">{{ scope.row.caseShare == '1' ? '共享' : '取消共享' }}</el-dropdown-item>
                           <el-dropdown-item @click="dialog6Fun(scope.row.id)">详情</el-dropdown-item>
                     </el-dropdown-menu>

                     <el-dropdown-menu v-show="down.show3">
                           <el-dropdown-item @click="dialog5Fun(scope.row.id)">重新立案</el-dropdown-item>
                           <el-dropdown-item @click="dialog6Fun(scope.row.id)">详情</el-dropdown-item>
                     </el-dropdown-menu>

                  </template>
                  </el-dropdown>
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

      <dialog1 ref="propsEmitDialog1"/>
      <dialog2 ref="propsEmitDialog2"/>
      <dialog3 ref="propsEmitDialog3" @on-change="getList"/>
      <dialog4 ref="propsEmitDialog4"/>
      <dialog5 ref="propsEmitDialog5" @on-change="getList"/>
      <dialog6 ref="propsEmitDialog6"/>
   </div>
</template>

<script lang="ts" setup>
import { myCaseSummary, getCaseLawInfo, editCaseLaw } from "@/api/cases/CaseLaw.js";
import { listCaseType } from "@/api/cases/CaseCaseType.js";
import useUserStore from '@/store/modules/user';
import { ArrowLeft } from '@element-plus/icons-vue';
import type { TabsPaneContext } from 'element-plus';
import { ElMessage } from 'element-plus';
import { RefreshRight } from '@element-plus/icons-vue';

import dialog1 from '../dialog/dialog1.vue';
import dialog2 from '../dialog/dialog2.vue';
import dialog3 from '../dialog/dialog3.vue';
import dialog4 from '../dialog/dialog4.vue';
import dialog5 from '../dialog/dialog5.vue';
import dialog6 from '../dialog/dialog6.vue';

const labelPosition = ref('top'); // form表单样式
const userStore = useUserStore();
const { proxy } = getCurrentInstance();
const tableList = ref([]); // 个人案件列表
const caseTypeList = ref([]); // 客户类型列表
const formInline = reactive({}); // 条件查询form
const queryParams = reactive({
   pageSize: 10,
   pageNum: 1,
   total: 0,
   // id: 0,
   // caseShare: '',
}); // 条件查询参数
const down = reactive({
   show: false,
   show2: false,
   show3: false,
}); // 下拉框参数
const id = ref();   // 案件id
const propsEmitDialog1 = ref();
const propsEmitDialog2 = ref();
const propsEmitDialog3 = ref();
const propsEmitDialog4 = ref();
const propsEmitDialog5 = ref();
const propsEmitDialog6 = ref();

/* 打开dialog1 */
function dialog1Fun(status,id){
   if(status == 'Y'){
      ElMessage.info("已结案不能编辑");
      return;
   }
   propsEmitDialog1.value.propsEmitRef(id);
}

/* 打开dialog2 */
function dialog2Fun(id){
   propsEmitDialog2.value.propsEmitRef(id);
}

/* 打开dialog3 */
function dialog3Fun(status,id){
    if(status == 'Y'){
      ElMessage.info("已结案不能编辑");
      return;
   }
   propsEmitDialog3.value.propsEmitRef(id);
}

/* 打开dialog4 */
function dialog4Fun(){
   propsEmitDialog4.value.propsEmitRef(queryParams.hrEmpId);
}

/* 打开dialog5 */
function dialog5Fun(id){
   propsEmitDialog5.value.propsEmitRef(id);
}

/* 打开dialog6 */
function dialog6Fun(id){
   propsEmitDialog6.value.propsEmitRef(id);
}


/* 编辑共享状态 */
function editSaseShare(param){
   queryParams.id = id.value;
   queryParams.caseContract = null;
   queryParams.caseRecord = null;
   if(param == "1"){
      queryParams.caseShare = "2";
   }else{
      queryParams.caseShare = "1";
   }
   editCaseLaw(queryParams).then(response => {
      if(response.code == 200){
         ElMessage.success("已提交");
      }else{
         ElMessage.error("提交失败 !");
      }
      getList();
   })
}

/* 编辑合同归档 */
function editCaseContract(param){
   queryParams.id = id.value;
   queryParams.caseShare = null;
   queryParams.caseRecord = null;
   if(param == "1"){
      queryParams.caseContract = "2";
   }else{
      queryParams.caseContract = "1";
   }
   editCaseLaw(queryParams).then(response => {
      if(response.code == 200){
         ElMessage.success("已提交");
      }else{
         ElMessage.error("提交失败 !");
      }
      getList();
   })
}

/* 编辑档案归档 */
function editCaseRecord(param){
   queryParams.id = id.value;
   queryParams.caseShare = null;
   queryParams.caseContract = null;
   if(param == "1"){
      queryParams.caseRecord = "2";
   }else{
      queryParams.caseRecord = "1";
   }
   editCaseLaw(queryParams).then(response => {
      if(response.code == 200){
         ElMessage.success("已提交");
      }else{
         ElMessage.error("提交失败 !");
      }
      getList();
   })
}

/* 条件查询 */
function queryParamsSubmit(){
   getQueryParams();
   myCaseSummary(proxy.addDateRange(queryParams)).then(response => {
      queryParams.total = response.total;
      tableList.value =  response.rows;
   });
}

/* 获取条件查询方法 */
function getQueryParams(){
   if(formInline.sortParam == '1'){
      queryParams.caseSubtime2 = 'desc';
      queryParams.collectionTime2 = '';
      queryParams.casePropernum = '';
   }
   if(formInline.sortParam == '2'){
      queryParams.caseSubtime2 = 'asc';
      queryParams.collectionTime2 = '';
      queryParams.casePropernum = '';
   }
   if(formInline.sortParam == '3'){
      queryParams.collectionTime2 = 'desc';
      queryParams.caseSubtime2 = '';
      queryParams.casePropernum = '';
   }
   if(formInline.sortParam == '4'){
      queryParams.collectionTime2 = 'asc';
      queryParams.caseSubtime2 = '';
      queryParams.casePropernum = '';
   }
   if(formInline.sortParam == '5'){
      queryParams.casePropernum = 'desc';
      queryParams.caseSubtime2 = '';
      queryParams.collectionTime2 = '';
   }
   if(formInline.sortParam == '6'){
      queryParams.casePropernum = 'asc';
      queryParams.caseSubtime2 = '';
      queryParams.collectionTime2 = '';
   }

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
   if(formInline.param == 'caseRecordNum'){
      queryParams.caseRecordNum = formInline.content;
   }
   queryParams.caseApproveStatus = formInline.caseApproveStatus;
   queryParams.caseCaseTypeId = formInline.typeId;
   queryParams.caseSettleStatus = formInline.caseSettleStatus;
}

/* 查询列表 */
function getList(){
   myCaseSummary(proxy.addDateRange(queryParams)).then(response => {
      queryParams.total = response.total;
      tableList.value =  response.rows;
   })
   listCaseType().then(response => {
      caseTypeList.value = response.rows;
   })
}
getList();

// 统一暴露属性,方法
defineExpose({
    getList
})

/* 获取日期 */
function getDate(){
  var now = new Date();
  var year = now.getFullYear(); //得到年份
  var month = now.getMonth(); //得到月份
  var date = now.getDate(); //得到日期
  month = month + 1;
  month = month.toString().padStart(2, "0");
  date = date.toString().padStart(2, "0");
  var defaultDate = `${year}-${month}-${date}`;
  return defaultDate;
}

/* 重置查询表单 */
function resetForm(){
   queryParams.collectionTime2 = '';
   queryParams.caseSubtime2 = '';
   queryParams.casePropernum = 'asc';
   queryParams.caseWtr = '';
   queryParams.caseOppositeParties = '';
   queryParams.caseNo = '';
   queryParams.caseCause = '';
   queryParams.caseAttorney = '';
   queryParams.caseAccept = '';
   queryParams.caseRecordNum = '';
   queryParams.caseApproveStatus = '';
   queryParams.caseCaseTypeId = '';
   queryParams.caseSettleStatus = '';
   
   formInline.content = '';
   formInline.param = '';
   formInline.caseApproveStatus = '';
   formInline.typeId = '';
   formInline.caseSettleStatus = '';
   formInline.sortParam = '';
   getList();
}

/* 下拉框切换 */
function openClick(row){
   id.value = row.id;
   if(row.caseApproveStatus == '1'){
      down.show = true;
      down.show2 = false;
      down.show3 = false;
   }
   if(row.caseApproveStatus == '2'){
      down.show2 = true;
      down.show = false;
      down.show3 = false;
   }
   if(row.caseApproveStatus == '3'){
      down.show3 = true;
      down.show = false;
      down.show2 = false;
   }
}

</script>



