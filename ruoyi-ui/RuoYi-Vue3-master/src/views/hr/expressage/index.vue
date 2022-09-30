<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="收件人" prop="systemUserRecipients">
        <el-input
          v-model="queryParams.systemUserRecipients"
          placeholder="请输入收件人"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>

      <el-form-item label="领件人" prop="collarPerson">
        <el-input
          v-model="queryParams.collarPerson"
          placeholder="请输入领件人"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
       
     

     <el-form-item  prop="expressageSendWaayId">
      <el-select v-model="queryParams.expressageSendWaayId" placeholder="选择快递公司" style="width: 150px;"  >
          <el-option v-for="t in sendTypeList" :key="t.id"  :label="t.sendName" :value="t.id"></el-option>
     </el-select>
     </el-form-item>


     <el-form-item prop="expressageExpressTypeId">

      <el-select v-model="queryParams.expressageExpressTypeId" placeholder="选择快件类型" style="width: 150px;" clearable >
          <el-option v-for="t in expressList" :key="t.id"  :label="t.expressName" :value="t.id"></el-option>
     </el-select>

     </el-form-item>


     <el-form-item prop="takestatus">
      <el-select v-model="queryParams.takestatus" placeholder="领取状态">
        <el-option label="未领取" value="1" />
        <el-option label="已领取" value="2" />
      </el-select>
     </el-form-item>


      <el-form-item label="日期" prop="selectStr">

        <el-date-picker clearable v-model="queryParams.selectStr" value-format="YYYY-MM-DD">
          </el-date-picker>

      </el-form-item>
  
      <el-form-item  prop="selectType">
        <el-select v-model="queryParams.selectType" placeholder="根据收件日期">
            <el-option label="根据收件日期" value="1" />
            <el-option label="根据领件日期" value="2" />
        </el-select>
      </el-form-item>
      
      <el-form-item>
        <el-button type="primary"  size="mini" @click="handleQuery">搜索</el-button>
        <el-button  size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          size="mini"
          @click="handleAdd"
          v-hasPermi="['hr:expressage:add']"
        >添加收件</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="success"
          plain
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['hr:expressage:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['hr:expressage:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          size="mini"
          @click="handleExport"
          v-hasPermi="['hr:expressage:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch="showSearch" @queryTable="getList"></right-toolbar> -->
    </el-row>

    <el-table border="1px" v-loading="loading" :data="expressageList" @selection-change="handleSelectionChange">
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <!-- <el-table-column label="id" align="center" prop="id" /> -->
      <!-- <el-table-column label="发件人" align="center" prop="systemUserAddresser" /> -->
      <el-table-column label="收件人" align="center" prop="systemUserRecipients"  width="100"/>
      <!-- <el-table-column label="收件单位" align="center" prop="expressageSendaunit" /> -->
      <!-- <el-table-column label="发件日期" align="center" prop="expressageSendadate" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.expressageSendadate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
      <el-table-column label="收件日期" align="center" prop="expressageDateofreceipt" width="130">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.expressageDateofreceipt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="案号" align="center" prop="caseNo" width="180" />
      <el-table-column label="快递公司" align="center" prop="sendName" width="100"/>
      <el-table-column label="快件方式" align="center" prop="expressName" width="100" />
      <!-- <el-table-column label="快递单号" align="center" prop="expressageCourierNumber" /> -->
      <!-- <el-table-column label="领件说明" align="center" prop="expressageCollarnote" /> -->
      <el-table-column label="快递费" align="center" prop="expressFee" >
        <template v-slot="scope">
          <span v-if="scope.row.expressFee == null">-</span>
        </template>
      </el-table-column>

      <el-table-column label="领件人" align="center" prop="collarPerson" width="70"/>
      <el-table-column label="领件状态" align="center" prop="takestatus"  >
        <template v-slot="scope">
          <span v-if="scope.row.takestatus == 1">未领取</span>
          <span v-if="scope.row.takestatus == 2">已领取</span>
        </template>
      </el-table-column>
      <el-table-column label="领件时间" align="center" prop="expressageBringDate">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.expressageBringDate, '{y}-{m}-{d}') }}</span>
          <span v-if="scope.row.expressageBringDate == null">-</span>
        </template> 
      </el-table-column>
      <el-table-column label="备注说明" align="center" prop="expressageRemark"/>
      
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <!-- <template v-slot="scope">
          <el-button
            size="mini"
            type="text"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['hr:expressage:edit']"
          >修改</el-button>

          <el-button
            size="mini"
            type="text"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['hr:expressage:edit']"
          >修改</el-button>
          
          <el-button
            size="mini"
            type="text"
            @click="handleDelete(scope.row)"
            v-hasPermi="['hr:expressage:remove']"
          >删除</el-button>
        </template> -->

        <template v-slot="scope">
          <el-dropdown trigger="click">
            <el-button>
              操作<el-icon class="el-icon--right">
                <arrow-down />
              </el-icon>
            </el-button>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="Query(scope.row.id)">查看</el-dropdown-item>
                <el-dropdown-item @click="handleUpdate(scope.row)">修改</el-dropdown-item>
                <el-dropdown-item @click="handleDelete(scope.row)">删除</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </template>
        
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 查看对话框 -->
    <el-dialog :title="title" v-model="this.open" width="1000px" append-to-body>
        <!-- <div style="margin:0px 0px 0px 20px;color: black;  line-height: 20px; display: inline-block;font-size: 16px;font-weight: bold;">基本资料</div> -->
        <el-descriptions title="基本资料" :column="3" border>
        <el-descriptions-item label="收件人" align="center">{{form.systemUserRecipients}}</el-descriptions-item>
        <el-descriptions-item label="收件日期" align="center">{{form.expressageDateofreceipt}}</el-descriptions-item>
        <el-descriptions-item label="收件来源" align="center">{{form.sendName}}</el-descriptions-item>
        <el-descriptions-item label="快件类型" align="center">{{form.expressName}}</el-descriptions-item>
        <el-descriptions-item label="领取状态" align="center">{{form.takestatus == 1 ?'未领取':'已领取'}}</el-descriptions-item>
        </el-descriptions>
        <br>
        <br>
        <el-descriptions title="详细信息" :column="3" border>
        <el-descriptions-item label="案件" align="center">{{form.caseNo}}</el-descriptions-item>
        <el-descriptions-item label="快递单号" align="center">{{form.expressageCourierNumber}}</el-descriptions-item>
        <el-descriptions-item label="快递费" align="center">{{form.expressFee}}</el-descriptions-item>
        <el-descriptions-item label="领件人" align="center">{{form.collarPerson}}</el-descriptions-item>
        <el-descriptions-item label="领件时间" align="center">{{form.expressageBringDate}}</el-descriptions-item>
        <el-descriptions-item label="相关文件" align="center">{{form.expressageFile}}</el-descriptions-item>
        <el-descriptions-item label="备注说明" align="center">{{form.expressageRemark}}</el-descriptions-item>
        </el-descriptions>


      </el-dialog>
 

    <!-- 添加或修改快速登记对话框 -->
    <el-dialog :title="title" v-model="AddOpen" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="发件人" prop="systemUserAddresser">
          <el-input v-model="form.systemUserAddresser" placeholder="请输入发件人" />
        </el-form-item>
        <el-form-item label="收件人" prop="systemUserRecipients">
          <el-input v-model="form.systemUserRecipients" placeholder="请输入收件人" />
        </el-form-item>
        <el-form-item label="收件单位" prop="expressageSendaunit">
          <el-input v-model="form.expressageSendaunit" placeholder="请输入收件单位" />
        </el-form-item>
        <el-form-item label="发件日期" prop="expressageSendadate">
          <el-date-picker clearable
            v-model="form.expressageSendadate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择发件日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="收件日期" prop="expressageDateofreceipt">
          <el-date-picker clearable
            v-model="form.expressageDateofreceipt"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择收件日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="领件时间" prop="expressageBringDate">
          <el-date-picker clearable
            v-model="form.expressageBringDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择领件时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="快递方式" prop="expressageSendWaayId">
          <el-input v-model="form.expressageSendWaayId" placeholder="请输入快递方式" />
        </el-form-item>
        <el-form-item label="快件类型" prop="expressageExpressTypeId">
          <el-input v-model="form.expressageExpressTypeId" placeholder="请输入快件类型" />
        </el-form-item>
        <el-form-item label="快递单号" prop="expressageCourierNumber">
          <el-input v-model="form.expressageCourierNumber" placeholder="请输入快递单号" />
        </el-form-item>
        <el-form-item label="文件">
          <file-upload v-model="form.expressageFile"/>
        </el-form-item>
        <el-form-item label="领件说明" prop="expressageCollarnote">
          <el-input v-model="form.expressageCollarnote" placeholder="请输入领件说明" />
        </el-form-item>
        <el-form-item label="备注说明" prop="expressageRemark">
          <el-input v-model="form.expressageRemark" placeholder="请输入备注说明" />
        </el-form-item>
        <el-form-item label="快递费" prop="expressFee">
          <el-input v-model="form.expressFee" placeholder="请输入快递费" />
        </el-form-item>
        <el-form-item label="领件人" prop="collarPerson">
          <el-input v-model="form.collarPerson" placeholder="请输入领件人" />
        </el-form-item>
        <el-form-item label="联系电话" prop="expressageTel">
          <el-input v-model="form.expressageTel" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="案件id 外键" prop="caseNo">
          <el-input v-model="form.caseNo" placeholder="请输入案件id 外键" />
        </el-form-item>
        <el-divider content-position="center">发送信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary"  size="mini" @click="handleAddSendWaay">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger"  size="mini" @click="handleDeleteSendWaay">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="sendWaayList" :row-class-name="rowSendWaayIndex" @selection-change="handleSendWaaySelectionChange" ref="sendWaay">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="序号" prop="sorting" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.sorting" placeholder="请输入序号" />
            </template>
          </el-table-column>
          <el-table-column label="快递方式" prop="sendName" width="150">
            <template v-slot="scope">
              <el-input v-model="scope.row.sendName" placeholder="请输入快递方式" />
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
import { listExpressage, getExpressage, delExpressage, addExpressage, updateExpressage,sendList,expressList} from "@/api/hr/expressage";


