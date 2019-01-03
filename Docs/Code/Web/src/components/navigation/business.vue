<template lang="pug">
  .nav
    .header
      img.logo(src="../../assets/logo.png")
      | 商家管理端
    el-menu.menu(
      :default-active="activeMenu"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b",
      router
    )
      el-menu-item(index="1", route="/business")
        i.el-icon-goods
        span(slot="title") 商品管理
      el-menu-item(index="2", route="/business/order")
        i.el-icon-tickets
        span(slot="title") 订单管理
      el-menu-item(index="3", route="/business/comment")
        i.el-icon-edit
        span(slot="title") 评论管理
</template>

<script>
const PATHS = ["/business", "/business/order", "/business/order"]
export default {
  data () {
    return {
      activeMenu: '1'
    }
  },
  mounted () {
    this.activeMenu = PATHS.indexOf(this.$route.path) + 1 + ""
  },
  watch: {
    '$route' () {
      this.activeMenu = PATHS.indexOf(this.$route.path) + 1 + ""
    }
  }
}
</script>

<style scoped>
  .nav {
    background: rgb(84, 92, 100);
    border-right: 1px solid rgba(0, 0, 0, 0.1);
  }
  .nav .header {
    color: white;
    height: 60px;
    line-height: 60px;
    border-bottom: 1px solid rgba(0, 0, 0, 0.1);
  }
  .nav .header .logo {
    display: inline-block;
    height: 30px;
    vertical-align: middle;
    margin-right: 10px;
  }
  .nav .menu {
    border-right: none;
  }
</style>

