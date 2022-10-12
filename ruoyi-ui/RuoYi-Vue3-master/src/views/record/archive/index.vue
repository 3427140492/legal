<template>
<div class="app-container">
  <span style="font-weight: bold;font-size: 20px;">| 案件归档</span>
  <hr/>
  <el-form ref="form" :model="form" :rules="rules" >
    <el-row>
      <el-col :span="7">
        <el-form-item>
          <label><span style="color:red;">*</span>案件：</label>
        </el-form-item>
        <el-form-item  prop="caseLawId">
          <el-input v-model="form.caseLawId" @click="selectcase" />
        </el-form-item>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="10">
        <el-form-item>
          <label><span style="color:red;">*</span>文件夹：</label>
        </el-form-item>
        <el-form-item  prop="folderId">
          <el-select v-model="form.folderId" style="width: 350px">
            <el-option v-for="t in folderList" :key="t.fid" :label="t.folderName" :value="t.fid" />
          </el-select>
          <el-button @click="handleAddfol" type="primary">添加文件夹</el-button>
        </el-form-item>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="10">
        <el-form-item>
          <label>相关文件：</label>
        </el-form-item>
        <el-form-item  prop="documentFile">
          <file-upload v-model="form.documentFile"/>
        </el-form-item>
      </el-col>
    </el-row>
  </el-form>
  <el-button type="primary" @click="submitForm">上传</el-button>

    <!-- 案件查询 -->
    <el-dialog title="案件选择" v-model="ajcx" draggable>
      <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" >
      </el-form>
      <el-table :data="caselist" border ref="ajlist" @select="ajhandleSelectionChange" >
        <el-table-column type="selection" width="55" align="center" />
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
      <el-button @click="ajcx = false">取消</el-button>
      <el-button @click="quding">确定</el-button>
      <pagination
          v-show="total>0"
          :total="total"
          v-model="queryParams.pageNum"
          v-model:limit="queryParams.pageSize"
          @pagination="selectcase"
        />
    </el-dialog>

    <!--文件夹新增-->
    <el-dialog title="文件新增" v-model="folderxz" draggable>
      <span style="font-weight: bold;font-size: 20px;">| 基本信息</span>
      <hr/>
      <el-form  :model="forms">
        <el-row>
          <el-col :span="7">
            <label><span style="color:red;">*</span>名称：</label>
            <el-form-item >
            <el-input v-model="forms.folderName" />
            </el-form-item>
          </el-col>
        </el-row>
        <div slot="footer" class="dialog-footer">
          <el-button plain type="primary" @click="submitFormfol">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-form>
    </el-dialog>
</div>
</template>
<script>
import { listDocument, getDocument, delDocument, addDocument, updateDocument } from "@/api/record/document";
import { selectCaseLawList, selectfolderList, addFolder } from "@/api/record/law";

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
      //查询案件
      caselist: [],
      //文件夹查询
      folderList: [],
      // ${subTable.functionName}表格数据
      caseLawList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      ajcx: false,
      folderxz: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        SearchStr: null,
        SearchType:null
      },
      // 表单参数
      form: {},
      forms: {},
      // 表单校验
      rules: {
      },
      //案件复选框
      ajlist: [],
      qudings: {},
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
      selectfolderList().then(response => {
        this.folderList = response.rows;
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
      this.forms = {
       folderName: null
      };
      this.caseLawList = [];
      this.resetForm("form");
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
    /** 新增文件夹 */
    handleAddfol() {
      this.folderxz = true;
      this.title = "添加文件夹";
      this.reset();
    },
    selectcase(){//案件查询
      this.ajcx=true;
      selectCaseLawList(this.queryParams).then(response=>{
         this.caselist = response.rows;
         this.total = response.total;
          this.$nextTick(()=>{
            this.$refs.ajlist.toggleRowSelection(this.caselist)
         })
      });
    },
    //案件复选框
    ajhandleSelectionChange(selection,row){
       if(selection.length >1){
          const del_row = selection.shift()
          this.$refs.ajlist.toggleRowSelection(del_row,false);
       }
       this.systemlist = selection[0];
       console.log(selection);
       this.quding1 = selection;
    },
    //案件复选框
    quding(){
      this.form.caseLawId = this.quding1[0].id;
      this.ajcx = false;
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
    /** 文件夹提交按钮 */
    submitFormfol() {
        addFolder(this.forms).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.folderxz = false;
          this.getList();
        });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('record/document/export', {
        ...this.queryParams
      }, `document_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>
