<template>
   <div>
      <el-descriptions :icon="Edit" title="基本资料" :size="large" style="boder: 1px solid red;">
          <!-- <el-descriptions-item label="Username">kooriookami</el-descriptions-item> -->
      </el-descriptions>
      <el-divider />
      <!-- ref="ruleFormRef"
          :rules="rules" -->
      <el-form :inline="true" :label-position="labelPosition" class="demo-form-inline" style="margin-left: 30px;" :model="formClientInline" >
        <el-form-item label=" * 客户:">
            <el-input v-model="formClientInline.client" clearable  style="width: 500px;"/><span style="display: inline-black; width: 185px;"></span>
        </el-form-item>
        <el-form-item label=" * 手机号:">
            <el-input v-model="formClientInline.phone" oninput="value=value.replace(/[^\d]/g,'')" clearable  style="width: 500px;"/>
        </el-form-item>
        <el-form-item label=" * 客户类型:">
            <el-select v-model="formClientInline.type" @change="getType" clearable style="width: 500px;">
                <el-option v-for="t in typeList" :key="t.id" :label="t.clientType" :value="t.id"/>
            </el-select>
            <span style="display: inline-black; width: 185px;"></span>
        </el-form-item>
        <el-form-item label=" * 身份证号:">
            <el-input v-model="formClientInline.cardnum" disabled style="width: 500px;"/>
        </el-form-item>
        <el-form-item label=" * 身份特征:" v-show="option_show1">
            <el-checkbox-group v-model="typeinfoCheck">
                <el-checkbox label="妇女" name="妇女" />
                <el-checkbox label="未成年人" name="未成年人" />
                <el-checkbox label="残疾人" name="残疾人" />
                <el-checkbox label="老年人" name="老年人" />
                <el-checkbox label="农民" name="农民" />
                <el-checkbox label="农民工" name="农民工" />
                <el-checkbox label="下岗职工" name="下岗职工" />
                <el-checkbox label="城市居民" name="城市居民" />
                <el-checkbox label="外来人员" name="外来人员" />
            </el-checkbox-group>
        </el-form-item>
        <el-form-item label=" * 类型:" v-show="option_show2">
          <el-select v-model="formClientInline.typeinfo" clearable  style="width: 1218px;">
            <el-option-group v-for="group in jgdwOptions" :key="group.label" :label="group.label">
              <el-option v-for="item in group.options" :key="item.value" :label="item.label" :value="item.value"/>
            </el-option-group>
          </el-select>
        </el-form-item>
        <el-form-item label=" * 类型:" v-show="option_show3">
          <el-select v-model="typeinfOp" v-show="option_show3" clearable  style="width: 1218px;">
            <el-option label="国有" value="国有"/>
            <el-option label="民营" value="民营"/>
            <el-option label="外贸" value="外贸"/>
          </el-select>
        </el-form-item>
        <el-form-item label=" * 单位类型:" v-show="option_show3">
          <el-radio-group v-model="typeinfoRad">
            <el-radio :label="1">世界500强</el-radio>
            <el-radio :label="2">国家500强</el-radio>
            <el-radio :label="3">上市公司</el-radio>
            <el-radio :label="4">规上企业</el-radio>
            <el-radio :label="5">小微企业</el-radio>
          </el-radio-group>
          <span style="display: inline-black; width: 185px;"></span>
        </el-form-item>
        <el-form-item label=" * 类型:" v-show="option_show4">
          <el-select v-model="formClientInline.typeinfo" v-show="option_show4" clearable  style="width: 1218px;">
            <el-option label="商会" value="商会"/>
            <el-option label="行业协会" value="行业协会"/>
            <el-option label="其它社团组织" value="其它社团组织"/>
          </el-select>
        </el-form-item>
      </el-form><br>
      
      <div>
        <span><el-icon><Edit/></el-icon></span>
        <font style="color: #494949;font-size: 16px;font-weight: 700;"> 其他选项</font> &nbsp;&nbsp;
        <el-button size="small" plain @click="btnShow">{{ btnText }}</el-button>
      </div>
      <el-divider />

      <div v-show="show3_in" style="margin-left: 30px;">
        <el-form :model="formClientInline" :inline="true" :label-position="labelPosition" class="demo-form-inline">
          <el-form-item label="主要负责人:">
              <el-input v-model="formClientInline.principal" clearable  style="width: 500px;"/><span style="display: inline-black; width: 185px;"></span>
          </el-form-item>
          <el-form-item label="业务联系人:">
              <el-input v-model="formClientInline.contact" clearable  style="width: 500px;"/>
          </el-form-item>
          <el-form-item label="邮箱:">
              <el-input v-model="formClientInline.email" clearable  style="width: 500px;"/><span style="display: inline-black; width: 185px;"></span>
          </el-form-item>
          <el-form-item label="职务:">
              <el-input v-model="formClientInline.duty" clearable  style="width: 500px;"/>
          </el-form-item>
          <el-form-item label="地区影响力:">
              <el-input v-model="formClientInline.affectedareas" clearable  style="width: 500px;"/><span style="display: inline-black; width: 185px;"></span>
          </el-form-item>
          <el-form-item label="固定电话:">
              <el-input v-model="formClientInline.tel" oninput="value=value.replace(/[^\d]/g,'')" clearable  style="width: 500px;"/>
          </el-form-item>
          <el-form-item label="所属地区:" prop="cardnum">
              <input style="display: none" v-model="formClientInline.pertainarea">
              <elui-china-area-dht :leave="2" @change="onChange" style="width: 500px;"></elui-china-area-dht><span style="display: inline-black; width: 185px;"></span>
          </el-form-item>
          <!-- <el-form-item label="地区:" prop="cardnum">
              <elui-china-area-dht isall :leave="2"  style="width: 500px;"></elui-china-area-dht><span style="display: inline-black; width: 185px;"></span>
          </el-form-item> -->
          <el-form-item label="客户状态:">
            <el-select v-model="formClientInline.status" clearable  style="width: 500px;">
            <el-option label="潜在客户" value="1" />
            <el-option label="目标客户" value="2" />
            <el-option label="正式客户" value="3" />
            <el-option label="有效客户" value="4" />
            <el-option label="无效客户" value="5" />
            <el-option label="重点跟进" value="6" />
          </el-select>
          </el-form-item>
          <el-form-item label="法定代表人:">
              <el-input v-model="formClientInline.legalperson" clearable  style="width: 500px;"/><span style="display: inline-black; width: 185px;"></span>
          </el-form-item>
          <el-form-item label="法人联系方式:">
              <el-input v-model="formClientInline.legalpersontel" oninput="value=value.replace(/[^\d]/g,'')" clearable  style="width: 500px;"/>
          </el-form-item>
          <el-form-item label="相关文件:" prop="caseOppositeCardnum">
              <el-button type="primary" plain>选择文件</el-button><span>未选择任何文件</span><span style="display: inline-black; width: 1000px;"></span>
          </el-form-item>
          <el-form-item label="民族:">
              <el-select v-model="formClientInline.nation" style="width: 500px;" placeholder="请选择民族">
                  <el-option v-for="item in nationList" :key="item.value" :label="item.info" :value="item.info" clearable></el-option>
              </el-select>
              <span style="display: inline-black; width: 185px;"></span>
          </el-form-item>
          <el-form-item label="性别:">
              <el-select v-model="formClientInline.sex" style="width: 500px;" placeholder="请选择" clearable>
                  <el-option label="男" value="男" />
                  <el-option label="女" value="女" />
              </el-select>
          </el-form-item>
          <el-form-item label="出生日期:">
              <el-date-picker
                  v-model="formClientInline.birth"
                  type="date"
                  value-format="YYYY-MM-DD"
                  placeholder="Pick a day" style="width: 500px;" 
              />
              <span style="display: inline-black; width: 185px;"></span>
          </el-form-item>
          <el-form-item label="详细地址:">
              <el-input v-model="formClientInline.address" clearable  style="width: 500px;"/>
          </el-form-item>
          <el-form-item label="备注:">
              <el-input v-model="formClientInline.remark" :autosize="{ minRows: 2, maxRows: 4 }" type="textarea" placeholder="" style="width: 1217px;"/>
          </el-form-item>
        </el-form>
      </div>
      <br>
      <el-button type="primary" @click="onSubmit()">确认添加</el-button>
   </div>
