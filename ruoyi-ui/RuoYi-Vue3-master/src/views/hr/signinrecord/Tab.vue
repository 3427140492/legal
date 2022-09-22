<template>
   <el-card>
     <el-tabs v-model="activeName" type="border-card">
       <el-tab-pane label="签到管理" name="basic">
         <basic-info-form ref="basicInfo" :info="info" />
       </el-tab-pane>
       <el-tab-pane label="签到统计" name="columnInfo">
        
       </el-tab-pane>
     </el-tabs>
   </el-card>
 </template>
 
 <script setup name="GenEdit">
 import { optionselect as getDictOptionselect } from "@/api/system/dict/type";

 
 const route = useRoute();
 const { proxy } = getCurrentInstance();
 
 const activeName = ref("columnInfo");
 const tableHeight = ref(document.documentElement.scrollHeight - 245 + "px");
 const tables = ref([]);
 const columns = ref([]);
 const dictOptions = ref([]);
 const info = ref({});
 

 </script>
 