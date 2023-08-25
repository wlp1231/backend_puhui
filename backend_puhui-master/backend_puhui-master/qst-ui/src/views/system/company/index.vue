<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="企业编号" prop="comNo">
        <el-input
          v-model="queryParams.comNo"
          placeholder="请输入企业编号"
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
      <el-form-item label="邮箱" prop="comEmail">
        <el-input
          v-model="queryParams.comEmail"
          placeholder="请输入邮箱"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电话" prop="comPhone">
        <el-input
          v-model="queryParams.comPhone"
          placeholder="请输入电话"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="comStatus">
        <el-select v-model="queryParams.comStatus" placeholder="请选择状态" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
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
          v-hasPermi="['system:company:add']"
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
          v-hasPermi="['system:company:edit']"
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
          v-hasPermi="['system:company:remove']"
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
          v-hasPermi="['system:company:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="companyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="comId" />
      <el-table-column label="企业编号" align="center" prop="comNo" />
      <el-table-column label="企业名称" align="center" prop="comName" />
      <el-table-column label="邮箱" align="center" prop="comEmail" />
      <el-table-column label="电话" align="center" prop="comPhone" />
      <el-table-column label="状态" align="center" prop="comStatus" >
        <template slot-scope="scope">
          <dict-tag :options="statusOptions" :value="scope.row.comStatus"/>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:company:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:company:remove']"
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

    <!-- 添加或修改【请填写功能名称】对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="企业编号" prop="comNo">
          <el-input v-model="form.comNo" placeholder="请输入企业编号" />
        </el-form-item>
        <el-form-item label="企业名称" prop="comName">
          <el-input v-model="form.comName" placeholder="请输入企业名称" />
        </el-form-item>
        <el-form-item label="邮箱" prop="comEmail">
          <el-input v-model="form.comEmail" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="电话" prop="comPhone">
          <el-input v-model="form.comPhone" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="企业状态" prop="comStatus">
          <el-select v-model="form.comStatus" placeholder="请选择企业状态">
            <el-option
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"/>
          </el-select>
        </el-form-item>
        <el-form-item label="密码" prop="comPwd">
          <el-input v-model="form.comPwd" placeholder="请输入密码" />
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
import { listCompany, getCompany, delCompany, addCompany, updateCompany, exportCompany } from "@/api/system/company";

export default {
  name: "Company",
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
      // 【请填写功能名称】表格数据
      companyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 状态数据字典
      statusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        comNo: null,
        comName: null,
        comEmail: null,
        comPhone: null,
        comStatus: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        comNo: [{ required: true, message: "企业编号不能为空", trigger: "blur" }],
        comName: [{ required: true, message: "企业名称不能为空", trigger: "blur" }],
        comPhone: [{
          pattern: /^(13[0-9]{9})|(15[0-9]{9})|(17[0-9]{9})|(18[0-9]{9})|(19[0-9]{9})$/,
          required: true, message: "企业电话格式不正确", trigger: "blur" }],
        comEmail: [{ required: true, message: "企业邮箱不能为空", trigger: "blur" }],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("company_com_status").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    /** 查询企业列表 */
    getList() {
      this.loading = true;
      listCompany(this.queryParams).then(response => {
        this.companyList = response.rows;
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
        comId: null,
        comNo: null,
        comName: null,
        comEmail: null,
        comPhone: null,
        comPwd: null,
        comRemark: null,
        comStatus: "0",
        comDel: null
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
      this.ids = selection.map(item => item.comId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加企业";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const comId = row.comId || this.ids
      getCompany(comId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改企业";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.comId != null) {
            updateCompany(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCompany(this.form).then(response => {
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
      const comIds = row.comId || this.ids;
      this.$confirm('是否确认删除企业序号为"' + comIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delCompany(comIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有企业信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportCompany(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
