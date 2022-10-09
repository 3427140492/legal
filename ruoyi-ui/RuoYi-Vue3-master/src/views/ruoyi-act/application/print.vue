<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="printInfoForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="案号" prop="caseNo">
        <el-input
          v-model="queryParams.caseNo"
          placeholder="请输入案号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审批状态" prop="applyforApprovalStatus">
        <el-select  v-model="queryParams.applyforApprovalStatus" >
          <el-option label="" value="">请选择审批</el-option>  
          <el-option label="审批中" value="1">审批中</el-option>    
          <el-option label="审批通过" value="2">审批通过</el-option>
          <el-option label="审批不通过" value="3">审批不通过</el-option>      
        </el-select>
      </el-form-item>
      <el-form-item label="案件类型" prop="typeid">
        <el-select v-model="queryParams.typeid" class="m-2" placeholder="案件类型">
          <el-option label="" value="">请选择案件类型</el-option>
          <el-option
            v-for="item in options"
            :key="item.id"
            :label="item.typeName"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="提交时间" prop="applyforReceive">
        <el-date-picker clearable
          v-model="queryParams.applyforReceive"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择提交时间">
        </el-date-picker>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="Search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="applyforList" @selection-change="handleSelectionChange">
      <el-table-column label="案号" align="center" prop="caseNo" />
      <el-table-column label="业务子类型" align="center" prop="typeName" />
      <el-table-column label="文书名称" align="center" prop="applyforUsingTheItem" />
      <el-table-column label="审批状态 1,2,3" align="center" prop="applyforApprovalStatus" >
        <template v-slot="scope">
          <span v-if="scope.row.applyforApprovalStatus == 1">审批中</span>
          <span v-if="scope.row.applyforApprovalStatus == 2">审批通过</span>
          <span v-if="scope.row.applyforApprovalStatus == 3">审批不通过</span>
        </template>
      </el-table-column>
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
    <el-dialog :title="title" v-model="open" width="800px" append-to-body>
      <el-descriptions :title=form.typeName >
        <el-descriptions-item label="案号:">{{form.caseNo}}</el-descriptions-item>
        <el-descriptions-item label="案由:">{{form.caseCause}}</el-descriptions-item>
        <el-descriptions-item label="委托人/当事人:"> {{form.caseWtr}}</el-descriptions-item>
        <el-descriptions-item label="诉讼阶段:">{{form.caseStatus}}</el-descriptions-item>
        <el-descriptions-item label="诉讼地位:">{{form.caseLawsuitStatus}}</el-descriptions-item>
        <el-descriptions-item label="受理法院:">{{form.caseCourt}}</el-descriptions-item>
        <el-descriptions-item label="代理费:">{{form.caseAgencyfee}}</el-descriptions-item>
        <el-descriptions-item label="已开票金额:">{{form.casePaidsal}}</el-descriptions-item>
        <el-descriptions-item label="已开票金额:">{{form.caseInvoiced}}</el-descriptions-item>
        <el-descriptions-item label="收费方式:">{{form.caseChargeWay}}</el-descriptions-item>
        <el-descriptions-item label="收案审批状态:">
          {{form.applyforApprovalStatus == '1' ? '审批中' : (form.applyforApprovalStatus == '2' ? '审批通过' : (form.applyforApprovalStatus == '3' ? '审批不通过' :'')) }}
        </el-descriptions-item>
        <el-descriptions-item label="备注:">{{form.caseRemarks}}</el-descriptions-item>
      </el-descriptions>
      <el-descriptions
        title=""
        direction="vertical"
        :column="6"
        :size="size"
        border
      >
        <el-descriptions-item label="序号">1</el-descriptions-item>
        <el-descriptions-item label="收函方/委托人">{{form.caseWtr}}</el-descriptions-item>
        <el-descriptions-item label="文书名称">{{form.typeName}}</el-descriptions-item>
        <el-descriptions-item label="审批状态">{{form.applyforApprovalStatus == '1' ? '审批中' : (form.applyforApprovalStatus == '2' ? '审批通过' : (form.applyforApprovalStatus == '3' ? '审批不通过' :'')) }}</el-descriptions-item>
        <el-descriptions-item label="操作" >{{form.applyforApprovalStatus == '1' ? '审批中：无法打印' : (form.applyforApprovalStatus == '2' ? '审批通过：可以打印' : (form.applyforApprovalStatus == '3' ? '审批不通过： 无法打印' :'')) }}</el-descriptions-item>
      </el-descriptions>
      <el-descriptions
        title="审批记录"
        direction="vertical"
        :column="4"
        :size="size"
        border
      >
        <el-descriptions-item label="序号">{{form.applyforApprovalStatus != '1' ? 1 :''}}</el-descriptions-item>
        <el-descriptions-item label="审批时间">{{form.applyforApprovalStatus != '1' ? form.caseSubtime :''}}</el-descriptions-item>
        <el-descriptions-item label="办理人">{{form.applyforApprovalStatus != '1' ? form.applyforRecipient :''}}</el-descriptions-item>
        <el-descriptions-item label="备注">{{form.applyforApprovalStatus != '1' ? form.caseRemarks :''}}</el-descriptions-item>
      </el-descriptions>
      <el-descriptions title="审批结果">
        <el-descriptions-item label="">{{form.applyforApprovalStatus == '1' ? '审批中' : (form.applyforApprovalStatus == '2' ? '审批通过' : (form.applyforApprovalStatus == '3' ? '审批不通过' :'')) }}</el-descriptions-item>
      </el-descriptions>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>


<style scoped>
.el-descriptions {
  margin-top: 20px;
}
</style>
<script>
import { listApplyfor,listApplyforxl, getApplyfor, delApplyfor, addApplyfor, updateApplyfor } from "@/api/ruoyi-act/applyfor";
import { getUserProfile } from "@/api/system/user";

export default {
  name: "Applyfor",
  data() {
    return {
      //下拉框数据源
      options:[],
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
    console.log("开始进行赋值");
    listApplyforxl().then(response => {
      this.options = response.rows;
      console.log("赋值完成的下拉框数据"+this.options);
    });
    

    getUserProfile().then(response => {
      this.user = response.data;
      this.queryParams.applyforRecipient=this.user.nickName;
      console.log("昵称："+this.queryParams.applyforRecipient);
      this.getList();
    });
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
        typeid: null,
        caseWtr: null
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
