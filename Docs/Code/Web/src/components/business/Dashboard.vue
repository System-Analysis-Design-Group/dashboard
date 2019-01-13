<template lang="pug">
  .dashboard
    .header
      el-breadcrumb.nav(separator="/")
        el-breadcrumb-item 首页
        el-breadcrumb-item {{subMenuName}}
      .logout
        el-button(round, @click="logout") 退出
    router-view
</template>

<script>
import Cache from '@/utils/cache'
import authService from '@/api/rest/auth'

const MENUS = ["商品管理", "订单管理", "评论管理"]
const PATHS = ["/business", "/business/orders", "/business/comments"]

export default {
  data () {
    return {
      subMenuName: "商品管理"
    }
  },
  created () {
    this.subMenuName = MENUS[PATHS.indexOf(this.$route.path)]
  },
  methods: {
    logout () {
      authService.logout()
        .then(success => {
          Cache.removeToken()
          this.$router.push("/")
        }).catch(error => {
          this.$message({
            showClose: true,
            message: '退出失败',
            type: 'error'
          })
        })
    }
  },
  watch: {
    '$route' () {
      this.subMenuName = MENUS[PATHS.indexOf(this.$route.path)]
    }
  }
}
</script>

<style scoped>
  .dashboard {
    overflow: auto;
  }
  .dashboard .header {
    position: relative;
    height: 60px;
    border-bottom: 1px solid rgba(0, 0, 0, 0.1);
    padding-left: 20px;
  }
  .dashboard .header .nav {
    line-height: 60px;
  }
  .dashboard .logout {
    position: absolute;
    right: 10px;
    top: 10px;
  }
</style>

