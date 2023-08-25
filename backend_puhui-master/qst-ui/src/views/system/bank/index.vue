<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="银行名称" prop="bankName">
        <el-input
          v-model="queryParams.bankName"
          placeholder="请输入银行名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电子邮箱" prop="bankEmail">
        <el-input
          v-model="queryParams.bankEmail"
          placeholder="请输入电子邮箱"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="贷款利率" prop="bankRate">
        <el-input
          v-model="queryParams.bankRate"
          placeholder="请输入贷款利率"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系方式" prop="bankPhone">
        <el-input
          v-model="queryParams.bankPhone"
          placeholder="请输入联系方式"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="贷款期限(月)" prop="bankLength">
        <el-input
          v-model="queryParams.bankLength"
          placeholder="请输入贷款期限"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>-->
<!--      <el-form-item label="省" prop="bankProId">
        <el-input
          v-model="queryParams.bankProId"
          placeholder="请输入省"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="市" prop="bankCitId">
        <el-input
          v-model="queryParams.bankCitId"
          placeholder="请输入市"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>-->
<!--      <el-form-item label="区" prop="bankAraId">
        <el-input
          v-model="queryParams.bankAraId"
          placeholder="请输入区"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>-->
      <el-form-item label="详细地址" prop="bankAdress">
        <el-input
          v-model="queryParams.bankAdress"
          placeholder="请输入详细地址"
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
          v-hasPermi="['system:bank:add']"
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
          v-hasPermi="['system:bank:edit']"
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
          v-hasPermi="['system:bank:remove']"
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
          v-hasPermi="['system:bank:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="bankList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="银行编号" align="center" prop="bankId" />
      <el-table-column label="银行名称" align="center" prop="bankName" />
      <el-table-column label="电子邮箱" align="center" prop="bankEmail" />
      <el-table-column label="贷款利率" align="center" prop="bankRate" />
      <el-table-column label="联系方式" align="center" prop="bankPhone" />
      <el-table-column label="最低贷款期限(月)" align="center" prop="bankLength" />
      <el-table-column label="省" align="center" prop="provinces.province" />
      <el-table-column label="市" align="center" prop="cities.city" />
      <el-table-column label="区" align="center" prop="areas.area" />
      <el-table-column label="详细地址" align="center" prop="bankAdress" />
      <el-table-column label="操作" align="center" width="100" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:bank:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:bank:remove']"
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

    <!-- 添加或修改银行对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="银行名称" prop="bankName">
          <el-input v-model="form.bankName" placeholder="请输入银行名称" />
        </el-form-item>
        <el-form-item label="电子邮箱" prop="bankEmail">
          <el-input v-model="form.bankEmail" placeholder="请输入电子邮箱" />
        </el-form-item>
<!--        <el-form-item label="密码" prop="bankPassword">
          <el-input v-model="form.bankPassword" placeholder="请输入密码" />
        </el-form-item>-->
        <el-form-item label="贷款利率" prop="bankRate">
          <el-input v-model="form.bankRate" placeholder="请输入贷款利率" />
        </el-form-item>
        <el-form-item label="联系方式" prop="bankPhone">
          <el-input v-model="form.bankPhone" placeholder="请输入联系方式" />
        </el-form-item>
        <el-form-item label="最低贷款期限(月)" prop="bankLength">
          <el-input v-model="form.bankLength" placeholder="请输入贷款期限" />
        </el-form-item>
        <el-form-item label="省/市/区" prop="bankProId">
          <el-cascader
            size="large"
            :options="options"
            v-model="selectOptions"
            placeholder="请选择省/市/区"
            @change="handleChange" >
          </el-cascader>
<!--          <el-select v-model="form.bankProId" placeholder="请选择省" >
            <el-option
              v-for="item in provinceOptions"
              :key="item.provinceid"
              :label="item.province"
              :value="item.provinceid">
            </el-option>
          </el-select>-->
        </el-form-item>
<!--        <el-form-item label="市" prop="bankCitId">
          <el-input v-model="form.bankCitId" placeholder="请输入市" />
        </el-form-item>
        <el-form-item label="区" prop="bankAraId">
          <el-input v-model="form.bankAraId" placeholder="请输入区" />
        </el-form-item>-->
        <el-form-item label="详细地址" prop="bankAdress">
          <el-input v-model="form.bankAdress" placeholder="请输入详细地址" />
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
import { listBank, getBank, delBank, addBank, updateBank, exportBank } from "@/api/system/bank";
import {getAreaCompany, getCityCompany, getProCompany, getPros} from "@/api/system/details";
import { regionData } from 'element-china-area-data';
import provinceAndCity from '../provinces/province.json';

export default {
  name: "Bank",
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
      // 银行表格数据
      bankList: [],
      // 省份数据
      options: regionData,
      // json省市区
      provinceAndCity,
      //省份选项
      provinceOptions: [],
      // 组件省市
      selectOptions: [],
      //市选项
      cityOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        bankName: null,
        bankEmail: null,
        bankRate: null,
        bankPhone: null,
        bankLength: null,
        bankProId: null,
        bankCitId: null,
        bankAraId: null,
        bankAdress: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        bankName: [{required: true, message: "银行名称不能为空", trigger: "blur"}],
        bankEmail: [{required: true, message: "银行邮箱账号不能为空", trigger: "blur"}],
        bankPassword: [{required: true, message: "银行密码不能为空", trigger: "blur"}],
        bankProId: [{required: true, message: "地址不能为空", trigger: "blur"}],
        bankPhone: [{
          pattern: /^(13[0-9]{9})|(15[0-9]{9})|(17[0-9]{9})|(18[0-9]{9})|(19[0-9]{9})$/,
          required: true, message: "银行电话格式不正确", trigger: "blur" }],
        bankAdress: [{required: true, message: "详细地址不能为空", trigger: "blur"}],
        bankLength: [{required: true, message: "期限不能为空", trigger: "blur"}],
        bankRate: [{required: true, message: "利率不能为空", trigger: "blur"}],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询银行列表 */
    getList() {
      this.loading = true;
      listBank(this.queryParams).then(response => {
        this.bankList = response.rows;
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
        bankId: null,
        bankName: null,
        bankEmail: null,
        bankPassword: null,
        bankRate: null,
        bankPhone: null,
        bankLength: null,
        bankProId: null,
        bankCitId: null,
        bankAraId: null,
        bankAdress: null,
        bankDel: null
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
      this.ids = selection.map(item => item.bankId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加银行";
    },
    handleChange(value){
      this.form.bankProId = value[0];
      this.form.bankCitId = value[1];
      this.form.bankAraId = value[2];
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const bankId = row.bankId || this.ids
      getBank(bankId).then(response => {
        this.form = response.data;

        this.open = true;
        this.title = "修改银行";


      });


      getProCompany(row.bankProId,row.bankCitId,row.bankAraId).then(response => {
        this.selectOptions=[];
        for (const i in response.info) {
          this.selectOptions.push(response.info[i]);
        }
      });





    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.bankId != null) {
            updateBank(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBank(this.form).then(response => {
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
      const bankIds = row.bankId || this.ids;
      this.$confirm('是否确认删除银行编号为"' + bankIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delBank(bankIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有银行数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportBank(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
