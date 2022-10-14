<template>
   <div>
      <el-descriptions :icon="Edit" title="基本资料" :size="large" style="boder: 1px solid red;">
          <!-- <el-descriptions-item label="Username">kooriookami</el-descriptions-item> -->
      </el-descriptions>
      <el-divider />
      <!-- ref="ruleFormRef"
          :rules="rules" -->
      <el-form  ref="ruleFormRef" :inline="true" :label-position="labelPosition" class="demo-form-inline" style="margin-left: 30px;"
          :model="formClientInline"  
          >
          <el-form-item label=" * 案号:">
              <el-input v-model="caseNoInfo.year" disabled style="width: 100px; text-align: center;"/>
              <span style="display: inline-black; width: 58px;  margin: 0px 0px 0px 8px;">( {{caseNoInfo.type}} )&nbsp;第</span>
              <el-input v-model="caseNoInfo.id" disabled style="width: 100px; text-align: center;"/>&nbsp;&nbsp;号
              <!-- <el-input v-model="formClientInline.caseNo" clearable  style="width: 500px;"/> -->
              <span style="display: inline-black; width: 397px;"></span>
          </el-form-item>
          <el-form-item label=" * 案由:">
              <el-input v-model="formClientInline.caseCause" clearable  style="width: 500px;"/>
          </el-form-item>
          <el-form-item label=" * 委托人:">
              <el-input v-model="formClientInline.caseWtr" disabled  style="width: 500px;"/>
              <span style="display: inline-black; width: 185px;"></span>
          </el-form-item>
          <el-form-item label=" * 对方当事人:">
              <el-input v-model="formClientInline.caseOppositeParties" clearable  style="width: 500px;"/>
          </el-form-item>
          <el-form-item label="当事人(多个当事人用,分隔):">
              <el-input v-model="formClientInline.caseParties" clearable  style="width: 500px;"/>
              <span style="display: inline-black; width: 185px;"></span>
          </el-form-item>
          <el-form-item label=" * 对方当事人证件号:">
              <el-input v-model="formClientInline.caseOppositeCardnum" disabled style="width: 500px;"/>
          </el-form-item>
          <el-form-item label=" * 收案日期:" >
            <el-date-picker
              v-model="formClientInline.collectionTime"
              type="date"
              placeholder=""
              value-format="YYYY-MM-DD"
              style="width: 500px;"
              clearable
            />
              <span style="display: inline-black; width: 185px;"></span>
          </el-form-item>
          <el-form-item label=" * 受理机关:">
              <el-select v-model="formClientInline.caseAccept" clearable  style="width: 500px;">
                <el-option label="公安机关" value="公安机关" />
                <el-option label="检查机关" value="检查机关" />
                <el-option label="审查机关" value="审查机关" />
              </el-select>
          </el-form-item>
          <el-form-item label=" * 诉讼标的:">
              <el-input v-model="formClientInline.caseLawsuitobj" disabled style="width: 500px;"/>
              <span style="display: inline-black; width: 185px;"></span>
          </el-form-item>
          <el-form-item label=" * 代理费:">
              <el-input v-model="formClientInline.caseAgencyfee" clearable oninput="value=value.replace(/[^\d]/g,'')" style="width: 500px;"/>
          </el-form-item>
          <el-form-item label=" * 收费方式:">
            <el-select v-model="formClientInline.caseChargeWay" clearable  style="width: 500px;">
                <el-option label="免费" value="免费" />
                <el-option label="计件收费" value="计件收费" />
                <el-option label="计时收费" value="计时收费" />
                <el-option label="按标的额比例收费" value="按标的额比例收费" />
                <el-option label="风险代理收费" value="风险代理收费" />
                <el-option label="标的+风险代理" value="标的+风险代理" />
                <el-option label="固定+风险代理" value="固定+风险代理" />
              </el-select>
              <span style="display: inline-black; width: 185px;"></span>
          </el-form-item>
          <el-form-item label=" * 风险收费:">
              <el-input v-model="formClientInline.caseFxmoney" oninput="value=value.replace(/[^\d]/g,'')" clearable  style="width: 500px;"/>
          </el-form-item>
          <el-form-item label=" * 是否享受政府补助:">
            <el-select v-model="formClientInline.caseSubsidy" clearable  style="width: 500px;">
                <el-option label="是" value="Y" />
                <el-option label="否" value="N" />
              </el-select>
              <span style="display: inline-black; width: 185px;"></span>
          </el-form-item>
          <el-form-item label=" * 补助金额:">
              <el-input v-model="formClientInline.caseSubsidysal" oninput="value=value.replace(/[^\d]/g,'')" clearable  style="width: 500px;"/>
          </el-form-item>
          <el-form-item label=" * 诉讼阶段:">
              <el-checkbox-group v-model="typeinfoCheck">
                  <el-checkbox label="立案" name="立案" />
                  <el-checkbox label="一审" name="一审" />
                  <el-checkbox label="二审" name="二审" />
                  <el-checkbox label="执行" name="执行" />
                  <el-checkbox label="再审" name="再审" />
              </el-checkbox-group>
              <span style="display: inline-black; width: 1000px;"></span>
          </el-form-item>
          <el-form-item label=" * 诉讼地位:">
              <el-radio-group v-model="statusInfo">
                <el-radio :label="1">原告</el-radio>
                <el-radio :label="2">被告</el-radio>
                <el-radio :label="3">上诉人</el-radio>
                <el-radio :label="4">被上诉人</el-radio>
                <el-radio :label="5">申请执行人</el-radio>
                <el-radio :label="6">被申请执行人</el-radio>
                <el-radio :label="7">申请人</el-radio>
                <el-radio :label="8">被申请人</el-radio>
                <el-radio :label="9">第三人</el-radio>
                <el-radio :label="10">其他</el-radio>
              </el-radio-group>
          </el-form-item>
          
      </el-form>
      <br>
      <div>
          <span><el-icon><Edit/></el-icon></span>
          <font style="color: #494949;font-size: 16px;font-weight: 700;"> 其他选项</font> &nbsp;&nbsp;
          <el-button size="small" plain @click="btnShow">{{ btnText }}</el-button>
      </div>
      <el-divider />
      <div v-show="show1" style="margin-left: 30px;">
          <el-form :model="formClientInline" :inline="true" :label-position="labelPosition" class="demo-form-inline">
              <el-form-item label="专属案号:">
                  <el-input v-model="formClientInline.casePropernum" clearable  style="width: 500px;"/><span style="display: inline-black; width: 185px;"></span>
              </el-form-item>
              <el-form-item label="杂费:">
                  <el-input v-model="formClientInline.caseProxysal" oninput="value=value.replace(/[^\d]/g,'')"  clearable  style="width: 500px;"/>
              </el-form-item>
              <el-form-item label="办案地区:" prop="cardnum">
                  <input style="display: none" v-model="formClientInline.caseTransactionRegion">
                  <elui-china-area-dht :leave="2" @change="onChange" style="width: 500px;"></elui-china-area-dht><span style="display: inline-black; width: 185px;"></span>
              </el-form-item>
              <el-form-item label="是否指派:">
                <el-select v-model="formClientInline.caseAssign" clearable  style="width: 500px;">
                  <el-option label="是" value="Y" />
                  <el-option label="否" value="N" />
                </el-select>
              </el-form-item>
              <el-form-item label="第三人:">
                  <el-input v-model="formClientInline.caseThirdParty" clearable  style="width: 500px;"/><span style="display: inline-black; width: 185px;"></span>
              </el-form-item>
              <el-form-item label="相关文件:" prop="caseCorrelationFile">
                  <el-button type="primary" plain>选择文件</el-button><span>未选择任何文件</span>
                  <span style="display: inline-black; width: 185px;"></span>
              </el-form-item>
              <el-form-item label="备注:">
                  <el-input
                      v-model="formClientInline.caseRemarks"
                      :autosize="{ minRows: 2.5, maxRows: 4 }"
                      type="textarea"
                      placeholder=""
                      style="width: 1217px;"
                  />
              </el-form-item>
          </el-form>
        </div>

        <br>
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
          <el-table 
            :data="tableData"
            ref="singleTableRef"
            highlight-current-row
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
        <br>

        <el-button type="primary" @click="onSubmit()">确认添加</el-button>
   </div>
   
