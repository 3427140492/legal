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
          <el-option label="借档人" value="2"/>
          <el-option label="档案号" value="3"/>
          <el-option label="档案名称" value="4"/>
        </el-select>
      </el-form-item>

      <el-form-item prop="borrowStatus">
        <el-select v-model="queryParams.borrowStatus" style="width: 120px;" placeholder="归还状态">
        <el-option label="已归还" value="Y"/>
          <el-option label="未归还" value="N"/>
        </el-select>
      </el-form-item>

      <el-form-item prop="Sertime">
        <el-select v-model="queryParams.Sertime" style="width: 160px;" placeholder="自定义排序">
          <el-option label="借阅时间从高到低" value="1"/>
          <el-option label="借阅时间从低到高" value="2"/>
          <el-option label="归还时间从高到低" value="3"/>
          <el-option label="归还时间从低到高" value="4"/>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh"  @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          plain
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          plain
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['record:archiveslibrary:remove']"
        >批量删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          plain
          :disabled="multiple"
          @click="updatezt"
        >设为已归还</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          plain
          :disabled="multiple"
          @click=""
        >设为未归还</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="archiveslibraryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="案号" align="center" prop="caseNo" />
      <el-table-column label="档案号" align="center" prop="recordNum" />
      <el-table-column label="档案名称" align="center" prop="recordName" />
      <el-table-column label="借档人" align="center" prop="empName" />
      <el-table-column label="借出时间" align="center" prop="borrowDate" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.borrowDate, '{yyyy}-{mm}-{dd}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="归还时间" align="center" prop="giveBackDate" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.giveBackDate, '{yyyy}-{mm}-{dd}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="归还状态" align="center" prop="borrowStatus">
        <template v-slot="scope">
          <span v-if="scope.row.borrowStatus == 'Y'">已归还</span>
          <span v-if="scope.row.borrowStatus == 'N'">未归还</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['record:archiveslibrary:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['record:archiveslibrary:remove']"
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

    <!-- 添加或修改档案借阅对话框 -->
    <el-dialog :title="title" v-model="open" width="760px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" >

        <el-row>
          <el-col :span="10">
            <label><span style="color:red;">*</span>案件：</label>
            <el-form-item  prop="caseLawId">
              <el-input v-model="form.caseLawId" @click="selectcase" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="10">
            <label><span style="color:red;">*</span>档案号：</label>
            <el-form-item  prop="recordNum">
              <el-input v-model="form.recordNum" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="10">
            <label><span style="color:red;">*</span>档案名称：</label>
            <el-form-item  prop="recordName">
              <el-input v-model="form.recordName" />
            </el-form-item>
          </el-col>
          <el-col :span="2">
          </el-col>
          <el-col :span="10">
            <label><span style="color:red;">*</span>借档人：</label>
            <el-form-item  prop="borrowFilePeople">
              <el-input v-model="form.borrowFilePeople" @click="selectjdrname" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="10">
            <label><span style="color:red;">*</span>借出时间：</label>
            <el-form-item  prop="borrowDate" style="width:450px;">
              <el-date-picker clearable v-model="form.borrowDate" value-format="YYYY-MM-DD">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="2">
          </el-col>
          <el-col :span="10">
            <label><span style="color:red;">*</span>归还时间：</label>
            <el-form-item  prop="giveBackDate" style="width:450px;">
              <el-date-picker clearable v-model="form.giveBackDate" value-format="YYYY-MM-DD">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="10">
            <label>备注：</label>
            <el-form-item  prop="remark" style="width:1100px;">
              <textarea rows="5" cols="90" class="form-control" style="width:570px;" v-model="form.remark"></textarea>
            </el-form-item>
          </el-col>
        </el-row>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog> 

    <!-- 案件查询 -->
    <el-dialog title="案件选择" v-model="ajcx" draggable>
      <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" >
      </el-form>
      <el-table :data="caselist" border  >
        <el-table-column label="案号" align="center" prop="caseNo" width="180" />
        <el-table-column label="委托人" align="center" prop="caseWtr" />
        <el-table-column label="对方当事人" align="center" prop="caseOppositeParties" width="150" />
        <el-table-column label="已到款" align="center" prop="casePaidsal" width="150" />
        <el-table-column label="已开票" align="center" prop="caseInvoiced" width="150"/>
        <el-table-column label="承办律师" align="center" prop="caseAttorney" width="180"/>
        <el-table-column label="收案日期" align="center" prop="collectionTime" width="180">
          <template v-slot="scope">
            <span>{{ parseTime(scope.row.collectionTime, '{yyyy}-{mm}-{dd}') }}</span>
          </template>
        </el-table-column>
      </el-table>
      <pagination
          v-show="total>0"
          :total="total"
          v-model="queryParams.pageNum"
          v-model:limit="queryParams.pageSize"
          @pagination="selectcase"
        />
    </el-dialog>

    <el-dialog title="人员选择" v-model="jdrxc" draggable>
      <el-table :data="namelist" border  >
        <el-table-column label="姓名" align="center" prop="userRealname" />
        <el-table-column label="手机号码" align="center" prop="userPhone">
          <template v-slot="scope">
            <span v-if="scope.row.userPhone == null">-</span>
          </template>
        </el-table-column>
        <el-table-column label="状态" align="center" prop="userStatus">
         <template v-slot="scope">
            <span v-if="scope.row.userStatus == 'Y'">在职</span>
          </template>
        </el-table-column>
      </el-table>
      <pagination
          v-show="total>0"
          :total="total"
          v-model="queryParams.pageNum"
          v-model:limit="queryParams.pageSize"
          @pagination="selectjdrname"
        />
    </el-dialog>
  </div>
</template>

<script>
import { listArchiveslibrary, getArchiveslibrary, delArchiveslibrary, addArchiveslibrary, updateArchiveslibrary } from "@/api/record/archiveslibrary";
import { selectCaseLawList, selectNameList } from "@/api/record/law";

export default {
  name: "Archiveslibrary",
  dicts: ['case_case_type_id'],
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
      // 档案借阅表格数据
      archiveslibraryList: [],
      //查询案件
      caselist: [],
      //查询借档人
      namelist: [],
      // 审批中心表格数据
      caseLawList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      ajcx: false,
      jdrxc: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        caseLawId: null,
        recordNum: null,
        recordName: null,
        borrowFilePeople: null,
        borrowDate: null,
        giveBackDate: null,
        borrowStatus: null,
        caseNo: null
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
    /** 查询档案借阅列表 */
    getList() {
      this.loading = true;
      listArchiveslibrary(this.queryParams).then(response => {
        this.archiveslibraryList = response.rows;
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
        recordNum: null,
        recordName: null,
        borrowFilePeople: null,
        borrowDate: null,
        giveBackDate: null,
        remark: null,
        borrowStatus: "0"
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
      this.title = "借档管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getArchiveslibrary(id).then(response => {
        this.form = response.data;
        this.caseLawList = response.data.caseLawList;
        this.open = true;
        this.title = "修改档案借阅";
      });
    },
    selectcase(){//案件查询
      this.ajcx=true;
      selectCaseLawList(this.queryParams).then(response=>{
         this.caselist = response.rows;
         this.total = response.total;
      });
    },
    selectjdrname(){//借档人查询
      this.jdrxc=true;
      selectNameList(this.queryParams).then(response=>{
         this.namelist = response.rows;
         this.total = response.total;
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.caseLawList = this.caseLawList;
          if (this.form.id != null) {
            updateArchiveslibrary(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addArchiveslibrary(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除档案借阅编号为"' + ids + '"的数据项？').then(function() {
        return delArchiveslibrary(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
	/** 审批中心序号 */
    rowCaseLawIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 复选框选中数据 */
    handleCaseLawSelectionChange(selection) {
      this.checkedCaseLaw = selection.map(item => item.index)
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('record/archiveslibrary/export', {
        ...this.queryParams
      }, `archiveslibrary_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
