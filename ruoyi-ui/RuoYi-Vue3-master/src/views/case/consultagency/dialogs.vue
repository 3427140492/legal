//  重新立案
<template>
   <el-dialog v-model="dialogVisible" :title="title" width="60%">
      <el-descriptions :icon="Edit" title="基本信息 " :size="large" style="boder: 1px solid red;">
          <!-- <el-descriptions-item label="Username">kooriookami</el-descriptions-item> -->
      </el-descriptions>
      <el-divider />
      <el-form :model="form" :inline="true" :label-position="labelPosition" class="demo-form-inline" style="margin-left: 30px;">
         <el-form-item label=" * 服务时间:" >
            <el-date-picker
               v-model="form.collectionTime"
               type="date"
               placeholder=""
               value-format="YYYY-MM-DD"
               style="width: 440px;"
               clearable
            />
            <span style="display: inline-black; width: 120px;"></span>
         </el-form-item>
         <el-form-item label=" * 服务费用:">
              <el-input v-model="form.caseAgencyfee" oninput="value=value.replace(/[^\d]/g,'')" clearable  style="width: 440px;"/>
          </el-form-item>
          <el-form-item label=" * 咨询/代书事项:">
              <el-input v-model="form.caseCause" clearable style="width: 440px;"/>
              <span style="display: inline-black; width: 120px;"></span>
          </el-form-item>
          <el-form-item label=" * 提供服务人次:">   
              <el-input v-model="form.standard" clearable oninput="value=value.replace(/[^\d]/g,'')" style="width: 440px;"/>
          </el-form-item>
          <el-form-item label=" * 服务类型:">
              <el-radio-group v-model="form.caseCaseTypeId">
                <el-radio label="101101">口头法律咨询(人次)</el-radio>
                <el-radio label="101102">书面法律咨询(人次)</el-radio>
                <el-radio label="101103">代写法律文书(件)</el-radio>
              </el-radio-group>
          </el-form-item>
      </el-form><br>

      <div>
          <span><el-icon><Edit/></el-icon></span>
          <font style="color: #494949;font-size: 16px;font-weight: 700;"> 其他选项</font> &nbsp;&nbsp;
          <el-button size="small" plain @click="btnShow">{{ btnText }}</el-button>
      </div>
      <el-divider />

      <div v-show="show1" style="margin-left: 30px;">
          <el-form :model="form" :inline="true" :label-position="labelPosition" class="demo-form-inline">
              <el-form-item label="委托人:">
                  <el-input v-model="form.caseWtr" clearable  style="width: 440px;"/><span style="display: inline-black; width: 120px;"></span>
              </el-form-item>
              <el-form-item label="对方当事人:">
                  <el-input v-model="form.caseOppositeParties"  clearable  style="width: 440px;"/>
              </el-form-item>
              <el-form-item label="相关文件:" prop="caseCorrelationFile">
                  <el-button type="primary" plain>选择文件</el-button><span>未选择任何文件</span>
                  <span style="display: inline-black; width: 120px;"></span>
              </el-form-item>
              <el-form-item label="备注:">
                  <el-input
                      v-model="form.caseRemarks"
                      :autosize="{ minRows: 2.5, maxRows: 4 }"
                      type="textarea"
                      placeholder=""
                      style="width: 1031px;"
                  />
              </el-form-item>
          </el-form>
      </div><br>
      <div>
         <span><el-icon><Edit/></el-icon></span>
         <font style="color: #494949;font-size: 16px;font-weight: 700;"> 办案律师</font> &nbsp;&nbsp;
      </div>
      <el-divider />

      <div style="margin-left: 30px;">
         <el-form :model="dynamicValidateForm" class="demo-dynamic">
         <el-form-item>
            <el-input v-model="dynamicValidateForm.caseAttorney" disabled style="width: 200px; margin-right: 5px;"/>
            <el-input @click="getIndex(-1)" readonly v-model="dynamicValidateForm.value" style="width: 200px;"/>
            <span style="display: inline-black; margin: 0px 5px 0px 10px; color: #606266;">业绩分成:</span>
            <el-input v-model="dynamicValidateForm.cwPerformancesplit" style="width: 200px; margin-right: 5px;"/>
            <el-select v-model="dynamicValidateForm.cwSplitType" style="width: 200px; margin-right: 5px;">
               <el-option label="百分比" value="百分比"/>
               <el-option label="固定金额" value="固定金额" />
            </el-select>
            <el-button @click="addDomain" type="success">新增一行</el-button>
         </el-form-item>

         <el-form-item
            v-for="(domain, index) in dynamicValidateForm.domains"
            :key="domain.key"
            :prop="'domains.' + index + '.value'"
         >
            <el-select v-model="domain.caseAttorney" style="width: 200px; margin-right: 5px;">
               <el-option label="督办律师" value="督办律师" />
               <el-option label="协助律师" value="协助律师" />
               <el-option label="实习律师" value="实习律师" />
            </el-select>
            <el-input @click="getIndex(index)" v-model="domain.value" readonly style="width: 200px;"/>

            <span style="display: inline-black; margin: 0px 5px 0px 10px; color: #606266;">业绩分成:</span>
            <el-input v-model="domain.cwPerformancesplit" style="width: 200px; margin-right: 5px;"/>
            <el-select v-model="domain.cwSplitType" style="width: 200px; margin-right: 5px;">
               <el-option label="百分比" value="百分比"/>
               <el-option label="固定金额" value="固定金额" />
            </el-select>
            <el-button class="mt-2" @click.prevent="removeDomain(domain)" type="danger">删除</el-button>
         </el-form-item>

         </el-form>
      </div>

      <el-dialog v-model="open" title="" width="40%" draggable>
         <el-table :data="tableData" ref="singleTableRef" highlight-current-row
         @current-change="handleCurrentChange" 
         :table-layout="auto" style="width: 100%">
         <el-table-column label="序号" type="index" align="center" width="120" />
         <el-table-column prop="userName" label="用户名"/>
         <el-table-column prop="hrEmp.empName" label="姓名"/>
         <el-table-column property="hrEmp.empEntrancenum" label="职位" />
         </el-table>
         <br>
         <el-pagination style="float: right;"
         v-model:currentPage="pageParams.currentPage"
         v-model:page-size="pageParams.pageSize"
         :page-sizes="[10, 20, 30]"
         :small="small"
         background="background"
         layout="total, sizes, prev, pager, next, jumper"
         :total="pageParams.total"
         @size-change="getList"
         @current-change="getList"
         />
         <br>


         <template #footer>
          <span class="dialog-footer">
              <el-button type="primary" @click="clear(nums)">确定</el-button>
          </span>
         </template>
      </el-dialog>

      <template #footer>
         <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="onSubmit()">确认提交</el-button>
         </span>
      </template>
   </el-dialog>


