<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item prop="SearchStr">
        <el-input
          style="width: 150px;"
          v-model="queryParams.SearchStr"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item prop="SearchType">
        <el-select v-model="queryParams.SearchType" style="width: 120px;">
        <el-option label="案号搜索" value="1"/>
          <el-option label="收支人搜索" value="2"/>
          <el-option label="委托人搜索" value="3"/>
        </el-select>
      </el-form-item>

      <el-form-item prop="cwName">
        <el-tree-select placeholder="请选择收支类别" v-model="queryParams.cwName" :data="cwInoutTypeListSelect" :props="{ value: 'id', label: 'cwName', children: 'children' }" :render-after-expand="false" clearable style="width: 270px;"/> 
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

      <el-form-item>
        <el-select v-model="queryParams.cctid" placeholder="请选择案件类型" style="width:150px;">
        <el-option v-for="t in caseTypeList" :key="t.cctid" :label="t.caseTypeName" :value="t.cctid" />
        </el-select>
      </el-form-item>

      <el-form-item label="日期" prop="cwInoutDate">
        <el-date-picker clearable
          v-model="queryParams.cwInoutDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="">
        </el-date-picker>
      </el-form-item>

      <el-form-item label="" prop="overTime">
        <el-date-picker clearable
          v-model="queryParams.overTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="">
        </el-date-picker>
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
      <el-table-column label="支出(元)" align="center" prop="casePaidsal">
        <template v-slot="scope">
          <span v-if="scope.row.casePaidsal == null">-</span>
        </template>
      </el-table-column>
      <el-table-column label="支付方式" align="center" prop="cwPayType">
        <template v-slot="scope">
          <span v-if="scope.row.cwPayType == 1">转账支票</span>
          <span v-if="scope.row.cwPayType == 3">网银</span>
          <span v-if="scope.row.cwPayType == 5">现金</span>
        </template>
      </el-table-column>
      <el-table-column label="案号" align="center" prop="caseNo" width="160" />
      <el-table-column label="收支人" align="center" prop="empName" />
      <el-table-column label="付款人" align="center" prop="cwPayer" />
      <el-table-column label="备注" align="center" prop="cwRemark" width="150" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="80">
        <template v-slot="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['finance:payments:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
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
import { listPayments, getPayments, delPayments, addPayments, updatePayments, cwInoutlistType, listType } from "@/api/finance/payments";
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
        cctid: null,
        overTime: null,
        typeId: null
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
      listPayments(this.queryParams).then(response => {
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
        cwPayer: null,
        overTime: null
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加全所收支明细";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getPayments(id).then(response => {
        this.form = response.data;
        this.cwInoutTypeList = response.data.cwInoutTypeList;
        this.open = true;
        this.title = "修改全所收支明细";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.cwInoutTypeList = this.cwInoutTypeList;
          if (this.form.id != null) {
            updatePayments(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPayments(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除全所收支明细编号为"' + ids + '"的数据项？').then(function() {
        return delPayments(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** ${subTable.functionName}序号 */
    rowCwInoutTypeIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** ${subTable.functionName}添加按钮操作 */
    handleAddCwInoutType() {
      let obj = {};
      obj.cwName = "";
      obj.cwPid = "";
      obj.type = "";
      this.cwInoutTypeList.push(obj);
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
