<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="企业类型" prop="cType">
        <el-select v-model="queryParams.cType" placeholder="请选择企业类型" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
            @keyup.enter.native="handleQuery"/>
        </el-select>
      </el-form-item>
      <el-form-item label="法人姓名" prop="cLegalName">
        <el-input
          v-model="queryParams.cLegalName"
          placeholder="请输入法人姓名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="法人证件" prop="cLegalId">
        <el-input
          v-model="queryParams.cLegalId"
          placeholder="请输入法人证件号"
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
          v-hasPermi="['system:details:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:details:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:details:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['system:details:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table  v-loading="loading" :data="detailsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="企业名称" align="center" prop="company.comName"/>
      <el-table-column label="企业积分" align="center" prop="cIntegral"/>
      <el-table-column label="企业类型" align="center" prop="cType">
        <template slot-scope="scope">
          <dict-tag :options="statusOptions" :value="scope.row.cType"/>
        </template>
      </el-table-column>
      <el-table-column label="法人姓名" align="center" prop="cLegalName"/>
      <el-table-column label="法人证件号" align="center" prop="cLegalId"/>
      <el-table-column label="注册省" align="center" prop="provinces.province"/>
      <el-table-column label="注册市" align="center" prop="cities.city"/>
      <!--      <el-table-column label="营业执照到期时间" align="center" prop="cLicTime" width="180">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.cLicTime, '{y}-{m}-{d}') }}</span>
              </template>
            </el-table-column>-->
      <el-table-column label="状态" align="center" prop="company.comStatus">
        <template slot-scope="scope">
          <dict-tag :options="companyOptions" :value="scope.row.company.comStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="160" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:details:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:details:remove']"
          >删除
          </el-button>
          <el-dropdown size="mini" @command="(command) => handleCommand(command, scope.row)"
                       v-hasPermi="['system:details:list']">
                <span class="el-dropdown-link">
                  <i class="el-icon-d-arrow-right el-icon--right"></i>更多
                </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="handleCompanyDetails" icon="el-icon-circle-check"
                                v-hasPermi="['system:details:list']">查看详情
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

    <!-- 添加或修改企业详情对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="150px">
        <!-- <el-form-item label="企业积分" prop="cIntegral">
                  <el-input v-model="form.cIntegral" placeholder="请输入企业积分" />
                </el-form-item>-->
        <el-form-item label="企业名称" prop="coId">
           <!--  新增为 下拉列表 可选择状态为3 的企业, 修改时 采用文本框 显示 不可编辑         -->
          <el-select v-model="form.coId" placeholder="请选企业" v-if="flag">
            <el-option
              v-for="item in companyName"
              :key="item.comId"
              :label="item.comName"
              :value="item.comId"/>
          </el-select>

          <!--  新增为 下拉列表 可选择状态为3 的企业, 修改时 采用文本框 显示 不可编辑         -->
          <el-input v-model="form.company.comName" placeholder="请输入企业名称" v-if="!flag" :disabled="true"/>


        </el-form-item>
        <el-form-item label="营业执照" prop="cLicense">
          <imageUpload v-model="form.cLicense"/>
        </el-form-item>
        <el-form-item label="到期时间" prop="cLicTime">
          <el-date-picker
            clearable size="small"
            v-model="form.cLicTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择营业执照到期时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="企业类型" prop="cType">
          <el-select v-model="form.cType" placeholder="请选择企业类型">
            <el-option
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"/>
          </el-select>
        </el-form-item>
        <el-form-item label="法人姓名" prop="cLegalName">
          <el-input v-model="form.cLegalName" placeholder="请输入法人姓名"/>
        </el-form-item>
        <el-form-item label="法人证件号" prop="cLegalId">
          <el-input v-model="form.cLegalId" placeholder="请输入法人证件号"/>
        </el-form-item>
<!--        <el-form-item label="法人证件(正反面)" prop="cLegalImg">
          <imageUpload v-model="form.cLegalImg"/>
        </el-form-item>-->
        <el-form-item label="有效期限" prop="cLegalTime">
          <el-date-picker
            clearable size="small"
            v-model="form.cLegalTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择有效期限">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="注册省/市" prop="cPro">
          <el-cascader
            size="large"
            :options="options"
            v-model="selectOptions"
            placeholder="请选择省/市"
            @change="handleChange" >
          </el-cascader>
        </el-form-item>

        <el-form-item label="公司详细地址" prop="cAddress">
          <el-input v-model="form.cAddress" placeholder="请输入公司详细地址"/>
        </el-form-item>
        <el-form-item label="经营范围" prop="cBusSco">
          <el-input v-model="form.cBusSco" type="textarea" placeholder="请输入经营范围"/>
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
  listDetails,
  getDetails,
  delDetails,
  addDetails,
  updateDetails,
  exportDetails,
  getPros, getCompanyName
} from "@/api/system/details";

import { regionData ,provinceAndCityData} from 'element-china-area-data';
//import provinceAndCity from '../provinces/province.json';

