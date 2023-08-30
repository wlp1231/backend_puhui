import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: 路由配置项
 *
 * hidden: true                   // 当设置 true 的时候该路由不会再侧边栏出现 如401，login等页面，或者如一些编辑页面/edit/1
 * alwaysShow: true               // 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
 *                                // 只有一个时，会将那个子路由当做根路由显示在侧边栏--如引导页面
 *                                // 若你想不管路由下面的 children 声明的个数都显示你的根路由
 *                                // 你可以设置 alwaysShow: true，这样它就会忽略之前定义的规则，一直显示根路由
 * redirect: noRedirect           // 当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
 * name:'router-name'             // 设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题
 * meta : {
    noCache: true                // 如果设置为true，则不会被 <keep-alive> 缓存(默认 false)
    title: 'title'               // 设置该路由在侧边栏和面包屑中展示的名字
    icon: 'svg-name'             // 设置该路由的图标，对应路径src/assets/icons/svg
    breadcrumb: false            // 如果设置为false，则不会在breadcrumb面包屑中显示
    activeMenu: '/system/user'   // 当路由设置了该属性，则会高亮相对应的侧边栏。
  }
 */

// 公共路由
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: (resolve) => require(['@/views/redirect'], resolve)
      }
    ]
  },
  {
    path: '/login',
    component: (resolve) => require(['@/views/login'], resolve),
    hidden: true
  },
  {

    path: '/refund',
    component: (resolve) => require(['@/views/system/refund/index'], resolve),
    hidden: true
  },
  {
    path: '/bank/bank',
    component: (resolve) => require(['@/views/system/bank/index'], resolve),
    hidden: true
  },
  {
    path: '/overdue',
    component: (resolve) => require(['@/views/system/overdue/index'], resolve),
    hidden: true
  },
  {
    path: '/loans',
    component: (resolve) => require(['@/views/system/loans/index'], resolve),
    hidden: true
  },
  {
    path: '/bankLoans',
    component: (resolve) => require(['@/views/system/bankExamine/index'], resolve),
    hidden: true
  },
  {
    path: '/examine',
    component: (resolve) => require(['@/views/system/examine/index'], resolve),
    hidden: true
  },
  {
    path: '/materials',
    component: (resolve) => require(['@/views/system/materials/index'], resolve),
    hidden: true
  },

  {
    path: '/register',
    component: (resolve) => require(['@/views/register'], resolve),
    hidden: true
  },
  {
    path: '/404',
    component: (resolve) => require(['@/views/error/404'], resolve),
    hidden: true
  },
  {
    path: '/401',
    component: (resolve) => require(['@/views/error/401'], resolve),
    hidden: true
  },
  {
    path: '',
    component: Layout,
    redirect: 'index',
    children: [
      {
        path: 'index',
        component: (resolve) => require(['@/views/index_v1'], resolve),
        name: 'Index',
        meta: {title: '首页', icon: 'dashboard', affix: true}
      }
    ]
  },
  {
    path: '/user',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'profile',
        component: (resolve) => require(['@/views/system/user/profile/index'], resolve),
        name: 'Profile',
        meta: {title: '个人中心', icon: 'user'}
      }
    ]
  },
  {
    path: '/system/user-auth',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'role/:userId(\\d+)',
        component: (resolve) => require(['@/views/system/user/authRole'], resolve),
        name: 'AuthRole',
        meta: {title: '分配角色', activeMenu: '/system/user'}
      }
    ]
  },
  {
    path: '/system/role-auth',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'user/:roleId(\\d+)',
        component: (resolve) => require(['@/views/system/role/authUser'], resolve),
        name: 'AuthUser',
        meta: {title: '分配用户', activeMenu: '/system/role'}
      }
    ]
  },
  {
    path: '/system/dict-data',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'index/:dictId(\\d+)',
        component: (resolve) => require(['@/views/system/dict/data'], resolve),
        name: 'Data',
        meta: {title: '字典数据', activeMenu: '/system/dict'}
      }
    ]
  },
  {
    path: '/monitor/job-log',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'index',
        component: (resolve) => require(['@/views/monitor/job/log'], resolve),
        name: 'JobLog',
        meta: {title: '调度日志', activeMenu: '/monitor/job'}
      }
    ]
  },
  {
    path: '/tool/gen-edit',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'index/:tableId(\\d+)',
        component: (resolve) => require(['@/views/tool/gen/editTable'], resolve),
        name: 'GenEdit',
        meta: {title: '修改生成配置', activeMenu: '/tool/gen'}
      }
    ]
  },
  {
    path: '/company/comuser',
    component: (resolve) => require(['@/views/system/company/index'], resolve),
    hidden: true
  },
  {
    path: '/details',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'companyDetails/:cId(\\d+)',
        component: (resolve) => require(['@/views/system/details/companyDetails'], resolve),
        name: 'company',
        meta: {title: '查看详情', activeMenu: '/system/details'}
      }
    ]
  },
  {
    path: '/bankLoans',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'bankLoanDetails/:beId(\\d+)',
        component: (resolve) => require(['@/views/system/bankLoans/bankLoanDetails'], resolve),
        name: 'bankLoans',
        meta: {title: '贷款审批', activeMenu: '/system/bankLoans'}
      }
    ]
  },
  {

    path: '/send',
    component: (resolve) => require(['@/views/system/send/index'], resolve),
    hidden: true
  },

  {

    path: '/company/comuser',

    component: (resolve) => require(['@/views/system/company/index'], resolve),

    hidden: true
  },
  {

    path: '/details',

    component: Layout,

    hidden: true,

    children: [

      {

        path: 'companyDetails/:cId(\\d+)',

        component: (resolve) => require(['@/views/system/details/companyDetails'], resolve),

        name: 'company',

        meta: {title: '查看详情', activeMenu: '/system/details'}

      }

    ]

  },
  {
    path: '/loans',
    component: Layout,
    hidden: true,
    children: [
      {
        path: 'loanDetails/:eId(\\d+)',
        component: (resolve) => require(['@/views/system/loans/loanDetails'], resolve),
        name: 'loans',
        meta: {title: '贷款审批', activeMenu: '/system/loans'}
      }
    ]
  },
  {

    path: '/bank/bank',
    component: (resolve) => require(['@/views/system/bank/index'], resolve),
    hidden: true},

]

export default new Router({
  mode: 'history', // 去掉url中的#
  scrollBehavior: () => ({y: 0}),
  routes: constantRoutes
})
