<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="贷款编号" prop="loaNo">
        <el-input
          v-model="queryParams.loaNo"
          placeholder="请输入贷款编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="企业名称" prop="comName">
        <el-input
          v-model="queryParams.comName"
          placeholder="请输入企业名称"
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

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="riskList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="riskId" />
      <el-table-column label="贷款编号" align="center" prop="loaNo" />
      <el-table-column label="企业名称" align="center" prop="comName" />
      <el-table-column label="贷款金额" align="center" prop="loaMoney" />
      <el-table-column label="贷款周期" align="center" prop="loaLisId" />
      <el-table-column label="抵押价值" align="center" prop="aptPrice" />
      <el-table-column label="月最小值" align="center" prop="monthAccountMin" />
      <el-table-column label="总负债值" align="center" prop="grossLiabilities" />
      <el-table-column label="额度值" align="center" prop="creditLine" />
      </el-table-column>
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
import { listRisk, getRisk, delRisk, addRisk, updateRisk} from "@/api/system/risk";

export default {
  name: "Risk",
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
      // 风控管理表格数据
      riskList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        loaNo: null,
        comName: null,
        loaMoney: null,
        loaLisId: null,
        aptPrice: null,
        monthAccountMin: null,
        grossLiabilities: null,
        creditLine: null,
        riskDel: null
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
    /** 查询风控管理列表 */
    getList() {
      this.loading = true;
      listRisk(this.queryParams).then(response => {
        this.riskList = response.rows;
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.riskId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    }
  }
};
</script>
