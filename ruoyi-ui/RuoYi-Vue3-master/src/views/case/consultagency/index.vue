// 咨询与代理
<template>
   <div class="app-container"><br>
      <el-form :model="formInline" :inline="true" style="margin-left: 0.2%;">
         <el-form-item label="">
            <el-date-picker
                v-model="formInline.collectionTime"
                type="date"
                placeholder="服务时间"
                :size="size"
                style="width: 200px;"
                value-format="YYYY-MM-DD"
            />
         </el-form-item>
         <el-form-item style="margin-left: -20px;">
               <el-button type="primary" icon="Search" @click="queryParamsSubmit">检索</el-button>
               <el-button icon="Refresh" @click="resetForm">重置</el-button>
         </el-form-item>
         <el-form-item style="margin-left: 30px;">
               <el-button icon="Plus" @click="openDialog(undefined,'add')">添加</el-button>
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
        <el-table-column label="编号" align="center" prop="caseNo"/>
        <el-table-column label="委托人" align="center" prop="caseWtr" />
        <el-table-column label="服务时间" align="center" prop="collectionTime" />
        <el-table-column label="服务类型" align="center" prop="caseCaseType.caseTypeName" />
        <el-table-column label="服务内容" align="center" prop="caseCause" />
        <el-table-column label="服务人次" align="center" prop="standard" />
        <el-table-column label="服务费用" align="center" prop="caseAgencyfee" />
        <el-table-column label="登记时间" align="center" prop="caseSubtime" />
        <el-table-column label="操作" align="center" fixed="right">
               <template #default="scope">
                  <el-dropdown trigger="click">
                     <el-button>操作<el-icon class="el-icon--right"><arrow-down /></el-icon></el-button>
                  <template #dropdown>
                     <el-dropdown-menu>
                           <el-dropdown-item @click="openDetails(scope.row.id)">查看</el-dropdown-item>
                           <el-dropdown-item @click="openDialog(scope.row.id,'edit')">修改</el-dropdown-item>
                           <el-dropdown-item @click="openAutoDialog(scope.row)">批量生产文书</el-dropdown-item>
                           <el-dropdown-item @click="dialog4Fun(scope.row)">申请发票</el-dropdown-item>
                           <el-dropdown-item @click="editCaseRecord(scope.row)">{{scope.row.caseRecord == '1' ? '案件归档' : '取消案件归档'}}</el-dropdown-item>
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

      <el-dialog v-model="dialogVisible" title="案件详情" width="55%">
          <div>
            <el-descriptions :icon="Edit" title="基本资料" :size="large" style="margin-bottom: -10px;">
               <!-- <el-descriptions-item label="Username">kooriookami</el-descriptions-item> -->
            </el-descriptions>
            <el-divider />
            <el-descriptions title="" :column="3" border >
               <el-descriptions-item label="案号：">{{ data.caseNo }}</el-descriptions-item>
               <el-descriptions-item label="案件类型：">{{ data.caseCaseType }}</el-descriptions-item>
               <el-descriptions-item label="服务时间">{{ data.collectionTime }}</el-descriptions-item>
               <el-descriptions-item label="服务内容：">{{ data.caseCause }}</el-descriptions-item>
               <el-descriptions-item label="服务人次：">{{ data.standard  }}</el-descriptions-item>
               <el-descriptions-item label="服务费用：">{{ data.caseAgencyfee }}</el-descriptions-item>
               <el-descriptions-item label="委托人：" >{{ data.caseWtr }}</el-descriptions-item>     
               <el-descriptions-item label="登记时间：" >{{ data.caseSubtime }}</el-descriptions-item>
               <el-descriptions-item label="服务律师：" >{{ data.caseAttorney }}</el-descriptions-item>
               <el-descriptions-item label="收案审批状态：" >{{ data.caseApproveStatus == '1' ? '审批中' : data.caseApproveStatus == '2' ? '审批通过' : '审批不通过' }}</el-descriptions-item>
               <el-descriptions-item label="备注：" >{{ data.caseRemarks }}</el-descriptions-item>
            </el-descriptions><br><br><br>
            <el-descriptions :icon="Edit" title="承办律师" :size="large" style="margin-bottom: -10px;">
               <!-- <el-descriptions-item label="Username">kooriookami</el-descriptions-item> -->
            </el-descriptions>
            <el-divider />
            <el-table :data="CwPerList" border style="width: 100%" :table-layout="auto">
               <el-table-column prop="caseAttorney" label="类型" />
               <el-table-column prop="empName" label="姓名" />
               <el-table-column prop="cwPerformancesplit" label="业绩分成" />
               <el-table-column prop="cwSplitType" label="分成方式" />   
            </el-table><br>
         </div>
         <template #footer>
            <span class="dialog-footer">
               <el-button @click="dialogVisible = false">关闭</el-button>
            </span>
         </template>
      </el-dialog>

      <dialogs ref="propsEmitDialog6" @on-change="getList"/>
      <autoUpoloadLog ref="propsEmitDialog"/>
      <dialog4 ref="propsEmitDialog4"/>
   </div>
