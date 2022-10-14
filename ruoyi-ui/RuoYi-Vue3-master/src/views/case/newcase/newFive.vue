<template>
   <div>
      <el-descriptions :icon="Edit" title="案件信息" :size="large" style="margin-bottom: -10px;">
          <!-- <el-descriptions-item label="Username">kooriookami</el-descriptions-item> -->
      </el-descriptions>
      <el-divider />

      <el-descriptions
        title=""
        direction="vertical"
        :column="3"
        border
      >
        <el-descriptions-item label="案号："><br>{{ data.caseNo }}<br><br></el-descriptions-item>
        <el-descriptions-item label="委托人："><br>{{ data.caseWtr }}<br><br></el-descriptions-item>
        <el-descriptions-item label="案由："><br>{{ data.caseCause }}<br><br></el-descriptions-item>
        <el-descriptions-item label="诉讼地位："><br>{{ data.caseLawsuitStatus }}<br><br></el-descriptions-item>
        <el-descriptions-item label="诉讼阶段："><br>{{ data.caseStatus }}<br><br></el-descriptions-item>
        <el-descriptions-item label="最低收费："><br>{{ data.typeMoneyMin  }}<br><br></el-descriptions-item>
        <el-descriptions-item label="实收代理费："><br>{{ data.caseAgencyfee }}<br><br></el-descriptions-item>
        <el-descriptions-item label="已申请减免(元)："><br>{{ data.decreaseMoney }}<br><br></el-descriptions-item>   
      </el-descriptions><br><br>

      <el-descriptions :icon="Edit" title="减免申请" :size="large" style="margin-bottom: -10px;">
          <!-- <el-descriptions-item label="Username">kooriookami</el-descriptions-item> -->
      </el-descriptions>
      <el-divider />

      <el-form :model="form" :inline="true" :label-position="labelPosition" class="demo-form-inline">
          <el-form-item label="申请人:">
              <el-input v-model="form.person" disabled  style="width: 500px;"/><span style="display: inline-black; width: 185px;"></span>
          </el-form-item>
          <el-form-item label="申请减免金额:">
              <el-input v-model="form.decreaseMoney" oninput="value=value.replace(/[^\d]/g,'')"  clearable  style="width: 500px;"/>
          </el-form-item>
          <el-form-item label="申请日期:">
              <el-date-picker
                v-model="form.applyDate"
                type="date"
                placeholder=""
                value-format="YYYY-MM-DD"
                style="width: 500px;"
                disabled
              />
              <span style="display: inline-black; width: 185px;"></span>
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
                  style="width: 1217px;"
              />
          </el-form-item>
      </el-form><br>
      <el-button type="primary" @click="onSubmit()">确认添加</el-button>

   </div>
</template>
<script lang="ts" setup>
import { getCLAndDecById } from "@/api/cases/CaseLaw.js";
import { addDecrease } from "@/api/cases/decrease.js";
import { ElMessage } from 'element-plus';
import { defineExpose } from "vue";

const labelPosition = ref('top'); // form表单样式
const data = reactive({}); // 页面数据
const form = reactive({});
const emit = defineEmits<{(e:'toFather'):void}>();

function onSubmit() {
  addDecrease(form).then(response => {
    if(response.code == 200){
      ElMessage.success("已提交");
      emit("toFather");
    }else{
      ElMessage.error("提交失败 !");
    }
  })
}

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

export interface ExposeType {
  getCaseLawId:Function
}
const getCaseLawId = (val:number) => {
  getCLAndDecById(val).then(response => {
    form.caseLawId = val;
    form.person = response.data.caseWtr;
    form.applyDate = getDate();
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
defineExpose({
  getCaseLawId
})

</script>





