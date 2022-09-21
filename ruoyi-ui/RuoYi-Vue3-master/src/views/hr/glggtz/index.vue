<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm"  :inline="true" v-show="showSearch" label-width="70px" >
        <el-form-item label="标题" prop="title" >
          <el-input
            v-model="queryParams.title"
            placeholder="请输入标题"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="开始时间" prop="noticeNotificationtime">
          <el-input
            v-model="queryParams.noticeNotificationtime"
            placeholder="请输入时间"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="结束时间" prop="noticeNotificationendtime">
          <el-input
            v-model="queryParams.noticeNotificationendtime"
            placeholder="请输入"
            clearable
            @keyup.enter.native="handleQuery"
          />
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
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['hr:notice:add']"
          >新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="success"
            plain
           icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['hr:notice:edit']"
          >修改</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['hr:notice:remove']"
          >删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['hr:notice:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
  
      <el-table border="1px" v-loading="loading" :data="noticeList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="编号" width="150" align="center" prop="id"  /> -->
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column label="时间" align="center" prop="noticeNotificationtime" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template v-slot="scope">
  
            <!-- query(scope.$index,scope.row) -->
            <!-- <router-link to="/notice/ggtzQR"> -->
              <!-- <el-button 
               @click="toView" >
               查看
               </el-button> -->
            <!-- </router-link> -->
  
            <el-button 
               @click="toView(scope.row,'ggtzQR')" >
               查看
               </el-button>
  
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
  
      <!-- 添加或修改公告通知对话框 -->
      <el-dialog :title="title" v-model="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
            <el-form-item label="标题" prop="title" >
            <el-input
              v-model="queryParams.title"
              placeholder="请输入标题"
              clearable
              @keyup.enter.native="handleQuery"
            />
           </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>
    </div>
  </template>
  
  <script>
  import { listNotice, getNotice, delNotice, addNotice, updateNotice } from "@/api/hr/notice";
  
  
  export default {
    name: "Notice",
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
        // 公告通知表格数据
        noticeList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          title: null,
          noticeNotificationtime: null,
          noticeNotificationendtime: null,
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
      toView(row,name){
        // alert("sdf");
        console.log(row.id);
        // this.$router.push('/hr/ggtzQR');
        this.$router.push({ 
            name:name,
            params:{
               id:row.id
            }
        })
      },
      //公告通知查看传值
      // query(index,row){
      //      console.log(row);
      //      console.log(row,index);
      //      this.$router.push({path:"",query:{id:row.id}})
      // },
      /** 查询公告通知列表 */
      getList() {
        this.loading = true;
        listNotice(this.queryParams).then(response => {
          this.noticeList = response.rows;
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
          title: null,
          noticeReleasetime: null,
          systemUserId: null,
          noticeNotificationtime: null,
          noticeNotificationendtime: null,
          noticeContent: null,
          noticeFile: null
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
        this.title = "添加公告通知";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getNotice(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改公告通知";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateNotice(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addNotice(this.form).then(response => {
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
        this.$modal.confirm('是否确认删除公告通知编号为"' + ids + '"的数据项？').then(function() {
          return delNotice(ids);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('hr/notice/export', {
          ...this.queryParams
        }, `notice_${new Date().getTime()}.xlsx`)
      },
      // query(row){
        //  router.push({
        //    path:'/notice/ggtzQR',
        //    query:{
        //       id:row.id
        //    }
        //  })
  
      //   this.$router.push({name:"ggtzQR",query:{id:row.id}})
      // }
    }
  
  };
  </script>

 <style scoped>
   .item{
      height:50px;
   }
</style>
  