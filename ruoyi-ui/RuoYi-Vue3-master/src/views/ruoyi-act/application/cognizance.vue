<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="cognizanceInfoForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="案号" prop="caseNo">
        <el-input
          v-model="queryParams.caseNo"
          placeholder="请输入案号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审批状态" prop="caseApproveStatus">
        <el-select  v-model="queryParams.caseApproveStatus" >
          <el-option label="" value="">请选择审批</el-option>  
          <el-option label="审批中" value="1">审批中</el-option>    
          <el-option label="审批通过" value="2">审批通过</el-option>
          <el-option label="审批不通过" value="3">审批不通过</el-option>      
        </el-select>
      </el-form-item>
      <el-form-item label="案件类型" prop="caseCaseTypeId">
        <el-select v-model="queryParams.caseCaseTypeId" class="m-2" placeholder="案件类型">
          <el-option label="" value="">请选择案件类型</el-option>
          <el-option
            v-for="item in options"
            :key="item.id"
            :label="item.caseTypeName"
            :value="item.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="提交时间" prop="caseSubtime">
        <el-date-picker clearable
          v-model="queryParams.caseSubtime"
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

    <el-table v-loading="loading" :data="applicationList" @selection-change="handleSelectionChange">
      <el-table-column label="案号" align="center" prop="caseNo" />
      <el-table-column label="案件类型" align="center" prop="caseTypeName" />
      <el-table-column label="收案审批状态" align="center" prop="caseApproveStatus">
        <template v-slot="scope">
          <span v-if="scope.row.caseApproveStatus == 1">审批中</span>
          <span v-if="scope.row.caseApproveStatus == 2">审批通过</span>
          <span v-if="scope.row.caseApproveStatus == 3">审批不通过</span>
        </template>
      </el-table-column>
      <el-table-column label="提交时间" align="center" prop="caseSubtime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.caseSubtime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ruoyi-act:application:edit']"
          >查看流程</el-button>
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

    <!-- 添加或修改我的申请对话框 -->
    <el-dialog :title="title" v-model="open" width="800px" append-to-body>
      <el-descriptions :title=form.caseTypeName >
        <el-descriptions-item label="案号:">{{form.caseNo}}</el-descriptions-item>
        <el-descriptions-item label="提交时间:">{{form.caseSubtime}}</el-descriptions-item>
        <el-descriptions-item label="案件类型:"> {{form.caseTypeName}}</el-descriptions-item>
        <el-descriptions-item label="委托人:">{{form.caseParties}}</el-descriptions-item>
        <el-descriptions-item label="第三方:">{{form.caseThirdParty}}</el-descriptions-item>
        <el-descriptions-item label="对方当事人:">{{form.caseOppositeParties}}</el-descriptions-item>
        <el-descriptions-item label="办理地区:">{{form.caseTransactionRegion}}</el-descriptions-item>
        <el-descriptions-item label="受理法院:">{{form.caseCourt}}</el-descriptions-item>
        <el-descriptions-item label="主办律师:">{{form.empName}}</el-descriptions-item>
        <el-descriptions-item label="诉讼标的:">{{form.caseLawsuitobj}}</el-descriptions-item>
        <el-descriptions-item label="代理费:">{{form.caseAgencyfee}}</el-descriptions-item>
        <el-descriptions-item label="杂费:">{{form.caseProxysal}}</el-descriptions-item>
        <el-descriptions-item label="收费方式:">{{form.caseChargeWay}}</el-descriptions-item>
        <el-descriptions-item label="补助金额:">{{form.caseSubsidysal}}</el-descriptions-item>
        <el-descriptions-item label="是否指派:">
          {{form.caseAssign == 'N' ? '否' : (form.caseAssign == 'Y' ? '是' : '') }}
        </el-descriptions-item>
        <el-descriptions-item label="收案审批状态:">
          {{form.caseApproveStatus == '1' ? '审批中' : (form.caseApproveStatus == '2' ? '审批通过' : (form.caseApproveStatus == '3' ? '审批不通过' :'')) }}
        </el-descriptions-item>
        <el-descriptions-item label="档案号:">{{form.caseFilenumber}}</el-descriptions-item>
        <el-descriptions-item label="专属案号:">{{form.casePropernum}}</el-descriptions-item>
        <el-descriptions-item label="收案审批人:">{{form.systemApprovalId}}</el-descriptions-item>
        <el-descriptions-item label="案由:">{{form.caseCause}}</el-descriptions-item>
        <el-descriptions-item label="备注:">{{form.caseRemarks}}</el-descriptions-item>
      </el-descriptions>
      <el-descriptions
        title="审批记录"
        direction="vertical"
        :column="4"
        :size="size"
        border
      >
        <el-descriptions-item label="序号">{{form.caseApproveStatus != '1' ? form.id :''}}</el-descriptions-item>
        <el-descriptions-item label="审批时间">{{form.caseApproveStatus != '1' ? form.caseApprovalEndtime :''}}</el-descriptions-item>
        <el-descriptions-item label="办理人">{{form.caseApproveStatus != '1' ? form.empName :''}}</el-descriptions-item>
        <el-descriptions-item label="备注">{{form.caseApproveStatus != '1' ? form.caseRemarks :''}}</el-descriptions-item>
      </el-descriptions>
      <el-descriptions title="审批结果">
        <el-descriptions-item label="">{{form.caseApproveStatus == '1' ? '审批中' : (form.caseApproveStatus == '2' ? '审批通过' : (form.caseApproveStatus == '3' ? '审批不通过' :'')) }}</el-descriptions-item>
        <el-descriptions-item label="结案审批时间:">{{form.caseApproveStatus != '1' ? form.caseApprovalEndtime :''}}</el-descriptions-item>
        <el-descriptions-item label="办理人:">{{form.caseApproveStatus != '1' ? form.empName :''}}</el-descriptions-item>
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
import { listApplication, listApplicationxl, getApplication, delApplication  } from "@/api/ruoyi-act/application";
import { getUserProfile } from "@/api/system/user";


