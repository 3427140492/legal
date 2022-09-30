<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="" prop="clientname">
        <el-input
          v-model="queryParams.clientname"
          placeholder="请输入客户名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      

      <el-form-item label="提交日期" prop="complainSubmitTime">
              <el-date-picker clearable v-model="queryParams.complainSubmitTime" value-format="YYYY-MM-DD" placeholder="请选择提交日期">
              </el-date-picker>
        </el-form-item>
     
      <el-form-item>
        <el-button type="primary"  size="mini" @click="handleQuery">搜索</el-button>
        <el-button size="mini"  @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          size="mini"
          @click="handleAdd"
          v-hasPermi="['hr:complain:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['hr:complain:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['hr:complain:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          size="mini"
          @click="handleExport"
          v-hasPermi="['hr:complain:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table border="1px" v-loading="loading" :data="complainList" @selection-change="handleSelectionChange">
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column label="投诉内容" align="center" prop="complainContent" />
      <el-table-column label="案件" align="center" prop="caseno" />
      <el-table-column label="客户姓名" align="center" prop="clientname" />
      <el-table-column label="联系电话" align="center" prop="phone" />
      <el-table-column label="提交时间" align="center" prop="complainSubmitTime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.complainSubmitTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="回复数" align="center" prop="complainReplyNumber" />
      <!-- <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['hr:complain:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['hr:complain:remove']"
          >删除</el-button>
        </template>
      </el-table-column> -->
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改建议投诉对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="投诉内容">
          <editor v-model="form.complainContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="案件id 外键" prop="lawCaseId">
          <el-input v-model="form.lawCaseId" placeholder="请输入案件id 外键" />
        </el-form-item>
        <el-form-item label="客户id 外键" prop="clientId">
          <el-input v-model="form.clientId" placeholder="请输入客户id 外键" />
        </el-form-item>
        <el-form-item label="提交日期" prop="complainSubmitTime">
          <el-date-picker clearable
            v-model="form.complainSubmitTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择提交日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="回复数" prop="complainReplyNumber">
          <el-input v-model="form.complainReplyNumber" placeholder="请输入回复数" />
        </el-form-item>
        <el-divider content-position="center">客户信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddClient">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteClient">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="clientList" :row-class-name="rowClientIndex" @selection-change="handleClientSelectionChange" ref="client">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="客户姓名" prop="client" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.client" placeholder="请输入客户姓名" />
            </template>
          </el-table-column>
          <el-table-column label="手机号" prop="phone" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.phone" placeholder="请输入手机号" />
            </template>
          </el-table-column>
          <el-table-column label="类型" prop="type" width="150">
            <template v-slot="scope">
              <el-select v-model="scope.row.type" placeholder="请选择类型">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="类型名称" prop="typeinfo" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.typeinfo" placeholder="请输入类型名称" />
            </template>
          </el-table-column>
          <el-table-column label="卡号" prop="cardnum" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.cardnum" placeholder="请输入卡号" />
            </template>
          </el-table-column>
          <el-table-column label="主要" prop="principal" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.principal" placeholder="请输入主要" />
            </template>
          </el-table-column>
          <el-table-column label="联系方式" prop="contact" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.contact" placeholder="请输入联系方式" />
            </template>
          </el-table-column>
          <el-table-column label="邮箱" prop="email" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.email" placeholder="请输入邮箱" />
            </template>
          </el-table-column>
          <el-table-column label="职责" prop="duty" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.duty" placeholder="请输入职责" />
            </template>
          </el-table-column>
          <el-table-column label="地区" prop="affectedareas" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.affectedareas" placeholder="请输入地区" />
            </template>
          </el-table-column>
          <el-table-column label="电话" prop="tel" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.tel" placeholder="请输入电话" />
            </template>
          </el-table-column>
          <el-table-column label="门管区" prop="pertainarea" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.pertainarea" placeholder="请输入门管区" />
            </template>
          </el-table-column>
          <el-table-column label="状态" prop="status" width="150">
            <template v-slot="scope">
              <el-select v-model="scope.row.status" placeholder="请选择状态">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="法人代表" prop="legalperson" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.legalperson" placeholder="请输入法人代表" />
            </template>
          </el-table-column>
          <el-table-column label="法务人员" prop="legalpersontel" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.legalpersontel" placeholder="请输入法务人员" />
            </template>
          </el-table-column>
          <el-table-column label="民族" prop="nation" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.nation" placeholder="请输入民族" />
            </template>
          </el-table-column>
          <el-table-column label="性别" prop="sex" width="150">
            <template v-slot="scope">
              <el-select v-model="scope.row.sex" placeholder="请选择性别">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="生日" prop="birth" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.birth" placeholder="请输入生日" />
            </template>
          </el-table-column>
          <el-table-column label="地址" prop="address" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.address" placeholder="请输入地址" />
            </template>
          </el-table-column>
          <el-table-column label="备注" prop="remark" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.remark" placeholder="请输入备注" />
            </template>
          </el-table-column>
          <el-table-column label="添加时间" prop="addtime" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.addtime" placeholder="请输入添加时间" />
            </template>
          </el-table-column>
          <el-table-column label="用户编号" prop="systemUserId" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.systemUserId" placeholder="请输入用户编号" />
            </template>
          </el-table-column>
          <el-table-column label="状态" prop="assign" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.assign" placeholder="请输入状态" />
            </template>
          </el-table-column>
          <el-table-column label="文件地址" prop="fileurl" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.fileurl" placeholder="请输入文件地址" />
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
import { listComplain, getComplain, delComplain, addComplain, updateComplain } from "@/api/hr/complain";

export default {
  name: "Complain",
  data() {
    return {
      // 遮罩层
      loading: true,
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
      // 建议投诉表格数据
      complainList: [],
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
        complainContent: null,
        lawCaseId: null,
        clientId: null,
        complainSubmitTime: null,
        complainReplyNumber: null,
        clientname:null
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
    /** 查询建议投诉列表 */
    getList() {
      this.loading = true;
      listComplain(this.queryParams).then(response => {
        this.complainList = response.rows;
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
        complainContent: null,
        lawCaseId: null,
        clientId: null,
        complainSubmitTime: null,
        complainReplyNumber: null
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
      this.title = "添加建议投诉";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getComplain(id).then(response => {
        this.form = response.data;
        this.clientList = response.data.clientList;
        this.open = true;
        this.title = "修改建议投诉";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.clientList = this.clientList;
          if (this.form.id != null) {
            updateComplain(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addComplain(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除建议投诉编号为"' + ids + '"的数据项？').then(function() {
        return delComplain(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 客户序号 */
    rowClientIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 客户添加按钮操作 */
    handleAddClient() {
      let obj = {};
      obj.client = "";
      obj.phone = "";
      obj.type = "";
      obj.typeinfo = "";
      obj.cardnum = "";
      obj.principal = "";
      obj.contact = "";
      obj.email = "";
      obj.duty = "";
      obj.affectedareas = "";
      obj.tel = "";
      obj.pertainarea = "";
      obj.status = "";
      obj.legalperson = "";
      obj.legalpersontel = "";
      obj.file = "";
      obj.nation = "";
      obj.sex = "";
      obj.birth = "";
      obj.address = "";
      obj.remark = "";
      obj.addtime = "";
      obj.systemUserId = "";
      obj.assign = "";
      obj.fileurl = "";
      this.clientList.push(obj);
    },
    /** 客户删除按钮操作 */
    handleDeleteClient() {
      if (this.checkedClient.length == 0) {
        this.$modal.msgError("请先选择要删除的客户数据");
      } else {
        const clientList = this.clientList;
        const checkedClient = this.checkedClient
        this.clientList = clientList.filter(function(item) {
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
      this.download('hr/complain/export', {
        ...this.queryParams
      }, `complain_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