export default {
  name: "Expressage",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedSendWaay: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 快速登记表格数据
      expressageList: [],
      sendTypeList: [],
      expressList:[],
      // 发送表格数据
      sendWaayList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        systemUserRecipients: null,
        expressageBringDate: null,
        expressageSendWaayId: null,
        expressageExpressTypeId: null,
        takestatus: null,
        collarPerson: null,
        selectType:null,
        selectStr:null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询快速登记列表 */
    getList() {
      this.loading = true;
      listExpressage(this.queryParams).then(response => {
        this.expressageList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      sendList().then(response=>{ //快递公司查询
        console.log(response.rows);
         this.sendTypeList = response.rows;
         console.log(this.sendTypeList);
      });
      expressList().then(response=>{  //快件类型查询
        console.log(response.rows);
         this.expressList = response.rows;
         console.log(this.expressList);
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
        systemUserAddresser: null,
        systemUserRecipients: null,
        expressageSendaunit: null,
        expressageSendadate: null,
        expressageDateofreceipt: null,
        expressageBringDate: null,
        expressageSendWaayId: null,
        expressageExpressTypeId: null,
        expressageCourierNumber: null,
        expressageFile: null,
        expressageCollarnote: null,
        expressageRemark: null,
        takestatus: null,
        expressFee: null,
        collarPerson: null,
        expressageTel: null,
        expressageType: null,
        caseNo: null
      };
      this.sendWaayList = [];
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
      this.AddOpen = true;
      this.title = "添加快速登记";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getExpressage(id).then(response => {
        this.form = response.data;
        this.sendWaayList = response.data.sendWaayList;
        this.open = true;
        this.title = "修改快速登记";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.sendWaayList = this.sendWaayList;
          if (this.form.id != null) {
            updateExpressage(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addExpressage(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除快速登记编号为"' + ids + '"的数据项？').then(function() {
        return delExpressage(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 发送序号 */
    rowSendWaayIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 发送添加按钮操作 */
    handleAddSendWaay() {
      let obj = {};
      obj.sorting = "";
      obj.sendName = "";
      this.sendWaayList.push(obj);
    },
    /** 发送删除按钮操作 */
    handleDeleteSendWaay() {
      if (this.checkedSendWaay.length == 0) {
        this.$modal.msgError("请先选择要删除的发送数据");
      } else {
        const sendWaayList = this.sendWaayList;
        const checkedSendWaay = this.checkedSendWaay
        this.sendWaayList = sendWaayList.filter(function(item) {
          return checkedSendWaay.indexOf(item.index) == -1
        });
      }
    },
    /** 复选框选中数据 */
    handleSendWaaySelectionChange(selection) {
      this.checkedSendWaay = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('hr/expressage/export', {
        ...this.queryParams
      }, `expressage_${new Date().getTime()}.xlsx`)
    },
    Query(id){  //根据id查询
          this.open = true;
          getExpressage(id).then(response => {
            this.form = response.data;
            this.title = "收件详情";
          });
        },

  }
}
</script>
