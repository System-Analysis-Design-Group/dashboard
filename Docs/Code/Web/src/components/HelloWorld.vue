<template>
  <div class="hello">
    <el-button @click="test">默认按钮</el-button>
  </div>
</template>

<script>
import auth from '@/api/rest/auth.js'

export default {
  name: 'HelloWorld',
  data () {
    return {
    }
  },
  methods: {
    test () {
      auth.login({ username: 'aaa', password: 'ss' })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
