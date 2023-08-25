<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="企业名称" prop="lisComId">
        <el-select v-model="queryParams.lisComId" filterable placeholder="请选择企业名称" clearable size="small">
          <el-option
            v-for="dict in companiesName"
            :key="dict.comId"
            :label="dict.comName"
            :value="dict.comId"
            @keyup.enter.native="handleQuery"/>
        </el-select>
      </el-form-item>
      <el-form-item label="贷款编号" prop="lisLoaId">
        <el-select v-model="queryParams.lisLoaId" filterable placeholder="请选择贷款编号" clearable size="small">
          <el-option
            v-for="dict in loansNoOptions"
            :key="dict.loaId"
            :label="dict.loaNo"
            :value="dict.loaId"
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
          v-hasPermi="['system:materials:add']"
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
          v-hasPermi="['system:materials:edit']"
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
          v-hasPermi="['system:materials:remove']"
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
          v-hasPermi="['system:materials:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="materialsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="lisId" />
      <el-table-column label="企业名称" align="center" prop="company.comName" />
      <el-table-column label="近六个月流水" align="center" prop="lisSixRunWater" >
        <template slot-scope="scope">
          <el-popover placement="top-start" trigger="click"><!--trigger属性值：hover、click、focus 和 manual-->
            <a :href="'/dev-api'+scope.row.lisSixRunWater" target="_blank" title="查看最大化图片">
              <img :src="'/dev-api'+scope.row.lisSixRunWater" style="width: 300px;height: 300px">
            </a>
            <img slot="reference" style="width: 80px; border: none; cursor:pointer;" :src="'/dev-api'+scope.row.lisSixRunWater">
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="企业营业执照" align="center" prop="lisWork" >
        <template slot-scope="scope">
          <el-popover placement="top-start" trigger="click"><!--trigger属性值：hover、click、focus 和 manual-->
            <a :href="'/dev-api'+scope.row.lisWork" target="_blank" title="查看最大化图片">
              <img :src="'/dev-api'+scope.row.lisWork">
            </a>
            <img slot="reference" style="width: 80px; border: none; cursor:pointer;" :src="'/dev-api'+scope.row.lisWork">
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="征信报告" align="center" prop="lisReport" >
        <template slot-scope="scope">
          <el-popover placement="top-start" trigger="click"><!--trigger属性值：hover、click、focus 和 manual-->
            <a :href="'/dev-api'+scope.row.lisReport" target="_blank" title="查看最大化图片">
              <img :src="'/dev-api'+scope.row.lisReport">
            </a>
            <img slot="reference" style="width: 80px; border: none; cursor:pointer;" :src="'/dev-api'+scope.row.lisReport">
          </el-popover>
        </template>
      </el-table-column>
      <el-table-column label="抵押物" align="center" prop="lisPawn" >
        <template width="80" slot-scope="scope">
          <el-popover placement="top-start" trigger="click"><!--trigger属性值：hover、click、focus 和 manual-->
            <a :href="'/dev-api'+scope.row.lisPawn" target="_blank" title="查看最大化图片">
              <img :src="'/dev-api'+scope.row.lisPawn" style="width: 500px">
            </a>
            <img slot="reference" style="width: 80px; border: none; cursor:pointer;" :src="'/dev-api'+scope.row.lisPawn">
          </el-popover>
        </template>
      </el-table-column>
<!--  2022.7.14 按客户要求 去掉 修改与删除     -->
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:materials:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:materials:remove']"
          >删除</el-button>
        </template>
      </el-table-column>-->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改材料对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="企业名称" prop="lisComId">
          <el-select v-model="form.lisComId" filterable placeholder="请选择企业名称" clearable size="small">
            <el-option
              v-for="dict in companiesName"
              :key="dict.comId"
              :label="dict.comName"
              :value="dict.comId"
              />
          </el-select>
        </el-form-item>
        <el-form-item label="贷款编号" prop="lisLoaId">
          <el-select v-model="form.lisLoaId" filterable placeholder="请选择贷款编号" clearable size="small">
            <el-option
              v-for="dict in loansNoOptions"
              :key="dict.loaId"
              :label="dict.loaNo"
              :value="dict.loaId"
              />
          </el-select>
        </el-form-item>
        <el-form-item label="近六个月流水" prop="lisSixRunWater">
          <imageUpload v-model="form.lisSixRunWater"/>
        </el-form-item>
        <el-form-item label="企业营业执照" prop="lisWork">
          <imageUpload v-model="form.lisWork"/>
        </el-form-item>
        <el-form-item label="征信报告" prop="lisReport">
          <imageUpload v-model="form.lisReport"/>
        </el-form-item>
        <el-form-item label="抵押物" prop="lisPawn">
          <imageUpload v-model="form.lisPawn"/>
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
import { listMaterials, getMaterials, delMaterials, addMaterials, updateMaterials, exportMaterials } from "@/api/system/materials";
import {getAptitudeCompanyName} from "@/api/system/aptitude";
import {getLoansNo} from "@/api/system/loans";

export default {
  name: "Materials",
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
      // 材料表格数据
      materialsList: [],
      //企业名称
      companiesName: [],
      //企业编号
      loansNoOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        lisComId: null,
        lisLoaId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        lisComId:[{ required: true, message: "企业名称不能为空", trigger: "blur" }],
        lisLoaId:[{ required: true, message: "贷款编号不能为空", trigger: "blur" }],
        lisSixRunWater: [{required: true, message: "近六个月流水不能为空", trigger: "blur"}],
        lisWork: [{required: true, message: "企业营业执照不能为空", trigger: "blur"}],
        lisReport: [{required: true, message: "征信报告不能为空", trigger: "blur"}],
        lisPawn: [{required: true, message: "抵押物不能为空", trigger: "blur"}],
      }
    };
  },
  created() {
    this.getList();
    this.getListComName();
    this.getListLoansNo();
  },
  methods: {
    // 查询已实名企业列表
    getListComName(){
      getAptitudeCompanyName().then(response => {
        this.companiesName = response.data;
      });
    },
    // 查询贷款列表
    getListLoansNo(){
      getLoansNo().then(response => {
        this.loansNoOptions = response.data;
      });
    },
    /** 查询材料列表 */
    getList() {
      this.loading = true;
      listMaterials(this.queryParams).then(response => {
        this.materialsList = response.rows;
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
        lisId: null,
        lisComId: null,
        lisLoaId: null,
        lisIdCard: null,
        lisSixRunWater: null,
        lisWork: null,
        lisReport: null,
        lisPawn: null,
        lisDel: null
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
      this.ids = selection.map(item => item.lisId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加材料";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const lisId = row.lisId || this.ids
      getMaterials(lisId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改材料";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.lisId != null) {
            updateMaterials(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMaterials(this.form).then(response => {
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
      const lisIds = row.lisId || this.ids;
      this.$confirm('是否确认删除材料编号为"' + lisIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delMaterials(lisIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有材料数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportMaterials(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