</template>
<script lang="ts" setup>
import useUserStore from '@/store/modules/user';
import { getLaw, addCaseLaw, editCaseLaw, getCaseLawId } from "@/api/cases/CaseLaw.js";
import { selectSysUserByHrEmp } from "@/api/cases/SysUser.js";
import { getPerformancesplit, delPerformancesplit, addPerformancesplit } from "@/api/cases/performancesplit.js";
import { getEmp } from "@/api/cases/hrEmp.js";
import { Edit } from '@element-plus/icons-vue';
import { ElMessage, ElMessageBox } from 'element-plus';
import { EluiChinaAreaDht } from 'elui-china-area-dht';
import type { FormInstance } from 'element-plus';
import { ElTable } from 'element-plus'; 

const userStore = useUserStore();
const title = ref("添加咨询与代书");
const labelPosition = ref('top'); // form表单样式
const btnText = ref('展开');
const show1 = ref(false); // 展开选项
const open = ref(false);
const statusInfo = ref(undefined); // 诉讼地位
const form = reactive({}); // 新增委托人form
// 分页参数
const pageParams = reactive({
  pageSize: 10,
  currentPage: 1,
  total: 0
});
const tableData = ref([]); // 表格数据
const currentRow = ref();
const singleTableRef = ref<InstanceType<typeof ElTable>>();
const nums = ref(undefined); 
const dialogVisible = ref(false);
// 统一暴露属性,方法
defineExpose({
  propsEmitRef
})
const emit = defineEmits(['on-change']);
var sum = 0;
var arrs = [];
const id = ref();
const type = ref();

function propsEmitRef(lid,types){
  dialogVisible.value = true;
  if(types == 'add'){
    title.value = '添加咨询与代书';
    getCaseLawIdFun();
  }else{
    title.value = '修改咨询与代书';
    id.value = lid;
    getCaseLawInfo(lid);
  }
  type.value = types;
  show1.value = false;
  btnText.value = '展开';
  resetForm();
  getList();
  getEmpInfo();
}

