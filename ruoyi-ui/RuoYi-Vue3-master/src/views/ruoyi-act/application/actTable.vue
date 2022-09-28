<template>
  <el-card>
    <el-tabs v-model="activeName">
      <el-tab-pane label="收案申请" name="cognizance">
        <cognizance-info-form ref="cognizanceInfo" :info="info" :tables="tables" :menus="menus" />
      </el-tab-pane>
      
      <el-tab-pane label="用印申请" name="print">
        <print-info-form ref="printInfo" :info="info" />
      </el-tab-pane>
      <el-tab-pane label="业务申请" name="apply">
        <apply-info-form ref="applyInfo" :info="info"/>
      </el-tab-pane>
    </el-tabs>
    <!-- <el-form label-width="100px">
      <el-form-item style="text-align: center;margin-left:-100px;margin-top:10px;">
        <el-button type="primary" @click="submitForm()">提交</el-button>
        <el-button @click="close()">返回</el-button>
      </el-form-item>
    </el-form> -->
  </el-card>
</template>

<script>
import { getGenTable, updateGenTable } from "@/api/tool/gen";
import { optionselect as getDictOptionselect } from "@/api/system/dict/type";
import { listMenu as getMenuTreeselect } from "@/api/system/menu";
import cognizanceInfoForm from "./cognizance";
import printInfoForm from "./print.vue";
import applyInfoForm from "./apply.vue";

export default {
  name: "GenEdit",
  components: {
    printInfoForm,
    cognizanceInfoForm,
    applyInfoForm
  },
  data() {
    return {
      // 选中选项卡的 name
      activeName: "cognizance",
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
  // },
  // methods: {
  //   /** 提交按钮 */
  //   submitForm() {
  //     const cognizanceForm = this.$refs.cognizanceInfo.$refs.cognizanceInfoForm;
  //     const printForm = this.$refs.printInfo.$refs.printInfoForm;
  //     Promise.all([cognizanceForm, printForm].map(this.getFormPromise)).then(res => {
  //       const validateResult = res.every(item => !!item);
  //       if (validateResult) {
  //         const genTable = Object.assign({}, cognizanceForm.model, printForm.model);
  //         genTable.columns = this.columns;
  //         genTable.params = {
  //           treeCode: genTable.treeCode,
  //           treeName: genTable.treeName,
  //           treeParentCode: genTable.treeParentCode,
  //           parentMenuId: genTable.parentMenuId
  //         };
  //         updateGenTable(genTable).then(res => {
  //           this.$modal.msgSuccess(res.msg);
  //           if (res.code === 200) {
  //             this.close();
  //           }
  //         });
  //       } else {
  //         this.$modal.msgError("表单校验未通过，请重新检查提交内容");
  //       }
  //     });
  //   },
  //   getFormPromise(form) {
  //     return new Promise(resolve => {
  //       form.validate(res => {
  //         resolve(res);
  //       });
  //     });
  //   },
  //   /** 关闭按钮 */
  //   close() {
  //     const obj = { path: "/tool/gen", query: { t: Date.now(), pageNum: this.$route.query.pageNum } };
  //     this.$tab.closeOpenPage(obj);
  //   }
  // },
  // mounted() {
  //   const el = this.$refs.dragTable.$el.querySelectorAll(".el-table__body-wrapper > table > tbody")[0];
  //   const sortable = Sortable.create(el, {
  //     handle: ".allowDrag",
  //     onEnd: evt => {
  //       const targetRow = this.columns.splice(evt.oldIndex, 1)[0];
  //       this.columns.splice(evt.newIndex, 0, targetRow);
  //       for (let index in this.columns) {
  //         this.columns[index].sort = parseInt(index) + 1;
  //       }
  //     }
  //   });
   }
};
</script>