</template>
<script lang="ts" setup>
import { getClientByCardnum, addClient, editClient } from "@/api/cases/Client.js";
import { selectClientType } from "@/api/cases/ClientType.js";
import { Edit } from '@element-plus/icons-vue';
import { ElMessage, ElMessageBox } from 'element-plus';
import { EluiChinaAreaDht } from 'elui-china-area-dht';
import useUserStore from '@/store/modules/user';

const labelPosition = ref('top'); // form表单样式
const btnText = ref('展开');
const show3_in = ref(false); // 展开选项
const typeinfoCheck = ref([]); // 身份特征
const typeList = ref(); // 客户类型列表
// 客户类型选项显示参数
const option_show1 = ref(true);
const option_show2 = ref(false);
const option_show3 = ref(false);
const option_show4 = ref(false);
const typeinfOp = ref('');
const typeinfoRad = ref('');
const chinaData = new EluiChinaAreaDht.ChinaArea().chinaAreaflat; // 下拉框 省级数据
const formClientInline = reactive({}); // 新增委托人form
const addretrun = ref(true); // 数据校验判断
const params = reactive({}); // 查询客户参数
const subType = ref(true); // 提交类型 (true=添加/false=修改)
const emit = defineEmits<{(e:'toFather', msg:string):void}>();

