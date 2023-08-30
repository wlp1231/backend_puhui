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

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="overdueList" >
      <el-table-column label="企业编号" align="center" prop="oveComNo" />
      <el-table-column label="企业名称" align="center" prop="oveComName" />
      <el-table-column label="逾期总金额" align="center" prop="oveAllMoney" />
      <el-table-column label="逾期总次数" align="center" prop="oveAllCount" />
      <el-table-column label="逾期未还款次数" align="center" prop="ovedueCount" />
      <el-table-column label="逾期已还款次数" align="center" prop="oveEndCount" />
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
import { listOverdueCompany} from "@/api/system/overduecompany";
import {getAptitudeCompanyName} from "@/api/system/aptitude";

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
        oveComNo: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        oveComNo:[{ required: true, message: "企业名称不能为空", trigger: "blur" }]
      }
    };
  },
  created() {
    this.getList();
    this.getListComName();
  },
  methods: {
    /** 查询逾期列表 */
    getList() {
      this.loading = true;
      listOverdueCompany(this.queryParams).then(response => {
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
        oveComNo: null
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
    // 查询已实名企业列表
    getListComName(){
      getAptitudeCompanyName().then(response => {
        this.companiesName = response.data;
      });
    }

  }
};
</script>