const onSubmit = () => {
  if(rulesCli() == false) return;
  dispose();
  if(disposeCaseFun() == false) return;
  if(type.value == 'add'){
    addLaw();
  }else{
    editCaseLawFun();
  }
  console.log(form);
  console.log(arrs);
}

/* 增案件法律信息 */
function addLaw(){
  addCaseLaw(form).then(response => {
    if(response.code == 200){
      addPerformancesplit(arrs).then(response => {
        if(response.code == 200){
          ElMessage.success("已提交");
        }else{
          ElMessage.error("提交失败 ！");
        }
      })
    }else{
      ElMessage.error("添加失败 ！");
    }
    emit("on-change");
  });
  dialogVisible.value = false;
}

/* 编辑件法律信息 */
function editCaseLawFun(){
  if(deletePerFun() == false) return;
  editCaseLaw(form).then(response => {
    if(response.code == 200){
      addPerFun();
    }else{
      ElMessage.error("提交失败 ！");
    }
    for(var i = 0; i < 9; i++){
      dynamicValidateForm.domains.splice(i, 1);
    }
    emit("on-change");
  });
  dialogVisible.value = false;
}

/* 新增律师业绩 */
function addPerFun(){
  addPerformancesplit(arrs).then(response => {
    if(response.code == 200){
      ElMessage.success("已提交");
    }else{
      ElMessage.error("提交失败 ！");
    }
  })
}

/* 删除律师业绩 */
function deletePerFun(){
  delPerformancesplit(id.value).then(response => {
    if(response.code == 200){
      return true;
    }else{
      return false;
    }
  })
}

// 修改回显
function getCaseLawInfo(id){
  getLaw(id).then(response => {
    form.collectionTime = response.data.collectionTime;
    form.caseAgencyfee = response.data.caseAgencyfee;
    form.caseCause = response.data.caseCause;
    form.standard = response.data.standard;
    form.caseCaseTypeId = response.data.caseCaseTypeId;
    form.caseWtr = response.data.caseWtr;
    form.caseOppositeParties = response.data.caseOppositeParties;
    form.caseRemarks = response.data.caseRemarks;
    form.id = response.data.id;
  });
  dynamicValidateForm.domains = [];
  getPerformancesplit(id).then(response => {
    var data = response.rows;
    for(var i = 0; i < data.length; i++){
      if(data[i].caseAttorney == "主办律师"){
        form.hrEmpId = data[i].hrEmpId;
        dynamicValidateForm.hrEmpId = data[i].hrEmpId;
        dynamicValidateForm.caseLawId = data[i].caseLawId;
        dynamicValidateForm.value = data[i].empName;
        dynamicValidateForm.cwPerformancesplit = data[i].cwPerformancesplit;
        dynamicValidateForm.cwSplitType = data[i].cwSplitType;
        dynamicValidateForm.type = 2;
        continue;
      }else{
        addDomain2(
          data[i].caseAttorney,
          data[i].empName,
          data[i].cwPerformancesplit,
          data[i].cwSplitType,
          data[i].hrEmpId,
          data[i].caseLawId,
          2
        );
      }
    }
  });
}

