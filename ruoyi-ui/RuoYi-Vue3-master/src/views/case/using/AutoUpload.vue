<template>
   <div class="app-container">
        <el-form :model="formInline" :inline="true" style="margin-left: 0.2%;">
            <el-form-item label="">
                <el-input v-model="formInline.content"  style="width: 210px;" clearable />&nbsp;&nbsp;
                <el-select v-model="formInline.param" placeholder="请选择类型" style="width: 180px;" clearable >
                    <el-option label="案号搜索" value="caseNo"/>
                    <el-option label="委托人搜索" value="caseWtr" />
                    <el-option label="对方当事人搜索" value="caseOppositeParties" />
                    <el-option label="案由搜索" value="caseCause" />
                    <el-option label="承办律师搜索" value="caseAttorney" />
                    <el-option label="受理机关搜索" value="caseAccept" />
                </el-select>
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
            <el-form-item>
                <el-button type="primary" icon="Search" @click="queryParamsSubmit">检索</el-button>
                <el-button icon="Refresh" @click="resetForm">重置</el-button>
            </el-form-item>
        </el-form>
        <el-table :data="tableList" border :table-layout="auto" highlight-current-row style="width: 100%">
            <el-table-column prop="caseNo" label="案号" show-overflow-tooltip align="center"/>
            <el-table-column prop="caseWtr" label="委托人" align="center"/>
            <el-table-column property="caseOppositeParties" label="对方当事人" align="center"/>
            <el-table-column prop="caseAgencyfee" label="代理费" align="center" width="130"/>
            <el-table-column prop="casePaidsal" label="已付金额(元)" align="center" width="130"/>
            <el-table-column prop="caseInvoiced" label="已开票(元)" align="center" width="130"/>
            <el-table-column prop="caseAttorney" label="承办律师" show-overflow-tooltip align="center"/>
            <el-table-column prop="collectionTime" label="收案日期" align="center"/>
            <el-table-column label="操作" align="center">
                <template #default="scope">
                    <el-button @click="openDialog(scope.row)">上传</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div style="margin-top: -18px;">
            <pagination
                v-show="queryParams.total > 0"
                :total="queryParams.total"
                v-model:page="queryParams.pageNum"
                v-model:limit="queryParams.pageSize"
                @pagination="getList"
            />
        </div>

        <el-dialog v-model="dialogVisible" title="批量生成文书" width="50%">
            <el-table :data="ApplyList" border 
                :table-layout="auto" 
                :cell-class-name="isRender" 
                @selection-change="handleSelectionChange"
                style="width: 100%">
                <el-table-column type="selection" width="120" align="center"></el-table-column>
                <el-table-column label="编号" type="index" align="center" width="150" />
                <el-table-column prop="file_name" label="模板名称" align="center"/>
                <el-table-column prop="filename" label="">
                    <template #default="scope">
                        <span style="color: #F96666;">{{scope.row.filename == "空" ? '' : '立案文书已上传...'}}</span>
                    </template>
                </el-table-column>
            </el-table>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogVisible = false">关闭</el-button>
                    <el-button type="primary" @click="onSubmit">批量生成</el-button>
                </span>
            </template>
        </el-dialog>

        <autoUpoloadLog ref="propsEmitDialog"/>
   </div>
</template>
<script lang="ts" setup>
import useUserStore from '@/store/modules/user';
import { getEmp } from "@/api/cases/HrEmp.js"; 
import { myCaseSummary } from "@/api/cases/CaseLaw.js";
import { ElMessage, ElMessageBox } from 'element-plus';
import { RefreshRight } from '@element-plus/icons-vue';
import { ElTable } from 'element-plus';
import autoUpoloadLog from '../dialog/autoUpoloadLog.vue';

const formInline = reactive({}); // 条件查询form
const userStore = useUserStore();
const tableList = ref([]); 
const open = ref(false); // 弹出框参数
const queryParams = reactive({
   pageSize: 10,
   pageNum: 1,
   total: 0,
}); // 条件查询参数
const dialogVisible = ref(false);
const multipleSelection = ref();
const propsEmitDialog = ref();
const applyforRecipient = ref();
// 统一暴露属性,方法
defineExpose({
    getList
})

function openDialog(row){
    propsEmitDialog.value.propsEmitRef(row,applyforRecipient.value);
}

/* 查询列表 */
function getList(){
   userStore.getInfo().then((response) => {
      getEmp(response.user.hrEmpId).then(resp => {
         queryParams.caseSubmitter = resp.data.empName;
        //  form.applyforRecipient = resp.data.empName;
        applyforRecipient.value = resp.data.empName;
         myCaseSummary(queryParams).then(response => {
            queryParams.total = response.total;
            tableList.value =  response.rows;
         })
      })
   })
}

/* 条件查询 */
function queryParamsSubmit(){
   getQueryParams();
   myCaseSummary(queryParams).then(response => {
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
}

/* 重置查询表单 */
function resetForm(){
   queryParams.caseWtr = '';
   queryParams.caseOppositeParties = '';
   queryParams.caseNo = '';
   queryParams.caseCause = '';
   queryParams.caseAttorney = '';
   queryParams.caseAccept = '';
   formInline.content = '';
   formInline.param = '';
   getList();
}

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
</script>

