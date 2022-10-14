<template>
   <div class="app-container">
      <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
         <el-tab-pane label="我的个人案件" name="first" style="width: 108%;  margin-left: -4%; margin-top: -2.3%;">
            <myPersonalCase ref="first"></myPersonalCase>
         </el-tab-pane>

         <el-tab-pane label="我的合办案件" name="second" style="width: 108%;  margin-left: -4%; margin-top: -2.3%;">
            <myPersonalCases ref="second" ></myPersonalCases>
         </el-tab-pane>

         <el-tab-pane label="共享案件" name="third" style="width: 108%;  margin-left: -4%; margin-top: -2.3%;">
            <shareTheCase ref="third"></shareTheCase>
         </el-tab-pane>

         <el-tab-pane label="我的待收款案件" name="fourth" style="width: 108%;  margin-left: -4%; margin-top: -2.3%;">
            <myStayProceedsCase ref="fourth"></myStayProceedsCase>
         </el-tab-pane>
         
         <el-tab-pane label="我的案件汇总" name="sour" style="width: 108%;  margin-left: -4%; margin-top: -2.3%;">
            <myCaseSummary ref="sour"></myCaseSummary>
         </el-tab-pane>
      </el-tabs>

   </div>
</template>

<script lang="ts" setup>
import myPersonalCase from './myPersonalCase.vue';
import myPersonalCases from './myPersonalCases.vue';
import shareTheCase from './shareTheCase.vue';
import myStayProceedsCase from './myStayProceedsCase.vue';
import myCaseSummary from './myCaseSummary.vue';

const activeName = ref('first');
const first = ref();
const second = ref();
const third = ref();
const fourth = ref();
const sour = ref();
  
/* 切换Tab */
const handleClick = (tab: TabsPaneContext, event: Event) => {
   // alert(tab.props.name);
   if(tab.props.name == 'first'){
      first.value.getList();
   }
   if(tab.props.name == 'second'){
      second.value.getList();
   }
   if(tab.props.name == 'third'){
      third.value.getList();
   }
   if(tab.props.name == 'fourth'){
      fourth.value.getList();
   }
   if(tab.props.name == 'sour'){
      sour.value.getList();
   }
}
</script>

<style>
.demo-tabs > .el-tabs__content {
  padding: 42px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}
</style>

