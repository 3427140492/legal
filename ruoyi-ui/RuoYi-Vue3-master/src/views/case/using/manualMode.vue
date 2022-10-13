<template>
   <div class="app-container">
      <!-- ref="ruleFormRef"
          :rules="rules" -->
      <el-form :inline="true" :label-position="labelPosition" class="demo-form-inline"  :model="form" >
        <el-form-item label=" * 用印事项:">
            <el-select v-model="form.applyforUsingTheItem" style="width: 500px;" placeholder="选择用印事项" clearable>
                <el-option label="立案文书" value="立案文书" />
                <el-option label="公函" value="公函" />
                <el-option label="介绍信" value="介绍信" />
                <el-option label="律师函" value="律师函" />
                <el-option label="非诉" value="非诉" />
              </el-select>
            <span style="display: inline-black; width: 185px;"></span>
        </el-form-item>
        <el-form-item label=" * 收函方/委托人:">
            <el-input v-model="form.sendPerson" clearable style="width: 500px;"/>
        </el-form-item>
        <el-form-item label=" * 领用日期:">
            <el-date-picker
                v-model="form.applyforReceive"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="" 
                style="width: 500px;" 
                clearable
            />
            <span style="display: inline-black; width: 185px;"></span>
        </el-form-item>
        <el-form-item label="案件:">
            <el-input v-model="form.caseNo" @click="open = true" readonly  style="width: 500px;"/>
        </el-form-item>
        <el-form-item label=" * 上传文件:" prop="caseOppositeCardnum">
              <el-button type="primary" plain>选择文件</el-button><span>未选择任何文件</span><span style="display: inline-black; width: 1000px;"></span>
          </el-form-item>
        <el-form-item label=" * 领用人:">
            {{form.applyforRecipient}}
            <span style="display: inline-black; width: 1085px;"></span>
        </el-form-item>
        <el-form-item label="备注:">
            <el-input v-model="form.remark" :autosize="{ minRows: 2.5, maxRows: 4 }" type="textarea" placeholder="" style="width: 1217px;"/>
        </el-form-item>
      </el-form>
      <br>
      <el-button type="primary" @click="onSubmit()">确认提交</el-button>

        <el-dialog v-model="open" title="" width="70%" draggable>
            <el-form :model="formInline" :inline="true">
                <el-form-item label="">
                    <el-input v-model="formInline.content"  style="width: 210px;" clearable />&nbsp;&nbsp;
                    <el-select v-model="formInline.param" placeholder="请选择类型" style="width: 180px;" clearable >
                        <el-option label="案号搜索" value="caseNo" />
                        <el-option label="委托人搜索" value="caseWtr" />
                        <el-option label="对方当事人搜索" value="caseOppositeParties" />
                        <el-option label="案由搜索" value="caseCause" />
                        <el-option label="承办律师搜索" value="caseAttorney" />
                        <el-option label="受理机关搜索" value="caseAccept" />
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" icon="Search" @click="queryParamsSubmit">检索</el-button>
                    <el-button icon="Refresh" @click="resetForm">重置</el-button>
                </el-form-item>
            </el-form>
            <el-table :data="tableList" ref="singleTableRef" highlight-current-row @current-change="handleCurrentChange" :table-layout="auto" style="width: 100%">
                <!-- <el-table-column label="序号" type="index" align="center" width="120" /> -->
                <el-table-column prop="caseNo" label="案号" show-overflow-tooltip/>
                <el-table-column prop="caseWtr" label="委托人" align="center"/>
                <el-table-column property="caseOppositeParties" label="对方当事人" align="center"/>
                <el-table-column prop="caseAgencyfee" label="代理费" align="center" width="130"/>
                <el-table-column prop="casePaidsal" label="已付金额(元)" align="center" width="130"/>
                <el-table-column prop="caseInvoiced" label="已开票(元)" align="center" width="130"/>
                <el-table-column prop="caseAttorney" label="承办律师" show-overflow-tooltip/>
                <el-table-column prop="collectionTime" label="收案日期" align="center"/>
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
            <template #footer>
                <span class="dialog-footer">
                    <el-button type="primary" @click="getCurrentRow">确定</el-button>
                </span>
            </template>
        </el-dialog>
   </div>
</template>
<script lang="ts" setup>
import useUserStore from '@/store/modules/user';
import { getEmp } from "@/api/cases/HrEmp.js"; 
import { myCaseSummary } from "@/api/cases/CaseLaw.js";
import { addApplyfor } from "@/api/cases/CaseSealApplyfor.js";
import { Edit } from '@element-plus/icons-vue';
import { ElMessage, ElMessageBox } from 'element-plus';
import { EluiChinaAreaDht } from 'elui-china-area-dht';

const labelPosition = ref('top'); // form表单样式
const form = reactive({}); // form
const formInline = reactive({}); // 条件查询form
const userStore = useUserStore();
const singleTableRef = ref<InstanceType<typeof ElTable>>();
const tableList = ref([]); 
const currentRow = ref();
const open = ref(false); // 弹出框参数
const queryParams = reactive({
   pageSize: 10,
   pageNum: 1,
   total: 0,
}); // 条件查询参数

// 统一暴露属性,方法
defineExpose({
    getList
})

const onSubmit = () => {
    if(rulesCli() == false) return;
    addApplyfor(form).then(resp => {
        if(resp.code == 200){
            ElMessage.success("已提交");
        }else{
            ElMessage.error("提交失败!");
        }
    })
}

function getCurrentRow(){
    form.caseNo = currentRow.value.caseNo;
    form.sendPerson = currentRow.value.caseWtr;
    form.applyforApprovalStatus = 1;
    form.caseid = currentRow.value.id;
    open.value = false;
}

/* 获取表格单行数据 */
const handleCurrentChange = (val) => {
  currentRow.value = val;
}

/* 查询列表 */
function getList(){
   userStore.getInfo().then((response) => {
      getEmp(response.user.hrEmpId).then(resp => {
         queryParams.caseSubmitter = resp.data.empName;
         form.applyforRecipient = resp.data.empName;
         myCaseSummary(queryParams).then(response => {
            queryParams.total = response.total;
            tableList.value =  response.rows;
         })
      })
   })
}
getList();

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

// 数据校验
const rulesCli = () => {
    if(form.applyforUsingTheItem == undefined || form.applyforUsingTheItem == ''){
        ElMessage.warning("用印事项未填写!");
        return false;
    }
    if(form.sendPerson == undefined || form.sendPerson == ''){
        ElMessage.warning("收函方/委托人未填写!");
        return false;
    }
    if(form.applyforReceive == undefined || form.applyforReceive == ''){
        ElMessage.warning("领用日期未填写!");
        return false;
    }
    if(form.caseNo == undefined || form.caseNo == ''){
        ElMessage.warning("案件未填写!");
        return false;
    }
    if(form.applyforUsingTheItem == '立案文书'){
        form.typeid = 6;
    }
    if(form.applyforUsingTheItem == '公函'){
        form.typeid = 7;
    }
    if(form.applyforUsingTheItem == '介绍信'){
        form.typeid = 8;
    }
    if(form.applyforUsingTheItem == '律师函'){
        form.typeid = 9;
    }
    if(form.applyforUsingTheItem == '非诉'){
        form.typeid = 10;
    }
}

</script>

