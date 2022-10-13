<template>
    <div>
        <el-descriptions :icon="Edit" title="案件信息" :size="large" style="margin-bottom: -10px;">
            <!-- <el-descriptions-item label="Username">kooriookami</el-descriptions-item> -->
        </el-descriptions>
        <el-divider />
        <el-descriptions title="" direction="vertical" :column="10" border >
            <el-descriptions-item label="案号">{{ data.caseNo }}</el-descriptions-item>
            <el-descriptions-item label="案件类型">{{ data.caseCaseType }}</el-descriptions-item>
            <el-descriptions-item label="案由" >{{ data.caseCause }}</el-descriptions-item>
            <el-descriptions-item label="委托人">{{ data.caseWtr }}</el-descriptions-item>
            <el-descriptions-item label="利益冲突">无</el-descriptions-item>
            <el-descriptions-item label="承办律师">{{ data.caseAttorney }}</el-descriptions-item>
            <el-descriptions-item label="收案时间">{{ data.collectionTime }}</el-descriptions-item>
            <el-descriptions-item label="结案">{{ data.caseSettleStatus == 'Y' ? '已结案' : '未结案'}}</el-descriptions-item>
            <el-descriptions-item label="代理费">{{ data.caseAgencyfee }}</el-descriptions-item>
            <el-descriptions-item label="收案审批">{{ data.caseApproveStatus == '1' ? '审批中' : data.caseApproveStatus == '2' ? '审批通过' : '审批不通过' }}</el-descriptions-item>
        </el-descriptions><br>
            
        
        <el-descriptions :icon="Edit" title="文书记录" :size="large" style="margin-bottom: -10px;">
            <!-- <el-descriptions-item label="Username">kooriookami</el-descriptions-item> -->
        </el-descriptions>
        <el-divider />

        <el-table :data="CAppList" border style="width: 100%" :table-layout="auto">
            <el-table-column prop="caseNo" label="案号" width="170" show-overflow-tooltip/>
            <el-table-column prop="applyforRecipient" label="领用人" />
            <el-table-column prop="sendPerson" label="收函方/委托人" />
            <el-table-column prop="applyforUsingTheItem" label="用印事项" width="170" show-overflow-tooltip/>   
            <el-table-column prop="applyforReceive" label="领用日期" />   
            <el-table-column prop="caseFiles.filename" label="文书名称"  width="170" show-overflow-tooltip/>   
            <el-table-column label="审批状态">
                <template #default="scope">
                    <span>{{ scope.row.applyforApprovalStatus == '1' ? '审批中' : scope.row.applyforApprovalStatus == '2' ? '审批通过' : '审批不通过' }}</span>
                </template>   
            </el-table-column>      
        </el-table>
    </div>
</template>

<script lang="ts" setup>
import { getCaseLawInfo } from "@/api/cases/CaseLaw.js";
import { listApplyfor } from "@/api/cases/CaseSealApplyfor.js";
import { ElMessage } from 'element-plus';
// 统一暴露属性,方法
defineExpose({
    propsEmitRef
})
const labelPosition = ref('top'); // form表单样式
const data = reactive({}); // 页面数据
const CAppList = ref([]); 

function propsEmitRef(id):void{
   getCaseLawInfo(id).then(response => {
        data.caseNo = response.data.caseNo;
        data.caseCaseType = response.data.caseCaseType.caseTypeName;
        data.caseCause = response.data.caseCause;
        data.caseWtr = response.data.caseWtr;
        data.caseAttorney = response.data.caseAttorney;
        data.collectionTime = response.data.collectionTime;
        data.caseSettleStatus = response.data.caseSettleStatus;
        data.caseAgencyfee = response.data.caseAgencyfee;
        data.caseApproveStatus = response.data.caseApproveStatus;
   });
   listApplyfor(id).then(response => {
      CAppList.value = response.rows;
   });
}
</script>