/* 处理律师业绩 */
function disposeCaseFun(){
  sum = 0;
  arrs = [];
  arrs.push(dynamicValidateForm);
  var d = dynamicValidateForm.domains;
  for(var i = 0; i < d.length; i++){
    arrs.push(d[i]);
  }
  if(dynamicValidateForm.cwPerformancesplit == '' || dynamicValidateForm.cwPerformancesplit == null){
    ElMessage.warning("请填写第 1 行业绩分成填写完整 ！");
    return false;
  }else if(dynamicValidateForm.cwSplitType == '' || dynamicValidateForm.cwSplitType == null){
    ElMessage.warning("请填写第 1 行业绩分成填写完整 ！");
    return false;
  }else{
    if(dynamicValidateForm.cwSplitType == '百分比'){
      sum = sum + parseFloat((form.caseAgencyfee*dynamicValidateForm.cwPerformancesplit/100));
      dynamicValidateForm.cwRealSplitMoney = (form.caseAgencyfee*dynamicValidateForm.cwPerformancesplit/100);
    }else if(dynamicValidateForm.cwSplitType == '固定金额'){
      sum = sum + parseFloat(dynamicValidateForm.cwPerformancesplit);
      dynamicValidateForm.cwRealSplitMoney = dynamicValidateForm.cwPerformancesplit;
    }
  }  
  for(var i = 0; i < d.length; i++){
    if(d[i].caseAttorney == '' || d[i].caseAttorney == null){
      ElMessage({
        message: '请填写第 ' + (i+2) + ' 行业绩分成填写完整 ！',
        type: 'warning',
      })
      return false;
    }else if(d[i].cwPerformancesplit == '' || d[i].cwPerformancesplit == null){
      ElMessage({
        message: '请填写第 ' + (i+2) + ' 行业绩分成填写完整 ！',
        type: 'warning',
      })
      return false;
    }else if(d[i].value == '' || d[i].value == null){
      ElMessage({
        message: '请填写第 ' + (i+2) + ' 行业绩分成填写完整 ！',
        type: 'warning',
      })
      return false;
    }else if(d[i].cwSplitType == '' || d[i].cwSplitType == null){
      ElMessage({
        message: '请填写第 ' + (i+2) + ' 行业绩分成填写完整 ！',
        type: 'warning',
      })
      return false;
    }else{
      if(d[i].cwSplitType == '百分比'){
        sum = sum + parseFloat((form.caseAgencyfee*d[i].cwPerformancesplit/100));
        d[i].cwRealSplitMoney = (form.caseAgencyfee*d[i].cwPerformancesplit/100);
        continue;
      }else if(d[i].cwSplitType == '固定金额'){
        sum = sum + parseFloat(d[i].cwPerformancesplit);
        d[i].cwRealSplitMoney = d[i].cwPerformancesplit;
        continue;
      }
    }
  }
  if(sum > parseFloat(form.caseAgencyfee)){
    ElMessage.warning("承办律师分成不可超出代理费 ！");
    return false;
  }
  console.log(sum);
  return true;
}

/* 处理办案律师数据方法 */
function dispose(){
  var st = '';
  if(dynamicValidateForm.domains.length > 0){
    var data = dynamicValidateForm.domains;
    var num = dynamicValidateForm.domains.length;
    for(var i = 0; i < num; i++){
      if(data[i].caseAttorney != '' && data[i].value != ''){
        if((i + 1) == num){
          st += data[i].caseAttorney + ":" + data[i].value;
        }else {
          st += data[i].caseAttorney + ":" + data[i].value + ",";
        }
      }
    }
  }
  var str = dynamicValidateForm.caseAttorney + ":" + dynamicValidateForm.value;
  if(st != ''){
    str += "," + st;
  }
  form.caseAttorney = str;
}

const getIndex = (idx) => {
  open.value = true;
  nums.value = idx;
}

/* 关闭弹出框方法 */
const clear = (nums) => {
  open.value = false;
  if(nums == -1){
    form.hrEmpId = currentRow.value.hrEmpId;
    dynamicValidateForm.hrEmpId = currentRow.value.hrEmpId;
    if(type.value == 'add'){
      dynamicValidateForm.caseLawId = form.id;
    }else{
      dynamicValidateForm.caseLawId = id.value;
    }
    dynamicValidateForm.value = currentRow.value.hrEmp.empName;
  }else{
    dynamicValidateForm.domains[nums].value = currentRow.value.hrEmp.empName;
    dynamicValidateForm.domains[nums].hrEmpId = currentRow.value.hrEmpId;
    dynamicValidateForm.domains[nums].caseLawId = id.value;
    if(type.value == 'add'){
      dynamicValidateForm.domains[nums].caseLawId = form.id;
    }else{
      dynamicValidateForm.domains[nums].caseLawId = id.value;
    }
  }
}

