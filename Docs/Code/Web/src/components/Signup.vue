<template lang="pug">
  .home-page
    el-card.signup-box
      .signup-header(slot="header")
        |「吃点啥」注册窗口
      el-form.signup-form(v-if="!accountCreated", :model="userForm", label-width="100px", :rules="userRules", status-icon, ref="userForm")
        el-form-item(label="用户名：", prop="username")
          el-input(v-model="userForm.username")
        el-form-item(label="密码：", prop="password")
          el-input(type="password", v-model="userForm.password")
        el-form-item(label="确认密码：", prop="checkPassword")
          el-input(type="password", v-model="userForm.checkPassword")
      el-form.signup-form(v-else, :model="storeForm", label-width="100px", :rules="storeRules", status-icon, ref="storeForm")
        el-form-item(label="店名：", prop="name")
          el-input(v-model="storeForm.name")
        el-form-item(label="联系方式：", prop="phone")
          el-input(v-model="storeForm.phone")
        el-form-item(label="菜系：", prop="type")
          el-input(v-model="storeForm.type")
        el-form-item(label="地址：", prop="address")
          el-input(v-model="storeForm.address")
        el-form-item(label="经度：", prop="longitude")
          el-input(v-model="storeForm.longitude", type="number", min=-180, max=180, step=0.01)
        el-form-item(label="纬度：", prop="latitude")
          el-input(v-model="storeForm.latitude", type="number", min=-90, max=90, step=0.01)
      .signup-footer
        el-button.signup-button(v-if="!accountCreated", type="primary", plain, @click="createAccount") 下一步
        el-button.signup-button(v-else, type="primary", plain, @click="createStore") 完成注册
</template>

<script>
import AccountsService from '@/api/rest/accounts'

export default {
  name: 'signup',
  data () {
    let validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.userForm.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      userForm: {
        username: '',
        password: '',
        checkPassword: '',
      },
      storeForm: {
        name: '',
        phone: '',
        type: '',
        address: '',
        longitude: 0.0,
        latitude: 0.0,
        user_id: -1
      },
      userRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        checkPassword: [
          { required: true, validator: validatePass, trigger: 'blur' }
        ]
      },
      storeRules:{
        name: [
          { required: true, message: '请输入店名', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请输入联系方式', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '请输入菜系', trigger: 'blur' }
        ],
        address: [
          { required: true, message: '请输入地址', trigger: 'blur' }
        ],
        longitude: [
          { required: true, message: '请输入经度', trigger: 'blur' }
        ],
        latitude: [
          { required: true, message: '请输入纬度', trigger: 'blur' }
        ],                
      },
      accountCreated: false
    }
  },
  methods: {
    createAccount() {
      this.$refs.userForm.validate(isValid => {
        if (isValid) {
          AccountsService.createAccount(this.userForm)
            .then(res => {
              this.storeForm.user_id = res.data.obj.userid
              this.accountCreated = true
              this.$nextTick(()=>this.$refs.storeForm.resetFields())
            }).catch(err => {
              this.showError(err.message)
            })
        } else {
          this.showError('请输入正确的信息！')
        }
      })
    },
    createStore() {
      this.$refs.storeForm.validate(isValid => {
        if (isValid) {
          AccountsService.createStore(this.storeForm)
            .then(res => {
              this.$message({
                message: '注册成功！',
                type: 'success'
              });
              this.$router.push('/')
            }).catch(err => {
              this.showError(err.message)
            })
        } else {
          this.showError('请输入正确的信息！')
        }
      })
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
  .signup-page {
    position: relative;
    height: 100%;
    width: 100%;
    background-size: cover;
    background-repeat: no-repeat;
    background-position: bottom;
  }
  .signup-box {
    position: relative;
    width: 400px;
    margin: 100px auto;
  }
  .signup-header {
    color: #409EFF;
    height: 35px;
    line-height: 35px;
    font-size: 18px;
  }

  .signup-footer {
    width: 100%;
    padding: 10px 20px;
    box-sizing: border-box;
  }

  .signup-footer .signup-button {
    width: 100%;
  }
</style>
