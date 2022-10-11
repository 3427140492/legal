<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm"  :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>

      <!-- <el-form-item label="创建时间" style="width: 308px">
        <el-date-picker
          v-model="dateRange"
          value-format="YYYY-MM-DD"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item> -->


     <el-form-item label="开始时间" prop="noticeNotificationtime">
     
          <el-date-picker clearable v-model="queryParams.noticeNotificationtime" value-format="YYYY-MM-DD" placeholder="请输入开始时间">
          </el-date-picker>

      </el-form-item>

      <el-form-item label="结束时间" prop="noticeNotificationtime2">
           <el-date-picker clearable v-model="queryParams.noticeNotificationtime2" value-format="YYYY-MM-DD" placeholder="请输入结束时间">
           </el-date-picker>
      </el-form-item>



     
      <!-- <el-form-item label="" prop="noticeNotificationendtime">
        <el-date-picker clearable
          v-model="queryParams.noticeNotificationendtime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择">
        </el-date-picker>
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" @click="handleQuery">搜索</el-button>
        <el-button @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          size="mini"
          @click="handleAdd"
          v-hasPermi="['hr:notice:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
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
          size="mini"
          @click="handleExport"
          v-hasPermi="['hr:notice:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table border="1px" v-loading="loading" :data="noticeList" @selection-change="handleSelectionChange">
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <!-- <el-table-column label="编号" align="center" prop="id" /> -->
      <el-table-column label="标题" align="center" prop="title" width="250"/>
      <el-table-column label="时间" align="center" prop="noticeNotificationtime" :formatter="formatTime">
        
        <!-- <template  v-slot="scope">
          <span>{{ parseTime(scope.row.noticeNotificationtime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template> -->
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['hr:notice:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['hr:notice:remove']"
          >删除</el-button> -->

          <el-button
          plain
          size="mini"
          @click="Query(scope.row)"
          v-hasPermi="['hr:notice:query']"
        >查看</el-button>

        </template>
        
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model.limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改公告通知对话框 -->
    <el-dialog :title="title"  v-model="open" width="1000px" append-to-body>
      
      <div style="width:800px;margin:auto;border:0px solid black;text-align:center;font-size:30px;font-weight:bold;">{{form.title}}</div>

      <div style="width:950px;margin-top:20px;padding-bottom: 20px; border-bottom:1px solid black;text-align:center;font-size:15px;color:gray;">{{parseTime(form.noticeNotificationtime,'{y}年{m}月{d}日')}} 发布人:{{form.userRealname}}</div>
       
      <div style="width:800px;margin:30px;border:0px solid black;font-size:16px;">{{form.noticeContent}}</div>

      <div style="width:800px;margin:30px 0px 50px 100px;border:0px solid black;font-size:15px;color:gray;">已阅：{{form.userRealname}}</div>

      <!-- <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div> -->
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
        noticeNotificationtime2: null,
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
        noticeFile: null,
        dateRange:null
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
    Query(row){  //根据id查询
      this.reset();
      const id = row.id || this.ids
      getNotice(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "查看详情";
      });
    },
    formatTime(row, column) {
       let date = row[column.property]
       let dtime = new Date(date)
       const year = dtime.getFullYear()
       let month = dtime.getMonth()+1
       if(month < 10){
          month = '0' + month
       }

       let day = dtime.getDate()
       if(day < 10){
          day = '0' + day
       }
       
       let hour = dtime.getHours()
       if(hour < 10){
        hour = '0' + hour
       }

       let minute = dtime.getMinutes()
       if(minute < 10){
        minute = '0' + minute
       }

       let second = dtime.getSeconds()
       if(second < 10){
        second = '0' + second
       }

      return year + '年' + month + '月' + day +'日'+' '+hour +':'+minute + ':' +second
    },
  }
};

</script>
