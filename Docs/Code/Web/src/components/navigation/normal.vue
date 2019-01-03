<template lang="pug">
  .nav-bar
    img.logo(src="../../assets/logo.png")
    .poster 你没有尝试过的「船新版本」
</template>

<script>

export default {
  data () {
    return {
    }
  }
}
</script>

<style scoped>
  .nav-bar {
    position: fixed;
    left: 0px;
    top: 0px;
    width: 100%;
    background: #409EFF;
    padding: 6px 60px;
    color: white;
  }
  .nav-bar .logo {
    width: 50px;
    float: left;
  }
  .nav-bar .poster {
    position: relative;
    margin-left: 40px;
    float: left;
    line-height: 50px;
    font-size: 18px;
  }
  .nav-bar .poster::before {
    content: '';
    position: absolute;
    height: 46px;
    width: 1px;
    left: -20px;
    top: 2px;
    display: block;
    background: white;
  }
</style>

