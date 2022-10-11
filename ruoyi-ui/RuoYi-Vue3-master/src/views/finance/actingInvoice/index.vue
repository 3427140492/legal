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

import { listInvoice, getInvoice, delInvoice, addInvoice } from "@/api/finance/invoice";

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
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加我的发票";
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
    }
  }
};
</script>