const onSubmit = () => {
  if(dataPg() == false) return;
  if(subType.value == true){
    addFun();
  }else{
    editFun();
  }
}

/* 添加客户方法 */
function addFun(){
  addClient(formClientInline).then(response => {
    if(response.code == 200){
      emit('toFather',formClientInline.cardnum);
      ElMessage.success('添加客户信息成功');
    }else{
      ElMessage.error('添加客户信息失败!');
    }
  });
}

/* 修改客户方法 */
function editFun(){
  editClient(formClientInline).then(response => {
    if(response.code == 200){
      emit('toFather',formClientInline.cardnum);
      ElMessage.success('修改客户信息成功');
    }else{
      ElMessage.error('修改客户信息失败!');
    }
  });
}

/* 数据处理方法 */
function dataPg(){ 
  addretrun.value = true;
  rulesCli();
  if(addretrun.value == false) return false;
  if(formClientInline.type == 1){
    if (typeinfoCheck.value.length > 0) {
      var typeinfo = '';
      for(var i = 0; i < typeinfoCheck.value.length; i++){
          typeinfo = typeinfo + typeinfoCheck.value[i] + ",";
      }
      formClientInline.typeinfo = typeinfo; 
    }else{
      return false;
    }
  }
  if(formClientInline.type == 3){
    if(typeinfOp.value == '' || typeinfoRad.value == '') return false;
    if(typeinfoRad.value == '1'){
      formClientInline.typeinfo = typeinfOp.value + "," + "世界500强";
    }
    if(typeinfoRad.value == '2'){
      formClientInline.typeinfo = typeinfOp.value + "," + "国家500强";
    }
    if(typeinfoRad.value == '3'){
      formClientInline.typeinfo = typeinfOp.value + "," + "世界500强";
    }
    if(typeinfoRad.value == '4'){
      formClientInline.typeinfo = typeinfOp.value + "," + "规上企业";
    }
    if(typeinfoRad.value == '5'){
      formClientInline.typeinfo = typeinfOp.value + "," + "小微企业";
    }
  }
  if(formClientInline.type == '5') formClientInline.typeinfo = '其他';
}

export interface ExposeType {
    getCardnum:Function
} 
/* 获取身份证号码 */
const getCardnum = (val:number) => {
    subType.value = true;
    formClientInline.cardnum = val;
    params.cardnum = val;
    getClientType();
    selectCByCardnum();
    getUserInfo();
}
defineExpose({
    getCardnum
})

/* 查询客户是否存在 */
const selectCByCardnum = () => {
  getClientByCardnum(params).then(response => {
      if(response.total > 0){
        subType.value = false;
        formClientInline.id = response.rows[0].id;
        formClientInline.client = response.rows[0].client;
        openElM();
      }else{
        formClientInline.client = undefined;
      }
    });
}

