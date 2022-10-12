<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="" prop="systemUserRecipients" style="width: 180px;" >
        <el-input
          v-model="queryParams.systemUserRecipients"
          placeholder="请输入发件人"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>

      <el-form-item label="" prop="collarPerson" style="width: 180px;" >
        <el-input
          v-model="queryParams.collarPerson"
          placeholder="请输入收件人"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
       
     

     <el-form-item  prop="expressageSendWaayId">
      <el-select v-model="queryParams.expressageSendWaayId" placeholder="选择快递公司" style="width: 180px;"  clearable>
          <el-option value="">选择快递公司</el-option>
          <el-option v-for="t in sendTypeList" :key="t.id"  :label="t.sendName" :value="t.id"></el-option>
     </el-select>
     </el-form-item>


     <el-form-item prop="expressageExpressTypeId">

      <el-select v-model="queryParams.expressageExpressTypeId" placeholder="选择快件类型" style="width: 180px;" clearable >
          <el-option value="">选择快件类型</el-option>
          <el-option v-for="t in expressList" :key="t.id"  :label="t.expressName" :value="t.id"></el-option>
     </el-select>

     </el-form-item>


     <el-form-item prop="takestatus">
      <el-select v-model="queryParams.takestatus" placeholder="领取状态">
        <el-option label="" value="" >选择快件类型</el-option>
        <el-option label="未领取" value="1" />
        <el-option label="已领取" value="2" />
      </el-select>
     </el-form-item>


      <el-form-item label="日期" prop="selectStr">

        <el-date-picker clearable v-model="queryParams.selectStr" value-format="YYYY-MM-DD">
        </el-date-picker>

        <el-date-picker clearable v-model="queryParams.selectStrs" value-format="YYYY-MM-DD">
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
        <!-- <el-button  size="mini" @click="resetQuery">重置</el-button> -->
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
    <el-dialog :title="title" v-model="this.AddOpen" width="1000px" append-to-body draggable>
      <el-descriptions title="详细信息" :column="3" border>基本信息</el-descriptions>
      <el-form ref="form" :model="form" :rules="rules" >
        <el-row style="margin-left:15px;">
          <el-col :span="12">
            <el-form-item  prop="systemUserRecipients" style="width:450px;">
              <label><span style="color:red;">*</span>收件人：</label>
              <el-input v-model="form.systemUserRecipients"   @click="openSystemUser" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <!-- <el-form-item prop="expressageDateofreceipt" style="width:450px;">
              <label><span style="color:red;">*</span>收件日期：</label>
              <el-date-picker clearable v-model="queryParams.expressageDateofreceipt" value-format="YYYY-MM-DD"  style="width:450px;">
              </el-date-picker>
            </el-form-item> -->


            <el-form-item label="" prop="expressageDateofreceipt" style="width:450px;">
              <label><span style="color:red;">*</span>收件日期：</label>
              <el-date-picker clearable v-model="form.expressageDateofreceipt" value-format="YYYY-MM-DD"  placeholder="请选择收件日期" style="width:450px;">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row style="margin-left:15px;">
          <el-col :span="12">
            <el-form-item  prop="expressageSendWaayId" style="width:450px;">
              <label><span style="color:red;">*</span>快递公司：</label>
              <el-input v-model="form.expressageSendWaayId"  @click="openSendWaayFun" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item  prop="expressageExpressTypeId" style="width:450px;">
              <label><span style="color:red;">*</span>快件类型：</label>
              <el-input v-model="form.expressageExpressTypeId"  @click="openExpressFun"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row style="margin-left:15px;">
          <el-col :span="12">
            <el-form-item  prop="takestatus" style="width:450px;">
              <label><span style="color:red;">*</span>领取状态：</label>
              <el-select v-model="form.takestatus" style="width:450px;">
              <el-option label="未领取" value="1" ></el-option>
              <el-option label="已领取" value="2" ></el-option>
              
            </el-select>
            </el-form-item>
          </el-col>
        
        </el-row>

        <div class="demo-collapse" style="width:950px;margin:auto;">
          <el-collapse accordion>
            <el-collapse-item name="1">
              <template #title>
                <span style="font-size:16px;font-weight: bold;">其他选填</span>
              </template>
              <el-row style="margin-left:15px;">
                <el-col :span="12">
                  <el-form-item  prop="caseNo" style="width:450px;">
                    <label>案件：</label>
                    <el-input v-model="form.caseNo"  @click="openCaseLawFun" />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item  prop="expressageCourierNumber" style="width:450px;">
                    <label>快递单号：</label>
                    <el-input v-model="form.expressageCourierNumber"  />
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row style="margin-left:15px;">
                <el-col :span="12">
                  <el-form-item  prop="expressFee" style="width:450px;">
                    <label>快递费：</label>
                    <el-input v-model="form.expressFee"  />
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item  prop="collarPerson" style="width:450px;">
                    <label>领件人</label>
                    <el-input v-model="form.collarPerson" @click="openSystemUserljr" />
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row style="margin-left:15px;">
                <el-col :span="12">
                  <el-form-item label=""  prop="expressageFile">
                    <label>相关文件：</label>
                    <div style="width:450px;"><file-upload v-model="form.expressageFile"/></div>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="" prop="expressageBringDate" style="width:450px;">
                    <label><span style="color:red;">*</span>领件日期：</label>
                    <el-date-picker clearable v-model="form.expressageBringDate" value-format="YYYY-MM-DD"  placeholder="" style="width:450px;">
                    </el-date-picker>
                  </el-form-item>
                </el-col>
              </el-row>


              <el-row style="margin-left:15px;">
                <el-col :span="12">
                  <el-form-item  prop="expressageCollarnote" style="width:920px;">
                    <label>领件说明：</label>
                    <el-input v-model="form.expressageCollarnote"  />
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row style="margin-left:15px;">
                <el-col :span="12">
                  <el-form-item  prop="expressageRemark" style="width:920px;">
                    <label>其他备注说明：</label>
                    <!-- <el-input v-model="form.systemUserAddresser"  /> -->
                    <textarea rows="3" cols="90" class="form-control" style="width:920px;" v-model="form.expressageRemark"></textarea>
                  </el-form-item>
                </el-col>
              </el-row>

            </el-collapse-item>
           
          
          </el-collapse>
        </div>
      

        <!-- <el-form-item label="收件单位" prop="expressageSendaunit">
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
        </el-table>-->
      </el-form>
      <div slot="footer" class="dialog-footer" style="margin-left:15px;">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div> 
    </el-dialog> 

    <!-- 收件人弹出框 -->
    <el-dialog title="收件人" v-model="sjr" draggable>
      <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" >
        <el-form-item label="" prop="userRealname">
              <el-input
              v-model="queryParams.userRealname"
              placeholder="请输入姓名"
              clearable
             />
        </el-form-item>
          <el-form-item>
            <el-button type="primary"  @click="SystemhandleQuery">检索</el-button>
          </el-form-item>
        </el-form>
        <el-table :data="systemlist" ref="systemlist" border @select="SjrhandleSelectionChange">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="姓名" align="center" prop="userRealname" />
          <el-table-column label="手机号" align="center" prop="empPhone" />
          <el-table-column label="提成组" align="center" prop="">
            <span>-</span>
          </el-table-column>
          <el-table-column label="权限组" align="center" prop="roleName" />
      </el-table>
        <el-button @click="sjr = false">取消</el-button>
        <el-button @click="quding">确定</el-button>
      <pagination
          v-show="total>0"
          :total="total"
          v-model="queryParams.pageNum"
          v-model:limit="queryParams.pageSize"
          @pagination="openSystemUser"
        />
    </el-dialog>

    <!-- 领件人弹出框 -->
    <el-dialog title="收件人" v-model="ljr" draggable>
      <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" >
        <el-form-item label="" prop="userRealname">
              <el-input
              v-model="queryParams.userRealname"
              placeholder="请输入姓名"
              clearable
             />
        </el-form-item>
          <el-form-item>
            <el-button type="primary"  @click="SystemhandleQuery">检索</el-button>
          </el-form-item>
        </el-form>
        <el-table :data="systemlist" ref="systemlist" border @select="LjrhandleSelectionChange">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="姓名" align="center" prop="userRealname" />
          <el-table-column label="手机号" align="center" prop="empPhone" />
          <el-table-column label="提成组" align="center" prop="">
            <span>-</span>
          </el-table-column>
          <el-table-column label="权限组" align="center" prop="roleName" />
      </el-table>
        <el-button @click="sjr = false">取消</el-button>
        <el-button @click="qudinges">确定</el-button>
      <pagination
          v-show="total>0"
          :total="total"
          v-model="queryParams.pageNum"
          v-model:limit="queryParams.pageSize"
          @pagination="openSystemUser"
        />
    </el-dialog>


    <!-- 快递公司弹出框 -->
    <el-dialog title="设置管理" v-model="sendWaayDia" draggable width="1000px">
      <el-card>
        <el-tabs v-model="activeName" >
          <el-tab-pane label="列表" name="sendwaaylist" >
              <el-table border="1px" v-loading="loading" ref="sendlist" :data="sendWaayList" @select="handleSelectionChange">
                <el-table-column type="selection" width="55" align="center" />
                <!-- <el-table-column label="编号" width="150" align="center" prop="id"  /> -->
                <el-table-column label="排序" align="center" prop="sorting" />
                <el-table-column label="名称" align="center" prop="sendName" />
                <el-table-column label="操作" align="center" class-name="small-padding fixed-width" >

                    <template v-slot="scope">
                    <el-dropdown trigger="click">
                      <el-button>
                        操作<el-icon class="el-icon--right">
                          <arrow-down />
                        </el-icon>
                      </el-button>
                      <template #dropdown>
                        <el-dropdown-menu>
                          <el-dropdown-item @click="handleUpdateSendwaay(scope.row)">修改</el-dropdown-item>
                          <el-dropdown-item @click="handleDeleteSendWaay(scope.row)">删除</el-dropdown-item>
                        </el-dropdown-menu>
                      </template>
                    </el-dropdown>
                  </template>

                </el-table-column>
               
              </el-table> 

              <el-button @click="sendWaayDia = false">取消</el-button>
              <el-button @click="qudings">确定</el-button>

              <pagination
                  v-show="total>0"
                  :total="total"
                  v-model="queryParams.pageNum"
                  v-model:limit="queryParams.pageSize"
                  @pagination="openExpressFun"
                />
          </el-tab-pane>

          <el-tab-pane label="添加/编辑" name="sendwaayAdd">
             <div style="width:1000px;height:30px;border-bottom:1px solid gainsboro;margin-bottom: 20px;">
              <span style="font-size:15px;font-weight:bold;">基本信息</span>
            </div>
            <el-form :inline="true">
            <el-row style="margin-left:15px;">
              <el-col :span="12">
                <el-form-item  prop="sendName" style="width:400px;">
                  <label><span style="color:red;">*</span>名称：</label>
                  <el-input v-model="form.sendName"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item prop="sorting" style="width:400px;">
                  <label><span style="color:red;">*</span>排序：</label>
                  <el-input v-model="form.sorting"/>
                </el-form-item>
              </el-col>
            </el-row>
              
            <el-form-item style="margin-left:15px;">
              <el-button type="primary"  size="mini" @click="SendWaaysubmitForm">确定提交</el-button>
            </el-form-item>

            </el-form>

          </el-tab-pane>

        </el-tabs>
      </el-card>
        
    </el-dialog>



       <!-- 快递类型弹出框 -->
       <el-dialog title="设置管理" v-model="expressDia" draggable width="1000px">
      <el-card>
        <el-tabs v-model="expressName" >
          <el-tab-pane label="列表" name="expresslist" >
              <el-table border="1px" v-loading="loading" ref="expresslist" :data="expressList" @select="ExpresshandleSelectionChange">
                <el-table-column type="selection" width="55" align="center" />
                <!-- <el-table-column label="编号" width="150" align="center" prop="id"  /> -->
                <el-table-column label="排序" align="center" prop="sorting" />
                <el-table-column label="名称" align="center" prop="expressName" />
                <el-table-column label="操作" align="center" class-name="small-padding fixed-width" >

                    <template v-slot="scope">
                    <el-dropdown trigger="click">
                      <el-button>
                        操作<el-icon class="el-icon--right">
                          <arrow-down />
                        </el-icon>
                      </el-button>
                      <template #dropdown>
                        <el-dropdown-menu>
                          <el-dropdown-item @click="handleUpdateExpress(scope.row)">修改</el-dropdown-item>
                          <el-dropdown-item @click="handleDeleteExpress(scope.row)">删除</el-dropdown-item>
                        </el-dropdown-menu>
                      </template>
                    </el-dropdown>
                  </template>

                </el-table-column>
               
              </el-table> 

              <el-button @click="expressDia = false">取消</el-button>
              <el-button @click="qudingss">确定</el-button>

              <pagination
                  v-show="total>0"
                  :total="total"
                  v-model="queryParams.pageNum"
                  v-model:limit="queryParams.pageSize"
                  @pagination="openExpressFun"
                />
          </el-tab-pane>

          <el-tab-pane label="添加/编辑" name="expressAdd">
             <div style="width:1000px;height:30px;border-bottom:1px solid gainsboro;margin-bottom: 20px;">
              <span style="font-size:15px;font-weight:bold;">基本信息</span>
            </div>
            <el-form :inline="true">
            <el-row style="margin-left:15px;">
              <el-col :span="12">
                <el-form-item  prop="expressName" style="width:400px;">
                  <label><span style="color:red;">*</span>名称：</label>
                  <el-input v-model="form.expressName"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item prop="sorting" style="width:400px;">
                  <label><span style="color:red;">*</span>排序：</label>
                  <el-input v-model="form.sorting"/>
                </el-form-item>
              </el-col>
            </el-row>
              
            <el-form-item style="margin-left:15px;">
              <el-button type="primary"  size="mini" @click="ExpresssubmitForm">确定提交</el-button>
            </el-form-item>

            </el-form>

          </el-tab-pane>

        </el-tabs>
      </el-card>
        
    </el-dialog>


     <!-- 案件弹出框 -->
     <el-dialog title="案件选择" v-model="caselawDia" width="1000px"  draggable >
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="" prop="caseStr">
              <el-input
              v-model="queryParams.caseStr"
              placeholder=""
              clearable
              />
        </el-form-item>
        <el-form-item  prop="caseType">
          <el-select v-model="queryParams.caseType" placeholder="">
              <el-option label="案号搜索" value="1" />
              <el-option label="委托人搜索" value="2" />
              <el-option label="对方当事人搜索" value="3" />
              <el-option label="案由搜索" value="4" />
              <el-option label="承办律师搜索" value="5" />
              <el-option label="受理法院搜索" value="6" />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="mini" @click="CasehandleQuery">搜索</el-button>
          <el-button size="mini" @click="CaseresetQuery">重置</el-button>
        </el-form-item>
      </el-form>

        <el-table v-loading="loading" :data="lawList" @selection-change="CaselawhandleSelectionChange">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="案号" align="center" prop="caseNo" />
          <el-table-column label="委托人" align="center" prop="caseParties" />
          <el-table-column label="对方当事人" align="center" prop="caseOppositeParties" />
          <el-table-column label="已付金额" align="center" prop="casePaidsal" />
          <el-table-column label="已开票金额" align="center" prop="caseInvoiced" />
          <el-table-column label="承办律师" align="center" prop="caseSubmitter" />
          <el-table-column label="收案时间" align="center" prop="collectionTime" width="180">
            <template v-slot="scope">
              <span>{{ parseTime(scope.row.collectionTime, '{y}-{m}-{d}') }}</span>
            </template>
          </el-table-column>
      </el-table>
      <el-button @click="caselawDia = false">取消</el-button>
      <el-button @click="qudingsss">确定</el-button>
        <pagination
        v-show="total>0"
        :total="total"
        v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize"
        @pagination="openCaseLawFun"
      />

    </el-dialog>


  </div>
