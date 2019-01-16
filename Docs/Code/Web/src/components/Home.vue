<template lang="pug">
  .home-page
    el-card.login-box
      .login-header(slot="header")
        |「吃点啥」登陆窗口
      el-form.login-form(:model="loginForm", :rules="rules", status-icon, ref="loginForm")
        el-form-item(label="用户名：", prop="username")
          el-input(v-model="loginForm.username", @keyup.enter.native="login")
        el-form-item(label="密码：", prop="password")
          el-input(type="password", v-model="loginForm.password", auto-complete="off", @keyup.enter.native="login")
      .login-footer
        el-button.login-button(type="primary", plain, @click="login") 登陆
        el-button(type="text", @click="signup") 没有账号？注册成为商家
</template>

<script>
import Cache from '@/utils/cache'
import UserUtils from '@/utils/user'
import authService from '@/api/rest/auth.js'

export default {
  name: 'Home',
  data () {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      }
    }
  },
  mounted () {
    Cache.removeToken()
    UserUtils.removeUserInfo()
  },
  methods: {
    login () {
      this.$refs.loginForm.validate(isValid => {
        if (isValid) {
          authService.login(this.loginForm)
            .then(success => {
              let {token, obj} = success.data
              Cache.setToken(token)
              UserUtils.setUserInfo(obj)
              this.$router.push('/business')
            }).catch(fail => {
              this.showError('登陆失败，请再次检查账号和密码')
            })
        } else {
          this.showError('请输入正确的账号密码')
          return false
        }
      })
    },
    signup () {
      this.$router.push({ name: 'signup' })
    },
    showError (msg) {
      this.$message({
        showClose: true,
        message: msg,
        type: 'error'
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .login-page {
    position: relative;
    height: 100%;
    width: 100%;
    background-size: cover;
    background-repeat: no-repeat;
    background-position: bottom;
  }
  .login-box {
    position: relative;
    height: 400px;
    width: 400px;
    margin: 100px auto;
  }
  .login-header {
    color: #409EFF;
    height: 35px;
    line-height: 35px;
    font-size: 18px;
  }

  .login-footer {
    position: absolute;
    bottom: 10px;
    left: 0px;
    width: 100%;
    padding: 0px 20px;
    box-sizing: border-box;
  }

  .login-footer .login-button {
    width: 100%;
  }
</style>