/* 查询登录用户信息 */
const userStore = useUserStore();
function getUserInfo(){
  userStore.getInfo().then((response) => {
    formClientInline.sysUserId = response.user.userId;
  });
}

/* 获取客户类型 */
const getClientType = () => {
  selectClientType().then(response => {
    typeList.value = response.rows;
  });
}

/* 信息弹出框 */
const openElM = () => {
  ElMessageBox.confirm(
    '该客户已经存在，是否需要直接立案？',
    '消息提示',
    {
      confirmButtonText: '是',
      cancelButtonText: '否',
      type: 'warning',
    }
  )
  .then(() => {
    emit('toFather',formClientInline.cardnum);
  })
  .catch(() => {
    ElMessage({
      type: 'info',
      message: '正在修改客户信息',
    })
  })
}

/* 展开按钮方法 */
const btnShow = () => {
  if(show3_in.value == true){
      show3_in.value = false;
      btnText.value = '展开';
  }else{
      show3_in.value = true;
      btnText.value = '收起';
  }
}

/* 客户类型选项是否显示 */
const getType = (val) => {
  if(val == 1){
    option_show1.value = true;
    option_show2.value = false;
    option_show3.value = false;
    option_show4.value = false;
    formClientInline.typeinfo = '';
  }
  if(val == 2){
    option_show1.value = false;
    option_show2.value = true;
    option_show3.value = false;
    option_show4.value = false;
    formClientInline.typeinfo = '';
  }
  if(val == 3){
    option_show1.value = false;
    option_show2.value = false;
    option_show3.value = true;
    option_show4.value = false;
    formClientInline.typeinfo = '';
  }
  if(val == 4){
    option_show1.value = false;
    option_show2.value = false;
    option_show3.value = false;
    option_show4.value = true;
    formClientInline.typeinfo = '';
  }
  if(val == 5){
    option_show1.value = false;
    option_show2.value = false;
    option_show3.value = false;
    option_show4.value = false;
    formClientInline.typeinfo = '';
  }
}

/** 获取 省级 地区数据 */
function onChange(e) {
    const one = chinaData[e[0]];
    const two = chinaData[e[1]];
    formClientInline.pertainarea = one.label + "/" + two.label;
}

