import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

import Cache from '@/utils/cache';

import Home from '@/components/Home'
import Signup from '@/components/Signup'
import NormalNav from '@/components/navigation/normal'
import BusinessNav from '@/components/navigation/business'
import BusinessDashboard from '@/components/business/dashboard'
import BusinessDishesManagement from '@/components/business/DishesManagement'
import BusinessOrdersManagement from '@/components/business/OrdersManagement'
import BusinessCommentsManagement from '@/components/business/CommentsManagement'

let router = new VueRouter({
  routes: [
    {
      path: '/',
      name: 'login',
      components: {
        navigation: NormalNav,
        content: Home
      }
    },
    {
      path: '/signup',
      name: 'signup',
      components: {
        navigation: NormalNav,
        content: Signup
      }
    },
    {
      path: '/business',
      name: 'business',
      components: {
        navigation: BusinessNav,
        content: BusinessDashboard
      },
      children: [{
        path: '',
        component: BusinessDishesManagement
      }, {
        path: 'orders',
        component: BusinessOrdersManagement
      }, {
        path: 'comments',
        component: BusinessCommentsManagement
      }]
    },
    // {
    //   path: '/dashboard',
    //   name: 'dashboard',
    //   components: {
    //     navigation: Navigation,
    //     content: Dashboard
    //   },
    //   children: [{
    //     path: '',
    //     component: CategoryManagement
    //   }, {
    //     path: 'review',
    //     component: AnalysisReview
    //   }, {
    //     path: 'download',
    //     component: DownloadManagement
    //   }]
    // },
  ]
})

// router.beforeEach((to, from, next) => {
//   if (to.name !== 'signin' && to.name !== 'login' && !Cache.getToken()) {
//     router.push({ name: 'login' })
//   } else {
//     next()
//   }
// })

export default router