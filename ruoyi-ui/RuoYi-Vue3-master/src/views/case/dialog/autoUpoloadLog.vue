//   自动上传用印记录
<template>
   <!-- <div class="app-container"> -->
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
   <!-- </div> -->
</template>

<script lang="ts" setup>
import { addApplyfor, getProduction } from "@/api/cases/CaseSealApplyfor.js";
import { ElMessage, ElMessageBox } from 'element-plus';
import { RefreshRight } from '@element-plus/icons-vue';
import { ElTable } from 'element-plus';
// 统一暴露属性,方法
defineExpose({
    propsEmitRef
})
const labelPosition = ref('top'); // form表单样式
const dialogVisible = ref(false);
const form = reactive({});
const ApplyList = ref([]); 
const multipleSelection = ref();

function propsEmitRef(row,applyforRecipient):void{
   dialogVisible.value = true;
   form.caseNo = row.caseNo;
   form.sendPerson = row.caseWtr;
   form.applyforApprovalStatus = 1;
   form.caseid = row.id;
   form.applyforReceive = getDate();
   form.typeid = 5;
   form.applyforRecipient = applyforRecipient;
   getProduction(row.id).then(response => {
        ApplyList.value = response.rows;
    })
   console.log(form);
}

function onSubmit(){
    var bool = true;
    for (let i = 0; i < multipleSelection.value.length; i++) {
        if (multipleSelection.value[i].filename == '空') {
            form.applyforUsingTheItem = multipleSelection.value[i].file_name;
            addApplyfor(form).then(resp => {
                if(resp.code == 200){
                    bool = true;
                }else{
                    bool = false;
                }
            })
        }
    }
    if(bool == true){
        ElMessage.success("已提交");
    }else{
        ElMessage.error("提交失败!");
    }
    dialogVisible.value = false;
}

const handleSelectionChange = (val) => {
    multipleSelection.value = val;
}

function isRender(row) {
    if (row.row.filename != '空') {
        return 'disabled-column';
    }
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

<style>
 /* 隐藏多选框 */
.disabled-column .el-checkbox__input {
  display: none;
}
</style>