</template>



<script>
import { listExpressage, getExpressage, delExpressage, addExpressage, updateExpressage,sendList,expressList,getSendwaay,addSendwaay,updateSendwaay,delSendwaay } from "@/api/hr/expressage";
import { listUser } from "@/api/hr/user";
import {getExpress, addExpress, updateExpress ,delExpress} from "@/api/hr/express"
import { listHrLaw } from "@/api/hr/HrLaw";

export default {
  name: "expressage",
  data() {
    return {
      // 选中选项卡的 name
      activeName: "sendwaaylist",
      expressName: "expresslist",
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      sendwaaylist: [],
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
      lawList:[],
      //收件人
      systemlist:[],
      // 发送表格数据
      sendWaayList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      AddOpen: false,
      sendWaayDia:false,
      sjr:false,
      ljr:false,
      expressDia:false,
      caselawDia:false,
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
        selectStrs:null,
        userRealname:null,
        caseType:null,
        caseStr:null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      //收件人复选框
      systemlist:[],
      quding1: {},
      //快递公司复选框
      sendlist:[],
      quding2: {},
      //快递类型复选框
      expresslist:[],
      quding3: {},
      //案件复选框
      lawList:[],
      quding4: {},
      //领件人复选框
       systemlist:[],
      quding5: {},
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
      this.AddOpen = false;
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
        caseNo: null,
        sendName:null,
        sorting:null,
        userRealname:null,
        expressName:null,
      };
      this.sendWaayList = [];
      this.resetForm("form");
    },
    openSendWaayFun(){//快递公司查询
      this.sendWaayDia=true;
      sendList().then(res=>{ 
        console.log(res.rows);
         this.sendWaayList = res.rows
         this.total = res.total;
         this.$nextTick(()=>{
            this.$refs.sendlist.toggleRowSelection(this.checklist)
         })
      })
      

    },
    openSystemUser(){//收件人查询
      this.loading = true;
      this.sjr=true;
      listUser(this.queryParams).then(res =>{ 
        console.log(res.rows);
        //  this.systemlist = res.rows;
        this.systemlist = res.rows
         this.total = res.total;
         this.loading = false;
        //  this.checklist = this.systemlist[0]
         this.$nextTick(()=>{
            this.$refs.systemlist.toggleRowSelection(this.checklist)
         })
      });
    },
    openSystemUserljr(){//领件人查询
      this.loading = true;
      this.ljr=true;
      listUser(this.queryParams).then(res =>{ 
        console.log(res.rows);
        //  this.systemlist = res.rows;
        this.systemlist = res.rows
         this.total = res.total;
         this.loading = false;
        //  this.checklist = this.systemlist[0]
         this.$nextTick(()=>{
            this.$refs.systemlist.toggleRowSelection(this.checklist)
         })
      });
    },
    openExpressFun(){//快递类型查询
      this.expressDia=true;
      expressList().then(res =>{ 
        console.log(res.rows);
        //  this.systemlist = res.rows;
        this.expressList = res.rows
         this.total = res.total;
        //  this.checklist = this.systemlist[0]
         this.$nextTick(()=>{
            this.$refs.expresslist.toggleRowSelection(this.checklist)
         })
      });
    },
    openCaseLawFun(){//案件查询
      this.loading = true;
      this.caselawDia=true;
      listHrLaw(this.queryParams).then(res =>{ 
        console.log(res.rows);
        //  this.systemlist = res.rows;
        this.lawList = res.rows
         this.total = res.total;
         this.loading = false;
        //  this.checklist = this.lawList[0]
         this.$nextTick(()=>{
            this.$refs.lawList.toggleRowSelection(this.checklist)
         })
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    CasehandleQuery(){//案件
      this.queryParams.pageNum = 1;
      this.openCaseLawFun();
    },
    SystemhandleQuery() { //收件人
      this.queryParams.pageNum = 1;
      this.openSystemUser();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    CaseresetQuery() {
      this.resetForm("queryForm");
      this.openCaseLawFun();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.AddOpen = true;
      this.title = "添加快速登记";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      // this.reset();
      this.AddOpen = true;
      this.title = "修改快速登记";
      const id = row.id || this.ids
      getExpressage(id).then(response => {
        this.form = response.data;
        // this.expressageList = response.data.expressageList;
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.expressageList = this.expressageList;
          if (this.form.id != null) {
            updateExpressage(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.AddOpen = false;
              this.getList();
            });
          } else {
            addExpressage(this.form).then(response => {
              this.reset;
              this.$modal.msgSuccess("新增成功");
              this.AddOpen = false;
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
    /** 快递公司发送添加按钮操作 */
    handleAddSendWaay() {
      let obj = {};
      obj.sorting = "";
      obj.sendName = "";
      this.sendWaayList.push(obj);
    },
    /** 快递公司删除按钮操作 */
    handleDeleteSendWaay(row) {
        const ids = row.id || this.ids;
        this.$modal.confirm('是否确认删除快速登记编号为"' + ids + '"的数据项？').then(function() {
          return delSendwaay(ids);
        }).then(() => {
          this.openSendWaayFun();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
     
    },
      /** 快递公司修改按钮操作 */
      handleUpdateSendwaay(row) {
        const id = row.id || this.ids;
        this.activeName = "sendwaayAdd";
        getSendwaay(id).then(response => {
          this.form = response.data;
          this.sendWaayList = response.data.sendWaayList;

        });
    },
    /** 快递公司提交按钮 */
    SendWaaysubmitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.sendWaayList = this.sendWaayList;
          if (this.form.id != null) {
            updateSendwaay(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              // this.open = false;
              this.activeName = "sendwaaylist";
              this.openSendWaayFun();
            });
          } else {
            addSendwaay(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              // this.open = false;
              this.activeName = "sendwaaylist";
              this.openSendWaayFun();
            });
          }
        }
      });
    },
    // /** 复选框选中数据 */
    // handleSendWaaySelectionChange(selection) {
    //   this.sendwaaylist = selection.map(item => item.index)
    // },
     /** 快递类型新增按钮操作 */
    //  handleAdd() {
    //   this.reset();
    //   this.open = true;
    //   this.title = "添加快递类型";
    // },
    /** 快递类型修改按钮操作 */
    handleUpdateExpress(row) {
      const id = row.id || this.ids
      this.expressName = "expressAdd";
      getExpress(id).then(response => {
        this.form = response.data;
        // this.open = true;
        this.expressName = "expressAdd";
      });
    },
    /** 快递类型提交按钮 */
    ExpresssubmitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateExpress(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              // this.open = false;
              this.expressName = "expresslist";
              this.openExpressFun();
            });
          } else {
            addExpress(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              // this.open = false;
              this.expressName = "expresslist";
              this.openExpressFun();
            });
          }
        }
      });
    },
    /** 快递类型删除按钮操作 */
    handleDeleteExpress(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除快递类型编号为"' + ids + '"的数据项？').then(function() {
        return delExpress(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    Query(id){  //根据id查询
          this.open = true;
          getExpressage(id).then(response => {
            this.form = response.data;
            this.title = "收件详情";
          });
     },
        //收件人复选框
    SjrhandleSelectionChange(selection,row){
       if(selection.length > 1){
          const del_row = selection.shift()
          this.$refs.systemlist.toggleRowSelection(del_row,false);
       }
       this.checklist = selection[0];
       console.log(selection);
        this.quding1 = selection;
    },
    //收件人复选框
    LjrhandleSelectionChange(selection,row){
       if(selection.length > 1){
          const del_row = selection.shift()
          this.$refs.systemlist.toggleRowSelection(del_row,false);
       }
       this.checklist = selection[0];
       console.log(selection);
        this.quding5 = selection;
    },
     // 快递公司多选框选中数据
     handleSelectionChange(selection,row) {
      if(selection.length >1){
          const del_row = selection.shift()
          this.$refs.sendlist.toggleRowSelection(del_row,false);
       }
       this.checklist = selection[0];
       console.log(selection);
       this.quding2 = selection;
    },
     // 快递类型多选框选中数据
     ExpresshandleSelectionChange(selection,row) {
      if(selection.length >1){
          const del_row = selection.shift()
          this.$refs.expresslist.toggleRowSelection(del_row,false);
       }
       this.checklist = selection[0];
       console.log(selection);
       this.quding3 = selection;
    },
     // 案件多选框选中数据
     CaselawhandleSelectionChange(selection,row) {
      if(selection.length >1){
          const del_row = selection.shift()
          this.$refs.lawList.toggleRowSelection(del_row,false);
       }
       this.checklist = selection[0];
       console.log(selection);
       this.quding4 = selection;
    },
    //收件人复选框
    quding(){
      this.form.systemUserRecipients = this.quding1[0].userRealname;
      this.sjr = false;
    },
    //领件人复选框
    qudinges(){
      this.form.collarPerson = this.quding5[0].userRealname;
      this.ljr = false;
    },
     //快递公司复选框
     qudings(){
      this.form.expressageSendWaayId = this.quding2[0].id;
      this.sendWaayDia = false;
    },
    //快递类型复选框
    qudingss(){
      this.form.expressageExpressTypeId = this.quding3[0].id;
      this.expressDia = false;
    },
    //案件复选框
    qudingsss(){
      this.form.caseNo = this.quding4[0].caseNo;
      this.caselawDia = false;
    },
    
  }


}
</script>
