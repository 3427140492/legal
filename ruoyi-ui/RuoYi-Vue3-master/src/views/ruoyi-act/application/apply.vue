<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="applyInfoForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="案号" prop="caseNo">
        <el-input
          v-model="queryParams.caseNo"
          placeholder="请输入案号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审批状态" prop="status">
        <el-select  v-model="queryParams.status" >
          <el-option label="" value="">请选择审批</el-option>  
          <el-option label="审批中" value="1">审批中</el-option>    
          <el-option label="审批通过" value="2">审批通过</el-option>
          <el-option label="审批不通过" value="3">审批不通过</el-option>      
        </el-select>
      </el-form-item>
      
      <el-form-item label="审批状态" prop="type">
        <el-select  v-model="queryParams.type" >
          <el-option label="" value="">请选择业务子类型</el-option>  
          <el-option label="费用减免申请" value="费用减免申请">费用减免申请</el-option>    
          <el-option label="退款申请" value="退款申请">退款申请</el-option>
          <el-option label="结案申请" value="结案申请">结案申请</el-option>      
        </el-select>
      </el-form-item>
      <el-form-item label="提交时间" prop="createtime">
        <el-date-picker clearable
          v-model="queryParams.createtime"
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


    <el-table v-loading="loading" :data="applyList" @selection-change="handleSelectionChange">
      <el-table-column label="案号" align="center" prop="caseNo" />
      <el-table-column label="提交人" align="center" prop="submiter" />
      <el-table-column label="业务子类型" align="center" prop="type" />
      <el-table-column label="审批状态" align="center" prop="status" >
        <template v-slot="scope">
          <span v-if="scope.row.status == 1">审批中</span>
          <span v-if="scope.row.status == 2">审批通过</span>
          <span v-if="scope.row.status == 3">审批不通过</span>
        </template>
      </el-table-column>
      <el-table-column label="提交时间" align="center" prop="createtime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.createtime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ruoyi-act:apply:edit']"
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

    <!-- 添加或修改我的申请对话框 -->
    <el-dialog :title="title" v-model="open" width="800px" append-to-body>
      <el-descriptions :title=form.type >
        <el-descriptions-item label="案号:">{{form.caseNo}}</el-descriptions-item>
        <el-descriptions-item label="委托人:">{{form.wtr}}</el-descriptions-item>
        <el-descriptions-item label="提交人:">{{form.submiter}}</el-descriptions-item>
        <el-descriptions-item label="减免金额:">{{form.decreaseMoney}}</el-descriptions-item>
        <el-descriptions-item label="申请日期:">{{form.createtime}}</el-descriptions-item>
        <el-descriptions-item label="减免事由:">{{form.reason}}</el-descriptions-item>
        <el-descriptions-item label="退费申请单:">{{form.empName}}</el-descriptions-item>
        <el-descriptions-item label="备注:">{{form.caseRemarks}}</el-descriptions-item>
      </el-descriptions>
      <!-- <el-table :data="ActHiCommentList">
        <el-table-column label="提交人" align="center" prop="time" />
        <el-table-column label="办理人" align="center" prop="userId" />
        <el-table-column label="备注" align="center" prop="message" />
      </el-table> -->
      <el-descriptions
        title="审批记录"
        direction="vertical"
        :column="4"
        :size="size"
        border
      >
        <el-descriptions-item label="序号">{{form.status != '1' ? form.id :''}}</el-descriptions-item>
        <el-descriptions-item label="审批时间">{{form.status != '1' ? form.caseApprovalEndtime :''}}</el-descriptions-item>
        <el-descriptions-item label="办理人">{{form.status != '1' ? form.empName :''}}</el-descriptions-item>
        <el-descriptions-item label="备注">{{form.status != '1' ? form.caseRemarks :''}}</el-descriptions-item>
      </el-descriptions>
      <el-descriptions title="审批结果">
        <el-descriptions-item label="">{{form.status == '1' ? '审批中' : (form.status == '2' ? '审批通过' : (form.status == '3' ? '审批不通过' :'')) }}</el-descriptions-item>
        <el-descriptions-item label="结案审批时间:">{{form.status != '1' ? form.caseApprovalEndtime :''}}</el-descriptions-item>
        <el-descriptions-item label="办理人:">{{form.status != '1' ? form.empName :''}}</el-descriptions-item>
      </el-descriptions>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listApply, getApply, delApply, addApply, updateApply } from "@/api/ruoyi-act/apply";
import { getUserProfile } from "@/api/system/user";

export default {
  name: "Apply",
  data() {
    return {
      //下拉框数据源
      // options:[],
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
      // 业务申请表格数据
      applyList: [],
      //业务申请表格数据
      ActHiCommentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        caseNo: null,
        submiter: null,
        type: null,
        leader: null,
        createtime: null,
        status: null,
        wid: null,
        file: null,
        wtr: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    //传值
    getUserProfile().then(response => {
      this.user = response.data;
      this.queryParams.submiter=this.user.nickName;
      console.log("昵称："+this.queryParams.submiter);

      this.getList();
    });
  },
  methods: {
    /** 查询业务申请列表 */
    getList() {
      this.loading = true;
      listApply(this.queryParams).then(response => {
        this.applyList = response.rows;
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
        submiter: null,
        type: null,
        leader: null,
        createtime: null,
        status: 0,
        wid: null,
        remark: null,
        file: null,
        wtr: null
      };
      this.resetForm("form");
    },
    /** 查询业务申请回显表 */
    getListhxb() {
      
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("applyInfoForm");
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
      this.title = "添加业务申请";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getApply(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "流程查看";
      });
      /** 查询业务申请回显表 */
      // selapphxb(this.queryParams).then(response => {
      //   console.log(response.rows);
      //   this.ActHiCommentList = response.rows;
      // });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateApply(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addApply(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除业务申请编号为"' + ids + '"的数据项？').then(function() {
        return delApply(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ruoyi-act/apply/export', {
        ...this.queryParams
      }, `apply_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
