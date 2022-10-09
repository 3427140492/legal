<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item  prop="serchText">
        <el-input
          style="width: 150px;"
          v-model="queryParams.serchText"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item prop="SearchType">
        <el-select v-model="queryParams.SearchType" style="width: 120px;">
        <el-option label="案号" value="1"/>
          <el-option label="发票抬头" value="2"/>
          <el-option label="办案律师" value="3"/>
        </el-select>
      </el-form-item>

      <el-form-item prop="cwInvoiceStatus">
        <el-select v-model="queryParams.cwInvoiceStatus" style="width: 120px;" placeholder="开票状态">
        <el-option label="已开" value="Y"/>
          <el-option label="未开" value="N"/>
        </el-select>
      </el-form-item>

      <el-form-item prop="cwInvoiceType">
        <el-select v-model="queryParams.cwInvoiceType" style="width: 160px;" placeholder="开票类型">
          <el-option label="增值税专用发票" value="1"/>
          <el-option label="增值税普通发票" value="2"/>
          <el-option label="通用机打发票" value="3"/>
        </el-select>
      </el-form-item>

      <el-form-item prop="cwInvoiceItem">
        <el-select v-model="queryParams.cwInvoiceItem" style="width: 160px;" placeholder="开票项目">
          <el-option label="律师代理费" value="1"/>
          <el-option label="法律顾问费" value="2"/>
          <el-option label="咨询费" value="3"/>
          <el-option label="法务托管费" value="4"/>
          <el-option label="非诉" value="5"/>
          <el-option label="其他(备注中填写)" value="5"/>
        </el-select>
      </el-form-item>

      <el-form-item prop="cctid">
        <el-select v-model="queryParams.cctid" placeholder="请选择案件类型" style="width:150px;">
        <el-option v-for="t in caseTypeList" :key="t.cctid" :label="t.caseTypeName" :value="t.cctid" />
        </el-select>
      </el-form-item>

      <el-form-item label="开票日期" prop="cwInvoiceDate">
        <el-date-picker clearable
          v-model="queryParams.cwInvoiceDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="">
        </el-date-picker>
      </el-form-item>

      <el-form-item label="" prop="overTime">
        <el-date-picker clearable
          v-model="queryParams.overTime"
          type="date"
          value-format="YYYY-MM-DD"
          >
        </el-date-picker>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="kinvoiceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="案号" align="center" prop="caseNo" />
      <el-table-column label="办案律师" align="center" prop="empName" />
      <el-table-column label="发票抬头" align="center" prop="cwInvoiceTitle" />
      <el-table-column label="代理费(元)" align="center" prop="caseAgencyfee" />
      <el-table-column label="发票金额" align="center" prop="cwInvoiceMoney" />
      <el-table-column label="已开发票金额(元)" align="center" prop="caseInvoiced" />
      <el-table-column label="已付款(元)" align="center" prop="casePaidsal" />
      <el-table-column label="开票日期" align="center" prop="cwInvoiceDate" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.cwInvoiceDate, '{yyyy}-{mm}-{dd}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="开票状态" align="center" prop="cwInvoiceStatus">
        <template v-slot="scope">
          <span v-if="scope.row.cwInvoiceStatus == 'Y'">已开</span>
          <span v-if="scope.row.cwInvoiceStatus == 'N'">未开</span>
        </template>
      </el-table-column>

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
                <el-dropdown-item @click="ViewDetail(scope.row.id)">查看详细信息</el-dropdown-item>
                <el-dropdown-item @click="handleDelete(scope.row)">删除</el-dropdown-item>
                </el-dropdown-menu>
            </template>
          </el-dropdown>
        </template>
      </el-table-column>
      
    </el-table>

    <el-dialog v-model="this.ViewDetails" title="发票详情">
      <el-descriptions title="信息" :column="3" border>
        <el-descriptions-item label="案号" align="center">{{data.caseNo}}</el-descriptions-item>
        <el-descriptions-item label="办案律师" align="center">{{data.empName}}</el-descriptions-item>
        <el-descriptions-item label="发票抬头" align="center">{{data.cwInvoiceTitle}}</el-descriptions-item>
        <el-descriptions-item label="代理费(元)" align="center">{{data.caseAgencyfee}}</el-descriptions-item>
        <el-descriptions-item label="发票金额" align="center">{{data.cwInvoiceMoney}}</el-descriptions-item>
        <el-descriptions-item label="已开发票金额(元)" align="center">{{data.caseInvoiced}}</el-descriptions-item>
        <el-descriptions-item label="已付款(元)" align="center">{{data.casePaidsal}}</el-descriptions-item>
        <el-descriptions-item label="开票日期" align="center">{{data.cwInvoiceDate}}</el-descriptions-item>
        <el-descriptions-item label="开票状态" align="center">{{data.cwInvoiceStatus == 'Y'?'已开':'未开'}}</el-descriptions-item>
      </el-descriptions>
    </el-dialog>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import { listKinvoice, getKinvoice, delKinvoice, addKinvoice, updateKinvoice, listType } from "@/api/finance/kinvoice";

