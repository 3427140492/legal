// 全所案件
<template>
   <div class="app-container">
      <el-form :model="queryParams" :inline="true" style="margin-left: 0.2%;">
         <el-form-item label="">
               <el-input v-model="queryParams.content"  style="width: 210px;" clearable />&nbsp;&nbsp;
               <el-select v-model="queryParams.param" placeholder="请选择类型" style="width: 180px;" clearable >
                  <el-option label="案号搜索" value="caseNo" />
               </el-select>&nbsp;&nbsp;
               <el-select v-model="queryParams.applyforUsingTheItem" style="width: 180px;" placeholder="选择用印事项" clearable>
                <el-option label="立案文书" value="立案文书" />
                <el-option label="公函" value="公函" />
                <el-option label="介绍信" value="介绍信" />
                <el-option label="律师函" value="律师函" />
                <el-option label="非诉" value="非诉" />
              </el-select>&nbsp;&nbsp;
               <el-select v-model="queryParams.caseApproveStatus" placeholder="选择审批状态" style="width: 180px;" clearable >
                  <el-option label="审批中" value="1" />
                  <el-option label="审批通过" value="2" />
                  <el-option label="审批未通过" value="3" />
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
         <el-table-column label="编号" type="index" align="center" width="120" />
         <el-table-column label="案号" align="center" prop="caseNo" width="190" resizable/>
         <el-table-column label="领用人" align="center" prop="applyforRecipient" />
         <el-table-column label="收函方/委托人" align="center" prop="sendPerson" />
         <el-table-column label="用印事项" align="center" prop="applyforUsingTheItem" />
         <el-table-column label="领用日期" align="center" prop="applyforReceive" />
         <el-table-column label="审批状态" align="center">
            <template #default="scope">
               <span>{{scope.row.applyforApprovalStatus == '1' ? '未结案' : '结案'}}</span>
            </template>
         </el-table-column>
         <el-table-column label="操作" align="center" fixed="right">
               <template #default="scope">
                  <el-dropdown trigger="click">
                     <el-button>操作<el-icon class="el-icon--right"><arrow-down /></el-icon></el-button>
                  <template #dropdown>
                     <el-dropdown-menu>
                           <el-dropdown-item @click="dialog6Fun(scope.row.caseid)">查看</el-dropdown-item>
                           <el-dropdown-item @click="deleteApply  (scope.row.id)">删除</el-dropdown-item>
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
               <el-descriptions-item label="提交时间：">{{ data.caseSubtime }}</el-descriptions-item>
               <el-descriptions-item label="案件类型：">{{ data.caseCaseType }}</el-descriptions-item>
               <el-descriptions-item label="收案时间：">{{ data.collectionTime }}</el-descriptions-item>
               <el-descriptions-item label="委托人：">{{ data.caseWtr }}</el-descriptions-item>
               <el-descriptions-item label="对方当事人：">{{ data.caseOppositeParties  }}</el-descriptions-item>
               <el-descriptions-item label="第三人：">{{ data.caseAgencyfee }}</el-descriptions-item>
               <el-descriptions-item label="承办律师：">{{ data.caseAttorney }}</el-descriptions-item> 
               <el-descriptions-item label="诉讼地位：">{{ data.caseLawsuitStatus }}</el-descriptions-item>   
               <el-descriptions-item label="诉讼阶段：">{{ data.caseStatus }}</el-descriptions-item>   
               <el-descriptions-item label="诉讼标的：">{{ data.caseLawsuitobj }}</el-descriptions-item>   
               <el-descriptions-item label="代理费：">{{ data.caseAgencyfee }}</el-descriptions-item>   
               <el-descriptions-item label="收费方式：">{{ data.caseChargeWay }}</el-descriptions-item>   
               <el-descriptions-item label="是否享受政府补助：">{{ data.caseSubsidy == 'Y' ? '是' : '否' }}</el-descriptions-item>
               <el-descriptions-item label="补助金额：">{{ data.caseSubsidysal }}</el-descriptions-item>    
               <el-descriptions-item label="收案申请状态：">{{ data.caseApproveStatus == '1' ? '审批中' : data.caseApproveStatus == '2' ? '审批通过' : '审批不通过' }}</el-descriptions-item> 
               <el-descriptions-item label="案由：" >{{ data.caseCause }}</el-descriptions-item>     
               <el-descriptions-item label="备注：" >{{ data.caseRemarks }}</el-descriptions-item>     
            </el-descriptions><br><br><br>
            <el-descriptions :icon="Edit" title="公函记录" :size="large" style="margin-bottom: -10px;">
               <!-- <el-descriptions-item label="Username">kooriookami</el-descriptions-item> -->
            </el-descriptions>
            <el-divider />
            <el-table :data="CAppList" border style="width: 100%" :table-layout="auto">
               <el-table-column prop="caseNo" label="案号" width="170" show-overflow-tooltip/>
               <el-table-column prop="applyforRecipient" label="领用人" />
               <el-table-column prop="sendPerson" label="收函方" />
               <el-table-column prop="applyforUsingTheItem" label="用印事项" width="170" show-overflow-tooltip/>   
               <el-table-column prop="applyforReceive" label="领用日期" />   
               <el-table-column prop="caseFiles.filename" label="文书名称"  width="170" show-overflow-tooltip/>   
               <el-table-column label="审批状态">
                  <template #default="scope">
                     <span>{{ scope.row.applyforApprovalStatus == '1' ? '审批中' : scope.row.applyforApprovalStatus == '2' ? '审批通过' : '审批不通过' }}</span>
                  </template>   
               </el-table-column>      
               <el-table-column label="操作">
                  <template #default="scope">
                     <el-button text size="small" >查看</el-button>
                  </template>
               </el-table-column>       
            </el-table>

         </div>
         <template #footer>
            <span class="dialog-footer">
               <el-button @click="dialogVisible = false">关闭</el-button>
            </span>
         </template>
      </el-dialog>

   </div>
