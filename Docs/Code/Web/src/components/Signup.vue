<template>
  <!-- TODO -->
  <div class="hello">
    <el-button @click="test">TODO</el-button>
  </div>
</template>

<script>
import auth from '@/api/rest/auth.js'

export default {
  name: 'signup',
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
.hello {
  margin-top: 100px;
}
</style>
