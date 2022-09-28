<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="empName">
        <el-input v-model="queryParams.empName" placeholder="请输入姓名" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="手机号码" prop="empPhone">
        <el-input v-model="queryParams.empPhone" placeholder="请输入手机号码" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="身份" prop="identityName">
        <el-select v-model="queryParams.identityName" placeholder="请选择身份">
          <el-option label="选择身份" value="" />
          <el-option label="律师" value="律师" />
          <el-option label="实习人员" value="实习" />
          <el-option label="辅助人员" value="辅助" />
        </el-select>
      </el-form-item>
      <el-form-item label="入职时间" prop="empEntrydate">
        <el-date-picker clearable v-model="queryParams.empEntrydate" value-format="YYYY-MM-DD" placeholder="请选择入职时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="合同截止" prop="empContEnddate">
        <el-date-picker clearable v-model="queryParams.empContEnddate" type="date" value-format="YYYY-MM-DD"
          placeholder="请选择合同截止">
        </el-date-picker>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" size="mini" @click="handleQuery">搜索</el-button>
        <el-button size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="archivesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="身份" align="center" prop="identityName" />
      <el-table-column label="姓名" align="center" prop="empName" />
      <el-table-column label="手机号码" align="center" prop="empPhone" />
      <el-table-column label="入职时间" align="center" prop="empEntrydate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.empEntrydate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="合同到期时间" align="center" prop="empContEnddate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.empContEnddate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否在职" align="center" prop="empWorkstatus">
        <template v-slot="scope">
          <span v-if="scope.row.empWorkstatus == 'Y'">在职</span>
          <span v-if="scope.row.empWorkstatus == 'N'">离职</span>
        </template>
      </el-table-column>
      <el-table-column label="出生日期" align="center" prop="empBirthdate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.empBirthdate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-dropdown trigger="click">
            <el-button>
              操作<el-icon class="el-icon--right">
                <arrow-down />
              </el-icon>
            </el-button>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="ViewDetail(scope.row.id)">查看详细信息</el-dropdown-item>
                <el-dropdown-item>修改员工信息</el-dropdown-item>
                <el-dropdown-item>查看工作经历</el-dropdown-item>
                <el-dropdown-item>查看教育经历</el-dropdown-item>
                <el-dropdown-item>删除员工</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </template>


      </el-table-column>
    </el-table>

    <el-dialog v-model="this.ViewDetails" title="人事资料详情">
      <el-descriptions title="基本资料" :column="3" border>
        <el-descriptions-item label="姓名" align="center">{{data.empName}}</el-descriptions-item>
        <el-descriptions-item label="性别" align="center">{{data.empSex == 'F'?'女':'男'}}</el-descriptions-item>
        <el-descriptions-item label="是否在职" align="center">{{data.empWorkstatus == 'Y'?'是':'否'}}</el-descriptions-item>
        <el-descriptions-item label="出生日期" align="center">{{data.empBirthdate}}</el-descriptions-item>
        <el-descriptions-item label="证件类型" align="center">{{data.empPaperstype}}</el-descriptions-item>
        <el-descriptions-item label="证件号" align="center">{{data.empPapersnum}}</el-descriptions-item>
        <el-descriptions-item label="最高学历" align="center">{{data.empHigthEducation}}</el-descriptions-item>
        <el-descriptions-item label="最高学位" align="center">{{data.empHigthDegree}}</el-descriptions-item>
        <el-descriptions-item label="所内身份" align="center">{{data.empPosition}}</el-descriptions-item>
        <el-descriptions-item label="民族" align="center">{{data.hrNationId}}</el-descriptions-item>
        <el-descriptions-item label="政治面貌" align="center">{{data.hrPoliticalId}}</el-descriptions-item>
        <el-descriptions-item label="婚姻情况" align="center">{{data.empMaritalsta}}</el-descriptions-item>
        <el-descriptions-item label="门禁号" align="center">{{data.empEntrancenum}}</el-descriptions-item>
        <el-descriptions-item label="专业部" align="center">{{data.hrSpecialtyId}}</el-descriptions-item>
      </el-descriptions>

      <br>

      <el-descriptions title="从业资料" :column="3" border>
        <el-descriptions-item label="执业类别" align="center">{{data.hrPractiseId}}</el-descriptions-item>
        <el-descriptions-item label="执业证号码" align="center">{{data.empPracnum}}</el-descriptions-item>
        <el-descriptions-item label="首次执业时间" align="center">{{data.empPracdate}}</el-descriptions-item>
        <el-descriptions-item label="首次执业地" align="center">{{data.empPracsite}}</el-descriptions-item>
        <el-descriptions-item label="资格证类别" align="center">{{data.hrCertifyId}}</el-descriptions-item>
        <el-descriptions-item label="资格证号码" align="center">{{data.empCertifynum}}</el-descriptions-item>
        <el-descriptions-item label="资格证取得方式" align="center">{{data.empGainway}}</el-descriptions-item>
        <el-descriptions-item label="资格证取得地" align="center">{{data.empGainsite}}</el-descriptions-item>
        <el-descriptions-item label="个人介绍" align="center">{{data.empIntroduce}}</el-descriptions-item>
      </el-descriptions>

      <br>

      <el-descriptions title="入职情况" :column="3" border>
        <el-descriptions-item label="入职时间" align="center">{{data.empEntrydate}}</el-descriptions-item>
        <el-descriptions-item label="合同开始" align="center">{{data.empContStartdate}}</el-descriptions-item>
        <el-descriptions-item label="合同截止" align="center">{{data.empContStartdate}}</el-descriptions-item>
        <el-descriptions-item label="社保号码" align="center">{{data.empContEnddate}}</el-descriptions-item>
        <el-descriptions-item label="是否参保" align="center">{{data.empInsured}}</el-descriptions-item>
      </el-descriptions>

      <br>

      <el-descriptions title="联系信息" :column="3" border>
        <el-descriptions-item label="联系电话" align="center">{{data.empContactPhone}}</el-descriptions-item>
        <el-descriptions-item label="手机号码" align="center">{{data.empPhone}}</el-descriptions-item>
        <el-descriptions-item label="邮箱" align="center">{{data.empEmail}}</el-descriptions-item>
        <el-descriptions-item label="邮政编码" align="center">{{data.empPostcode}}</el-descriptions-item>
        <el-descriptions-item label="QQ号码" align="center">{{data.empQq}}</el-descriptions-item>
        <el-descriptions-item label="微信号" align="center">{{data.empWechat}}</el-descriptions-item>
        <el-descriptions-item label="联系地址" align="center">{{data.empSite}}</el-descriptions-item>
      </el-descriptions>
    </el-dialog>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

  </div>