export default {
  name: "Details",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 选中数组
      ids: [],
      currentId: "",
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 企业详情表格数据
      detailsList: [],
      // 省份数据  --- 这种方式没有 从数据库中读取数据
     // options: provinceAndCityData,
      // 身份数据 -- 从数据库读取
      options: [],
      // json省市区
    //  provinceAndCity,
      // 组件省市
      selectOptions: [],
      // 弹出层标题
      title: "",
      //区分 修改还是 新增    //flag=true 为 新增   flag=false 修改
      flag:true,
      // 是否显示弹出层
      open: false,
      // 状态数据字典
      statusOptions: [],
      // 企业状态数据字典
      companyOptions: [],
      // 未实名企业
      companyName: "",
      //省份选项
      provinceOptions: "",
      //市选项
      cityOptions: "",
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        cIntegral: null,
        cLicense: null,
        cType: null,
        cLegalName: null,
        cLegalId: null,
      },
      // 表单参数
      form: {
        company:{
          comName:null
        }
      },
      // 表单校验
      rules: {
        cLicense: [{required: true, message: "营业执照不能为空", trigger: "blur"}],
        cLicTime: [{required: true, message: "营业执照到期时间不能为空", trigger: "blur"}],
        cType: [{required: true, message: "企业类型不能为空", trigger: "blur"}],
        cLegalName: [{required: true, message: "法人姓名不能为空", trigger: "blur"}],
        cLegalId: [{
          pattern: /^[1-9]\d{5}(18|19|20|(3\d))\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/,
          required: true, message: "法人证件号码格式不正确", trigger: "blur"
        }],
        cLegalImg: [{required: true, message: "法人证件照片不能为空", trigger: "blur"}],
        cLegalTime: [{required: true, message: "法人证件有效期不能为空", trigger: "blur"}],
        cPro: [{required: true, message: "企业注册省份不能为空", trigger: "blur"}],
        cCity: [{required: true, message: "企业注册市不能为空", trigger: "blur"}],
        cAddress: [{required: true, message: "企业详细地址不能为空", trigger: "blur"}],
        cBusSco: [{required: true, message: "企业经营范围不能为空", trigger: "blur"}]
      //  coId: [{required: true, message: "企业名称不能为空", trigger: "blur"}],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("company_type").then(response => {
      this.statusOptions = response.data;
    });
    this.getDicts("company_com_status").then(response => {
      this.companyOptions = response.data;
    });
    this.getPro();
  },
  methods: {
    /** 查询企业详情列表 */
    getList() {
      this.loading = true;
      listDetails(this.queryParams).then(response => {
        this.detailsList = response.rows;
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
        cId: null,
        coId: null,
        cIntegral: null,
        cLicense: null,
        cType: null,
        cLegalName: null,
        cLegalId: null,
        cLegalImg: null,
        cLegalTime: null,
        cPro: undefined,
        cCity: undefined,
        cAddress: null,
        cBusSco: null,
        cLicTime: null,
        company:{
          comName:null
        }
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
      this.ids = selection.map(item => item.cId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    // 更多操作触发
    handleCommand(command, row) {
      switch (command) {
        case "handleCompanyDetails":
          this.handleCompanyDetails(row);
          break;
        default:
          break;
      }
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      getCompanyName().then(response => {
        this.companyName = response.data;
      });
      this.open = true;
      this.title = "添加企业详情";
      this.flag=true; // 设置为新增
      this.selectOptions=[]; // 清空选项
    },
    handleChange(value) {
      this.form.cPro = value[0];
      this.form.cCity = value[1];
    },
    // 获得省列表
    getPro() {
      getPros().then(response => {
      // this.options=[];
        response.provincs.forEach((item)=>{
          let obj = {"value":item.provinceid,"label":item.province,"children":[]};
          item.citiesList.forEach((c)=>{
            let city={"value":c.cityid,"label":c.city};
            obj.children.push(city);
          });
          // 遍历list 组装数据
          this.options.push(obj);
        });

      });
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const cId = row.cId || this.ids;
      getDetails(cId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改企业详情";
        this.flag=false; // 设置为修改

        //alert(response.data.cPro+"----"+response.data.cCity);
      this.selectOptions=[response.data.cPro,response.data.cCity];


      });

    },
    /** 查看详情操作 */
    handleCompanyDetails: function (row) {
      const cId = row.cId;
      this.$router.push("/details/companyDetails/" + cId);
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.cId != null) {
            updateDetails(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDetails(this.form).then(response => {
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
      const cIds = row.cId || this.ids;
      this.$confirm('是否确认删除企业详情编号为"' + cIds + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function () {
        return delDetails(cIds);
      }).then(() => {
        this.getList();
        this.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有企业详情数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.exportLoading = true;
        return exportDetails(queryParams);
      }).then(response => {
        this.download(response.msg);
        this.exportLoading = false;
      }).catch(() => {
      });
    },
  }
};
</script>

