<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm"  :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="委托人" prop="client">
        <el-input
          v-model="queryParams.client"
          placeholder="请输入委托人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="tel">
        <el-input
          v-model="queryParams.tel"
          placeholder="请输入联系电话"
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
          v-hasPermi="['client:client:add']"
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
          v-hasPermi="['client:client:edit']"
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
          v-hasPermi="['client:client:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['client:client:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="clientList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
  
      <el-table-column label="客户（委托人）" align="center" prop="client" />
     
      <el-table-column label="联系电话" align="center" prop="tel" />

      <el-table-column label="地区" align="center" prop="address" />
     
      <el-table-column label="状态" align="center" prop="assign" >
        <template v-slot="scope">
          <span v-if="scope.row.assign == '1'">是</span>
          <span v-if="scope.row.assign == '0'">否</span>
        </template>
        </el-table-column>

      
     
      <el-table-column label="入库时间" align="center" prop="addtime" />
      
     
      


       
     
     
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改客户管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户" prop="client">
          <el-input v-model="form.client" placeholder="请输入客户" />
        </el-form-item>
        <el-form-item label="手机号码" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="类型" prop="typeinfo">
          <el-input v-model="form.typeinfo" placeholder="请输入类型" />
        </el-form-item>
        <el-form-item label="身份证号码 " prop="cardnum">
          <el-input v-model="form.cardnum" placeholder="请输入身份证号码 " />
        </el-form-item>
        <el-form-item label="主要负责人" prop="principal">
          <el-input v-model="form.principal" placeholder="请输入主要负责人" />
        </el-form-item>
        <el-form-item label="联络人" prop="contact">
          <el-input v-model="form.contact" placeholder="请输入联络人" />
        </el-form-item>
        <el-form-item label="邮件" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮件" />
        </el-form-item>
        <el-form-item label="职责" prop="duty">
          <el-input v-model="form.duty" placeholder="请输入职责" />
        </el-form-item>
        <el-form-item label="受影响区" prop="affectedareas">
          <el-input v-model="form.affectedareas" placeholder="请输入受影响区" />
        </el-form-item>
        <el-form-item label="联系电话" prop="tel">
          <el-input v-model="form.tel" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="汇管区" prop="pertainarea">
          <el-input v-model="form.pertainarea" placeholder="请输入汇管区" />
        </el-form-item>
        <el-form-item label="法人代表" prop="legalperson">
          <el-input v-model="form.legalperson" placeholder="请输入法人代表" />
        </el-form-item>
        <el-form-item label="法方电话" prop="legalpersontel">
          <el-input v-model="form.legalpersontel" placeholder="请输入法方电话" />
        </el-form-item>
        <el-form-item label="文件 ">
          <file-upload v-model="form.file"/>
        </el-form-item>
        <el-form-item label="民族" prop="nation">
          <el-select v-model="form.nation">
            <el-option value="">选择民族</el-option>
            <el-option value="汉族">汉族</el-option>
            <el-option value="壮族">壮族</el-option>
            <el-option value="满族">满族</el-option>
            <el-option value="回族">回族</el-option>
            <el-option value="苗族">苗族</el-option>
            <el-option value="维吾尔族">维吾尔族</el-option>
            <el-option value="土家族">土家族</el-option>
            <el-option value="彝族">彝族</el-option>
            <el-option value="蒙古族">蒙古族</el-option>
            <el-option value="藏族">藏族</el-option>
            <el-option value="布依族">布依族</el-option>
            <el-option value="侗族">侗族</el-option>
            <el-option value="瑶族">瑶族</el-option>
            <el-option value="朝鲜族">朝鲜族</el-option>
            <el-option value="白族">白族</el-option>
            <el-option value="哈尼族">哈尼族</el-option>
            <el-option value="哈萨克族">哈萨克族</el-option>
            <el-option value="黎族">黎族</el-option>
            <el-option value="傣族">傣族</el-option>
            <el-option value="畲族">畲族</el-option>
            <el-option value="傈僳族">傈僳族</el-option>
            <el-option value="仡佬族">仡佬族</el-option>
            <el-option value="东乡族">东乡族</el-option>
            <el-option value="高山族">高山族</el-option>
            <el-option value="拉祜族">拉祜族</el-option>
            <el-option value="水族">水族</el-option>
            <el-option value="佤族">佤族</el-option>
            <el-option value="纳西族">纳西族</el-option>
            <el-option value="羌族">羌族</el-option>
            <el-option value="土族">土族</el-option>
            <el-option value="仫佬族">仫佬族</el-option>
            <el-option value="锡伯族">锡伯族</el-option>
            <el-option value="柯尔克孜族">柯尔克孜族</el-option>
            <el-option value="达斡尔族">达斡尔族</el-option>
            <el-option value="景颇族">景颇族</el-option>
            <el-option value="毛南族">毛南族</el-option>
            <el-option value="撒拉族">撒拉族</el-option>
            <el-option value="布朗族">布朗族</el-option>
            <el-option value="塔吉克族">塔吉克族</el-option>
            <el-option value="阿昌族">阿昌族</el-option>
            <el-option value="普米族">普米族</el-option>
            <el-option value="鄂温克族">鄂温克族</el-option>
            <el-option value="怒族">怒族</el-option>
            <el-option value="京族">京族</el-option>
            <el-option value="基诺族">基诺族</el-option>
            <el-option value="德昂族">德昂族</el-option>
            <el-option value="保安族">保安族</el-option>
            <el-option value="俄罗斯族">俄罗斯族</el-option>
            <el-option value="裕固族">裕固族</el-option>
            <el-option value="乌孜别克族">乌孜别克族</el-option>
            <el-option value="门巴族">门巴族</el-option>
            <el-option value="鄂伦春族">鄂伦春族</el-option>
            <el-option value="独龙族">独龙族</el-option>
            <el-option value="塔塔尔族">塔塔尔族</el-option>
            <el-option value="赫哲族">赫哲族</el-option>
            <el-option value="珞巴族">珞巴族</el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="出生" prop="birth">
          <el-input v-model="form.birth" placeholder="请输入出生" />
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="发布时间" prop="addtime">
          <el-input v-model="form.addtime" placeholder="请输入发布时间" />
        </el-form-item>
        <el-form-item label="系统用户" prop="systemUserId">
          <el-input v-model="form.systemUserId" placeholder="请输入系统用户" />
        </el-form-item>
        <el-form-item label="状态" prop="assign">
          <el-input v-model="form.assign" placeholder="请输入状态" />
        </el-form-item>
        <el-form-item label="文件地址 " prop="fileurl">
          <el-input v-model="form.fileurl" placeholder="请输入文件地址 " />
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
import { listClient, getClient, delClient, addClient, updateClient } from "@/api/client/client";

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
      // 客户管理表格数据
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
        typeinfo: null,
        cardnum: null,
        principal: null,
        contact: null,
        email: null,
        duty: null,
        affectedareas: null,
        tel: null,
        pertainarea: null,
        status: null,
        legalperson: null,
        legalpersontel: null,
        file: null,
        nation: null,
        sex: null,
        birth: null,
        address: null,
        addtime: null,
        systemUserId: null,
        assign: null,
        fileurl: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        client: [
          { required: true, message: "客户不能为空", trigger: "blur" }
        ],
        phone: [
          { required: true, message: "手机号码不能为空", trigger: "blur" }
        ],
        cardnum: [
          { required: true, message: "身份证号码不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询客户管理列表 */
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
      this.title = "添加客户管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getClient(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改客户管理";
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
      this.$modal.confirm('是否确认删除客户管理编号为"' + ids + '"的数据项？').then(function() {
        return delClient(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('client/client/export', {
        ...this.queryParams
      }, `client_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
