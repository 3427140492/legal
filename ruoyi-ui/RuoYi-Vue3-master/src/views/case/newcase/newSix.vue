<template>
   <div class="app-container">

      <div v-show="shows">
        <div style="margin: 32px 30px 45px 380px; font-size: 19px;">案号：{{form.caseNo}}</div>
        <div @click="toMatic" style="display: inline-block; width: 310px; height: 105px; background-color: #409eff; color: #FFFFFF; 
            border-radius: 8px; margin: 0px 30px 0px 380px">
          <div style="padding: 40px 0px 0px 106px;">自动生成模式</div>
        </div>
        <div @click="toAuto" style="display: inline-block; width: 310px; height: 105px; background-color: #409eff; color: #FFFFFF; 
          border-radius: 6px;">
          <div style="padding: 40px 0px 0px 106px;">手动上传模式</div>
        </div><br>
        <div style="margin: 100px 30px 0px 380px;">
          <el-button @click="next" type="primary" size="large">暂不申请文书，下一步</el-button>
        </div>
      </div>

      <div v-show="toMaticShow">
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
              <el-input v-model="form.caseNo" readonly  style="width: 500px;"/>
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
        <el-button  @click="goBack">返回</el-button>
      </div>

      <div v-show="toAutoShow">
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
            </el-table><br><br>
          <el-button type="primary" @click="onSubmitAuto()">确认提交</el-button>
          <el-button  @click="goBack">返回</el-button>
      </div>

   </div>
</template>
<script lang="ts" setup>
import useUserStore from '@/store/modules/user';
import { getEmp } from "@/api/cases/HrEmp.js";
import { addApplyfor, getProduction } from "@/api/cases/CaseSealApplyfor.js";
import { ElMessage, ElMessageBox } from 'element-plus';

const emit = defineEmits<{(e:'toFather'):void}>();
const userStore = useUserStore();
const labelPosition = ref('top'); // form表单样式
const form = reactive({});
const form2 = reactive({});
const shows = ref(true);
const toMaticShow = ref(false);
const toAutoShow = ref(false);
const ApplyList = ref([]); 
const multipleSelection = ref();

defineExpose({
  getInfo
})

function getInfo(obj){
  form.caseNo = obj.caseNo;
  form.sendPerson = obj.caseWtr;
  form.applyforApprovalStatus = 1;
  form.caseid = obj.id;

  form2.id = obj.id;
  form2.caseNo = obj.caseNo;
  form2.sendPerson = obj.caseWtr;
  form2.applyforApprovalStatus = 1;
  form2.caseid = obj.id;
  form2.applyforReceive = getDate();
  form2.typeid = 5;

  userStore.getInfo().then((response) => {
      getEmp(response.user.hrEmpId).then(res => {
        if(res.code == 200){
            form.applyforRecipient = res.data.empName;
            form2.applyforRecipient = res.data.empName;
        }
      })
   })
}

/* 显示手动生产 */
function toMatic(){
  shows.value = false;
  toAutoShow.value = false;
  toMaticShow.value = true;
}

/* 显示自动生成 */
function toAuto(){
    shows.value = false;
    toMaticShow.value = false;
    toAutoShow.value = true;
    getProduction(form2.id).then(response => {
        ApplyList.value = response.rows;
    })
}

/* 提交手动生成 */
const onSubmit = () => {
    if(rulesCli() == false) return;
    addApplyfor(form).then(resp => {
        if(resp.code == 200){
            ElMessage.success("已提交");
        }else{
            ElMessage.error("提交失败!");
        }
        emit('toFather');
    })
}

/* 提交自动生成 */
function onSubmitAuto(){
    var bool = true;
    for (let i = 0; i < multipleSelection.value.length; i++) {
        if (multipleSelection.value[i].filename == '空') {
            form2.applyforUsingTheItem = multipleSelection.value[i].file_name;
            addApplyfor(form2).then(resp => {
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
        emit('toFather');
    }else{
        ElMessage.error("提交失败!");
        emit('toFather');
    }
}

const handleSelectionChange = (val) => {
    multipleSelection.value = val;
}

function isRender(row) {
    if (row.row.filename != '空') {
        return 'disabled-column';
    }
}

/* 返回 */
function goBack(){
  shows.value = true;
  toMaticShow.value = false;
  toAutoShow.value = false;
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






