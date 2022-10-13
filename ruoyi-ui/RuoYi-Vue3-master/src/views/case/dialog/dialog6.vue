//   详情
<template>
   <!-- <div class="app-container"> -->
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
   <!-- </div> -->
</template>

<script lang="ts" setup>
import { getCaseLawInfo } from "@/api/cases/CaseLaw.js";
import { getPerformancesplit } from "@/api/cases/performancesplit.js";
import { listApplyfor } from "@/api/cases/CaseSealApplyfor.js";
import { ElMessage } from 'element-plus';
// 统一暴露属性,方法
defineExpose({
    propsEmitRef
})
const labelPosition = ref('top'); // form表单样式
const data = reactive({}); // 页面数据
const dialogVisible = ref(false);
const CwPerList = ref([]); // 承办律时列表
const CAppList = ref([]);  // 公函记录列表
const cid = ref();

function propsEmitRef(id):void{
   dialogVisible.value = true;
   cid.value = id;
   getCaseLawInfoFun();
}

function getCaseLawInfoFun(){
   getCaseLawInfo(cid.value).then(response => {
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
   getPerformancesplit(cid.value).then(response => {
      CwPerList.value = response.rows;
   });
   listApplyfor(cid.value).then(response => {
      CAppList.value = response.rows;
   });
}
</script>


