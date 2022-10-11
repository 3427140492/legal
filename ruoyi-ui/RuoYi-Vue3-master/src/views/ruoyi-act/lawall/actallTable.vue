<template>
  <el-card>
    <el-tabs v-model="activeName">
      <el-tab-pane label="收案申请" name="index">
        <index-info-form ref="indexInfoForm" :info="info" :tables="tables" :menus="menus" />
      </el-tab-pane>
      
      <el-tab-pane label="用印申请" name="prints">
        <prints-info-form ref="printsInfo" :info="info" />
      </el-tab-pane>
      <el-tab-pane label="业务申请" name="applys">
        <applys-info-form ref="applysInfo" :info="info"/>
      </el-tab-pane>
    </el-tabs>
  </el-card>
</template>

<script>
import { getGenTable, updateGenTable } from "@/api/tool/gen";
import { optionselect as getDictOptionselect } from "@/api/system/dict/type";
import { listMenu as getMenuTreeselect } from "@/api/system/menu";
import indexInfoForm from "./index";
import printsInfoForm from "./prints";
import applysInfoForm from "./applys";

export default {
  name: "GenEdit",
  components: {
    printsInfoForm,
    indexInfoForm,
    applysInfoForm
  },
  data() {
    return {
      // 选中选项卡的 name
      activeName: "index",
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
