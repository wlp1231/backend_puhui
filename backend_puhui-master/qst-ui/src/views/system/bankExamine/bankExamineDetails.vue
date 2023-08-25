<template>
  <div class="page-register">
    <el-steps :active="active" finish-status="success">
      <el-step title="贷款信息"></el-step>
      <el-step title="贷款材料"></el-step>
      <el-step title="贷款资质"></el-step>
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
          <el-table v-loading="loading" :data="examineOptions" >
            <el-table-column label="编号" align="center" prop="exaId" />
            <el-table-column label="贷款ID" align="center" prop="exaLoaId" />
            <el-table-column label="审批人" align="center" prop="exaName" />
            <el-table-column label="审批时间" align="center" prop="exaTime" width="180">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.exaTime, '{y}-{m}-{d}') }}</span>
              </template>
            </el-table-column>
            <el-table-column label="审批状态" align="center" prop="exaState">
            </el-table-column>
            <el-table-column label="审批理由" align="center" prop="exaRemark" />
          </el-table>
        </div>
        <div v-if="active == 1">
          <el-form-item label="用户名" prop="name">
            <el-col :span="10">
              <el-input v-model="ruleForm.name"/>
            </el-col>
          </el-form-item>
        </div>
        <div v-if="active == 2">
          <el-form-item prop="textarea">
            <el-input
              type="textarea"
              :autosize="{ minRows: 2, maxRows: 10 }"
              :readonly="true"
              v-html="ruleForm.textarea"
            >
            </el-input>
          </el-form-item>
          <el-form-item prop="agreed">
            <el-checkbox v-model="ruleForm.agreed">同意注册协议</el-checkbox>
          </el-form-item>
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
      >注册
      </el-button>
      <div class="error">{{ error }}</div>
    </div>
  </div>

</template>

<script>
import { listExamineById} from "@/api/system/examine";

export default {
  name: "examineDetails",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 步骤节点数
      step: 3,
      active: 0,
      // 获取贷款数据
      examineOptions: [],
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
      rules: {
      }
    }
  },
  created() {
    const beId = this.$route.params && this.$route.params.beId;
    if (beId){
      console.log(beId)
      this.loading = true;
      listExamineById(beId).then(response => {
        this.examineOptions = response.rows;
        this.loading = false;
      });
    }
  },
  methods: {
    next() {
      if (this.active++ > 3) {
        this.active = 0;
      }
    },
    prev() {
      if (--this.active < 0) this.active = 0
    },

    // 用户注册
    register: function () {
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          const user = {
            username: this.ruleForm.email,
            nickName: this.ruleForm.name,
            code: this.ruleForm.code,
            email: this.ruleForm.email,
            phone: this.ruleForm.phone,
            enabled: true
          }
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