</template>
<script lang="ts" setup>
import { getCaseLawId, addCaseLaw } from "@/api/cases/CaseLaw.js";
import { getCaseTypeMin } from "@/api/cases/CaseCaseType.js";
import { getClientByCardnum } from "@/api/cases/Client.js";
import { getEmp } from "@/api/cases/HrEmp.js";
import { selectSysUserByHrEmp } from "@/api/cases/SysUser.js";
import { addPerformancesplit } from "@/api/cases/performancesplit.js";

import { Edit } from '@element-plus/icons-vue';
import { ElMessage, ElMessageBox } from 'element-plus';
import { EluiChinaAreaDht } from 'elui-china-area-dht';
import type { FormInstance } from 'element-plus';
import { ElTable } from 'element-plus'; 
import useUserStore from '@/store/modules/user';

const labelPosition = ref('top'); // form表单样式
const btnText = ref('展开');
const caseNoInfo = reactive({ year: 0, type: '', id: 0 });
const show1 = ref(false); // 展开选项
const typeinfoCheck = ref([]); // 诉讼阶段
const statusInfo = ref(undefined); // 诉讼地位
// const typeList = ref(); // 客户类型列表
const chinaData = new EluiChinaAreaDht.ChinaArea().chinaAreaflat; // 下拉框 省级数据
const formClientInline = reactive({}); // 新增委托人form
const open = ref(false); // 弹出框参数
// 分页参数
const pageParams = reactive({
  pageSize: 10,
  currentPage: 1,
  total: 0
});
const tableData = ref([]); // 表格数据
const formRef = ref<FormInstance>();
const currentRow = ref();
const singleTableRef = ref<InstanceType<typeof ElTable>>();
const nums = ref(undefined); 
const userStore = useUserStore();
const params = reactive({}); // 查询律师参数
const emit = defineEmits<{(e:'toFather', msg:string, msg:Object):void}>();
var sum = 0;
var arrs = [];