export default {
  name: "Kinvoice",
  dicts: ['case_case_type_id'],
  data() {
    return {
      // 遮罩层
      loading: true,
      //发票信息
      ViewDetails: false,
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
      // 开具发票表格数据
      kinvoiceList: [],
      caseTypeList: [],
      // 审批中心表格数据
      caseLawList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cwInvoiceTitle: null,
        cwInvoiceMoney: null,
        cwApplicationDate: null,
        cwInvoiceDate: null,
        cwInvoiceStatus: null,
        cwInvoiceNumber: null,
        cwOpenType: null,
        cwInvoiceType: null,
        cwInvoiceItem: null,
        cwTaxpayerIdentityNumber: null,
        cwBasicBankofdeposit: null,
        cwRegisteredAddress: null,
        cwFixedlineTelephone: null,
        recordFileId: null,
        caseLawId: null,
        cwApplicationRemark: null,
        cwInvoiceRemark: null,
        cwAccountNumber: null,
        cctid: null,
        overTime: null,
        empName: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      data: {}
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询开具发票列表 */
    getList() {
      this.loading = true;
      listKinvoice(this.queryParams).then(response => {
        this.kinvoiceList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      listType().then(response => {
        this.caseTypeList = response.rows;
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
        cwInvoiceTitle: null,
        cwInvoiceMoney: null,
        cwApplicationDate: null,
        cwInvoiceDate: null,
        cwInvoiceStatus: "0",
        cwInvoiceNumber: null,
        cwOpenType: null,
        cwInvoiceType: null,
        cwInvoiceItem: null,
        cwTaxpayerIdentityNumber: null,
        cwBasicBankofdeposit: null,
        cwRegisteredAddress: null,
        cwFixedlineTelephone: null,
        recordFileId: null,
        caseLawId: null,
        cwApplicationRemark: null,
        cwInvoiceRemark: null,
        cwAccountNumber: null
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
      this.title = "添加开具发票";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getKinvoice(id).then(response => {
        this.form = response.data;
        this.caseLawList = response.data.caseLawList;
        this.open = true;
        this.title = "修改开具发票";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.caseLawList = this.caseLawList;
          if (this.form.id != null) {
            updateKinvoice(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addKinvoice(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除开具发票编号为"' + ids + '"的数据项？').then(function() {
        return delKinvoice(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    ViewDetail(id) {
      this.ViewDetails = true;
      getKinvoice(id).then(response => { 
        this.data.empName = response.data.empName;
        this.data.caseNo = response.data.caseNo;
        this.data.caseAgencyfee = response.data.caseAgencyfee;
        this.data.cwInvoiceTitle = response.data.cwInvoiceTitle;
        this.data.cwInvoiceMoney = response.data.cwInvoiceMoney;
        this.data.caseInvoiced = response.data.caseInvoiced;
        this.data.casePaidsal = response.data.casePaidsal;
        this.data.cwInvoiceDate = response.data.cwInvoiceDate;
        this.data.cwInvoiceStatus = response.data.cwInvoiceStatus;
      })
    },
	/** 审批中心序号 */
    rowCaseLawIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 审批中心添加按钮操作 */
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
    /** 审批中心删除按钮操作 */
    handleDeleteCaseLaw() {
      if (this.checkedCaseLaw.length == 0) {
        this.$modal.msgError("请先选择要删除的审批中心数据");
      } else {
        const caseLawList = this.caseLawList;
        const checkedCaseLaw = this.checkedCaseLaw
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
      this.download('finance/kinvoice/export', {
        ...this.queryParams
      }, `kinvoice_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
