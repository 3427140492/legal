<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item prop="SearchStr">
        <el-input
          v-model="queryParams.SearchStr"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item prop="SearchType">
        <el-select v-model="queryParams.SearchType">
        <el-option label="案号搜索" value="1"/>
          <el-option label="上传人搜索" value="2"/>
          <el-option label="归档文件搜索" value="3"/>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

   

    <el-table v-loading="loading" :data="documentList" @selection-change="handleSelectionChange" border >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="案号" align="center" prop="caseNo" />
      <el-table-column label="归档文件" align="center" prop="documentFile" />
      <el-table-column label="上传人" align="center" prop="caseSubmitter" />
      <el-table-column label="最后更新日期" align="center" prop="upddate" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['record:document:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['record:document:remove']"
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

    <!-- 添加或修改全所电子归档对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="律师ID" prop="caseLawId">
          <el-input v-model="form.caseLawId" placeholder="请输入律师ID" />
        </el-form-item>
        <el-form-item label="用户ID" prop="systemUserId">
          <el-input v-model="form.systemUserId" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="最后更新日期" prop="upddate">
          <el-input v-model="form.upddate" placeholder="请输入最后更新日期" />
        </el-form-item>
        <el-form-item label="${comment}" prop="folderId">
          <el-input v-model="form.folderId" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="归档文件" prop="documentFile">
          <el-input v-model="form.documentFile" placeholder="请输入归档文件" />
        </el-form-item>
        <el-divider content-position="center">${subTable.functionName}信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddCaseLaw">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteCaseLaw">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="caseLawList" :row-class-name="rowCaseLawIndex" @selection-change="handleCaseLawSelectionChange" ref="caseLaw">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="案号" prop="caseNo" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseNo" placeholder="请输入案号" />
            </template>
          </el-table-column>
          <el-table-column label="对方当事人" prop="caseOppositeParties" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseOppositeParties" placeholder="请输入对方当事人" />
            </template>
          </el-table-column>
          <el-table-column label="对方当事人证件号" prop="caseOppositeCardnum" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseOppositeCardnum" placeholder="请输入对方当事人证件号" />
            </template>
          </el-table-column>
          <el-table-column label="第三方" prop="caseThirdParty" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseThirdParty" placeholder="请输入第三方" />
            </template>
          </el-table-column>
          <el-table-column label="嫌疑人" prop="caseSuspect" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseSuspect" placeholder="请输入嫌疑人" />
            </template>
          </el-table-column>
          <el-table-column label="案件冲突" prop="caseClash" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseClash" placeholder="请输入案件冲突" />
            </template>
          </el-table-column>
          <el-table-column label="案由" prop="caseCause" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseCause" placeholder="请输入案由" />
            </template>
          </el-table-column>
          <el-table-column label="受理机关" prop="caseAccept" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseAccept" placeholder="请输入受理机关" />
            </template>
          </el-table-column>
          <el-table-column label="诉讼标的" prop="caseLawsuitobj" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseLawsuitobj" placeholder="请输入诉讼标的" />
            </template>
          </el-table-column>
          <el-table-column label="补助金额" prop="caseSubsidysal" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseSubsidysal" placeholder="请输入补助金额" />
            </template>
          </el-table-column>
          <el-table-column label="结案情况Y?N" prop="caseSettleStatus" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.caseSettleStatus" placeholder="请选择结案情况Y?N">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="收案审批状态:1:审批中2:审批通过3:审批不通过" prop="caseApproveStatus" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.caseApproveStatus" placeholder="请选择收案审批状态:1:审批中2:审批通过3:审批不通过">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="代理费" prop="caseAgencyfee" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseAgencyfee" placeholder="请输入代理费" />
            </template>
          </el-table-column>
          <el-table-column label="杂费" prop="caseProxysal" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseProxysal" placeholder="请输入杂费" />
            </template>
          </el-table-column>
          <el-table-column label="办理地区" prop="caseTransactionRegion" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseTransactionRegion" placeholder="请输入办理地区" />
            </template>
          </el-table-column>
          <el-table-column label="已付金额" prop="casePaidsal" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.casePaidsal" placeholder="请输入已付金额" />
            </template>
          </el-table-column>
          <el-table-column label="未付金额" prop="caseUnpaidsal" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseUnpaidsal" placeholder="请输入未付金额" />
            </template>
          </el-table-column>
          <el-table-column label="已开票金额" prop="caseInvoiced" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseInvoiced" placeholder="请输入已开票金额" />
            </template>
          </el-table-column>
          <el-table-column label="未开票金额" prop="caseNotinvoiced" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseNotinvoiced" placeholder="请输入未开票金额" />
            </template>
          </el-table-column>
          <el-table-column label="专属案号" prop="casePropernum" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.casePropernum" placeholder="请输入专属案号" />
            </template>
          </el-table-column>
          <el-table-column label="受理法院" prop="caseCourt" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseCourt" placeholder="请输入受理法院" />
            </template>
          </el-table-column>
          <el-table-column label="档案号" prop="caseFilenumber" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseFilenumber" placeholder="请输入档案号" />
            </template>
          </el-table-column>
          <el-table-column label="提交人" prop="caseSubmitter" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseSubmitter" placeholder="请输入提交人" />
            </template>
          </el-table-column>
          <el-table-column label="提交时间" prop="caseSubtime" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.caseSubtime" type="date" value-format="yyyy-MM-dd" placeholder="请选择提交时间" />
            </template>
          </el-table-column>
          <el-table-column label="案件回收" prop="caseRecycle" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseRecycle" placeholder="请输入案件回收" />
            </template>
          </el-table-column>
          <el-table-column label="备注" prop="caseRemarks" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseRemarks" placeholder="请输入备注" />
            </template>
          </el-table-column>
          <el-table-column label="客户id 外键" prop="clientId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.clientId" placeholder="请输入客户id 外键" />
            </template>
          </el-table-column>
          <el-table-column label="承办律师" prop="caseAttorney" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseAttorney" placeholder="请输入承办律师" />
            </template>
          </el-table-column>
          <el-table-column label="诉讼阶段" prop="caseStatus" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.caseStatus" placeholder="请选择诉讼阶段">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="诉讼地位" prop="caseLawsuitStatus" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.caseLawsuitStatus" placeholder="请选择诉讼地位">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="收费方式" prop="caseChargeWay" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseChargeWay" placeholder="请输入收费方式" />
            </template>
          </el-table-column>
          <el-table-column label="收案审批人" prop="systemApprovalId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.systemApprovalId" placeholder="请输入收案审批人" />
            </template>
          </el-table-column>
          <el-table-column label="案件类型id 外键" prop="caseCaseTypeId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseCaseTypeId" placeholder="请输入案件类型id 外键" />
            </template>
          </el-table-column>
          <el-table-column label="案件文书id 外键" prop="caseSealApplyforId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseSealApplyforId" placeholder="请输入案件文书id 外键" />
            </template>
          </el-table-column>
          <el-table-column label="主办律师id 外键" prop="hrEmpId" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.hrEmpId" placeholder="请输入主办律师id 外键" />
            </template>
          </el-table-column>
          <el-table-column label="收案时间" prop="collectionTime" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.collectionTime" type="date" value-format="yyyy-MM-dd" placeholder="请选择收案时间" />
            </template>
          </el-table-column>
          <el-table-column label="委托人" prop="caseParties" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseParties" placeholder="请输入委托人" />
            </template>
          </el-table-column>
          <el-table-column label="风险费" prop="caseFxmoney" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseFxmoney" placeholder="请输入风险费" />
            </template>
          </el-table-column>
          <el-table-column label="委托人" prop="caseWtr" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseWtr" placeholder="请输入委托人" />
            </template>
          </el-table-column>
          <el-table-column label="结案方式(0=默认，1=调解，2=判决，3=撤诉，4=其他方式)" prop="caseSettleType" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.caseSettleType" placeholder="请选择结案方式(0=默认，1=调解，2=判决，3=撤诉，4=其他方式)">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="结案审批时间" prop="caseApprovalEndtime" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.caseApprovalEndtime" type="date" value-format="yyyy-MM-dd" placeholder="请选择结案审批时间" />
            </template>
          </el-table-column>
          <el-table-column label="归档号" prop="caseRecordNum" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.caseRecordNum" placeholder="请输入归档号" />
            </template>
          </el-table-column>
          <el-table-column label="服务人次" prop="standard" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.standard" placeholder="请输入服务人次" />
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
import { listDocument, getDocument, delDocument, addDocument, updateDocument } from "@/api/record/document";

export default {
  name: "Document",
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
      // 全所电子归档表格数据
      documentList: [],
      // ${subTable.functionName}表格数据
      caseLawList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        SearchStr: null,
        SearchType:null
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
    /** 查询全所电子归档列表 */
    getList() {
      this.loading = true;
      listDocument(this.queryParams).then(response => {
        this.documentList = response.rows;
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
        caseLawId: null,
        systemUserId: null,
        upddate: null,
        folderId: null,
        documentFile: null
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
      this.title = "添加全所电子归档";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getDocument(id).then(response => {
        this.form = response.data;
        this.caseLawList = response.data.caseLawList;
        this.open = true;
        this.title = "修改全所电子归档";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.caseLawList = this.caseLawList;
          if (this.form.id != null) {
            updateDocument(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDocument(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除全所电子归档编号为"' + ids + '"的数据项？').then(function() {
        return delDocument(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** ${subTable.functionName}序号 */
    rowCaseLawIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** ${subTable.functionName}添加按钮操作 */
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
    /** ${subTable.functionName}删除按钮操作 */
    handleDeleteCaseLaw() {
      if (this.checkedCaseLaw.length == 0) {
        this.$modal.msgError("请先选择要删除的${subTable.functionName}数据");
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
      this.download('record/document/export', {
        ...this.queryParams
      }, `document_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
