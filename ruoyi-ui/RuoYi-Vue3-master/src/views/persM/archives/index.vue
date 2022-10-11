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
        <el-button size="mini" @click="handleAdd('律师')" v-hasPermi="['persM:archives:add']">添加律师资料</el-button>
        <el-button size="mini" @click="handleAdd('实习')" v-hasPermi="['persM:archives:add']">添加实习人员资料</el-button>
        <el-button size="mini" @click="handleAdd('辅助')" v-hasPermi="['persM:archives:add']">添加辅助人员资料</el-button>
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
                <el-dropdown-item @click="handleUpdate(scope.row)" v-hasPermi="['persM:archives:edit']">修改员工信息</el-dropdown-item>
                <el-dropdown-item @click="WorkExperience(scope.row.id)">查看工作经历</el-dropdown-item>
                <el-dropdown-item @click="Education(scope.row.id)">查看教育经历</el-dropdown-item>
                <el-dropdown-item @click="delArchivesUser(scope.row)">删除员工</el-dropdown-item>
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
        <el-descriptions-item label="资格证取得方式" align="center">{{data.empGainway == 'exam' ? '考试':'考核'}}
        </el-descriptions-item>
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
    <el-dialog :title="title" v-model="this.open">
      <div>
        <h2>基本信息</h2>
      </div>
      <el-form :inline="true" ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="姓名" prop="empName">
          <el-input v-model="form.empName" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="出生日期" prop="empBirthdate">
          <el-date-picker clearable v-model="form.empBirthdate" type="date" value-format="YYYY-MM-DD"
            placeholder="请选择出生日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="性别" style="width: 42%;">
          <el-radio-group v-model="form.empSex">
            <el-radio label="M">男</el-radio>
            <el-radio label="F">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="是否在职">
          <el-radio-group v-model="form.empWorkstatus">
            <el-radio label="Y">在职</el-radio>
            <el-radio label="N">离职</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="证件类型">
          <el-select v-model="form.empPaperstype">
            <el-option label="身份证" value="身份证" />
            <el-option label="回乡证" value="回乡证" />
            <el-option label="通行证" value="通行证" />
            <el-option label="护照" value="护照" />
            <el-option label="其他" value="其他" />
          </el-select>
        </el-form-item>
        <el-form-item label="证件号" prop="empPapersnum">
          <el-input v-model="form.empPapersnum" placeholder="请输入证件号" />
        </el-form-item>
        <el-form-item label="最高学历">
          <el-select v-model="form.empHigthEducation">
            <el-option label="无" value="无" />
            <el-option label="初中" value="初中" />
            <el-option label="高中" value="高中" />
            <el-option label="中技" value="中技" />
            <el-option label="中专" value="中专" />
            <el-option label="大专" value="大专" />
            <el-option label="本科" value="本科" />
            <el-option label="双学位" value="双学位" />
            <el-option label="硕士" value="硕士" />
            <el-option label="博士" value="博士" />
            <el-option label="硕士研究生" value="硕士研究生" />
            <el-option label="博士研究生" value="博士研究生" />
            <el-option label="其他" value="其他" />
          </el-select>
        </el-form-item>
        <el-form-item label="最高学位">
          <el-select v-model="form.empHigthDegree">
            <el-option label="无" value="无" />
            <el-option label="学士学位" value="学士学位" />
            <el-option label="双学士学位" value="双学士学位" />
            <el-option label="硕士学位" value="硕士学位" />
            <el-option label="双硕士学位" value="双硕士学位" />
            <el-option label="博士学位" value="博士学位" />
          </el-select>
        </el-form-item>
        <el-form-item label="所内身份" prop="empPosition">
          <el-select v-model="form.empPosition">
            <el-option label="无" value="无" />
            <el-option label="合伙人" value="合伙人" />
            <el-option label="行政主管" value="行政主管" />
            <el-option label="股东" value="股东" />
            <el-option label="律师" value="律师" />
            <el-option label="实习律师" value="实习律师" />
          </el-select>
        </el-form-item>
        <el-form-item label="民族" prop="hrNationId">
          <el-select v-model="form.hrNationId">
            <el-option value="">选择民族</el-option>
            <el-option value="汉族">汉族</el-option>
            <el-option value="壮族">壮族</el-option>
            <el-option value="满族">满族</el-option>
            <el-option value="回族">回族</el-option>
            <el-option value="苗族">苗族</el-option>
            <el-option value="维吾尔族">维吾尔族</el-option>
            <el-option value="土家族">土家族</el-option>
            <el-option value="彝族">彝族</el-option>
            <el-option value="蒙古族">蒙古族</el-option>
            <el-option value="藏族">藏族</el-option>
            <el-option value="布依族">布依族</el-option>
            <el-option value="侗族">侗族</el-option>
            <el-option value="瑶族">瑶族</el-option>
            <el-option value="朝鲜族">朝鲜族</el-option>
            <el-option value="白族">白族</el-option>
            <el-option value="哈尼族">哈尼族</el-option>
            <el-option value="哈萨克族">哈萨克族</el-option>
            <el-option value="黎族">黎族</el-option>
            <el-option value="傣族">傣族</el-option>
            <el-option value="畲族">畲族</el-option>
            <el-option value="傈僳族">傈僳族</el-option>
            <el-option value="仡佬族">仡佬族</el-option>
            <el-option value="东乡族">东乡族</el-option>
            <el-option value="高山族">高山族</el-option>
            <el-option value="拉祜族">拉祜族</el-option>
            <el-option value="水族">水族</el-option>
            <el-option value="佤族">佤族</el-option>
            <el-option value="纳西族">纳西族</el-option>
            <el-option value="羌族">羌族</el-option>
            <el-option value="土族">土族</el-option>
            <el-option value="仫佬族">仫佬族</el-option>
            <el-option value="锡伯族">锡伯族</el-option>
            <el-option value="柯尔克孜族">柯尔克孜族</el-option>
            <el-option value="达斡尔族">达斡尔族</el-option>
            <el-option value="景颇族">景颇族</el-option>
            <el-option value="毛南族">毛南族</el-option>
            <el-option value="撒拉族">撒拉族</el-option>
            <el-option value="布朗族">布朗族</el-option>
            <el-option value="塔吉克族">塔吉克族</el-option>
            <el-option value="阿昌族">阿昌族</el-option>
            <el-option value="普米族">普米族</el-option>
            <el-option value="鄂温克族">鄂温克族</el-option>
            <el-option value="怒族">怒族</el-option>
            <el-option value="京族">京族</el-option>
            <el-option value="基诺族">基诺族</el-option>
            <el-option value="德昂族">德昂族</el-option>
            <el-option value="保安族">保安族</el-option>
            <el-option value="俄罗斯族">俄罗斯族</el-option>
            <el-option value="裕固族">裕固族</el-option>
            <el-option value="乌孜别克族">乌孜别克族</el-option>
            <el-option value="门巴族">门巴族</el-option>
            <el-option value="鄂伦春族">鄂伦春族</el-option>
            <el-option value="独龙族">独龙族</el-option>
            <el-option value="塔塔尔族">塔塔尔族</el-option>
            <el-option value="赫哲族">赫哲族</el-option>
            <el-option value="珞巴族">珞巴族</el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="政治面貌">
          <el-select v-model="form.hrPoliticalId">
            <el-option value="">请选择</el-option>
            <el-option value="中共党员">中共党员</el-option>
            <el-option value="中共预备党员">中共预备党员</el-option>
            <el-option value="共青团员">共青团员</el-option>
            <el-option value="民革党员">民革党员</el-option>
            <el-option value="民盟盟员">民盟盟员</el-option>
            <el-option value="民建会员">民建会员</el-option>
            <el-option value="民进会员">民进会员</el-option>
            <el-option value="农工党党员">农工党党员</el-option>
            <el-option value="致公党党员">致公党党员</el-option>
            <el-option value="九三学社社员">九三学社社员</el-option>
            <el-option value="台盟盟员">台盟盟员</el-option>
            <el-option value="无党派">无党派</el-option>
            <el-option value="群众">群众</el-option>
            <el-option value="民促会">民促会</el-option>
            <el-option value="其他">其他</el-option>
            <el-option value="工商联">工商联</el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="婚姻情况" prop="empMaritalsta">
          <el-select v-model="form.empMaritalsta">
            <el-option value="">请选择</el-option>
            <el-option value="未婚">未婚</el-option>
            <el-option value="已婚">已婚</el-option>
            <el-option value="离异">离异</el-option>
            <el-option value="丧偶">丧偶</el-option>
            <el-option value="无配偶">无配偶</el-option>
            <el-option value="同居">同居</el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="门禁号" prop="empEntrancenum">
          <el-input v-model="form.empEntrancenum" placeholder="请输入门禁号" />
        </el-form-item>
        <el-form-item label="专业部" prop="hrSpecialtyId">
          <el-select v-model="form.hrSpecialtyId">
            <el-option value="">请选择</el-option>
            <el-option value="行政与政府法律顾问部">行政与政府法律顾问部</el-option>
            <el-option value="建筑与房地产专业部">建筑与房地产专业部</el-option>
            <el-option value="知识产权部">知识产权部</el-option>
            <el-option value="婚姻与家庭专业部">婚姻与家庭专业部</el-option>
            <el-option value="公司与企业法律顾问部">公司与企业法律顾问部</el-option>
            <el-option value="金融与担保部">金融与担保部</el-option>
            <el-option value="劳动与社会保障部">劳动与社会保障部</el-option>
            <el-option value="企业重整重组清算部">企业重整重组清算部</el-option>
            <el-option value="村社区专业部">村社区专业部</el-option>
            <el-option value="保险诉讼部">保险诉讼部</el-option>
            <el-option value="金融与资产处置部">金融与资产处置部</el-option>
          </el-select>
        </el-form-item>
        <div>
          <h2>从业资料</h2>
        </div>
        <el-form-item label="执业类别" prop="hrPractiseId">
          <el-select v-model="form.hrPractiseId">
            <el-option value="">选择类别</el-option>
            <el-option value="专职">专职</el-option>
            <el-option value="兼职">兼职</el-option>
            <el-option value="公司">公司</el-option>
            <el-option value="公职">公职</el-option>
            <el-option value="法援">法援</el-option>
            <el-option value="香港">香港</el-option>
            <el-option value="澳门">澳门</el-option>
            <el-option value="台湾">台湾</el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="执业证号码" prop="empPracnum">
          <el-input v-model="form.empPracnum" placeholder="请输入执业证号码" />
        </el-form-item>
        <el-form-item label="首次执业时间" prop="empPracdate">
          <el-date-picker clearable v-model="form.empPracdate" type="date" value-format="YYYY-MM-DD"
            placeholder="请选择首次执业时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="首次执业地" prop="empPracsite">
          <el-input v-model="form.empPracsite" placeholder="请输入首次执业地" />
        </el-form-item>
        <el-form-item label="资格证类别" prop="hrCertifyId">
          <el-select v-model="form.hrCertifyId">
            <el-option value="">选择类别</el-option>
            <el-option value="法律职业资格证">法律职业资格证</el-option>
            <el-option value="律师职业资格证">律师职业资格证</el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="资格证号码" prop="empCertifynum">
          <el-input v-model="form.empCertifynum" placeholder="请输入资格证号码" />
        </el-form-item>
        <el-form-item label="资格证取得方式" prop="empGainway">
          <el-select v-model="form.empGainway">
            <el-option value="">选择类别</el-option>
            <el-option value="check" label="考核" />
            <el-option value="exam" label="考试" />
          </el-select>
        </el-form-item>
        <el-form-item label="资格证取得地" prop="empGainsite">
          <el-input v-model="form.empGainsite" placeholder="请输入资格证取得地" />
        </el-form-item>
        <el-form-item label="个人介绍" prop="empIntroduce">
          <el-input type="textarea" v-model="form.empIntroduce" placeholder="请输入个人介绍" />
        </el-form-item>
        <div>
          <h2>入职情况</h2>
        </div>
        <el-form-item label="入职时间" prop="empEntrydate">
          <el-date-picker clearable v-model="form.empEntrydate" type="date" value-format="YYYY-MM-DD"
            placeholder="请选择入职时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="社保号码" prop="empSocialNum">
          <el-input v-model="form.empSocialNum" placeholder="请输入社保号码" />
        </el-form-item>
        <el-form-item label="合同开始" prop="empContStartdate">
          <el-date-picker clearable v-model="form.empContStartdate" type="date" value-format="YYYY-MM-DD"
            placeholder="请选择合同开始">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="合同截止" prop="empContEnddate">
          <el-date-picker clearable v-model="form.empContEnddate" type="date" value-format="YYYY-MM-DD"
            placeholder="请选择合同截止">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否参保" prop="empInsured">
          <el-radio-group v-model="form.empInsured">
            <el-radio label="是">是</el-radio>
            <el-radio label="否">否</el-radio>
          </el-radio-group>
        </el-form-item>
        <div>
          <h2>联系信息</h2>
        </div>
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
      </el-form>
      <div slot="footer" class="dialog-footer" style="text-align: center;">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog v-model="this.WorkExperiences" title="查看工作经历" width="65%">
      <div style="height: 50px;">
        <el-button @click="handleAddWork">添加工作经历</el-button>
      </div>
      <el-table v-loading="loading" :data="workhistoryList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" align="center" />
        <el-table-column label="序号" align="center" prop="id" width="100" />
        <el-table-column label="起止时间" align="center" prop="workhistoryStartdate" width="100">
          <template #default="scope">
            <span>{{ parseTime(scope.row.workhistoryStartdate, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="结束时间" align="center" prop="workhistoryEnddate" width="100">
          <template #default="scope">
            <span>{{ parseTime(scope.row.workhistoryEnddate, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="工作单位" align="center" prop="workhistoryWorkunit" width="100" />
        <el-table-column label="工作职务" align="center" prop="workhistoryJobPosition" width="100" />
        <el-table-column label="工作类别" align="center" prop="workhistoryWorktype" width="100" />
        <el-table-column label="单位性质" align="center" prop="workhistoryWorknature" width="100" />
        <el-table-column label="所在地" align="center" prop="workhistorySite" width="100" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="100">
          <template #default="scope">
            <el-button type="text" @click="handleUpdateWork(scope.row)"> 修改
            </el-button>
            <el-button type="text" @click="handleDeleteWork(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>

    <!-- 添加或修改工作经历对话框 -->
    <el-dialog :title="title" v-model="openWork">
      <el-form :inline="true" ref="formWork" :model="formWork" :rules="rules" label-width="80px">
        <el-form-item label="起止时间" prop="workhistoryStartdate">
          <el-date-picker clearable v-model="formWork.workhistoryStartdate" type="date" value-format="YYYY-MM-DD"
            placeholder="请选择起止时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="workhistoryEnddate">
          <el-date-picker clearable v-model="formWork.workhistoryEnddate" type="date" value-format="YYYY-MM-DD"
            placeholder="请选择结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="工作单位" prop="workhistoryWorkunit">
          <el-input v-model="formWork.workhistoryWorkunit" placeholder="请输入工作单位" />
        </el-form-item>
        <el-form-item label="工作职务" prop="workhistoryJobPosition">
          <el-input v-model="formWork.workhistoryJobPosition" placeholder="请输入工作职务" />
        </el-form-item>
        <el-form-item label="工作类别">
          <el-select v-model="formWork.workhistoryWorktype">
            <el-option value="">请选择…</el-option>
            <el-option value="律师工作">律师工作</el-option>
            <el-option value="法官">法官</el-option>
            <el-option value="检察官">检察官</el-option>
            <el-option value="法律工作">法律工作</el-option>
            <el-option value="警察">警察</el-option>
            <el-option value="公务员">公务员</el-option>
            <el-option value="其他">其他</el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="单位性质">
          <el-select v-model="formWork.workhistoryWorknature">
            <el-option value="">请选择…</el-option>
            <el-option value="党委">党委</el-option>
            <el-option value="政府">政府</el-option>
            <el-option value="人大">人大</el-option>
            <el-option value="政协">政协</el-option>
            <el-option value="公安">公安</el-option>
            <el-option value="检察院">检察院</el-option>
            <el-option value="法院">法院</el-option>
            <el-option value="司法行政机关">司法行政机关</el-option>
            <el-option value="群团组织">群团组织</el-option>
            <el-option value="企业">企业</el-option>
            <el-option value="社会团体">社会团体</el-option>
            <el-option value="其他">其他</el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="所在地" prop="workhistorySite">
          <el-input v-model="formWork.workhistorySite" placeholder="请输入所在地" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" style="text-align: center;">
        <el-button type="primary" @click="submitFormWork">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog v-model="this.Educations" title="查看教育经历" width="65%">
      <el-table v-loading="loading" :data="degreeList" @selection-change="handleSelectionChange">
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="开始日期" align="center" prop="degreeStartdate" width="100">
        <template #default="scope">
          <span>{{ parseTime(scope.row.degreeStartdate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束日期" align="center" prop="degreeEnddate" width="100">
        <template #default="scope">
          <span>{{ parseTime(scope.row.degreeEnddate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="毕业学校" align="center" prop="degreeSchool" />
      <el-table-column label="主修专业" align="center" prop="degreeMajor" />
      <el-table-column label="获得学历" align="center" prop="degreeEducation" />
      <el-table-column label="获得学位" align="center" prop="degreeDegrees" />
      <el-table-column label="证书编号" align="center" prop="degreeCrednum" />
      <el-table-column label="全日制" align="center" prop="degreeFulltime">
        <template v-slot="scope">
          <span v-if="scope.row.degreeFulltime == 'Y'">是</span>
          <span v-if="scope.row.degreeFulltime == 'N'">否</span>
        </template>
      </el-table-column>
      <el-table-column label="所在地" align="center" prop="degreeSite" />
      </el-table>
    </el-dialog>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

  </div>
</template>
  
<script>

import { listArchives, getArchives, delArchives, addArchives, updateArchives, getWorkArchives, updateWorkhistory, getWork, addWorkhistory,delWorkhistory,getDegree} from "@/api/persM/archives";


export default {
  name: "Archives",
  data() {
    return {
      // 遮罩层
      loading: true,
      //律师信息弹出框
      ViewDetails: false,
      WorkExperiences: false,
      Educations: false,
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
      //工作经历
      workhistoryList: [],
      //学历经历
      degreeList: [],
      // ${subTable.functionName}表格数据
      //hrLawyerIdentityList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      openWork: false,
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
      form: {
      },
      // 表单校验
      rules: {
      },
      rules2: {
      },
      formWork: {
        id: null,
        workhistoryStartdate: null,
        workhistoryEnddate: null,
        workhistoryWorkunit: null,
        workhistoryJobPosition: null,
        workhistoryWorktype: null,
        workhistoryWorknature: null,
        workhistorySite: null,
        hrEmpId: null
      },
      hrEmpId: undefined
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
      this.openWork = false;
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
      };
      this.hrLawyerIdentityList = [];
      this.resetForm("form");
    },
    resetWork() {
      this.formWork = {
        id: null,
        workhistoryStartdate: null,
        workhistoryEnddate: null,
        workhistoryWorkunit: null,
        workhistoryJobPosition: null,
        workhistoryWorktype: null,
        workhistoryWorknature: null,
        workhistorySite: null,
        hrEmpId: null
      };
      this.resetForm("formWork");
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
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          //this.form.hrLawyerIdentityList = this.hrLawyerIdentityList;
          if (this.form.id != null) {
            updateArchives(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addArchives(this.form).then(response => {
              if (response.code == 200) {
                this.$modal.msgSuccess("新增成功");
              } else {
                this.$modal.msgError("新增失败");
              }
              this.open = false;
              this.getList();
            });
          }
        }
      });
      // console.log(this.form);
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getArchives(id).then(response => {
        this.form = response.data;
        if (response.data.empGainway == 'check') {
          this.form.empGainway = '考核';
        } else {
          this.form.empGainway = '考试';
        }

        //this.hrLawyerIdentityList = response.data.hrLawyerIdentityList;
        this.open = true;
        this.title = "修改人事档案";
      });
    },
    /** 新增按钮操作 */
    handleAdd(type) {
      this.reset();
      this.open = true;
      this.title = "添加人事档案";
      this.form.identityName = type;

    },
    WorkExperience(id) {
      this.WorkExperiences = true;
      this.hrEmpId = id;
      getWorkArchives(id).then(response => {
        this.workhistoryList = response.rows;
        console.log(this.workhistoryList)
      })
    },
    Education(id) {
      this.Educations = true;
      this.hrEmpId = id;
      getDegree(id).then(response => {
        this.degreeList  = response.rows;
      })
    },
    /** 提交按钮 */
    submitFormWork() {
      this.formWork.hrEmpId = this.hrEmpId;
      this.$refs["formWork"].validate(valid => {
        if (valid) {
          if (this.formWork.id != null) {
            updateWorkhistory(this.formWork).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.openWork = false;
              this.WorkExperience(this.formWork.hrEmpId);
            });
          } else {
            addWorkhistory(this.formWork).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.openWork = false;
              this.WorkExperience(this.hrEmpId);
            });
          }
        }
      });
    },
    /** 修改按钮操作 */
    handleUpdateWork(row) {
      this.resetWork();
      const id = row.id || this.ids
      getWork(id).then(response => {
        this.formWork = response.data;
        this.openWork = true;
        this.title = "修改工作经历";
      });
    },
    /** 新增按钮操作 */
    handleAddWork() {
      this.resetWork();
      this.openWork = true;
      this.title = "添加人事档案";
    },
    handleDeleteWork(row){
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除工作经历编号为"' + ids + '"的数据项？').then(function() {
        return delWorkhistory(ids);
      }).then(() => {
        this.WorkExperience(this.hrEmpId);
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    delArchivesUser(row){
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除编号为"' + ids + '"的员工？').then(function() {
        return delArchives(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
}
</script>

<style>
.el-input {
  width: 200px;
}

.el-date-editor.el-input,
.el-date-editor.el-input__inner {
  width: 200px;
}

.el-textarea {
  position: relative;
  display: inline-block;
  width: 535px;
  vertical-align: bottom;
  font-size: var(--el-font-size-base);
}
</style>
