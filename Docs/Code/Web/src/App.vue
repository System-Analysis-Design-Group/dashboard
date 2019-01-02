<template lang="pug">
  #app
    router-view.navigation(name="navigation")
    router-view.main-content(name="content")
</template>

<script>
export default {
}
</script>

<style>
#app {
  font-family: Helvetica, sans-serif;
  text-align: center;
  height: 100%;
  display: flex;
}
.navigation {
  flex: 0 0 200px;
}
.main-content {
  flex: auto;
}
</style>
