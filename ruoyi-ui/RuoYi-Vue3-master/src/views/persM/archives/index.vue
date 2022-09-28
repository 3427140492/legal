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
                <el-dropdown-item @click="revise = true">修改员工信息</el-dropdown-item>
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

    <!-- 添加或修改人事档案对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="姓名" prop="empName">
          <el-input v-model="form.empName" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="出生日期" prop="empBirthdate">
          <el-date-picker clearable v-model="form.empBirthdate" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择出生日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="证件号" prop="empPapersnum">
          <el-input v-model="form.empPapersnum" placeholder="请输入证件号" />
        </el-form-item>
        <el-form-item label="最高学历" prop="empHigthEducation">
          <el-input v-model="form.empHigthEducation" placeholder="请输入最高学历" />
        </el-form-item>
        <el-form-item label="最高学位" prop="empHigthDegree">
          <el-input v-model="form.empHigthDegree" placeholder="请输入最高学位" />
        </el-form-item>
        <el-form-item label="所内身份" prop="empPosition">
          <el-input v-model="form.empPosition" placeholder="请输入所内身份" />
        </el-form-item>
        <el-form-item label="民族" prop="hrNationId">
          <el-input v-model="form.hrNationId" placeholder="请输入民族" />
        </el-form-item>
        <el-form-item label="政治面貌" prop="hrPoliticalId">
          <el-input v-model="form.hrPoliticalId" placeholder="请输入政治面貌" />
        </el-form-item>
        <el-form-item label="婚姻情况" prop="empMaritalsta">
          <el-input v-model="form.empMaritalsta" placeholder="请输入婚姻情况" />
        </el-form-item>
        <el-form-item label="门禁号" prop="empEntrancenum">
          <el-input v-model="form.empEntrancenum" placeholder="请输入门禁号" />
        </el-form-item>
        <el-form-item label="专业部" prop="hrSpecialtyId">
          <el-input v-model="form.hrSpecialtyId" placeholder="请输入专业部" />
        </el-form-item>
        <el-form-item label="执业类别" prop="hrPractiseId">
          <el-input v-model="form.hrPractiseId" placeholder="请输入执业类别" />
        </el-form-item>
        <el-form-item label="执业证号码" prop="empPracnum">
          <el-input v-model="form.empPracnum" placeholder="请输入执业证号码" />
        </el-form-item>
        <el-form-item label="首次执业时间" prop="empPracdate">
          <el-date-picker clearable v-model="form.empPracdate" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择首次执业时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="首次执业地" prop="empPracsite">
          <el-input v-model="form.empPracsite" placeholder="请输入首次执业地" />
        </el-form-item>
        <el-form-item label="资格证类别
" prop="hrCertifyId">
          <el-input v-model="form.hrCertifyId" placeholder="请输入资格证类别
" />
        </el-form-item>
        <el-form-item label="资格证号码" prop="empCertifynum">
          <el-input v-model="form.empCertifynum" placeholder="请输入资格证号码" />
        </el-form-item>
        <el-form-item label="资格证取得方式" prop="empGainway">
          <el-input v-model="form.empGainway" placeholder="请输入资格证取得方式" />
        </el-form-item>
        <el-form-item label="资格证取得地" prop="empGainsite">
          <el-input v-model="form.empGainsite" placeholder="请输入资格证取得地" />
        </el-form-item>
        <el-form-item label="个人介绍" prop="empIntroduce">
          <el-input v-model="form.empIntroduce" placeholder="请输入个人介绍" />
        </el-form-item>
        <el-form-item label="入职时间" prop="empEntrydate">
          <el-date-picker clearable v-model="form.empEntrydate" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择入职时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="社保号码" prop="empSocialNum">
          <el-input v-model="form.empSocialNum" placeholder="请输入社保号码" />
        </el-form-item>
        <el-form-item label="合同开始" prop="empContStartdate">
          <el-date-picker clearable v-model="form.empContStartdate" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择合同开始">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="合同截止" prop="empContEnddate">
          <el-date-picker clearable v-model="form.empContEnddate" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择合同截止">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否参保" prop="empInsured">
          <el-input v-model="form.empInsured" placeholder="请输入是否参保" />
        </el-form-item>
        <el-form-item label="手机号码" prop="empPhone">
          <el-input v-model="form.empPhone" placeholder="请输入手机号码" />
        </el-form-item>
        <el-form-item label="联系地址" prop="empSite">
          <el-input v-model="form.empSite" placeholder="请输入联系地址" />
        </el-form-item>
        <el-form-item label="联系电话" prop="empContactPhone">
          <el-input v-model="form.empContactPhone" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="邮箱" prop="empEmail">
          <el-input v-model="form.empEmail" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="邮政编码" prop="empPostcode">
          <el-input v-model="form.empPostcode" placeholder="请输入邮政编码" />
        </el-form-item>
        <el-form-item label="QQ号码" prop="empQq">
          <el-input v-model="form.empQq" placeholder="请输入QQ号码" />
        </el-form-item>
        <el-form-item label="微信号" prop="empWechat">
          <el-input v-model="form.empWechat" placeholder="请输入微信号" />
        </el-form-item>
        <el-divider content-position="center">${subTable.functionName}信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddHrLawyerIdentity">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteHrLawyerIdentity">删除
            </el-button>
          </el-col>
        </el-row>
        <el-table :data="hrLawyerIdentityList" :row-class-name="rowHrLawyerIdentityIndex"
          @selection-change="handleHrLawyerIdentitySelectionChange" ref="hrLawyerIdentity">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50" />
          <el-table-column label="$comment" prop="identityTutor" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.identityTutor" placeholder="请输入$comment" />
            </template>
          </el-table-column>
          <el-table-column label="$comment" prop="identityType" width="150">
            <template slot-scope="scope">
              <el-select v-model="scope.row.identityType" placeholder="请选择$comment">
                <el-option label="请选择字典生成" value="" />
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="$comment" prop="identityStartdate" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.identityStartdate" type="date" value-format="yyyy-MM-dd"
                placeholder="请选择$comment" />
            </template>
          </el-table-column>
          <el-table-column label="$comment" prop="identityEnddate" width="240">
            <template slot-scope="scope">
              <el-date-picker clearable v-model="scope.row.identityEnddate" type="date" value-format="yyyy-MM-dd"
                placeholder="请选择$comment" />
            </template>
          </el-table-column>
          <el-table-column label="$comment" prop="identityNum" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.identityNum" placeholder="请输入$comment" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
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
      revise: false,
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