</template>

<script lang="ts" setup>
import useUserStore from '@/store/modules/user';
import { getEmp } from "@/api/cases/HrEmp.js"; 
import { listApplyfor2, listApplyfor, delApplyfor } from "@/api/cases/CaseSealApplyfor.js";
import { getCaseLawInfo } from "@/api/cases/CaseLaw.js";
import { ArrowLeft } from '@element-plus/icons-vue';
import type { TabsPaneContext } from 'element-plus';
import { ElMessage, ElMessageBox  } from 'element-plus';
import { RefreshRight } from '@element-plus/icons-vue';

const userStore = useUserStore();
const { proxy } = getCurrentInstance();
const tableList = ref([]); 
const queryParams = reactive({
   pageSize: 10,
   pageNum: 1,
   total: 0,
}); // 条件查询参数
const dialogVisible = ref(false);
const id = ref();
const CAppList = ref([]);  // 公函记录列表
const data = reactive({}); // 页面数据

// 统一暴露属性,方法
defineExpose({
    getList
})

/* 打开dialog6 */
function dialog6Fun(clid){
   dialogVisible.value = true;
   getCaseLawInfo(clid).then(response => {
      data.caseNo = response.data.caseNo;
      data.caseSubtime = response.data.caseSubtime;
      data.caseCaseType = response.data.caseCaseType.caseTypeName
      data.collectionTime = response.data.collectionTime;
      data.caseWtr = response.data.caseWtr;
      data.caseOppositeParties = response.data.caseOppositeParties;
      data.caseAttorney = response.data.caseAttorney;
      data.caseLawsuitStatus = response.data.caseLawsuitStatus;
      data.caseStatus = response.data.caseStatus;
      data.caseLawsuitobj = response.data.caseLawsuitobj;
      data.caseAgencyfee = response.data.caseAgencyfee;
      data.caseChargeWay = response.data.caseChargeWay;
      data.caseSubsidy = response.data.caseSubsidy;
      data.caseSubsidysal = response.data.caseSubsidysal;
      data.caseApproveStatus = response.data.caseApproveStatus;
      data.caseCause = response.data.caseCause;
      data.caseRemarks = response.data.caseRemarks;
   });
   listApplyfor(clid).then(response => {
      CAppList.value = response.rows;
   });
}

/* 删除 */
function deleteApply(id){
   ElMessageBox.confirm(
    '确定删除这条记录吗?',
    '信息提示',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    }
  )
    .then(() => {
      delApplyfor(id).then(response => {
         if(response.code == 200){
            ElMessage.success("操作成功");
         }else{
            ElMessage.error("操作失败 !");
         }
         getList();
      })
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '取消操作',
      })
    })

}

/* 查询列表 */
function getList(){
   userStore.getInfo().then((response) => {
      getEmp(response.user.hrEmpId).then(resp => {
         queryParams.applyforRecipient = resp.data.empName;
         listApplyfor2(queryParams).then(response => {
            queryParams.total = response.total;
            tableList.value =  response.rows;
         })
      })
   })
}

/* 条件查询 */
function queryParamsSubmit(){
   if(queryParams.param == "caseNo"){
      queryParams.caseNo = queryParams.content;
   }
   listApplyfor2(queryParams).then(response => {
      queryParams.total = response.total;
      tableList.value =  response.rows;
   });
}

/* 重置查询表单 */
function resetForm(){
   queryParams.content = '';
   queryParams.param = '';
   queryParams.caseNo = '';
   queryParams.applyforUsingTheItem = '';
   queryParams.caseApproveStatus = '';
   getList();
}
</script>



