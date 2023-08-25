<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="银行名称" prop="senBankId">
        <el-select v-model="queryParams.senBankId" placeholder="请选择银行" filterable clearable size="small">
          <el-option
            v-for="dict in bankNames"
            :key="dict.bankId"
            :label="dict.bankName"
            :value="dict.bankId"
            @keyup.enter.native="handleQuery"/>
        </el-select>
      </el-form-item>
      <el-form-item label="贷款编号" prop="senLoaId">
        <el-select v-model="queryParams.senLoaId" placeholder="请选择贷款编号" filterable clearable size="small">
          <el-option
            v-for="dict in loansNoOptions"
            :key="dict.loaId"
            :label="dict.loaNo"
            :value="dict.loaId"
            @keyup.enter.native="handleQuery"/>
        </el-select>
      </el-form-item>
      <el-form-item label="企业名称" prop="senComId">
        <el-select v-model="queryParams.senComId" placeholder="请选择企业ID" filterable clearable size="small">
          <el-option
            v-for="dict in companiesName"
            :key="dict.comId"
            :label="dict.comName"
            :value="dict.comId"
            @keyup.enter.native="handleQuery"/>
        </el-select>
      </el-form-item>
      <el-form-item label="用户姓名" prop="senUname">
        <el-input
          v-model="queryParams.senUname"
          placeholder="请输入放款用户姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户证件" prop="senUid">
        <el-input
          v-model="queryParams.senUid"
          placeholder="请输入放款用户证件号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="放款金额" prop="senMoney">
        <el-input
          v-model="queryParams.senMoney"
          placeholder="请输入放款金额"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="放款时间" prop="senTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.senTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择放款时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="放款方式" prop="senMode">
        <el-select v-model="queryParams.senMode" placeholder="请选择放款方式" filterable clearable size="small">
          <el-option
            v-for="dict in bankSendModes"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="放款状态" prop="senStatus">
        <el-select v-model="queryParams.senStatus" placeholder="请选择放款状态" filterable clearable size="small">
          <el-option
            v-for="dict in bankSendStatus"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
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
          v-hasPermi="['system:send:add']"
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
          v-hasPermi="['system:send:edit']"
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
          v-hasPermi="['system:send:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['system:send:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="sendList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="45" align="center" />
      <el-table-column label="序号" align="center" prop="senId" />
      <el-table-column label="银行名称" align="center" prop="bank.bankName" />
      <el-table-column label="贷款编号" align="center" prop="loans.loaNo" />
      <el-table-column label="企业名称" align="center" prop="company.comName" />
      <el-table-column label="放款账号" align="center" prop="senAccount" />
      <el-table-column label="用户姓名" align="center" prop="senUname" />
      <el-table-column label="用户证件" align="center" prop="senUid" />
      <el-table-column label="放款金额" align="center" prop="senMoney" />
      <el-table-column label="放款时间" align="center" prop="senTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.senTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="放款方式" align="center" prop="senMode" >
        <template slot-scope="scope">
          <dict-tag :options="bankSendModes" :value="scope.row.senMode"/>
        </template>
      </el-table-column>
      <el-table-column label="放款" align="center" prop="senStatus">
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.senStatus"
            active-value="2"
            inactive-value="1"
            :disabled="scope.row.senStatus=='2'"
            @change="handleStatusChange(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="100">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:send:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:send:remove']"
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

    <!-- 添加或修改放款对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="银行名称" prop="senBankId">
          <el-select v-model="form.senBankId" placeholder="请选择银行" filterable>
            <el-option
              v-for="dict in bankNames"
              :key="dict.bankId"
              :label="dict.bankName"
              :value="dict.bankId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="贷款编号" prop="senLoaId">
          <el-select v-model="form.senLoaId" placeholder="请选择贷款编号" filterable>
            <el-option
              v-for="dict in loansNoOptions"
              :key="dict.loaId"
              :label="dict.loaNo"
              :value="dict.loaId"
              />
          </el-select>
        </el-form-item>
        <el-form-item label="企业ID" prop="senComId">
          <el-select v-model="form.senComId" placeholder="请选择企业ID" filterable>
            <el-option
              v-for="dict in companiesName"
              :key="dict.comId"
              :label="dict.comName"
              :value="dict.comId"
              />
          </el-select>
        </el-form-item>
        <el-form-item label="放款账号" prop="senAccount">
          <el-input v-model="form.senAccount" placeholder="请输入放款账号" />
        </el-form-item>
        <el-form-item label="用户姓名" prop="senUname">
          <el-input v-model="form.senUname" placeholder="请输入放款用户姓名" />
        </el-form-item>
        <el-form-item label="用户证件" prop="senUid">
          <el-input v-model="form.senUid" placeholder="请输入放款用户证件号" />
        </el-form-item>
        <el-form-item label="放款金额" prop="senMoney">
          <el-input v-model="form.senMoney" placeholder="请输入放款金额" />
        </el-form-item>
        <el-form-item label="放款时间" prop="senTime">
          <el-date-picker clearable size="small"
            v-model="form.senTime"
            type="date"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择放款时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="放款方式" prop="senMode">
          <el-select v-model="form.senMode" placeholder="请选择放款方式">
            <el-option
              v-for="dict in bankSendModes"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue" />
          </el-select>
        </el-form-item>
