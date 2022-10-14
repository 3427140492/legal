//    代开发票
<template>
   <!-- <div class="app-container"> -->
      <el-dialog v-model="dialogVisible" title="代开发票" width="60%">
         <el-descriptions :icon="Edit" title="基本资料" :size="large" style="boder: 1px solid red;">
          <!-- <el-descriptions-item label="Username">kooriookami</el-descriptions-item> -->
         </el-descriptions>
         <el-divider />
         <el-form :model="form" :inline="true" :label-position="labelPosition" class="demo-form-inline" style="margin-left: 30px;">
            <el-form-item label=" * 案件:">
               <el-input v-model="data.caseNo" readonly @click="innerVisible = true" style="width: 440px;"/><span style="display: inline-black; width: 120px;"></span>
            </el-form-item>
            <el-form-item label=" * 申请人:">
               <el-input v-model="data.caseAttorney" readonly style="width: 440px;"/>
            </el-form-item>
            <el-form-item label=" 委托人:">
               <div style="width: 440px;">{{ data.caseWtr == '' || data.caseWtr == null ? '-' : data.caseWtr }}</div>
               <span style="display: inline-black; width: 120px;"></span>
            </el-form-item>
            <el-form-item label=" 案由:">
               <div style="width: 440px;">{{ data.caseCause == '' || data.caseCause == null ? '-' : data.caseCause }}</div>
            </el-form-item>
            <el-form-item label=" 代理费:">
               <div style="width: 440px;">{{ data.caseAgencyfee == '' || data.caseAgencyfee == null ? '0' : data.caseAgencyfee }}</div>
               <span style="display: inline-black; width: 120px;"></span>
            </el-form-item>
            <el-form-item label=" 已到款项:">
               <div style="width: 440px;">{{ data.casePaidsal == '' || data.casePaidsal == null ? '0' : data.casePaidsal }} 元（已经收到的款)</div>
            </el-form-item>
            <el-form-item label=" 已开发票:">
               <div style="width: 440px;">{{ data.caseInvoiced == '' || data.caseInvoiced == null ? '0' : data.caseInvoiced }} 元（已经收到的款)</div>
               <span style="display: inline-black; width: 120px;"></span>
            </el-form-item>
            <el-form-item label=" 办案律师:">
               <div style="width: 440px;">{{ data.caseAttorney == '' || data.caseAttorney == null ? '-' : data.caseAttorney }}</div>
            </el-form-item>
            <el-form-item label=" * 发票抬头:">
               <el-input v-model="form.cwInvoiceTitle" clearable style="width: 440px;"/><span style="display: inline-black; width: 120px;"></span>
            </el-form-item>
            <el-form-item label=" * 开票金额:">
               <el-input v-model="form.cwInvoiceMoney" oninput="value=value.replace(/[^\d]/g,'')" clearable  style="width: 440px;"/>
            </el-form-item>
            <el-form-item label=" * 开票项目:">
               <el-select v-model="form.cwInvoiceItem" clearable placeholder="" style="width: 440px;">
                  <el-option label="律师代理费" value="1"/>
                  <el-option label="法律顾问费" value="2"/>
                  <el-option label="咨询费" value="3"/>
                  <el-option label="法务托管费" value="4"/>
                  <el-option label="非诉" value="5"/>
                  <el-option label="其他（备注中填写）" value="5"/>
               </el-select>
               <span style="display: inline-black; width: 120px;"></span>
            </el-form-item>
            <el-form-item label=" * 开具类型:">
               <el-select v-model="form.cwOpenType" clearable placeholder="" style="width: 440px;">
                  <el-option label="企业" value="1"/>
                  <el-option label="个人" value="2"/>
               </el-select>
            </el-form-item>
            <el-form-item label=" * 发票类型:">
               <el-select v-model="form.cwInvoiceType" clearable placeholder="" style="width: 440px;">
                  <el-option label="增值税专用发票" value="1"/>
                  <el-option label="增值税普通发票" value="2"/>
                  <el-option label="通用机打发票" value="3"/>
               </el-select>
               <span style="display: inline-black; width: 120px;"></span>
            </el-form-item>
            <el-form-item label=" * 纳税人识别号:">
               <el-input v-model="form.cwTaxpayerIdentityNumber" oninput="value=value.replace(/[^\d]/g,'')" clearable  style="width: 440px;"/>
            </el-form-item>
            <el-form-item label=" * 发票号:">
               <el-input v-model="form.cwInvoiceNumber" clearable  style="width: 440px;"/><span style="display: inline-black; width: 120px;"></span>
            </el-form-item>
            <el-form-item label=" * 开票日期:">
               <el-date-picker v-model="form.cwInvoiceDate" type="date" placeholder="" readonly  style="width: 440px;" />
            </el-form-item>
         </el-form><br>
         <div>
            <span><el-icon><Edit/></el-icon></span>
            <font style="color: #494949;font-size: 16px;font-weight: 700;"> 其他选项</font> &nbsp;&nbsp;
            <el-button size="small" plain @click="btnShow">{{ btnText }}</el-button>
         </div>
         <el-divider />

        <el-dialog v-model="innerVisible" width="45%" title="选择案件" append-to-body align-center draggable>
            <el-form :model="queryParams" :inline="true" style="margin-left: 0.4%; margin-top: -20px;">
               <el-form-item label="">
                  <el-input v-model="queryParams.content"  style="width: 200px;" clearable />&nbsp;&nbsp;
                  <el-select v-model="queryParams.param" placeholder="请选择类型" style="width: 180px;" clearable >
                     <el-option label="案号搜索" value="caseNo" />
                     <el-option label="委托人搜索" value="caseWtr" />
                     <el-option label="对方当事人搜索" value="caseOppositeParties" />
                     <el-option label="案由搜索" value="caseCause" />
                     <el-option label="承办律师搜索" value="caseAttorney" />
                     <el-option label="受理法院搜索" value="caseCourt" />
                  </el-select>&nbsp;&nbsp;
               </el-form-item>
               <el-form-item>
                  <el-button type="primary" icon="Search" @click="querySubmit">检索</el-button>
                  <el-button icon="Refresh" @click="resetQuery">重置</el-button>
               </el-form-item>
            </el-form>
            <el-table 
               :data="invoiceClList"
               ref="singleTableRef"
               highlight-current-row
               @current-change="handleCurrentChange"
               :table-layout="auto" style="width: 100%">
               <el-table-column label="序号" type="index" align="center" width="120" />
               <el-table-column prop="caseNo" label="案号"/>
               <el-table-column prop="client.client" label="委托人"/>
               <el-table-column property="caseOppositeParties" label="对方当事人" />
               <el-table-column prop="hrEmp.empName" label="承办律师"/>
               <el-table-column prop="collectionTime" label="收案日期"/>
            </el-table>
            <br>
            <el-pagination style="float: right;"
               v-model:currentPage="queryParams.currentPage"
               v-model:page-size="queryParams.pageSize"
               :page-sizes="[10, 20, 30, 50]"
               :small="small"
               background="background"
               layout="total, sizes, prev, pager, next, jumper"
               :total="queryParams.total"
               @size-change="getTable"
               @current-change="getTable"
            /><br>
            <template #footer>
               <span class="dialog-footer">
                  <el-button type="primary" @click="selectedCases">确认</el-button>
               </span>
            </template>
         </el-dialog>

         <div v-show="show3_in" style="margin-left: 30px;">
            <el-form :model="form" :inline="true" :label-position="labelPosition" class="demo-form-inline">
               <el-form-item label="相关文件:" prop="caseOppositeCardnum">
                  <div style="width: 440px;"><el-button type="primary" plain>选择文件</el-button><span>未选择任何文件</span></div>
                  <span style="display: inline-black; width: 120px;"></span>
               </el-form-item>
               <el-form-item label="基本开户银行:">
                  <el-input v-model="form.cwBasicBankofdeposit" clearable style="width: 440px;"/>
               </el-form-item>
               <el-form-item label="基本开户账号:">
                  <el-input v-model="form.cwAccountNumber" clearable style="width: 440px;"/><span style="display: inline-black; width: 120px;"></span>
               </el-form-item>
               <el-form-item label="注册场所地址:">
                     <el-input v-model="form.cwRegisteredAddress" clearable  style="width: 440px;"/>
               </el-form-item>
               <el-form-item label="注册固定电话:">
                  <div style="width: 1240px;"><el-input v-model="form.cwFixedlineTelephone" oninput="value=value.replace(/[^\d]/g,'')" clearable style="width: 440px;"/></div>
               </el-form-item>

               <el-form-item label="收件人:">
                  <el-input v-model="form.systemUserRecipients" clearable style="width: 440px;"/><span style="display: inline-black; width: 120px;"></span>
               </el-form-item>
               <el-form-item label="联系电话:">
                     <el-input v-model="form.expressageTel" clearable oninput="value=value.replace(/[^\d]/g,'')" style="width: 440px;"/>
               </el-form-item>
               <el-form-item label="物流公司:">
                  <el-input v-model="form.logistics" clearable style="width: 440px;"/><span style="display: inline-black; width: 120px;"></span>
               </el-form-item>
               <el-form-item label="物流单号:">
                     <el-input v-model="form.expressageCourierNumber" clearable oninput="value=value.replace(/[^\d]/g,'')" style="width: 440px;"/>
               </el-form-item>
               <el-form-item label="收件地址:">
                  <el-input v-model="form.expressageSendaunit" clearable style="width: 1031px;"/>
               </el-form-item>

               <el-form-item label="申请备注:">
                  <el-input v-model="form.cwApplicationRemark" :autosize="{ minRows: 2.5, maxRows: 4 }" type="textarea" placeholder="" style="width: 1031px;"/>
               </el-form-item>
                <el-form-item label="开票备注:">
                  <el-input v-model="form.cwInvoiceRemark" :autosize="{ minRows: 2.5, maxRows: 4 }" type="textarea" placeholder="" style="width: 1031px;"/>
               </el-form-item>
            </el-form>
         </div>
         <template #footer>
            <span class="dialog-footer">
               <el-button @click="open4 = false">取消</el-button>
               <el-button type="primary" @click="onSubmit">确认提交</el-button>
            </span>
         </template>
      </el-dialog>
   <!-- </div> -->
