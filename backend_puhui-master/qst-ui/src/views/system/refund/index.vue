<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="企业名称" prop="refComNo">
        <el-select v-model="queryParams.refComNo" filterable placeholder="请选择企业名称" clearable size="small">
          <el-option
            v-for="dict in companiesName"
            :key="dict.comId"
            :label="dict.comName"
            :value="dict.comId"
            @keyup.enter.native="handleQuery"/>
        </el-select>
      </el-form-item>
      <el-form-item label="银行名称" prop="refBankId">
        <el-select v-model="queryParams.v" placeholder="请选择银行名称" filterable clearable size="small">
          <el-option
            v-for="dict in bankNames"
            :key="dict.bankId"
            :label="dict.bankName"
            :value="dict.bankId"
            @keyup.enter.native="handleQuery"/>
        </el-select>
      </el-form-item>
      <el-form-item label="贷款编号" prop="refLoId">
        <el-select v-model="queryParams.refLoId" placeholder="请选择贷款编号" filterable clearable size="small">
          <el-option
            v-for="dict in loansNoOptions"
            :key="dict.loaId"
            :label="dict.loaNo"
            :value="dict.loaId"
            @keyup.enter.native="handleQuery"/>
        </el-select>
      </el-form-item>
      <el-form-item label="逾期ID" prop="refOveId">
        <el-input
          v-model="queryParams.refOveId"
          placeholder="请输入逾期ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="还款截止时间" prop="refTime" >
        <el-date-picker clearable size="small"
          v-model="queryParams.refTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择还款截止时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="还款金额" prop="refMoney">
        <el-input
          v-model="queryParams.refMoney"
          placeholder="请输入还款金额"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />

      </el-form-item>
      <el-form-item label="还款方式" prop="refMode">
        <el-select v-model="queryParams.refMode" placeholder="请选择还款方式" clearable size="small">
          <el-option
            v-for="dict in bankSendModes"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="还款状态" prop="refState">
        <el-select v-model="queryParams.refState" placeholder="请选择还款状态" clearable size="small">
          <el-option
            v-for="dict in refundStatus"
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
          v-hasPermi="['system:refund:add']"
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
          v-hasPermi="['system:refund:edit']"
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
          v-hasPermi="['system:refund:remove']"
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
          v-hasPermi="['system:refund:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="refundList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="refId" />
      <el-table-column label="企业名称" align="center" prop="company.comName" />
      <el-table-column label="银行名称" align="center" prop="bank.bankName" />
      <el-table-column label="贷款编号" align="center" prop="loans.loaNo" />
<!--      <el-table-column label="逾期ID" align="center" prop="refOveId" />-->
      <el-table-column label="还款截止时间" align="center" prop="refTime" width="200">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.refTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="还款金额" align="center" prop="refMoney" />
      <el-table-column label="还款方式" align="center" prop="refMode" >
        <template slot-scope="scope">
          <dict-tag :options="bankSendModes" :value="scope.row.refMode"/>
        </template>
      </el-table-column>
      <el-table-column label="还款状态" align="center" prop="refState" >
        <template slot-scope="scope">
          <dict-tag :options="refundStatus" :value="scope.row.refState"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:refund:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:refund:remove']"
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


    <!-- 添加或修改还款对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="企业名称" prop="refComNo">
          <el-select v-model="form.refComNo" placeholder="请选择企业名称" filterable>
            <el-option
              v-for="dict in companiesName"
              :key="dict.comId"
              :label="dict.comName"
              :value="dict.comId"

            />
          </el-select>
        </el-form-item>
        <el-form-item label="银行名称" prop="refBankId">
          <el-select v-model="form.refBankId" placeholder="请选择银行名称" filterable>
            <el-option
              v-for="dict in bankNames"
              :key="dict.bankId"
              :label="dict.bankName"
              :value="dict.bankId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="贷款编号" prop="refLoId">
          <el-select v-model="form.refLoId" placeholder="请选择贷款编号" filterable>
            <el-option
              v-for="dict in loansNoOptions"
              :key="dict.loaId"
              :label="dict.loaNo"
              :value="dict.loaId"
            />
          </el-select>
        </el-form-item>
