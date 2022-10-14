// 全所案件汇总
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
               <el-select v-model="formInline.typeId" placeholder="案件类型" style="width: 180px;" clearable >
                  <el-option v-for="t in caseTypeList" :key="t.id" :label="t.caseTypeName" :value="t.id"/>
               </el-select>&nbsp;&nbsp;
               <el-select v-model="formInline.caseSettleStatus" placeholder="选中案件状态" style="width: 180px;" clearable >
                  <el-option label="未结案" value="N" />
                  <el-option label="已结案" value="Y" />
               </el-select>&nbsp;&nbsp;
               <el-select v-model="queryParams.collectionTime" placeholder="选择案件年份" style="width: 130px;" clearable >
                  <el-option v-for="y in yearList" :key="y.id" :label="y.collectionTime + '年案'" :value="y.collectionTime"/>
               </el-select>&nbsp;&nbsp;
               <el-select v-model="queryParams.caseChargeWay" placeholder="选择收费方式" style="width: 180px;" clearable >
                  <el-option label="免费" value="免费"/>
                  <el-option label="计件收费" value="计件收费"/>
                  <el-option label="计时收费" value="计时收费"/>
                  <el-option label="按标的额比例收费" value="按标的额比例收费"/>
                  <el-option label="风险代理收费" value="风险代理收费"/>
                  <el-option label="标的+风险代理" value="标的+风险代理"/>
                  <el-option label="固定+风险代理" value="固定+风险代理"/>    
               </el-select>
         </el-form-item>
         <el-form-item>
               <el-button type="primary" icon="Search" @click="queryParamsSubmit">检索</el-button>
               <el-button icon="Refresh" @click="resetForm">重置</el-button>
               <el-button>打印</el-button>
               <el-button>导出</el-button>
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
         <el-table-column label="收案日期" prop="collectionTime" sortable align="center"/>
         <el-table-column label="委托人/当事人" align="center" prop="caseWtr" />
         <el-table-column label="对方当事人" align="center" prop="caseOppositeParties" />
         <el-table-column label="诉讼阶段" align="center" prop="caseStatus" resizable show-overflow-tooltip/>
         <el-table-column label="诉讼地位" align="center" prop="caseLawsuitStatus" />
         <el-table-column label="承办律师" align="center" prop="caseAttorney" width="220" resizable show-overflow-tooltip/>
         <el-table-column label="诉讼标的" align="center" prop="caseLawsuitobj" />
         <el-table-column label="代理费(元)" align="center" prop="caseAgencyfee" />
         <el-table-column label="已付款(元)" align="center" prop="casePaidsal" />
         <el-table-column label="已开票(元)" align="center" prop="caseInvoiced" />
         <el-table-column label="结案" align="center">
               <template #default="scope">
                  <span>{{ scope.row.caseSettleStatus == 'Y' ? '已结案' : '未结案' }}</span>
               </template>
         </el-table-column>
         <el-table-column label="结案方式" align="center">
               <template #default="scope">
                  <span>{{ scope.row.caseSettleType == '0' ? "默认" : scope.row.caseSettleType == '1' ? "调解" : scope.row.caseSettleType == '2' ? "判决" : scope.row.caseSettleType == '3' ? "撤诉" : "其他方式" }}</span>
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
   </div>
</template>

<script lang="ts" setup>
import { myCaseSummary, getCaseLawInfo, editCaseLaw, myStayYear } from "@/api/cases/CaseLaw.js";
import { listCaseType } from "@/api/cases/CaseCaseType.js";
import useUserStore from '@/store/modules/user';
import { ArrowLeft } from '@element-plus/icons-vue';
import type { TabsPaneContext } from 'element-plus';
import { ElMessage } from 'element-plus';
import { RefreshRight } from '@element-plus/icons-vue';

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
   id: 0,
   caseShare: '',
}); // 条件查询参数
const down = reactive({
   show: false,
   show2: false,
   show3: false,
}); // 下拉框参数
const id = ref();   // 案件id
const yearList = ref();

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
   myStayYear().then(response => {
      yearList.value = response.rows;
   })
}

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
   queryParams.collectionTime = '';
   queryParams.caseChargeWay = '';
   
   formInline.content = '';
   formInline.param = '';
   formInline.caseApproveStatus = '';
   formInline.typeId = '';
   formInline.caseSettleStatus = '';
   formInline.sortParam = '';
   getList();
}


</script>



