//    结案
<template>
   <!-- <div class="app-container"> -->
      <el-dialog v-model="dialogVisible" title="结案" width="55%">
          <div>
            <el-descriptions :icon="Edit" title="案件信息" :size="large" style="margin-bottom: -10px;">
               <!-- <el-descriptions-item label="Username">kooriookami</el-descriptions-item> -->
            </el-descriptions>
            <el-divider />
            <el-descriptions title="" :column="3" border>
               <el-descriptions-item label="案号：">{{ data.caseNo }}</el-descriptions-item>
               <el-descriptions-item label="案件类别：">{{ data.caseTypeName }}</el-descriptions-item>
               <el-descriptions-item label="委托人：">{{ data.caseWtr }}</el-descriptions-item>
               <el-descriptions-item label="案由：">{{ data.caseCause }}</el-descriptions-item>
            </el-descriptions><br><br><br>
            <el-form :model="form" :inline="true" :label-position="labelPosition" class="demo-form-inline">
               <el-form-item label="结案方式:">
                  <el-select v-model="form.caseSettleType" clearable style="width: 405px;" placeholder="">
                     <el-option label="调解" value="1" />
                     <el-option label="判决" value="2" />
                     <el-option label="撤诉" value="3" />
                     <el-option label="其他方式" value="4" />
                  </el-select>
                  <span style="display: inline-black; width: 130px;"></span>
               </el-form-item>
               <el-form-item label="结案日期:">
                  <el-date-picker
                     v-model="form.caseApprovalEndtime"
                     type="date"
                     placeholder=""
                     value-format="YYYY-MM-DD"
                     style="width: 405px;"
                  />
               </el-form-item>
               <el-form-item label="备注说明:">
                  <el-input
                        v-model="form.caseRemarks"
                        :autosize="{ minRows: 2.5, maxRows: 4 }"
                        type="textarea"
                        placeholder=""
                        style="width: 975px;"
                  />
               </el-form-item>
               <el-form-item label="">
                  <span style="color: #F65F5F;">结案后该案件将不允许修改以下内容：案件内容、付款方式、电子档案、费用减免申请；<br/>请在结案前，把以上禁止修改的内容补充齐全。</span>
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
import { getCaseLawInfo, LawEnd } from "@/api/cases/CaseLaw.js";
import { ElMessage } from 'element-plus';
// const props = defineProps({
//    id:{
//       type: Number,
//       default: () => '默认值'
//    }
// })
// 统一暴露属性,方法
defineExpose({
    propsEmitRef
})
const emit = defineEmits(['on-change']);
const labelPosition = ref('top'); // form表单样式
const data = reactive({}); // 页面数据
const form = reactive({}); // 表单数据
const dialogVisible = ref(false);

function propsEmitRef(id):void{
   dialogVisible.value = true;
   form.caseSettleType = '';
   form.caseRemarks = '';
   // 获取案件信息详细信息 (结案)
   getCaseLawInfo(id).then(response => {
      data.caseNo = response.data.caseNo;
      data.caseTypeName = response.data.caseCaseType.caseTypeName;
      data.caseWtr = response.data.caseWtr;
      data.caseCause = response.data.caseCause;
      form.id = response.data.id;
      form.caseApprovalEndtime = getDate();
      form.caseSettleStatus = 'Y';
   });
}

/* 编辑结案状态 */
function onSubmit(){
   if(form.caseSettleType == '' || form.caseSettleType == null){
      ElMessage.warning("结案方式未填写 !");
      return;
   }
   LawEnd(form).then(response => {
      if(response.code == 200){
         ElMessage.success("已提交");
      }else{
         ElMessage.error("提交失败 !");
      }
   });
   emit("on-change");
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