const onSubmit = () => {
  if(rulesCli() == false) return;
  if(getCaseLawsuitStatus() == false) return;
  dataPg();
  if(disposeCaseFun() == false) return;
  addLaw();
}

/* 数据处理方法 */
function dataPg(){ 
  formClientInline.caseNo = caseNoInfo.year +  "(" + caseNoInfo.type + ")" + "第" + caseNoInfo.id + "号";
  var typeinfo = '';   
  for(var i = 0; i < typeinfoCheck.value.length; i++){
      if(typeinfoCheck.value[i] != '' && typeinfoCheck.value[i] != null){
         typeinfo = typeinfo + typeinfoCheck.value[i] + ",";
      }
  }
  formClientInline.caseStatus = typeinfo;
  dispose();
}

/* 增案件法律信息 */
function addLaw(){
  addCaseLaw(formClientInline).then(response => {
    if(response.code == 200){
      addPerformancesplit(arrs).then(response => {
        if(response.code == 200){
          ElMessage.success("已提交");
          emit("toFather",caseNoInfo.id, formClientInline);
        }else{
          ElMessage.error("提交失败 ！");
        }
      })
    }else{
      ElMessage.error("添加失败 ！");
    }
  });
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
  }else{
    if(dynamicValidateForm.cwSplitType == '百分比'){
      sum = sum + parseFloat((formClientInline.caseAgencyfee*dynamicValidateForm.cwPerformancesplit/100));
      dynamicValidateForm.cwRealSplitMoney = (formClientInline.caseAgencyfee*dynamicValidateForm.cwPerformancesplit/100);
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
        sum = sum + parseFloat((formClientInline.caseAgencyfee*d[i].cwPerformancesplit/100));
        d[i].cwRealSplitMoney = (formClientInline.caseAgencyfee*d[i].cwPerformancesplit/100);
        continue;
      }else if(d[i].cwSplitType == '固定金额'){
        sum = sum + parseFloat(d[i].cwPerformancesplit);
        d[i].cwRealSplitMoney = d[i].cwPerformancesplit;
        continue;
      }
    }
  }
  if(sum > parseFloat(formClientInline.caseAgencyfee)){
    ElMessage.warning("承办律师分成不可超出代理费 ！");
    return false;
  }
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
  formClientInline.caseAttorney = str;
}

