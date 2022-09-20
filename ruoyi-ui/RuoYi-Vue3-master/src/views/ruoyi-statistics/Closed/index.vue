<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!-- <el-form-item label="案号" prop="caseNo">
        <el-input
          v-model="queryParams.caseNo"
          placeholder="请输入案号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="对方当事人" prop="caseOppositeParties">
        <el-input
          v-model="queryParams.caseOppositeParties"
          placeholder="请输入对方当事人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="对方当事人证件号" prop="caseOppositeCardnum">
        <el-input
          v-model="queryParams.caseOppositeCardnum"
          placeholder="请输入对方当事人证件号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="第三方" prop="caseThirdParty">
        <el-input
          v-model="queryParams.caseThirdParty"
          placeholder="请输入第三方"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="嫌疑人" prop="caseSuspect">
        <el-input
          v-model="queryParams.caseSuspect"
          placeholder="请输入嫌疑人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="案件冲突" prop="caseClash">
        <el-input
          v-model="queryParams.caseClash"
          placeholder="请输入案件冲突"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="案由" prop="caseCause">
        <el-input
          v-model="queryParams.caseCause"
          placeholder="请输入案由"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="受理机关" prop="caseAccept">
        <el-input
          v-model="queryParams.caseAccept"
          placeholder="请输入受理机关"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="诉讼标的" prop="caseLawsuitobj">
        <el-input
          v-model="queryParams.caseLawsuitobj"
          placeholder="请输入诉讼标的"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="补助金额" prop="caseSubsidysal">
        <el-input
          v-model="queryParams.caseSubsidysal"
          placeholder="请输入补助金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="代理费" prop="caseAgencyfee">
        <el-input
          v-model="queryParams.caseAgencyfee"
          placeholder="请输入代理费"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="杂费" prop="caseProxysal">
        <el-input
          v-model="queryParams.caseProxysal"
          placeholder="请输入杂费"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="办理地区" prop="caseTransactionRegion">
        <el-input
          v-model="queryParams.caseTransactionRegion"
          placeholder="请输入办理地区"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="已付金额" prop="casePaidsal">
        <el-input
          v-model="queryParams.casePaidsal"
          placeholder="请输入已付金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="未付金额" prop="caseUnpaidsal">
        <el-input
          v-model="queryParams.caseUnpaidsal"
          placeholder="请输入未付金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="已开票金额" prop="caseInvoiced">
        <el-input
          v-model="queryParams.caseInvoiced"
          placeholder="请输入已开票金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="未开票金额" prop="caseNotinvoiced">
        <el-input
          v-model="queryParams.caseNotinvoiced"
          placeholder="请输入未开票金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="专属案号" prop="casePropernum">
        <el-input
          v-model="queryParams.casePropernum"
          placeholder="请输入专属案号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="受理法院" prop="caseCourt">
        <el-input
          v-model="queryParams.caseCourt"
          placeholder="请输入受理法院"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="档案号" prop="caseFilenumber">
        <el-input
          v-model="queryParams.caseFilenumber"
          placeholder="请输入档案号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="提交人" prop="caseSubmitter">
        <el-input
          v-model="queryParams.caseSubmitter"
          placeholder="请输入提交人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="提交时间" prop="caseSubtime">
        <el-date-picker clearable
          v-model="queryParams.caseSubtime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择提交时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="案件回收" prop="caseRecycle">
        <el-input
          v-model="queryParams.caseRecycle"
          placeholder="请输入案件回收"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="备注" prop="caseRemarks">
        <el-input
          v-model="queryParams.caseRemarks"
          placeholder="请输入备注"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="客户id 外键" prop="clientId">
        <el-input
          v-model="queryParams.clientId"
          placeholder="请输入客户id 外键"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="承办律师" prop="caseAttorney">
        <el-input
          v-model="queryParams.caseAttorney"
          placeholder="请输入承办律师"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收费方式" prop="caseChargeWay">
        <el-input
          v-model="queryParams.caseChargeWay"
          placeholder="请输入收费方式"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收案审批人" prop="systemApprovalId">
        <el-input
          v-model="queryParams.systemApprovalId"
          placeholder="请输入收案审批人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="案件类型id 外键" prop="caseCaseTypeId">
        <el-input
          v-model="queryParams.caseCaseTypeId"
          placeholder="请输入案件类型id 外键"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="案件文书id 外键" prop="caseSealApplyforId">
        <el-input
          v-model="queryParams.caseSealApplyforId"
          placeholder="请输入案件文书id 外键"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="主办律师id 外键" prop="hrEmpId">
        <el-input
          v-model="queryParams.hrEmpId"
          placeholder="请输入主办律师id 外键"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收案时间" prop="collectionTime">
        <el-date-picker clearable
          v-model="queryParams.collectionTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择收案时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="委托人" prop="caseParties">
        <el-input
          v-model="queryParams.caseParties"
          placeholder="请输入委托人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="风险费" prop="caseFxmoney">
        <el-input
          v-model="queryParams.caseFxmoney"
          placeholder="请输入风险费"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="委托人" prop="caseWtr">
        <el-input
          v-model="queryParams.caseWtr"
          placeholder="请输入委托人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="结案审批时间" prop="caseApprovalEndtime">
        <el-date-picker clearable
          v-model="queryParams.caseApprovalEndtime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择结案审批时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="归档号" prop="caseRecordNum">
        <el-input
          v-model="queryParams.caseRecordNum"
          placeholder="请输入归档号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="服务人次" prop="standard">
        <el-input
          v-model="queryParams.standard"
          placeholder="请输入服务人次"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ruoyi-statistics:Closed:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="closedList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="案号" align="center" prop="caseNo" width="180" />
      <el-table-column label="案由" align="center" prop="caseCause" />
      <el-table-column label="收案日期" align="center" prop="collectionTime">
        <template #default="scope">
          <span>{{ parseTime(scope.row.collectionTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      

      <el-table-column label="对方当事人" align="center" prop="caseOppositeParties" />

      <el-table-column label="受理单位" align="center" prop="caseAccept" />
      <el-table-column label="标的(万元)" align="center" prop="caseLawsuitobj" />
      <el-table-column label="收费(万元)" align="center" prop="caseAgencyfee" />
      <el-table-column label="已付款(元)" align="center" prop="casePaidsal" />
      <el-table-column label="已开票(元)" align="center" prop="caseInvoiced" />

      <el-table-column label="结案日期" align="center" prop="caseApprovalEndtime">
        <template #default="scope">
          <span>{{ parseTime(scope.row.caseSubtime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结案方式" align="center" prop="caseSettleType" />
      <el-table-column label="归档状态" align="center" prop="caseRecord" />
      <el-table-column label="归档号" align="center" prop="caseRecordNum" />
    </el-table>
    
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
import { listClosed, getClosed, delClosed, addClosed, updateClosed } from "@/api/ruoyi-statistics/Closed";

export default {
  name: "Closed",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedHrEmp: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 收结案统计表格数据
      closedList: [],
      // ${subTable.functionName}表格数据
      hrEmpList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
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
        standard: null
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
    /** 查询收结案统计列表 */
    getList() {
      this.loading = true;
      listClosed(this.queryParams).then(response => {
        this.closedList = response.rows;
        console.log(this.closedList);
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
      this.hrEmpList = [];
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
      this.title = "添加收结案统计";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getClosed(id).then(response => {
        this.form = response.data;
        this.hrEmpList = response.data.hrEmpList;
        this.open = true;
        this.title = "修改收结案统计";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.hrEmpList = this.hrEmpList;
          if (this.form.id != null) {
            updateClosed(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addClosed(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除收结案统计编号为"' + ids + '"的数据项？').then(function() {
        return delClosed(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** ${subTable.functionName}序号 */
    rowHrEmpIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** ${subTable.functionName}添加按钮操作 */
    handleAddHrEmp() {
      let obj = {};
      obj.empName = "";
      obj.empBirthdate = "";
      obj.empSex = "";
      obj.empWorkstatus = "";
      obj.empPaperstype = "";
      obj.empPapersnum = "";
      obj.empHigthEducation = "";
      obj.empHigthDegree = "";
      obj.empPosition = "";
      obj.hrNationId = "";
      obj.hrPoliticalId = "";
      obj.empMaritalsta = "";
      obj.empEntrancenum = "";
      obj.hrSpecialtyId = "";
      obj.hrPractiseId = "";
      obj.empPracnum = "";
      obj.empPracdate = "";
      obj.empPracsite = "";
      obj.hrCertifyId = "";
      obj.empCertifynum = "";
      obj.empGainway = "";
      obj.empGainsite = "";
      obj.empIntroduce = "";
      obj.empEntrydate = "";
      obj.empSocialNum = "";
      obj.empContStartdate = "";
      obj.empContEnddate = "";
      obj.empInsured = "";
      obj.empPhone = "";
      obj.empPicture = "";
      obj.empSite = "";
      obj.empContactPhone = "";
      obj.empEmail = "";
      obj.empPostcode = "";
      obj.empQq = "";
      obj.empWechat = "";
      this.hrEmpList.push(obj);
    },
    /** ${subTable.functionName}删除按钮操作 */
    handleDeleteHrEmp() {
      if (this.checkedHrEmp.length == 0) {
        this.$modal.msgError("请先选择要删除的${subTable.functionName}数据");
      } else {
        const hrEmpList = this.hrEmpList;
        const checkedHrEmp = this.checkedHrEmp;
        this.hrEmpList = hrEmpList.filter(function(item) {
          return checkedHrEmp.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleHrEmpSelectionChange(selection) {
      this.checkedHrEmp = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ruoyi-statistics/Closed/export', {
        ...this.queryParams
      }, `Closed_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
