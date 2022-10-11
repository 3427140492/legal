<template>
<div class="app-container">
<span style="font-weight: bold;font-size: 20px;">| 案件归档</span>
<hr/>
<p><font color="red">*</font> 案件</p>
<el-input type="text" @click="caseFiling = true" style="width: 300px" />

<el-dialog v-model="caseFiling">
  <el-form :inline="true">
    <el-form-item prop="SearchStr">
      <el-input/>
    </el-form-item>
    <el-form-item prop="SearchType">
      <el-select >
        <el-option label="案号搜索" value="1"/>
        <el-option label="委托人搜索" value="2"/>
        <el-option label="对方当事人搜索" value="3"/>
        <el-option label="案由搜索" value="4"/>
        <el-option label="承办律师搜索" value="5"/>
        <el-option label="受理法院搜索" value="6"/>
      </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="lawlist" border >
      <el-table-column label="案号" align="center" prop="caseNo" />
      <el-table-column label="对方当事人" align="center" prop="caseOppositeParties" />
      <el-table-column label="委托人" align="center" prop="caseWtr" />
    </el-table> 
</el-dialog>
</div>
</template>
<script lang="ts" setup>
import { selectCaseLawList } from "@/api/record/law";
import {reactive,ref} from 'vue'

const caseFiling = ref(false);

const lawlist = ref();

function caseLawList(){
  selectCaseLawList().then(response => {
    lawlist.value = response.rows;
  });
}
caseLawList();

</script>
<style></style>