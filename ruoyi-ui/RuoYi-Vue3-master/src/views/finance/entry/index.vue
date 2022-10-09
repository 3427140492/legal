<template>
  <div class="app-container">
  <el-form ref="form" :model="form" :rules="rules">
    <el-row style="margin-left:15px;">
        <el-col :span="12">
            <el-form-item>
                <label><span style="color:red;">*</span>收支日期：</label>
            </el-form-item>
            <el-form-item  prop="cwEnteringdate" style="width:450px;">
              <el-date-picker clearable v-model="form.cwEnteringdate" value-format="YYYY-MM-DD"  style="width:450px;">
              </el-date-picker>
            </el-form-item>
        </el-col>
        <el-col :span="12">
            <el-form-item>
                <label><span style="color:red;">*</span>收支金额：</label>
            </el-form-item>
            <el-form-item prop="cwInoutMoney" style="width:450px;">
                <el-input v-model="form.cwInoutMoney"  />
            </el-form-item>
        </el-col>
    </el-row>

    <el-row style="margin-left:15px;">
        <el-col :span="12">
            <el-form-item>
              <label><span style="color:red;">*</span>收支人：</label>
            </el-form-item>
            <el-form-item  prop="cwInoutPerson">
              <el-select v-model="form.cwInoutPerson" style="width:450px;">
                <el-option label="刘爽" value="1" />
                <el-option label="王兰" value="2" />
              </el-select>
            </el-form-item>
        </el-col>
        <el-col :span="12">
            <el-form-item>
                <label><span style="color:red;">*</span>收支类别：</label>
            </el-form-item>
            <el-form-item prop="cwInoutTypeId">
                <el-tree-select placeholder="请选择收支类别" v-model="form.cwInoutTypeId" :data="cwInoutTypeListSelect" :props="{ value: 'id', label: 'cwName', children: 'children' }" :render-after-expand="false" clearable style="width: 450px;"/> 
            </el-form-item>
        </el-col>
    </el-row>

    <el-row style="margin-left:15px;">
        <el-col :span="12">
            <el-form-item>
                <label><span style="color:red;">*</span>付款人：</label>
            </el-form-item>
            <el-form-item  prop="cwPayer" style="width:450px;">
              <el-input v-model="form.cwPayer"  />
            </el-form-item>
        </el-col>
        <el-col :span="12">
            <el-form-item>
                <label><span style="color:red;">*</span>支付方式：</label>
            </el-form-item>
            <el-form-item prop="cwPayType">
                <el-select v-model="form.cwPayType" placeholder="请选择支付方式" style="width:450px;">
                    <el-option label="转账支票" value="1" />
                    <el-option label="电汇" value="2" />
                    <el-option label="网银" value="3" />
                    <el-option label="Pos机" value="4" />
                    <el-option label="现金" value="5" />
                </el-select>
            </el-form-item>
        </el-col>
    </el-row>

    <el-row style="margin-left:15px;">
        <el-col :span="12">
            <el-form-item>
                <label>备注：</label>
            </el-form-item>
            <el-form-item  prop="cwRemark" style="width:1100px;">
                <textarea rows="5" cols="90" class="form-control" style="width:1100px;" v-model="form.cwRemark"></textarea>
            </el-form-item>
        </el-col>
    </el-row>
  </el-form>

    <div slot="footer" class="dialog-footer" style="text-align:center;">
        <el-button type="primary" @click="submitForm">确 定</el-button>
    </div>

  </div>
</template>

<script>
import { listPayments, getPayments, delPayments, addPayments, updatePayments, cwInoutlistType, listType } from "@/api/finance/payments";
export default {
  name: "Payments",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedCwInoutType: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 全所收支明细表格数据
      paymentsList: [],
      caseTypeList: [],
      // ${subTable.functionName}表格数据
      cwInoutTypeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cwInoutTypeId: null,
        cwInoutDate: null,
        cwInoutPerson: null,
        cwInoutMoney: null,
        cwPayType: null,
        caseLawId: null,
        cwEnteringdate: null,
        cwRemark: null,
        cwPayer: null,
        casePaidsal: null,
        cwName: null,
        cctid: null,
        overTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      cwInoutTypeListSelect: []
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询全所收支明细列表 */
    getList() {
      this.loading = true;
      listPayments(this.queryParams).then(response => {
        this.paymentsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      cwInoutlistType().then(response => {//树形菜单
        this.cwInoutTypeListSelect = response.rows;
      });
    },

    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加全所收支明细";
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.cwInoutTypeList = this.cwInoutTypeList;
          if (this.form.id != null) {
            updatePayments(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPayments(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** ${subTable.functionName}删除按钮操作 */
    handleDeleteCwInoutType() {
      if (this.checkedCwInoutType.length == 0) {
        this.$modal.msgError("请先选择要删除的${subTable.functionName}数据");
      } else {
        const cwInoutTypeList = this.cwInoutTypeList;
        const checkedCwInoutType = this.checkedCwInoutType;
        this.cwInoutTypeList = cwInoutTypeList.filter(function(item) {
          return checkedCwInoutType.indexOf(item.index) == -1
        });
      }
    }
  }
};
</script>