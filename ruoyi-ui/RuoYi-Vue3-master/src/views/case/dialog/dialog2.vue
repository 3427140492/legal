//    添加收款记录
<template>
   <!-- <div class="app-container"> -->
      <el-dialog v-model="dialogVisible" title="添加收款记录" width="55%">
         <div>
            <el-descriptions :icon="Edit" title="案件收支" :size="large" style="margin-bottom: -10px;">
               <!-- <el-descriptions-item label="Username">kooriookami</el-descriptions-item> -->
            </el-descriptions>
            <el-divider />
            <el-form :model="form" :inline="true" :label-position="labelPosition" class="demo-form-inline">
               <el-form-item label="案件:">
                  <el-input v-model="data.caseNo" disabled  style="width: 405px;"/><span style="display: inline-black; width: 130px;"></span>
               </el-form-item>
               <el-form-item label="已收款:">
                  {{data.casePaidsal == '' || data.casePaidsal == null ? '0' : data.casePaidsal}} 元 (已经收到的款)
               </el-form-item>
               <el-form-item label="收支金额:">
                  <el-input v-model="form.cwInoutMoney" style="width: 405px;" clearable /><span style="display: inline-black; width: 130px   ;"></span>
               </el-form-item>
               <el-form-item label="收支日期:">
                  <el-date-picker
                     v-model="form.cwInoutDate"
                     type="date"
                     placeholder=""
                     value-format="YYYY-MM-DD"
                     style="width: 405px;"
                     disabled    
                  />
               </el-form-item>
              <el-form-item label="代理费:">
                  <div style="width: 405px;">{{data.caseAgencyfee == '' || data.caseAgencyfee == null ? '0' : data.caseAgencyfee}} 元</div><span style="display: inline-black; width: 130px;"></span>
               </el-form-item>
               <el-form-item label="已开发票:">
                  <div style="width: 405px;">{{data.caseInvoiced == '' || data.caseInvoiced == null ? '0' : data.caseInvoiced}} 元 (已经开出的发票)</div>
               </el-form-item>
               <el-form-item label="收支类别:">
                  <el-tree-select v-model="cwInoutTypeId" :data="cwInoutTypeList" :props="{ value: 'id', label: 'cwName', children: 'children' }" :render-after-expand="false" clearable style="width: 400px;"/>
                  <span style="display: inline-black; width: 130px;"></span>
               </el-form-item>
               <el-form-item label="支付方式:">
                  <el-select v-model="form.cwPayType" clearable style="width: 405px;" placeholder="">
                     <el-option label="转账支票" value="1" />
                     <el-option label="电汇" value="2" />
                     <el-option label="网银" value="3" />
                     <el-option label="Pos机" value="4" />
                     <el-option label="现金" value="5" />
                  </el-select>
               </el-form-item>  
               <el-form-item label="付款人:">
                  <el-input v-model="form.cwPayer" clearable style="width: 400px;"/>
               </el-form-item>            
               <el-form-item label="备注:">
                  <el-input
                        v-model="form.cwRemark"
                        :autosize="{ minRows: 2.5, maxRows: 4 }"
                        type="textarea"
                        placeholder=""
                        style="width: 990px;"
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
import { cwInoutlistType } from "@/api/cases/cwInoutType.js";
import { addInout } from "@/api/cases/CwInout.js";
import useUserStore from '@/store/modules/user';
import { getEmp } from "@/api/cases/HrEmp.js";
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
const labelPosition = ref('top'); // form表单样式
const userStore = useUserStore();
const data = reactive({}); // 页面数据
const form = reactive({}); // 表单数据
const cwInoutTypeList = ref([]); // 收支类别类型列表
const cwInoutTypeId = ref(); // 收支类别类型id
const dialogVisible = ref(false);

function propsEmitRef(id):void{
   dialogVisible.value = true;
   form.cwInoutDate = getDate();
   form.cwInoutMoney = '';
   cwInoutTypeId.value = '';
   form.cwPayType = '';
   form.cwPayer = '';
   form.cwRemark = '';
   // 获取案件信息详细信息 (添加收款记录)
   getCLAndDecById(id).then(response => {
      data.caseNo = response.data.caseNo;
      data.casePaidsal = response.data.casePaidsal;
      data.caseAgencyfee = response.data.caseAgencyfee;
      data.caseInvoiced = response.data.caseInvoiced;
      form.caseLawId = response.data.id;
   })
   // 查询收支类别列表
   cwInoutlistType().then(response => {
      cwInoutTypeList.value = response.rows;
   });
   // 查询收支人
   userStore.getInfo().then((response) => {
      getEmp(response.user.hrEmpId).then(response => {
         form.cwInoutPerson = response.data.empName;
      })
   });
}

/* 新增收支 */
function onSubmit(){
   form.cwInoutTypeId = cwInoutTypeId.value;
   form.cwEnteringdate = getDate();
   if(form.cwInoutMoney == '' || form.cwInoutMoney == null){
      ElMessage.warning("收支金额未填写 !");
      return;
   }
   if(form.cwInoutTypeId == '' || form.cwInoutTypeId == null){
      ElMessage.warning("收支类别未填写 !");
      return;
   }
   if(form.cwPayType == '' || form.cwPayType == null){
      ElMessage.warning("支付方式未填写 !");
      return;
   }
   if(form.cwPayer == '' || form.cwPayer == null){
      ElMessage.warning("付款人未填写 !");
      return;
   }
   addInout(form).then(response => {
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


