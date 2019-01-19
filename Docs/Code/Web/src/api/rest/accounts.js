import fetch from '@/api/fetch.js'
import FormUtil from '@/utils/form'

const AccountsServicePrefix = "/AccountService"

class AccountsService {
  static ChineseStore = "Chinese"

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
  static getUserAvator (userId) {
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
  static getAllStores () {
    return fetch({
      url: `${AccountsServicePrefix}/Accounts/Stores`,
      method: 'get',
    })
  }
  static createStore (formData) {
    let uploadData = {}
    FormUtil.assignTo(formData, uploadData, [
      "name", "phone", "type", "user_id", "address", "longitude", "latitude"
    ])
    return fetch({
      url: `${AccountsServicePrefix}/Accounts/Stores`,
      method: 'post',
      data: uploadData,
    })
  }
  static _getSomeTypeOfStores (type) {
    return fetch({
      url: `${AccountsServicePrefix}/Accounts/Stores/${type}`,
      method: 'get',
    })
  }
  static getChineseStore () {
    return this._getSomeTypeOfStores(this.ChineseStore)
  }
}

export default AccountsService