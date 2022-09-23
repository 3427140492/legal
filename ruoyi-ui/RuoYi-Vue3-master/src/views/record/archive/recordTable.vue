<template>
  <el-card>
    <el-tabs v-model="activeName" type="border-card">
      <el-tab-pane label="案件归档" name="ajarchive">
        <ajarchive-info-form ref="ajarchiveInfo" :info="info" :tables="tables" :menus="menus" />
      </el-tab-pane>
      
      <el-tab-pane label="我的电子归档" name="myarchive">
        <myarchive-info-form ref="myarchiveInfo" :info="info" />
      </el-tab-pane>
    </el-tabs>
  </el-card>
</template>

<script>
import { getGenTable, updateGenTable } from "@/api/tool/gen";
import { optionselect as getDictOptionselect } from "@/api/system/dict/type";
import { listMenu as getMenuTreeselect } from "@/api/system/menu";
import ajarchiveInfoForm from "./index.vue";
import myarchiveInfoForm from "./document.vue";

export default {
  name: "GenEdit",
  components: {
    ajarchiveInfoForm,
    myarchiveInfoForm
  },
  data() {
    return {
      // 选中选项卡的 name
      activeName: "ajarchive",
      // 表格的高度
      tableHeight: document.documentElement.scrollHeight - 245 + "px",
      // 表信息
      tables: [],
      // 表列信息
      columns: [],
      // 字典信息
      dictOptions: [],
      // 菜单信息
      menus: [],
      // 表详细信息
      info: {}
    };
  },
  created() {
    const tableId = this.$route.params && this.$route.params.tableId;
    if (tableId) {
      // 获取表详细信息
      getGenTable(tableId).then(res => {
        this.columns = res.data.rows;
        this.info = res.data.info;
        this.tables = res.data.tables;
      });
      /** 查询字典下拉列表 */
      getDictOptionselect().then(response => {
        this.dictOptions = response.data;
      });
      /** 查询菜单下拉列表 */
      getMenuTreeselect().then(response => {
        this.menus = this.handleTree(response.data, "menuId");
      });
    }
   }
};
</script>
