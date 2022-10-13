<template>
  <div>
    <el-descriptions title="选择案件类型" :size="large">
        <!-- <el-descriptions-item label="Username">kooriookami</el-descriptions-item> -->
    </el-descriptions><br>
    <span v-for="t in typeList" :key="t.id">
        <el-button @click="getTypes(t.id, t.caseTypeName)" type="primary" size="large" plain style="width: 150px;margin:0px 38px 25px 0px;">{{ t.caseTypeName }}</el-button>
    </span>
    <el-divider /><br>
    <span v-for="t in typeListChild" :key="t.id">
        <el-button @click="toNext(t.id)" type="info" plain size="large" style="width: 160px; margin:0px 38px 25px 0px;">{{ t.caseTypeName }}</el-button>
    </span>
  </div>
      
</template>
<script lang="ts" setup>
import { listCaseType, getType } from "@/api/cases/CaseCaseType.js";

const emit = defineEmits<{(e:'toFather',msg:string,msg2:string):void}>();
const typeList = ref(); // 案件类型列表
const typeListChild = ref(); // 子案件类型列表
const type = ref(); // 案件类型

const toNext = (id) => {
  emit('toFather',type.value, id);
}

/** 查询案件类型子列表 */
const getTypes = (id, caseTypeName) => {
    type.value = caseTypeName;
    getType(id).then(response => {
        typeListChild.value = response.rows;
    });
}

/** 查询案件类型列表 */
const listCaseTypes = () => {
    listCaseType().then(response => {
        typeList.value = response.rows;
        getTypes(typeList.value[0].id, typeList.value[0].caseTypeName);
    });
}

listCaseTypes();
</script>