</template>

<script lang="ts" setup>
import { getCaseLawInfo, selectInvoiceCaseLawList } from "@/api/cases/CaseLaw.js";
import { addInvoice } from "@/api/cases/invoice.js";
import { ElMessage } from 'element-plus';
// const props = defineProps({
//    hrEmpId:{
//       type: Number,
//       default: () => '0'
//    }
// })
// defineProps<{hrid:string}>();
// 统一暴露属性,方法
defineExpose({
    propsEmitRef, propsEmitRef2
})
const labelPosition = ref('top'); // form表单样式
const form = reactive({}); // 表单数据
const data = reactive({}); // 表单数据
const btnText = ref('展开');
const show3_in = ref(false); // 展开选项
const dialogVisible = ref(false);
const innerVisible = ref(false); // 案件信息弹出框
const invoiceClList = ref([]); // 案件信息弹出框列表
const queryParams = reactive({
   pageSize: 10,
   currentPage: 1,
   total: 0,
}) // 案件信息弹出框-条件查询参数
const currentRow = ref(); // 选中表格行数据

function propsEmitRef(hrEmpId){
   dialogVisible.value = true;
   resetQuery();
   queryParams.hrEmpId = hrEmpId;
   form.cwInvoiceDate = getDate();
   selectInvoiceCaseLawList(queryParams).then(response => {
      invoiceClList.value = response.rows;
      queryParams.total = response.total;
   })
}