<!--        <el-form-item label="放款状态" prop="senStatus">
          <el-select v-model="form.senStatus" placeholder="请选择放款状态">
            <el-option
              v-for="dict in bankSendStatus"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue" />
          </el-select>
        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {listSend, getSend, delSend, addSend, updateSend, exportSend, changeSendStatus} from "@/api/system/send";
import {getAptitudeCompanyName} from "@/api/system/aptitude";
import {getBankNames, getLoansNo} from "@/api/system/loans";

export default {
  name: "Send",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
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
      // 放款表格数据
      sendList: [],
      //放款状态
      bankSendStatus: [],
      //放款方式
      bankSendModes: [],
      //贷款编号
      loansNoOptions: [],
      //企业名称
      companiesName: [],
      //银行
      bankNames:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        senBankId: null,
        senLoaId: null,
        senComId: null,
        senAccount: null,
        senUname: null,
        senUid: null,
        senMoney: null,
        senTime: null,
        senMode: null,
        senStatus: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        senAccount: [{
          pattern: /^(\d{16}|\d{19}|\d{17})$/,
          required: true, message: "放款账号格式不正确", trigger: "blur" }],
        senUid: [{
          pattern: /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}(\d|x|X)$/,
          required: true, message: "用户证件格式不正确", trigger: "blur" }],

        senBankId:[{ required: true, message: "银行名称不能为空", trigger: "blur" }],
        senLoaId:[{ required: true, message: "贷款编号不能为空", trigger: "blur" }],
        senComId:[{ required: true, message: "企业ID不能为空", trigger: "blur" }],
        senMode:[{ required: true, message: "放款方式不能为空", trigger: "blur" }],
      }
    };
  },
  created() {
    this.getList();
    this.getListLoansNo();
    this.getListComName();
    this.getListBankName();
    this.getDicts("sys_bankSend_status").then(response => {
      this.bankSendStatus = response.data;
    });
    this.getDicts("sys_send_mode").then(response => {
      this.bankSendModes = response.data;
    });
  },
  methods: {
    /** 查询放款列表 */
    getList() {
      this.loading = true;
      listSend(this.queryParams).then(response => {
        this.sendList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 查询已实名企业列表
    getListComName(){
      getAptitudeCompanyName().then(response => {
        this.companiesName = response.data;
      });
    },
    // 查询银行列表
    getListBankName(){
      getBankNames().then(response => {
        this.bankNames = response.data;
      });
    },
    // 查询贷款列表
    getListLoansNo(){
      getLoansNo().then(response => {
        this.loansNoOptions = response.data;
      });
    },
    // 用户状态修改
    handleStatusChange(row) {
      let text = row.senStatus === "2" ? "放款" : "停止放款";
      this.$confirm('确认要"' + text + '"给"' + row.senAccount + '"账号吗?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return changeSendStatus(row.senId, row.senBankId, row.senLoaId, row.senComId, row.senStatus);
      }).then(() => {
        this.msgSuccess(text + "成功");
      }).catch(function() {
        row.senStatus = row.senStatus === "2" ? "1" : "2";
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
        senId: null,
        senBankId: null,
        senLoaId: null,
        senComId: null,
        senAccount: null,
        senUname: null,
        senUid: null,
        senMoney: null,
        senTime: null,
        senMode: null,
        senStatus: null,
        senDel: null
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
      this.ids = selection.map(item => item.senId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加放款";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const senId = row.senId || this.ids
      getSend(senId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改放款";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.senId != null) {
            updateSend(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSend(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const senIds = row.senId || this.ids;
      this.$confirm('是否确认删除放款编号为"' + senIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delSend(senIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有放款数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportSend(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
