<template>
   <div class="app-container">
      <el-steps :space="200" :active="active" finish-status="finish" simple direction="vertical">
        <el-step @click="toTitle('newOneShow',0)" title="1.选择案件类型" :icon="Edit"/>
        <el-step @click="toTitle('newTwoShow',1)" title="2.利益冲突审查" :icon="Edit"/>
        <el-step @click="toTitle('newThreeShow',2)" title="3.新增委托人" :icon="Edit"/>
        <el-step @click="toTitle('newFourShow',3)" title="4.登记立案信息" :icon="Edit"/>
        <el-step @click="toTitle('newFiveShow',4)" title="5.立案文书上传" :icon="Edit"/>
        <el-step @click="toTitle('newSixShow',5)" title="6.收案审批" :icon="Edit"/>
      </el-steps>
      <br><br>

      <newOne v-show="newOneShow" @to-father="getNewOne"></newOne>

      <newTwo v-show="newTwoShow" @to-father="getNewTwo"></newTwo>

      <newThree ref="cardnum" v-show="newThreeShow" @to-father="getNewThree"></newThree>

      <newFour ref="Ocardnum" v-show="newFourShow" @to-father="getNewFour"></newFour>

      <newFive ref="caseLawId" v-show="newFiveShow" @to-father="getNewFive"></newFive>

      <newSix ref="newSixRef" v-show="newSixShow" @to-father="getNewSix"></newSix>

      <newSeven ref="newSevenRef" v-show="newSevenShow"></newSeven>
   </div>
</template>
<script lang="ts" setup name="Case">
import { Edit } from '@element-plus/icons-vue';
import newOne from './newOne.vue';
import newTwo from './newTwo.vue';
import newThree from './newThree.vue';
import newFour from './newFour.vue';
import newFive from './newFive.vue';
import newSix from './newSix.vue';
import newSeven from './newSeven.vue';

const active = ref(0); // 进度条值
const newOneShow = ref(true);
const newTwoShow = ref(false);
const newThreeShow = ref(false);
const newFourShow = ref(false);
const newFiveShow = ref(false);
const newSixShow = ref(false);
const newSevenShow = ref(false);

const cardnum = ref<ExposeType | null>(null); // 身份证号码
const Ocardnum = ref<ExposeType | null>(null); // 对方当事人证件号
const num = ref();
const caseLawId = ref<ExposeType | null>(null); // 对方当事人证件号
const newSixRef = ref();
const newSevenRef = ref();

const toTitle = (tit:string, id:number) => {
    if (tit == 'newOneShow') {
      newOneShow.value = true;
      newTwoShow.value = false;
      newThreeShow.value = false;
      newFourShow.value = false;
      newFiveShow.value = false;
      newSixShow.value = false;
      newSevenShow.value = false;
    }
    if (tit == 'newTwoShow') {
      newOneShow.value = false;
      newTwoShow.value = true;
      newThreeShow.value = false;
      newFourShow.value = false;
      newFiveShow.value = false;
      newSixShow.value = false;
      newSevenShow.value = false;
    }
    if (tit == 'newThreeShow') {
      newOneShow.value = false;
      newTwoShow.value = false;
      newThreeShow.value = true;
      newFourShow.value = false;
      newFiveShow.value = false;
      newSixShow.value = false;
      newSevenShow.value = false;
    }
    if (tit == 'newFourShow') {
      newOneShow.value = false;
      newTwoShow.value = false;
      newThreeShow.value = false;
      newFourShow.value = true;
      newFiveShow.value = false;
      newSixShow.value = false;
      newSevenShow.value = false;
    }
    if (tit == 'newFiveShow') {
      newOneShow.value = false;
      newTwoShow.value = false;
      newThreeShow.value = false;
      newFourShow.value = false;
      newFiveShow.value = true;
      newSixShow.value = false;
      newSevenShow.value = false;
    }
    if (tit == 'newSixShow') {
      newOneShow.value = false;
      newTwoShow.value = false;
      newThreeShow.value = false;
      newFourShow.value = false;
      newFiveShow.value = false;
      newSixShow.value = true;
      newSevenShow.value = false;
    }
    active.value = id;
}

const getNewOne = (val:string, val2:string) => {
  newOneShow.value = false;
  newTwoShow.value = true;
  next();
  Ocardnum.value.getType(val, val2);
}

const getNewTwo = (val:number, val2:number) => {
  newOneShow.value = false;
  newTwoShow.value = false;
  newThreeShow.value = true;
  next();
  num.value = val2;
  if(cardnum.value) cardnum.value.getCardnum(val);
}

const getNewThree = (val:string) => {
  newOneShow.value = false;
  newTwoShow.value = false;
  newThreeShow.value = false;
  newFourShow.value = true;
  var ss = 1;
  Ocardnum.value.getClient(val,num.value);
  // Ocardnum.value.getOcardnum(num.value);
  next();
}

const caseInfo = ref();
const getNewFour = (val:number, val2: Object) => {
  newOneShow.value = false;
  newTwoShow.value = false;
  newThreeShow.value = false;
  newFourShow.value = false;
  newFiveShow.value = true;
  caseInfo.value = val2;
  if(caseLawId.value) caseLawId.value.getCaseLawId(val);
}

const getNewFive = () => {
  newOneShow.value = false;
  newTwoShow.value = false;
  newThreeShow.value = false;
  newFourShow.value = false;
  newFiveShow.value = false;
  newSixShow.value = true;
  next();
  newSixRef.value.getInfo(caseInfo.value);
}

const getNewSix = () => {
  newOneShow.value = false;
  newTwoShow.value = false;
  newThreeShow.value = false;
  newFourShow.value = false;
  newFiveShow.value = false;
  newSixShow.value = false;
  newSevenShow.value = true;
  next();
  newSevenRef.value.propsEmitRef(caseInfo.value.id);
}

const next = () => {
  if (active.value++ > 5) active.value = 0
}

</script>

<style>

</style>
