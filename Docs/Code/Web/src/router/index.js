import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

import Cache from '@/utils/cache';

import Home from '@/components/Home'
import Signin from '@/components/Signin'
import NormalNav from '@/components/navigation/normal'
import BussinessNav from '@/components/navigation/business'
import BussinessDashboard from '@/components/business/dashboard'
import BussinessDishesManagement from '@/components/business/DishesManagement'

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
      path: '/signin',
      name: 'signin',
      components: {
        navigation: NormalNav,
        content: Signin
      }
    },
    {
      path: '/business',
      name: 'business',
      components: {
        navigation: BussinessNav,
        content: BussinessDashboard
      },
      children: [{
        path: '',
        component: BussinessDishesManagement
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