<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="签到日期" prop="signinrecordSign">
        <el-date-picker clearable
          v-model="queryParams.signinrecordSign"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择签到日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['hr:signinrecord:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['hr:signinrecord:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['hr:signinrecord:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['hr:signinrecord:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="signinrecordList" @selection-change="handleSelectionChange">
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column label="签到类型" align="center" prop="classify">
        <template v-slot="scope">
          <span v-if="scope.row.classify == 1">上班签到</span>
          <span v-if="scope.row.classify == 2">会议签到</span>
          <span v-if="scope.row.classify == 3">外勤签到</span>
        </template>
      </el-table-column>
      <el-table-column label="签到人" align="center" prop="systemUserId" />
      <el-table-column label="签到日期" align="center" prop="signinrecordSign" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.signinrecordSign, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="签到地点" align="center" prop="signinrecordSite" />
      <!-- <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['hr:signinrecord:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['hr:signinrecord:remove']"
          >删除</el-button>
        </template>
      </el-table-column> -->
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改签到对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-divider content-position="center">系统用户信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddSystemUser">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteSystemUser">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="systemUserList" :row-class-name="rowSystemUserIndex" @selection-change="handleSystemUserSelectionChange" ref="systemUser">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="$comment" prop="userName" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.userName" placeholder="请输入$comment" />
            </template>
          </el-table-column>
          <el-table-column label="$comment" prop="userPwd" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.userPwd" placeholder="请输入$comment" />
            </template>
          </el-table-column>
          <el-table-column label="$comment" prop="userRealname" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.userRealname" placeholder="请输入$comment" />
            </template>
          </el-table-column>
          <el-table-column label="$comment" prop="userPhone" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.userPhone" placeholder="请输入$comment" />
            </template>
          </el-table-column>
          <el-table-column label="$comment" prop="userEmail" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.userEmail" placeholder="请输入$comment" />
            </template>
          </el-table-column>
          <el-table-column label="$comment" prop="userCreatedate" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.userCreatedate" type="date" value-format="yyyy-MM-dd" placeholder="请选择$comment" />
            </template>
          </el-table-column>
          <el-table-column label="$comment" prop="userPreDate" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.userPreDate" type="date" value-format="yyyy-MM-dd" placeholder="请选择$comment" />
            </template>
          </el-table-column>
          <el-table-column label="$comment" prop="userStatus" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.userStatus" placeholder="请选择$comment">
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
import { listSigninrecord, getSigninrecord, delSigninrecord, addSigninrecord, updateSigninrecord } from "@/api/hr/signinrecord";

export default {
  name: "Signinrecord",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedSystemUser: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 签到表格数据
      signinrecordList: [],
      // 系统用户表格数据
      systemUserList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        classify: null,
        signinrecordSign: null,
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
    /** 查询签到列表 */
    getList() {
      this.loading = true;
      listSigninrecord(this.queryParams).then(response => {
        this.signinrecordList = response.rows;
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
        systemUserId: null,
        classify: null,
        signinrecordSign: null,
        signinrecordSite: null,
        signinStatus: "0"
      };
      this.systemUserList = [];
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
      this.title = "添加签到";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSigninrecord(id).then(response => {
        this.form = response.data;
        this.systemUserList = response.data.systemUserList;
        this.open = true;
        this.title = "修改签到";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.systemUserList = this.systemUserList;
          if (this.form.id != null) {
            updateSigninrecord(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSigninrecord(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除签到编号为"' + ids + '"的数据项？').then(function() {
        return delSigninrecord(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 系统用户序号 */
    rowSystemUserIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 系统用户添加按钮操作 */
    handleAddSystemUser() {
      let obj = {};
      obj.userName = "";
      obj.userPwd = "";
      obj.userRealname = "";
      obj.userPhone = "";
      obj.userEmail = "";
      obj.userCreatedate = "";
      obj.userPreDate = "";
      obj.userLoginNum = "";
      obj.hrEmpId = "";
      obj.userStatus = "";
      this.systemUserList.push(obj);
    },
    /** 系统用户删除按钮操作 */
    handleDeleteSystemUser() {
      if (this.checkedSystemUser.length == 0) {
        this.$modal.msgError("请先选择要删除的系统用户数据");
      } else {
        const systemUserList = this.systemUserList;
        const checkedSystemUser = this.checkedSystemUser
        this.systemUserList = systemUserList.filter(function(item) {
          return checkedSystemUser.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleSystemUserSelectionChange(selection) {
      this.checkedSystemUser = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('hr/signinrecord/export', {
        ...this.queryParams
      }, `signinrecord_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
