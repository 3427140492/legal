<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item prop="SearchStr">
        <el-input
          v-model="queryParams.SearchStr"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item prop="SearchType">
        <el-select v-model="queryParams.SearchType">
        <el-option label="案号搜索" value="1"/>
          <el-option label="上传人搜索" value="2"/>
          <el-option label="归档文件搜索" value="3"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

   

    <el-table v-loading="loading" :data="documentList" @selection-change="handleSelectionChange" border >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="案号" align="center" prop="caseNo" />
      <el-table-column label="归档文件" align="center" prop="documentFile" />
      <el-table-column label="上传人" align="center" prop="userRealname" />
      <el-table-column label="最后更新日期" align="center" prop="upddate" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['record:document:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['record:document:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改全所电子归档对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="归档文件" prop="documentFile">
          <el-input v-model="form.documentFile" placeholder="" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listDocument, getDocument, delDocument, addDocument, updateDocument } from "@/api/record/document";

export default {
  name: "Document",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedCaseLaw: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 全所电子归档表格数据
      documentList: [],
      // ${subTable.functionName}表格数据
      caseLawList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        SearchStr: null,
        SearchType:null
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
    /** 查询全所电子归档列表 */
    getList() {
      this.loading = true;
      listDocument(this.queryParams).then(response => {
        this.documentList = response.rows;
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
        caseLawId: null,
        systemUserId: null,
        upddate: null,
        folderId: null,
        documentFile: null
      };
      this.caseLawList = [];
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
      this.title = "添加全所电子归档";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getDocument(id).then(response => {
        this.form = response.data;
        this.caseLawList = response.data.caseLawList;
        this.open = true;
        this.title = "修改全所电子归档";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.caseLawList = this.caseLawList;
          if (this.form.id != null) {
            updateDocument(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDocument(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除全所电子归档编号为"' + ids + '"的数据项？').then(function() {
        return delDocument(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** ${subTable.functionName}序号 */
    rowCaseLawIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** ${subTable.functionName}添加按钮操作 */
    handleAddCaseLaw() {
      let obj = {};
      obj.caseNo = "";
      obj.caseOppositeParties = "";
      obj.caseOppositeCardnum = "";
      obj.caseThirdParty = "";
      obj.caseSuspect = "";
      obj.caseClash = "";
      obj.caseCause = "";
      obj.caseAccept = "";
      obj.caseLawsuitobj = "";
      obj.caseSubsidy = "";
      obj.caseSubsidysal = "";
      obj.caseSettleStatus = "";
      obj.casePlan = "";
      obj.caseApproveStatus = "";
      obj.caseAgencyfee = "";
      obj.caseProxysal = "";
      obj.caseTransactionRegion = "";
      obj.caseAssign = "";
      obj.casePaidsal = "";
      obj.caseUnpaidsal = "";
      obj.caseInvoiced = "";
      obj.caseNotinvoiced = "";
      obj.casePropernum = "";
      obj.caseCourt = "";
      obj.caseFilenumber = "";
      obj.caseCorrelationFile = "";
      obj.caseSubmitter = "";
      obj.caseSubtime = "";
      obj.caseRecycle = "";
      obj.caseRemarks = "";
      obj.clientId = "";
      obj.caseAttorney = "";
      obj.caseStatus = "";
      obj.caseLawsuitStatus = "";
      obj.caseChargeWay = "";
      obj.systemApprovalId = "";
      obj.caseCaseTypeId = "";
      obj.caseSealApplyforId = "";
      obj.hrEmpId = "";
      obj.collectionTime = "";
      obj.caseParties = "";
      obj.caseFxmoney = "";
      obj.caseWtr = "";
      obj.caseContract = "";
      obj.caseRecord = "";
      obj.caseShare = "";
      obj.caseSettleType = "";
      obj.caseApprovalEndtime = "";
      obj.caseRecordNum = "";
      obj.standard = "";
      this.caseLawList.push(obj);
    },
    /** ${subTable.functionName}删除按钮操作 */
    handleDeleteCaseLaw() {
      if (this.checkedCaseLaw.length == 0) {
        this.$modal.msgError("请先选择要删除的${subTable.functionName}数据");
      } else {
        const caseLawList = this.caseLawList;
        const checkedCaseLaw = this.checkedCaseLaw;
        this.caseLawList = caseLawList.filter(function(item) {
          return checkedCaseLaw.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleCaseLawSelectionChange(selection) {
      this.checkedCaseLaw = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('record/document/export', {
        ...this.queryParams
      }, `document_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
