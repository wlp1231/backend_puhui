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

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:risk:add']"
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
          v-hasPermi="['system:risk:edit']"
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
          v-hasPermi="['system:risk:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
              <el-button
                type="warning"
                plain
                size="mini"
                @click="handleIntro"
              >额度值计算规则
              </el-button>
            </el-col>

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
      <el-table-column label="征信状态" align="center" prop="creInvesState" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:risk:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:risk:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleInvest(scope.row)"
            v-hasPermi="['system:risk:invest']"
          >查询征信</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleCalc(scope.row)"
            v-hasPermi="['system:risk:calc']"
          >计算额度</el-button>
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

    <!-- 添加或修改风控管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="贷款编号" prop="loaNo">
          <el-input v-model="form.loaNo" placeholder="请输入贷款编号" />
        </el-form-item>
        <el-form-item label="企业名称" prop="comName">
          <el-input v-model="form.comName" placeholder="请输入企业名称" />
        </el-form-item>
        <el-form-item label="贷款金额" prop="loaMoney">
          <el-input v-model="form.loaMoney" placeholder="请输入贷款金额" />
        </el-form-item>
        <el-form-item label="贷款周期" prop="loaLisId">
          <el-input v-model="form.loaLisId" placeholder="请输入贷款周期" />
        </el-form-item>
        <el-form-item label="抵押价值" prop="aptPrice">
          <el-input v-model="form.aptPrice" placeholder="请输入抵押价值" />
        </el-form-item>
        <el-form-item label="月最小值" prop="monthAccountMin">
          <el-input v-model="form.monthAccountMin" placeholder="请输入月最小值" />
        </el-form-item>
        <el-form-item label="总负债值" prop="grossLiabilities">
          <el-input v-model="form.grossLiabilities" placeholder="请输入总负债值" />
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
import { listRisk, getRisk, delRisk, addRisk, updateRisk, calcRisk, investRisk} from "@/api/system/risk";

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
        riskDel: null,
        creInvesState: null
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
    // 表单重置
    reset() {
      this.form = {
        riskId: null,
        loaNo: null,
        comName: null,
        loaMoney: null,
        loaLisId: null,
        aptPrice: null,
        monthAccountMin: null,
        grossLiabilities: null,
        creditLine: null,
        riskDel: null,
        creInvesState: null
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
      this.ids = selection.map(item => item.riskId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加风控管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const riskId = row.riskId || this.ids
      getRisk(riskId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改风控管理";
      });
    },
    /** 查看计算规则 */
    handleIntro(){
      this.$alert('通过公式【抵押价值*70%-总负债值+月最小值*贷款周期】系统计算出额度值，再将其与申请额度进行比较。</br>当额度值>=申请额度,显示为申请额度;</br>当额度值<申请额度,显示为额度值',{dangerouslyUseHTMLString:true})
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.riskId != null) {
            updateRisk(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRisk(this.form).then(response => {
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
      const riskIds = row.riskId || this.ids;
      this.$confirm('是否确认删除风控管理编号为"' + riskIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delRisk(riskIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 计算额度操作 */
    handleCalc(row) {
       const riskId = row.riskId || this.ids;
       calcRisk(riskId).then(() => {
       this.getList();
       }).catch(() => {});
    },
    /** 查询征信 */
    handleInvest(row) {
      const riskId = row.riskId || this.ids;
      // this.$alert(riskId)
      investRisk(riskId).then(() => {
      this.getList();
        this.$alert("查询完毕");
      }).catch(() => {});
    }
  }
};
</script>
