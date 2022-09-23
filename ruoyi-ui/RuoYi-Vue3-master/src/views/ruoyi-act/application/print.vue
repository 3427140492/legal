<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="printInfoForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="案号" prop="caseNo">
        <el-input
          v-model="queryParams.caseNo"
          placeholder="请输入案号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="申请领用日期" prop="applyforReceive">
        <el-date-picker clearable
          v-model="queryParams.applyforReceive"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择申请领用日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="申请人" prop="applyforRecipient">
        <el-input
          v-model="queryParams.applyforRecipient"
          placeholder="请输入申请人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收函方" prop="sendPerson">
        <el-input
          v-model="queryParams.sendPerson"
          placeholder="请输入收函方"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="案件id" prop="caseid">
        <el-input
          v-model="queryParams.caseid"
          placeholder="请输入案件id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="typeid">
        <el-input
          v-model="queryParams.typeid"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="applyforList" @selection-change="handleSelectionChange">
      <el-table-column label="案号" align="center" prop="caseNo" />
      <el-table-column label="业务子类型" align="center" prop="typenName" />
      <el-table-column label="文书名称" align="center" prop="applyforUsingTheItem" />
      <el-table-column label="审批状态 1,2,3" align="center" prop="applyforApprovalStatus" />
      <el-table-column label="提交时间" align="center" prop="applyforReceive" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.applyforReceive, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ruoyi-act:applyfor:edit']"
          >流程查看</el-button>
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

    <!-- 添加或修改用印申请对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <h3>{{form.applyforUsingTheItem}} </h3>
        <el-form-item label="案号" prop="caseNo">
          {{form.caseNo}}
        </el-form-item>
        <el-form-item label="申请领用日期" prop="applyforReceive">
          <el-date-picker clearable
            v-model="form.applyforReceive"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择申请领用日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="申请人" prop="applyforRecipient">
          <el-input v-model="form.applyforRecipient" placeholder="请输入申请人" />
        </el-form-item>
        <el-form-item label="收函方" prop="sendPerson">
          <el-input v-model="form.sendPerson" placeholder="请输入收函方" />
        </el-form-item>
        <el-form-item label="用印事项" prop="applyforUsingTheItem">
          <el-input v-model="form.applyforUsingTheItem" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="案件id" prop="caseid">
          <el-input v-model="form.caseid" placeholder="请输入案件id" />
        </el-form-item>
        <el-form-item label="${comment}" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="${comment}" prop="typeid">
          <el-input v-model="form.typeid" placeholder="请输入${comment}" />
        </el-form-item>
        <el-divider content-position="center">${subTable.functionName}信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddCaseFiles">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteCaseFiles">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="caseFilesList" :row-class-name="rowCaseFilesIndex" @selection-change="handleCaseFilesSelectionChange" ref="caseFiles">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="用印申请外键" prop="appid" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.appid" placeholder="请输入用印申请外键" />
            </template>
          </el-table-column>
          <el-table-column label="$comment" prop="filename" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.filename" placeholder="请输入$comment" />
            </template>
          </el-table-column>
          <el-table-column label="$comment" prop="filepath" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.filepath" placeholder="请输入$comment" />
            </template>
          </el-table-column>
          <el-table-column label="文件类型外键" prop="typeid" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.typeid" placeholder="请输入文件类型外键" />
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
import { listApplyfor, getApplyfor, delApplyfor, addApplyfor, updateApplyfor } from "@/api/ruoyi-act/applyfor";

export default {
  name: "Applyfor",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedCaseFiles: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 用印申请表格数据
      applyforList: [],
      // ${subTable.functionName}表格数据
      caseFilesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        caseNo: null,
        applyforReceive: null,
        applyforApprovalStatus: null,
        applyforRecipient: null,
        sendPerson: null,
        applyforUsingTheItem: null,
        caseid: null,
        typeid: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        applyforApprovalStatus: [
          { required: true, message: "审批状态 1,2,3不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询用印申请列表 */
    getList() {
      this.loading = true;
      listApplyfor(this.queryParams).then(response => {
        this.applyforList = response.rows;
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
        caseNo: null,
        applyforReceive: null,
        applyforApprovalStatus: "0",
        applyforRecipient: null,
        sendPerson: null,
        applyforUsingTheItem: null,
        caseid: null,
        remark: null,
        typeid: null
      };
      this.caseFilesList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("printInfoForm");
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
      this.title = "添加用印申请";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getApplyfor(id).then(response => {
        this.form = response.data;
        this.caseFilesList = response.data.caseFilesList;
        this.open = true;
        this.title = "流程查看";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.caseFilesList = this.caseFilesList;
          if (this.form.id != null) {
            updateApplyfor(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addApplyfor(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除用印申请编号为"' + ids + '"的数据项？').then(function() {
        return delApplyfor(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** ${subTable.functionName}序号 */
    rowCaseFilesIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** ${subTable.functionName}添加按钮操作 */
    handleAddCaseFiles() {
      let obj = {};
      obj.appid = "";
      obj.filename = "";
      obj.filepath = "";
      obj.typeid = "";
      this.caseFilesList.push(obj);
    },
    /** ${subTable.functionName}删除按钮操作 */
    handleDeleteCaseFiles() {
      if (this.checkedCaseFiles.length == 0) {
        this.$modal.msgError("请先选择要删除的${subTable.functionName}数据");
      } else {
        const caseFilesList = this.caseFilesList;
        const checkedCaseFiles = this.checkedCaseFiles;
        this.caseFilesList = caseFilesList.filter(function(item) {
          return checkedCaseFiles.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleCaseFilesSelectionChange(selection) {
      this.checkedCaseFiles = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ruoyi-act/applyfor/export', {
        ...this.queryParams
      }, `applyfor_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