</template>

<script lang="ts" setup>
import useUserStore from '@/store/modules/user';
import { getEmp } from "@/api/cases/HrEmp.js";
import { listDecrease, listDecrease1 } from "@/api/cases/decrease.js";
import { consultagency, getCaseLawInfo, editCaseLaw } from "@/api/cases/CaseLaw.js";
import { getPerformancesplit } from "@/api/cases/performancesplit.js";
import type { TabsPaneContext } from 'element-plus';
import { ElMessage } from 'element-plus';
import { RefreshRight, Plus, ArrowLeft } from '@element-plus/icons-vue';

import dialogs from './dialogs.vue';
import dialog4 from '../dialog/dialog4.vue';
import autoUpoloadLog from '../dialog/autoUpoloadLog.vue';

const userStore = useUserStore();
const tableList = ref([]); // 个人案件列表
const formInline = reactive({});
const queryParams = reactive({
   pageSize: 10,
   pageNum: 1,
   total: 0
}); // 条件查询参数
const data = reactive({}); // 页面数据
const CwPerList = ref([]); // 承办律时列表
const propsEmitDialog4 = ref();
const propsEmitDialog6 = ref();
const propsEmitDialog = ref();
const dialogVisible = ref(false);

/* 编辑档案归档 */
function editCaseRecord(param){
   queryParams.id = param.id;
   // queryParams.caseShare = null;
   // queryParams.caseContract = null;
   if(param.caseRecord == "1"){
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


/* 打开dialog4 */
function dialog4Fun(row){
   propsEmitDialog4.value.propsEmitRef(queryParams.hrEmpId);
   propsEmitDialog4.value.propsEmitRef2(row);
}

/* 详情 */
function openDetails(id){
   dialogVisible.value = true;
   getCaseLawInfo(id).then(response => {
      data.caseNo = response.data.caseNo;
      data.caseCaseType = response.data.caseCaseType.caseTypeName;
      data.collectionTime = response.data.collectionTime;
      data.caseCause = response.data.caseCause;
      data.standard = response.data.standard;
      data.caseAgencyfee = response.data.caseAgencyfee;
      data.caseWtr = response.data.caseWtr;
      data.caseSubtime = response.data.caseSubtime;
      data.caseAttorney = response.data.caseAttorney;
      data.caseApproveStatus = response.data.caseApproveStatus;
      data.caseRemarks = response.data.caseRemarks;
   });
   getPerformancesplit(id).then(response => {
      CwPerList.value = response.rows;
   });
}

/* 批量生产文书 */
function openAutoDialog(row){
    propsEmitDialog.value.propsEmitRef(row,queryParams.caseSubmitter);
}

/* 修改 */
function openDialog(id,types){
   propsEmitDialog6.value.propsEmitRef(id,types); // 方法传值
}

/* 条件查询 */
function queryParamsSubmit(){
   queryParams.collectionTime = formInline.collectionTime;
   consultagency(queryParams).then(resp => {
      queryParams.total = resp.total;
      tableList.value =  resp.rows;
   })
}

/* 查询列表 */
function getList(){
   userStore.getInfo().then((response) => {
      queryParams.hrEmpId = response.user.hrEmpId;
      getEmp(response.user.hrEmpId).then(res => {
        if(res.code == 200){
            queryParams.caseSubmitter = res.data.empName;
            consultagency(queryParams).then(resp => {
               queryParams.total = resp.total;
               tableList.value =  resp.rows;
            })
        }
      })
   })
}
getList();

/* 重置查询表单 */
function resetForm(){
   queryParams.collectionTime = '';
   formInline.collectionTime = '';
   getList();
}
</script>



