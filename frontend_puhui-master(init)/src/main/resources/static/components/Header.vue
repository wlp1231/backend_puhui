<template>
  <header class="main-header">
    <div class="topbar">
      <div class="container">
        <div class="topbar__left">
        </div>
        <div class="topbar__right">
          <a href="#"><i class="pylon-icon-email1"></i>needhelp@Joker.com</a>
          <div class="dropdown">
            <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenuDivider"
                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
              <span class="caret" th:text="${session.users}"></span>
            </button>
            <ul class="dropdown-menu" aria-labelledby="dropdownMenuDivider">
              <li><a href="lma?loaDown=personal.html" style="margin-left: 10px; color: black;">个人中心</a>
              </li>
              <li class="list-group-item">
                <a href="me?mgs=fmessages.html" style="margin-left: 10px; color: black;">
                  消息
                  <span id="megText" class="badge">4</span>
                </a>
              </li>
              <!--                            <li>-->
              <!--                                <a href="message?mg=messages.html" style="margin-left: 10px; color: black;">消息</a>-->
              <!--                            </li>-->
              <hr>
              <li><a href="signOut" style="margin-left: 10px; color: black;">退出登录</a></li>
            </ul>
          </div>

        </div><!-- /.topbar__right -->
      </div><!-- /.container -->
    </div><!-- /.topbar -->
    <nav class="main-menu">
      <div class="container">
        <div class="logo-box">
          <a href="index-2.html" aria-label="logo image"><img src="moban5146/assets/images/logo-dark.png"
                                                              width="300" alt=""></a>
          <span class="fa fa-bars mobile-nav__toggler"></span>
        </div>
        <ul class="main-menu__list" id="app2">
          <li class="dropdown">
            <a href="index-2.html">首页</a>
            <ul>
              <li>
                <a href="index-2.html">企业贷款</a>
              </li>
              <li class="dropdown">
                <a href="#">个人贷款</a>
                <ul>
                  <li><a href="index-2.html">信用贷</a></li>
                  <li><a href="index-2.html">订单贷</a></li>
                </ul>
              </li>
            </ul>
          </li>
          <li class="dropdown">
            <a href="#">品牌合作</a>
            <ul>
              <li><a>买好车</a></li>
              <li><a>中国银行</a></li>
            </ul>
          </li>
          <li class="dropdown"><a href="#">产品中心</a>
            <ul>
              <li><a href="lma?loaDown=how-it-works.html">普惠金融</a></li>
              <li><a>信用贷</a></li>
            </ul>
          </li>
          <li class="dropdown"><a href="#">快捷银行</a>
            <ul>
              <li><a href="credit-card">办卡</a></li>
              <li><a>转账</a></li>
            </ul>
          </li>
          <li class="dropdown"><a href="#">其它</a>
            <ul>
              <li><a href="/actlist">活动信息</a></li>
              <li><a href="/rate">利率计算</a></li>
              <li class="dropdown">
                <a href="#">宣传信息</a>
                <ul>
                  <li><a href="/news/?type=1">法律法规</a></li>
                  <li><a href="/news/?type=2">贷款知识</a></li>
                  <li><a href="/news/?type=3">公司新闻</a></li>
                  <li><a href="/news/?type=4">常见问题</a></li>
                </ul>
              </li>
              <li><a href="/company/showMessage">用户留言</a></li>
              <li v-if="isLogin" @click="vite()"><a>邀请分享</a></li>
            </ul>
          </li>
          <li class="search-btn search-toggler">
            <a href="#"><i class="pylon-icon-magnifying-glass"></i></a>
          </li>
        </ul>
        <div class="main-header__info">
          <div class="main-header__info-phone">
            <i class="pylon-icon-tech-support"></i>
            <div class="main-header__info-phone-content">
              <span>联系电话</span>
              <h3><a href="tel:92-666-888-0000">92 666 888 0000</a></h3>
            </div>
          </div>
        </div>
      </div>
    </nav>
  </header>
</template>
<script>
module.exports = {
  name: "Myheader"
}
</script>