function propsEmitRef2(row){
   console.log(row);
   data.caseNo = row.caseNo;
   data.caseAttorney = row.hrEmp.empName;
   data.caseWtr = row.caseWtr;
   data.caseCause = row.caseCause;
   data.caseAgencyfee = row.caseAgencyfee;
   data.casePaidsal = row.casePaidsal;
   data.caseInvoiced = row.caseInvoiced;

   form.caseLawId = row.id;
   form.caseNo = row.caseNo;
   form.systemUserAddresser = row.hrEmp.empName;
}

/* 提价申请发票 */
function onSubmit(){
   if(rulesCli() == false) return;
   addInvoice(form).then(response => {
      if(response.code == 200){
         ElMessage.success("已提交");
      }else{
         ElMessage.error("提交失败 !");
      }
   })
   dialogVisible.value = false;
}

/* 设置页面数据 */
function selectedCases(){
   data.caseNo = currentRow.value.caseNo;
   data.caseAttorney = currentRow.value.hrEmp.empName;
   data.caseWtr = currentRow.value.client.client;
   data.caseCause = currentRow.value.caseCause;
   data.caseAgencyfee = currentRow.value.caseAgencyfee;
   data.casePaidsal = currentRow.value.casePaidsal;
   data.caseInvoiced = currentRow.value.caseInvoiced;

   form.caseLawId = currentRow.value.id;
   // form.cwInvoiceTitle = currentRow.value.caseWtr;
   form.caseNo = currentRow.value.caseNo;
   form.systemUserAddresser = currentRow.value.hrEmp.empName;
   innerVisible.value = false;
}

/* 查询方法 */
function querySubmit(){
   if(queryParams.param == 'caseNo'){
      queryParams.caseNo = queryParams.content;
   }
   if(queryParams.param == 'caseWtr'){
      queryParams.caseWtr = queryParams.content;
   }
   if(queryParams.param == 'caseOppositeParties'){
      queryParams.caseOppositeParties = queryParams.content;
   }
   if(queryParams.param == 'caseCause'){
      queryParams.caseCause = queryParams.content;
   }
   if(queryParams.param == 'caseAttorney'){
      queryParams.caseAttorney = queryParams.content;
   }
   if(queryParams.param == 'caseCourt'){
      queryParams.caseCourt = queryParams.content;
   }
   console.log(queryParams);
   selectInvoiceCaseLawList(queryParams).then(response => {
      invoiceClList.value = response.rows;
      queryParams.total = response.total;
   })
}

