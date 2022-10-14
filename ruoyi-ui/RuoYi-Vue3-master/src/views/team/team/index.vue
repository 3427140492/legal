<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="团队名称" prop="teamName">
        <el-input v-model="queryParams.teamName" placeholder="请输入团队名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="负责人" prop="teamPrincipal">
        <el-input v-model="queryParams.teamPrincipal" placeholder="请输入团队负责人" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="团队成员" prop="teamMember">
        <el-input v-model="queryParams.teamMember" placeholder="请输入团队成员" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="mini" @click="handleQuery">搜索</el-button>
        <el-button size="mini" @click="addUserTeam">添加团队</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="teamList" @selection-change="handleSelectionChange">
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="团队名称" align="center" prop="teamName" />
      <el-table-column label="团队负责人" align="center" prop="teamPrincipal" />
      <el-table-column label="团队成员" align="center" prop="teamMember" />
      <el-table-column label="团队介绍" align="center" prop="teamIntro" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-dropdown trigger="click">
            <el-button>
              操作<el-icon class="el-icon--right">
                <arrow-down />
              </el-icon>
            </el-button>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="ViewDetail(scope.row.id)">查看</el-dropdown-item>
                <el-dropdown-item @click="handleUpdate(scope.row)">修改</el-dropdown-item>
                <el-dropdown-item @click="delArchivesUser(scope.row)">删除</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </template>

      </el-table-column>
    </el-table>

    <el-dialog v-model="this.ViewDetails" title="人事资料详情">
      <el-descriptions :column="3" border>
        <el-descriptions-item label="团队名称" align="center">{{data.teamName}}</el-descriptions-item>
        <el-descriptions-item label="团队负责人" align="center">{{data.teamPrincipal}}</el-descriptions-item>
        <el-descriptions-item label="团队成员" align="center">{{data.teamMember}}</el-descriptions-item>
        <el-descriptions-item label="团队介绍" align="center">{{data.teamIntro}}</el-descriptions-item>
      </el-descriptions>
    </el-dialog>


    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改团队对话框 -->
    <el-dialog :title="title" v-model="this.open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="团队名称" prop="teamName">
          <el-input v-model="form.teamName" placeholder="请输入团队名称" />
        </el-form-item>
        <el-form-item label="负责人"  prop="teamPrincipal">
          <el-select v-model="form.teamPrincipal" placeholder="请输入团队负责人">
            <el-option label="请选择---" value="" />
            <el-option v-for="item in ListUserName" :label="item.empName" :value="item.empName" />
          </el-select>
        </el-form-item>
        <el-form-item label="团队成员" prop="teamMember">
          <div style="display: inline-block">
            <el-select v-model="value1" multiple placeholder="请选择用户">
              <el-option v-for="item in ListUserName" :key="item.id" :label="item.empName" :value="item.empName" />
            </el-select>
          </div>
        </el-form-item>
        <el-form-item label="团队介绍" prop="teamIntro">
          <el-input v-model="form.teamIntro" placeholder="请输入团队介绍" />
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
import { listTeam, getTeam, delTeam, addTeam, updateTeam, listUser } from "@/api/team/team";

export default {
  name: "Team",
  data() {
    return {
      ViewDetails: false,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 团队表格数据
      teamList: [],
      // 团队表格数据
      ListUserName: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        teamName: [
          { required: true, message: "团队名称不能为空", trigger: "blur" }
        ],
        teamPrincipal: [
          { required: true, message: "负责人不能为空", trigger: "blur" }
        ],
        teamIntro: [
          { required: true, message: "团队介绍不能为空", trigger: "blur" }
        ]
      },
      data: {

      },
      value1: []

    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询团队列表 */
    getList() {
      this.loading = true;
      listTeam(this.queryParams).then(response => {
        this.teamList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      listUser().then(response => {
        this.ListUserName = response.rows;
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
        teamName: null,
        teamPrincipal: null,
        teamMember: null,
        teamIntro: null
      };
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
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加团队";
      this.value1 = [];
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getTeam(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改团队";
        this.value1 = [];
        var str = row.teamMember.split(",");
        for (var i = 0; i < str.length; i++) {
          this.value1.push(str[i]);
        }
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            var arr = this.value1;

            var str1 = "";
            for (var i = 0; i < arr.length; i++) {
              if (i < arr.length - 1) {
                str1 += arr[i] + ",";
              } else {
                str1 += arr[i];
              }
            }
            this.form.teamMember = str1;
            updateTeam(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            var arr = this.value1;

            var str1 = "";
            for (var i = 0; i < arr.length; i++) {
              if (i < arr.length - 1) {
                str1 += arr[i] + ",";
              } else {
                str1 += arr[i];
              }
            }
            this.form.teamMember = str1;
            addTeam(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    delArchivesUser(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除团队编号为"' + ids + '"的数据项？').then(function () {
        return delTeam(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('team/team/export', {
        ...this.queryParams
      }, `team_${new Date().getTime()}.xlsx`)
    },
    ViewDetail(id) {
      this.ViewDetails = true;
      getTeam(id).then(response => {
        this.data.teamName = response.data.teamName;
        this.data.teamPrincipal = response.data.teamPrincipal;
        this.data.teamMember = response.data.teamMember;
        this.data.teamIntro = response.data.teamIntro;
      })
    },
    addUserTeam() {
      this.reset();
      this.open = true;
      this.title = "添加人事档案";
      this.value1 = [];
    }
  }
};
</script>
<style>
.el-descriptions {
  margin-top: 20px;
}
</style>
