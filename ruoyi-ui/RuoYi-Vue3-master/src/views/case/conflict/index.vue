<template>
    <div class="app-container"><br>
        <el-form :inline="true" class="demo-form-inline" :model="formInline">
            <el-form-item label="委托人/当事人证件号：" prop="cardnum">
                <el-input v-model="formInline.cardnum" oninput="value=value.replace(/[^\d]/g,'')" clearable  style="width: 400px;"/>
            </el-form-item>
            <el-form-item label="对方当事人证件号：" prop="caseOppositeCardnum">
                <el-input v-model="formInline.caseOppositeCardnum" oninput="value=value.replace(/[^\d]/g,'')" clearable  style="width: 400px;"/>
            </el-form-item>
            <el-form-item>
                <el-button @click="onSubmit" type="primary" :icon="Search">立即检测</el-button>&nbsp;&nbsp;
                <el-button @click="reset" :icon="Refresh">重置</el-button>&nbsp;&nbsp;
            </el-form-item>
        </el-form>
        <br>
        <el-table v-loading="loading" :data="caseLawList" @selection-change="handleSelectionChange">
            <!-- <el-table-column type="selection" width="50" align="center" /> -->
            <el-table-column  label="案号" prop="id" align="center" />
            <el-table-column label="当事人/委托人" prop="caseWtr" align="center" :show-overflow-tooltip="true" />
            <el-table-column label="嫌疑人" prop="caseSuspect" align="center" :show-overflow-tooltip="true" />
            <el-table-column label="对方当事人" prop="caseOppositeParties" align="center"  :show-overflow-tooltip="true" />
            <el-table-column label="承办律师	" prop="caseAttorney" align="center"  :show-overflow-tooltip="true" />
            <el-table-column label="案由" prop="caseCause" align="center"  :show-overflow-tooltip="true" />
            <el-table-column label="收案日期	" prop="collectionTime" align="center" :show-overflow-tooltip="true" />
            <el-table-column label="结案情况" prop="caseSettleStatus" align="center" :show-overflow-tooltip="true">
                <template #default="scope">
                    {{ scope.row.caseSettleStatus == 'N' ? '已结案' : '未结案' }}
                </template>
            </el-table-column>
        </el-table>
    </div>        
</template>

<script lang="ts" setup>
import { selectCaseLawParamsList } from "@/api/cases/CaseLaw.js";
import { Edit, Search, Plus, InfoFilled, Refresh  } from '@element-plus/icons-vue';
import { ElMessage } from 'element-plus';

const caseLawList = ref(); // 利益冲突列表
const formInline = reactive({cardnum: '',caseOppositeCardnum: ''}); // 利益冲突form

/* 查询利益冲突 */
const onSubmit = () => {
    if(rules() == false) return;
    selectCaseLawParamsList(formInline).then(response => {
        caseLawList.value = [];
        if (response.total > 0){
            caseLawList.value = response.rows;
        }
    });
}

const reset = () => {
  formInline.cardnum = '';
  formInline.caseOppositeCardnum = '';
  caseLawList.value = [];
}

/* 数据校验 */
const rules = () => {
    if(formInline.cardnum != '' && formInline.caseOppositeCardnum != ''){
        if(formInline.cardnum == formInline.caseOppositeCardnum){
            ElMessage.warning('当事人/对方当事人证件号有误!');
            return false;
        }
    }else{
        ElMessage.warning('请填写当事人/对方当事人!');
        return false;
    }
}
</script>