/* 办案律师操作 */
const dynamicValidateForm = reactive<{
  domains: DomainItem[]
}>({
  caseAttorney: '主办律师',
  value: '',
  cwPerformancesplit: '',
  cwSplitType: '',
  hrEmpId: '',
  caseLawId: '',
  cwRealSplitMoney: '',
  type: '',
  domains: []
})
interface DomainItem {
  key: number
  caseAttorney: string
  value: string
  cwPerformancesplit: string
  cwSplitType: string
  hrEmpId: number
  caseLawId: string
  cwRealSplitMoney: string
  type: string
}
const removeDomain = (item: DomainItem) => {
  const index = dynamicValidateForm.domains.indexOf(item)
  console.log(index + "-------" + item)
  if (index !== -1) {
    dynamicValidateForm.domains.splice(index, 1)
  }
}
const addDomain = () => {
  if (dynamicValidateForm.domains.length < 9){
    dynamicValidateForm.domains.push({
      key: Date.now(),
      caseAttorney: '',
      value: '',
      cwPerformancesplit: '',
      cwSplitType: '',
      hrEmpId: '',
      caseLawId: '',
      cwRealSplitMoney: '',
      type: 2
    });
  }else {
    ElMessage.warning('办案律师最多10个!');
  }
}
const addDomain2 = (caseAttorney1, value1, cwPerformancesplit1, cwSplitType1, hrEmpId1, caseLawId1, type1) => {
  if (dynamicValidateForm.domains.length < 9){
    dynamicValidateForm.domains.push({
      key: Date.now(),
      caseAttorney: caseAttorney1,
      value: value1,
      cwPerformancesplit: cwPerformancesplit1,
      cwSplitType: cwSplitType1,
      hrEmpId: hrEmpId1,
      caseLawId: caseLawId1,
      cwRealSplitMoney: '',
      type: type1
    });
  }else {
    ElMessage.warning('办案律师最多10个!');
  }
}

/* 获取表格单行数据 */
const handleCurrentChange = (val) => {
  currentRow.value = val;
}

/* 查询办案律师 加载办案律师 */
function getList(){
  selectSysUserByHrEmp(pageParams).then(response => {
    pageParams.total = response.rows.length;
    tableData.value = response.rows;
    form.hrEmpId = tableData.value[0].hrEmpId;
    dynamicValidateForm.hrEmpId = tableData.value[0].hrEmpId;
    dynamicValidateForm.value = tableData.value[0].hrEmp.empName;
    dynamicValidateForm.type = 2;
  });
}

/* 查询提交人(律师信息) */
function getEmpInfo(){
  userStore.getInfo().then((response) => {
      getEmp(response.user.hrEmpId).then(res => {
        form.caseSubmitter = res.data.empName;
      })
   })
}

/* 获取案件信息表id */
function getCaseLawIdFun(){
  getCaseLawId().then(response => {
    if(response.rows[0] == null || response.rows[0] == ''){
      form.id = 1;
    }else{
      form.id = response.rows[0].id + 1;
    }
    var now = new Date();
    var year = now.getFullYear(); //得到年份
    form.caseNo = year + "(文)第" + form.id + "号";
    dynamicValidateForm.caseLawId = form.id
  })
}

/* 展开按钮方法 */
function btnShow(){
  if(show1.value == true){
      show1.value = false;
      btnText.value = '展开';
  }else{
      show1.value = true;
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

/* 重置查询表单 */
function resetForm(){
  form.id = '';
  form.collectionTime = '';
  form.caseAgencyfee = '';
  form.caseCause = '';
  form.standard = '';
  form.caseCaseTypeId = '';
  form.caseWtr = '';
  form.caseOppositeParties = '';
  form.caseRemarks = '';
  dynamicValidateForm.cwPerformancesplit = '';
  dynamicValidateForm.cwSplitType = '';
  dynamicValidateForm.domains = [];
  getList();
}

/* 数据校验 */
function rulesCli(){
  if(form.collectionTime == undefined || form.collectionTime === ''){
      ElMessage.warning("服务时间未填写 ！");
      return false;
  }
  if(form.caseAgencyfee == undefined || form.caseAgencyfee === ''){
      ElMessage.warning("服务费用未填写 ！");
      return false;
  }
  if(form.caseCause == undefined || form.caseCause === ''){
      ElMessage.warning("咨询/代书事项未填写 ！");
      return false;
  }
  if(form.standard == undefined || form.standard === ''){
      ElMessage.warning("提供服务人次未填写 ！");
      return false;
  }
  if(form.caseCaseTypeId == undefined || form.caseCaseTypeId === ''){
      ElMessage.warning("服务类型未填写 ！");
      return false;
  }
  form.caseNotinvoiced = form.caseAgencyfee;
  form.caseUnpaidsal = form.caseAgencyfee;
  return true;
}
</script>



