<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="贷款ID" prop="exaLoaId">
        <el-input
          v-model="queryParams.exaLoaId"
          placeholder="请输入贷款ID"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审批人" prop="exaName">
        <el-input
          v-model="queryParams.exaName"
          placeholder="请输入审批人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审批时间" prop="exaTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.exaTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择审批时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="审批状态" prop="exaState">
        <el-select v-model="queryParams.exaState" placeholder="请选择" clearable size="small">
          <el-option
            v-for="dict in exaStateOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
            @keyup.enter.native="handleQuery"/>
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
          v-hasPermi="['system:examine:add']"
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
          v-hasPermi="['system:examine:edit']"
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
          v-hasPermi="['system:examine:remove']"
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
          v-hasPermi="['system:examine:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="examineList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="exaId" />
      <el-table-column label="贷款ID" align="center" prop="exaLoaId" />
      <el-table-column label="审批人" align="center" prop="exaName" />
      <el-table-column label="审批时间" align="center" prop="exaTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.exaTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审批状态" align="center" prop="exaState">
        <template slot-scope="scope">
          <dict-tag :options="exaStateOptions" :value="scope.row.exaState"/>
        </template>
      </el-table-column>
      <el-table-column label="审批理由" align="center" prop="exaRemark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:examine:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:examine:remove']"
          >删除</el-button>
          <el-dropdown size="mini" @command="(command) => handleCommand(command, scope.row)"
                       v-hasPermi="['system:examine:list']">
                <span class="el-dropdown-link">
                  <i class="el-icon-d-arrow-right el-icon--right"></i>审批
                </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="handleBankExamineDetails" icon="el-icon-circle-check"
                                v-hasPermi="['system:examine:list']">进入审批
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

    <!-- 添加或修改审批对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="贷款ID" prop="exaLoaId">
          <el-input v-model="form.exaLoaId" placeholder="请输入贷款ID" />
        </el-form-item>
        <el-form-item label="审批人" prop="exaName">
          <el-input v-model="form.exaName" placeholder="请输入审批人" />
        </el-form-item>
        <el-form-item label="审批时间" prop="exaTime">
          <el-date-picker clearable size="small"
            v-model="form.exaTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择审批时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="审批状态" prop="exaState">
          <el-select v-model="form.exaState" placeholder="请选择" clearable size="small">
            <el-option
              v-for="dict in exaStateOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
              />
          </el-select>
        </el-form-item>
        <el-form-item label="审批理由" prop="exaRemark">
          <el-input v-model="form.exaRemark" type="textarea" placeholder="请输入内容" />
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
import { listExamine, getExamine, delExamine, addExamine, updateExamine, exportExamine } from "@/api/system/examine";

export default {
  name: "Examine",
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
      // 审批表格数据
      examineList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 审批状态 0 通过，1 驳回字典
      exaStateOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        exaLoaId: null,
        exaName: null,
        exaTime: null,
        exaState: null,
        exaRemark: null,
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
    this.getDicts("sys_loa_status").then(response => {
      this.exaStateOptions = response.data;
    });
  },
  methods: {
    /** 查询审批列表 */
    getList() {
      this.loading = true;
      listExamine(this.queryParams).then(response => {
        this.examineList = response.rows;
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
        exaId: null,
        exaLoaId: null,
        exaName: null,
        exaTime: null,
        exaState: null,
        exaRemark: null,
        exaDel: null
      };
      this.resetForm("form");
    },
    // 更多操作触发
    handleCommand(command, row) {
      switch (command) {
        case "handleBankExamineDetails":
          this.handleBankExamineDetails(row);
          break;
        default:
          break;
      }
    },
    /** 查看详情操作 */
    handleBankExamineDetails: function (row) {
      const beId = row.exaId;
      this.$router.push("/bankExamine/bankExamineDetails/" + beId);
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
      this.ids = selection.map(item => item.exaId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加审批";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const exaId = row.exaId || this.ids
      getExamine(exaId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改审批";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.exaId != null) {
            updateExamine(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addExamine(this.form).then(response => {
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
      const exaIds = row.exaId || this.ids;
      this.$confirm('是否确认删除审批编号为"' + exaIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delExamine(exaIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有审批数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportExamine(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