export default {
  name: "Application",
  data() {
    return {
      //下拉框数据源
      options:[],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedCaseCaseType: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 我的申请表格数据
      applicationList: [],
      // 案件类型表格数据
      caseCaseTypeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        id : 1,
        pageNum: 1,
        pageSize: 10,
        caseNo: null,
        caseOppositeParties: null,
        caseOppositeCardnum: null,
        caseThirdParty: null,
        caseSuspect: null,
        caseClash: null,
        caseCause: null,
        caseAccept: null,
        caseLawsuitobj: null,
        caseSubsidy: null,
        caseSubsidysal: null,
        caseSettleStatus: null,
        casePlan: null,
        caseApproveStatus: null,
        caseAgencyfee: null,
        caseProxysal: null,
        caseTransactionRegion: null,
        caseAssign: null,
        casePaidsal: null,
        caseUnpaidsal: null,
        caseInvoiced: null,
        caseNotinvoiced: null,
        casePropernum: null,
        caseCourt: null,
        caseFilenumber: null,
        caseCorrelationFile: null,
        caseSubmitter: null,
        caseSubtime: null,
        caseRecycle: null,
        caseRemarks: null,
        clientId: null,
        caseAttorney: null,
        caseStatus: null,
        caseLawsuitStatus: null,
        caseChargeWay: null,
        systemApprovalId: null,
        caseCaseTypeId: null,
        caseSealApplyforId: null,
        hrEmpId: null,
        collectionTime: null,
        caseParties: null,
        caseFxmoney: null,
        caseWtr: null,
        caseContract: null,
        caseRecord: null,
        caseShare: null,
        caseSettleType: null,
        caseApprovalEndtime: null,
        caseRecordNum: null,
        standard: null,
        empName: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    //给下拉框数据源赋值
    console.log("开始进行赋值");
    listApplicationxl().then(response => {
      this.options = response.rows;
      console.log("赋值完成的下拉框数据"+this.options);
    });

    getUserProfile().then(response => {
      this.user = response.data;
      this.queryParams.caseSubmitter=this.user.nickName;
      // console.log("昵称："+this.queryParams.caseSubmitter);
      this.getList();
    });
  },
  methods: {
    /** 查询我的申请列表 */
    getList() {
      this.loading = true;
      listApplication(this.queryParams).then(response => {
        this.applicationList = response.rows;
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
        caseOppositeParties: null,
        caseOppositeCardnum: null,
        caseThirdParty: null,
        caseSuspect: null,
        caseClash: null,
        caseCause: null,
        caseAccept: null,
        caseLawsuitobj: null,
        caseSubsidy: null,
        caseSubsidysal: null,
        caseSettleStatus: "0",
        casePlan: null,
        caseApproveStatus: "0",
        caseAgencyfee: null,
        caseProxysal: null,
        caseTransactionRegion: null,
        caseAssign: null,
        casePaidsal: null,
        caseUnpaidsal: null,
        caseInvoiced: null,
        caseNotinvoiced: null,
        casePropernum: null,
        caseCourt: null,
        caseFilenumber: null,
        caseCorrelationFile: null,
        caseSubmitter: null,
        caseSubtime: null,
        caseRecycle: null,
        caseRemarks: null,
        clientId: null,
        caseAttorney: null,
        caseStatus: "0",
        caseLawsuitStatus: "0",
        caseChargeWay: null,
        systemApprovalId: null,
        caseCaseTypeId: null,
        caseSealApplyforId: null,
        hrEmpId: null,
        collectionTime: null,
        caseParties: null,
        caseFxmoney: null,
        caseWtr: null,
        caseContract: null,
        caseRecord: null,
        caseShare: null,
        caseSettleType: null,
        caseApprovalEndtime: null,
        caseRecordNum: null,
        standard: null
      };
      this.caseCaseTypeList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("cognizanceInfoForm");
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
      this.title = "添加我的申请";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getApplication(id).then(response => {
        this.form = response.data;
        this.caseCaseTypeList = response.data.caseCaseTypeList;
        this.open = true;
        this.title = "查看";
      });
    },
    /** 提交按钮 */
    // submitForm() {
    //   this.$refs["form"].validate(valid => {
    //     if (valid) {
    //       this.form.caseCaseTypeList = this.caseCaseTypeList;
    //       if (this.form.id != null) {
    //         updateApplication(this.form).then(response => {
    //           this.$modal.msgSuccess("修改成功");
    //           this.open = false;
    //           this.getList();
    //         });
    //       } else {
    //         addApplication(this.form).then(response => {
    //           this.$modal.msgSuccess("新增成功");
    //           this.open = false;
    //           this.getList();
    //         });
    //       }
    //     }
    //   });
    // },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除我的申请编号为"' + ids + '"的数据项？').then(function() {
        return delApplication(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 案件类型序号 */
    rowCaseCaseTypeIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 案件类型添加按钮操作 */
    handleAddCaseCaseType() {
      let obj = {};
      obj.caseTypeName = "";
      obj.typePid = "";
      obj.typeMoneyMin = "";
      obj.typeMoneyMax = "";
      this.caseCaseTypeList.push(obj);
    },
    /** 案件类型删除按钮操作 */
    handleDeleteCaseCaseType() {
      if (this.checkedCaseCaseType.length == 0) {
        this.$modal.msgError("请先选择要删除的案件类型数据");
      } else {
        const caseCaseTypeList = this.caseCaseTypeList;
        const checkedCaseCaseType = this.checkedCaseCaseType;
        this.caseCaseTypeList = caseCaseTypeList.filter(function(item) {
          return checkedCaseCaseType.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleCaseCaseTypeSelectionChange(selection) {
      this.checkedCaseCaseType = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ruoyi-act/application/export', {
        ...this.queryParams
      }, `application_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
