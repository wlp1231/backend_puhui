<template>
  <div class="page-register">
    <el-steps :active="active" finish-status="success">
      <el-step title="贷款信息"></el-step>
      <el-step title="贷款资质"></el-step>
      <el-step title="贷款材料"></el-step>
      <el-step title="审核"></el-step>
    </el-steps>
    <section>
      <el-form
        ref="ruleForm"
        :model="ruleForm"
        :rules="rules"
        label-width="100px"
        autocomplete="off"
        size="medium"
      >
        <!--        审批进度内容-->
        <div v-if="active == 0" style="padding-top:10px">
          <el-table v-loading="loading" :data="loansOptions">
            <el-table-column label="编号" align="center" prop="loaNo"/>
            <el-table-column label="企业名称" align="center" prop="company.comName"/>
            <el-table-column label="银行名称" align="center" prop="bank.bankName"/>
            <!--      <el-table-column label="材料ID" align="center" prop="loaLisId" />-->
            <el-table-column label="贷款用途" align="center" prop="loaUse"/>
<!--            <el-table-column label="贷款积分" align="center" prop="loaIntegral"/>-->
            <el-table-column label="贷款利率" align="center" prop="loaRate"/>
            <el-table-column label="贷款时间" align="center" prop="loaTime" width="180">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.loaTime) }}</span>
              </template>
            </el-table-column>
            <el-table-column label="贷款金额(￥)" align="center" prop="loaMoney"/>
            <el-table-column label="贷款期限(月)" align="center" prop="loaLisId" width="100">
            </el-table-column>
          </el-table>
        </div>
        <div v-if="active == 1">
          <el-table v-loading="loading" :data="aptitudeList">
            <el-table-column label="编号" align="center" prop="aptId"/>
            <el-table-column label="信用额度" align="center" prop="aptCredit"/>
            <el-table-column label="抵押物" align="center" prop="aptPawn"/>
            <el-table-column label="贷款记录" align="center" prop="aptLoansRecord">
              <template slot-scope="scope">
                <dict-tag :options="sysLoaState" :value="scope.row.aptLoansRecord"/>
              </template>
            </el-table-column>
            <el-table-column label="企业市值" align="center" prop="aptComValue"/>
          </el-table>
        </div>
        <div v-if="active == 2">
          <el-table v-loading="loading" :data="materialsList">
            <el-table-column label="企业ID" align="center" prop="lisComId"/>
            <el-table-column label="近六个月流水" align="center" prop="lisSixRunWater">
              <template slot-scope="scope">
                <el-popover placement="top-start" trigger="click"><!--trigger属性值：hover、click、focus 和 manual-->
                  <a :href="'/dev-api'+scope.row.lisSixRunWater" target="_blank" title="查看最大化图片">
                    <img :src="'/dev-api'+scope.row.lisSixRunWater" style="width: 300px;height: 300px">
                  </a>
                  <img slot="reference" style="width: 80px; border: none; cursor:pointer;"
                       :src="'/dev-api'+scope.row.lisSixRunWater">
                </el-popover>
              </template>
            </el-table-column>
            <el-table-column label="工作证明" align="center" prop="lisWork">
              <template slot-scope="scope">
                <el-popover placement="top-start" trigger="click"><!--trigger属性值：hover、click、focus 和 manual-->
                  <a :href="'/dev-api'+scope.row.lisWork" target="_blank" title="查看最大化图片">
                    <img :src="'/dev-api'+scope.row.lisWork">
                  </a>
                  <img slot="reference" style="width: 80px; border: none; cursor:pointer;"
                       :src="'/dev-api'+scope.row.lisWork">
                </el-popover>
              </template>
            </el-table-column>
            <el-table-column label="征信报告" align="center" prop="lisReport">
              <template slot-scope="scope">
                <el-popover placement="top-start" trigger="click"><!--trigger属性值：hover、click、focus 和 manual-->
                  <a :href="'/dev-api'+scope.row.lisReport" target="_blank" title="查看最大化图片">
                    <img :src="'/dev-api'+scope.row.lisReport">
                  </a>
                  <img slot="reference" style="width: 80px; border: none; cursor:pointer;"
                       :src="'/dev-api'+scope.row.lisReport">
                </el-popover>
              </template>
            </el-table-column>
            <el-table-column label="抵押物" align="center" prop="lisPawn">
              <template width="80" slot-scope="scope">
                <el-popover placement="top-start" trigger="click"><!--trigger属性值：hover、click、focus 和 manual-->
                  <a :href="'/dev-api'+scope.row.lisPawn" target="_blank" title="查看最大化图片">
                    <img :src="'/dev-api'+scope.row.lisPawn" style="width: 500px">
                  </a>
                  <img slot="reference" style="width: 80px; border: none; cursor:pointer;"
                       :src="'/dev-api'+scope.row.lisPawn">
                </el-popover>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div v-if="active == 3">
          <el-form ref="form" :model="form" :rules="rules" label-width="80px">
            <el-form-item prop="exaLoaId">
              <el-input v-show="false" v-model="form.exaLoaId" placeholder="请输入贷款ID"/>
            </el-form-item>
            <!--<el-form-item prop="exaName">
              <el-input type="hidden" v-model="form.exaName" placeholder="请输入审批人"/>
            </el-form-item>
            -->
            <el-form-item label="审批理由" prop="exaRemark">
              <el-input v-model="form.exaRemark" type="textarea" placeholder="请输入内容"/>
            </el-form-item>
          </el-form>
        </div>
      </el-form>
    </section>

    <div class="footer">
      <el-button
        v-if="active > 0"
        type="info"
        icon="el-icon-arrow-left"
        @click="prev"
      >上一步
      </el-button>
      <el-button
        v-if="active < step"
        type="primary"
        icon="el-icon-arrow-right"
        @click="next"
      >下一步
      </el-button>
      <el-button
        v-if="active == step"
        type="primary"
        @click="register"
      >驳回
      </el-button>
      <el-button
        v-if="active == step"
        type="primary"
        @click="submitForm"
      >通过
      </el-button>
      <div class="error">{{ error }}</div>
    </div>
  </div>

