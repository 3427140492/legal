// 用印申请
<template>
   <div class="app-container">
      <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
         <el-tab-pane label="手动模式" name="first" style="width: 100%;  margin-left: -1.5%; margin-top: -2.3%;">
            <manualMode ref="first" />
         </el-tab-pane>
         <el-tab-pane label="自动模式" name="second" style="width: 108%;  margin-left: -4%; margin-top: -2.3%;">
            <AutoUpload ref="second" />
         </el-tab-pane>
         <el-tab-pane label="用印记录" name="three" style="width: 108%;  margin-left: -4%; margin-top: -2.3%;">
            <usingRecord ref="three" />
         </el-tab-pane>
      </el-tabs>
   </div>
</template>

<script lang="ts" setup>
import manualMode from './manualMode.vue';
import usingRecord from './usingRecord.vue';
import AutoUpload from './AutoUpload.vue';

const activeName = ref('first');
const first = ref();
const second = ref();
const three = ref();

/* 切换Tab */
const handleClick = (tab: TabsPaneContext, event: Event) => {
   if(tab.props.name == 'first'){
      first.value.getList();
   }
   if(tab.props.name == 'second'){
      second.value.getList();
   }
   if(tab.props.name == 'three'){
      three.value.getList();
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