<!--        <el-form-item label="逾期ID" prop="refOveId">
          <el-input v-model="form.refOveId" placeholder="请输入逾期ID" />
        </el-form-item>-->
        <el-form-item label="还款截止时间" prop="refTime">
          <el-date-picker clearable size="small"
            v-model="form.refTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择还款截止时间">
          </el-date-picker>
        </el-form-item>

        <el-form-item label="还款金额" prop="refMoney">
          <el-input v-model="form.refMoney" placeholder="请输入还款金额" />
        </el-form-item>
        <el-form-item label="还款方式" prop="refMode">
          <el-select v-model="form.refMode" placeholder="请选择还款方式">
            <el-option
              v-for="dict in bankSendModes"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue" />
          </el-select>
        </el-form-item>
        <el-form-item label="还款状态" prop="refState">
          <el-select v-model="form.refState" placeholder="请选择还款状态" @change="changeStatus1">
            <el-option
              v-for="dict in refundStatus"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue" />
          </el-select>
        </el-form-item>
        <!--   2022.1.22 增加实际还款时间     -->
        <el-form-item label="实际还款时间" prop="refActTime" >
          <el-date-picker clearable size="small"
                          v-model="form.refActTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择实际还款时间"
                          :picker-options="pickerOptions"   :disabled="timdDisable"
          >
          </el-date-picker>

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
import { listRefund, getRefund, delRefund, addRefund, updateRefund, exportRefund } from "@/api/system/refund";
import {getAptitudeCompanyName} from "@/api/system/aptitude";
import {getBankNames, getLoansNo} from "@/api/system/loans";

export default {
  name: "Refund",

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
      // 还款表格数据
      refundList: [],
      // 还款状态
      refundStatus: [],
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
        refComNo: null,
        refBankId: null,
        refLoId: null,
        refOveId: null,
        refTime: null,
        refMoney: null,
        refMode: null,
        refState: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        refComNo:[{ required: true, message: "企业名称不能为空", trigger: "blur" }],
        refBankId:[{ required: true, message: "银行名称不能为空", trigger: "blur" }],
        refLoId:[{ required: true, message: "贷款编号不能为空", trigger: "blur" }],
        refMode:[{ required: true, message: "还款方式不能为空", trigger: "blur" }],
        refState:[{ required: true, message: "还款状态不能为空", trigger: "blur" }],


      },
      timdDisable:true, // 控制 只有 还款状态 为 已还清 才可以选择 实际还款时间

      // 增加 当前日期的验证 2022.1.22
      pickerOptions:{
        disabledDate:(time)=>{
          return time.getTime() >= Date.now() - 8.64e6;
        }
      }
    };
  },
  created() {
    this.getList();
    this.getListLoansNo();
    this.getListComName();
    this.getListBankName();
    this.getDicts("sys_refund_status").then(response => {
      this.refundStatus = response.data;
    });
    this.getDicts("sys_send_mode").then(response => {
      this.bankSendModes = response.data;
    });
  },
  methods: {

    changeStatus1(value){
       if(value == 2){
         this.timdDisable=false;
       }else {
         this.timdDisable=true;
       }
    },

    /** 查询还款列表 */
    getList() {
      this.loading = true;
      listRefund(this.queryParams).then(response => {
        this.refundList = response.rows;
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
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        refId: null,
        refComNo: null,
        refBankId: null,
        refLoId: null,
        refOveId: null,
        refTime: null,
        refMoney: null,
        refMode: null,
        refState: null,
        refDel: null
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
      this.ids = selection.map(item => item.refId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加还款";
      this.timdDisable=true ;   // 设置 不可输入 实际还款时间 2022.1.22
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const refId = row.refId || this.ids
      getRefund(refId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改还款";

        // 2022.1.22
        if(this.form.refState==2){
          this.timdDisable=false;
        }else{
          this.timdDisable=true;
        }

      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.refId != null) {
            updateRefund(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRefund(this.form).then(response => {
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
      const refIds = row.refId || this.ids;
      this.$confirm('是否确认删除还款编号为"' + refIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delRefund(refIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有还款数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportRefund(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
