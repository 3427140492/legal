// 管理案件
<template>
   <div class="app-container">
      <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
         <el-tab-pane label="管理案件" name="first" style="width: 108%;  margin-left: -4%; margin-top: -2.3%;">
            <caseSummary ref="first"></caseSummary>
         </el-tab-pane>

         <el-tab-pane label="案件回收站" name="second" style="width: 108%;  margin-left: -4%; margin-top: -2.3%;">
            <caseSummarys ref="second" ></caseSummarys>
         </el-tab-pane>

      </el-tabs>

   </div>
</template>

<script lang="ts" setup>
import caseSummary from './caseSummary.vue';
import caseSummarys from './caseSummarys.vue';

const activeName = ref('first');
const first = ref();
const second = ref();

  
/* 切换Tab */
const handleClick = (tab: TabsPaneContext, event: Event) => {
   // alert(tab.props.name);
   if(tab.props.name == 'first'){
      first.value.getList();
   }
   if(tab.props.name == 'second'){
      second.value.getList();
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

