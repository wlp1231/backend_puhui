<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="企业名称" prop="oveComNo">
        <el-select v-model="queryParams.oveComNo" filterable  placeholder="请选择企业名称" clearable size="small">
          <el-option
            v-for="dict in companiesName"
            :key="dict.comId"
            :label="dict.comName"
            :value="dict.comId"
            @keyup.enter.native="handleQuery"/>
        </el-select>
      </el-form-item>
      <el-form-item label="贷款编号" prop="oveLoaId">
        <el-select v-model="queryParams.oveLoaId" filterable  placeholder="请选择贷款编号" clearable size="small">
          <el-option
            v-for="dict in loansNoOptions"
            :key="dict.loaId"
            :label="dict.loaNo"
            :value="dict.loaId"
            @keyup.enter.native="handleQuery"/>
        </el-select>
      </el-form-item>
      <el-form-item label="逾期金额" prop="oveMoney">
        <el-input
          v-model="queryParams.oveMoney"
          placeholder="请输入逾期金额"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始时间" prop="oveStartTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.oveStartTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择逾期开始时间">
        </el-date-picker>
      </el-form-item>

<!--      <el-form-item label="减少积分" prop="oveCutCount">
        <el-input
          v-model="queryParams.oveCutCount"
          placeholder="请输入减少积分数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>-->
      <el-form-item label="额外利息" prop="oveAddInt">
        <el-input
          v-model="queryParams.oveAddInt"
          placeholder="请输入额外利息"
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
          v-hasPermi="['system:overdue:add']"
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
          v-hasPermi="['system:overdue:edit']"
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
          v-hasPermi="['system:overdue:remove']"
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
          v-hasPermi="['system:overdue:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="overdueList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="oveId" />
      <el-table-column label="企业名称" align="center" prop="company.comName" />
      <el-table-column label="贷款编号" align="center" prop="loans.loaNo" />
      <el-table-column label="逾期金额" align="center" prop="oveMoney" />
      <el-table-column label="逾期开始时间" align="center" prop="oveStartTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.oveStartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="逾期结束时间" align="center" prop="oveLastTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.oveLastTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="减少积分数" align="center" prop="oveCutCount" />-->
      <el-table-column label="额外利息" align="center" prop="oveAddInt" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:overdue:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:overdue:remove']"
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

    <!-- 添加或修改逾期对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="企业名称" prop="oveComNo">
          <el-select v-model="form.oveComNo" placeholder="请选择企业名称" filterable>
            <el-option
              v-for="dict in companiesName"
              :key="dict.comId"
              :label="dict.comName"
              :value="dict.comId"/>
          </el-select>
        </el-form-item>
        <el-form-item label="贷款编号" prop="oveLoaId">
          <el-select v-model="form.oveLoaId" placeholder="请选择贷款编号" filterable>
            <el-option
              v-for="dict in loansNoOptions"
              :key="dict.loaId"
              :label="dict.loaNo"
              :value="dict.loaId"/>
          </el-select>
        </el-form-item>
        <el-form-item label="逾期金额" prop="oveMoney">
          <el-input v-model="form.oveMoney" placeholder="请输入逾期金额" />
        </el-form-item>
        <el-form-item label="逾期开始时间" prop="oveStartTime">
          <el-date-picker clearable size="small"
            v-model="form.oveStartTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择逾期开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="逾期结束时间" prop="oveLastTime">
          <el-date-picker clearable size="small"
            v-model="form.oveLastTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择逾期结束时间">
          </el-date-picker>
        </el-form-item>
<!--        <el-form-item label="减少积分数" prop="oveCutCount">
          <el-input v-model="form.oveCutCount" placeholder="请输入减少积分数" />
        </el-form-item>-->
        <el-form-item label="额外利息" prop="oveAddInt">
          <el-input v-model="form.oveAddInt" placeholder="请输入额外利息" />
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
import { listOverdue, getOverdue, delOverdue, addOverdue, updateOverdue, exportOverdue } from "@/api/system/overdue";
import {getAptitudeCompanyName} from "@/api/system/aptitude";
import {getLoansNo} from "@/api/system/loans";

export default {
  name: "Overdue",
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
      // 逾期表格数据
      overdueList: [],
      //企业列表
      companiesName: [],
      //贷款列表
      loansNoOptions:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        oveComNo: null,
        oveLoaId: null,
        oveMoney: null,
        oveStartTime: null,
        oveLastTime: null,
        oveCutCount: null,
        oveAddInt: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        oveComNo:[{ required: true, message: "企业名称不能为空", trigger: "blur" }],
        oveLoaId:[{ required: true, message: "贷款编号不能为空", trigger: "blur" }],
      }
    };
  },
  created() {
    this.getList();
    this.getListComName();
    this.getListLoansNo();
  },
  methods: {
    /** 查询逾期列表 */
    getList() {
      this.loading = true;
      listOverdue(this.queryParams).then(response => {
        this.overdueList = response.rows;
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
        oveId: null,
        oveComNo: null,
        oveLoaId: null,
        oveMoney: null,
        oveStartTime: null,
        oveLastTime: null,
        oveCutCount: null,
        oveAddInt: null,
        oveDel: null
      };
      this.resetForm("form");
    },
    // 查询贷款列表
    getListLoansNo(){
      getLoansNo().then(response => {
        this.loansNoOptions = response.data;
      });
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
      this.ids = selection.map(item => item.oveId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加逾期";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const oveId = row.oveId || this.ids
      getOverdue(oveId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改逾期";
      });
    },
    // 查询已实名企业列表
    getListComName(){
      getAptitudeCompanyName().then(response => {
        this.companiesName = response.data;
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.oveId != null) {
            updateOverdue(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOverdue(this.form).then(response => {
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
      const oveIds = row.oveId || this.ids;
      this.$confirm('是否确认删除逾期编号为"' + oveIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delOverdue(oveIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有逾期数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportOverdue(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
