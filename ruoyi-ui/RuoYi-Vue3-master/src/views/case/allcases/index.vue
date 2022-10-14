<template>
   <div class="app-container">
      <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
         <el-tab-pane label="全所案件" name="first" style="width: 108%;  margin-left: -4%; margin-top: -2.3%;">
            <caseSummary ref="first"></caseSummary>
         </el-tab-pane>

         <el-tab-pane label="全所待收款案件" name="second" style="width: 108%;  margin-left: -4%; margin-top: -2.3%;">
            <stayProceedsCase ref="second" ></stayProceedsCase>
         </el-tab-pane>

         <el-tab-pane label="全所案件汇总" name="third" style="width: 108%;  margin-left: -4%; margin-top: -2.3%;">
            <caseSummarys ref="third"></caseSummarys>
         </el-tab-pane>
      </el-tabs>
   </div>
</template>

<script lang="ts" setup>
import caseSummary from './caseSummary.vue';
import stayProceedsCase from './stayProceedsCase.vue';
import caseSummarys from './caseSummarys.vue';

const activeName = ref('first');
const first = ref();
const second = ref();
const third = ref();
  
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