// 数据校验
const rulesCli = () => {
    if(formClientInline.client == undefined || formClientInline.client === ''){
        ElMessage.warning("客户未填写 ！");
        addretrun.value = false;
        return;
    }

    if(formClientInline.phone == undefined || formClientInline.phone == ''){
        ElMessage.warning("手机号未填写 ！");
        addretrun.value = false;
        return;
    }

    if(formClientInline.type == undefined || formClientInline.type == ''){
        ElMessage.warning("客户类型未填写 ！");
        addretrun.value = false;
        return;
    }else{
      if(formClientInline.type == '1'){
        if(typeinfoCheck.value.length == 0){
          ElMessage.warning("身份特征未填写 ！");
          addretrun.value = false;
          return;
        }
      }
      if(formClientInline.type == '2'){
        if(formClientInline.typeinfo == ''  ){
          ElMessage.warning("类型未填写 ！");
          addretrun.value = false;
          return;
        }
      }
      if(formClientInline.type == '3'){
        if(typeinfOp.value == ''){
          ElMessage.warning("类型未填写 ！");
          addretrun.value = false;
          return;
        }
        if(typeinfoRad.value == ''){
          ElMessage.warning("单位类型未填写 ！");
          addretrun.value = false;
          return;
        }
      }
      if(formClientInline.type == '4'){
        if(formClientInline.typeinfo == ''  ){
          ElMessage.warning("类型未填写 ！");
          addretrun.value = false;
          return;
        }
      }
    }

    if(formClientInline.cardnum == undefined || formClientInline.cardnum == ''){
        ElMessage.warning("身份证号未填写 ！");
        addretrun.value = false;
        return;
    }
    
    // if(formClientInline.principal == undefined || formClientInline.principal == ''){
    //     ElMessage({
    //         message: "主要负责人未填写 ！",
    //         type: 'warning',
    //     })
    //     addretrun.value = false;
    //     return;
    // }
    // if(formClientInline.contact == undefined || formClientInline.contact == ''){
    //     ElMessage({
    //         message: "业务联系人未填写 ！",
    //         type: 'warning',
    //     })
    //     addretrun.value = false;
    //     return;
    // }
    // if(formClientInline.duty == undefined || formClientInline.duty == ''){
    //     ElMessage({
    //         message: "职务未填写 ！",
    //         type: 'warning',
    //     })
    //     addretrun.value = false;
    //     return;
    // }
    // if(formClientInline.tel == undefined || formClientInline.tel == ''){
    //     ElMessage({
    //         message: "固定电话未填写 ！",
    //         type: 'warning',
    //     })
    //     addretrun.value = false;
    //     return;
    // }
    // if(formClientInline.pertainarea == undefined || formClientInline.pertainarea == ''){
    //     ElMessage({
    //         message: "所属地区未填写 ！",
    //         type: 'warning',
    //     })
    //     addretrun.value = false;
    //     return;
    // }
    // if(formClientInline.status == undefined || formClientInline.status == ''){
    //     ElMessage({
    //         message: "客户状态未填写 ！",
    //         type: 'warning',
    //     })
    //     addretrun.value = false;
    //     return;
    // }
    // if(formClientInline.legalperson == undefined || formClientInline.legalperson == ''){
    //     ElMessage({
    //         message: "法定代表人未填写 ！",
    //         type: 'warning',
    //     })
    //     addretrun.value = false;
    //     return;
    // }
    // if(formClientInline.legalpersontel == undefined || formClientInline.legalpersontel == ''){
    //     ElMessage({
    //         message: "法人联系方式未填写 ！",
    //         type: 'warning',
    //     })
    //     addretrun.value = false;
    //     return;
    // }
    // if(formClientInline.nation == undefined || formClientInline.nation == ''){
    //     ElMessage({
    //         message: "民族未填写 ！",
    //         type: 'warning',
    //     })
    //     addretrun.value = false;
    //     return;
    // }
    // if(formClientInline.sex == undefined || formClientInline.sex == ''){
    //     ElMessage({
    //         message: "性别未填写 ！",
    //         type: 'warning',
    //     })
    //     addretrun.value = false;
    //     return;
    // }
    // if(formClientInline.birth == undefined || formClientInline.birth == ''){
    //     ElMessage({
    //         message: "出生日期未填写 ！",
    //         type: 'warning',
    //     })
    //     addretrun.value = false;
    //     return;
    // }if(formClientInline.address == undefined || formClientInline.address == ''){
    //     ElMessage({
    //         message: "详细地址未填写 ！",
    //         type: 'warning',
    //     })
    //     addretrun.value = false;
    //     return;
    // }

    // if(formClientInline.email == undefined || formClientInline.phone == ''){
    //     ElMessage({
    //         message: "邮箱未填写 ！",
    //         type: 'warning',
    //     })
    //     return;
    // }
    // if(formClientInline.affectedareas == undefined || formClientInline.phone == ''){
    //     ElMessage({
    //         message: "地区影响力",
    //         type: 'warning',
    //     })
    //     return;
    // }
}

// 机关单位下拉框列表
const jgdwOptions = [
  {
    label: '政府',
    options: [
      {
        value: '省级政府',
        label: '省级政府',
      },
      {
        value: '市级政府',
        label: '市级政府',
      },
      {
        value: '县级政府',
        label: '县级政府',
      },
      {
        value: '乡级政府',
        label: '乡级政府',
      },
      {
        value: '街道办事处',
        label: '街道办事处',
      },
    ],
  },
  {
    label: '政府部门',
    options: [
      {
        value: '国家部委办',
        label: '国家部委办',
      },
      {
        value: '省政府部门',
        label: '省政府部门',
      },
      {
        value: '市级政府部门',
        label: '市级政府部门',
      },
      {
        value: '市级政府部门',
        label: '市级政府部门',
      },
      {
        value: '县级政府部门',
        label: '县级政府部门',
      },
    ],
  },
]

