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
        <el-button type="primary" icon="Search"  @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="invoiceList" @selection-change="handleSelectionChange" border>
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="案号" align="center" prop="caseNo" />
      <el-table-column label="发票抬头" align="center" prop="cwInvoiceTitle" />
      <el-table-column label="发票金额" align="center" prop="cwInvoiceMoney" />
      <el-table-column label="申请日期" align="center" prop="cwApplicationDate" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.cwApplicationDate, '{yyyy}-{mm}-{dd}') }}</span>
        </template>
      </el-table-column>
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
        <template v-slot="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['finance:invoice:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['finance:invoice:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改我的发票对话框 -->
    <el-dialog :title="title" v-model="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="发票抬头" prop="cwInvoiceTitle">
          <el-input v-model="form.cwInvoiceTitle" placeholder="请输入发票抬头" />
        </el-form-item>
        <el-form-item label="发票金额" prop="cwInvoiceMoney">
          <el-input v-model="form.cwInvoiceMoney" placeholder="请输入发票金额" />
        </el-form-item>
        <el-form-item label="申请日期" prop="cwApplicationDate">
          <el-date-picker clearable
            v-model="form.cwApplicationDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择申请日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="开票日期" prop="cwInvoiceDate">
          <el-date-picker clearable
            v-model="form.cwInvoiceDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择开票日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="开票状态" prop="cwInvoiceStatus">
          <el-input v-model="form.cwInvoiceStatus" />
        </el-form-item>
        <el-form-item label="发票号" prop="cwInvoiceNumber">
          <el-input v-model="form.cwInvoiceNumber" placeholder="请输入发票号" />
        </el-form-item>
        <el-form-item label="开票项目" prop="cwInvoiceItem">
          <el-input v-model="form.cwInvoiceItem" placeholder="请输入开票项目" />
        </el-form-item>
        <el-form-item label="纳税人识别号" prop="cwTaxpayerIdentityNumber">
          <el-input v-model="form.cwTaxpayerIdentityNumber" placeholder="请输入纳税人识别号" />
        </el-form-item>
        <el-form-item label="基本开户银行" prop="cwBasicBankofdeposit">
          <el-input v-model="form.cwBasicBankofdeposit" placeholder="请输入基本开户银行" />
        </el-form-item>
        <el-form-item label="注册地址" prop="cwRegisteredAddress">
          <el-input v-model="form.cwRegisteredAddress" placeholder="请输入注册地址" />
        </el-form-item>
        <el-form-item label="固定电话" prop="cwFixedlineTelephone">
          <el-input v-model="form.cwFixedlineTelephone" placeholder="请输入固定电话" />
        </el-form-item>
        <el-form-item label="申请备注" prop="cwApplicationRemark">
          <el-input v-model="form.cwApplicationRemark" placeholder="请输入申请备注" />
        </el-form-item>
        <el-form-item label="开票备注" prop="cwInvoiceRemark">
          <el-input v-model="form.cwInvoiceRemark" placeholder="请输入开票备注" />
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
import { listInvoice, getInvoice, delInvoice, addInvoice, updateInvoice, listType } from "@/api/finance/invoice";

export default {
  name: "Invoice",
  data() {
    return {
      // 遮罩层
      loading: true,
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
      // 我的发票表格数据
      invoiceList: [],
      caseTypeList: [],
      // 归档表格数据
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
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询我的发票列表 */
    getList() {
      this.loading = true;
      listInvoice(this.queryParams).then(response => {
        this.invoiceList = response.rows;
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
      this.title = "添加我的发票";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getInvoice(id).then(response => {
        this.form = response.data;
        this.caseLawList = response.data.caseLawList;
        this.open = true;
        this.title = "修改我的发票";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.caseLawList = this.caseLawList;
          if (this.form.id != null) {
            updateInvoice(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInvoice(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除我的发票编号为"' + ids + '"的数据项？').then(function() {
        return delInvoice(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 归档序号 */
    rowCaseLawIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 归档添加按钮操作 */
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
    /** 归档删除按钮操作 */
    handleDeleteCaseLaw() {
      if (this.checkedCaseLaw.length == 0) {
        this.$modal.msgError("请先选择要删除的归档数据");
      } else {
        const caseLawList = this.caseLawList;
        const checkedCaseLaw = this.checkedCaseLaw;
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
      this.download('finance/invoice/export', {
        ...this.queryParams
      }, `invoice_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
