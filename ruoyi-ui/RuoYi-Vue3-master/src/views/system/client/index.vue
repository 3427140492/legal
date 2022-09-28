<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="客户姓名" prop="client">
        <el-input
          v-model="queryParams.client"
          placeholder="请输入客户姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['system:client:add']"
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
          v-hasPermi="['system:client:edit']"
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
          v-hasPermi="['system:client:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:client:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="clientList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="客户姓名" align="center" prop="client" />
      <el-table-column label="手机号" align="center" prop="phone" />
      <el-table-column label="类型" align="center" prop="type" />
      <el-table-column label="类型名称" align="center" prop="typeinfo" />
      <el-table-column label="卡号" align="center" prop="cardnum" />
      <el-table-column label="主要" align="center" prop="principal" />
      <el-table-column label="联系方式" align="center" prop="contact" />
      <el-table-column label="邮箱" align="center" prop="email" />
      <el-table-column label="职责" align="center" prop="duty" />
      <el-table-column label="地区" align="center" prop="affectedareas" />
      <el-table-column label="电话" align="center" prop="tel" />
      <el-table-column label="门管区" align="center" prop="pertainarea" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="法人代表" align="center" prop="legalperson" />
      <el-table-column label="法务人员" align="center" prop="legalpersontel" />
      <el-table-column label="文件夹" align="center" prop="file" />
      <el-table-column label="民族" align="center" prop="nation" />
      <el-table-column label="性别" align="center" prop="sex" />
      <el-table-column label="生日" align="center" prop="birth" />
      <el-table-column label="地址" align="center" prop="address" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="添加时间" align="center" prop="addtime" />
      <el-table-column label="用户编号" align="center" prop="systemUserId" />
      <el-table-column label="状态" align="center" prop="assign" />
      <el-table-column label="文件地址" align="center" prop="fileurl" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:client:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:client:remove']"
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

    <!-- 添加或修改客户对话框 -->
    <el-dialog :title="title" v-model="open"  width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户姓名" prop="client">
          <el-input v-model="form.client" placeholder="请输入客户姓名" />
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="类型名称" prop="typeinfo">
          <el-input v-model="form.typeinfo" placeholder="请输入类型名称" />
        </el-form-item>
        <el-form-item label="卡号" prop="cardnum">
          <el-input v-model="form.cardnum" placeholder="请输入卡号" />
        </el-form-item>
        <el-form-item label="主要" prop="principal">
          <el-input v-model="form.principal" placeholder="请输入主要" />
        </el-form-item>
        <el-form-item label="联系方式" prop="contact">
          <el-input v-model="form.contact" placeholder="请输入联系方式" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="职责" prop="duty">
          <el-input v-model="form.duty" placeholder="请输入职责" />
        </el-form-item>
        <el-form-item label="地区" prop="affectedareas">
          <el-input v-model="form.affectedareas" placeholder="请输入地区" />
        </el-form-item>
        <el-form-item label="电话" prop="tel">
          <el-input v-model="form.tel" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="门管区" prop="pertainarea">
          <el-input v-model="form.pertainarea" placeholder="请输入门管区" />
        </el-form-item>
        <el-form-item label="法人代表" prop="legalperson">
          <el-input v-model="form.legalperson" placeholder="请输入法人代表" />
        </el-form-item>
        <el-form-item label="法务人员" prop="legalpersontel">
          <el-input v-model="form.legalpersontel" placeholder="请输入法务人员" />
        </el-form-item>
        <el-form-item label="文件夹">
          <file-upload v-model="form.file"/>
        </el-form-item>
        <el-form-item label="民族" prop="nation">
          <el-input v-model="form.nation" placeholder="请输入民族" />
        </el-form-item>
        <el-form-item label="生日" prop="birth">
          <el-input v-model="form.birth" placeholder="请输入生日" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="添加时间" prop="addtime">
          <el-input v-model="form.addtime" placeholder="请输入添加时间" />
        </el-form-item>
        <el-form-item label="用户编号" prop="systemUserId">
          <el-input v-model="form.systemUserId" placeholder="请输入用户编号" />
        </el-form-item>
        <el-form-item label="状态" prop="assign">
          <el-input v-model="form.assign" placeholder="请输入状态" />
        </el-form-item>
        <el-form-item label="文件地址" prop="fileurl">
          <el-input v-model="form.fileurl" placeholder="请输入文件地址" />
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
import { listClient, getClient, delClient, addClient, updateClient } from "@/api/system/client";

export default {
  name: "Client",
  data() {
    return {
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
      // 客户表格数据
      clientList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        client: null,
        phone: null,
        type: null,
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
    /** 查询客户列表 */
    getList() {
      this.loading = true;
      listClient(this.queryParams).then(response => {
        this.clientList = response.rows;
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
        client: null,
        phone: null,
        type: null,
        typeinfo: null,
        cardnum: null,
        principal: null,
        contact: null,
        email: null,
        duty: null,
        affectedareas: null,
        tel: null,
        pertainarea: null,
        status: 0,
        legalperson: null,
        legalpersontel: null,
        file: null,
        nation: null,
        sex: null,
        birth: null,
        address: null,
        remark: null,
        addtime: null,
        systemUserId: null,
        assign: null,
        fileurl: null
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
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加客户";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getClient(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改客户";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateClient(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addClient(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除客户编号为"' + ids + '"的数据项？').then(function() {
        return delClient(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/client/export', {
        ...this.queryParams
      }, `client_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
