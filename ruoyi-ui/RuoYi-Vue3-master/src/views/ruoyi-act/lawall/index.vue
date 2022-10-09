<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="案号" prop="caseNo">
        <el-input
          v-model="queryParams.caseNo"
          placeholder="请输入案号"
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
      <el-form-item label="案件类型id 外键" prop="caseCaseTypeId">
        <el-select v-model="queryParams.caseCaseTypeId" placeholder="请选择案件类型id 外键" clearable>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>


    <el-table v-loading="loading" :data="lawallList" @selection-change="handleSelectionChange"> 
      <el-table-column label="案号" align="center" prop="caseNo" />
      <el-table-column label="对方当事人" align="center" prop="caseOppositeParties" />
      <el-table-column label="诉讼标的" align="center" prop="caseLawsuitobj" />
      <el-table-column label="承办律师" align="center" prop="caseAttorney" /> 
      <el-table-column label="代理费" align="center" prop="caseAgencyfee" />
      <el-table-column label="收费方式" align="center" prop="caseChargeWay" />
      <el-table-column label="案件冲突" align="center" prop="caseClash" />
      <el-table-column label="提交人" align="center" prop="caseSubmitter" />
      <el-table-column label="收案审批人" align="center" prop="systemApprovalId" />
      <el-table-column label="业务子类型" align="center" prop="caseTypeName" />
      <el-table-column label="审批状态:1:审批中2:审批通过3:审批不通过" align="center" prop="caseApproveStatus" />
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
            v-hasPermi="['ruoyi-act:lawall:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ruoyi-act:lawall:remove']"
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

    <!-- 添加或修改审批中心对话框 -->
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
        <el-descriptions-item label="是否指派:">{{form.caseAssign}}</el-descriptions-item>
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
    <!-- <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="案号" prop="caseNo">
          <el-input v-model="form.caseNo" placeholder="请输入案号" />
        </el-form-item>
        <el-form-item label="对方当事人" prop="caseOppositeParties">
          <el-input v-model="form.caseOppositeParties" placeholder="请输入对方当事人" />
        </el-form-item>
        <el-form-item label="对方当事人证件号" prop="caseOppositeCardnum">
          <el-input v-model="form.caseOppositeCardnum" placeholder="请输入对方当事人证件号" />
        </el-form-item>
        <el-form-item label="第三方" prop="caseThirdParty">
          <el-input v-model="form.caseThirdParty" placeholder="请输入第三方" />
        </el-form-item>
        <el-form-item label="嫌疑人" prop="caseSuspect">
          <el-input v-model="form.caseSuspect" placeholder="请输入嫌疑人" />
        </el-form-item>
        <el-form-item label="案件冲突" prop="caseClash">
          <el-input v-model="form.caseClash" placeholder="请输入案件冲突" />
        </el-form-item>
        <el-form-item label="案由" prop="caseCause">
          <el-input v-model="form.caseCause" placeholder="请输入案由" />
        </el-form-item>
        <el-form-item label="受理机关" prop="caseAccept">
          <el-input v-model="form.caseAccept" placeholder="请输入受理机关" />
        </el-form-item>
        <el-form-item label="诉讼标的" prop="caseLawsuitobj">
          <el-input v-model="form.caseLawsuitobj" placeholder="请输入诉讼标的" />
        </el-form-item>
        <el-form-item label="补助金额" prop="caseSubsidysal">
          <el-input v-model="form.caseSubsidysal" placeholder="请输入补助金额" />
        </el-form-item>
        <el-form-item label="代理费" prop="caseAgencyfee">
          <el-input v-model="form.caseAgencyfee" placeholder="请输入代理费" />
        </el-form-item>
        <el-form-item label="杂费" prop="caseProxysal">
          <el-input v-model="form.caseProxysal" placeholder="请输入杂费" />
        </el-form-item>
        <el-form-item label="办理地区" prop="caseTransactionRegion">
          <el-input v-model="form.caseTransactionRegion" placeholder="请输入办理地区" />
        </el-form-item>
        <el-form-item label="已付金额" prop="casePaidsal">
          <el-input v-model="form.casePaidsal" placeholder="请输入已付金额" />
        </el-form-item>
        <el-form-item label="未付金额" prop="caseUnpaidsal">
          <el-input v-model="form.caseUnpaidsal" placeholder="请输入未付金额" />
        </el-form-item>
        <el-form-item label="已开票金额" prop="caseInvoiced">
          <el-input v-model="form.caseInvoiced" placeholder="请输入已开票金额" />
        </el-form-item>
        <el-form-item label="未开票金额" prop="caseNotinvoiced">
          <el-input v-model="form.caseNotinvoiced" placeholder="请输入未开票金额" />
        </el-form-item>
        <el-form-item label="专属案号" prop="casePropernum">
          <el-input v-model="form.casePropernum" placeholder="请输入专属案号" />
        </el-form-item>
        <el-form-item label="受理法院" prop="caseCourt">
          <el-input v-model="form.caseCourt" placeholder="请输入受理法院" />
        </el-form-item>
        <el-form-item label="档案号" prop="caseFilenumber">
          <el-input v-model="form.caseFilenumber" placeholder="请输入档案号" />
        </el-form-item>
        <el-form-item label="相关文件">
          <file-upload v-model="form.caseCorrelationFile"/>
        </el-form-item>
        <el-form-item label="提交人" prop="caseSubmitter">
          <el-input v-model="form.caseSubmitter" placeholder="请输入提交人" />
        </el-form-item>
        <el-form-item label="提交时间" prop="caseSubtime">
          <el-date-picker clearable
            v-model="form.caseSubtime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择提交时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="案件回收" prop="caseRecycle">
          <el-input v-model="form.caseRecycle" placeholder="请输入案件回收" />
        </el-form-item>
        <el-form-item label="备注" prop="caseRemarks">
          <el-input v-model="form.caseRemarks" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="客户id 外键" prop="clientId">
          <el-input v-model="form.clientId" placeholder="请输入客户id 外键" />
        </el-form-item>
        <el-form-item label="承办律师" prop="caseAttorney">
          <el-input v-model="form.caseAttorney" placeholder="请输入承办律师" />
        </el-form-item>
        <el-form-item label="收费方式" prop="caseChargeWay">
          <el-input v-model="form.caseChargeWay" placeholder="请输入收费方式" />
        </el-form-item>
        <el-form-item label="收案审批人" prop="systemApprovalId">
          <el-input v-model="form.systemApprovalId" placeholder="请输入收案审批人" />
        </el-form-item>
        <el-form-item label="案件类型id 外键" prop="caseCaseTypeId">
          <el-select v-model="form.caseCaseTypeId" placeholder="请选择案件类型id 外键">
          </el-select>
        </el-form-item>
        <el-form-item label="案件文书id 外键" prop="caseSealApplyforId">
          <el-input v-model="form.caseSealApplyforId" placeholder="请输入案件文书id 外键" />
        </el-form-item>
        <el-form-item label="主办律师id 外键" prop="hrEmpId">
          <el-input v-model="form.hrEmpId" placeholder="请输入主办律师id 外键" />
        </el-form-item>
        <el-form-item label="收案时间" prop="collectionTime">
          <el-date-picker clearable
            v-model="form.collectionTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择收案时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="委托人" prop="caseParties">
          <el-input v-model="form.caseParties" placeholder="请输入委托人" />
        </el-form-item>
        <el-form-item label="风险费" prop="caseFxmoney">
          <el-input v-model="form.caseFxmoney" placeholder="请输入风险费" />
        </el-form-item>
        <el-form-item label="委托人" prop="caseWtr">
          <el-input v-model="form.caseWtr" placeholder="请输入委托人" />
        </el-form-item>
        <el-form-item label="结案审批时间" prop="caseApprovalEndtime">
          <el-date-picker clearable
            v-model="form.caseApprovalEndtime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择结案审批时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="归档号" prop="caseRecordNum">
          <el-input v-model="form.caseRecordNum" placeholder="请输入归档号" />
        </el-form-item>
        <el-form-item label="服务人次" prop="standard">
          <el-input v-model="form.standard" placeholder="请输入服务人次" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog> -->
  </div>
</template>

<script>
import { listLawall, getLawall, delLawall, addLawall, updateLawall } from "@/api/ruoyi-act/lawall";

export default {
  name: "Lawall",
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
      // 审批中心表格数据
      lawallList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        caseNo: null,
        caseApproveStatus: null,
        caseSubtime: null,
        caseCaseTypeId: null,
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
    /** 查询审批中心列表 */
    getList() {
      this.loading = true;
      listLawall(this.queryParams).then(response => {
        this.lawallList = response.rows;
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
      this.title = "添加审批中心";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getLawall(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改审批中心";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateLawall(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addLawall(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除审批中心编号为"' + ids + '"的数据项？').then(function() {
        return delLawall(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ruoyi-act/lawall/export', {
        ...this.queryParams
      }, `lawall_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
