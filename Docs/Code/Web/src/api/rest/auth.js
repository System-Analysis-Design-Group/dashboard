import fetch from '@/api/fetch.js'
import FormUtil from '@/utils/form'

const AuthServicePrefix = "/AccountService"

class AuthService {
  static login(formData) {
    let uploadData = {}
    FormUtil.assignTo(formData, uploadData, [
      "username", "password",
    ])
    return fetch({
      url: `${AuthServicePrefix}/Accounts/Signin`,
      method: 'post',
      data: uploadData,
    })
  }
  static logout() {
    return fetch({
      url: `${AuthServicePrefix}/Accounts/logout`,
      method: 'get',
    })
  }
}

export default AuthService