</template>
  
<script>

import { listArchives, getArchives } from "@/api/persM/archives";



export default {
  name: "Archives",
  data() {
    return {
      // 遮罩层
      loading: true,
      ViewDetails: false,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedHrLawyerIdentity: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 人事档案表格数据
      archivesList: [],
      // ${subTable.functionName}表格数据
      hrLawyerIdentityList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      data: {

      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        empName: null,
        empEntrydate: null,
        empContEnddate: null,
        empPhone: null,
        identityName: null,
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
    /** 查询人事档案列表 */
    getList() {
      this.loading = true;
      listArchives(this.queryParams).then(response => {
        this.archivesList = response.rows;
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
        empName: null,
        empBirthdate: null,
        empSex: null,
        empWorkstatus: "0",
        empPaperstype: null,
        empPapersnum: null,
        empHigthEducation: null,
        empHigthDegree: null,
        empPosition: null,
        hrNationId: null,
        hrPoliticalId: null,
        empMaritalsta: null,
        empEntrancenum: null,
        hrSpecialtyId: null,
        hrPractiseId: null,
        empPracnum: null,
        empPracdate: null,
        empPracsite: null,
        hrCertifyId: null,
        empCertifynum: null,
        empGainway: null,
        empGainsite: null,
        empIntroduce: null,
        empEntrydate: null,
        empSocialNum: null,
        empContStartdate: null,
        empContEnddate: null,
        empInsured: null,
        empPhone: null,
        empPicture: null,
        empSite: null,
        empContactPhone: null,
        empEmail: null,
        empPostcode: null,
        empQq: null,
        empWechat: null,
        identityName: null
      };
      this.hrLawyerIdentityList = [];
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
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    ViewDetail(id) {
      this.ViewDetails = true;
      getArchives(id).then(response => {
        this.data.empName = response.data.empName;
        this.data.empBirthdate = response.data.empBirthdate;
        this.data.empSex = response.data.empSex;
        this.data.empWorkstatus = response.data.empWorkstatus;
        this.data.empPaperstype = response.data.empPaperstype;
        this.data.empPapersnum = response.data.empPapersnum;
        this.data.empHigthEducation = response.data.empHigthEducation;
        this.data.empHigthDegree = response.data.empHigthDegree;
        this.data.empPosition = response.data.empPosition;
        this.data.hrNationId = response.data.hrNationId;
        this.data.hrPoliticalId = response.data.hrPoliticalId;
        this.data.empMaritalsta = response.data.empMaritalsta;
        this.data.empEntrancenum = response.data.empEntrancenum;
        this.data.hrSpecialtyId = response.data.hrSpecialtyId;
        this.data.hrPractiseId = response.data.hrPractiseId;
        this.data.empPracnum = response.data.empPracnum;
        this.data.empPracdate = response.data.empPracdate;
        this.data.empPracsite = response.data.empPracsite;
        this.data.hrCertifyId = response.data.hrCertifyId;
        this.data.empCertifynum = response.data.empCertifynum;
        this.data.empGainway = response.data.empGainway;
        this.data.empGainsite = response.data.empGainsite;
        this.data.empIntroduce = response.data.empIntroduce;
        this.data.empEntrydate = response.data.empEntrydate;
        this.data.empSocialNum = response.data.empSocialNum;
        this.data.empContStartdate = response.data.empContStartdate;
        this.data.empContEnddate = response.data.empContEnddate;
        this.data.empInsured = response.data.empInsured;
        this.data.empPhone = response.data.empPhone;
        this.data.empPicture = response.data.empPicture;
        this.data.empSite = response.data.empSite;
        this.data.empContactPhone = response.data.empContactPhone;
        this.data.empEmail = response.data.empEmail;
        this.data.empPostcode = response.data.empPostcode;
        this.data.empQq = response.data.empQq;
        this.data.empWechat = response.data.empWechat;
      })
    }
  }
}
</script>
