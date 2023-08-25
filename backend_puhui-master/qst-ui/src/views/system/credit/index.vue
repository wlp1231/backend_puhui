<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="积分值" prop="scoreNum">
        <el-input
          v-model="queryParams.scoreNum"
          placeholder="请输入积分值"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="等级" prop="levelDetail">
        <el-input
          v-model="queryParams.levelDetail"
          placeholder="请输入等级"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="贷款总次数" prop="loanCount">
        <el-input
          v-model="queryParams.loanCount"
          placeholder="请输入贷款总次数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="还款次数" prop="refundCount">
        <el-input
          v-model="queryParams.refundCount"
          placeholder="请输入还款次数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="逾期次数" prop="overdueCount">
        <el-input
          v-model="queryParams.overdueCount"
          placeholder="请输入逾期次数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="信用状态" prop="creditState">
        <el-input
          v-model="queryParams.creditState"
          placeholder="请输入信用状态"
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
         @click="handleUpdate"
         v-hasPermi="['system:credit:edit']"
         >更新</el-button>
        </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['system:credit:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="creditList" >

      <el-table-column label="企业名称" align="center" prop="comName" />
      <el-table-column label="积分值" align="center" prop="scoreNum" />
      <el-table-column label="等级" align="center" prop="levelDetail" />
      <el-table-column label="贷款总次数" align="center" prop="loanCount" />
      <el-table-column label="还款次数" align="center" prop="refundCount" />
      <el-table-column label="逾期次数" align="center" prop="overdueCount" />
      <el-table-column label="信用状态" align="center" prop="creditState" />
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />


  </div>
</template>

<script>
import { listCredit, getCredit, updateCredit, exportCredit } from "@/api/system/credit";

export default {
  name: "Credit",
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
      // 平台信用管理表格数据
      creditList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        scoreNum: null,
        levelDetail: null,
        loanCount: null,
        refundCount: null,
        overdueCount: null,
        creditState: null
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
    /** 查询平台信用管理列表 */
    getList() {
      this.loading = true;
      listCredit(this.queryParams).then(response => {
        this.creditList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
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
    /** 更新按钮操作 */
    handleUpdate() {
          console.log("aa");
          updateCredit().then(() => {
              this.getList();
          }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有平台信用管理数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportCredit(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