</template>

<script>
import {listAptitudeById, listLoansById, listMaterialsById} from "@/api/system/loans";
import {addExamine, addNoExamine, updateExamine} from "@/api/system/examine";

export default {
  name: "loanDetails",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 步骤节点数
      step: 3,
      active: 0,
      // 获取贷款数据
      loansOptions: [],
      // 获取材料数据
      materialsList: [],
      //获取贷款数据
      aptitudeList: [],
      sysLoaState: [],
      // 获得当前用户
      user: {},
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        exaLoaId: null,
        exaName: null,
        exaTime: null,
        exaState: null,
        exaRemark: null,
      },
      statusMsg: '',
      error: '',
      isDisable: false,
      codeLoading: false,
      ruleForm: {
        agreed: false,
        name: '',
        code: '',
        pwd: '',
        email: '',
        companyFullName: ''
      },
      // 表单参数
      form: {
        exaLoaId: null
      },
      rules: {}
    }
  },
  created() {
    // 获得传入贷款ID
    const eId = this.$route.params && this.$route.params.eId;
    // 获取贷款数据
    if (eId) {
      this.loading = true;
      listLoansById(eId).then(response => {
        this.loansOptions = response.rows;
        this.loading = false;
      });
    }

    // 获取资质数据
    if (eId) {
      listAptitudeById(eId).then(response => {
        this.aptitudeList = response.rows;
      });
    }
    // 获取材料数据
    if (eId) {
      listMaterialsById(eId).then(response => {
        this.materialsList = response.rows;
      });
    };
    this.form.exaLoaId = eId;

    this.getDicts("sys_loa_state").then(response => {
      this.sysLoaState = response.data;
    });
  },
  methods: {
    // 下一步
    next() {
      if (this.active++ > 3) {
        this.active = 0;
      }
    },
    // 上一步
    prev() {
      if (--this.active < 0) this.active = 0
    },
// 表单重置
    reset() {
      this.form = {
        exaLoaId: null,
        exaName: null,
        exaTime: null,
        exaState: null,
        exaRemark: null,
        exaDel: null
      };
      this.resetForm("form");
    },
    // 取消按钮
    cancel() {
      this.reset();
    },
    /** 通过按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.exaId != null) {
            updateExamine(this.form).then(response => {
              this.msgSuccess("修改成功");
            });
          } else {
            addExamine(this.form).then(response => {
              this.msgSuccess("操作成功");
              this.active++;
              //跳转上一层
              this.$router.go(-1);
            });
          }
        }
      });
    },
    // 驳回按钮
    register: function () {
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          addNoExamine(this.form).then(response => {
            this.msgSuccess("操作成功");
            this.active++;
            //跳转上一层
            this.$router.go(-1);
            // 强制刷新

          });
        }
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.page-register {
  width: 90%;
  margin: 20px auto;

  .register {
    color: #1890ff;
  }

  > section {
    margin: 0 auto 30px;
    padding-top: 30px;
    width: 980px;
    min-height: 300px;
    padding-right: 100px;
    box-sizing: border-box;

    .status {
      font-size: 12px;
      margin-left: 20px;
      color: #e6a23c;
    }

    .error {
      color: red;
    }
  }

  .footer {
    text-align: center;
    margin: 0 auto;
  }

}
</style>