const nationList = [
      {
        id: 1,
        info: "汉族",
        value: 1,
      },
      {
        id: 2,
        info: "壮族",
        value: 2,
      },
      {
        id: 3,
        info: "满族",
        value: 3,
      },
      {
        id: 4,
        info: "回族",
        value: 4,
      },
      {
        id: 5,
        info: "苗族",
        value: 5,
      },
      {
        id: 6,
        info: "维吾尔族",
        value: 6,
      },
      {
        id: 7,
        info: "土家族",
        value: 7,
      },
      {
        id: 8,
        info: "彝族",
        value: 8,
      },
      {
        id: 9,
        info: "蒙古族",
        value: 9,
      },
      {
        id: 10,
        info: "藏族",
        value: 10,
      },
      {
        id: 11,
        info: "布依族",
        value: 11,
      },
      {
        id: 12,
        info: "侗族",
        value: 12,
      },
      {
        id: 13,
        info: "瑶族",
        value: 13,
      },
      {
        id: 14,
        info: "朝鲜族",
        value: 14,
      },
      {
        id: 15,
        info: "白族",
        value: 15,
      },
      {
        id: 16,
        info: "哈尼族",
        value: 16,
      },
      {
        id: 17,
        info: "哈萨克族",
        value: 17,
      },
      {
        id: 18,
        info: "黎族",
        value: 18,
      },
      {
        id: 19,
        info: "傣族",
        value: 19,
      },
      {
        id: 20,
        info: "畲族",
        value: 20,
      },
      {
        id: 21,
        info: "傈僳族",
        value: 21,
      },
      {
        id: 22,
        info: "仡佬族",
        value: 22,
      },
      {
        id: 23,
        info: "东乡族",
        value: 23,
      },
      {
        id: 24,
        info: "高山族",
        value: 24,
      },
      {
        id: 25,
        info: "拉祜族",
        value: 25,
      },
      {
        id: 26,
        info: "水族",
        value: 26,
      },
      {
        id: 27,
        info: "佤族",
        value: 27,
      },
      {
        id: 28,
        info: "纳西族",
        value: 28,
      },
      {
        id: 29,
        info: "羌族",
        value: 29,
      },
      {
        id: 30,
        info: "土族",
        value: 30,
      },
      {
        id: 31,
        info: "仫佬族",
        value: 31,
      },
      {
        id: 32,
        info: "锡伯族",
        value: 32,
      },
      {
        id: 33,
        info: "柯尔克孜族",
        value: 33,
      },
      {
        id: 34,
        info: "达斡尔族",
        value: 34,
      },
      {
        id: 35,
        info: "景颇族",
        value: 35,
      },
      {
        id: 36,
        info: "毛南族",
        value: 36,
      },
      {
        id: 37,
        info: "撒拉族",
        value: 37,
      },
      {
        id: 38,
        info: "布朗族",
        value: 38,
      },
      {
        id: 39,
        info: "塔吉克族",
        value: 39,
      },
      {
        id: 40,
        info: "阿昌族",
        value: 40,
      },
      {
        id: 41,
        info: "普米族",
        value: 41,
      },
      {
        id: 42,
        info: "鄂温克族",
        value: 42,
      },
      {
        id: 43,
        info: "怒族",
        value: 43,
      },
      {
        id: 44,
        info: "京族",
        value: 44,
      },
      {
        id: 45,
        info: "基诺族",
        value: 45,
      },
      {
        id: 46,
        info: "德昂族",
        value: 46,
      },
      {
        id: 47,
        info: "保安族",
        value: 47,
      },
      {
        id: 48,
        info: "俄罗斯族",
        value: 48,
      },
      {
        id: 49,
        info: "裕固族",
        value: 49,
      },
      {
        id: 50,
        info: "乌孜别克族",
        value: 50,
      },
      {
        id: 51,
        info: "门巴族",
        value: 51,
      },
      {
        id: 52,
        info: "鄂伦春族",
        value: 52,
      },
      {
        id: 53,
        info: "独龙族",
        value: 53,
      },
      {
        id: 54,
        info: "塔塔尔族",
        value: 54,
      },
      {
        id: 55,
        info: "赫哲族",
        value: 55,
      },
      {
        id: 56,
        info: "珞巴族",
        value: 56,
      }
  ];

</script>

