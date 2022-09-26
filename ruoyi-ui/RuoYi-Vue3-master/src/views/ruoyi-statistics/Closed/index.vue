<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="承办律师" prop="empName">
        <el-input
          v-model="queryParams.empName"
          placeholder="请输入承办律师"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

    <el-form-item label="结案状态" prop="caseSettleStatus">
      <el-select v-model="queryParams.caseSettleStatus" placeholder="结案状态" >
          <el-option label="未结案" value="N" />
          <el-option label="已结案" value="Y" />
      </el-select>
    </el-form-item>

    <el-form-item label="档案归档" prop="caseRecord">
      <el-select v-model="queryParams.caseRecord" placeholder="档案归档" >
          <el-option label="未归档" value="1" />
          <el-option label="已归档" value="2" />
      </el-select>
    </el-form-item>

    <el-form-item label="收案时间" prop="collectionTime">
        <el-date-picker clearable
          v-model="queryParams.collectionTime1"
          type="date"
          value-format="YYYY-MM-DD">
        </el-date-picker>
        <el-date-picker clearable
          v-model="queryParams.collectionTime2"
          type="date"
          value-format="YYYY-MM-DD">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="mini" @click="handleQuery">搜索</el-button>
        <el-button size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          size="mini"
          @click="handleExport"
          v-hasPermi="['ruoyi-statistics:Closed:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="closedList" @selection-change="handleSelectionChange">
      <el-table-column label="案号" align="center" prop="caseNo" width="180" />
      <el-table-column label="案由" align="center" prop="caseCause" />
      <el-table-column label="收案日期" width="100" align="center" prop="collectionTime">
        <template #default="scope">
          <span>{{ parseTime(scope.row.collectionTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      
      <el-table-column label="委托人" align="center" prop="client" />
      <el-table-column label="对方当事人" width="100" align="center" prop="caseOppositeParties" />
      <el-table-column label="承办律师" align="center" prop="empName" />
      <el-table-column label="受理单位" align="center" prop="caseAccept" />
      <el-table-column label="标的(万元)" width="100" align="center" prop="caseLawsuitobj" />
      <el-table-column label="收费(万元)" width="100" align="center" prop="caseAgencyfee" />
      <el-table-column label="已付款(元)" width="100" align="center" prop="casePaidsal" />
      <el-table-column label="已开票(元)" width="100" align="center" prop="caseInvoiced" />

      <el-table-column label="结案日期" width="100" align="center" prop="caseApprovalEndtime">
        <template #default="scope">
          <span>{{ parseTime(scope.row.caseSubtime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结案方式" align="center" prop="caseSettleType" >
          <template v-slot="scope">
            <span v-if="scope.row.caseSettleType == 0">默认</span>
            <span v-if="scope.row.caseSettleType == 1">调解</span>
            <span v-if="scope.row.caseSettleType == 2">判决</span>
            <span v-if="scope.row.caseSettleType == 3">撤诉</span>
            <span v-if="scope.row.caseSettleType == 4">其他方式</span>
          </template>
      </el-table-column>
      <el-table-column label="归档状态" align="center" prop="caseRecord" >
        <template v-slot="scope">
            <span v-if="scope.row.caseRecord == 1">未归档</span>
            <span v-if="scope.row.caseRecord == 2">归档</span>
          </template>
      </el-table-column>
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
        standard: null,
        empName:null,
        collectionTime1:null,
        collectionTime2:null,
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
        standard: null,
        collectionTime1:null,
        collectionTime2:null,
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