const getIndex = (idx) => {
  open.value = true;
  nums.value = idx;
}

/* 关闭弹出框方法 */
const clear = (nums) => {
  open.value = false;
  if(nums == -1){
    formClientInline.hrEmpId = currentRow.value.hrEmpId;
    dynamicValidateForm.hrEmpId = currentRow.value.hrEmpId;
    dynamicValidateForm.value = currentRow.value.hrEmp.empName;
  }else{
    dynamicValidateForm.domains[nums].value = currentRow.value.hrEmp.empName;
    dynamicValidateForm.domains[nums].hrEmpId = currentRow.value.hrEmpId;
    dynamicValidateForm.domains[nums].caseLawId = caseNoInfo.id;
  }
}

/* 获取组件参数 */
export interface ExposeType {
    getType:Function,
    getOcardnum:Function,
} 
/* 获取案件类型、案件类型id、诉讼标的 */
const getType = (val:string, val2:number) => {
  var strs = val.split("");
  caseNoInfo.type = strs[0];
  formClientInline.caseCaseTypeId = val2;
  getCaseTypeMin(val2).then(response => {
    formClientInline.caseLawsuitobj = response.data.typeMoneyMin;
  });
}
/* 获取客户信息 */
const getClient = (val:string,val2:string) => {
  params.cardnum = val;
  getOcardnum(val2);
  getCaseLawIdFun();
  getList();
  getClientByCardnum(params).then(response => {
    formClientInline.caseWtr = response.rows[0].client;
    formClientInline.clientId = response.rows[0].id;
  });
  getUserInfo();
}
defineExpose({
   getType,getClient
})

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
      caseLawId: caseNoInfo.id,
      cwRealSplitMoney: '',
      type: '1'
    });
  }else {
    ElMessage.warning('办案律师最多10个!');
  }
}
/* 获取表格单行数据 */
const handleCurrentChange = (val) => {
  currentRow.value = val;
}

/* 查询登录用户信息 */
function getUserInfo(){
  userStore.getInfo().then((response) => {
    formClientInline.hrEmpId = response.user.hrEmpId;
    getEmpInfo(response.user.hrEmpId);
  });
}
/* 查询提交人(律师信息) */
function getEmpInfo(hrEmpId){
  getEmp(hrEmpId).then(response => {
    formClientInline.caseSubmitter = response.data.empName;
  });
}

/* 获取对方当事人证件号、设置案号年份 */
const getOcardnum = (val) => {
  var myDate = new Date();
  caseNoInfo.year = myDate.getFullYear();
  formClientInline.caseOppositeCardnum = val;
}

/* 查询办案律师 加载首个办案律师 */
function getList(){
  selectSysUserByHrEmp(pageParams).then(response => {
    pageParams.total = response.rows.length;
    tableData.value = response.rows;
    formClientInline.hrEmpId = tableData.value[0].hrEmpId;
    dynamicValidateForm.hrEmpId = tableData.value[0].hrEmpId;
    dynamicValidateForm.value = tableData.value[0].hrEmp.empName;
    dynamicValidateForm.type = 1;
  });
}

