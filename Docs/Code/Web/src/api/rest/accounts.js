import fetch from '@/api/fetch.js'
import FormUtil from '@/utils/form'

const AccountsServicePrefix = "/AccountService"

class AccountsService {
  static createAccount (formData) {
    let uploadData = {}
    FormUtil.assignTo(formData, uploadData, [
      "username", "password",
    ])
    return fetch({
      url: `${AccountsServicePrefix}/Accounts/Signup`,
      method: 'post',
      data: uploadData,
    })
  }
  static deleteAccount(userId) {
    return fetch({
      url: `${AccountsServicePrefix}/Accounts/Customer/${userId}`,
      method: 'delete',
    })
  }
  static getUserInfo (userId) {
    return fetch({
      url: `${AccountsServicePrefix}/Accounts/Customer/${userId}`,
      method: 'get',
    })
  }
  static getUserAvator (userid) {
    return fetch({
      url: `${AccountsServicePrefix}/Accounts/Avator/${userId}`,
      method: 'get',
    })
  }
  static getUserPermission (userId) {
    return fetch({
      url: `${AccountsServicePrefix}/Accounts/Permission/${userId}`,
      method: 'get',
    })
  }
}

export default AccountsService