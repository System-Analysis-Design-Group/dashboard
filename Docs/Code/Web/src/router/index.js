import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

import Cache from '@/utils/cache';

import HelloWorld from '@/components/HelloWorld'

let router = new VueRouter({
  routes: [
    {
      path: '/',
      name: 'login',
      components: {
        content: HelloWorld
      }
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

router.beforeEach((to, from, next) => {
  if (to.name !== 'login' && !Cache.getToken()) {
    router.push({ name: 'login' })
  } else {
    next()
  }
})

export default router