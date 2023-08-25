<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="企业名称" prop="loaComNo">
        <el-select v-model="queryParams.loaComNo" filterable placeholder="请选择企业名称" clearable size="small">
          <el-option
            v-for="dict in companiesName"
            :key="dict.comId"
            :label="dict.comName"
            :value="dict.comId"
            @keyup.enter.native="handleQuery"/>
        </el-select>
      </el-form-item>
      <el-form-item label="银行名称" prop="loaBankId">
        <el-select v-model="queryParams.loaBankId" filterable placeholder="请选择银行">
          <el-option
            v-for="dict in bankNames"
            :key="dict.bankId"
            :label="dict.bankName"
            :value="dict.bankId"
            @keyup.enter.native="handleQuery"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="贷款编号" prop="loaNo">
        <el-input
          v-model="queryParams.loaNo"
          placeholder="请输入贷款编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="贷款用途" prop="loaUse">
        <el-input
          v-model="queryParams.loaUse"
          placeholder="请输入贷款用途"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="贷款积分" prop="loaIntegral">
        <el-input
          v-model="queryParams.loaIntegral"
          placeholder="请输入贷款积分"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>-->
      <el-form-item label="贷款利率" prop="loaRate">
        <el-input
          v-model="queryParams.loaRate"
          placeholder="请输入贷款利率"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="贷款状态" prop="loaState">
        <el-select v-model="queryParams.loaState" placeholder="请选择状态" clearable size="small">
          <el-option
            v-for="dict in sysLoaStatus"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="贷款时间" prop="loaTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.loaTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择贷款时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="贷款金额" prop="loaMoney">
        <el-input
          v-model="queryParams.loaMoney"
          placeholder="请输入贷款金额"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="到期时间" prop="loaEndTime">
        <el-input
          v-model="queryParams.loaEndTime"
          placeholder="请输入贷款到期时间"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>-->
      <el-form-item label="贷款期限" prop="loaLisId">
        <el-input
          v-model="queryParams.loaLisId"
          placeholder="请输入贷款期限"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['system:loans:add']"
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
          v-hasPermi="['system:loans:edit']"
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
          v-hasPermi="['system:loans:remove']"
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
          v-hasPermi="['system:loans:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="loansList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="45" align="center" />
      <el-table-column label="编号" align="center" prop="loaId" />
      <el-table-column label="编号" align="center" prop="loaNo" />
      <el-table-column label="企业名称" align="center" prop="company.comName" />
      <el-table-column label="银行" align="center" prop="bank.bankName" />
      <el-table-column label="贷款期限(月)" align="center" prop="loaLisId" />
      <el-table-column label="贷款用途" align="center" prop="loaUse" />
<!--      <el-table-column label="贷款积分" align="center" prop="loaIntegral" />-->
      <el-table-column label="贷款利率" align="center" prop="loaRate" />
      <el-table-column label="贷款时间" align="center" prop="loaTime" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.loaTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="贷款金额(￥)" align="center" prop="loaMoney" />
<!--      <el-table-column label="到期时间" align="center" prop="loaEndTime" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.loaEndTime) }}</span>
        </template>
      </el-table-column>-->
      <el-table-column label="贷款状态" align="center" prop="loaState" >
        <template slot-scope="scope">
          <dict-tag :options="sysLoaStatus" :value="scope.row.loaState"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="150" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            :disabled="scope.row.loaState=='2'"
            v-hasPermi="['system:loans:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:loans:remove']"
          >删除</el-button>
          <el-dropdown size="mini" @command="(command) => handleCommand(command, scope.row)"
                       v-hasPermi="['system:loans:loan']" >
                <span class="el-dropdown-link">
                  <i class="el-icon-d-arrow-right el-icon--right"></i>审批
                </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="handleExamineDetails" icon="el-icon-circle-check"
                                v-hasPermi="['system:loans:loan']" :disabled="scope.row.loaState=='2'">进入审批
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
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

    <!-- 添加或修改贷款对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="企业名称" prop="loaComNo">
          <el-select v-model="form.loaComNo" filterable placeholder="请选择企业名称" clearable size="small">
            <el-option
              v-for="dict in companiesName"
              :key="dict.comId"
              :label="dict.comName"
              :value="dict.comId"/>
          </el-select>
        </el-form-item>
        <el-form-item label="银行" prop="loaBankId">
          <el-select v-model="form.loaBankId" filterable placeholder="请选择银行" clearable size="small">
            <el-option
              v-for="dict in bankNames"
              :key="dict.bankId"
              :label="dict.bankName"
              :value="dict.bankId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="贷款用途" prop="loaUse">
          <el-input v-model="form.loaUse" placeholder="请输入贷款用途" />
        </el-form-item>
<!--        <el-form-item label="贷款积分" prop="loaIntegral">
          <el-input v-model="form.loaIntegral" placeholder="请输入贷款积分" />
        </el-form-item>-->
        <el-form-item label="贷款利率" prop="loaRate">
          <el-input v-model="form.loaRate" placeholder="请输入贷款利率" />
        </el-form-item>
        <el-form-item label="贷款状态" prop="loaState">