/* 重置 */
function resetQuery(){
   btnText.value = '展开';
   show3_in.value = false;

   data.caseNo = '';
   data.caseAttorney = '';
   data.caseWtr = '';
   data.caseCause = '';
   data.caseAgencyfee = '';
   data.casePaidsal = '';
   data.caseInvoiced = '';
   data.caseAttorney = '';

   queryParams.param = '';
   queryParams.content = '';
   queryParams.caseNo = '';
   queryParams.caseWtr = '';
   queryParams.caseOppositeParties = '';
   queryParams.caseCause = '';
   queryParams.caseAttorney = '';
   queryParams.caseCourt = '';

   form.cwInvoiceTitle = '';
   form.cwInvoiceMoney = '';
   form.cwInvoiceItem = '';
   form.cwOpenType = '';
   form.cwInvoiceType = '';
   form.cwTaxpayerIdentityNumber = '';
   form.cwInvoiceNumber = '';
   form.cwInvoiceDate = '';
   form.cwBasicBankofdeposit = '';
   form.cwAccountNumber = '';
   form.cwRegisteredAddress = '';
   form.cwFixedlineTelephone = '';

   form.systemUserRecipients = '';
   form.expressageTel = '';
   form.expressageSendaunit = '';
   form.expressageCourierNumber = '';
   form.logistics = '';

   form.cwApplicationRemark = '';
   form.cwInvoiceRemark = '';
}

// 数据校验
const rulesCli = () => {
   if(data.caseNo == undefined || data.caseNo == ''){
      ElMessage.warning("案件未填写 ！");
      return false;
   }
   if(form.cwInvoiceTitle == undefined || form.cwInvoiceTitle == ''){
      ElMessage.warning("发票抬头未填写 ！");
      return false;
   }
   if(form.cwInvoiceMoney == undefined || form.cwInvoiceMoney == ''){
      ElMessage.warning("开票金额未填写 ！");
      return false;
   }
   if(form.cwInvoiceItem == undefined || form.cwInvoiceItem == ''){
      ElMessage.warning("开票项目未填写 ！");
      return false;
   }
   if(form.cwOpenType == undefined || form.cwOpenType == ''){
      ElMessage.warning("开具类型未填写 ！");
      return false;
   }
   if(form.cwInvoiceType == undefined || form.cwInvoiceType == ''){
      ElMessage.warning("发票类型未填写 ！");
      return false;
   }
   if(form.cwTaxpayerIdentityNumber == undefined || form.cwTaxpayerIdentityNumber == ''){
      ElMessage.warning("纳税人识别号未填写 ！");
      return false;
   }
   if(form.cwInvoiceNumber == undefined || form.cwInvoiceNumber == ''){
      ElMessage.warning("发票号未填写 ！");
      return false;
   }
   if(form.cwInvoiceDate == undefined || form.cwInvoiceDate == ''){
      ElMessage.warning("开票日期未填写 ！");
      return false;
   }
   console.log(form.systemUserRecipients);
   console.log(typeof(form.systemUserRecipients))
   if(form.systemUserRecipients != undefined && form.systemUserRecipients != ''){
      if(form.expressageTel == undefined || form.expressageTel == ''){
         ElMessage.warning("联系电话未填写 ！");
         return false;
      }
      if(form.expressageTel == undefined || form.expressageTel == ''){
         ElMessage.warning("联系电话未填写 ！");
         return false;
      }
      if(form.logistics == undefined || form.logistics == ''){
         ElMessage.warning("物流公司未填写 ！");
         return false;
      }
      if(form.expressageCourierNumber == undefined || form.expressageCourierNumber == ''){
         ElMessage.warning("物流单号未填写 ！");
         return false;
      }
      if(form.expressageSendaunit == undefined || form.expressageSendaunit == ''){
         ElMessage.warning("收件地址未填写 ！");
         return false;
      }
   }
   return true;
}

/* 获取表格行数据 */
const handleCurrentChange = (val) => {
  currentRow.value = val;
  console.log(currentRow.value);
}

/* 展开按钮方法 */
function btnShow (){
  if(show3_in.value == true){
      show3_in.value = false;
      btnText.value = '展开';
  }else{
      show3_in.value = true;
      btnText.value = '收起';
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


