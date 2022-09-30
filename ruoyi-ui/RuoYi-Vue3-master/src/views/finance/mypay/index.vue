<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item prop="caseNo">
        <el-input
          style="width: 150px;"
          v-model="queryParams.caseNo"
          clearable
          placeholder="案号"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item prop="cwName">
        <el-tree-select placeholder="请选择收支类别" v-model="queryParams.cwName" :data="cwInoutTypeListSelect" :props="{ value: 'id', label: 'cwName', children: 'children' }" :render-after-expand="false" clearable style="width: 400px;"/> 
      </el-form-item>


      <el-form-item prop="cwPayType">
        <el-select v-model="queryParams.cwPayType" placeholder="请选择支付方式">
          <el-option label="转账支票" value="1" />
          <el-option label="电汇" value="2" />
          <el-option label="网银" value="3" />
          <el-option label="Pos机" value="4" />
          <el-option label="现金" value="5" />
        </el-select>
      </el-form-item>

      <el-form-item prop="cctid">
        <el-select v-model="queryParams.cctid" placeholder="请选择案件类型">
        <el-option v-for="t in caseTypeList" :key="t.cctid" :label="t.caseTypeName" :value="t.cctid" />
        </el-select>
      </el-form-item>


      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh"  @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="paymentsList" @selection-change="handleSelectionChange" border :summary-method="getSummaries" show-summary>
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="收支类别" align="center" prop="cwName" width="170" />
      <el-table-column label="发生日期" align="center" prop="cwEnteringdate" />
      <el-table-column label="收入(元)" align="center" prop="cwInoutMoney" />
      <el-table-column label="支付方式" align="center" prop="cwPayType">
        <template v-slot="scope">
          <span v-if="scope.row.cwPayType == 1">转账支票</span>
          <span v-if="scope.row.cwPayType == 3">网银</span>
          <span v-if="scope.row.cwPayType == 5">现金</span>
        </template>
      </el-table-column>
      <el-table-column label="案号" align="center" prop="caseNo" width="160" />
      <el-table-column label="录入日期" align="center" prop="cwEnteringdate" />
      <el-table-column label="备注" align="center" prop="cwRemark" width="150" />
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import { mylistPayments, getPayments, delPayments, addPayments, updatePayments, cwInoutlistType, listType } from "@/api/finance/payments";
export default {
  name: "Payments",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedCwInoutType: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 全所收支明细表格数据
      paymentsList: [],
      caseTypeList: [],
      // ${subTable.functionName}表格数据
      cwInoutTypeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cwInoutTypeId: null,
        cwInoutDate: null,
        cwInoutPerson: null,
        cwInoutMoney: null,
        cwPayType: null,
        caseLawId: null,
        cwEnteringdate: null,
        cwRemark: null,
        cwPayer: null,
        casePaidsal: null,
        cwName: null,
        cctid: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      cwInoutTypeListSelect: []
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询全所收支明细列表 */
    getList() {
      this.loading = true;
      mylistPayments(this.queryParams).then(response => {
        this.paymentsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      cwInoutlistType().then(response => {//树形菜单
        this.cwInoutTypeListSelect = response.rows;
      });
      listType().then(response => {
        this.caseTypeList = response.rows;
      });
    },

    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        cwInoutTypeId: null,
        cwInoutDate: null,
        cwInoutPerson: null,
        cwInoutMoney: null,
        cwPayType: null,
        caseLawId: null,
        cwEnteringdate: null,
        cwRemark: null,
        cwPayer: null
      };
      this.cwInoutTypeList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** ${subTable.functionName}删除按钮操作 */
    handleDeleteCwInoutType() {
      if (this.checkedCwInoutType.length == 0) {
        this.$modal.msgError("请先选择要删除的${subTable.functionName}数据");
      } else {
        const cwInoutTypeList = this.cwInoutTypeList;
        const checkedCwInoutType = this.checkedCwInoutType;
        this.cwInoutTypeList = cwInoutTypeList.filter(function(item) {
          return checkedCwInoutType.indexOf(item.index) == -1
        });
      }
    }
  }
};

</script>