<!--          <el-input v-model="form.loaState" placeholder="请输入贷款状态" />-->
          <el-select v-model="form.loaState" placeholder="请选择状态" clearable size="small">
            <el-option
              v-for="dict in sysLoaStatus"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue" />
          </el-select>
        </el-form-item>
        <el-form-item label="贷款时间" prop="loaTime">
          <el-date-picker clearable size="small"
            v-model="form.loaTime"
            type="date"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择贷款时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="贷款金额" prop="loaMoney">
          <el-input v-model="form.loaMoney" placeholder="请输入贷款金额" />
        </el-form-item>
<!--        <el-form-item label="到期时间" prop="loaEndTime">
          <el-date-picker clearable size="small"
                          v-model="form.loaEndTime"
                          type="date"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="选择到期时间（最少一个月）">
          </el-date-picker>
        </el-form-item>-->
        <el-form-item label="收款账户" prop="bAccountNo">
          <el-input v-model="form.bAccountNo" placeholder="请输入收款账户" />
        </el-form-item>
        <el-form-item label="收款人" prop="bName">
          <el-input v-model="form.bName" placeholder="请输入收款人" />
        </el-form-item>
        <el-form-item label="开户行" prop="bBankName">
          <el-input v-model="form.bBankName" placeholder="请输入开户行" />
        </el-form-item>
        <el-form-item label="身份证号" prop="bIdCard">
        <el-input v-model="form.bIdCard" placeholder="请输入身份证号" />
      </el-form-item>
        <el-form-item label="贷款期限" prop="loaLisId">
          <el-input v-model="form.loaLisId" placeholder="请输入贷款期限" />
        </el-form-item>
        <!--  2022.7.14  签约材料     -->
        <el-form-item label="签约材料" prop="loaSign" v-if="form.loaSign!=null">
          <el-image
            style="width: 100px; height: 100px"
            :src="'/dev-api'+form.loaSign"
            ></el-image>

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
import {
  listLoans,
  getLoans,
  delLoans,
  addLoans,
  updateLoans,
  exportLoans,
  getBankNames,
  getLoansNo
} from "@/api/system/loans";
import {getAptitudeCompanyName} from "@/api/system/aptitude";
import {getDetailsCompanyName} from "@/api/system/details";

export default {
  name: "Loans",
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
      // 贷款表格数据
      loansList: [],
      //企业名称
      companiesName: [],
      //银行列表
      bankNames: [],
      //贷款状态
      sysLoaStatus: [],
      loansNoOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        loaNo: null,
        loaComNo: null,
        loaBankId: null,
        loaLisId: null,
        loaUse: null,
        loaIntegral: null,
        loaRate: null,
        loaState: null,
        loaTime: null,
        loaMoney: null,
        loaEndTime: null,
        loaSign:null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        loaComNo:[{ required: true, message: "企业名称不能为空", trigger: "blur" }],
        loaBankId:[{ required: true, message: "银行不能为空", trigger: "blur" }],
        loaRate:[{ required: true, message: "贷款利率不能为空", trigger: "blur" }],
        loaState:[{ required: true, message: "贷款状态不能为空", trigger: "blur" }],
        loaMoney:[{ required: true, message: "贷款金额不能为空", trigger: "blur" }],
        loaLisId:[{ required: true, message: "贷款期限不能为空", trigger: "blur" }],
      }
    };
  },
  created() {
    this.getList();
    this.getListComName();
    this.getListBankName();
    this.getListLoansNo();
    this.getDicts("sys_loans_status").then(response => {
      this.sysLoaStatus = response.data;
    });
  },
  methods: {
    /** 查询贷款列表 */
    getList() {
      this.loading = true;
      listLoans(this.queryParams).then(response => {
        this.loansList = response.rows;
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
        loaId: null,
        loaNo: null,
        loaComNo: null,
        loaComName: null,
        loaBankId: null,
        loaLisId: null,
        loaUse: null,
        loaIntegral: null,
        loaRate: null,
        loaState: null,
        loaTime: null,
        loaMoney: null,
        loaEndTime: null,
        loaDel: null,
        bAccountNo: null,
        bName: null,
        bBankName: null,
        bIdCard: null,
        loaSign:null
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
      this.ids = selection.map(item => item.loaId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    // 更多操作触发
    handleCommand(command, row) {
      switch (command) {
        case "handleExamineDetails":
          this.handleExamineDetails(row);
          break;
        default:
          break;
      }
    },
    /** 查看详情操作 */
    handleExamineDetails: function (row) {
      const eId = row.loaId;
      this.$router.push("/loans/loanDetails/" + eId);
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加贷款";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const loaId = row.loaId || this.ids
      getLoans(loaId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改贷款";
      });
      /*getDetailsCompanyName(row.loaComNo).then(response => {
        this.form.loaComNo = response.companiesName;
      });*/
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
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.loaId != null) {
            // 2022.7.14 当用户loaSign有值, 上传合约材料后,后台才可以更改为 待放款
            if(this.form.loaState=='6'&&this.form.loaSign==null){

                   this.msgSuccess("还未上传合约资料,不允许更改为 待放款");
                    return;

            }

              updateLoans(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });

            /*updateLoans(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });*/
          } else {
            addLoans(this.form).then(response => {
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
      const loaIds = row.loaId || this.ids;
      this.$confirm('是否确认删除贷款编号为"' + loaIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delLoans(loaIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有贷款数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportLoans(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
