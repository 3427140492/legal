//   费用减免申请
<template>
   <!-- <div class="app-container"> -->
      <el-dialog v-model="dialogVisible" title="费用减免申请" width="55%">
          <div>
            <el-descriptions :icon="Edit" title="案件信息" :size="large" style="margin-bottom: -10px;">
               <!-- <el-descriptions-item label="Username">kooriookami</el-descriptions-item> -->
            </el-descriptions>
            <el-divider />
            <el-descriptions title="" :column="3" border>
               <el-descriptions-item label="案号：">{{ data.caseNo }}</el-descriptions-item>
               <el-descriptions-item label="委托人：">{{ data.caseWtr }}</el-descriptions-item>
               <el-descriptions-item label="案由：">{{ data.caseCause }}</el-descriptions-item>
               <el-descriptions-item label="诉讼地位：">{{ data.caseLawsuitStatus }}</el-descriptions-item>
               <el-descriptions-item label="诉讼阶段：">{{ data.caseStatus }}</el-descriptions-item>
               <el-descriptions-item label="最低收费：">{{ data.typeMoneyMin  }}</el-descriptions-item>
               <el-descriptions-item label="实收代理费：">{{ data.caseAgencyfee }}</el-descriptions-item>
               <el-descriptions-item label="已申请减免(元)：">{{ data.decreaseMoney }}</el-descriptions-item>   
            </el-descriptions><br><br><br>
            <el-descriptions :icon="Edit" title="减免申请" :size="large" style="margin-bottom: -10px;">
               <!-- <el-descriptions-item label="Username">kooriookami</el-descriptions-item> -->
            </el-descriptions>
            <el-divider />
            <el-form :model="form" :inline="true" :label-position="labelPosition" class="demo-form-inline">
               <el-form-item label="申请人:">
                  <el-input v-model="form.person" disabled  style="width: 405px;"/><span style="display: inline-black; width: 130px;"></span>
               </el-form-item>
               <el-form-item label="申请减免金额:">
                  <el-input v-model="form.decreaseMoney" oninput="value=value.replace(/[^\d]/g,'')"  clearable  style="width: 405px;"/>
               </el-form-item>
               <el-form-item label="申请日期:">
                  <el-date-picker
                     v-model="form.applyDate"
                     type="date"
                     placeholder=""
                     value-format="YYYY-MM-DD"
                     style="width: 405px;"
                     disabled    
                  />
                  <span style="display: inline-black; width: 130px;"></span>
               </el-form-item>
               <el-form-item label="相关文件:" prop="caseCorrelationFile">
                  <el-button type="primary" plain>选择文件</el-button><span>未选择任何文件</span>
               </el-form-item>
               <el-form-item label="备注:">
                  <el-input
                        v-model="form.reason"
                        :autosize="{ minRows: 2.5, maxRows: 4 }"
                        type="textarea"
                        placeholder=""
                        style="width: 975px;"
                  />
               </el-form-item>
            </el-form>
         </div>
         <template #footer>
            <span class="dialog-footer">
               <el-button @click="dialogVisible = false">取消</el-button>
               <el-button type="primary" @click="onSubmit">确认提交</el-button>
            </span>
         </template>
      </el-dialog>
   <!-- </div> -->
</template>

<script lang="ts" setup>
import { getCLAndDecById } from "@/api/cases/CaseLaw.js";
import { addDecrease } from "@/api/cases/decrease.js";
import { ElMessage } from 'element-plus';
// const props = defineProps({
//    id:{
//       type: String,
//       default: () => '默认值'
//    }
// })
// 统一暴露属性,方法
defineExpose({
    propsEmitRef
})
const labelPosition = ref('top'); // form表单样式
// const id = ref();   // 案件id
const data = reactive({}); // 页面数据
const form = reactive({}); // 表单数据
const dialogVisible = ref(false);

function propsEmitRef(id):void{
   dialogVisible.value = true;
   form.reason = '';
   form.decreaseMoney = '';
   form.applyDate = getDate();
   // 查询案件信息 (减免申请数据)
   getCLAndDecById(id).then(response => {
      form.caseLawId = response.data.id;
      form.person = response.data.caseWtr;
      form.status = "1";
      data.caseNo = response.data.caseNo;
      data.caseWtr = response.data.caseWtr;
      data.caseCause = response.data.caseCause;
      data.caseLawsuitStatus = response.data.caseLawsuitStatus;
      data.caseStatus = response.data.caseStatus;
      data.typeMoneyMin = response.data.caseCaseType.typeMoneyMin;
      data.caseAgencyfee = response.data.caseAgencyfee;
      data.decreaseMoney = response.data.caseDecrease.decreaseMoney;
   })
}

/* 新增减免申请 */
function onSubmit(){
   if(form.decreaseMoney == ''){
      ElMessage.warning("申请减免金额未填写 !");
      return;
   }
   addDecrease(form).then(response => {
      if(response.code == 200){
         ElMessage.success("已提交");
      }else{
         ElMessage.error("提交失败 !");
      }
   })
   dialogVisible.value = false;
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