/* 获取案件信息表id */
function getCaseLawIdFun(){
  getCaseLawId().then(response => {
    console.log(response);
    if(response.rows[0] == null || response.rows[0] == ''){
      caseNoInfo.id = 1;
    }else{
      caseNoInfo.id = response.rows[0].id + 1;
    }
    formClientInline.id = caseNoInfo.id;
    dynamicValidateForm.caseLawId = caseNoInfo.id;
  })
}

/* 获取诉讼地位 */
function getCaseLawsuitStatus(){
  if(statusInfo.value == "1") {
    formClientInline.caseLawsuitStatus = "原告";
    return true;
  }
  if(statusInfo.value == "2") {
    formClientInline.caseLawsuitStatus = "被告";
    return true;
  }
  if(statusInfo.value == "3") {
    formClientInline.caseLawsuitStatus = "上诉人";
    return true;
  }
  if(statusInfo.value == "4") {
    formClientInline.caseLawsuitStatus = "被上诉人";
    return true;
  }
  if(statusInfo.value == "5") {
    formClientInline.caseLawsuitStatus = "申请执行人";
    return true;
  }
  if(statusInfo.value == "6") {
    formClientInline.caseLawsuitStatus = "被申请执行人";
    return true;
  }
  if(statusInfo.value == "7") {
    formClientInline.caseLawsuitStatus = "申请人";
    return true;
  }
  if(statusInfo.value == "8") {
    formClientInline.caseLawsuitStatus = "被申请人";
    return true;
  }
  if(statusInfo.value == "9") {
    formClientInline.caseLawsuitStatus = "第三人";
    return true;
  }
  if(statusInfo.value == "10") {
    formClientInline.caseLawsuitStatus = "其他";
    return true;
  }
  return false;
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

/* 获取 省级 地区数据 */
function onChange(e) {
  const one = chinaData[e[0]];
  const two = chinaData[e[1]];
  formClientInline.caseTransactionRegion = one.label + "/" + two.label;
}

/* 数据校验 */
function rulesCli(){
  if(formClientInline.caseCause == undefined || formClientInline.caseCause === ''){
      ElMessage.warning("案由未填写 ！");
      return false;
  }
  if(formClientInline.caseOppositeParties == undefined || formClientInline.caseOppositeParties == ''){
      ElMessage.warning("对方当事人未填写 ！");
      return false;
  }
  if(formClientInline.collectionTime == undefined || formClientInline.collectionTime == ''){
      ElMessage.warning("收案日期未填写 ！");
      return false;
  }
  if(formClientInline.caseAccept == undefined || formClientInline.caseAccept == ''){
      ElMessage.warning("受理机关未填写 ！");
      return false;
  }
  if(formClientInline.caseAgencyfee == undefined || formClientInline.caseAgencyfee == ''){
      ElMessage.warning("代理费未填写 ！");
      return false;
  }
  if(formClientInline.caseChargeWay == undefined || formClientInline.caseChargeWay == ''){
      ElMessage.warning("收费方式未填写 ！");
      return false;
  }
  if(formClientInline.caseFxmoney == undefined || formClientInline.caseFxmoney == ''){
      ElMessage.warning("风险收费未填写 ！");
      return false;
  }
  if(formClientInline.caseSubsidy == undefined || formClientInline.caseSubsidy == ''){
      ElMessage.warning("是否享受政府补助未填写 ！");
      return false;
  }
  if(formClientInline.caseSubsidysal == undefined || formClientInline.caseSubsidysal == ''){
      ElMessage.warning("补助金额未填写 ！");
      return false;
  }
  if (typeinfoCheck.value.length == 0){
    ElMessage.warning("诉讼阶段未填写 ！");
    return false;
  }
  if (statusInfo.value == undefined || statusInfo.value == ''){
    ElMessage.warning("诉讼地位未填写 ！");
    return false;
  }
  return true;
}
</script>



