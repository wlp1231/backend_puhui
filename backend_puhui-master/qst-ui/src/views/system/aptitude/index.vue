<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="企业名称" prop="comId">
        <el-select v-model="queryParams.comId" filterable placeholder="请选择企业名称" clearable size="small">
          <el-option
            v-for="dict in companiesName"
            :key="dict.comId"
            :label="dict.comName"
            :value="dict.comId"
            @keyup.enter.native="handleQuery"/>
        </el-select>
      </el-form-item>
      <el-form-item label="贷款编号" prop="aptLoaId">
        <el-select v-model="queryParams.aptLoaId" filterable placeholder="请选择贷款编号" clearable size="small">
          <el-option
            v-for="dict in loansNoOptions"
            :key="dict.loaId"
            :label="dict.loaNo"
            :value="dict.loaId"
            @keyup.enter.native="handleQuery"/>
        </el-select>
      </el-form-item>
      <el-form-item label="信用额度" prop="aptCredit">
        <el-input
          v-model="queryParams.aptCredit"
          placeholder="请输入信用额度"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="抵押物" prop="aptPawn">
        <el-input
          v-model="queryParams.aptPawn"
          placeholder="请输入抵押物"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="贷款记录" prop="aptLoansRecord">
        <el-input
          v-model="queryParams.aptLoansRecord"
          placeholder="请输入贷款记录"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>-->
      <el-form-item label="企业市值" prop="aptComValue">
        <el-input
          v-model="queryParams.aptComValue"
          placeholder="请输入企业市值"
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
          v-hasPermi="['system:aptitude:add']"
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
          v-hasPermi="['system:aptitude:edit']"
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
          v-hasPermi="['system:aptitude:remove']"
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
          v-hasPermi="['system:aptitude:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="aptitudeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="aptId" />
<!--      <el-table-column label="企业ID" align="center" prop="comId" />-->
      <el-table-column label="企业名称" align="center" prop="comName" />
<!--      <el-table-column label="贷款ID" align="center" prop="aptLoaId" />-->
      <el-table-column label="信用额度" align="center" prop="aptCredit" />
      <el-table-column label="抵押物" align="center" prop="aptPawn" />
      <el-table-column label="贷款记录" align="center" prop="aptLoansRecord" >
        <template slot-scope="scope">
          <dict-tag :options="sysLoaState" :value="scope.row.aptLoansRecord"/>
        </template>
      </el-table-column>
      <el-table-column label="企业市值" align="center" prop="aptComValue" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:aptitude:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:aptitude:remove']"
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

    <!-- 添加或修改资质对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="企业名称" prop="comId">
          <el-select v-model="form.comId" filterable placeholder="请选择企业名称" clearable size="small">
            <el-option
              v-for="dict in companiesName"
              :key="dict.comId"
              :label="dict.comName"
              :value="dict.comId"
              />
          </el-select>
        </el-form-item>
        <el-form-item label="贷款ID" prop="aptLoaId">
          <el-select v-model="form.aptLoaId" filterable placeholder="请选择贷款编号" clearable size="small">
            <el-option
              v-for="dict in loansNoOptions"
              :key="dict.loaId"
              :label="dict.loaNo"
              :value="dict.loaId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="信用额度" prop="aptCredit">
          <el-input v-model="form.aptCredit" placeholder="请输入信用额度" />
        </el-form-item>
        <el-form-item label="抵押物" prop="aptPawn">
          <el-input v-model="form.aptPawn" placeholder="请输入抵押物" />
        </el-form-item>
        <el-form-item label="贷款记录" prop="aptLoansRecord">
          <el-select v-model="form.aptLoansRecord" placeholder="请选择贷款记录" clearable size="small" @change="aptLoansRecordChange">
            <el-option
              v-for="dict in sysLoaState"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"/>




          </el-select>
        </el-form-item>
        <el-form-item label="企业市值" prop="aptComValue">
          <el-input v-model="form.aptComValue" placeholder="请输入企业市值" />
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
  listAptitude,
  getAptitude,
  delAptitude,
  addAptitude,
  updateAptitude,
  exportAptitude,
  getAptitudeCompanyName
} from "@/api/system/aptitude";
import {getLoansNo} from "@/api/system/loans";

export default {
  name: "Aptitude",
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
      // 资质表格数据
      aptitudeList: [],
      // 已实名企业数据
      companiesName: [],
      //企业编号
      loansNoOptions: [],
      sysLoaState: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        comId: null,
        aptLoaId: null,
        aptCredit: null,
        aptPawn: null,
        aptLoansRecord: null,
        aptComValue: null,
        aptDel: null
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
    this.getListComName();
    this.getListLoansNo();
  },
  methods: {

    /** 查询资质列表 */
    getList() {
      this.loading = true;
      listAptitude(this.queryParams).then(response => {
        this.aptitudeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      this.getDicts("sys_loa_state").then(response => {
        this.sysLoaState = response.data;
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
        aptId: null,
        comId: null,
        aptLoaId: null,
        aptCredit: null,
        aptPawn: null,
        aptLoansRecord: null,
        aptComValue: null,
        aptDel: null
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
      this.ids = selection.map(item => item.aptId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加资质";
    },
    // 查询已实名企业列表
    getListComName(){
      getAptitudeCompanyName().then(response => {
        this.companiesName = response.data;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const aptId = row.aptId || this.ids
      getAptitude(aptId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改资质";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.aptId != null) {
            updateAptitude(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAptitude(this.form).then(response => {
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
      const aptIds = row.aptId || this.ids;
      this.$confirm('是否确认删除资质编号为"' + aptIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delAptitude(aptIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有资质数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportAptitude(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
