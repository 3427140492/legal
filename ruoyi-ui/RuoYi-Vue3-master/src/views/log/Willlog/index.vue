<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm"  :inline="true" v-show="showSearch" label-width="68px">
     


      <el-form-item label="开始时间" prop="joblogStarttime">
        <el-date-picker clearable v-model="queryParams.joblogStarttime" type="date" value-format="YYYY-MM-DD"
          placeholder="请选择开始时间">
        </el-date-picker>
      </el-form-item>

      <el-form-item label="自报时间" prop="joblogReportedtime">
        <el-input v-model="queryParams.joblogReportedtime" placeholder="请输入自报时间" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <br />
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="WilllogList" @selection-change="handleSelectionChange">
      
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="日志类型" align="center" prop="logTypeLogname" />
      <el-table-column label="案号" align="center" prop="caseNo" />
      <el-table-column label="客户" align="center" prop="client" />
      <el-table-column label="开始时间" align="center" prop="joblogStarttime" width="180" />
      <el-table-column label="自报时间" align="center" prop="joblogReportedtime" />
      <el-table-column label="工作描述" align="center" prop="joblogText" />
      <el-table-column label="工作状态" align="center" prop="joblogStatus" >
       
      <template v-slot="scope">
          <span v-if="scope.row.joblogStatus == 'Y'">公开</span>
          <span v-if="scope.row.joblogStatus == 'N'">不公开</span>
        </template>
        </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button type="info" plain @click="ViewDetail(scope.row.id)">查看</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <el-dialog v-model="this.ViewDetails" title="查看日志">
      <el-descriptions title="" :column="3" border>
        <el-descriptions-item label="日志类型" align="center">{{data.logTypeLogname}}</el-descriptions-item>
        <el-descriptions-item label="归属人" align="center">{{data.caseSubmitter}}</el-descriptions-item>
        <el-descriptions-item label="公开状态" align="center">{{data.joblogStatus == 'Y'?'公开':'未公开'}}</el-descriptions-item>
        <el-descriptions-item label="案件" align="center">{{data.caseNo}}</el-descriptions-item>
        <el-descriptions-item label="客户名称" align="center">{{data.client}}</el-descriptions-item>
        <el-descriptions-item label="起始时间" align="center">{{data.joblogStarttime}}</el-descriptions-item>
        <el-descriptions-item label="自报时间" align="center">{{data.joblogReportedtime}}</el-descriptions-item>
        <el-descriptions-item label="更新时间" align="center">{{data.joblogUpdatetime}}</el-descriptions-item>
        <el-descriptions-item label="备注" align="center">{{data.joblogText}}</el-descriptions-item>
      </el-descriptions>
    </el-dialog>

    <!-- 添加或修改全所日志对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-divider content-position="center">客户信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddClient">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteClient">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="clientList" :row-class-name="rowClientIndex" @selection-change="handleClientSelectionChange"
          ref="client">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50" />
          <el-table-column label="客户" prop="client" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.client" placeholder="请输入客户" />
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
import { listWilllog, getWilllog, delWilllog, addWilllog, updateWilllog } from "@/api/log/Willlog";

export default {
  name: "Willlog",
  data() {
    return {
      // 遮罩层
      loading: true,

      ViewDetails: false,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedClient: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 全所日志表格数据
      WilllogList: [],
      // 客户表格数据
      clientList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      data:{

      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        logtypeId: null,
        caseLawId: null,
        clientId: null,
        joblogStarttime: null,
        joblogReportedtime: null,
        joblogText: null,
        joblogStatus: null
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
    /** 查询全所日志列表 */
    getList() {
      this.loading = true;
      listWilllog(this.queryParams).then(response => {
        this.WilllogList = response.rows;
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
        whatisPerson: null,
        logtypeId: null,
        caseLawId: null,
        clientId: null,
        joblogStarttime: null,
        joblogEnddtime: null,
        joblogReportedtime: null,
        joblogUpdatetime: null,
        joblogFile: null,
        joblogText: null,
        joblogStatus: "0"
      };
      this.clientList = [];
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
    ViewDetail(id) {
      this.ViewDetails = true;
      getWilllog(id).then(response => {
        this.data.logTypeLogname = response.data.logTypeLogname;
        this.data.caseSubmitter = response.data.caseSubmitter;
        this.data.joblogStatus = response.data.joblogStatus;
        this.data.caseNo = response.data.caseNo;
        this.data.client = response.data.client;
        this.data.joblogStarttime = response.data.joblogStarttime;
        this.data.joblogReportedtime = response.data.joblogReportedtime;
        this.data.joblogUpdatetime = response.data.joblogUpdatetime;
        this.data.joblogText = response.data.joblogText;
      })
    },





    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加全所日志";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getWilllog(id).then(response => {
        this.form = response.data;
        this.clientList = response.data.clientList;
        this.open = true;
        this.title = "修改全所日志";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.clientList = this.clientList;
          if (this.form.id != null) {
            updateWilllog(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWilllog(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除全所日志编号为"' + ids + '"的数据项？').then(function () {
        return delWilllog(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 客户序号 */
    rowClientIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 客户添加按钮操作 */
    handleAddClient() {
      let obj = {};
      obj.client = "";
      this.clientList.push(obj);
    },
    /** 客户删除按钮操作 */
    handleDeleteClient() {
      if (this.checkedClient.length == 0) {
        this.$modal.msgError("请先选择要删除的客户数据");
      } else {
        const clientList = this.clientList;
        const checkedClient = this.checkedClient
        this.clientList = clientList.filter(function (item) {
          return checkedClient.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleClientSelectionChange(selection) {
      this.checkedClient = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('log/Willlog/export', {
        ...this.queryParams
      }, `Willlog_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
