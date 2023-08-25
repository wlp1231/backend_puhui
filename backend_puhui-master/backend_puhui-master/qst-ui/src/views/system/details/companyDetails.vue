<template>
  <div class="app-container">
    <h4 class="form-header h4">基本信息</h4>
    <div class="dashboard-editor-container">
        <h3 class="comName">
          企业名称：{{ companies['company']['comName']}}
        </h3>
        <h4 class="comName">
          企业积分：{{ companies.cIntegral}}
        </h4>
<!--        <img class="user-avatar" :src="'/dev-api'+companies.cLicense" @click="handleChangeImg($event)" />-->

      <el-row>

      </el-row>
      <div class="content">
        营业执照：
        <viewer :images="licenseImg">
          <img class="user-avatar" v-for="(src,index) in licenseImg" :src="publicPath+companies.cLicense" :key="index">
        </viewer>
        有效期：{{companies.cLicTime}}
      </div>
      <div class="content">
        <h4>法人姓名：{{companies.cLegalName}}</h4>

        <h4>法人证件号：{{companies.cLegalId}}</h4>
        证件有效期：{{companies.cLegalTime}}
      </div>
    </div>

    <el-form label-width="100px">
      <el-form-item style="text-align: center;margin-left:-120px;margin-top:30px;">
        <el-button @click="close()">返回</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {getCompanies} from "@/api/system/details";

export default {
  name: "companyDetails",
  data() {
    return {
      licenseImg: [{
        url: ''
      }],
      publicPath: '/dev-api',
      loading: true,
      companies: {},
      dialogVisible: false,
      open: false,
    };
  },
  created() {
    const cId = this.$route.params && this.$route.params.cId;
    if (cId) {
      getCompanies(cId).then((response) => {
        this.companies = response.data;
      });
    };
    this.licenseImg.url = '/dev-api'+this.companies.cLicense;
  },
  methods: {
    /** 关闭按钮 */
    close() {
      this.$router.back();
      // this.$store.dispatch("tagsView/delView", this.$route);
      // this.$router.push({path: "/system/details/index"});
    },
  },
};
</script>
<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;
  text-align: center;

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
  .user-avatar {
    cursor: pointer;
    width: 240px;
    height: 240px;
    border-radius: 5px;
  }
  .user-avatar.open{
    cursor: zoom-out;
    max-width: 800px;
    max-height: 1000px;
  }
}
.comName{
  margin: 0 auto;
}

</style>
