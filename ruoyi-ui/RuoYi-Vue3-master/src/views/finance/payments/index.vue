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

    <!-- 添加或修改全所收支明细对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="收支类别id" prop="cwInoutTypeId">
          <el-input v-model="form.cwInoutTypeId" placeholder="请输入收支类别id" />
        </el-form-item>
        <el-form-item label="发生日期" prop="cwInoutDate">
          <el-input v-model="form.cwInoutDate" placeholder="请输入发生日期" />
        </el-form-item>
        <el-form-item label="收支人" prop="cwInoutPerson">
          <el-input v-model="form.cwInoutPerson" placeholder="请输入收支人" />
        </el-form-item>
        <el-form-item label="收支金额" prop="cwInoutMoney">
          <el-input v-model="form.cwInoutMoney" placeholder="请输入收支金额" />
        </el-form-item>
        <el-form-item label="案件id" prop="caseLawId">
          <el-input v-model="form.caseLawId" placeholder="请输入案件id" />
        </el-form-item>
        <el-form-item label="录入日期" prop="cwEnteringdate">
          <el-input v-model="form.cwEnteringdate" placeholder="请输入录入日期" />
        </el-form-item>
        <el-form-item label="备注" prop="cwRemark">
          <el-input v-model="form.cwRemark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="付款人" prop="cwPayer">
          <el-input v-model="form.cwPayer" placeholder="请输入付款人" />
        </el-form-item>
        <el-divider content-position="center">${subTable.functionName}信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddCwInoutType">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteCwInoutType">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="cwInoutTypeList" :row-class-name="rowCwInoutTypeIndex" @selection-change="handleCwInoutTypeSelectionChange" ref="cwInoutType">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="类别名称" prop="cwName" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.cwName" placeholder="请输入类别名称" />
            </template>
          </el-table-column>
          <el-table-column label="$comment" prop="cwPid" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.cwPid" placeholder="请输入$comment" />
            </template>
          </el-table-column>
          <el-table-column label="$comment" prop="type" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.type" placeholder="请选择$comment">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPayments, getPayments, delPayments, addPayments, updatePayments } from "@/api/finance/payments";

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
        casePaidsal: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
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
